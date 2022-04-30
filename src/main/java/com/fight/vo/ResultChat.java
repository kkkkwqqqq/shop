package com.fight.vo;

public class ResultChat {
    private Integer status; //状态码  200表示请求成功 100表示错误
    private String message; //返回信息
    private Object data1;    //返回数据
    private Object data2;

    public ResultChat(Integer status, String message, Object data1, Object data2) {
        this.status = status;
        this.message = message;
        this.data1 = data1;
        this.data2 = data2;
    }

    public Integer getStatus() {
        return status;
    }

    public void setStatus(Integer status) {
        this.status = status;
    }

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }

    public Object getData1() {
        return data1;
    }

    public void setData1(Object data1) {
        this.data1 = data1;
    }

    public Object getData2() {
        return data2;
    }

    public void setData2(Object data2) {
        this.data2 = data2;
    }
}
