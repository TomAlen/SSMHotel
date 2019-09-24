package com.zwhzzz.DTO;

import lombok.Data;

import java.util.Date;

/**
 * @author alen zhong
 * @date 19-9-20
 */
@Data
public class bookOrderDTO {
    private Integer id;//订单id
    private Integer accountId;//客户id
    private Integer roomTypeId;//所属房型id
    private String name;//预定者姓名
    private String realName;//预定着真实姓名
    private String idCard;//预定着身份证号
    private String mobile;//手机号
    private int status;//状态 0：预定中  1：已入住   2：已结算离店
    private String arriveTime;//入住日期
    private String leaveTime;//离店日期
    private Date createTime;//预定日期
    private String remark;//备注
    private Float price;//价格
    private Double prePrice;//促销前价格
    private int cuStatus;//表示促销状态 //0 ：原价   1：促销状态

}
