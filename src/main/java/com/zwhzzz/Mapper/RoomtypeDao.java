package com.zwhzzz.Mapper;

import com.zwhzzz.Pojo.Roomtype;
import com.zwhzzz.Pojo.RoomtypeExample;
import java.util.List;
import java.util.Map;

import org.apache.ibatis.annotations.Param;
import org.springframework.stereotype.Repository;


public interface RoomtypeDao {
    long countByExample(RoomtypeExample example);

    int deleteByExample(RoomtypeExample example);

    int deleteByPrimaryKey(Integer id);

    int insert(Roomtype record);

    int insertSelective(Roomtype record);

    List<Roomtype> selectByExample(RoomtypeExample example);

    Roomtype selectByPrimaryKey(Integer id);

    int updateByExampleSelective(@Param("record") Roomtype record, @Param("example") RoomtypeExample example);

    int updateByExample(@Param("record") Roomtype record, @Param("example") RoomtypeExample example);

    int updateByPrimaryKeySelective(Roomtype record);

    int updateByPrimaryKey(Roomtype record);

    List<Roomtype> getRoomTypeList(Map<String,Object> queryMap);
}