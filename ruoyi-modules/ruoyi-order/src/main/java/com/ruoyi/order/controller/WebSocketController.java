package com.ruoyi.order.controller;


import com.alibaba.fastjson2.JSON;
import com.ruoyi.order.pojo.OrderInfo;
import org.springframework.stereotype.Component;
import javax.websocket.*;
import javax.websocket.server.ServerEndpoint;
import java.util.concurrent.CopyOnWriteArraySet;

@ServerEndpoint("/websocket")
@Component
public class WebSocketController {

    private Session session;

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

            OrderInfo orderInfo = JSON.parseObject(message, OrderInfo.class);
            //可以再次判断是否大于3km
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
    @OnError
    public void OnError(Throwable throwable,Session session) {
        //异常
        System.out.println("OnError");
    }


}
