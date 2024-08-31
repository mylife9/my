package com.ruoyi.cms.controller;

import com.ruoyi.cms.domain.OrderEntity;
import com.ruoyi.cms.domain.TbAddress;
import com.ruoyi.cms.service.impl.OrderServiceImpl;
import com.ruoyi.common.core.web.controller.BaseController;
import com.ruoyi.common.core.web.domain.AjaxResult;
import io.jsonwebtoken.Claims;
import io.jsonwebtoken.Jwts;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.redis.core.StringRedisTemplate;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletRequest;
import java.util.List;
import java.util.Map;

/**
 * @version 1.0
 * @Author 佩奇🍂
 * @Date 2024/7/27 14:58
 * @注释
 */
@RestController
@RequestMapping("/order")
public class OrderController extends BaseController{
    @Autowired
    OrderServiceImpl orderService;
    @Autowired
    private StringRedisTemplate stringRedisTemplate;
    @PostMapping("/preOrder")
    public AjaxResult preOrder(@RequestBody List<Map> items){
        String msg="";
        for (Map item : items) {
            String saleNum = (String) stringRedisTemplate.opsForHash().get("goods:" + item.get("id"), "saleNum");
            Integer redisCount=Integer.parseInt(saleNum);
            if (redisCount<(Integer) item.get("count")){
                msg+="["+item.get("title")+"]库存不足，剩余["+redisCount+"]件:";
            }
        }
        if (msg.equals("")){
            return  success();
        }
        return  error(msg);
    }
    @GetMapping("/getAddress")
    public AjaxResult getAddress(HttpServletRequest request){
        String token = request.getHeader("token");
        Claims claims = Jwts.parser().setSigningKey("pinxixi").parseClaimsJws(token).getBody();
        Long id = claims.get("id", Long.class);

        List<TbAddress> address = orderService.getAddress(id);
        return  success(address);
    }
    @GetMapping("/getProvince")
    public AjaxResult getProvince(HttpServletRequest request){
        List<Map> list=orderService.getProvince();
        return  success(list);
    }
    @GetMapping("/getCity/{provinceId}")
    public AjaxResult getCity(@PathVariable Integer provinceId){
        List<Map> list=orderService.getCity(provinceId);
        return  success(list);
    }
    @GetMapping("/getArea/{cityId}")
    public AjaxResult getArea(@PathVariable Integer cityId){
        List<Map> list=orderService.getArea(cityId);
        return  success(list);
    }
    @PostMapping("/saveAddress")
    public AjaxResult saveAddress(@RequestBody TbAddress address,HttpServletRequest request){
        String token = request.getHeader("token");
        Claims claims = Jwts.parser().setSigningKey("pinxixi").parseClaimsJws(token).getBody();
        Long id = claims.get("id", Long.class);

        address.setUserId(String.valueOf(id));

        orderService.saveAddres(address);
        return  success();
    }
    @PostMapping("/create")
    public AjaxResult create(@RequestBody OrderEntity orderEntity, HttpServletRequest request){
        String token = request.getHeader("token");
        Claims claims = Jwts.parser().setSigningKey("pinxixi").parseClaimsJws(token).getBody();
        Long id = claims.get("id", Long.class);
        String username = claims.get("username", String.class);

        orderEntity.getOrder().setUserId(String.valueOf(id));
        orderEntity.getOrder().setBuyerNick(username);

        String ua = request.getHeader("User-Agent");
        if (ua.contains("Windows")){
            orderEntity.getOrder().setSourceType("pc");
        }


      return  orderService.createOrder(orderEntity);

    }
}
