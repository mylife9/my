package com.ruoyi.approve.controller;

import com.ruoyi.approve.domain.TbCar;
import com.ruoyi.approve.service.ICarService;
import com.ruoyi.common.core.utils.poi.ExcelUtil;
import com.ruoyi.common.core.web.controller.BaseController;
import com.ruoyi.common.core.web.domain.AjaxResult;
import com.ruoyi.common.core.web.page.TableDataInfo;
import com.ruoyi.common.log.annotation.Log;
import com.ruoyi.common.log.enums.BusinessType;
import com.ruoyi.common.security.annotation.RequiresPermissions;
import org.springframework.web.bind.annotation.*;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletResponse;
import java.util.List;

/**
 * 车辆管理Controller
 * 
 * @author ruoyi
 * @date 2024-08-17
 */

@RestController
@RequestMapping("/car/car")
public class CarApproveController extends BaseController
{
    @Resource
    private ICarService carService;



    /**
     * 查询车辆管理列表
     */
/*    @RequiresPermissions("driver:car:list")*/
    @GetMapping("/list")
    public TableDataInfo list(TbCar car)
    {
        startPage();
        List<TbCar> list = carService.selectCarList(car);
        return getDataTable(list);
    }

    /**
     * 导出车辆管理列表
     */
    @RequiresPermissions("driver:car:export")
    @Log(title = "车辆管理", businessType = BusinessType.EXPORT)
    @PostMapping("/export")
    public void export(HttpServletResponse response, TbCar car)
    {
        List<TbCar> list = carService.selectCarList(car);
        ExcelUtil<TbCar> util = new ExcelUtil<TbCar>(TbCar.class);
        util.exportExcel(response, list, "车辆管理数据");
    }

    /**
     * 获取车辆管理详细信息
     */
    @RequiresPermissions("driver:car:query")
    @GetMapping(value = "/{id}")
    public AjaxResult getInfo(@PathVariable("id") Long id)
    {
        return success(carService.selectCarById(id));
    }

    /**
     * 新增车辆管理
     */
    @RequiresPermissions("driver:car:add")
    @Log(title = "车辆管理", businessType = BusinessType.INSERT)
    @PostMapping
    public AjaxResult add(@RequestBody TbCar car)
    {
        return toAjax(carService.insertCar(car));
    }

    /**
     * 修改车辆管理
     */
    @RequiresPermissions("driver:car:edit")
    @Log(title = "车辆管理", businessType = BusinessType.UPDATE)
    @PutMapping
    public AjaxResult edit(@RequestBody TbCar car)
    {
        return toAjax(carService.updateCar(car));
    }

    /**
     * 删除车辆管理
     */
    @RequiresPermissions("driver:car:remove")
    @Log(title = "车辆管理", businessType = BusinessType.DELETE)
	@DeleteMapping("/{ids}")
    public AjaxResult remove(@PathVariable Long[] ids)
    {
        return toAjax(carService.deleteCarByIds(ids));
    }
    /**
     * 修改审批状态
     */
	@PutMapping("/carSuccessUpdata")
    public AjaxResult carSuccessUpdata(@RequestBody TbCar car)
    {

        System.out.println("Car successfully updated");


        return toAjax(carService.updateCar(car));
    }



}
