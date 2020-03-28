<template>
  <div class="app-container">
    <div class="app-container">
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

      <el-table-column align="center" label="订单编号">
        <template slot-scope="scope">
          {{ scope.row.orderNum }}
        </template>
      </el-table-column>

      <el-table-column align="center" label="产品名称">
        <template slot-scope="scope">
          {{ scope.row.product.productName }}
        </template>
      </el-table-column>

      <el-table-column align="center" label="金额">
        <template slot-scope="scope">
          {{ scope.row.product.productPrice }}
        </template>
      </el-table-column>
      <el-table-column align="center" label="下单时间">
        <template slot-scope="scope">
          {{ scope.row.orderTimeStr }}
        </template>
      </el-table-column>

      <el-table-column class-name="status-col" label="订单状态" align="center">
        <template slot-scope="scope">
          <el-tag :type="scope.row.orderStatus | statusFilter">{{ scope.row.orderStatus==1?'开启':'未开启' }}</el-tag>
        </template>
      </el-table-column>
      <el-table-column label="操作" align="center" width="200">
        <template slot-scope="scope">
          <router-link :to="'/base/order/detail/'+scope.row.id">
            <el-button
              size="mini"
              type="primary"
              @click="handleDetail(scope.row.id, scope.row)">详情
            </el-button>
          </router-link>
          <router-link :to="'/base/order/edit/'+scope.row.id">
            <el-button
              size="mini"
              type="primary">编辑
            </el-button>
          </router-link>
        </template>
      </el-table-column>
      <!--      <el-user-column align="center" prop="created_at" label="Display_time" width="200">-->
      <!--        <template slot-scope="scope">-->
      <!--          <i class="el-icon-time" />-->
      <!--          <span>{{ scope.row.display_time }}</span>-->
      <!--        </template>-->
      <!--      </el-user-column>-->
    </el-table>
    <pagination
      v-show="total>0"
      :total="total"
      :page.sync="listQuery.page"
      :limit.sync="listQuery.limit"
      @pagination="getPageList" />

    <el-dialog title="产品信息" :visible.sync="dialogFormVisible">
      <el-form :model="form">
        <el-form-item label="产品编号" :label-width="formLabelWidth">
          <el-input v-model="form.productNum" auto-complete="off"></el-input>
        </el-form-item>
        <el-form-item label="产品名称" :label-width="formLabelWidth">
          <el-input v-model="form.productName" auto-complete="off"></el-input>
        </el-form-item>
        <el-form-item label="出发时间" :label-width="formLabelWidth">
          <el-date-picker
            v-model="form.departureTime"
            type="datetime"
            format="yyyy-MM-dd HH:mm"
            value-format="yyyy-MM-dd HH:mm"
            placeholder="选择日期时间">
          </el-date-picker>
<!--          <el-input v-model="form.departureTime" auto-complete="off"></el-input>-->
        </el-form-item>
        <el-form-item label="出发城市" :label-width="formLabelWidth">
          <el-input v-model="form.cityName" auto-complete="off"></el-input>
        </el-form-item>
        <el-form-item label="产品价格" :label-width="formLabelWidth">
          <el-input v-model="form.productPrice" auto-complete="off"></el-input>
        </el-form-item>
        <el-form-item label="产品状态" :label-width="formLabelWidth">
          <el-select v-model="form.productStatus" placeholder="未开启">
            <el-option label="关闭" value="0"></el-option>
            <el-option label="开启" value="1"></el-option>
          </el-select>
        </el-form-item>
        <el-form-item label="其它信息" :label-width="formLabelWidth">
          <el-input
            type="textarea"
            :rows="2"
            placeholder="请输入内容"
            v-model="form.productDesc">
          </el-input>
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
import { addOrder, getPageList } from '@/api/order'
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
        limit: 20
      },

      dialogFormVisible: false,
      form: {
        productNum: '',
        productName: '',
        productPrice: '',
        orderTimeStr: '',
        orderStatus: '',
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
      this.form.departureTime = this.form.departureTime.replace(/\.[\d]{3}Z/, '')
      addOrder(this.form).then(response => {
        console.log(response)
        this.$message({
          message: '添加成功',
          type: 'success'
        })
        // this.total = response.data.total
        // Just to simulate the time of the request
        setTimeout(() => {
          this.listLoading = false
        }, 1.5 * 1000)
      })
    },
    handleDetail() {
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
