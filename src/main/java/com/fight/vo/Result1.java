package com.fight.vo;

public class Result1 {
        private Integer status; //状态码  200表示请求成功 100表示错误
        private String message; //返回信息
        private Object token;    //返回数据
    private Object data;


    public Result1(Integer status, String message, Object token, Object data) {
        this.status = status;
        this.message = message;
        this.token = token;
        this.data = data;
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

    public Object getToken() {
        return token;
    }

    public void setToken(Object token) {
        this.token = token;
    }

    public Object getData() {
        return data;
    }

    public void setData(Object data) {
        this.data = data;
    }
}
