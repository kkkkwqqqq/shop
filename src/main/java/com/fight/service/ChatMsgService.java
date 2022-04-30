package com.fight.service;

import com.fight.pojo.Chatmsg;

import java.util.List;

public interface ChatMsgService {


    Integer insertContent(String textMessage, String senderid, String reciverid, String senderName, String reciverName);

   List< Chatmsg> selectCharByUserid(Integer senduserid, Integer reciveuserid);
}
