package com.zwhzzz.Controller;

import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import com.github.pagehelper.util.StringUtil;
import com.zwhzzz.Pojo.Menu;
import com.zwhzzz.Service.MenuService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.ModelAndView;

import javax.servlet.http.HttpServletRequest;
import java.io.File;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Controller
@RequestMapping("/admin/menu")
public class MenuController {


    private final MenuService menuService;

    public MenuController(MenuService menuService) {
        this.menuService = menuService;
    }

    /**
     * 返回菜单列表页
     * 转向视图
     * @param modelAndView
     * @return
     */
    @RequestMapping("/list")
    public ModelAndView list (ModelAndView modelAndView) {
        modelAndView.addObject("topList",menuService.findTopList());
        modelAndView.setViewName("menu/list");
        return modelAndView;
    }


    /**
     * 添加菜单
     * @param menu
     * @return
     */
    @RequestMapping(value = "/add",method = RequestMethod.POST)
    @ResponseBody
    public Map<String,String> add(Menu menu) {
        Map<String,String> result = new HashMap<>(0);
        if(menu == null) {
            result.put("type","error");
            result.put("msg","请填写正确的菜单信息");
            return result;
        }
        if(StringUtil.isEmpty(menu.getName())) {
            result.put("type","error");
            result.put("msg","请填写正确的菜单名称！");
            return result;
        }
        if(StringUtil.isEmpty(menu.getIcon())) {
            result.put("type","error");
            result.put("msg","请填写正确的菜单图标！");
            return result;
        }
        if(menu.getParentid() == null) {
            menu.setParentid(01);
        }

        if(menuService.insertMenu(menu) <= 0) {
            result.put("type","error");
            result.put("msg","添加失败，请联系管理员！");
            return result;
        }
        menuService.insertMenu(menu);
        result.put("type","success");
        result.put("msg","添加成功！");
        return result;
    }

    /**
     * 更新菜单-
     * @param menu
     * @return
     */
    @RequestMapping(value = "/edit",method = RequestMethod.POST)
    @ResponseBody
    public Map<String,String> updateMenu(Menu menu) {
        Map<String,String> result = new HashMap<>(0);
        if(StringUtil.isEmpty(menu.getName())) {
            result.put("type","error");
            result.put("msg","请填写正确的菜单名称！");
            return result;
        }
        if(StringUtil.isEmpty(menu.getIcon())) {
            result.put("type","error");
            result.put("msg","请填写正确的菜单图标！");
            return result;
        }
        if(menu.getParentid() == null) {
            menu.setParentid(01);
        }
        menuService.updateMenu(menu);
        result.put("type","success");
        result.put("msg","添加成功！");
        return result;
    }

    /**
     * 获取菜单
     * @param name
     * @param rows
     * @return
     */
    @RequestMapping(value = "/list",method = RequestMethod.POST)
    @ResponseBody
    public Map<String,Object> getMenuList(@RequestParam(value = "name",required = false,defaultValue = "")String name,
                                          Integer rows, Integer page/*Page page*/) {
        Map<String,Object> result = new HashMap<>(0);
        //把搜得的结果封装成List对象
        List<Menu> menu_list = menuService.get_Menu_List(name);
        //PageHelper插件
        PageHelper.startPage(page,rows);
        PageInfo<Menu> pageInfo = new PageInfo<>(menu_list);
        result.put("rows",pageInfo.getList());
        result.put("total",pageInfo.getTotal());
        return result;
    }


    /**
     * 删除信息
     * @param id
     * @return
     */

    @RequestMapping(value = "/delete",method = RequestMethod.POST)
    @ResponseBody
    public Map<String,Object> deleteMenu(@RequestParam(name = "id",required = true) Integer id){
        Map<String,Object> result = new HashMap<>(0);
        if(id == null){
            result.put("success",false);
            result.put("msg","信息不能为空！");
            return result;
        }
        //先找出子类，判断是否有子类
        List<Menu> childernList = menuService.findChildernList(id);
        if(childernList != null && childernList.size() != 0) {
            //表示该分类下存在子分类，不能删除
            result.put("success",false);
            result.put("msg","该分类下存在子分类，不能删除!");
            return result;
        }
        //删除失败
        /*if(menuService.deleteId(id) <= 0) {
            result.put("success",false);
            result.put("msg","删除失败！");
            return result;
        }*/
        menuService.deleteId(id);
        result.put("success",true);
        result.put("msg","删除成功");
        return result;
    }


    /**
     * 获取图标
     * @return
     */
        @RequestMapping("/get_icons")
        @ResponseBody
        public Map<String,Object> getIconList (HttpServletRequest request) {
            Map<String,Object> result = new HashMap<>(0);
            String realPath = request.getServletContext().getRealPath("/");//获取根目录
            File file = new File(realPath + "\\WEB-INF\\statics\\easyui\\css\\icons");
            List<String> icons = new ArrayList<>(0);
            if(! file.exists()) {
                result.put("success",false);
                result.put("msg","文件不存在!");
                return result;
            }
            //获取目录中的文件
            File[] files = file.listFiles();
            for(File f : files) {
                //System.out.println(f.getName());
                if(f != null && f.getName().contains("png")) {
                    icons.add("icon-" + f.getName().substring(0,f.getName().indexOf(".")).replace("_","-"));
                }
            }
            result.put("success",true);
            result.put("content",icons);
            return result;
    }


}
