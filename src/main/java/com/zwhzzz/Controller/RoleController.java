package com.zwhzzz.Controller;

import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import com.github.pagehelper.util.StringUtil;
import com.zwhzzz.Pojo.Authority;
import com.zwhzzz.Pojo.Menu;
import com.zwhzzz.Pojo.Role;
import com.zwhzzz.Service.AuthorityService;
import com.zwhzzz.Service.MenuService;
import com.zwhzzz.Service.RoleService;
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
@RequestMapping("/admin/role")
public class RoleController {

    @Autowired
    RoleService roleService;

    @Autowired
    MenuService menuService;

    @Autowired
    AuthorityService authorityService;

    //返回值传给前端显示消息
    public Map<String,Object> result = new HashMap<>(0);

    @RequestMapping(value = "/list",method = RequestMethod.GET)
    public ModelAndView list(ModelAndView model) {
        model.setViewName("/role/list");
        return model;
    }

    /**
     * 获取角色列表
     * @param name
     * @return
     */
    @RequestMapping(value = "/list",method = RequestMethod.POST)
    @ResponseBody
    public Map<String,Object> getList(
            @RequestParam(value = "name",required = false,defaultValue = "")String name,
            Integer rows,Integer page) {
        Map<String,Object> result = new HashMap<>(0);
        List<Role> list = roleService.get_Role_List(name);
        PageHelper.startPage(page,rows);
        //封装
        PageInfo<Role> pageInfo = new PageInfo<>(list);
        result.put("rows",pageInfo.getList());
        result.put("total",pageInfo.getTotal());
        return result;
    }


    /**
     * 添加角色
     * @param role
     * @return
     */
    @RequestMapping(value = "/add",method = RequestMethod.POST)
    @ResponseBody
    public Map<String,Object> insertRole(Role role) {
        if(role == null) {
            result.put("success",false);
            result.put("msg","角色信息为空！");
            return result;
        }
        if(StringUtil.isEmpty(role.getName())) {
            result.put("success",false);
            result.put("msg","请填写正确的角色名称！");
            return result;
        }

        if(roleService.insertRole(role) <= 0) {
            result.put("success",false);
            result.put("msg","添加失败，请联系管理员！");
            return result;
        }
        result.put("success",true);
        result.put("msg","添加成功！");
        return result;
    }


    /**
     * 更新角色信息
     * @param role
     * @return
     */
    @RequestMapping(value = "/edit",method = RequestMethod.POST)
    @ResponseBody
    public  Map<String,Object> updateRole(Role role) {
        if(StringUtil.isEmpty(role.getName())) {
            result.put("success",false);
            result.put("msg","请填写正确的角色名称！");
            return result;
        }

        if(roleService.updateRole(role) <= 0) {
            result.put("success",false);
            result.put("msg","更新失败，请联系管理员！");
            return result;
        }
        result.put("success",true);
        result.put("msg","添加成功！");
        return result;
    }


    /**
     * 删除角色信息
     * @param id
     * @return
     */
    @RequestMapping(value = "/delete",method = RequestMethod.POST)
    @ResponseBody
    public Map<String,Object> deleteRole(@RequestParam(value = "id",required = false) Integer id) {
        if(id == null) {
            result.put("success",false);
            result.put("msg","要删除的信息为空！");
            return result;
        }
        //删除失败
        if(roleService.deleteId(id) <= 0) {
            result.put("success",false);
            result.put("msg","删除失败！");
            return result;
        }
        result.put("success",true);
        result.put("msg","删除成功");
        return result;
    }


    @RequestMapping(value = "/get_icons")
    @ResponseBody
    public Map<String,Object> getIconList (HttpServletRequest request) {
        //得到根目录
        String realPath = request.getServletContext().getRealPath("/");
        File file = new File(realPath + "\\WEB-INF\\statics\\easyui\\css\\icons");
        List<String> icons = new ArrayList<>(0);
        if(! file.exists()) {
            result.put("success",false);
            result.put("msg","文件不存在！");
            return result;
        }
        //获取文件中的目录
        File[] files = file.listFiles();
        for (File f : files){
            if(f != null && f.getName().contains("png")){
                icons.add("-icon" + f.getName().substring(0,f.getName().indexOf(".")).replace("_","-"));
            }
        }
        result.put("success",true);
        result.put("content",icons);
        return result;
    }


    /**
     * 获取所有的菜单信息
     * @return
     */
    @RequestMapping(value = "/get_all_menu",method = RequestMethod.POST)
    @ResponseBody
    public List<Menu> getAllMenu() {
        Map<String,Object> queryMap = new HashMap<>(0);
        /*queryMap.put("offset",0);
        queryMap.put("pageSize",99999);
        */
        PageHelper.startPage(0,999);
        return menuService.getAllMenu();
    }


    /**
     * 添加权限
     * @param ids
     * @return
     */
    @RequestMapping("/add_authority")
    @ResponseBody
    public Map<String,Object> addAuthority (@RequestParam(value = "ids",required = true)String ids,
                                            @RequestParam(value = "roleId",required = true)Integer roleId
                                            ) {
        if(ids == null){
            result.put("success",false);
            result.put("msg","请选择相应的权限！");
            return result;
        }
        if(roleId == null) {
            result.put("success",false);
            result.put("msg","全选择相应的角色！");
            return result;
        }
        if(ids.contains(",")) {
            ids = ids.substring(0,ids.length()-1);
        }
        String[] idArray = ids.split(",");
        //如果有了，就清空权限
        if (idArray.length > 0) {
            authorityService.deleteByRoleId(roleId);
        }
        for (String id : idArray) {
            Authority authority = new Authority();
            authority.setMenuid(Integer.valueOf(id));
            authority.setRoleid(roleId);
            authorityService.add(authority);
        }
        result.put("success",true);
        result.put("msg","添加权限成功！");
        return result;
    }

    /**
     * 根据roleId搜得权限
     * @param roleId
     * @return
     */
    @RequestMapping(value = "/get_role_authority",method = RequestMethod.POST)
    @ResponseBody
    public List<Authority> getAuthority(
            @RequestParam(name = "roleId",required = true)Integer roleId
    ) {
            return authorityService.getListByRoleId(roleId);
    }

}
