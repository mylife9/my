package com.ruoyi.cms.controller;

import com.ruoyi.cms.domain.TbContent;
import com.ruoyi.cms.service.ITbContentService;
import com.ruoyi.common.core.utils.poi.ExcelUtil;
import com.ruoyi.common.core.web.controller.BaseController;
import com.ruoyi.common.core.web.domain.AjaxResult;
import com.ruoyi.common.core.web.page.TableDataInfo;
import com.ruoyi.common.log.annotation.Log;
import com.ruoyi.common.log.enums.BusinessType;
import com.ruoyi.common.security.annotation.RequiresPermissions;
import freemarker.template.Configuration;
import freemarker.template.Template;
import freemarker.template.TemplateException;
import org.apache.commons.io.IOUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.ui.freemarker.FreeMarkerTemplateUtils;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletResponse;
import java.io.ByteArrayInputStream;
import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * 广告信息Controller
 * 
 * @author ruoyi
 * @date 2024-07-22
 */
@RestController
@RequestMapping("/content")
public class TbContentController extends BaseController
{
    @Autowired
    private ITbContentService tbContentService;

    /**
     * 查询广告信息列表
     */
    @RequiresPermissions("cms:content:list")
    @GetMapping("/list")
    public TableDataInfo list(TbContent tbContent)
    {
        startPage();
        List<TbContent> list = tbContentService.selectTbContentList(tbContent);
        return getDataTable(list);
    }

    /**
     * 导出广告信息列表
     */
    @RequiresPermissions("cms:content:export")
    @Log(title = "广告信息", businessType = BusinessType.EXPORT)
    @PostMapping("/export")
    public void export(HttpServletResponse response, TbContent tbContent)
    {
        List<TbContent> list = tbContentService.selectTbContentList(tbContent);
        ExcelUtil<TbContent> util = new ExcelUtil<TbContent>(TbContent.class);
        util.exportExcel(response, list, "广告信息数据");
    }

    /**
     * 获取广告信息详细信息
     */
    @RequiresPermissions("cms:content:query")
    @GetMapping(value = "/{id}")
    public AjaxResult getInfo(@PathVariable("id") Long id)
    {
        return success(tbContentService.selectTbContentById(id));
    }

    /**
     * 新增广告信息
     */
    @RequiresPermissions("cms:content:add")
    @Log(title = "广告信息", businessType = BusinessType.INSERT)
    @PostMapping
    public AjaxResult add(@RequestBody TbContent tbContent)
    {
        return toAjax(tbContentService.insertTbContent(tbContent));
    }

    /**
     * 修改广告信息
     */
    @RequiresPermissions("cms:content:edit")
    @Log(title = "广告信息", businessType = BusinessType.UPDATE)
    @PutMapping
    public AjaxResult edit(@RequestBody TbContent tbContent)
    {
        return toAjax(tbContentService.updateTbContent(tbContent));
    }

    /**
     * 删除广告信息
     */
    @RequiresPermissions("cms:content:remove")
    @Log(title = "广告信息", businessType = BusinessType.DELETE)
	@DeleteMapping("/{ids}")
    public AjaxResult remove(@PathVariable Long[] ids)
    {
        return toAjax(tbContentService.deleteTbContentByIds(ids));
    }

    @GetMapping("/sync")
    public  AjaxResult sync() throws IOException, TemplateException {
        //页面=freemarker(数据+模版)
        Configuration configuration = new Configuration();

        String path = this.getClass().getResource("/").getPath();
        File file = new File(path+"freemarker");
        configuration.setDirectoryForTemplateLoading(file);
        //根据模版名称从模版文件夹中获取模版文件
        Template template = configuration.getTemplate("banner.ftl");


        List<TbContent> list=tbContentService.findContentList();
        Map map=new HashMap<>();
        map.put("list",list);

        String html = FreeMarkerTemplateUtils.processTemplateIntoString(template, map);
        ByteArrayInputStream inputStream = new ByteArrayInputStream(html.getBytes("UTF-8"));


        File pageFile = new File("E:\\nginx-1.26.1\\nginx-1.26.1\\html\\banner.html");
        FileOutputStream outputStream = new FileOutputStream(pageFile);


        IOUtils.copy(inputStream,outputStream);
        return  success();
    }
}
