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

    private Integer livenum;

    private Integer bednum;

    private Integer roomnum;

    private Integer avilablenum;

    private Integer booknum;

    private Integer livednum;

    private Integer status;

    private String remark;

    private Integer custatus;

    private static final long serialVersionUID = 1L;
}