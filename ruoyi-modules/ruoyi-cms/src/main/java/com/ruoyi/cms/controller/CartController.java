package com.ruoyi.cms.controller;

import com.alibaba.fastjson.JSONArray;
import com.ruoyi.common.core.web.controller.BaseController;
import com.ruoyi.common.core.web.domain.AjaxResult;
import io.jsonwebtoken.Claims;
import io.jsonwebtoken.Jwts;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.redis.core.StringRedisTemplate;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletRequest;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * @version 1.0
 * @Author 佩奇🍂
 * @Date 2024/7/25 20:01
 * @注释
 */
@RestController
@RequestMapping("/cart")
public class CartController extends BaseController {
    @Autowired
    StringRedisTemplate stringRedisTemplate;

    @PostMapping("/merge")
    public  AjaxResult mergeCart(@RequestBody List<Map> loaclCart, HttpServletRequest request){
        String token = request.getHeader("token");
        Claims body = Jwts.parser().setSigningKey("pinxixi").parseClaimsJws(token).getBody();
        Long id = body.get("id", Long.class);

        List<Map> redisCart=null;

        String redisCartStr = stringRedisTemplate.opsForValue().get("cart:" + id);
        if (redisCartStr==null){
            //redis用户没添加过数据。以本地缓存中数据覆盖redis就行
            stringRedisTemplate.opsForValue().set("cart:"+id, JSONArray.toJSONString(loaclCart));
            return success();
        }
        //线上有数据
        redisCart=JSONArray.parseArray(redisCartStr, Map.class);

        for (Map localStore : loaclCart) {
            Boolean storeFlag=false;
            for (Map redisStore : redisCart) {

                if (redisStore.get("name").equals(localStore.get("name"))){
                    storeFlag=true;
                    //localStorage中店铺redis存在
                    List<Map> localItems = (List<Map>) localStore.get("items");
                    List<Map> redisItems = (List<Map>) redisStore.get("items");

                    for (Map localItem : localItems) {
                        Boolean itemFlag=false;
                        for (Map redisItem : redisItems) {
                            if (localItem.get("id").equals(redisItem.get("id"))){
                                itemFlag=true;
                                //localStorage中的商品在redis中存在
                                redisItem.put("count",localItem.get("count"));
                                break;
                            }
                        }
                        if (!itemFlag){
                            //localStorage中的商品在redis中不存在
                            redisItems.add(localItem);
                        }

                    }

                }
            }
            //localStorage中店铺redis不存在
            if(!storeFlag){
                redisCart.add(localStore);
            }

        }
        stringRedisTemplate.opsForValue().set("cart:"+id, JSONArray.toJSONString(redisCart));
        return success();
    }

    @GetMapping("/get")
    public  AjaxResult getCart(HttpServletRequest request){
        String token = request.getHeader("token");
        Claims body = Jwts.parser().setSigningKey("pinxixi").parseClaimsJws(token).getBody();
        Long id = body.get("id", Long.class);
        List<Map> cart=null;
        String redisCart = stringRedisTemplate.opsForValue().get("cart:" + id);
        if (redisCart!=null){
            cart=JSONArray.parseArray(redisCart, Map.class);
        }
        return success(cart);
    }



    @PostMapping("/add")
    public AjaxResult addCart(@RequestBody Map map, HttpServletRequest request){
        String token = request.getHeader("token");
        Claims body = Jwts.parser().setSigningKey("pinxixi").parseClaimsJws(token).getBody();
        Long id = body.get("id", Long.class);

        List<Map> cart=null;
        String redisCart = stringRedisTemplate.opsForValue().get("cart:" + id);
        if (redisCart==null){
            cart=new ArrayList<>();
            Map store =new HashMap();
            store.put("name",map.remove("seller"));

            List items=new ArrayList();
            items.add(map);
            store.put("items",items);

            cart.add(store);
            stringRedisTemplate.opsForValue().set("cart:"+id, JSONArray.toJSONString(cart));
            return  success();
        }
        cart=JSONArray.parseArray(redisCart, Map.class);
        String seller= (String) map.remove("seller");
       for(Map store :cart){
           if (store.get("name").equals(seller)){
               //店铺存在
               List<Map> items = (List<Map>) store.get("items");
               for (Map item : items) {
                   if (item.get("id").equals(map.get("id"))){
                       //店铺存在商品也存在
                       item.put("count",(Integer)item.get("count")+(Integer)map.get("count"));
                       stringRedisTemplate.opsForValue().set("cart:"+id, JSONArray.toJSONString(cart));
                       return  success();

                   }
               }
               //店铺存在但是商品不存在
               items.add(map);
               stringRedisTemplate.opsForValue().set("cart:"+id,JSONArray.toJSONString(cart));
               return  success();
           }
       }

       //店铺不存在
        Map store =new HashMap();
        store.put("name",seller);

        List items=new ArrayList();
        items.add(map);
        store.put("items",items);

        cart.add(store);
        stringRedisTemplate.opsForValue().set("cart:"+id, JSONArray.toJSONString(cart));
        return   success();
    }

    @PutMapping("/update")
    public AjaxResult updateCart(@RequestBody List<Map> cart, HttpServletRequest request){
        String token = request.getHeader("token");
        Claims body = Jwts.parser().setSigningKey("pinxixi").parseClaimsJws(token).getBody();
        Long id = body.get("id", Long.class);


        stringRedisTemplate.opsForValue().set("cart:"+id, JSONArray.toJSONString(cart));
        return success();
    }
}
