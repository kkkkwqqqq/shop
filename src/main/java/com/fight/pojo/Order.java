package com.fight.pojo;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.Date;
@Data
@AllArgsConstructor
@NoArgsConstructor
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

   private int count;

   private int sum;

   int isPay;
}
