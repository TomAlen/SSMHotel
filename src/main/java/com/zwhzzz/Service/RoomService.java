package com.zwhzzz.Service;

import com.zwhzzz.Mapper.RoomDao;
import com.zwhzzz.Pojo.Room;
import com.zwhzzz.Pojo.RoomExample;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Map;

/**
 * @author alen zhong
 * @date 19-9-25
 */
@Service
public class RoomService {

    private final RoomDao roomDao;

    public RoomService(RoomDao roomDao) {
        this.roomDao = roomDao;
    }


    public List<Room> getRoomList(Map<String, Object> queryMap) {
        return roomDao.getRoomList(queryMap);
    }


    //根据编号查出房间
    public Room getRoomBySn(String sn) {
        RoomExample example = new RoomExample();
        example.createCriteria()
                .andSnEqualTo(sn);
        List<Room> rooms = roomDao.selectByExample(example);
        if(rooms.size() > 0) {
            return rooms.get(0);
        }
        return null;
    }

    public int insertRoom(Room room) {
        return roomDao.insertSelective(room);
    }

    public int updateRoom(Room room) {
        return roomDao.updateByPrimaryKeySelective(room);
    }

    public int deleteRoom(Integer id) {
        return roomDao.deleteByPrimaryKey(id);
    }

    public List<Room> getRoomList() {
        return roomDao.selectByExample(new RoomExample());
    }

    public Room getRoomById(Integer id) {
        RoomExample roomExample = new RoomExample();
        roomExample.createCriteria()
                .andIdEqualTo(id);
        List<Room> rooms = roomDao.selectByExample(roomExample);
        if(rooms.size() > 0) {
            return rooms.get(0);
        }
        return null;
    }
}
