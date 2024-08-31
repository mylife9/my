package com.ruoyi.taxi.utils;


import lombok.extern.slf4j.Slf4j;


@Slf4j
public class TestSnowFlake {

     public void testSnow() {
        SnowFlake snowFlake = new SnowFlake(2, 1);

        long start = System.currentTimeMillis();
        for (int i = 0; i < 1000000; i++) {
            //System.out.println(snowFlake.nextId());
            log.info(String.valueOf(snowFlake.nextId()));
        }

        // System.out.println("总耗时：" + (System.currentTimeMillis() - start));
        log.info("总耗时：" + (System.currentTimeMillis() - start));

    }


}
