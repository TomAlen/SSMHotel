package com.zwhzzz.Mapper;

import com.zwhzzz.Pojo.Menu;
import com.zwhzzz.Pojo.MenuExample;
import java.util.List;
import java.util.Map;

import org.apache.ibatis.annotations.Param;

public interface MenuDao {
    long countByExample(MenuExample example);

    int deleteByExample(MenuExample example);

    int deleteByPrimaryKey(Integer id);

    int insert(Menu record);

    int insertSelective(Menu record);

    List<Menu> selectByExample(MenuExample example);

    Menu selectByPrimaryKey(Integer id);

    int updateByExampleSelective(@Param("record") Menu record, @Param("example") MenuExample example);

    int updateByExample(@Param("record") Menu record, @Param("example") MenuExample example);

    int updateByPrimaryKeySelective(Menu record);

    int updateByPrimaryKey(Menu record);

    //根据id获取菜单
    List<Menu> findByMenuList(String ids) ;

    List<Menu> findTopList();

    List<Menu> findChildernList(Integer parnetId);

    List<Menu> get_Menu_List(Map<String,Object> queryMap);

    int findParentById(Integer id);



}