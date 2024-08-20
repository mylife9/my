import request from '@/utils/request'

// 查询优惠券列表
export function listCoupons(query) {
  return request({
    url: '/coupons/coupons/list',
    method: 'get',
    params: query
  })
}

// 查询优惠券详细
export function getCoupons(id) {
  return request({
    url: '/coupons/coupons/query/' + id,
    method: 'get'
  })
}

// 新增优惠券
export function addCoupons(data) {
  return request({
    url: '/coupons/coupons/add',
    method: 'post',
    data: data
  })
}

// 修改优惠券
export function edit(data) {
  return request({
    url: '/coupons/coupons/edit',
    method: 'put',
    data: data
  })
}

// 删除优惠券
export function delCoupons(id) {
  return request({
    url: '/coupons/coupons/remove/' + id,
    method: 'delete'
  })
}

// 查询优惠券所有类型
export function listType() {
  return request({
    url: '/coupons/coupons/listType',
    method: 'get'
  })
}

// 发卷按钮和停止按钮
export function startCoupons(id) {
  return request({
    url: '/coupons/coupons/startCoupons'+id,
    method: 'post',
  })
}
