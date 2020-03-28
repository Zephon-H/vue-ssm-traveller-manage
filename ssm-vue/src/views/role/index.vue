<template>
  <div class="app-container">
    <div class="app-container">
      <!--    <el-button type="primary">新建</el-button>-->
      <el-button
        class="filter-item"
        style="margin-left: 10px;"
        type="primary"
        icon="el-icon-edit"
        @click="handleCreate">
        添加
      </el-button>
    </div>

    <el-table
      :key="tableKey"
      v-loading="listLoading"
      :data="list"
      element-loading-text="Loading"
      border
      fit
      highlight-current-row
    >
      <el-table-column align="center" label="ID">
        <template slot-scope="scope">
          {{ scope.$index }}
        </template>
      </el-table-column>

      <el-table-column align="center" label="角色名称">
        <template slot-scope="scope">
          {{ scope.row.roleName }}
        </template>
      </el-table-column>

      <el-table-column align="center" label="描述">
        <template slot-scope="scope">
          {{ scope.row.roleDesc }}
        </template>
      </el-table-column>
      <el-table-column label="操作" align="center">
        <template slot-scope="scope">
          <router-link :to="'role/detail/'+scope.row.id">
            <el-button
              size="mini"
              type="primary">详情
            </el-button>
          </router-link>
          <el-button
            size="mini"
            type="primary"
            @click="deleteRole(scope.row.id)">删除角色
          </el-button>
          <el-button
            size="mini"
            type="primary"
            @click="showPermissionDialog(scope.row.id)">添加权限
          </el-button>
        </template>
      </el-table-column>
      <!--      <el-user-column align="center" prop="created_at" label="Display_time" width="200">-->
      <!--        <template slot-scope="scope">-->
      <!--          <i class="el-icon-time" />-->
      <!--          <span>{{ scope.row.display_time }}</span>-->
      <!--        </template>-->
      <!--      </el-user-column>-->
    </el-table>
    <pagination v-show="total>0" :total="total" :page.sync="listQuery.page" :limit.sync="listQuery.limit" @pagination="getPageList" />

    <el-dialog title="角色信息" :visible.sync="dialogFormVisible">
      <el-form :model="form">
        <el-form-item label="角色名称" :label-width="formLabelWidth">
          <el-input v-model="form.roleName" auto-complete="off"></el-input>
        </el-form-item>
        <el-form-item label="角色描述" :label-width="formLabelWidth">
          <el-input v-model="form.roleDesc" auto-complete="off"></el-input>
        </el-form-item>
      </el-form>
      <div slot="footer" class="dialog-footer">
        <el-button @click="dialogFormVisible = false">取 消</el-button>
        <el-button type="primary" @click="add">确 定</el-button>
      </div>
    </el-dialog>

    <el-dialog title="添加权限" :visible.sync="dialogPermissionAddVisible">
      <el-table
        ref="multipleTable"
        :data="otherPermissions"
        tooltip-effect="dark"
        style="width: 100%"
        @selection-change="handleSelectionChange">
        <el-table-column
          type="selection"
          width="55">
        </el-table-column>
        <el-table-column
          prop="id"
          label="ID">
          <template slot-scope="scope">{{ scope.row.id }}</template>
        </el-table-column>
        <el-table-column
          prop="permissionName"
          label="权限名称">
          <template slot-scope="scope">{{ scope.row.permissionName }}</template>
        </el-table-column>
        <el-table-column
          prop="url"
          label="权限URL">
          <template slot-scope="scope">{{ scope.row.url }}</template>
        </el-table-column>
      </el-table>
      <div slot="footer" class="dialog-footer">
        <el-button @click="dialogPermissionAddVisible = false">取 消</el-button>
        <el-button type="primary" @click="addPermission">确 定</el-button>
      </div>
    </el-dialog>
  </div>
</template>

<script>
import { addRole, getPageList, getOtherPermission, addOtherPermission, deleteRole } from '@/api/role'
import Pagination from '@/components/Pagination'

export default {
  components: {
    Pagination
  },
  filters: {
    statusFilter(status) {
      const statusMap = {
        1: 'success',
        0: 'danger'
      }
      return statusMap[status]
    }
  },
  data() {
    return {
      roleId: '',
      tableKey: 0,
      list: null,
      total: 0,
      listLoading: true,
      listQuery: {
        page: 1,
        limit: 20,
        importance: undefined,
        title: undefined,
        type: undefined
      },
      dialogFormVisible: false,
      dialogPermissionAddVisible: false,
      form: {
        roleName: '',
        roleDesc: ''
      },
      formLabelWidth: '120px',
      otherPermissions: null,
      multipleSelection: []
    }
  },
  created() {
    // this.fetchData()
    this.getPageList()
  },
  methods: {
    getPageList() {
      this.listLoading = true
      getPageList(this.listQuery).then(response => {
        this.list = response.data.list
        this.total = response.data.total
        // this.total = response.data.total
        // Just to simulate the time of the request
        setTimeout(() => {
          this.listLoading = false
        }, 1.5 * 1000)
      })
    },
    handleCreate() {
      this.dialogFormVisible = true
    },
    add() {
      this.dialogFormVisible = false
      addRole(this.form).then(response => {
        this.$message({
          message: '添加成功',
          type: 'success'
        })
        history.go(0)
        // this.total = response.data.total
        // Just to simulate the time of the request
        setTimeout(() => {
          this.listLoading = false
        }, 1.5 * 1000)
      })
    },
    showPermissionDialog(id) {
      this.dialogPermissionAddVisible = true
      getOtherPermission(id).then(response => {
        this.otherPermissions = response.data
        this.roleId = id
      })
    },
    deleteRole(id) {
      deleteRole(id).then(response => {
        this.$message({
          message: '删除成功',
          type: 'success'
        })
        history.go(0)
      })
    },
    addPermission(id) {
      let permissionIds = []
      for (let i = 0; i < this.multipleSelection.length; i++) {
        permissionIds[i] = this.multipleSelection[i].id
      }
      let r = {}
      r.roleId = this.roleId
      r.permissionIds = permissionIds.toString()
      console.log(r)
      addOtherPermission(r).then(response => {
        this.$message({
          message: '添加成功',
          type: 'success'
        })
        history.go(0)
        this.dialogPermissionAddVisible = false
        // this.total = response.data.total
        // Just to simulate the time of the request
        setTimeout(() => {
          this.listLoading = false
        }, 1.5 * 1000)
      })
      // this.dialogPermissionAddVisible = false
    },
    //
    // handleFilter() {
    //   this.listQuery.page = 1
    //   this.getPageList()
    // },
    // fetchData() {
    //   this.listLoading = true
    //   getList().then(response => {
    //     this.list = response.data
    //     this.listLoading = false
    //     console.log(this.list)
    //   })
    // }
    toggleSelection(rows) {
      if (rows) {
        rows.forEach(row => {
          this.$refs.multipleTable.toggleRowSelection(row)
        })
      } else {
        this.$refs.multipleTable.clearSelection()
      }
    },
    handleSelectionChange(val) {
      this.multipleSelection = val
    }
  }
}
</script>
