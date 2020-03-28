import request from '@/utils/request'

export function getPageList(params) {
  return request({
    url: '/ssm/logs',
    method: 'get',
    params
  })
}
