package com.fight.pojo;

import java.math.BigDecimal;
import java.util.Date;

public class soldrecord {
    private Integer id;

    private Integer commid;

    private String commname;

    private String commdesc;

    private BigDecimal thinkmoney;

    private Date soldtime;

    private Integer userid;

    private Integer soldstatus;

    private String fahuostatus;

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

    public String getCommname() {
        return commname;
    }

    public void setCommname(String commname) {
        this.commname = commname == null ? null : commname.trim();
    }

    public String getCommdesc() {
        return commdesc;
    }

    public void setCommdesc(String commdesc) {
        this.commdesc = commdesc == null ? null : commdesc.trim();
    }

    public BigDecimal getThinkmoney() {
        return thinkmoney;
    }

    public void setThinkmoney(BigDecimal thinkmoney) {
        this.thinkmoney = thinkmoney;
    }

    public Date getSoldtime() {
        return soldtime;
    }

    public void setSoldtime(Date soldtime) {
        this.soldtime = soldtime;
    }

    public Integer getUserid() {
        return userid;
    }

    public void setUserid(Integer userid) {
        this.userid = userid;
    }

    public Integer getSoldstatus() {
        return soldstatus;
    }

    public void setSoldstatus(Integer soldstatus) {
        this.soldstatus = soldstatus;
    }

    public String getFahuostatus() {
        return fahuostatus;
    }

    public void setFahuostatus(String fahuostatus) {
        this.fahuostatus = fahuostatus == null ? null : fahuostatus.trim();
    }
}