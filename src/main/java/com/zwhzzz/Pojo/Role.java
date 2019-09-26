package com.zwhzzz.Pojo;

import lombok.Data;

import java.io.Serializable;

/**
 * role
 * @author 
 */
@Data
public class Role implements Serializable {
    private Integer id;

    private String name;

    private String remark;

    private static final long serialVersionUID = 1L;


}