package com.ruoyi.driver.controller;

import java.util.List;
import java.io.IOException;
import javax.servlet.http.HttpServletResponse;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import com.ruoyi.common.log.annotation.Log;
import com.ruoyi.common.log.enums.BusinessType;
import com.ruoyi.common.security.annotation.RequiresPermissions;
import com.ruoyi.driver.domain.License;
import com.ruoyi.driver.service.ILicenseService;
import com.ruoyi.common.core.web.controller.BaseController;
import com.ruoyi.common.core.web.domain.AjaxResult;
import com.ruoyi.common.core.utils.poi.ExcelUtil;
import com.ruoyi.common.core.web.page.TableDataInfo;

/**
 * 行驶证管理Controller
 * 
 * @author ruoyi
 * @date 2024-08-23
 */
@RestController
@RequestMapping("/driver/licensesd")
public class LicenseController extends BaseController
{
    @Autowired
    private ILicenseService licenseService;

    /**
     * 查询行驶证管理列表
     */
    @RequiresPermissions("driver:licensesd:list")
    @GetMapping("/list")
    public TableDataInfo list(License license)
    {
        startPage();
        List<License> list = licenseService.selectLicenseList(license);
        return getDataTable(list);
    }

    /**
     * 导出行驶证管理列表
     */
    @RequiresPermissions("driver:licensesd:export")
    @Log(title = "行驶证管理", businessType = BusinessType.EXPORT)
    @PostMapping("/export")
    public void export(HttpServletResponse response, License license)
    {
        List<License> list = licenseService.selectLicenseList(license);
        ExcelUtil<License> util = new ExcelUtil<License>(License.class);
        util.exportExcel(response, list, "行驶证管理数据");
    }

    /**
     * 获取行驶证管理详细信息
     */
    @RequiresPermissions("driver:licensesd:query")
    @GetMapping(value = "/{id}")
    public AjaxResult getInfo(@PathVariable("id") Long id)
    {
        return success(licenseService.selectLicenseById(id));
    }

    /**
     * 新增行驶证管理
     */
    @RequiresPermissions("driver:licensesd:add")
    @Log(title = "行驶证管理", businessType = BusinessType.INSERT)
    @PostMapping
    public AjaxResult add(@RequestBody License license)
    {
        return toAjax(licenseService.insertLicense(license));
    }

    /**
     * 修改行驶证管理
     */
    @RequiresPermissions("driver:licensesd:edit")
    @Log(title = "行驶证管理", businessType = BusinessType.UPDATE)
    @PutMapping
    public AjaxResult edit(@RequestBody License license)
    {
        return toAjax(licenseService.updateLicense(license));
    }

    /**
     * 删除行驶证管理
     */
    @RequiresPermissions("driver:licensesd:remove")
    @Log(title = "行驶证管理", businessType = BusinessType.DELETE)
	@DeleteMapping("/{ids}")
    public AjaxResult remove(@PathVariable Long[] ids)
    {
        return toAjax(licenseService.deleteLicenseByIds(ids));
    }


    @PutMapping("/licenseSuccessUpdata")
    public AjaxResult licenseSuccessUpdata(@RequestBody License license)
    {
        return toAjax(licenseService.updateLicenseStatus(license));
    }
}
