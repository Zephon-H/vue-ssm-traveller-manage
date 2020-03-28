<template>
  <div class="app-container" id="detail">
    <div class="app-container">
      <el-collapse value="1" accordion>
        <el-collapse-item title="订单信息" name="1">
          <el-row :gutter="20">
            <el-col :span="3" align="middle">订单编号</el-col>
            <el-col :span="7">
              <el-input
                :placeholder="data.orderNum"
                :disabled="true">
              </el-input>
            </el-col>
            <el-col :span="3" align="middle">下单时间</el-col>
            <el-col :span="7">
              <el-input
                :placeholder="data.orderTimeStr"
                :disabled="true">
              </el-input>
            </el-col>
          </el-row>
          <el-row :gutter="20">
            <el-col :span="3" align="middle">路线名称</el-col>
            <el-col :span="7">
              <el-input
                :placeholder="data.product.productName"
                :disabled="true">
              </el-input>
            </el-col>
            <el-col :span="3" align="middle">出发城市</el-col>
            <el-col :span="7">
              <el-input
                :placeholder="data.product.cityName"
                :disabled="true">
              </el-input>
            </el-col>
          </el-row>
          <el-row :gutter="20">
            <el-col :span="3" align="middle">出发时间</el-col>
            <el-col :span="7">
              <el-input
                :placeholder="data.product.departureTimeStr"
                :disabled="true">
              </el-input>
            </el-col>
            <el-col :span="3" align="middle">出游人数</el-col>
            <el-col :span="7">
              <el-input
                :placeholder="data.peopleCount"
                :disabled="true">
              </el-input>
            </el-col>
          </el-row>
          <el-row :gutter="20">
            <el-col :span="3" align="middle">其它信息</el-col>
            <el-col :span="17">
            <el-input type="textarea" :disabled="true" v-module="data.orderDesc"></el-input>
            </el-col>
          </el-row>
<!--          订单编号-->

<!--          订单编号-->
<!--          <el-input-->
<!--            placeholder="订单编号"-->
<!--            :disabled="true">-->
<!--          </el-input>-->
        </el-collapse-item>
        <el-collapse-item title="游客信息" name="2">
          <el-table
            :data="data.travellers"
            style="width: 100%">
            <el-table-column
              label="人群"
              prop="travellerTypeStr">
            </el-table-column>
            <el-table-column
              label="姓名"
              prop="name">
            </el-table-column>
            <el-table-column
              label="性别"
              prop="gender">
            </el-table-column>
            <el-table-column
              label="手机号码"
              prop="phoneNum">
            </el-table-column>
            <el-table-column
              label="证件类型"
              prop="credentialsTypeStr">
            </el-table-column>
            <el-table-column
              label="证件号码"
              prop="credentialsNum">
            </el-table-column>
          </el-table>
        </el-collapse-item>
        <el-collapse-item title="联系人信息" name="3">
          <el-row :gutter="20">
            <el-col :span="3" align="middle">会员</el-col>
            <el-col :span="7">
              <el-input
                :placeholder="data.member.nickname"
                :disabled="true">
              </el-input>
            </el-col>
            <el-col :span="3" align="middle">联系人</el-col>
            <el-col :span="7">
              <el-input
                :placeholder="data.member.name"
                :disabled="true">
              </el-input>
            </el-col>
          </el-row>
          <el-row :gutter="20">
            <el-col :span="3" align="middle">手机号</el-col>
            <el-col :span="7">
              <el-input
                :placeholder="data.member.phoneNum"
                :disabled="true">
              </el-input>
            </el-col>
            <el-col :span="3" align="middle">邮箱</el-col>
            <el-col :span="7">
              <el-input
                :placeholder="data.member.email"
                :disabled="true">
              </el-input>
            </el-col>
          </el-row>
        </el-collapse-item>
        <el-collapse-item title="费用信息" name="4">
          <el-row :gutter="20">
            <el-col :span="3" align="middle">支付方式</el-col>
            <el-col :span="7">
              <el-input
                :placeholder="data.payTypeStr"
                :disabled="true">
              </el-input>
            </el-col>
            <el-col :span="3" align="middle">金额</el-col>
            <el-col :span="7">
              <el-input
                :placeholder="'￥'+data.product.productPrice"
                :disabled="true">
              </el-input>
            </el-col>
          </el-row>
        </el-collapse-item>
      </el-collapse>
    </div>
  </div>
</template>
<script>
import { getOrderDetail } from '@/api/order'
export default {
  data() {
    return {
      id: '',
      data: null
    }
  },
  created() {
    console.log(this.$route.params.pathMatch)
    this.id = this.$route.params.pathMatch
    this.getDetail(this.id)
  },
  methods: {
    getDetail(id) {
      getOrderDetail(id).then(response => {
        console.log(response)
        this.data = response.data
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
