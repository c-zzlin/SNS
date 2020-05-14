package com.clim.im.controller;

import com.alibaba.fastjson.JSON;
import com.clim.im.config.ServerEncoder;
import com.clim.im.model.dto.SendMsg;
import com.clim.im.model.entity.OnLineUser;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.redis.core.StringRedisTemplate;
import org.springframework.stereotype.Component;

import javax.annotation.PostConstruct;
import javax.websocket.*;
import javax.websocket.server.PathParam;
import javax.websocket.server.ServerEndpoint;
import java.io.IOException;
import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;
import java.util.concurrent.CopyOnWriteArraySet;
import java.util.concurrent.atomic.AtomicInteger;

@ServerEndpoint(value = "/ws/{user_id}/{from}",encoders = { ServerEncoder.class })
@Component
public class WebSocketServer {


   /* @Autowired
    public static StringRedisTemplate redisTemplate;*/
   //此处是解决无法注入的关键
   //  这里使用静态，让 service 属于类
   private static StringRedisTemplate redisTemplate;

    // 注入的时候，给类的 service 注入
    @Autowired
    public void setChatService(StringRedisTemplate redisTemplate) {
        WebSocketServer.redisTemplate = redisTemplate;
    }


    @PostConstruct
    public void init() {
        System.out.println("websocket 加载");
    }
    private static Logger log = LoggerFactory.getLogger(WebSocketServer.class);
    private static final AtomicInteger OnlineCount = new AtomicInteger(0);
    // concurrent包的线程安全Set，用来存放每个客户端对应的Session对象。
    private static CopyOnWriteArraySet<Session> SessionSet = new CopyOnWriteArraySet<Session>();
    public static Map<String, OnLineUser> onlineUserMap = new ConcurrentHashMap<String,OnLineUser>();
    private String user_id="";
    private String from="";
    /**
     * 连接建立成功调用的方法
     */
    @OnOpen
    public void onOpen(@PathParam(value = "user_id")String user_id,
                       @PathParam(value = "from")String from,
                       Session session) {
        log.info("从"+from+"页面进来");
        OnLineUser onLineUser = new OnLineUser(session, from);
        onlineUserMap.put(user_id, onLineUser);
        this.user_id=user_id;
        this.from=from;
        int cnt = OnlineCount.incrementAndGet(); // 在线数加1
        log.info("user_id:   "+user_id);

        redisTemplate.opsForValue().set("WS"+from+":"+user_id,from);

        log.info("有连接加入，当前连接数为：{}", cnt,"id为"+user_id);
    }

    /**
     * 连接关闭调用的方法
     */
    @OnClose
    public void onClose() {
        onlineUserMap.remove(user_id);
        int cnt = OnlineCount.decrementAndGet();

        redisTemplate.delete("WS"+from+":"+user_id);
        log.info("有连接关闭，当前连接数为：{}", cnt);
        log.info("退出的ID："+user_id);
    }

    /**
     * 收到客户端消息后调用的方法
     *
     * @param data
     *            客户端发送过来的消息
     */
    @OnMessage
    public void onMessage(String data, Session session) {
        log.info("来自客户端的消息：{}");
        log.info(data);

        SendMsg sendMsg = JSON.parseObject(data, SendMsg.class);
        log.info(sendMsg.toString());

    }

    /**
     * 出现错误
     * @param session
     * @param error
     */
    @OnError
    public void onError(Session session, Throwable error) {
        redisTemplate.delete("WS"+from+":"+user_id);
        log.error("发生错误：{}，Session ID： {}",error.getMessage(),session.getId());
        error.printStackTrace();
    }

    /**
     * 发送消息，实践表明，每次浏览器刷新，session会发生变化。
     * @param session
     * @param message
     */
    public static void SendMessage(Session session, String message) {
        try {
            session.getBasicRemote().sendText(String.format("%s (From Server，Session ID=%s)",message,session.getId()));
        } catch (IOException e) {
            log.error("发送消息出错：{}", e.getMessage());
            e.printStackTrace();
        }
    }

    /**
     * 群发消息
     * @param message
     * @throws IOException
     */
    public static void BroadCastInfo(String message) throws IOException {
        for (Session session : SessionSet) {
            if(session.isOpen()){
                SendMessage(session, message);
            }
        }
    }

    /**
     * 指定Session发送消息
     * @param sessionId
     * @param message
     * @throws IOException
     */
    public static void SendMessage(String message,String sessionId) throws IOException {
        Session session = null;
        for (Session s : SessionSet) {
            if(s.getId().equals(sessionId)){
                session = s;
                break;
            }
        }
        if(session!=null){
            SendMessage(session, message);
        }
        else{
            log.warn("没有找到你指定ID的会话：{}",sessionId);
        }
    }

    public void sendTips(String type,String user_id) throws IOException, EncodeException {
        log.info("type:  "+type+"   user_ID:  "+user_id);
        if(onlineUserMap.get(user_id)!=null){
            if (type.equals("LIKE")) {

            }else if(type.equals("HOME")){

            }else if(type.equals("IM")){

            }else if(type.equals("FRIEND")){

            }

            onlineUserMap.get(user_id).getSession().getBasicRemote().sendText(type);
        }
    }

    public void sendOnlineFriend(SendMsg sendMsg) throws IOException, EncodeException {
        onlineUserMap.get(sendMsg.getTo()).getSession().getBasicRemote().sendObject(sendMsg);

    }

}