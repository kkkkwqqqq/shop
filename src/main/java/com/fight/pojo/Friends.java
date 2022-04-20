package com.fight.pojo;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.Date;
@Data
@AllArgsConstructor
@NoArgsConstructor
public class Friends {
    private Integer id;

    private Integer userid;

    private Integer fuserid;

    private Date addtime;

    private String username;

    private String friendname;


}
