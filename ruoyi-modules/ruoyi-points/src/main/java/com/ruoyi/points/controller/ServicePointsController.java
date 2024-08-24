package com.ruoyi.points.controller;

import javax.servlet.http.HttpServletResponse;

import com.ruoyi.common.core.utils.poi.ExcelUtil;
import com.ruoyi.points.domain.ServicePoints;
import com.ruoyi.points.service.IServicePointsService;
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
import com.ruoyi.common.core.web.controller.BaseController;
import com.ruoyi.common.core.web.domain.AjaxResult;
import com.ruoyi.common.core.web.page.TableDataInfo;

import java.util.List;

/**
 * 服务分Controller
 * 
 * @author ruoyi
 * @date 2024-08-22
 */
@RestController
@RequestMapping("/service")
public class ServicePointsController extends BaseController
{
    @Autowired
    private IServicePointsService servicePointsService;

    /**
     * 查询服务分列表
     */
    @RequiresPermissions("service:service:list")
    @GetMapping("/list")
    public TableDataInfo list(ServicePoints servicePoints)
    {
        startPage();
        List<ServicePoints> list = servicePointsService.selectServicePointsList(servicePoints);
        return getDataTable(list);
    }

    /**
     * 导出服务分列表
     */
    @RequiresPermissions("service:service:export")
    @Log(title = "服务分", businessType = BusinessType.EXPORT)
    @PostMapping("/export")
    public void export(HttpServletResponse response, ServicePoints servicePoints)
    {
        List<ServicePoints> list = servicePointsService.selectServicePointsList(servicePoints);
        ExcelUtil<ServicePoints> util = new ExcelUtil<ServicePoints>(ServicePoints.class);
        util.exportExcel(response, list, "服务分数据");
    }

    /**
     * 获取服务分详细信息
     */
    @RequiresPermissions("service:service:query")
    @GetMapping(value = "/{id}")
    public AjaxResult getInfo(@PathVariable("id") Long id)
    {
        return success(servicePointsService.selectServicePointsById(id));
    }

    /**
     * 新增服务分
     */
    @RequiresPermissions("service:service:add")
    @Log(title = "服务分", businessType = BusinessType.INSERT)
    @PostMapping
    public AjaxResult add(@RequestBody ServicePoints servicePoints)
    {
        return toAjax(servicePointsService.insertServicePoints(servicePoints));
    }

    /**
     * 修改服务分
     */
    @RequiresPermissions("service:service:edit")
    @Log(title = "服务分", businessType = BusinessType.UPDATE)
    @PutMapping
    public AjaxResult edit(@RequestBody ServicePoints servicePoints)
    {
        return toAjax(servicePointsService.updateServicePoints(servicePoints));
    }

    /**
     * 删除服务分
     */
    @RequiresPermissions("service:service:remove")
    @Log(title = "服务分", businessType = BusinessType.DELETE)
	@DeleteMapping("/{ids}")
    public AjaxResult remove(@PathVariable Long[] ids)
    {
        return toAjax(servicePointsService.deleteServicePointsByIds(ids));
    }
}
