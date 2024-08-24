package com.ruoyi.approve.service;
import net.sourceforge.pinyin4j.PinyinHelper;
import net.sourceforge.pinyin4j.format.HanyuPinyinCaseType;
import net.sourceforge.pinyin4j.format.HanyuPinyinOutputFormat;
import net.sourceforge.pinyin4j.format.HanyuPinyinToneType;
import net.sourceforge.pinyin4j.format.HanyuPinyinVCharType;

import org.springframework.stereotype.Service;
/**
 * @author : Wran
 * @date : 2024-08-18 20:06
 * @description :
 **/

@Service
public class PinyinCarService {
    public String convertToPinyin(String chineseText) {
        HanyuPinyinOutputFormat format = new HanyuPinyinOutputFormat();
        format.setCaseType(HanyuPinyinCaseType.LOWERCASE);
        format.setToneType(HanyuPinyinToneType.WITHOUT_TONE);
        format.setVCharType(HanyuPinyinVCharType.WITH_V);

        StringBuilder pinyinText = new StringBuilder();
        for (char c : chineseText.toCharArray()) {
            try {
                if (Character.toString(c).matches("[\\u4E00-\\u9FA5]+")) {
                    // 汉字转换为拼音
                    String[] pinyinArray = PinyinHelper.toHanyuPinyinStringArray(c, format);
                    if (pinyinArray != null) {
                        pinyinText.append(pinyinArray[0]);
                    }
                } else {
                    // 非汉字直接添加
                    pinyinText.append(c);
                }
            } catch (Exception e) {
                e.printStackTrace();
            }
        }
        return pinyinText.toString();
    }
}
