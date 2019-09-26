package com.zwhzzz.Mapper;

import com.zwhzzz.Pojo.Checkinn;
import com.zwhzzz.Pojo.CheckinnExample;
import java.util.List;
import java.util.Map;

import org.apache.ibatis.annotations.Param;
import org.springframework.stereotype.Repository;


public interface CheckinnDao {
    long countByExample(CheckinnExample example);

    int deleteByExample(CheckinnExample example);

    int deleteByPrimaryKey(Integer id);

    int insert(Checkinn record);

    int insertSelective(Checkinn record);

    List<Checkinn> selectByExample(CheckinnExample example);

    Checkinn selectByPrimaryKey(Integer id);

    int updateByExampleSelective(@Param("record") Checkinn record, @Param("example") CheckinnExample example);

    int updateByExample(@Param("record") Checkinn record, @Param("example") CheckinnExample example);

    int updateByPrimaryKeySelective(Checkinn record);

    int updateByPrimaryKey(Checkinn record);

    List<Checkinn> getList(Map<String,Object> queryMap);
}