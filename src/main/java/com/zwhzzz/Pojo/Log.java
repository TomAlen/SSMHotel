package com.zwhzzz.Pojo;

import lombok.Data;

import java.io.Serializable;
import java.util.Date;

/**
 * log
 * @author 
 */
@Data
public class Log implements Serializable {
    private Integer id;

    private String content;

    private Date createtime;

    private static final long serialVersionUID = 1L;

}