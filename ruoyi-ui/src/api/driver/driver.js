import request from '@/utils/request'

// 查询车辆列表
export function listDriver(query) {
  return request({
    url: '/driver/driver/list',
    method: 'get',
    params: query
  })
}
// 查询车辆列表
export function addressList(query) {
  return request({
    url: '/driver/driver/dicList',
    method: 'get',
    params: query
  })
}

// 查询车辆详细
export function getDriver(id) {
  return request({
    url: '/driver/driver/' + id,
    method: 'get'
  })
}

// 新增车辆
export function addDriver(data) {
  return request({
    url: '/driver/driver',
    method: 'post',
    data: data
  })
}

// 修改车辆
export function updateDriver(data) {
  return request({
    url: '/driver/driver',
    method: 'put',
    data: data
  })
}

// 删除车辆
export function delDriver(id) {
  return request({
    url: '/driver/driver/' + id,
    method: 'delete'
  })
}
