package com.zwhzzz.Pojo;

import lombok.Data;

import java.io.Serializable;

/**
 * menu
 * @author 
 */
@Data
public class Menu implements Serializable {
    private Integer id;

    private Integer parentid;

    private String name;

    private String url;

    private String icon;

    private static final long serialVersionUID = 1L;

}