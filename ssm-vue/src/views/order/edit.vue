<template>
  <div class="app-container" id="detail">
    <div class="app-container">
      <el-form>
        <el-collapse value="1" accordion>
          <el-collapse-item title="订单信息" name="1">
            <el-form :model="data">
              <el-row :gutter="20">
                <el-col :span="3" align="middle">订单编号</el-col>
                <el-col :span="7">
                  <el-input v-model="data.orderNum" :disabled="true">
                  </el-input>
                </el-col>
                <el-col :span="3" align="middle">下单时间</el-col>
                <el-col :span="7">
                  <el-date-picker
                    v-model="data.orderTime"
                    type="datetime"
                    :disabled="true">
                  </el-date-picker>
                </el-col>
              </el-row>
              <el-row :gutter="20">
                <el-col :span="3" align="middle">路线名称</el-col>
                <el-col :span="7">
                  <el-input
                    v-model="data.product.productName"
                    :disabled="true">
                  </el-input>
                </el-col>
                <el-col :span="3" align="middle">出发城市</el-col>
                <el-col :span="7">
                  <el-input
                    v-model="data.product.cityName"
                    :disabled="true">
                  </el-input>
                </el-col>
              </el-row>
              <el-row :gutter="20">
                <el-col :span="3" align="middle">出发时间</el-col>
                <el-col :span="7">
                  <el-date-picker
                    v-model="data.product.departureTime"
                    type="datetime"
                    :disabled="true">
                  </el-date-picker>
  <!--                <el-input v-model="data.product.departureTimeStr">-->
  <!--                </el-input>-->
                </el-col>
                <el-col :span="3" align="middle" >出游人数</el-col>
                <el-col :span="7">
                  <el-input v-model="data.peopleCount" :disabled="true">
                  </el-input>
                </el-col>
              </el-row>
              <el-row :gutter="20">
                <el-col :span="3" align="middle">其它信息</el-col>
                <el-col :span="17">
                  <el-input type="textarea" v-model="data.orderDesc"></el-input>
                </el-col>
              </el-row>
            </el-form>
            <!--          订单编号-->

            <!--          订单编号-->
            <!--          <el-input-->
            <!--            placeholder="订单编号"-->
            <!--            :disabled="true">-->
            <!--          </el-input>-->
          </el-collapse-item>
          <el-collapse-item title="游客信息" name="2">
            <el-form :model="data">
              <el-table
                :data="data.travellers"
                style="width: 100%">
                <el-table-column
                  label="人群"
                  prop="travellerTypeStr">
                  <template slot-scope="scope">
                    <el-form-item>
                      <el-select v-model="scope.row.travellerType" >
                        <el-option
                          v-for="item in travellerTypeOptions"
                          :key="item.value"
                          :label="item.label"
                          :value="item.value">
                        </el-option>
                      </el-select>
                    </el-form-item>
                  </template>
                </el-table-column>
                <el-table-column
                  label="姓名"
                  prop="name">
                  <template slot-scope="scope">
                    <el-form-item>
                      <el-input
                        v-model="scope.row.name"/>
                    </el-form-item>
                  </template>
                </el-table-column>
                <el-table-column
                  label="性别"
                  prop="gender">
                  <template slot-scope="scope">
                    <el-form-item>
                      <el-input
                        v-model="scope.row.gender"></el-input>
                    </el-form-item>
                  </template>
                </el-table-column>
                <el-table-column
                  label="手机号码"
                  prop="phoneNum">
                  <template slot-scope="scope">
                    <el-form-item>
                      <el-input
                        v-model="scope.row.phoneNum"/>
                    </el-form-item>
                  </template>
                </el-table-column>
                <el-table-column
                  label="证件类型"
                  prop="credentialsTypeStr">
                  <template slot-scope="scope">
                    <el-form-item>
                      <el-select v-model="scope.row.credentialsType" >
                        <el-option
                          v-for="item in credentialsTypeOptions"
                          :key="item.value"
                          :label="item.label"
                          :value="item.value">
                        </el-option>
                      </el-select>
                    </el-form-item>
                  </template>
                </el-table-column>
                <el-table-column
                  label="证件号码"
                  prop="credentialsNum">
                  <template slot-scope="scope">
                    <el-form-item>
                      <el-input
                        v-model="scope.row.credentialsNum"/>
                    </el-form-item>
                  </template>
                </el-table-column>
              </el-table>
            </el-form>
          </el-collapse-item>
          <el-collapse-item title="联系人信息" name="3">
            <el-form :model="data">
            <el-row :gutter="20">
              <el-col :span="3" align="middle">会员</el-col>
              <el-col :span="7">
                <el-input
                  v-model="data.member.nickname">
                </el-input>
              </el-col>
              <el-col :span="3" align="middle">联系人</el-col>
              <el-col :span="7">
                <el-input
                  v-model="data.member.name">
                </el-input>
              </el-col>
            </el-row>
            <el-row :gutter="20">
              <el-col :span="3" align="middle">手机号</el-col>
              <el-col :span="7">
                <el-input
                  v-model="data.member.phoneNum">
                </el-input>
              </el-col>
              <el-col :span="3" align="middle">邮箱</el-col>
              <el-col :span="7">
                <el-input
                  v-model="data.member.email">
                </el-input>
              </el-col>
            </el-row>
            </el-form>
          </el-collapse-item>
          <el-collapse-item title="费用信息" name="4">
            <el-form :model="data">
              <el-row :gutter="20">
                <el-col :span="3" align="middle">支付方式</el-col>
                <el-col :span="7">
                  <el-select v-model="data.payType" >
                    <el-option
                      v-for="item in payTypeOptions"
                      :key="item.value"
                      :label="item.label"
                      :value="item.value">
                    </el-option>
                  </el-select>
                </el-col>
                <el-col :span="3" align="middle">金额</el-col>
                <el-col :span="7">
                  <el-input
                    v-model="'￥'+data.product.productPrice"
                    :disabled="true">
                  </el-input>
                </el-col>
              </el-row>
            </el-form>
          </el-collapse-item>
        </el-collapse>
        <el-form-item>
          <el-button type="primary" @click="onSubmit">立即保存</el-button>
        </el-form-item>
      </el-form>
    </div>
  </div>
</template>
<script>
import { getOrderDetail, updateOrder } from '@/api/order'
export default {
  data() {
    return {
      id: '',
      data: '',
      travellerTypeOptions: [
        {
          value: 0,
          label: '成人'
        },
        {
          value: 1,
          label: '儿童'
        }
      ],
      credentialsTypeOptions: [
        {
          value: 0,
          label: '身份证'
        },
        {
          value: 1,
          label: '护照'
        },
        {
          value: 2,
          label: '军官证'
        }
      ],
      payTypeOptions: [
        {
          value: 0,
          label: '微信'
        },
        {
          value: 1,
          label: '支付宝'
        },
        {
          value: 2,
          label: '其它'
        }
      ]
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
    },
    onSubmit() {
      updateOrder(this.data).then(response => {
        console.log(response)
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
