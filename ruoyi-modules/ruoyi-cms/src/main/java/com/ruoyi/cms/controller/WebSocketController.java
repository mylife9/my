package com.ruoyi.cms.controller;

import com.alibaba.fastjson2.JSON;
import com.alibaba.fastjson2.JSONObject;
import com.ruoyi.cms.domain.OrderInfo;
import com.ruoyi.cms.service.IOrderInfoService;
import com.ruoyi.cms.utils.JWUtil;
import com.ruoyi.cms.utils.Utils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import javax.websocket.OnClose;
import javax.websocket.OnMessage;
import javax.websocket.OnOpen;
import javax.websocket.Session;
import javax.websocket.server.ServerEndpoint;
import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.net.URL;
import java.net.URLConnection;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Map;
import java.util.concurrent.CopyOnWriteArraySet;

/**
 * @version 1.0
 * @Author 佩奇🍂
 * @Date 2024/8/22 11:23
 * @注释
 */
@ServerEndpoint("/websocket")
@Component
//@ServerEndpoint(value = "/websocket", encoders = ServerEncoder.class)
public class WebSocketController {
    private Session session;
    private static IOrderInfoService orderInfoService;
    @Autowired
    public void setINoticeService(IOrderInfoService iOrderInfoService) {
        WebSocketController.orderInfoService = iOrderInfoService;
    }
    private static CopyOnWriteArraySet<WebSocketController> webSocket=new CopyOnWriteArraySet<>();
    @OnOpen
    public void OnOpen(Session session){
        this.session=session;
        //等待人数
        webSocket.add(this);
        System.out.println("目前人数"+webSocket.size()+"人");

    }
    @OnMessage
    public void OnMessage(String message) {
        //接受客户端消息
        System.out.println("客户端消息"+message);
        //模拟经纬度
        Map map = JWUtil.calculateTotalFee();
        //查询订单数据
        OrderInfo orderInfo = orderInfoService.getselectOrderInfoBytel(message);
        //距离(km)
        double distance = Utils.calculateDistance(Double.parseDouble(orderInfo.getPickUpPassengerLongitude()),Double.parseDouble(orderInfo.getPickUpPassengerLatitude()) ,Double.parseDouble( (String) map.get("J")),Double.parseDouble((String) map.get("W")) );

        //时间(分钟)
        int duration = Utils.calculateTime(orderInfo.getPickUpPassengerTime(), orderInfo.getPassengerGetoffTime());

        //计算价格
        DateFormat df = new SimpleDateFormat("HH:mm");
        String format = df.format(orderInfo.getPickUpPassengerTime());
        double price = Utils.calculateTotalFee(distance, duration, format);
        //修改订单金额，乘客下车位置
        //修改订单状态为6到达目的地，行程结束，未支付,模拟下车地点经纬度, ,修改订单表中乘客下车地点经纬度
        orderInfoService.updateInfoposition(message, (String) map.get("J"), (String) map.get("W"),price);
        try {
            // 2、根据经纬度获取地址
            String formattedAddress = getAMapByLngAndLat(orderInfo.getPassengerGetoffLongitude(), orderInfo.getPassengerGetoffLatitude(), "a741a390fc13c33bc447259eb1d35794");
            System.out.println("转换后地址为：" + formattedAddress);
        } catch (Exception e) {
            e.printStackTrace();
        }
        sendMessage(JSON.toJSONString(orderInfo));
    }



    private static String getResponse(String serverUrl) {
        // 用JAVA发起http请求，并返回json格式的结果
        StringBuffer result = new StringBuffer();
        try {
            URL url = new URL(serverUrl);
            URLConnection conn = url.openConnection();
            BufferedReader in = new BufferedReader(new InputStreamReader(conn.getInputStream()));
            String line;
            while ((line = in.readLine()) != null) {
                result.append(line);
            }
            in.close();
        } catch (Exception e) {
            e.printStackTrace();
        }
        return result.toString();
    }
    /**
     * 将经纬度getLng， getLat 通过getAMapByLngAndLat方法转换地址
     *
     * @param getLng 经度
     * @param getLat 纬度
     * @param key    高德地图应用key
     * @return 地址名称
     * @throws Exception
     */
    public static String getAMapByLngAndLat(String getLng, String getLat, String key) throws Exception {
        String url;
        try {
            url = "http://restapi.amap.com/v3/geocode/regeo?output=JSON&location=" + getLng + ","
                    + getLat + "&key=" + key + "&radius=0&extensions=base";
            String queryResult = getResponse(url); // 高德接品返回的是JSON格式的字符串
            // 将获取结果转为json数据
            JSONObject obj = JSONObject.parseObject(queryResult);
            System.out.println("obj为：" + obj);
            if (obj.get("status").toString().equals("1")) {
                // 如果没有返回-1
                JSONObject regeocode = obj.getJSONObject("regeocode");
                if (regeocode.size() > 0) {
                    // 在regeocode中拿到 formatted_address 具体位置
                    return regeocode.get("formatted_address").toString();
                } else {
                    throw new RuntimeException("未找到相匹配的地址！");
                }
            } else {
                throw new RuntimeException("请求错误！");
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return "-1";
    }

    public void sendMessage(String message) {
        for (WebSocketController webSocketController : webSocket) {
            System.out.println("消息通知"+message);
            try{
                webSocketController.session.getBasicRemote().sendText(message);
            }catch (Exception e) {
                e.printStackTrace();
            }
        }
    }
    @OnClose
    public void OnClose(Session session) {
        webSocket.remove(this);
        //退出连接
        System.out.println("剩余接单司机"+webSocket.size()+"人");
    }
//    @OnError
//    public void OnError(Throwable throwable,Session session) {
//        //异常
//        System.out.println("OnError");
//    }

}
