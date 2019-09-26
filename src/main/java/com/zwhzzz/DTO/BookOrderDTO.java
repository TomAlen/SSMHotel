package com.zwhzzz.DTO;

import lombok.Data;

/**
 * @author alen zhong
 * @date 19-9-25
 */
@Data
public class BookOrderDTO {

    private String name;
    private String idCard;
    private String mobile;
    private Integer accountId;
    private Integer roomTypeId;
    private Integer status;

}
