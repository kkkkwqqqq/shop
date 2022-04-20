package com.fight.pojo;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.Date;
@AllArgsConstructor
@NoArgsConstructor
@Data
public class Chatmsg {
    private Integer id;

    private Integer senduserid;

    private Integer reciveuserid;

    private Date sendtime;

    private String content;

    private String senderName;

    private String reciverName;

}
