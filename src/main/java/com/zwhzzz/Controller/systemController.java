package com.zwhzzz.Controller;

import com.github.pagehelper.util.StringUtil;
import com.zwhzzz.Pojo.Authority;
import com.zwhzzz.Pojo.Menu;
import com.zwhzzz.Pojo.Role;
import com.zwhzzz.Pojo.User;
import com.zwhzzz.Service.*;
import com.zwhzzz.Util.CpachaUtil;
import com.zwhzzz.Util.MenuUtil;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.ModelAndView;

import javax.imageio.ImageIO;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.awt.image.BufferedImage;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Controller
@RequestMapping("/system")
public class systemController {

    private final UserService userService;

    private final LogService logService;

    private final RoleService roleService;

    private final AuthorityService authorityService;

    private final MenuService menuService;

    public Map<String,Object> result = new HashMap<>(0);

    public systemController(UserService userService, LogService logService, RoleService roleService, AuthorityService authorityService, MenuService menuService) {
        this.userService = userService;
        this.logService = logService;
        this.roleService = roleService;
        this.authorityService = authorityService;
        this.menuService = menuService;
    }

    /**
     * 登录
     * @param model
     * @return
     */
    @RequestMapping(value = "/login",method = RequestMethod.GET)
    public ModelAndView login(ModelAndView model) {
        model.setViewName("system/login");
        return model;
    }

    /**
     * 登录页面
     * @param cpacha
     * @param request
     * @return
     */
    @RequestMapping(value = "/login",method = RequestMethod.POST)
    @ResponseBody
    public Map<String,Object> loginActice (User user, String cpacha, HttpServletRequest request) {
        if(user == null) {
            result.put("success",false);
            result.put("msg","请填写用户信息！");
            return result;
        }
        Object loginCpacha = request.getSession().getAttribute("loginCpacha");
        if(loginCpacha == null){
            result.put("success",false);
            result.put("msg","会话超时，请重新输入验证码！");
            logService.insertContent("用户"+user.getUsername()+"会话超时，重新输入验证码！");
            return result;
        }
        if(! cpacha.toUpperCase().equals(loginCpacha.toString().toUpperCase())) {
            result.put("success",false);
            result.put("msg","验证码错误！");
            logService.insertContent("用户"+user.getUsername()+"输入验证码错误！");
            return result;
        }
        User Byuser = userService.findByusername(user.getUsername());
        if(Byuser == null) {
            result.put("success",false);
            result.put("msg","该用户名不存在！");
            logService.insertContent("用户"+user.getUsername()+"登录时，该用户不存在！");
            return result;
        }
        //判断传入的密码与数据库中的密码是否一致
        if(! Byuser.getPassword().equals(user.getPassword())) {
            result.put("success",false);
            result.put("msg","密码错误！");
            logService.insertContent("用户"+user.getUsername()+"登录时，密码错误！");
            return result;
        }
        Role role = roleService.findById(Byuser.getRole());
        //根据角色id获取权限列表
        List<Authority> authorities = authorityService.getListByRoleId(role.getId());
        //将menuId取出
        String menuIds = "";
        for(Authority authority : authorities) {
            menuIds += authority.getMenuid() + ",";
        }
        //如果menuIds不为空，就去掉左后一个逗号
        if(!StringUtil.isEmpty(menuIds)) {
            menuIds = menuIds.substring(0,menuIds.length()-1);
        }
        //获取了所有的用户菜单信息
        List<Menu> menuList = menuService.findByMenuList(menuIds);
        //存入session中
        request.getSession().setAttribute("admin",Byuser);
        request.getSession().setAttribute("role",role);
        request.getSession().setAttribute("userMenus",menuList);
        result.put("success",true);
        result.put("msg","登录成功！");
        logService.insertContent(role.getName()+user.getUsername()+"登录成功！");
        return result;
    }

