<template>
  <div class="app-container">

    <el-table
      :key="tableKey"
      v-loading="listLoading"
      :data="list"
      element-loading-text="Loading"
      border
      fit
      highlight-current-row>
      <el-table-column align="center" label="ID">
        <template slot-scope="scope">
          {{ scope.row.id }}
        </template>
      </el-table-column>

      <el-table-column align="center" label="访问时间">
        <template slot-scope="scope">
          {{ scope.row.visitTimeStr }}
        </template>
      </el-table-column>

      <el-table-column align="center" label="访问用户">
        <template slot-scope="scope">
          {{ scope.row.username }}
        </template>
      </el-table-column>

      <el-table-column align="center" label="访问IP">
        <template slot-scope="scope">
          {{ scope.row.ip }}
        </template>
      </el-table-column>
      <el-table-column align="center" label="资源URL">
        <template slot-scope="scope">
          {{ scope.row.url }}
        </template>
      </el-table-column>

      <el-table-column align="center" label="执行时间">
        <template slot-scope="scope">
          {{ scope.row.executionTime }}
        </template>
      </el-table-column>

      <el-table-column align="center" label="访问方法">
        <template slot-scope="scope">
          {{ scope.row.method }}
        </template>
      </el-table-column>
    </el-table>
    <pagination
      v-show="total>0"
      :total="total"
      :page.sync="listQuery.page"
      :limit.sync="listQuery.limit"
      @pagination="getPageList" />

  </div>
</template>

<script>
import { getPageList } from '@/api/log'
import Pagination from '@/components/Pagination'

export default {
  components: {
    Pagination
  },
  data() {
    return {
      tableKey: 0,
      list: null,
      total: 0,
      listLoading: true,
      listQuery: {
        page: 1,
        limit: 20
      }
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
    }
  }
}
</script>
