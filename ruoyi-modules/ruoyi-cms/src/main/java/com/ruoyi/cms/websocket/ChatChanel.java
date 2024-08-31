package com.ruoyi.cms.websocket;

import com.alibaba.fastjson2.JSONObject;
import org.springframework.stereotype.Component;
import org.springframework.web.bind.annotation.CrossOrigin;

import javax.websocket.*;
import javax.websocket.server.PathParam;
import javax.websocket.server.ServerEndpoint;
import java.io.IOException;
import java.util.List;
import java.util.Map;
import java.util.Vector;
import java.util.concurrent.ConcurrentHashMap;

/**
 * @version 1.0
 * @Author 佩奇🍂
 * @Date 2024/7/19 9:47
 * @注释
 */
@ServerEndpoint("/chat/{room}/{username}")
@Component
@CrossOrigin
public class ChatChanel {


    private static  Map<String,List<Session>> userMap=new ConcurrentHashMap<>();

    @OnOpen
    public void onOpen(Session session,@PathParam("room")String room,@PathParam("username")String username) throws IOException {
        List<Session> userList = userMap.get(room);
        if (userList==null){
            userList=new Vector<>();
            userMap.put(room,userList);
        }
        userList.add(session);
        System.out.println("open.."+username+"进入直播间..当前在线人数:"+userList.size());
        Map<String, Object> userProperties = session.getUserProperties();
        userProperties.put("username",username);
        userProperties.put("room",room);
        session.getBasicRemote().sendText("当前直播间在线人数为,"+userList.size());
    }
    @OnMessage
    public void onMessage(String message, Session session) throws IOException {
        System.out.println(message);
        String username = (String) session.getUserProperties().get("username");
        String room = (String) session.getUserProperties().get("room");
        Map map = JSONObject.parseObject(message, Map.class);

        String  type=(String) map.get("type");
        String username2 = (String) map.get("username");

        if (type.equals("1")){
            List<Session> sessions = userMap.get(room);
            for (Session user : sessions) {
                user.getBasicRemote().sendText(username+":"+message);
            }
        }else {
            List<Session> sessions = userMap.get(room);
            System.out.println("onMessage...."+message);
            for (Session user : sessions) {
                String username1 = (String) user.getUserProperties().get("username");
                if (username1.equals(username2)){
                    user.getBasicRemote().sendText(username+":"+message);
                }

            }
            session.getBasicRemote().sendText(username+":"+message);
        }


    }
    @OnClose
    public void onClose(Session session){
        String room = (String) session.getUserProperties().get("room");
        List<Session> sessions = userMap.get(room);
        sessions.remove(session);
        System.out.println("onClose...当前在线人数:"+sessions.size());
    }
    @OnError
    public void onError(Throwable throwable,Session session){
        System.out.println("onError....");
    }
}
