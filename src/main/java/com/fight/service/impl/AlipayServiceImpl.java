package com.fight.service.impl;

import com.alipay.api.AlipayApiException;
import com.alipay.api.AlipayClient;
import com.alipay.api.DefaultAlipayClient;
import com.alipay.api.internal.util.AlipaySignature;
import com.alipay.api.request.AlipayTradePagePayRequest;
import com.fight.Constants.Constants;
import com.fight.config.AlipayConfig;
import com.fight.pojo.Order;
import com.fight.pojo.soldrecord;
import com.fight.service.AlipayService;
import com.fight.service.OrderService;
import com.fight.service.SoldrecordService;
import com.fight.util.JustPhone;
import com.fight.util.JwtUtil;
import com.fight.util.OrderCodeUtils;
import com.fight.vo.ResultVo;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.util.StringUtils;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.PrintWriter;
import java.io.UnsupportedEncodingException;
import java.math.BigDecimal;
import java.util.*;

@Service
@Slf4j
public class AlipayServiceImpl implements AlipayService {

    @Autowired
    SoldrecordService soldrecordService;
    @Override
    public void aliPay(String commdesc,Order order, HttpServletResponse response, HttpServletRequest request) throws IOException {
        response.setContentType("text/html;charset=utf-8");

        PrintWriter out = response.getWriter();
        //获得初始化的AlipayClient
        AlipayClient alipayClient = new DefaultAlipayClient(AlipayConfig.gatewayUrl, AlipayConfig.app_id, AlipayConfig.merchant_private_key, "json", AlipayConfig.charset, AlipayConfig.alipay_public_key, AlipayConfig.sign_type);
        //设置请求参数
        AlipayTradePagePayRequest aliPayRequest = new AlipayTradePagePayRequest();
        //本地同步回调，下面那个是异步回调（只有再公网下才能用）
       aliPayRequest.setReturnUrl(AlipayConfig.return_url);
        aliPayRequest.setNotifyUrl(AlipayConfig.notify_url);

        //商户订单号，后台可以写一个工具类生成一个订单号，必填
        String order_number = order.getOrderid();
        System.out.println("order_number:"+order_number);
        //付款金额，从前台获取，必填 也就是商品的价格
        String total_amount = new String(order.getCount()*order.getSoldprice()+"");
        //订单名称，必填
        String subject = new String("柯维钦");
        aliPayRequest.setBizContent("{\"out_trade_no\":\"" + order_number + "\","
                + "\"total_amount\":\"" + total_amount + "\","
                + "\"subject\":\"" + subject + "\","
                + "\"product_code\":\"FAST_INSTANT_TRADE_PAY\"}");
        //请求
        String result = null;

        try {
            result = alipayClient.pageExecute(aliPayRequest).getBody();
        } catch (AlipayApiException e) {
            e.printStackTrace();
        }

        //输出
        out.println(result);
        log.info("返回结果={}",result);


    }





}
