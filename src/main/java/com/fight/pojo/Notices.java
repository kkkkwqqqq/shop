package com.fight.pojo;

import java.util.Date;

public class Notices {
    private Integer id;

    private Integer userid;

    private String whys;

    private Integer isread;

    private Date nttime;

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public Integer getUserid() {
        return userid;
    }

    public void setUserid(Integer userid) {
        this.userid = userid;
    }

    public String getWhys() {
        return whys;
    }

    public void setWhys(String whys) {
        this.whys = whys == null ? null : whys.trim();
    }

    public Integer getIsread() {
        return isread;
    }

    public void setIsread(Integer isread) {
        this.isread = isread;
    }

    public Date getNttime() {
        return nttime;
    }

    public void setNttime(Date nttime) {
        this.nttime = nttime;
    }
}