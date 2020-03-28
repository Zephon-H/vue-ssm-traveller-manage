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

      <el-table-column align="center" label="用户名">
        <template slot-scope="scope">
          {{ scope.row.username }}
        </template>
      </el-table-column>

      <el-table-column align="center" label="邮箱">
        <template slot-scope="scope">
          {{ scope.row.email }}
        </template>
      </el-table-column>

      <el-table-column align="center" label="联系电话">
        <template slot-scope="scope">
          {{ scope.row.phoneNum }}
        </template>
      </el-table-column>

      <el-table-column class-name="status-col" label="状态" align="center">
        <template slot-scope="scope">
          <el-tag :type="scope.row.status | statusFilter">{{ scope.row.status==1?'开启':'未开启' }}</el-tag>
        </template>
      </el-table-column>
      <el-table-column label="操作" align="center" width="200">
        <template slot-scope="scope">
          <router-link :to="'user/detail/'+scope.row.id">
            <el-button
              size="mini"
              type="primary">详情
            </el-button>
          </router-link>
          <el-button
            size="mini"
            type="primary"
            @click="showRoleDialog(scope.row.id)">添加角色
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

    <el-dialog title="用户信息" :visible.sync="dialogFormVisible">
      <el-form :model="form">
        <el-form-item label="用户名称" :label-width="formLabelWidth">
          <el-input v-model="form.username" auto-complete="off"></el-input>
        </el-form-item>
        <el-form-item label="密码" :label-width="formLabelWidth">
          <el-input type="password" v-model="form.password" auto-complete="off"></el-input>
        </el-form-item>
        <el-form-item label="邮箱" :label-width="formLabelWidth">
          <el-input v-model="form.email" auto-complete="off"></el-input>
        </el-form-item>
        <el-form-item label="联系电话" :label-width="formLabelWidth">
          <el-input v-model="form.phoneNum" auto-complete="off"></el-input>
        </el-form-item>
        <el-form-item label="用户状态" :label-width="formLabelWidth">
          <el-select v-model="form.status" placeholder="未开启">
            <el-option label="未开启" value="0"></el-option>
            <el-option label="已开启" value="1"></el-option>
          </el-select>
        </el-form-item>
        <el-form-item label="用户头像" :label-width="formLabelWidth">
          <el-upload
            ref="upload"
            class="avatar-uploader"
            :action="'/ssm/user/upload'"
            :show-file-list="false"
            :on-change="handleChange"
            :on-success="handleSuccess"
            :before-upload="beforeAvatarUpload">
<!--            :auto-upload="false">-->
            <img v-if="form.avatar" :src="form.avatar" class="avatar">
            <i v-else class="el-icon-plus avatar-uploader-icon"></i>
          </el-upload>
        </el-form-item>
      </el-form>
      <div slot="footer" class="dialog-footer">
        <el-button @click="dialogFormVisible = false">取 消</el-button>
        <el-button type="primary" @click="add">确 定</el-button>
      </div>
    </el-dialog>

    <el-dialog title="添加角色" :visible.sync="dialogRoleVisible">
      <el-table
        ref="multipleTable"
        :data="otherRoles"
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
          prop="roleName"
          label="角色名称">
          <template slot-scope="scope">{{ scope.row.roleName }}</template>
        </el-table-column>
        <el-table-column
          prop="roleDesc"
          label="角色描述">
          <template slot-scope="scope">{{ scope.row.roleDesc }}</template>
        </el-table-column>
      </el-table>
      <div slot="footer" class="dialog-footer">
        <el-button @click="dialogRoleVisible = false">取 消</el-button>
        <el-button type="primary" @click="addRole">确 定</el-button>
      </div>
    </el-dialog>

  </div>
</template>

<script>
import { addUser, getPageList, getOtherRoles, addOtherRole } from '@/api/user'
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
      userId: '',
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
      dialogRoleVisible: false,
      form: {
        username: '',
        password: '',
        email: '',
        phoneNum: '',
        status: '0',
        avatar: null
      },
      formLabelWidth: '120px',
      otherRoles: null,
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
      this.dialogFormVisible = true;
    },
    add() {
      this.dialogFormVisible = false
      addUser(this.form).then(response => {
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
    showRoleDialog(id) {
      this.dialogRoleVisible = true
      getOtherRoles(id).then(response => {
        this.otherRoles = response.data
        this.userId = id
      })
    },
    addRole() {
      let roleIds = []
      for (let i = 0; i < this.multipleSelection.length; i++) {
        roleIds[i] = this.multipleSelection[i].id
      }
      let r = {}
      r.userId = this.userId
      r.roleIds = roleIds.toString()
      console.log(r)
      addOtherRole(r).then(response => {
        this.$message({
          message: '添加成功',
          type: 'success'
        })
        history.go(0)
        this.dialogRoleVisible = false
        // this.total = response.data.total
        // Just to simulate the time of the request
        setTimeout(() => {
          this.listLoading = false
        }, 1.5 * 1000)
      })
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
    },
    handleChange(res, file) {
      // console.log(res)
      // console.log(file)
      // this.form.avatar = URL.createObjectURL(res.raw)
      // // console.log(URL.createObjectURL(res.raw))
      // // this.form.avatar = res.row
      // this.$refs.upload.submit()
      // this.form.avatar = res.raw
      // console.log(this.form)
    },
    handleSuccess(res,file,fileList){
      console.log(res)
      this.form.avatar = res.data
    },
    beforeAvatarUpload(file) {
      const isLt2M = file.size / 1024 / 1024 < 2
      if (!isLt2M) {
        this.$message.error('上传头像图片大小不能超过 2MB!')
      }
      return isLt2M
    }
  }
}
</script>
<style>
  .avatar-uploader .el-upload {
    border: 1px dashed #d9d9d9;
    border-radius: 6px;
    cursor: pointer;
    position: relative;
    overflow: hidden;
  }
  .avatar-uploader .el-upload:hover {
    border-color: #409EFF;
  }
  .avatar-uploader-icon {
    font-size: 28px;
    color: #8c939d;
    width: 178px;
    height: 178px;
    line-height: 178px;
    text-align: center;
  }
  .avatar {
    width: 178px;
    height: 178px;
    display: block;
  }
</style>
