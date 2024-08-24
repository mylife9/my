import request from '@/utils/request'

// 查询司机入驻列表
export function listDriveruser(query) {
  return request({
    url: '/driveruser/driveruser/list',
    method: 'get',
    params: query
  })
}

// 查询司机入驻详细
export function getDriveruser(id) {
  return request({
    url: '/driveruser/driveruser/' + id,
    method: 'get'
  })
}

// 新增司机入驻
export function addDriveruser(data) {
  return request({
    url: '/driveruser/driveruser',
    method: 'post',
    data: data
  })
}

// 修改司机入驻
export function updateDriveruser(data) {
  return request({
    url: '/driveruser/driveruser',
    method: 'put',
    data: data
  })
}

// 删除司机入驻
export function delDriveruser(id) {
  return request({
    url: '/driveruser/driveruser/' + id,
    method: 'delete'
  })
}

// 修改审批状态
export function userSuccessUpdata(data) {
  return request({
    url: '/driveruser/driveruser/userSuccessUpdata',
    method: 'put',
    data
  })
}
