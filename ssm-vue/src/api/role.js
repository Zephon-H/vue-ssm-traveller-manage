import request from '@/utils/request'

export function getPageList(params) {
  return request({
    url: '/ssm/roles',
    method: 'get',
    params
  })
}

export function addRole(data) {
  return request({
    url: '/ssm/role',
    method: 'post',
    data
  })
}
export function getRoleDetail(param) {
  return request({
    url: '/ssm/role/' + param,
    method: 'get'
  })
}
export function getOtherPermission(param) {
  return request({
    url: '/ssm/role/permission/' + param,
    method: 'get'
  })
}

export function addOtherPermission(params) {
  return request({
    url: '/ssm/role/permission',
    method: 'get',
    params
  })
}
export function deleteRole(param) {
  return request({
    url: '/ssm/role/' + param,
    method: 'delete'
  })
}
