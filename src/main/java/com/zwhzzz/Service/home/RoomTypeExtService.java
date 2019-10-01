package com.zwhzzz.Service.home;

import com.zwhzzz.DTO.roomTypeDTO;
import com.zwhzzz.Mapper.RoomTypeExtDao;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Map;

/**
 * @author alen zhong
 * @date 19-9-27
 */
@Service
public class RoomTypeExtService {

    private final RoomTypeExtDao roomTypeExtDao;


    public RoomTypeExtService(RoomTypeExtDao roomTypeExtDao) {
        this.roomTypeExtDao = roomTypeExtDao;
    }

    public List<roomTypeDTO> getCuList() {
        return roomTypeExtDao.getCuList();
    }

    public List<roomTypeDTO> getHomeListByName(Map<String, Object> queryMap) {
        List<roomTypeDTO> list = roomTypeExtDao.getList(queryMap);
        return list;
    }

    public roomTypeDTO CuListRoomType(Integer roomTypeId) {
        return roomTypeExtDao.CuListRoomType(roomTypeId);
    }
}
