package com.fight.pojo;

import java.util.Date;

public class Collect {
    private Integer id;

    private Integer commid;

    private String commname;

    private String commdesc;

    private Date collecttime;

    private Integer collstatus;

    private Integer cmuserid;

    private String username;

    private Integer couserid;

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

    public Date getSoldtime() {
        return collecttime;
    }

    public void setSoldtime(Date soldtime) {
        this.collecttime = soldtime;
    }

    public Integer getCollstatus() {
        return collstatus;
    }

    public void setCollstatus(Integer collstatus) {
        this.collstatus = collstatus;
    }

    public Integer getCmuserid() {
        return cmuserid;
    }

    public void setCmuserid(Integer cmuserid) {
        this.cmuserid = cmuserid;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username == null ? null : username.trim();
    }

    public Integer getCouserid() {
        return couserid;
    }

    public void setCouserid(Integer couserid) {
        this.couserid = couserid;
    }
}
