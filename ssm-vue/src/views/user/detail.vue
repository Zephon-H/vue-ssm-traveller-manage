<template>
  <div class="app-container">
    <div class="app-container">
      <el-table
        v-loading="listLoading"
        :data="username"
        style="width: 100%">
        <el-table-column type="expand">
          <el-table
            v-loading="listLoading"
            :show-header="false"
            :data="data.roles">
            <el-table-column type="expand">
              <template slot-scope="scope">
                <el-table
                  v-loading="listLoading"
                  :show-header="false"
                  :data="scope.row.permissions">
                  <el-table-column
                    prop="permissionName">
                  </el-table-column>
                  <el-table-column
                    prop="url">
                  </el-table-column>
                </el-table>
              </template>
            </el-table-column>
            <el-table-column
              prop="roleName"
            label="名称">
            </el-table-column>
            <el-table-column
              label="描述"
              prop="roleDesc">
            </el-table-column>
          </el-table>
        </el-table-column>
        <el-table-column
          label="名称"
          prop="username">
        </el-table-column>
        <el-table-column
          label="描述">
        </el-table-column>
      </el-table>
    </div>
  </div>
</template>
<script>
import { getUserDetail } from '@/api/user'
export default {
  data() {
    return {
      id: '',
      listLoading: true,
      username: [],
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
      this.listLoading = true
      getUserDetail(id).then(response => {
        console.log(response)
        this.data = response.data
        this.username.push({ 'username': this.data.username})
        console.log(this.username)
        // this.total = response.data.total
        // Just to simulate the time of the request
        setTimeout(() => {
          this.listLoading = false
        }, 1.5 * 1000)
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
