package com.zwhzzz.Pojo;

import lombok.Data;

import java.io.Serializable;
import java.util.Date;

/**
 * book_order
 * @author 
 */
@Data
public class BookOrder implements Serializable {
    private Integer id;

    private Integer accountid;

    private Integer roomtypeid;

    private String name;

    private String realname;

    private String idcard;

    private String mobile;

    private Integer status;

    private String arrivetime;

    private String leavetime;

    private Date createtime;

    private String remark;

    private Float price;

    private Integer custatus;

    private int CheckDays;//入住天数

    private Roomtype roomtype;

    private static final long serialVersionUID = 1L;


}