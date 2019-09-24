package com.zwhzzz.Pojo;

import org.springframework.stereotype.Component;

import java.io.Serializable;
import java.util.Date;

/**
 * book_order
 * @author 
 */
@Component
public class BookOrder implements Serializable {
    private Integer id;

    private Integer accountid;

    private Integer roomtypeid;

    private String name;

    private String realname;

    private String idcard;

    private String mobile;

    private Integer status;

    private String arrivetime;

    private String leavetime;

    private Date createtime;

    private String remark;

    private Float price;

    private Integer custatus;

    private static final long serialVersionUID = 1L;

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public Integer getAccountid() {
        return accountid;
    }

    public void setAccountid(Integer accountid) {
        this.accountid = accountid;
    }

    public Integer getRoomtypeid() {
        return roomtypeid;
    }

    public void setRoomtypeid(Integer roomtypeid) {
        this.roomtypeid = roomtypeid;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getRealname() {
        return realname;
    }

    public void setRealname(String realname) {
        this.realname = realname;
    }

    public String getIdcard() {
        return idcard;
    }

    public void setIdcard(String idcard) {
        this.idcard = idcard;
    }

    public String getMobile() {
        return mobile;
    }

    public void setMobile(String mobile) {
        this.mobile = mobile;
    }

    public Integer getStatus() {
        return status;
    }

    public void setStatus(Integer status) {
        this.status = status;
    }

    public String getArrivetime() {
        return arrivetime;
    }

    public void setArrivetime(String arrivetime) {
        this.arrivetime = arrivetime;
    }

    public String getLeavetime() {
        return leavetime;
    }

    public void setLeavetime(String leavetime) {
        this.leavetime = leavetime;
    }

    public Date getCreatetime() {
        return createtime;
    }

    public void setCreatetime(Date createtime) {
        this.createtime = createtime;
    }

    public String getRemark() {
        return remark;
    }

    public void setRemark(String remark) {
        this.remark = remark;
    }

    public Float getPrice() {
        return price;
    }

    public void setPrice(Float price) {
        this.price = price;
    }

    public Integer getCustatus() {
        return custatus;
    }

    public void setCustatus(Integer custatus) {
        this.custatus = custatus;
    }

    @Override
    public boolean equals(Object that) {
        if (this == that) {
            return true;
        }
        if (that == null) {
            return false;
        }
        if (getClass() != that.getClass()) {
            return false;
        }
        BookOrder other = (BookOrder) that;
        return (this.getId() == null ? other.getId() == null : this.getId().equals(other.getId()))
            && (this.getAccountid() == null ? other.getAccountid() == null : this.getAccountid().equals(other.getAccountid()))
            && (this.getRoomtypeid() == null ? other.getRoomtypeid() == null : this.getRoomtypeid().equals(other.getRoomtypeid()))
            && (this.getName() == null ? other.getName() == null : this.getName().equals(other.getName()))
            && (this.getRealname() == null ? other.getRealname() == null : this.getRealname().equals(other.getRealname()))
            && (this.getIdcard() == null ? other.getIdcard() == null : this.getIdcard().equals(other.getIdcard()))
            && (this.getMobile() == null ? other.getMobile() == null : this.getMobile().equals(other.getMobile()))
            && (this.getStatus() == null ? other.getStatus() == null : this.getStatus().equals(other.getStatus()))
            && (this.getArrivetime() == null ? other.getArrivetime() == null : this.getArrivetime().equals(other.getArrivetime()))
            && (this.getLeavetime() == null ? other.getLeavetime() == null : this.getLeavetime().equals(other.getLeavetime()))
            && (this.getCreatetime() == null ? other.getCreatetime() == null : this.getCreatetime().equals(other.getCreatetime()))
            && (this.getRemark() == null ? other.getRemark() == null : this.getRemark().equals(other.getRemark()))
            && (this.getPrice() == null ? other.getPrice() == null : this.getPrice().equals(other.getPrice()))
            && (this.getCustatus() == null ? other.getCustatus() == null : this.getCustatus().equals(other.getCustatus()));
    }

    @Override
    public int hashCode() {
        final int prime = 31;
        int result = 1;
        result = prime * result + ((getId() == null) ? 0 : getId().hashCode());
        result = prime * result + ((getAccountid() == null) ? 0 : getAccountid().hashCode());
        result = prime * result + ((getRoomtypeid() == null) ? 0 : getRoomtypeid().hashCode());
        result = prime * result + ((getName() == null) ? 0 : getName().hashCode());
        result = prime * result + ((getRealname() == null) ? 0 : getRealname().hashCode());
        result = prime * result + ((getIdcard() == null) ? 0 : getIdcard().hashCode());
        result = prime * result + ((getMobile() == null) ? 0 : getMobile().hashCode());
        result = prime * result + ((getStatus() == null) ? 0 : getStatus().hashCode());
        result = prime * result + ((getArrivetime() == null) ? 0 : getArrivetime().hashCode());
        result = prime * result + ((getLeavetime() == null) ? 0 : getLeavetime().hashCode());
        result = prime * result + ((getCreatetime() == null) ? 0 : getCreatetime().hashCode());
        result = prime * result + ((getRemark() == null) ? 0 : getRemark().hashCode());
        result = prime * result + ((getPrice() == null) ? 0 : getPrice().hashCode());
        result = prime * result + ((getCustatus() == null) ? 0 : getCustatus().hashCode());
        return result;
    }

    @Override
    public String toString() {
        StringBuilder sb = new StringBuilder();
        sb.append(getClass().getSimpleName());
        sb.append(" [");
        sb.append("Hash = ").append(hashCode());
        sb.append(", id=").append(id);
        sb.append(", accountid=").append(accountid);
        sb.append(", roomtypeid=").append(roomtypeid);
        sb.append(", name=").append(name);
        sb.append(", realname=").append(realname);
        sb.append(", idcard=").append(idcard);
        sb.append(", mobile=").append(mobile);
        sb.append(", status=").append(status);
        sb.append(", arrivetime=").append(arrivetime);
        sb.append(", leavetime=").append(leavetime);
        sb.append(", createtime=").append(createtime);
        sb.append(", remark=").append(remark);
        sb.append(", price=").append(price);
        sb.append(", custatus=").append(custatus);
        sb.append(", serialVersionUID=").append(serialVersionUID);
        sb.append("]");
        return sb.toString();
    }
}