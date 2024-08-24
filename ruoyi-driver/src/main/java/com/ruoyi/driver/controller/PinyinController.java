package com.ruoyi.driver.controller;

import com.ruoyi.driver.service.PinyinService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/pin")
public class PinyinController {

    @Autowired
    private PinyinService pinyinService;

    @GetMapping("/convert")
    public String convertToPinyin(@RequestParam String chineseText) {
        return pinyinService.convertToPinyin(chineseText);
    }
}