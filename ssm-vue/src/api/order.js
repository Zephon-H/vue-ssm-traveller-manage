import request from '@/utils/request'

export function getPageList(params) {
  return request({
    url: '/ssm/orders',
    method: 'get',
    params
  })
}

export function addOrder(data) {
  return request({
    url: '/ssm/order',
    method: 'post',
    data
  })
}
export function getOrderDetail(param) {
  return request({
    url: '/ssm/order/' + param,
    method: 'get'
  })
}

export function updateOrder(data) {
  return request({
    url: '/ssm/order',
    method: 'put',
    data
  })
}
