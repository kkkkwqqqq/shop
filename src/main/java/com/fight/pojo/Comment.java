package com.fight.pojo;

import java.util.Date;

public class Comment {
    private Integer cid;

    private Integer commid;

    private Integer cuserid;

    private Integer spuserid;

    private String content;

    private Date commtime;

    public Integer getCid() {
        return cid;
    }

    public void setCid(Integer cid) {
        this.cid = cid;
    }

    public Integer getCommid() {
        return commid;
    }

    public void setCommid(Integer commid) {
        this.commid = commid;
    }

    public Integer getCuserid() {
        return cuserid;
    }

    public void setCuserid(Integer cuserid) {
        this.cuserid = cuserid;
    }

    public Integer getSpuserid() {
        return spuserid;
    }

    public void setSpuserid(Integer spuserid) {
        this.spuserid = spuserid;
    }

    public String getContent() {
        return content;
    }

    public void setContent(String content) {
        this.content = content == null ? null : content.trim();
    }

    public Date getCommtime() {
        return commtime;
    }

    public void setCommtime(Date commtime) {
        this.commtime = commtime;
    }
}