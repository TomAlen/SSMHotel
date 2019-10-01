package com.zwhzzz.Service;

import com.zwhzzz.Mapper.RoomtypeDao;
import com.zwhzzz.Pojo.Roomtype;
import com.zwhzzz.Pojo.RoomtypeExample;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Map;

/**
 * @author alen zhong
 * @date 19-9-25
 */
@Service
public class RoomTypeService {

    private final RoomtypeDao roomtypeDao;

    public RoomTypeService(RoomtypeDao roomtypeDao) {
        this.roomtypeDao = roomtypeDao;
    }


    public List<Roomtype> getRoomTypeList(Map<String, Object> queryMap) {
        return roomtypeDao.getRoomTypeList(queryMap);
    }


    public Roomtype findRoomTypeByName(String roomtypeName) {

        RoomtypeExample roomtypeExample = new RoomtypeExample();
        roomtypeExample.createCriteria()
                .andNameEqualTo(roomtypeName);
        List<Roomtype> roomtypes = roomtypeDao.selectByExample(roomtypeExample);
        if(roomtypes.size() > 0) {
            return roomtypes.get(0);
        }
        return null;
    }


    public int insertRoomType(Roomtype roomtype) {
        return roomtypeDao.insert(roomtype);
    }

    public Roomtype findById(Integer id) {

        RoomtypeExample roomtypeExample = new RoomtypeExample();
        roomtypeExample.createCriteria()
                .andIdEqualTo(id);
        List<Roomtype> roomtypeList = roomtypeDao.selectByExample(roomtypeExample);
        if(roomtypeList.size() > 0) {
            return roomtypeList.get(0);
        }
        return null;
    }

    public int updateRoomType(Roomtype roomtype) {
        return roomtypeDao.updateByPrimaryKeySelective(roomtype);
    }

    public int deleteRoomType(Integer id) {
        return roomtypeDao.deleteByPrimaryKey(id);
    }

    public List<Roomtype> getList() {
        return roomtypeDao.selectByExample(new RoomtypeExample());
    }


    public int updateNum(Roomtype roomType) {
        return roomtypeDao.updateByPrimaryKeySelective(roomType);
    }

    public void updateStatus(Roomtype roomtype) {
        roomtypeDao.updateByPrimaryKeySelective(roomtype);
    }

    public List<Roomtype> getHomeListByName(String name) {
        name = "%" + name + "%";
        RoomtypeExample example = new RoomtypeExample();
        example.createCriteria()
                .andNameLike(name);
        List<Roomtype> roomtypes = roomtypeDao.selectByExample(example);
        return roomtypes;
    }
}
