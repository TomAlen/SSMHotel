package com.zwhzzz.Util;

import com.zwhzzz.Pojo.Menu;

import java.util.ArrayList;
import java.util.List;

public class MenuUtil {

    //获取所有的顶级菜单
    public static List<Menu> getTopMenuList(List<Menu> menuList) {
        List<Menu> result = new ArrayList<>(0);
        //遍历菜单
        for(Menu menu : menuList) {
            //判断是否有顶级菜单
            if(menu.getParentid() == 0) {
                result.add(menu);
            }
        }
        return result;
    }

    /**
     * 获取二级菜单
     * @param menuList
     * @return
     */
    public static List<Menu> getSecondMenu(List<Menu> menuList) {
        List<Menu> result = new ArrayList<>(0);
        List<Menu> topMenuList = MenuUtil.getTopMenuList(menuList);
        //二重循环，如果传入菜单的id等于顶级菜单，就把该菜单返回
        for(Menu menus : menuList) {
            for (Menu topMennu : topMenuList) {
                if(menus.getParentid() == topMennu.getId()) {
                    result.add(menus);
                    break;
                }

            }
        }
        return result;
    }


    /**
     * 获取三级菜单
     * @param menuList
     * @param secondId 二级菜单id
     * @return
     */
    public static List<Menu> getThirdMenu(List<Menu> menuList,Integer secondId) {
        List<Menu> result = new ArrayList<>(0);
        for(Menu menu : menuList) {
            if(menu.getParentid() == secondId) {
                result.add(menu);
            }
        }
            return result;
    }

}
