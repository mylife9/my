package com.ruoyi.cms.controller;

import com.ruoyi.cms.domain.TbSpecification;
import com.ruoyi.cms.domain.TbSpecificationOption;
import com.ruoyi.cms.service.ITbSpecificationService;
import com.ruoyi.common.core.utils.poi.ExcelUtil;
import com.ruoyi.common.core.web.controller.BaseController;
import com.ruoyi.common.core.web.domain.AjaxResult;
import com.ruoyi.common.core.web.page.TableDataInfo;
import com.ruoyi.common.log.annotation.Log;
import com.ruoyi.common.log.enums.BusinessType;
import com.ruoyi.common.security.annotation.RequiresPermissions;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.data.redis.core.StringRedisTemplate;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletResponse;
import java.util.List;

/**
 * 规格管理Controller
 * 
 * @author ruoyi
 * @date 2024-06-21
 */
@RestController
@RequestMapping("/spec")
public class TbSpecificationController extends BaseController
{
    @Autowired
    private ITbSpecificationService tbSpecificationService;

    @Autowired
    private StringRedisTemplate stringRedisTemplate;

    @Autowired
    private RedisTemplate redisTemplate;

    /**
     * 查询规格管理列表
     */
    @RequiresPermissions("cms:spec:list")
    @GetMapping("/list")
    public TableDataInfo list(TbSpecification tbSpecification)
    {
        startPage();
        List<TbSpecification> list = tbSpecificationService.selectTbSpecificationList(tbSpecification);
        return getDataTable(list);
    }


    @RequiresPermissions("cms:spec:list")
    @GetMapping("/list/all")
    public TableDataInfo list()
    {
        List<TbSpecification> list = tbSpecificationService.selectTbSpecificationList(null);
        return getDataTable(list);
    }

    /**
     * 导出规格管理列表
     */
    @RequiresPermissions("cms:spec:export")
    @Log(title = "规格管理", businessType = BusinessType.EXPORT)
    @PostMapping("/export")
    public void export(HttpServletResponse response, TbSpecification tbSpecification)
    {
        List<TbSpecification> list = tbSpecificationService.selectTbSpecificationList(tbSpecification);
        ExcelUtil<TbSpecification> util = new ExcelUtil<TbSpecification>(TbSpecification.class);
        util.exportExcel(response, list, "规格管理数据");
    }

    /**
     * 获取规格管理详细信息
     */
    @RequiresPermissions("cms:spec:query")
    @GetMapping(value = "/{id}")
    public AjaxResult getInfo(@PathVariable("id") Long id)
    {
        return success(tbSpecificationService.selectTbSpecificationById(id));
    }

    /**
     * 根据规格ID查询规格参数
     */
    @RequiresPermissions("cms:spec:query")
    @GetMapping(value = "/options/{id}")
    public AjaxResult getOptions(@PathVariable("id") Long id)
    {
        List list = redisTemplate.opsForList().range("spec:option:" + id, 0, -1);
        if(list!= null && list.size()>0){
            System.out.println("走缓存...");
            return success(list);
        }else{
            System.out.println("走数据库...");
            List<TbSpecificationOption> list2 = tbSpecificationService.selectTbSpecificationOptionsById(id);
            redisTemplate.opsForList().leftPushAll("spec:option:" + id,list2);
            return success(list2);
        }
    }

    /**
     * 新增规格管理
     */
    @RequiresPermissions("cms:spec:add")
    @Log(title = "规格管理", businessType = BusinessType.INSERT)
    @PostMapping
    public AjaxResult add(@RequestBody TbSpecification tbSpecification)
    {
        return toAjax(tbSpecificationService.insertTbSpecification(tbSpecification));
    }

    /**
     * 修改规格管理
     */
    @RequiresPermissions("cms:spec:edit")
    @Log(title = "规格管理", businessType = BusinessType.UPDATE)
    @PutMapping
    public AjaxResult edit(@RequestBody TbSpecification tbSpecification)
    {
        return toAjax(tbSpecificationService.updateTbSpecification(tbSpecification));
    }

    /**
     * 删除规格管理
     */
    @RequiresPermissions("cms:spec:remove")
    @Log(title = "规格管理", businessType = BusinessType.DELETE)
	@DeleteMapping("/{ids}")
    public AjaxResult remove(@PathVariable Long[] ids)
    {
        return toAjax(tbSpecificationService.deleteTbSpecificationByIds(ids));
    }
}
