package com.zwhzzz.DTO;

import lombok.Data;

/**
 * @author alen zhong
 * @date 19-9-19
 * 房型的数据传输模型
 */
@Data
public class roomTypeDTO {
    private Integer id;
    private String name;//房型名称
    private String photo;//图片
    private Double price;//价格
    private Integer livenum;//可住人数
    private Integer bednum;//床位数
    private Integer roomnum;//房间数
    private Integer avilablenum;//可住或可预订数
    private Integer booknum;//已预定数
    private Integer livednum;//已入住人数
    private int status;//房型状态，0：房型已满，1：可预订入住
    private String remark;//备注
    private Double prePrice;//记录打折前的价格
    private int Custatus;//促销标识
    private Double LaterPrice;//促销后价格
    private int CuXiao;//打几折
}
