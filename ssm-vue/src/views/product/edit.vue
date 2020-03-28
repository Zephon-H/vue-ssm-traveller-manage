<template>
  <div class="app-container" id="detail">
    <div class="app-container">
      <el-form>
        <el-collapse value="1" accordion>
          <el-collapse-item title="产品信息" name="1">
            <el-row :gutter="20">
              <el-col :span="3" align="middle">产品编号</el-col>
              <el-col :span="7">
                <el-input
                  v-model="data.productNum">
                </el-input>
              </el-col>
              <el-col :span="3" align="middle">产品状态</el-col>
              <el-col :span="7">
                <el-switch
                  v-model="productStatus"
                  active-color="#13ce66"
                  inactive-color="#ff4949">
                </el-switch>
                <!--              <el-input-->
                <!--                v-model="data.productStatus"-->
                <!--                >-->
                <!--              </el-input>-->
              </el-col>
            </el-row>
            <el-row :gutter="20">
              <el-col :span="3" align="middle">路线名称</el-col>
              <el-col :span="7">
                <el-input
                  v-model="data.productName">
                </el-input>
              </el-col>
              <el-col :span="3" align="middle">出发城市</el-col>
              <el-col :span="7">
                <el-input
                  v-model="data.cityName">
                </el-input>
              </el-col>
            </el-row>
            <el-row :gutter="20">
              <el-col :span="3" align="middle">出发时间</el-col>
              <el-col :span="7">
                <el-date-picker
                  v-model="data.departureTime"
                  type="datetime">
                </el-date-picker>
              </el-col>
              <el-col :span="3" align="middle">产品价格</el-col>
              <el-col :span="7">
                <el-input
                  v-model="data.productPrice">
                </el-input>
              </el-col>
            </el-row>
            <el-row :gutter="20">
              <el-col :span="3" align="middle">产品描述</el-col>
              <el-col :span="17">
                <el-input type="textarea"  v-model="data.productDesc"></el-input>
              </el-col>
            </el-row>
            <!--          订单编号-->

            <!--          订单编号-->
            <!--          <el-input-->
            <!--            placeholder="订单编号"-->
            <!--            >-->
            <!--          </el-input>-->
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
import { getProductDetail, updateProduct } from '@/api/product'
export default {
  data() {
    return {
      id: '',
      data: null,
      productStatus: false
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
        this.productStatus = this.data.productStatus==1
        // this.total = response.data.total
        // Just to simulate the time of the request
        setTimeout(() => {
          this.listLoading = false
        }, 1.5 * 1000)
      })
    },
    onSubmit() {
      this.data.productStatus = this.productStatus ? 1 : 0
      updateProduct(this.data).then(response => {
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
