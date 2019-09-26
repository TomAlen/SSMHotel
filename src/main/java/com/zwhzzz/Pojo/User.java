package com.zwhzzz.Pojo;

import lombok.Data;

import java.io.Serializable;

/**
 * user
 * @author 
 */
@Data
public class User implements Serializable {
    private Integer id;

    private String username;

    private String password;

    private Integer role;

    private Integer gender;

    private Integer age;

    private String email;

    private String address;

    private String photo;

    private static final long serialVersionUID = 1L;


}