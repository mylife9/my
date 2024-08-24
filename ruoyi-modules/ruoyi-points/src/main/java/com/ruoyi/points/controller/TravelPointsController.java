package com.ruoyi.points.controller;

import java.util.List;
import javax.servlet.http.HttpServletResponse;

import com.ruoyi.points.domain.TravelPoints;
import com.ruoyi.points.service.impl.TravelPointsServiceImpl;
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
import com.ruoyi.common.core.utils.poi.ExcelUtil;
import com.ruoyi.common.core.web.page.TableDataInfo;

/**
 * 出行分Controller
 * 
 * @author ruoyi
 * @date 2024-08-22
 */
@RestController
@RequestMapping("/travel")
public class TravelPointsController extends BaseController
{
    @Autowired
    private TravelPointsServiceImpl travelPointsService;

    /**
     * 查询出行分列表
     */
    @RequiresPermissions("travel:travel:list")
    @GetMapping("/list")
    public TableDataInfo list(TravelPoints travelPoints)
    {
        startPage();
        List<TravelPoints> list = travelPointsService.selectTravelPointsList(travelPoints);
        return getDataTable(list);
    }

    /**
     * 导出出行分列表
     */
    @RequiresPermissions("travel:travel:export")
    @Log(title = "出行分", businessType = BusinessType.EXPORT)
    @PostMapping("/export")
    public void export(HttpServletResponse response, TravelPoints travelPoints)
    {
        List<TravelPoints> list = travelPointsService.selectTravelPointsList(travelPoints);
        ExcelUtil<TravelPoints> util = new ExcelUtil<TravelPoints>(TravelPoints.class);
        util.exportExcel(response, list, "出行分数据");
    }

    /**
     * 获取出行分详细信息
     */
    @RequiresPermissions("travel:travel:query")
    @GetMapping(value = "/{id}")
    public AjaxResult getInfo(@PathVariable("id") Long id)
    {
        return success(travelPointsService.selectTravelPointsById(id));
    }

    /**
     * 新增出行分
     */
    @RequiresPermissions("travel:travel:add")
    @Log(title = "出行分", businessType = BusinessType.INSERT)
    @PostMapping
    public AjaxResult add(@RequestBody TravelPoints travelPoints)
    {
        return toAjax(travelPointsService.insertTravelPoints(travelPoints));
    }

    /**
     * 修改出行分
     */
    @RequiresPermissions("travel:travel:edit")
    @Log(title = "出行分", businessType = BusinessType.UPDATE)
    @PutMapping
    public AjaxResult edit(@RequestBody TravelPoints travelPoints)
    {
        return toAjax(travelPointsService.updateTravelPoints(travelPoints));
    }

    /**
     * 删除出行分
     */
    @RequiresPermissions("travel:travel:remove")
    @Log(title = "出行分", businessType = BusinessType.DELETE)
	@DeleteMapping("/{ids}")
    public AjaxResult remove(@PathVariable Long[] ids)
    {
        return toAjax(travelPointsService.deleteTravelPointsByIds(ids));
    }
}
