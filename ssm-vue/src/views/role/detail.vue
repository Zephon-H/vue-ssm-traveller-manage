<template>
  <div class="app-container">
    <div class="app-container">
      <el-table
        v-loading="listLoading"
        :data="roleName"
        :default-expand-all="true"
        style="width: 100%">
        <el-table-column type="expand" >
        <el-table
          v-loading="listLoading"
          :data="data.permissions"
          :show-header = "false"
          style="width: 100%">
            <el-table-column
              label="名称"
              prop="permissionName">
            </el-table-column>
            <el-table-column
              label="描述"
              prop="url">
            </el-table-column>
          </el-table>
        </el-table-column>
        <el-table-column
          label="名称"
          prop="roleName">
          {{data.roleName}}
        </el-table-column>
        <el-table-column
          label="描述"
          prop="roleDesc">
        </el-table-column>
      </el-table>
    </div>
  </div>
</template>
<script>
import { getRoleDetail } from '@/api/role'
export default {
  data() {
    return {
      id: '',
      roleName: [],
      listLoading: true,
      data: ''
    }
  },
  created() {
    // console.log(this.$route.params.pathMatch)
    this.id = this.$route.params.pathMatch
    this.getDetail(this.id)
  },
  methods: {
    getDetail(id) {
      getRoleDetail(id).then(response => {
        console.log(response)
        this.data = response.data
        this.roleName.push(this.data.roleName)
        // this.total = response.data.total
        // Just to simulate the time of the request
        this.listLoading = false
      })
    }
  }
}
</script>
<style>
  #detail .el-col-3{
    height: 40px;
    line-height: 40px;
  }
  #detail .el-row{
    margin-bottom: 20px;
  }
</style>
