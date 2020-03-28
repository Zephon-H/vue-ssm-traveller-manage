import request from '@/utils/request'

export function login(data) {
  return request({
    url: '/ssm/user/login',
    method: 'post',
    data
  })
}

export function getInfo(token) {
  return request({
    url: '/ssm/user/info',
    method: 'get',
    params: { token }
  })
}

export function logout() {
  return request({
    url: '/ssm/user/logout',
    method: 'post'
  })
}

export function getList(params) {
  return request({
    url: '/ssm/users',
    method: 'get',
    params
  })
}

export function getPageList(params) {
  return request({
    url: '/ssm/users',
    method: 'get',
    params
  })
}

export function addUser(data) {
  return request({
    url: '/ssm/user',
    method: 'post',
    data
  })
}

export function getUserDetail(param) {
  return request({
    url: '/ssm/user/' + param,
    method: 'get'
  })
}

export function getOtherRoles(param) {
  return request({
    url: '/ssm/user/role/' + param,
    method: 'get'
  })
}

export function addOtherRole(params) {
  return request({
    url: '/ssm/user/role',
    method: 'get',
    params
  })
}
