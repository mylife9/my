package com.ruoyi.approve.controller;

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
import com.ruoyi.approve.domain.DriverUser;
import com.ruoyi.approve.service.IDriverUserService;
import com.ruoyi.common.core.web.controller.BaseController;
import com.ruoyi.common.core.web.domain.AjaxResult;
import com.ruoyi.common.core.utils.poi.ExcelUtil;
import com.ruoyi.common.core.web.page.TableDataInfo;

/**
 * 司机入驻Controller
 * 
 * @author ruoyi
 * @date 2024-08-17
 */
@RestController
@RequestMapping("/driveruser/driveruser")
public class DriverUserController extends BaseController
{
    @Autowired
    private IDriverUserService driverUserService;

    /**
     * 查询司机入驻列表
     */
    @RequiresPermissions("driver:driveruser:list")
    @GetMapping("/list")
    public TableDataInfo list(DriverUser driverUser)
    {
        startPage();
        List<DriverUser> list = driverUserService.selectDriverUserList(driverUser);
        return getDataTable(list);
    }

    /**
     * 导出司机入驻列表
     */
    @RequiresPermissions("driver:driveruser:export")
    @Log(title = "司机入驻", businessType = BusinessType.EXPORT)
    @PostMapping("/export")
    public void export(HttpServletResponse response, DriverUser driverUser)
    {
        List<DriverUser> list = driverUserService.selectDriverUserList(driverUser);
        ExcelUtil<DriverUser> util = new ExcelUtil<DriverUser>(DriverUser.class);
        util.exportExcel(response, list, "司机入驻数据");
    }

    /**
     * 获取司机入驻详细信息
     */
    @RequiresPermissions("driver:driveruser:query")
    @GetMapping(value = "/{id}")
    public AjaxResult getInfo(@PathVariable("id") Long id)
    {
        return success(driverUserService.selectDriverUserById(id));
    }

    /**
     * 新增司机入驻
     */
    @RequiresPermissions("driver:driveruser:add")
    @Log(title = "司机入驻", businessType = BusinessType.INSERT)
    @PostMapping
    public AjaxResult add(@RequestBody DriverUser driverUser)
    {
        return toAjax(driverUserService.insertDriverUser(driverUser));
    }

    /**
     * 修改司机入驻
     */
    @RequiresPermissions("driver:driveruser:edit")
    @Log(title = "司机入驻", businessType = BusinessType.UPDATE)
    @PutMapping
    public AjaxResult edit(@RequestBody DriverUser driverUser)
    {
        return toAjax(driverUserService.updateDriverUser(driverUser));
    }

    /**
     * 删除司机入驻
     */
    @RequiresPermissions("driver:driveruser:remove")
    @Log(title = "司机入驻", businessType = BusinessType.DELETE)
	@DeleteMapping("/{ids}")
    public AjaxResult remove(@PathVariable Long[] ids)
    {
        return toAjax(driverUserService.deleteDriverUserByIds(ids));
    }



    /**
     * 修改审批状态
     */
    @PutMapping("/userSuccessUpdata")
    public AjaxResult userSuccessUpdata(@RequestBody DriverUser driverUser)
    {
        return toAjax(driverUserService.updateCarStatus(driverUser));
    }


}
