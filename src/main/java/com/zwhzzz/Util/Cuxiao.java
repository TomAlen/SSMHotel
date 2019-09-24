package com.zwhzzz.Util;

import com.zwhzzz.DTO.roomTypeDTO;

/**
 * @author alen zhong
 * @date 19-9-21
 */
public class  Cuxiao {

    public static void CuxiaoPrice(roomTypeDTO roomTypeDTO){
        //id为4 8 -- > 75折
        if(roomTypeDTO.getId() == 4 || roomTypeDTO.getId() == 8) {
            roomTypeDTO.setLaterPrice((double)Math.round(roomTypeDTO.getPrice() * 0.75));
            roomTypeDTO.setCuXiao(75);
            roomTypeDTO.setCuStatus(1);//1代表促销
        }
        //id为5,12 8 --> 8折
        if(roomTypeDTO.getId() == 5 || roomTypeDTO.getId() == 12) {
            roomTypeDTO.setLaterPrice((double)Math.round(roomTypeDTO.getPrice() * 0.8));
            roomTypeDTO.setCuXiao(8);
            roomTypeDTO.setCuStatus(1);
        }
        //id 为 7,10,14 --> 85折
        if(roomTypeDTO.getId() == 7 || roomTypeDTO.getId() == 10 || roomTypeDTO.getId() == 14) {
            roomTypeDTO.setLaterPrice((double)Math.round(roomTypeDTO.getPrice() * 0.85));
            roomTypeDTO.setCuXiao(85);
            roomTypeDTO.setCuStatus(1);
        }
    }

}
