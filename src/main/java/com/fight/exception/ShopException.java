package com.fight.exception;

import com.fight.vo.ResultVo;
/*
描述  统一异常处理对象
**/

public class ShopException extends RuntimeException {
       private  final  Integer code;

       private final  String message;

    public ShopException(Integer code, String message) {
        this.code = code;
        this.message = message;
    }

    public ShopException(ResultVo resultVo) {
       this(100,resultVo.getMessage());
    }

    public Integer getCode() {
        return code;
    }

    public String getMessage() {
        return message;
    }
}
