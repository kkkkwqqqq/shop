package com.fight.pojo;

import java.math.BigDecimal;
import java.util.Date;

public class Commodity {
    private Integer commid;

    private String commname;

    private String commdesc;

    private String videourl;

    private BigDecimal orimoney;

    private BigDecimal thinkmoney;

    private Date createtime;

    private Integer commstatus;

    private Integer rednumber;

    private String category;

    private String image;

    private Integer userid;

    private String alreadsold;

    public String getAlreadsold() {
        return alreadsold;
    }

    public void setAlreadsold(String alreadsold) {
        this.alreadsold = alreadsold;
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

    public String getVideourl() {
        return videourl;
    }

    public void setVideourl(String videourl) {
        this.videourl = videourl == null ? null : videourl.trim();
    }

    public BigDecimal getOrimoney() {
        return orimoney;
    }

    public void setOrimoney(BigDecimal orimoney) {
        this.orimoney = orimoney;
    }

    public BigDecimal getThinkmoney() {
        return thinkmoney;
    }

    public void setThinkmoney(BigDecimal thinkmoney) {
        this.thinkmoney = thinkmoney;
    }

    public Date getCreatetime() {
        return createtime;
    }

    public void setCreatetime(Date createtime) {
        this.createtime = createtime;
    }

    public Integer getCommstatus() {
        return commstatus;
    }

    public void setCommstatus(Integer commstatus) {
        this.commstatus = commstatus;
    }

    public Integer getRednumber() {
        return rednumber;
    }

    public void setRednumber(Integer rednumber) {
        this.rednumber = rednumber;
    }

    public String getCategory() {
        return category;
    }

    public void setCategory(String category) {
        this.category = category == null ? null : category.trim();
    }

    public String getImage() {
        return image;
    }

    public void setImage(String image) {
        this.image = image == null ? null : image.trim();
    }

    public Integer getUserid() {
        return userid;
    }

    public void setUserid(Integer userid) {
        this.userid = userid;
    }
}
