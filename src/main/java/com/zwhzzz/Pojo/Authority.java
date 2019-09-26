package com.zwhzzz.Pojo;

import lombok.Data;

import java.io.Serializable;

/**
 * authority
 * @author 
 */
@Data
public class Authority implements Serializable {
    private Integer id;

    private Integer roleid;

    private Integer menuid;

    private static final long serialVersionUID = 1L;

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public Integer getRoleid() {
        return roleid;
    }

    public void setRoleid(Integer roleid) {
        this.roleid = roleid;
    }

    public Integer getMenuid() {
        return menuid;
    }

    public void setMenuid(Integer menuid) {
        this.menuid = menuid;
    }


}