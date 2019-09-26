package com.zwhzzz.Pojo;

import lombok.Data;

import java.io.Serializable;

/**
 * floor
 * @author 
 */
@Data
public class Floor implements Serializable {
    private Integer id;

    private String name;

    private String remark;

    private static final long serialVersionUID = 1L;


}