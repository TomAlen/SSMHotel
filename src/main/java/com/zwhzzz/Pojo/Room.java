package com.zwhzzz.Pojo;

import lombok.Data;

import java.io.Serializable;

/**
 * room
 * @author 
 */
@Data
public class Room implements Serializable {
    private Integer id;

    private String photo;

    private String sn;

    private Integer roomtypeid;

    private Integer floorid;

    private Integer status;

    private String remark;

    private static final long serialVersionUID = 1L;

}