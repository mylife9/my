package com.ruoyi.taxi.controller;


import com.alibaba.fastjson2.JSON;
import com.ruoyi.taxi.domain.vo.PassengerVo;
import org.springframework.stereotype.Component;
import javax.websocket.*;
import javax.websocket.server.ServerEndpoint;
import java.text.DecimalFormat;
import java.util.concurrent.CopyOnWriteArraySet;

@ServerEndpoint("/websocket")
@Component
public class WebSocketController {

    private Session session;
    private static final double EARTH_RADIUS = 6378137;

    private static CopyOnWriteArraySet<WebSocketController> webSocket=new CopyOnWriteArraySet<>();
    @OnOpen 
    public void OnOpen(Session session){
        this.session=session;
        //等待人数
        webSocket.add(this);
        System.out.println("等待接单"+webSocket.size()+"人");
    }
    @OnMessage
    public void OnMessage(String message) {
        //收到消息
        System.out.println("客户端消息"+message);

    }
    public void sendMessage(String message) {
        for (WebSocketController webSocketController : webSocket) {
            System.out.println("消息通知"+message);

            PassengerVo passengerVo = JSON.parseObject(message, PassengerVo.class);
            // 将起始经度转换为弧度
            double lat1 = Math.toRadians(Double.parseDouble(String.valueOf(passengerVo.getDepLongitude())));
            // 将目标经度转换为弧度
            double lat2 = Math.toRadians(139.11);
            // 将起始纬度转换为弧度
            double lng1 = Math.toRadians(Double.parseDouble(String.valueOf(passengerVo.getDepLatitude())));
            // 将目标纬度转换为弧度
            double lng2 = Math.toRadians(39.12);

            // 计算起始点和目标点的经度差和纬度差
            double a = lat1 - lat2;
            double b = lng1 - lng2;

            // 根据经纬度差计算两点间的距离
            double s = 2 * Math.asin(Math.sqrt(Math.pow(Math.sin(a / 2), 2) +
                    Math.cos(lat1) * Math.cos(lat2) * Math.pow(Math.sin(b / 2), 2)));

            // 将弧长乘以地球半径，得到距离，单位为米
            s = s * EARTH_RADIUS;

            // 格式化距离输出为两位小数
            DecimalFormat df = new DecimalFormat("0.00");

            // 输出距离，单位为公里
            System.out.println(df.format(s / 1000) + "公里");

            // 根据距离判断订单是否在3km以外
            if (Double.valueOf(df.format(s / 1000)) > 3) {
                try{
                    webSocketController.session.getBasicRemote().sendText(message);
                }catch (Exception e) {
                    e.printStackTrace();
                }
            }
        }
    }
    @OnClose
    public void OnClose(Session session) {
        webSocket.remove(this);
        //退出连接
        System.out.println("剩余接单司机"+webSocket.size()+"人");
    }
    @OnError
    public void OnError(Throwable throwable,Session session) {
        //异常
        System.out.println("OnError");
    }


}
