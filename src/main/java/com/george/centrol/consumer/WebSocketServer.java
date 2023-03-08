package com.george.centrol.consumer;

import com.george.centrol.mapper.CentrolMapper;
import com.george.centrol.pojo.Centrol;
import com.george.centrol.utils.JwtAuthentication;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import javax.websocket.*;
import javax.websocket.server.PathParam;
import javax.websocket.server.ServerEndpoint;
import java.io.IOException;
import java.util.concurrent.ConcurrentHashMap;

@Component
@ServerEndpoint("/websocket/{token}")  // 注意不要以'/'结尾
public class WebSocketServer {
    public final static ConcurrentHashMap<Integer, WebSocketServer> centrolUsers = new ConcurrentHashMap<>();
    private Centrol centrolUser;
    private Session session = null;
    private static CentrolMapper centrolMapper;

    @Autowired
    public void setUserMapper(CentrolMapper centrolMapper) {
        WebSocketServer.centrolMapper = centrolMapper;
    }

    @OnOpen
    public void onOpen(Session session, @PathParam("token") String token) throws IOException {
        this.session = session;
        Integer userId = JwtAuthentication.getUserId(token);
        this.centrolUser = centrolMapper.selectById(userId);

        if (this.centrolUser != null) {
            centrolUsers.put(userId, this);
        } else {
            this.session.close();
        }
    }

    @OnClose
    public void onClose() {
        if (this.centrolUser != null) {
            centrolUsers.remove(this.centrolUser.getId());
        }
    }

    @OnMessage
    public void onMessage(String message, Session session) {  // 当做路由
    }

    @OnError
    public void onError(Session session, Throwable error) {
        error.printStackTrace();
    }

    public void sendMessage(String message) {
        synchronized (this.session) {
            try {
                this.session.getBasicRemote().sendText(message);
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
    }
}