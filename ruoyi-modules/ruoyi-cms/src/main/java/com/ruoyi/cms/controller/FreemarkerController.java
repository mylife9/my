package com.ruoyi.cms.controller;

import freemarker.template.Configuration;
import freemarker.template.Template;
import freemarker.template.TemplateException;
import org.apache.commons.io.CopyUtils;
import org.springframework.ui.freemarker.FreeMarkerTemplateUtils;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.io.ByteArrayInputStream;
import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.util.HashMap;
import java.util.Map;

@RestController
@RequestMapping("/freemarker")
public class FreemarkerController {

    @GetMapping("/test")
    public Boolean genPage() throws IOException, TemplateException {
        Configuration configuration = new Configuration();

        //设置模版所在文件夹的路径（告诉freemarker模版在哪个文件夹）
        String path = this.getClass().getResource("/").getPath() + "freemarker";
        File dir = new File(path);
        configuration.setDirectoryForTemplateLoading(dir);

        //告诉freemarker模版叫什么名,获取模版对象（返回值）
        Template template = configuration.getTemplate("template.ftl");


        Map data = new HashMap();
        data.put("id",1001);
        data.put("name","Pura70");
        data.put("price",6888);

        String html = FreeMarkerTemplateUtils.processTemplateIntoString(template, data);
        ByteArrayInputStream inputStream = new ByteArrayInputStream(html.getBytes("UTF-8"));


        File file = new File("C:\\page\\"+data.get("id")+".html");
        FileOutputStream outputStream = new FileOutputStream(file);

        CopyUtils.copy(inputStream,outputStream);

        return true;
    }

}
