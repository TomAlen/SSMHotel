package com.zwhzzz.Controller;

import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import com.zwhzzz.Pojo.Account;
import com.zwhzzz.Pojo.Role;
import com.zwhzzz.Pojo.User;
import com.zwhzzz.Service.AccountService;
import com.zwhzzz.Service.LogService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.ModelAndView;

import javax.servlet.http.HttpServletRequest;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * @author alen zhong
 * @date 19-9-25
 */
@Controller
@RequestMapping("/admin/account")
public class AccountController {

    private final AccountService accountService;

    private final LogService logService;

    public AccountController(AccountService accountService, LogService logService) {
        this.accountService = accountService;
        this.logService = logService;
    }

    /**
     * 转到客户页面
     * @param model
     * @return
     */
    @RequestMapping(value = "/list",method = RequestMethod.GET)
    public ModelAndView toAccountList(ModelAndView model) {
        model.setViewName("account/list");
        return model;
    }


    /**
     * 获取客户列表
     * @param page
     * @param rows
     * @param name
     * @return
     */
    @RequestMapping(value = "/list",method = RequestMethod.POST)
    @ResponseBody
    public Map<String,Object> getList(Integer page, Integer rows,
                                      @RequestParam(value = "name",required = false,defaultValue = "")String name,
                                      @RequestParam(value = "realname",required = false,defaultValue = "")String realname,
                                      @RequestParam(value = "idCard",required = false,defaultValue = "")String idCard,
                                      @RequestParam(value = "mobile",required = false,defaultValue = "")String mobile,
                                      @RequestParam(value = "status",required = false)Integer status){
        Map<String,Object> result = new HashMap<>(0);
        Map<String,Object> queryMap = new HashMap<>(0);
        queryMap.put("name",name);
        queryMap.put("realname",realname);
        queryMap.put("idCard",idCard);
        queryMap.put("mobile",mobile);
        queryMap.put("status",status);
        //分页
        PageHelper.startPage(page,rows);
        List<Account> accountList = accountService.getAccountList(queryMap);
        //封装到PageInfo中
        PageInfo<Account> pageInfo = new PageInfo<>(accountList);
        //将rows，total传入
        result.put("rows",pageInfo.getList());
        result.put("total",pageInfo.getTotal());
        return result;
    }


    /**
     * 添加客户
     * @param account
     * @return
     */
    @RequestMapping(value = "/add",method = RequestMethod.POST)
    @ResponseBody
    public Map<String,Object> insertAccount(Account account, HttpServletRequest request) {
        Map<String,Object> result = new HashMap<>(0);
        Role role = (Role) request.getSession().getAttribute("role");
        User admin = (User) request.getSession().getAttribute("admin");
        if(account == null) {
            result.put("success",false);
            result.put("msg","添加的信息为空！");
            return result;
        }
        if(isExist(account.getName(),account.getId())) {
            result.put("success",false);
            result.put("msg","客户用户名已存在！");
            return result;
        }

        if(account.getRealname().length() > 5) {
            result.put("success",false);
            result.put("msg","姓名的长度必须小于5位！");
        }


        if(accountService.insertAccount(account) > 0) {
            result.put("success",false);
            result.put("msg","添加失败！");
            logService.insertContent(role.getName() + admin.getUsername() + " 添加客户信息失败！");
            return result;
        }
        result.put("success",true);
        logService.insertContent(role.getName() + admin.getUsername() + " 添加客户信息成功！");
        return result;
    }


    /**
     * 更新客户信息
     * @param account
     * @param request
     * @return
     */
    @RequestMapping(value = "/edit",method = RequestMethod.POST)
    @ResponseBody
    public Map<String,Object> updateAccount(Account account,HttpServletRequest request) {
        Map<String,Object> result = new HashMap<>(0);
        Role role = (Role) request.getSession().getAttribute("role");
        User admin = (User) request.getSession().getAttribute("admin");
        if(account == null) {
            result.put("success", false);
            result.put("msg", "添加的信息为空！");
            return result;
        }
        if(isExist(account.getName(),account.getId())) {
            result.put("success",false);
            result.put("msg","客户用户名已存在！");
            return result;
        }
        if(accountService.updateAccount(account) > 0) {
            result.put("success",false);
            result.put("msg","添加失败！");
            logService.insertContent(role.getName() + admin.getUsername() + " 更新客户信息失败！");
            return result;
        }
        result.put("success",true);
        logService.insertContent(role.getName() + admin.getUsername() + " 更新客户信息成功！");
        return result;
    }

    /**
     * 删除客户
     * @param id  客户id
     * @param request
     * @return
     */
    @RequestMapping(value = "/delete",method = RequestMethod.POST)
    @ResponseBody
    public Map<String,Object> deleteAccount(Integer id,HttpServletRequest request) {
        Map<String,Object> result = new HashMap<>(0);
        Role role = (Role) request.getSession().getAttribute("role");
        User admin = (User) request.getSession().getAttribute("admin");
        if(id == null) {
            result.put("success", false);
            result.put("msg", "请选择要删除的数据！");
            return result;
        }
        try{
            if(accountService.deleteAccount(id) > 0) {
                result.put("success",false);
                result.put("msg","删除失败！");
                logService.insertContent(role.getName() + admin.getUsername() + " 删除客户信息失败！");
                return  result;
            }
        }catch (Exception e){
            result.put("success",false);
            result.put("msg","该客户下存在订单信息，请先删除该客户下的所有订单信息！");
            logService.insertContent(role.getName() + admin.getUsername() + " 删除客户信息失败，该客户下存在订单信息，请先删除该客户下的所有订单信息！！");
            return  result;
        }
        result.put("success",true);
        logService.insertContent(role.getName() + admin.getUsername() + " 删除客户信息成功！");
        return result;
    }


    /**
     * 共用方法
     * 判断客户是否存在，如果存在就返回true
     * @param name
     * @return
     */
    public boolean isExist(String name,Integer id) {
        Account accountByName = accountService.getAccountByName(name);
        if(accountByName == null) {
            return false;
        }
        if(accountByName.getId() == id) {
            return false;
        }
        return true;
    }




}
