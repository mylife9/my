package com.ruoyi.points.controller;

import java.math.BigDecimal;
import java.util.List;
import javax.servlet.http.HttpServletResponse;

import com.ruoyi.common.core.utils.StringUtils;
import com.ruoyi.points.domain.*;
import com.ruoyi.points.service.*;
import org.apache.ibatis.annotations.Insert;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.scheduling.annotation.Scheduled;
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
 * 积分Controller
 * 
 * @author ruoyi
 * @date 2024-08-21
 */
@RestController
@RequestMapping("/points")
public class PointsController extends BaseController
{
    @Autowired
    private IPointsService pointsService;

    @Autowired
    private ITravelPointsService travelPointsService;

    @Autowired
    private IComplianceService complianceService;

    @Autowired
    private IModelPointsService modelPointsService;

    @Autowired
    private IServicePointsService servicePointsService;

    /**
     * 查询积分列表
     */
    @RequiresPermissions("points:points:list")
    @GetMapping("/list")
    public TableDataInfo list(Points points)
    {
        startPage();
        List<Points> list = pointsService.selectPointsList(points);
        return getDataTable(list);
    }

    /**
     * 导出积分列表
     */
    @RequiresPermissions("points:points:export")
    @Log(title = "积分", businessType = BusinessType.EXPORT)
    @PostMapping("/export")
    public void export(HttpServletResponse response, Points points)
    {
        List<Points> list = pointsService.selectPointsList(points);
        ExcelUtil<Points> util = new ExcelUtil<Points>(Points.class);
        util.exportExcel(response, list, "积分数据");
    }

    /**
     * 获取积分详细信息
     */
    @RequiresPermissions("points:points:query")
    @GetMapping(value = "/{id}")
    public AjaxResult getInfo(@PathVariable("id") Long id)
    {
        return success(pointsService.selectPointsById(id));
    }

    /**
     * 新增积分
     */
    @RequiresPermissions("points:points:add")
    @Log(title = "积分", businessType = BusinessType.INSERT)
    @PostMapping
    public AjaxResult add(@RequestBody Points points)
    {
        return toAjax(pointsService.insertPoints(points));
    }

    /**
     * 修改积分
     */
    @RequiresPermissions("points:points:edit")
    @Log(title = "积分", businessType = BusinessType.UPDATE)
    @PutMapping
    public AjaxResult edit(@RequestBody Points points)
    {
        return toAjax(pointsService.updatePoints(points));
    }

    /**
     * 删除积分
     */
    @RequiresPermissions("points:points:remove")
    @Log(title = "积分", businessType = BusinessType.DELETE)
	@DeleteMapping("/{ids}")
    public AjaxResult remove(@PathVariable Long[] ids)
    {
        return toAjax(pointsService.deletePointsByIds(ids));
    }

    /**
     * 司机信誉分计算
     *
     * @return
     */
    @GetMapping("compute/{id}")
    public AjaxResult compute(@PathVariable Long id){
        Points points= new Points();
        TravelPoints travelPoints = travelPointsService.selectTravelPointsById(id);
        points.setTripScore(travelPoints.getTripScope());


        ModelPoints modelPoints = modelPointsService.selectModelPointsById(id);
        points.setCarTypePoints(modelPoints.getModelPoints());


        ServicePoints servicePoints = servicePointsService.selectServicePointsById(id);
        points.setServiceScore(servicePoints.getServicePoints());


        Compliance compliance = complianceService.selectComplianceById(id);
        points.setComplianceScore(compliance.getComplianceScope());


        points.setDriverId(travelPoints.getDriverId());

        Points points1 = pointsService.selectPointsById(id);



        if (points1.getDriverId()  == null) {
            pointsService.inter(points);
            return success();
        }else {
            int inted = points.getTripScore().intValue();
            if (inted>170){
                return error("出行分已满，无法增加");
            }
            int inted1 = points.getCarTypePoints().intValue();
            if (inted1>30){
                return error("车型分已满，无法增加");
            }
            int inted2 = points.getComplianceScore().intValue();
            if (inted2>30){
                return error("合规分已满，无法增加");
            }
            int inted3 = points.getServiceScore().intValue();
            if (inted3>100){
                return error("服务分已满，无法增加");
            }
            Double selectNum = pointsService.selectNum(points);

            points.setCurrentScore(BigDecimal.valueOf(selectNum));
            pointsService.update(points,selectNum);
            return success();
        }
    }

}
