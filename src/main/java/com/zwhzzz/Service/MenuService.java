package com.zwhzzz.Service;

import com.zwhzzz.Mapper.MenuDao;
import com.zwhzzz.Pojo.Menu;
import com.zwhzzz.Pojo.MenuExample;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Map;

/**
 * @author alen zhong
 * @date 19-9-23
 */
@Service
public class MenuService {

    private final MenuDao menuDao;

    public MenuService(MenuDao menuDao) {
        this.menuDao = menuDao;
    }

    //获取菜单信息
    public List<Menu> findByMenuList(String menuIds) {
        List<Menu> byMenuList = menuDao.findByMenuList(menuIds);
        return byMenuList;
    }

    //获取顶级菜单信息
    public List<Menu> findTopList() {
        return menuDao.findTopList();
    }

    //插入菜单操作
    public int insertMenu(Menu menu) {
        return menuDao.insert(menu);
    }

    //更新操作
    public int updateMenu(Menu menu) {
        return menuDao.updateByPrimaryKey(menu);
    }

    //获取子菜单
    public List<Menu> findChildernList(Integer id) {
        return menuDao.findChildernList(id);
    }

    //删除菜单
    public int deleteId(Integer id) {
        return menuDao.deleteByPrimaryKey(id);
    }

    //根据模糊查询获取菜单信息
    public List<Menu> get_Menu_List(String name) {
        name = "%" + name + "%";
        MenuExample menuExample = new MenuExample();
        menuExample.createCriteria()
                .andNameLike(name);
        List<Menu> menus = menuDao.selectByExample(menuExample);
        return menus;
    }

    public List<Menu> getAllMenu() {
        return menuDao.selectByExample(new MenuExample());
    }




}
