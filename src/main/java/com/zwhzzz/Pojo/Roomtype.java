package com.zwhzzz.Pojo;

import lombok.Data;

import java.io.Serializable;

/**
 * roomtype
 * @author 
 */
@Data
public class Roomtype implements Serializable {
    private Integer id;

    private String name;

    private String photo;

    private Float price;

    private Integer livenum;//可住人数

    private Integer bednum;//床位数

    private Integer roomnum;//房间数

    private Integer avilablenum;//可用房间数

    private Integer booknum;//已预订数

    private Integer livednum;//已入住数

    private Integer status;//状态

    private String remark;

    private Integer custatus;

    private static final long serialVersionUID = 1L;
}