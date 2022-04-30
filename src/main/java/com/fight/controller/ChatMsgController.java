package com.fight.controller;

import com.fight.Constants.Constants;
import com.fight.pojo.Chatmsg;
import com.fight.service.ChatMsgService;
import com.fight.util.JwtUtil;
import com.fight.vo.Result1;
import com.fight.vo.ResultChat;
import com.fight.vo.ResultVo;
import com.sun.net.httpserver.HttpsServer;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.servlet.http.HttpServletRequest;
import java.util.List;

@Slf4j
@RestController
public class ChatMsgController {
    @Autowired
    private ChatMsgService chatMsgService;

//查看两人的历史聊天记录
    @RequestMapping("/chatRecord")
    public ResultChat chatRecord(HttpServletRequest request, Integer reciveuserid){
        String token = request.getHeader("token");
        String userId = JwtUtil.getUserId(token);

     //根据用户id 返回他的历史聊天内容
        List<Chatmsg> chatmsgs1 = chatMsgService.selectCharByUserid(Integer.parseInt(userId), reciveuserid);
        List<Chatmsg> chatmsgs2 = chatMsgService.selectCharByUserid(reciveuserid,Integer.parseInt(userId));
        return new ResultChat(Constants.SUCCESS_STATUS,"历史聊天记录返回成功",chatmsgs1,chatmsgs2);
    }


    }
