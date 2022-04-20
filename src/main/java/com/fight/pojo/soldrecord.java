package com.fight.pojo;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.math.BigDecimal;
import java.util.Date;
@Data
@AllArgsConstructor
@NoArgsConstructor
public class soldrecord {
    private Integer id;

    private Integer commid;

    private String commname;

    private String commdesc;

    private BigDecimal thinkmoney;

    private Date soldtime;

    private Integer userid;

    private Integer soldstatus;

    private String fahuostatus;

    private Integer buyerid;

    private String orderid;

    private int isPay;


}
