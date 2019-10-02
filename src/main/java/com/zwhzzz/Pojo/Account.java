package com.zwhzzz.Pojo;

import lombok.Data;
import org.springframework.stereotype.Component;

import java.io.Serializable;

/**
 * account
 * @author 
 */
@Data
public class Account implements Serializable {
    private Integer id;

    private String photo;

    private String name;

    private String password;

    private String realname;

    private String idcard;

    private String mobile;

    private String address;

    private Integer status;

    private String laterMobile;

    private String laterRealName;


    private static final long serialVersionUID = 1L;


}