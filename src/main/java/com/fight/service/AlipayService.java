package com.fight.service;

import com.fight.pojo.Order;
import com.fight.vo.ResultVo;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

public interface AlipayService {

   /**
    * 支付宝支付调用接口
    * @param response
    * @param request
    * @throws
    */
   void aliPay(String commdes,Order order,HttpServletResponse response, HttpServletRequest request) throws IOException;
}
