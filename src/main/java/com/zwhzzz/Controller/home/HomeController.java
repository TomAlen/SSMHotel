package com.zwhzzz.Controller.home;

import com.github.pagehelper.PageHelper;
import com.zwhzzz.DTO.roomTypeDTO;
import com.zwhzzz.Pojo.Account;
import com.zwhzzz.Pojo.Role;
import com.zwhzzz.Pojo.Roomtype;
import com.zwhzzz.Pojo.User;
import com.zwhzzz.Service.AccountService;
import com.zwhzzz.Service.LogService;
import com.zwhzzz.Service.RoomTypeService;
import com.zwhzzz.Service.home.RoomTypeExtService;
import com.zwhzzz.Util.Cuxiao;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.BeanUtils;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.ModelAndView;

import javax.servlet.http.HttpServletRequest;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * @author alen zhong
 * @date 19-9-27
 */
@Controller
@RequestMapping("/home")
public class HomeController {



    private final RoomTypeExtService roomTypeExtService;

    private final RoomTypeService roomTypeService;

    private final LogService logService;

    private final AccountService accountService;

    public HomeController(RoomTypeExtService roomTypeExtService, RoomTypeService roomTypeService, LogService logService, AccountService accountService) {
        this.roomTypeExtService = roomTypeExtService;
        this.roomTypeService = roomTypeService;
        this.logService = logService;
        this.accountService = accountService;
    }


    /**
     * 跳转前台首页
     * @param model
     * @param name
     * @return
     */
    @RequestMapping(value = "/index",method = RequestMethod.GET)
    public ModelAndView toIndex(ModelAndView model,
                                @RequestParam(value = "name",defaultValue = "")String name) {
        Map<String,Object> queryMap = new HashMap<>(0);
        queryMap.put("name",name);
        List<roomTypeDTO> homeListByName = roomTypeExtService.getHomeListByName(queryMap);
        PageHelper.startPage(0,999);
        //得到促销的价格信息
        List<roomTypeDTO> cuList = roomTypeExtService.getCuList();
        //计算促销价格
        for (roomTypeDTO roomTypeDTO : cuList) {
            Cuxiao.CuxiaoPrice(roomTypeDTO);
        }
        //赋值
        for (roomTypeDTO roomTypeDTO : homeListByName) {
            for (roomTypeDTO typeDTO : cuList) {
                if(roomTypeDTO.getId() == typeDTO.getId()) {
                    BeanUtils.copyProperties(typeDTO,roomTypeDTO);
                }
            }
        }
        model.addObject("roomTypeList", homeListByName);
        model.setViewName("/home/index/index");
        return model;
    }

    /**
     * 跳转到登录页面
     *
     * @param model
     * @return
     */
    @RequestMapping(value = "/login", method = RequestMethod.GET)
    public ModelAndView toLogin(ModelAndView model) {
        model.setViewName("home/index/login");
        return model;
    }

    /**
     * 跳转到注册页面
     *
     * @param model
     * @return
     */
    @RequestMapping(value = "/reg", method = RequestMethod.GET)
    public ModelAndView toRegister(ModelAndView model) {
        model.setViewName("home/index/reg");
        return model;
    }


    /**
     * 登录
     * @param account  前端传入的要验证的客户对象
     * @param vcode   前端传入的验证码
     * @param request  request请求域
     * @return
     */
    @RequestMapping(value = "/login",method = RequestMethod.POST)
    @ResponseBody
    public Map<String,Object> login(Account account, String vcode, HttpServletRequest request) {
        Map<String, Object> result = new HashMap<>(0);
        if(account == null) {
            result.put("success",false);
            result.put("msg","用户名和密码不能为空！");
            return result;
        }
        Object loginCpacha = request.getSession().getAttribute("loginCpacha");
        if(loginCpacha == null) {
            result.put("success",false);
            result.put("msg","验证码已过期！");
            return result;
        }
        if(!vcode.toUpperCase().equals(loginCpacha.toString().toUpperCase())) {
            result.put("success",false);
            result.put("msg","验证码错误！");
            logService.insertContent("客户" + account.getName() + " 登录前台时验证码错误!");
            return result;
        }
        Account accountByName = accountService.getAccountByName(account.getName());
        if(accountByName == null) {
            result.put("success",false);
            result.put("msg","用户名不存在！");
            return  result;
        }
        if(StringUtils.isBlank(account.getName())) {
            result.put("success",false);
            result.put("msg","用户名不能为空！");
            return result;
        }
        if(StringUtils.isBlank(account.getPassword())) {
            result.put("success",false);
            result.put("msg","密码不能为空！");
            return result;
        }
        if(! account.getPassword().equals(accountByName.getPassword())) {
            result.put("success",false);
            result.put("msg","密码错误！");
            return result;
        }
        if(accountByName.getStatus() == 1) {
            result.put("success",false);
            result.put("msg","该用户已被禁用！");
            return result;
        }
        request.getSession().setAttribute("loginCpacha",null);
        request.getSession().setAttribute("account",accountByName);
        logService.insertContent("客户" + account.getName() + " 登录前台成功");
        result.put("success",true);
        return result;
    }


    /**
     * 注册
     * @param account
     * @return
     */
    @RequestMapping(value = "/reg",method = RequestMethod.POST)
    @ResponseBody
    public Map<String,Object> register(Account account) {
        Map<String,Object> result = new HashMap<>(0);
        if(account == null) {
            result.put("success",false);
            result.put("msg","注册的信息不能为空！");
            return result;
        }
        Account accountByName = accountService.getAccountByName(account.getName());
        if(accountByName != null) {
            result.put("success",false);
            result.put("msg","用户名已存在！");
            return result;
        }
        if (accountService.insertAccount(account) > 0) {
            result.put("success", false);
            result.put("msg", "注册失败");
            logService.insertContent("客户" + account.getName() + " 注册失败！");
            return result;
        }
        result.put("success", true);
        logService.insertContent("客户" + account.getName() + " 注册成功！");
        return result;
    }


    /**
     * 登出
     * @param request
     * @return
     */
    @RequestMapping(value = "/logout",method = RequestMethod.GET)
    public String logout(HttpServletRequest request) {
        Account account = (Account) request.getSession().getAttribute("account");
        logService.insertContent("客户" + account.getName() +"退出登录！");
        request.getSession().invalidate();
        return "redirect:login";
    }





}
