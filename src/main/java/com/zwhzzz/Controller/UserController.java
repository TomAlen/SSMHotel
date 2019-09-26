package com.zwhzzz.Controller;

import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import com.github.pagehelper.util.StringUtil;
import com.zwhzzz.Pojo.User;
import com.zwhzzz.Service.RoleService;
import com.zwhzzz.Service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.servlet.ModelAndView;

import javax.servlet.http.HttpServletRequest;
import java.io.File;
import java.io.IOException;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * @author alen zhong
 * @date 19-9-24
 *
 *
 * 后台用户管理员控制器
 */
@Controller
@RequestMapping("/admin/user")
public class UserController {


    private final UserService userService;

    private final RoleService roleService;

    public UserController(UserService userService, RoleService roleService) {
        this.userService = userService;
        this.roleService = roleService;
    }


    @RequestMapping(value = "/list",method = RequestMethod.GET)
    public ModelAndView getUserList(ModelAndView model) {
        model.addObject("roleList",roleService.selectAll());
        model.setViewName("/user/list");
        return model;
    }

    /**
     * 获取所有的用户信息
     * @param username
     * @param role
     * @param gender
     * @param page
     * @return
     */
    @RequestMapping(value = "/list",method = RequestMethod.POST)
    @ResponseBody
    public Map<String,Object> getUserList(@RequestParam(value = "username",required = false,defaultValue = "")String username,
                                          @RequestParam(value = "role",required = false)Integer role,
                                          @RequestParam(value = "gender",required = false)Integer gender,
                                          Integer page,Integer rows
    ) {
        Map<String,Object> result = new HashMap<>(0);
        Map<String, Object> queryMap = new HashMap<>();
        queryMap.put("username",username);
        queryMap.put("role",role);
        queryMap.put("gender",gender);
        PageHelper.startPage(page,rows);
        List<User> userList = userService.findUserList(queryMap);
        PageInfo<User> userPageInfo = new PageInfo<>(userList);
        result.put("rows",userPageInfo.getList());
        result.put("total",userPageInfo.getTotal());
        return result;
    }


    /**
     * 添加用户信息
     * @param user
     * @return
     */
    @RequestMapping(value = "/add",method = RequestMethod.POST)
    @ResponseBody
    public Map<String,Object> insertUser(User user) {
        Map<String,Object> result = new HashMap<>(0);
        if(user == null) {
            result.put("success",false);
            result.put("msg","用户信息为空！");
            return result;
        }
        User byUsername = userService.findByUsername(user.getUsername());
        if(byUsername != null) {
            result.put("success",false);
            result.put("msg","用户名已存在！");
            return result;
        }
        if (userService.insertUser(user) > 0) {
            result.put("success",false);
            result.put("msg","添加失败！");
            return result;
        }
        result.put("success",true);
        return result;
    }

    /**
     * 更新用户信息
     * @param user
     * @return
     */
    @RequestMapping(value = "/edit",method = RequestMethod.POST)
    @ResponseBody
    public Map<String,Object> updateUser(User user) {
        Map<String,Object> result = new HashMap<>(0);
        if(user == null) {
            result.put("success",false);
            result.put("msg","更新的信息不能为空！");
            return result;
        }
        if(StringUtil.isEmpty(user.getUsername())) {
            result.put("success",false);
            result.put("msg","用户名不能为空！");
            return result;
        }

        if(userService.updateUser(user) >= 0) {
            result.put("success",false);
            result.put("msg","更新用户失败！");
            return result;
        }
        result.put("success",true);
        return result;
    }
    /**
     * 删除用户信息
     * @param ids
     * @return
     */
    @RequestMapping(value = "/delete",method = RequestMethod.POST)
    @ResponseBody
    public Map<String,Object> deleteUser(String ids) {
        Map<String,Object> result = new HashMap<>(0);
        if(StringUtil.isEmpty(ids)) {
            result.put("success",false);
            result.put("msg","删除的信息不能为空！");
            return result;
        }
        if(ids.contains(",")) {
            ids = ids.substring(0,ids.length()-1);
        }
        if(userService.deleteUser(ids) >= 0) {
            result.put("success",false);
            result.put("msg","删除用户失败！");
            return result;
        }
        result.put("success",true);
        return result;
    }

    /**
     * 判断该用户名是否存在
     * @return
     */
    public boolean isExist(String username,Integer id) {
        User byUsername = userService.findByUsername(username);
        if (byUsername == null)return false;//说明用户名不存在
        if(byUsername.getId().intValue() == id.intValue()) return false;
        return  true;
    }

    /**
     * 上传图片
     * @param photo
     * @param request
     * @return
     * @throws IOException
     */
    @RequestMapping(value = "/upload_photo",method = RequestMethod.POST)
    @ResponseBody
    public Map<String,Object> uploadPhoto(MultipartFile photo, HttpServletRequest request) throws IOException {
        Map<String,Object> result = new HashMap<>(0);
        if(photo == null ) {
            result.put("success",false);
            result.put("msg","请选择要上传的文件！");
            return result;
        }
        //10Mb
        if(photo.getSize() == 1021 * 1024 * 1024) {
            result.put("success",false);
            result.put("msg","文件不能超过10M");
            return result;
        }
        //截取后缀：eg：1236.jpg --> 截取jpg
        String suffix = photo.getOriginalFilename()
                .substring(photo.getOriginalFilename().lastIndexOf(".") + 1,photo.getOriginalFilename().length());
        //判断后缀是否为以下类型
        if(! "jpg,jpeg,gif,png".toUpperCase().contains(suffix.toUpperCase())) {
            result.put("success",false);
            result.put("msg","文件类型必须为jpg,jpeg,gif,png！");
            return result;
        }
        String savePath = request.getServletContext().getRealPath("/") + "/upload/";
        File savePathFile = new File(savePath);
        //如果文件不存在就创建文件目录
        if(!savePathFile.exists()) {
            savePathFile.mkdir();
        }
        //将文件存入路径中
        String fileName = new Date().getTime() + "." + suffix;
        photo.transferTo(new File(savePath  + fileName));
        result.put("success",true);
        result.put("filepath",request.getServletContext().getContextPath()  + "/upload/" + fileName);
        return result;
    }



}
