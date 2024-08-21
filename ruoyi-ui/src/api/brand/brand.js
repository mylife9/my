import request from '@/utils/request'

// 查询品牌管理列表
export function listBrand(query) {
  return request({
    url: '/brand/brand/list',
    method: 'get',
    params: query
  })
}

// 查询品牌一级数据
export function getBrandOne() {
  return request({
    url: '/brand/brand/getBrandOne',
    method: 'get',
  })
}




// 查询品牌管理详细
export function getBrand(id) {
  return request({
    url: '/brand/brand/' + id,
    method: 'get'
  })
}

// 新增品牌管理
export function addBrand(data) {
  return request({
    url: '/brand/brand',
    method: 'post',
    data: data
  })
}

// 修改品牌管理
export function updateBrand(data) {
  return request({
    url: '/brand/brand',
    method: 'put',
    data: data
  })
}

// 删除品牌管理
export function delBrand(id) {
  return request({
    url: '/brand/brand/' + id,
    method: 'delete'
  })
}
