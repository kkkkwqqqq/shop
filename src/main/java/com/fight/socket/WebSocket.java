package com.fight.socket;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONObject;
import com.fight.Constants.Constants;
import com.fight.exception.ShopException;
import com.fight.mapper.UserMapper;
import com.fight.service.ChatMsgService;
import com.fight.service.UserService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import javax.websocket.*;
import javax.websocket.server.PathParam;
import javax.websocket.server.ServerEndpoint;
import java.io.IOException;
import java.util.HashMap;
import java.util.Map;
import java.util.Set;
import java.util.concurrent.ConcurrentHashMap;

@Slf4j
@Component
@ServerEndpoint("/websocket/{username}")
public class WebSocket {

   @Autowired
   private UserService userService;

   @Autowired
   private ChatMsgService chatMsgService;
    /**
     * 以用户的姓名为key，WebSocket为对象保存起来
     */
    private static Map<String, WebSocket> clients = new ConcurrentHashMap<String, WebSocket>();
    /**
     * 会话
     */
    private Session session;
    /**
     * 用户名称
     */
    private String username;

    /**
     * OnOpen 表示有浏览器链接过来的时候被调用
     * OnClose 表示浏览器发出关闭请求的时候被调用
     * OnMessage 表示浏览器发消息的时候被调用
     * OnError 表示有错误发生，比如网络断开了等等
     */

    /**
     * 建立连接
     *
     * @param session
     */
    @OnOpen
    public void onOpen(@PathParam("username") String username, Session session) {
        log.info("现在来连接的客户id：" + session.getId() + "用户名：" + username);
        this.username = username;
        this.session = session;
            //把自己的信息加入到map当中去
            clients.put(username, this);
            log.info(username + "连接上了websocket");
    }

    @OnError
    public void onError(Session session, Throwable error) {
        log.info("服务端发生了错误" + error.getMessage());
        //error.printStackTrace();
    }

    /**
     * 连接关闭
     */
    @OnClose
    public void onClose() {
        //webSockets.remove(this); //移除当前建立的websocket
        clients.remove(username);
            log.info(username + "断开连接");

    }

    /**
     * 收到客户端的消息
     *
     * @param message 消息
     * @param session 会话
     */
    @OnMessage
    public void onMessage(String message, Session session) {
        try {
            log.info("来自客户端消息：" + message + "客户端的id是：" + session.getId());
            JSONObject jsonObject = JSON.parseObject(message);
            String textMessage = jsonObject.getString("message");
            String senderid = jsonObject.getString("senderid");
            String reciverid = jsonObject.getString("reciverid");
            String senderName = jsonObject.getString("senderName");
            String reciverName = jsonObject.getString("reciverName");
            Map<String, Object> map1 = new HashMap<>();
            map1.put("textMessage", textMessage);
            map1.put("senderid", senderid);
                map1.put("reciverid", reciverid);
                map1.put("senderName", senderName);
                map1.put("reciverName", reciverName);

                sendMessageTo(JSON.toJSONString(map1), reciverid);
        } catch (Exception e) {
            log.info("发生了错误了");
        }
    }

    public void sendMessageTo(String message, String reciverid) throws IOException {
        //查出接受者id对应的名字
      String ToUserName = userService.selectNameById(Integer.parseInt(reciverid));
        JSONObject jsonObject = JSON.parseObject(message);
        String textMessage = jsonObject.getString("message");
        String senderid = jsonObject.getString("senderid");
        String senderName = jsonObject.getString("senderName");
        String reciverName = jsonObject.getString("reciverName");
        for (WebSocket item : clients.values()) {
            if (item.username.equals(ToUserName)) {
                item.session.getAsyncRemote().sendText(message);
                //将信息插入到数据库
                Integer count = chatMsgService.insertContent(textMessage,senderid,reciverid,senderName,reciverName);
                if (count==0){
                    throw new ShopException(Constants.FAILED_STATUS,"插入聊天记录失败");
                }
                break;
            }
        }
    }

    public void sendMessageAll(String message, String FromUserName) throws IOException {
        //将消息发送给其他用户
        for (WebSocket item : clients.values()) {
            item.session.getAsyncRemote().sendText(message);
        }
    }



}
