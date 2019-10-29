package com.dmd;

import org.springframework.stereotype.Component;

import javax.websocket.OnClose;
import javax.websocket.OnError;
import javax.websocket.OnOpen;
import javax.websocket.Session;
import javax.websocket.server.PathParam;
import javax.websocket.server.ServerEndpoint;
import java.io.IOException;
import java.util.concurrent.ConcurrentHashMap;
import java.util.concurrent.CopyOnWriteArraySet;

/**
 * @author YangAnsheng
 * @version 1.0
 * @createDate 2019/10/28 14:57
 * @Description websocket util
 */
@ServerEndpoint("/websocket/{uid}")
@Component
public class WebSocketUtils {
    //静态变量，用来记录当前在线连接数。应该把它设计成线程安全的。
    private static int onlineCount = 0;
    //concurrent包的线程安全Set，用来存放每个客户端对应的MyWebSocket对象。
    private static ConcurrentHashMap<String,CopyOnWriteArraySet<WebSocketUtils>> socketMap = new ConcurrentHashMap<>();
    //与某个客户端的连接会话，需要通过它来给客户端发送数据
    private Session session;
    /**
     * 连接建立成功调用的方法*/
    @OnOpen
    public void onOpen(Session session, @PathParam("uid") String uid) {
        //socket对象
        this.session = session;
        CopyOnWriteArraySet<WebSocketUtils> socketUtilsSet = socketMap.get(uid);
        if(socketUtilsSet == null){
            CopyOnWriteArraySet<WebSocketUtils> socketSet = new CopyOnWriteArraySet<>();
            socketSet.add(this);
            socketMap.put(uid, socketSet);
        }else {
            socketUtilsSet.add(this);
        }

        System.out.println("连接成功");
    }

    /**
     * 连接关闭调用的方法
     */
    @OnClose
    public void onClose() {
        socketMap.values().forEach(value -> value.removeIf(socket -> socket.equals(this)));
        System.out.println("连接关闭");
    }

    /**
     * @param session
     * @param error
     */
    @OnError
    public void onError(Session session, Throwable error) {
        System.out.println("发生错误");
        error.printStackTrace();
    }

    /**
     * 发送消息,uid为null群发
     * */
    public static void sendMessage(String message, String uid){
        if (socketMap.get(uid)!=null){
            socketMap.get(uid).forEach(socketUtil -> {
                try {
                    socketUtil.session.getBasicRemote().sendText(message);
                } catch (IOException e) {
                    e.printStackTrace();
                }
            });
        }else {
            socketMap.keySet().getMappedValue().forEach(socketUtil -> {
                try {
                    socketUtil.session.getBasicRemote().sendText(message);
                } catch (IOException e) {
                    e.printStackTrace();
                }
            });
        }
    }

}
