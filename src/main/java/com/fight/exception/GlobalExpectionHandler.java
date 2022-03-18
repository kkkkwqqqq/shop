package com.fight.exception;

import com.fight.vo.ResultVo;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.validation.BindingResult;
import org.springframework.validation.ObjectError;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseBody;

import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

/*

* 描述：  处理统一异常的handler
*
*/

@ControllerAdvice
public class GlobalExpectionHandler {
    private final Logger log= LoggerFactory.getLogger(GlobalExpectionHandler.class);
    @ExceptionHandler(Exception.class)
    @ResponseBody
    public Object handleException(Exception e){
        log.error("Default Exception:",e);
        return new ResultVo(100,e.getMessage());

    }
    @ExceptionHandler(ShopException.class)
    @ResponseBody
    public Object handleImoocMallException(ShopException e) {
        log.error("ShopException: ", e);
        return new ResultVo(100,e.getMessage());
    }


}