    /**
     * 登录成功跳转的页面
     * @param model
     * @return
     */
    @RequestMapping("/index")
    public ModelAndView goSuccess(ModelAndView model,HttpServletRequest request){
        List<Menu> userMenus = (List<Menu>) request.getSession().getAttribute("userMenus");
        //找出顶级菜单
        model.addObject("topMenuList", MenuUtil.getTopMenuList(userMenus));
        //获取二级菜单
        model.addObject("secondMenuList",MenuUtil.getSecondMenu(userMenus));
        model.setViewName("system/index");
        return model;
    }

    /**
     * 在主页中显示welcome页面
     * @param model
     * @return
     */
    @RequestMapping("/welcome")
    public ModelAndView goWelcome(ModelAndView model){
        model.setViewName("system/welcome");
        return model;
    }

    /**
     * 安全退出功能
     * @return
     */
    @RequestMapping(value = "/logout",method = RequestMethod.GET)
    public String logout(HttpSession session) {
        session.setAttribute("admin",null);
        session.setAttribute("role",null);
        session.setAttribute("topMenuList",null);
        session.setAttribute("secondMenuList",null);
        session.setAttribute("thirdMenu",null);
        return "redirect:/system/login";
    }

    /**
     * 跳转修改密码页面
     * @param model
     * @return
     */
    @RequestMapping(value = "/list",method = RequestMethod.GET)
    public ModelAndView getEditPassword(ModelAndView model) {
        model.setViewName("/system/edit_password");
        return model;
    }

    /**
     * 修改密码
     * @param newPassword
     * @param request
     * @return
     */
    @RequestMapping(value = "/edit_password",method = RequestMethod.POST)
    @ResponseBody
    public Map<String,Object> EditPassword(String oldPassword,String newPassword, HttpServletRequest request) {
        Map<String,Object> result = new HashMap<>(0);
        if(StringUtil.isEmpty(newPassword)) {
            result.put("success",false);
            result.put("msg","密码不能为空！");
            return  result;
        }
        User user = (User) request.getSession().getAttribute("admin");
        //判断输入的密码与密码是否一致
        if(!user.getPassword().equals(oldPassword)) {
            result.put("success",false);
            result.put("msg","原密码错误！");
            return result;
        }
        //设置密码
        user.setPassword(newPassword);
        if(userService.editPassword(user) > 0) {
            result.put("success",false);
            result.put("msg","修改密码错误！");
            return result;
        }
        result.put("success",true);
        //获取角色姓名
        Role role = (Role)request.getSession().getAttribute("role");
        logService.insertContent(role.getName()+":"+user.getUsername()+"修改密码成功！");
        return result;
    }
    /**
     *
     * @param vcodeLen  验证码长度
     * @param width     验证码宽度
     * @param height    验证码高度
     * @param cpacha    验证码的类型，默认为登录验证码
     * @param request   前端发送的请求
     * @param response  前端收到的回应
     */
    @RequestMapping("/get_cpacha")
    public void generateCpacha(
            @RequestParam(name = "vl",required = false,defaultValue = "4")Integer vcodeLen,
            @RequestParam(name = "w",required = false,defaultValue = "100")Integer width,
            @RequestParam(name = "h",required = false,defaultValue = "30")Integer height,
            @RequestParam(name= "type",required = false,defaultValue = "loginCpacha")String cpacha,
            HttpServletRequest request,
            HttpServletResponse response) {
        //实例化一个验证码的长、宽、高
        CpachaUtil cpachaUtil = new CpachaUtil(vcodeLen,width,height);
        //生成一个验证码
        String generatorVCode = cpachaUtil.generatorVCode();
        //设置一个会话值，设置一个值传给前端进行显示
        request.getSession().setAttribute("loginCpacha",generatorVCode);
        //画一个背景图
        BufferedImage bufferedImage = cpachaUtil.generatorRotateVCodeImage(generatorVCode, true);
        try {
            //传入一个流中
            ImageIO.write(bufferedImage, "gif", response.getOutputStream());
        }catch (Exception e) {
            e.printStackTrace();
        }
    }

}
