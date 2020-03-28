import request from '@/utils/request'

export function getPageList(params) {
  return request({
    url: '/ssm/products',
    method: 'get',
    params
  })
}

export function addProduct(data) {
  return request({
    url: '/ssm/product',
    method: 'post',
    data
  })
}

export function getProductDetail(param) {
  return request({
    url: '/ssm/product/' + param,
    method: 'get'
  })
}

export function updateProduct(data) {
  return request({
    url: '/ssm/product',
    method: 'put',
    data
  })
}
