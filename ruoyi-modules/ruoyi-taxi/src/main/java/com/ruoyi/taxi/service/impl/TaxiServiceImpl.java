package com.ruoyi.taxi.service.impl;

import com.ruoyi.common.core.utils.JwtUtils;
import com.ruoyi.common.core.utils.StringUtils;
import com.ruoyi.common.core.web.domain.AjaxResult;
import com.ruoyi.common.security.utils.SecurityUtils;
import com.ruoyi.taxi.domain.OrderInfo;
import com.ruoyi.taxi.domain.PassengerUser;
import com.ruoyi.taxi.domain.vo.PassengerVo;
import com.ruoyi.taxi.mapper.TaxiMapper;
import com.ruoyi.taxi.service.TaxiService;
import io.jsonwebtoken.Claims;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureAlgorithm;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.regex.Pattern;

@Service
public class TaxiServiceImpl implements TaxiService {
    @Autowired
    TaxiMapper taxiMapper;

    //经度正则表达式
    private static final String LONGITUDE = "^-?((0|1?[0-7]?[0-9]?)(([.][0-9]{1,4})?)|180(([.][0]{1,4})?))$";
    //纬度正则表达式
    private static final String LATITUDE = "^-?((0|[1-8]?[0-9]?)(([.][0-9]{1,4})?)|90(([.][0]{1,4})?))$";

    @Override
    public AjaxResult saveOrder(String token, PassengerVo passengerVo) {

        //判断用户是否输入起始位置
        if(StringUtils.isEmpty(passengerVo.getDepLongitude()) && StringUtils.isEmpty(passengerVo.getDepLatitude())){
            return AjaxResult.error("请先输入您的起始位置");
        }

        //判断起始位置的经纬度是否合规
        boolean depLongitudePattern = Pattern.matches(LONGITUDE, passengerVo.getDepLongitude());
        boolean depLatitudePattern = Pattern.matches(LATITUDE, passengerVo.getDepLatitude());
        if(!depLongitudePattern && !depLatitudePattern){
            System.out.println("您输入的起始位置不符合规范");
            return AjaxResult.error("您输入的起始位置不符合规范");
        }

        //判断用户是否输入目的地
        if(StringUtils.isEmpty(passengerVo.getDestLongitude()) && StringUtils.isEmpty(passengerVo.getDestLatitude())){
            return AjaxResult.error("请您输入您的目的地");
        }

        //判断起始位置的经纬度是否合规
        boolean destLongitudePattern = Pattern.matches(LONGITUDE, passengerVo.getDepLongitude());
        boolean destLatitudePattern = Pattern.matches(LATITUDE, passengerVo.getDepLatitude());
        if(!destLongitudePattern && !destLatitudePattern){
            System.out.println("您输入的目的地不符合规范");
            return AjaxResult.error("您输入的目的地不符合规范");
        }

        //解析token获取用户id
        Claims body = Jwts.parser().parseClaimsJws(token).getBody();
        String passengerId = body.get("passengerId", String.class);

        //根据id获取用户信息
        PassengerUser user = taxiMapper.selectPassenger(passengerId);

        //判断用户年龄小于16岁，提示用户不能单独乘车
        if(user.getPassengerAge()<16){
            AjaxResult.error("未成年不可独自乘车");
        }

        //查询出用户最新的上一个订单
        OrderInfo orderInfo = taxiMapper.selectOrder(user.getPassengerId());

        //如果有进行判断是否未支付
        if(orderInfo!=null){
            if(orderInfo.getOrderStatus()==6){
                return AjaxResult.error("您上一单金额未支付,请先支付后在下单");
            }
        }

        Integer order = taxiMapper.saveOrder(passengerVo,user);

        if(order>0){

        }





        return null;
    }
}
