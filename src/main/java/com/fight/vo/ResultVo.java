package com.fight.vo;

public class ResultVo {
    private Integer status; //状态码  200表示请求成功 100表示错误
    private String message; //返回信息
    private Object data;    //返回数据


    public ResultVo( Integer status, String message, Object data) {

        this.status = status;
        this.message = message;
        this.data = data;
    }

    public ResultVo( Integer status, String message) {

        this.status = status;
        this.message = message;
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

    public Object getData() {
        return data;
    }

    public void setData(Object data) {
        this.data = data;
    }
}
