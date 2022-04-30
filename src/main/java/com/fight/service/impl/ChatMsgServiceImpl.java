package com.fight.service.impl;

import com.fight.mapper.ChatmsgMapper;
import com.fight.pojo.Chatmsg;
import com.fight.service.ChatMsgService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class ChatMsgServiceImpl implements ChatMsgService {
        @Autowired
    private ChatmsgMapper chatmsgMapper;

    @Override
    public Integer insertContent(String textMessage, String senderid, String reciverid, String senderName, String reciverName) {
        Chatmsg chatmsg = new Chatmsg();
        chatmsg.setContent(textMessage);
        chatmsg.setReciveuserid(Integer.parseInt(reciverid));
        chatmsg.setSenduserid(Integer.parseInt(senderid));
        chatmsg.setReciverName(reciverName);
        chatmsg.setSenderName(senderName);
        int count = chatmsgMapper.insert(chatmsg);
        return  count;
    }

    @Override
    public List <Chatmsg> selectCharByUserid(Integer senduserid, Integer reciveuserid) {
        return chatmsgMapper.selectCharByUserid(senduserid,reciveuserid);
    }
}
