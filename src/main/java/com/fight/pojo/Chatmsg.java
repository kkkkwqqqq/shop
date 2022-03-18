package com.fight.pojo;

import java.util.Date;

public class Chatmsg {
    private Integer id;

    private Integer senduserid;

    private Integer reciveuserid;

    private Date sendtime;

    private String content;

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public Integer getSenduserid() {
        return senduserid;
    }

    public void setSenduserid(Integer senduserid) {
        this.senduserid = senduserid;
    }

    public Integer getReciveuserid() {
        return reciveuserid;
    }

    public void setReciveuserid(Integer reciveuserid) {
        this.reciveuserid = reciveuserid;
    }

    public Date getSendtime() {
        return sendtime;
    }

    public void setSendtime(Date sendtime) {
        this.sendtime = sendtime;
    }

    public String getContent() {
        return content;
    }

    public void setContent(String content) {
        this.content = content == null ? null : content.trim();
    }
}