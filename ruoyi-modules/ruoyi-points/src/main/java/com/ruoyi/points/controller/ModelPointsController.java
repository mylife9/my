package com.ruoyi.points.controller;


import javax.servlet.http.HttpServletResponse;

import com.ruoyi.common.core.utils.poi.ExcelUtil;
import com.ruoyi.points.domain.ModelPoints;
import com.ruoyi.points.service.IModelPointsService;
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
 * 车型分Controller
 * 
 * @author ruoyi
 * @date 2024-08-22
 */
@RestController
@RequestMapping("/model")
public class ModelPointsController extends BaseController
{
    @Autowired
    private IModelPointsService modelPointsService;

    /**
     * 查询车型分列表
     */
    @RequiresPermissions("model:model:list")
    @GetMapping("/list")
    public TableDataInfo list(ModelPoints modelPoints)
    {
        startPage();
        List<ModelPoints> list = modelPointsService.selectModelPointsList(modelPoints);
        return getDataTable(list);
    }

    /**
     * 导出车型分列表
     */
    @RequiresPermissions("model:model:export")
    @Log(title = "车型分", businessType = BusinessType.EXPORT)
    @PostMapping("/export")
    public void export(HttpServletResponse response, ModelPoints modelPoints)
    {
        List<ModelPoints> list = modelPointsService.selectModelPointsList(modelPoints);
        ExcelUtil<ModelPoints> util = new ExcelUtil<ModelPoints>(ModelPoints.class);
        util.exportExcel(response, list, "车型分数据");
    }

    /**
     * 获取车型分详细信息
     */
    @RequiresPermissions("model:model:query")
    @GetMapping(value = "/{id}")
    public AjaxResult getInfo(@PathVariable("id") Long id)
    {
        return success(modelPointsService.selectModelPointsById(id));
    }

    /**
     * 新增车型分
     */
    @RequiresPermissions("model:model:add")
    @Log(title = "车型分", businessType = BusinessType.INSERT)
    @PostMapping
    public AjaxResult add(@RequestBody ModelPoints modelPoints)
    {
        return toAjax(modelPointsService.insertModelPoints(modelPoints));
    }

    /**
     * 修改车型分
     */
    @RequiresPermissions("model:model:edit")
    @Log(title = "车型分", businessType = BusinessType.UPDATE)
    @PutMapping
    public AjaxResult edit(@RequestBody ModelPoints modelPoints)
    {
        return toAjax(modelPointsService.updateModelPoints(modelPoints));
    }

    /**
     * 删除车型分
     */
    @RequiresPermissions("model:model:remove")
    @Log(title = "车型分", businessType = BusinessType.DELETE)
	@DeleteMapping("/{ids}")
    public AjaxResult remove(@PathVariable Long[] ids)
    {
        return toAjax(modelPointsService.deleteModelPointsByIds(ids));
    }
}
