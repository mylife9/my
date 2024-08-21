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
    url: '/coupons/coupons/' + id,
    method: 'get'
  })
}

// 新增优惠券
export function addCoupons(data) {
  return request({
    url: '/coupons/coupons',
    method: 'post',
    data: data
  })
}

// 修改优惠券
export function updateCoupons(data) {
  return request({
    url: '/coupons/coupons',
    method: 'put',
    data: data
  })
}

// 删除优惠券
export function delCoupons(id) {
  return request({
    url: '/coupons/coupons/' + id,
    method: 'delete'
  })
}

// 领取优惠券
export function redisAcquireLockLock(id) {
  return request({
    url: '/coupons/coupons/redisAcquireLockLock/' + id,
    method: 'post'
  })
}

// 查询优惠券详细
export function getCouponsTypeService() {
  return request({
    url: '/coupons/coupons/listType',
    method: 'get'
  })
}

// 发卷按钮和停止按钮
export function couponsUpdateService(data) {
  return request({
    url: '/coupons/coupons/updateCoupons',
    method: 'post',
    data
  })
}
