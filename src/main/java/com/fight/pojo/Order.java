package com.fight.pojo;

import java.util.Date;

public class Order {
    private Integer id;

    private String commname;

    private String orderid;

    private Integer soldprice;

    private String buyername;

    private String buyerphone;

    private String status;

    private String kuaidibianhao;

    private Date soldtime;

    private Integer buyerid;

    private Integer sellerid;

    private Integer commid;

    private String adress;

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getCommname() {
        return commname;
    }

    public void setCommname(String commname) {
        this.commname = commname == null ? null : commname.trim();
    }

    public String getOrderid() {
        return orderid;
    }

    public void setOrderid(String orderid) {
        this.orderid = orderid == null ? null : orderid.trim();
    }

    public Integer getSoldprice() {
        return soldprice;
    }

    public void setSoldprice(Integer soldprice) {
        this.soldprice = soldprice;
    }

    public String getBuyername() {
        return buyername;
    }

    public void setBuyername(String buyername) {
        this.buyername = buyername == null ? null : buyername.trim();
    }

    public String getBuyerphone() {
        return buyerphone;
    }

    public void setBuyerphone(String buyerphone) {
        this.buyerphone = buyerphone == null ? null : buyerphone.trim();
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status == null ? null : status.trim();
    }

    public String getKuaidibianhao() {
        return kuaidibianhao;
    }

    public void setKuaidibianhao(String kuaidibianhao) {
        this.kuaidibianhao = kuaidibianhao == null ? null : kuaidibianhao.trim();
    }

    public Date getSoldtime() {
        return soldtime;
    }

    public void setSoldtime(Date soldtime) {
        this.soldtime = soldtime;
    }

    public Integer getBuyerid() {
        return buyerid;
    }

    public void setBuyerid(Integer buyerid) {
        this.buyerid = buyerid;
    }

    public Integer getSellerid() {
        return sellerid;
    }

    public void setSellerid(Integer sellerid) {
        this.sellerid = sellerid;
    }

    public Integer getCommid() {
        return commid;
    }

    public void setCommid(Integer commid) {
        this.commid = commid;
    }

    public String getAdress() {
        return adress;
    }

    public void setAdress(String adress) {
        this.adress = adress == null ? null : adress.trim();
    }
}