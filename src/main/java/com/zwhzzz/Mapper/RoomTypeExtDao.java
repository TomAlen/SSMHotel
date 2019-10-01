package com.zwhzzz.Mapper;

import com.zwhzzz.DTO.roomTypeDTO;

import java.util.List;
import java.util.Map;

/**
 * @author alen zhong
 * @date 19-9-27
 */
public interface RoomTypeExtDao {

    List<roomTypeDTO> cuxiaoList(Map<String,Object> queryMap);
    roomTypeDTO CuListRoomType(Integer roomTypeId);
    //得到所有的房型
    List<roomTypeDTO> getList(Map<String,Object> queryMap);
    //将促销房型找出
    List<roomTypeDTO> getCuList();

}
