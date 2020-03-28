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

      <el-table-column align="center" label="权限名称">
        <template slot-scope="scope">
          {{ scope.row.permissionName }}
        </template>
      </el-table-column>

      <el-table-column align="center" label="URL">
        <template slot-scope="scope">
          {{ scope.row.url }}
        </template>
      </el-table-column>
      <el-table-column label="操作" align="center" width="200">
        <template slot-scope="scope">
          <router-link :to="'permission/detail/'+scope.row.id">
            <el-button
              size="mini"
              type="primary">详情
            </el-button>
          </router-link>
          <el-button
            size="mini"
            type="primary"
            @click="deletePermission(scope.row.id)">删除权限
          </el-button>
<!--          <el-button-->
<!--            size="mini"-->
<!--            type="primary"-->
<!--            @click="handleDelete(scope.row.id, scope.row)">添加角色-->
<!--          </el-button>-->
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

    <el-dialog title="资源权限信息" :visible.sync="dialogFormVisible">
      <el-form :model="form">
        <el-form-item label="权限名称" :label-width="formLabelWidth">
          <el-input v-model="form.permissionName" auto-complete="off"></el-input>
        </el-form-item>
        <el-form-item label="URL" :label-width="formLabelWidth">
          <el-input v-model="form.url" auto-complete="off"></el-input>
        </el-form-item>
      </el-form>
      <div slot="footer" class="dialog-footer">
        <el-button @click="dialogFormVisible = false">取 消</el-button>
        <el-button type="primary" @click="add">确 定</el-button>
      </div>
    </el-dialog>
  </div>
</template>

<script>
import {addPermission,getPageList,deletePermission} from '@/api/permission'
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
      form: {
        permissionName: '',
        url: ''
      },
      formLabelWidth: '120px'
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
      addPermission(this.form).then(response => {
        console.log(response)
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
    deletePermission(id) {
      deletePermission(id).then(response => {
        this.$message({
          message: '删除成功',
          type: 'success'
        })
        history.go(0)
      })
    }
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
  }
}
</script>
