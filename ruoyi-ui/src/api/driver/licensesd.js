import request from '@/utils/request'

// 查询行驶证管理列表
export function listLicensesd(query) {
  return request({
    url: '/driver/licensesd/list',
    method: 'get',
    params: query
  })
}

// 查询行驶证管理详细
export function getLicensesd(id) {
  return request({
    url: '/driver/licensesd/' + id,
    method: 'get'
  })
}

// 新增行驶证管理
export function addLicensesd(data) {
  return request({
    url: '/driver/licensesd',
    method: 'post',
    data: data
  })
}

// 修改行驶证管理
export function updateLicensesd(data) {
  return request({
    url: '/driver/licensesd',
    method: 'put',
    data: data
  })
}

// 删除行驶证管理
export function delLicensesd(id) {
  return request({
    url: '/driver/licensesd/' + id,
    method: 'delete'
  })
}

// 修改审批状态
export function licenseSuccessUpdata(data) {
  return request({
    url: '/driver/licensesd/licenseSuccessUpdata',
    method: 'put',
    data
  })
}
