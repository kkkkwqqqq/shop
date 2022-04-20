package com.fight.pojo;

import com.fasterxml.jackson.annotation.JsonFormat;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.Date;
@AllArgsConstructor
@NoArgsConstructor
@Data
public class Collect {
    private Integer id;

    private Integer commid;

    private String commname;

    private String commdesc;
    /*@JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss",timezone = "GMT+8")*/
    private Date collecttime;

    private Integer collstatus;

    private Integer cmuserid;

    private String username;

    private Integer couserid;


}
