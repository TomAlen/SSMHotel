package com.zwhzzz.Mapper;

import com.zwhzzz.DTO.BookOrderDTO;
import com.zwhzzz.Pojo.BookOrder;
import com.zwhzzz.Pojo.BookOrderExample;
import java.util.List;
import java.util.Map;

import org.apache.ibatis.annotations.Param;
import org.springframework.stereotype.Repository;


public interface BookOrderDao {
    long countByExample(BookOrderExample example);

    int deleteByExample(BookOrderExample example);

    int deleteByPrimaryKey(Integer id);

    int insert(BookOrder record);

    int insertSelective(BookOrder record);

    List<BookOrder> selectByExample(BookOrderExample example);

    BookOrder selectByPrimaryKey(Integer id);

    int updateByExampleSelective(@Param("record") BookOrder record, @Param("example") BookOrderExample example);

    int updateByExample(@Param("record") BookOrder record, @Param("example") BookOrderExample example);

    int updateByPrimaryKeySelective(BookOrder record);

    int updateByPrimaryKey(BookOrder record);

    List<BookOrder> getBookOrderList(BookOrderDTO bookOrderDTO);

    List<BookOrder> getList(Map<String,Object> queryMap);
}