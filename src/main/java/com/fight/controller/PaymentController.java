package com.fight.controller;


import com.alipay.api.internal.util.AlipaySignature;
import com.fight.Constants.Constants;
import com.fight.config.AlipayConfig;
import com.fight.exception.ShopException;
import com.fight.pojo.Order;
import com.fight.pojo.soldrecord;
import com.fight.service.AlipayService;
import com.fight.service.OrderService;
import com.fight.service.SoldrecordService;
import com.fight.util.JwtUtil;
import com.fight.vo.ResultVo;
import com.google.common.collect.Maps;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.math.BigDecimal;
import java.util.HashMap;
import java.util.Iterator;
import java.util.Map;
import java.util.UUID;



@RestController
public class PaymentController {

    @Autowired(required = true)
    AlipayService alipayService;
    @Autowired
    OrderService orderService;

    @Autowired
    SoldrecordService soldrecordService;

    @RequestMapping("/pay")
    @ResponseBody
    public void payMent(String commdesc,Order order, HttpServletResponse response, HttpServletRequest request) {
        if (order.getBuyerid()==order.getSellerid()){
            throw new ShopException(Constants.FAILED_STATUS,"自己不能买自己的商品");
        }
        try {
            System.out.println(order.getStatus());
            UUID uuid = UUID.randomUUID();
            String str = uuid.toString();
            String uuidStr = str.replace("-", "");
            order.setOrderid(uuidStr);
            alipayService.aliPay(commdesc,order, response, request);
            /* return new ResultVo(Constants.SUCCESS_STATUS,"购买成功");*/
        } catch (IOException e) {
            e.printStackTrace();
        }
       /* String token = request.getHeader("token");
        String userId = JwtUtil.getUserId(token);*/  //这里需要打开但是 没有前端联调就先注释 不然看不了效果
        order.setBuyerid(Integer.parseInt("1"));
        //在数据库中生成订单
        order.setSum(order.getCount() * order.getSoldprice());

        Integer count = orderService.addOrder(order);
        if (count==1){
            //生成一条售出记录
            soldrecord soldrecord = new soldrecord();
            soldrecord.setCommid(order.getCommid());
            soldrecord.setCommname(order.getCommname());
            soldrecord.setCommdesc(commdesc);
           soldrecord.setFahuostatus("待发货");
           soldrecord.setUserid(order.getSellerid());
           soldrecord.setBuyerid(order.getBuyerid());
           soldrecord.setThinkmoney(BigDecimal.valueOf(order.getCount()*order.getSoldprice()));
           soldrecord.setOrderid(order.getOrderid());
            Integer count1 = soldrecordService.addSoldRecord(soldrecord);
            if (count1==0){
               throw new ShopException(Constants.FAILED_STATUS,"系统异常");
            }
        }



    }


    @RequestMapping(value = "/alipayReturnNotice")
    public ResultVo alipayReturnNotice(HttpServletRequest request, HttpServletRequest response) throws Exception {

        //log.info("支付成功, 进入同步通知接口...");
        System.out.println("支付成功，进入同步通知接口");
        //获取支付宝GET过来反馈信息
        Map<String, String> params = new HashMap<String, String>();
        Map<String, String[]> requestParams = request.getParameterMap();
        for (Iterator<String> iter = requestParams.keySet().iterator(); iter.hasNext(); ) {
            String name = (String) iter.next();
            String[] values = (String[]) requestParams.get(name);
            String valueStr = "";
            for (int i = 0; i < values.length; i++) {
                valueStr = (i == values.length - 1) ? valueStr + values[i]
                        : valueStr + values[i] + ",";
            }
            //乱码解决，这段代码在出现乱码时使用
            valueStr = new String(valueStr.getBytes("ISO-8859-1"), "utf-8");
            params.put(name, valueStr);
        }
        boolean signVerified = AlipaySignature.rsaCheckV1(params, AlipayConfig.alipay_public_key, AlipayConfig.charset, AlipayConfig.sign_type); //调用SDK验证签名
        //——请在这里编写您的程序（以下代码仅作参考）——
        if (signVerified) {
            //商户订单号
            /*            String out_trade_no = new String(request.getParameter("out_trade_no").getBytes("ISO-8859-1"), "UTF-8");*/
            String order_number = new String(request.getParameter("out_trade_no").getBytes("ISO-8859-1"), "UTF-8");

           /* //支付宝交易号
            String trade_no = new String(request.getParameter("trade_no").getBytes("ISO-8859-1"), "UTF-8");
*/
            //付款金额，这里获取到三个参数就可以了，后面逻辑代码自己创作
            String total_amount = new String(request.getParameter("total_amount").getBytes("ISO-8859-1"), "UTF-8");

            System.out.println(order_number);
            //根据订单号修改状态（已经支付）
            Integer count = orderService.updateStatus1(1, order_number);
            /* System.out.println(trade_no);*/
            if (count == 1) {
                soldrecordService.updateIsPay(1,order_number);
                return new ResultVo(Constants.SUCCESS_STATUS, "支付成功");
            } else {
                return new ResultVo(Constants.FAILED_STATUS, "支付失败");
            }
            //与此同时生成一条售出记录   添加事务 保持一致性

        }
   return null; }
}
