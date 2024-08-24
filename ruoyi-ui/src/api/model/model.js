import request from '@/utils/request'

// 查询车型管理列表
export function listModel(query) {
  return request({
    url: '/model/model/list',
    method: 'get',
    params: query
  })
}
// 查询车型管理列表
export function listBrand() {
  return request({
    url: '/model/model/brandList',
    method: 'get',
  })
}

// 查询车型管理详细
export function getModel(id) {
  return request({
    url: '/model/model/' + id,
    method: 'get'
  })
}

// 新增车型管理
export function addModel(data) {
  return request({
    url: '/model/model',
    method: 'post',
    data: data
  })
}

// 修改车型管理
export function updateModel(data) {
  return request({
    url: '/model/model',
    method: 'put',
    data: data
  })
}

// 删除车型管理
export function delModel(id) {
  return request({
    url: '/model/model/' + id,
    method: 'delete'
  })
}
