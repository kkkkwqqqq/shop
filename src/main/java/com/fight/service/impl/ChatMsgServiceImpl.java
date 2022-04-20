package com.fight.service.impl;

import com.fight.mapper.ChatmsgMapper;
import com.fight.service.ChatMsgService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class ChatMsgServiceImpl implements ChatMsgService {
        @Autowired
    private ChatmsgMapper chatmsgMapper;


}
