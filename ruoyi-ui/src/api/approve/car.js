import request from '@/utils/request'

// 查询车辆管理列表
export function listCar(query) {
  return request({
    url: '/car/car/list',
    method: 'get',
    params: query
  })
}

// 查询车辆管理详细
export function getCar(id) {
  return request({
    url: '/car/car/' + id,
    method: 'get'
  })
}

// 新增车辆管理
export function addCar(data) {
  return request({
    url: '/car/car',
    method: 'post',
    data: data
  })
}

// 修改车辆管理
export function updateCar(data) {
  return request({
    url: '/car/car',
    method: 'put',
    data: data
  })
}

// 删除车辆管理
export function delCar(id) {
  return request({
    url: '/car/car/' + id,
    method: 'delete'
  })
}
// 修改审批状态
export function carSuccessUpdata(data) {
  return request({
    url: '/car/car/carSuccessUpdata',
    method: 'put',
    data
  })
}
