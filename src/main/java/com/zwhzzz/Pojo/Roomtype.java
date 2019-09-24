package com.zwhzzz.Pojo;

import org.springframework.stereotype.Component;

import java.io.Serializable;

/**
 * roomtype
 * @author 
 */
@Component
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

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getPhoto() {
        return photo;
    }

    public void setPhoto(String photo) {
        this.photo = photo;
    }

    public Float getPrice() {
        return price;
    }

    public void setPrice(Float price) {
        this.price = price;
    }

    public Integer getLivenum() {
        return livenum;
    }

    public void setLivenum(Integer livenum) {
        this.livenum = livenum;
    }

    public Integer getBednum() {
        return bednum;
    }

    public void setBednum(Integer bednum) {
        this.bednum = bednum;
    }

    public Integer getRoomnum() {
        return roomnum;
    }

    public void setRoomnum(Integer roomnum) {
        this.roomnum = roomnum;
    }

    public Integer getAvilablenum() {
        return avilablenum;
    }

    public void setAvilablenum(Integer avilablenum) {
        this.avilablenum = avilablenum;
    }

    public Integer getBooknum() {
        return booknum;
    }

    public void setBooknum(Integer booknum) {
        this.booknum = booknum;
    }

    public Integer getLivednum() {
        return livednum;
    }

    public void setLivednum(Integer livednum) {
        this.livednum = livednum;
    }

    public Integer getStatus() {
        return status;
    }

    public void setStatus(Integer status) {
        this.status = status;
    }

    public String getRemark() {
        return remark;
    }

    public void setRemark(String remark) {
        this.remark = remark;
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
        Roomtype other = (Roomtype) that;
        return (this.getId() == null ? other.getId() == null : this.getId().equals(other.getId()))
            && (this.getName() == null ? other.getName() == null : this.getName().equals(other.getName()))
            && (this.getPhoto() == null ? other.getPhoto() == null : this.getPhoto().equals(other.getPhoto()))
            && (this.getPrice() == null ? other.getPrice() == null : this.getPrice().equals(other.getPrice()))
            && (this.getLivenum() == null ? other.getLivenum() == null : this.getLivenum().equals(other.getLivenum()))
            && (this.getBednum() == null ? other.getBednum() == null : this.getBednum().equals(other.getBednum()))
            && (this.getRoomnum() == null ? other.getRoomnum() == null : this.getRoomnum().equals(other.getRoomnum()))
            && (this.getAvilablenum() == null ? other.getAvilablenum() == null : this.getAvilablenum().equals(other.getAvilablenum()))
            && (this.getBooknum() == null ? other.getBooknum() == null : this.getBooknum().equals(other.getBooknum()))
            && (this.getLivednum() == null ? other.getLivednum() == null : this.getLivednum().equals(other.getLivednum()))
            && (this.getStatus() == null ? other.getStatus() == null : this.getStatus().equals(other.getStatus()))
            && (this.getRemark() == null ? other.getRemark() == null : this.getRemark().equals(other.getRemark()))
            && (this.getCustatus() == null ? other.getCustatus() == null : this.getCustatus().equals(other.getCustatus()));
    }

    @Override
    public int hashCode() {
        final int prime = 31;
        int result = 1;
        result = prime * result + ((getId() == null) ? 0 : getId().hashCode());
        result = prime * result + ((getName() == null) ? 0 : getName().hashCode());
        result = prime * result + ((getPhoto() == null) ? 0 : getPhoto().hashCode());
        result = prime * result + ((getPrice() == null) ? 0 : getPrice().hashCode());
        result = prime * result + ((getLivenum() == null) ? 0 : getLivenum().hashCode());
        result = prime * result + ((getBednum() == null) ? 0 : getBednum().hashCode());
        result = prime * result + ((getRoomnum() == null) ? 0 : getRoomnum().hashCode());
        result = prime * result + ((getAvilablenum() == null) ? 0 : getAvilablenum().hashCode());
        result = prime * result + ((getBooknum() == null) ? 0 : getBooknum().hashCode());
        result = prime * result + ((getLivednum() == null) ? 0 : getLivednum().hashCode());
        result = prime * result + ((getStatus() == null) ? 0 : getStatus().hashCode());
        result = prime * result + ((getRemark() == null) ? 0 : getRemark().hashCode());
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
        sb.append(", name=").append(name);
        sb.append(", photo=").append(photo);
        sb.append(", price=").append(price);
        sb.append(", livenum=").append(livenum);
        sb.append(", bednum=").append(bednum);
        sb.append(", roomnum=").append(roomnum);
        sb.append(", avilablenum=").append(avilablenum);
        sb.append(", booknum=").append(booknum);
        sb.append(", livednum=").append(livednum);
        sb.append(", status=").append(status);
        sb.append(", remark=").append(remark);
        sb.append(", custatus=").append(custatus);
        sb.append(", serialVersionUID=").append(serialVersionUID);
        sb.append("]");
        return sb.toString();
    }
}