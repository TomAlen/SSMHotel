package com.zwhzzz.Pojo;

import lombok.Data;

import java.io.Serializable;
import java.util.Date;

/**
 * checkinn
 * @author 
 */
@Data
public class Checkinn implements Serializable {
    private Integer id;

    private Integer roomid;

    private Float checkinprice;

    private Integer roomtypeid;

    private String name;

    private String realname;

    private String idcard;

    private String mobile;

    private Integer status;

    private String arrivetime;

    private String leavetime;

    private Date createtime;

    private Integer bookorderid;

    private String remark;

    private static final long serialVersionUID = 1L;


}