package com.zwhzzz.Service;

import com.zwhzzz.Mapper.FloorDao;
import com.zwhzzz.Pojo.Floor;
import com.zwhzzz.Pojo.FloorExample;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * @author alen zhong
 * @date 19-9-24
 */
@Service
public class FloorService {

    private final FloorDao floorDao;

    public FloorService(FloorDao floorDao) {
        this.floorDao = floorDao;
    }


    public List<Floor> getList(String name) {
        name = "%" + name + "%";
        FloorExample floorExample = new FloorExample();
        floorExample.createCriteria()
                .andNameLike(name);
        return floorDao.selectByExample(floorExample);
    }

    public int insertFloor(Floor floor) {
        return floorDao.insert(floor);
    }

    public int updateFloor(Floor floor) {
        return floorDao.updateByPrimaryKey(floor);
    }

    public int deleteFloor(Integer id) {
        return floorDao.deleteByPrimaryKey(id);
    }

    public List<Floor> getListAll() {
        return floorDao.selectByExample(new FloorExample());
    }
}
