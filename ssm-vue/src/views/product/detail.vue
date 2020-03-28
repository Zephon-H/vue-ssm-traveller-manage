<template>
  <div class="app-container" id="detail">
    <div class="app-container">
      <el-collapse value="1" accordion>
        <el-collapse-item title="产品信息" name="1">
          <el-row :gutter="20">
            <el-col :span="3" align="middle">产品编号</el-col>
            <el-col :span="7">
              <el-input
                :placeholder="data.productNum"
                :disabled="true">
              </el-input>
            </el-col>
            <el-col :span="3" align="middle">产品状态</el-col>
            <el-col :span="7">
              <el-switch
                v-model="data.productStatus==1"
                active-color="#13ce66"
                :disabled="true"
                inactive-color="#ff4949">
              </el-switch>
<!--              <el-input-->
<!--                :placeholder="data.productStatus"-->
<!--                :disabled="true">-->
<!--              </el-input>-->
            </el-col>
          </el-row>
          <el-row :gutter="20">
            <el-col :span="3" align="middle">路线名称</el-col>
            <el-col :span="7">
              <el-input
                :placeholder="data.productName"
                :disabled="true">
              </el-input>
            </el-col>
            <el-col :span="3" align="middle">出发城市</el-col>
            <el-col :span="7">
              <el-input
                :placeholder="data.cityName"
                :disabled="true">
              </el-input>
            </el-col>
          </el-row>
          <el-row :gutter="20">
            <el-col :span="3" align="middle">出发时间</el-col>
            <el-col :span="7">
              <el-input
                :placeholder="data.departureTimeStr"
                :disabled="true">
              </el-input>
            </el-col>
            <el-col :span="3" align="middle">产品价格</el-col>
            <el-col :span="7">
              <el-input
                :placeholder="data.productPrice"
                :disabled="true">
              </el-input>
            </el-col>
          </el-row>
          <el-row :gutter="20">
            <el-col :span="3" align="middle">产品描述</el-col>
            <el-col :span="17">
              <el-input type="textarea" :disabled="true" :placeholder="data.productDesc"></el-input>
            </el-col>
          </el-row>
          <!--          订单编号-->

          <!--          订单编号-->
          <!--          <el-input-->
          <!--            placeholder="订单编号"-->
          <!--            :disabled="true">-->
          <!--          </el-input>-->
        </el-collapse-item>
      </el-collapse>
    </div>
  </div>
</template>
<script>
import { getProductDetail } from '@/api/product'
export default {
  data() {
    return {
      id: '',
      data: null,
    }
  },
  created() {
    console.log(this.$route.params.pathMatch)
    this.id = this.$route.params.pathMatch
    this.getDetail(this.id)
  },
  methods: {
    getDetail(id) {
      getProductDetail(id).then(response => {
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
