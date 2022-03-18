package com.fight.pojo;

import java.util.Date;

public class Commimages {
    private Integer id;

    private Integer commid;

    private String image;

    private Date createtime;

    private Integer imagestatus;

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public Integer getCommid() {
        return commid;
    }

    public void setCommid(Integer commid) {
        this.commid = commid;
    }

    public String getImage() {
        return image;
    }

    public void setImage(String image) {
        this.image = image == null ? null : image.trim();
    }

    public Date getCreatetime() {
        return createtime;
    }

    public void setCreatetime(Date createtime) {
        this.createtime = createtime;
    }

    public Integer getImagestatus() {
        return imagestatus;
    }

    public void setImagestatus(Integer imagestatus) {
        this.imagestatus = imagestatus;
    }
}