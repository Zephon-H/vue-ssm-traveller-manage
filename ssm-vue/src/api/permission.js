import request from '@/utils/request'

export function getPageList(params) {
  return request({
    url: '/ssm/permissions',
    method: 'get',
    params
  })
}

export function addPermission(data) {
  return request({
    url: '/ssm/permission',
    method: 'post',
    data
  })
}
export function getPermissionDetail(param) {
  return request({
    url: '/ssm/permission/' + param,
    method: 'get'
  })
}

export function deletePermission(param) {
  return request({
    url: '/ssm/permission/' + param,
    method: 'delete'
  })
}
