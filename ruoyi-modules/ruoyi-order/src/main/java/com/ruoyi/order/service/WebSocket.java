package com.ruoyi.order.service;

import org.springframework.stereotype.Component;

import javax.websocket.OnClose;
import javax.websocket.OnMessage;
import javax.websocket.OnOpen;
import javax.websocket.Session;
import javax.websocket.server.ServerEndpoint;
import java.io.IOException;
import java.util.concurrent.CopyOnWriteArraySet;

/**
 * @program: day0812
 * @author: gtx
 * @description:
 * @create: 2024-08-17 09:28
 */
@Component
@ServerEndpoint("/webSocket")
public class WebSocket {

    private Session session;

    private static CopyOnWriteArraySet<WebSocket> webSockets  = new CopyOnWriteArraySet<>();

    @OnOpen
    public void onOpen(Session session){

        this.session = session;
        webSockets.add(this);
        System.out.println("【webSocket消息】有新的连接，总数：}"+webSockets.size());
    }

    @OnClose
    public void onClose(){

        webSockets.remove(this);
        System.out.println("【webSocket消息】连接断开，总数：}"+webSockets.size());

    }

    @OnMessage
    public void onMessage(String message){

        System.out.println("【webSocket消息】收到客户端发来的消息:"+message);

    }


    public void sendMessage(String message){

        for (WebSocket webSocket : webSockets){

            System.out.println("【webSocket消息】广播消息，message="+message);

            try {
                webSocket.session.getBasicRemote().sendText(message);
            } catch (IOException e) {
                throw new RuntimeException(e);
            }

        }

    }

}
