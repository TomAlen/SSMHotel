package com.zwhzzz.Controller.home;

import com.github.pagehelper.PageHelper;
import com.zwhzzz.DTO.roomTypeDTO;
import com.zwhzzz.Pojo.Account;
import com.zwhzzz.Pojo.BookOrder;
import com.zwhzzz.Pojo.Roomtype;
import com.zwhzzz.Service.*;
import com.zwhzzz.Service.home.RoomTypeExtService;
import com.zwhzzz.Util.CalMonthUtil;
import com.zwhzzz.Util.CalStatDatas;
import com.zwhzzz.Util.Cuxiao;
import org.springframework.stereotype.Controller;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.ModelAndView;

import javax.servlet.http.HttpServletRequest;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * @author alen zhong
 * @date 19-9-28
 */
@Controller
@RequestMapping("/home/account")
public class HomeAccountController {

    private final RoomTypeService roomTypeService;

    private final AccountService accountService;

    private final BookOrderService bookOrderService;

    private final LogService logService;

    private final RoomTypeExtService roomTypeExtService;

    private final StatisticService statisticService;


    public HomeAccountController(RoomTypeService roomTypeService, AccountService accountService, BookOrderService bookOrderService, LogService logService, RoomTypeExtService roomTypeExtService, StatisticService statisticService) {
        this.roomTypeService = roomTypeService;
        this.accountService = accountService;
        this.bookOrderService = bookOrderService;
        this.logService = logService;
        this.roomTypeExtService = roomTypeExtService;
        this.statisticService = statisticService;
    }

    /**
     * 跳转到个人中心控制台
     * @param model
     * @param request
     * @return
     */
    @RequestMapping(value = "/index",method = RequestMethod.GET)
    public ModelAndView toIndexPage(ModelAndView model,HttpServletRequest request){
        Account account = (Account) request.getSession().getAttribute("account");
        //设置真实姓名
        String realName = account.getRealname().substring(1, 2);
        account.setLaterRealName(account.getRealname().replace(realName,"*"));
        //设置手机号
        String Mobile = account.getMobile().substring(3, 7);
        account.setLaterMobile(account.getMobile().replace(Mobile,"****"));

        PageHelper.startPage(0,999);
        List<BookOrder> bookOrders = bookOrderService.selectByAccountId(account.getId());
        List<Roomtype> roomtypeList = roomTypeService.getList();
        BookOrder order = new BookOrder();
        for (BookOrder bookOrder : bookOrders) {
            //计算入住时长
            String arrivetime = bookOrder.getArrivetime();
            String leavetime = bookOrder.getLeavetime();

            Integer arrive = Integer.parseInt(arrivetime.substring(5, 7));
            Integer leave = Integer.parseInt(leavetime.substring(5, 7));
            int days = CalMonthUtil.DaysWithBookOrder(arrive, leave, arrivetime, leavetime);
            bookOrder.setCheckDays(days);

            //判断房型是否为促销房型
            for (Roomtype roomtype : roomtypeList) {
                if(bookOrder.getRoomtypeid() == roomtype.getId()) {
                    bookOrder.setRoomtype(roomtype);
                    if(bookOrder.getPrice().floatValue() != roomtype.getPrice().floatValue()) {
                        //表示为促销房型
                        order.setId(bookOrder.getId());
                        order.setCustatus(1);
                    }else {
                        order.setId(bookOrder.getId());
                        order.setCustatus(0);
                    }
                    bookOrderService.updateBookOrder(order);
                }
            }
        }
        model.addObject("bookOrderList", bookOrders);
        model.addObject("roomTypeList", roomtypeList);
        model.setViewName("/home/account/index2");
        return model;
    }

    @RequestMapping(value = "/book_order",method = RequestMethod.GET)
    public ModelAndView BookOrderRoom(ModelAndView model,Integer roomTypeId) {
        Roomtype roomtype = roomTypeService.findById(roomTypeId);
        model.addObject("roomType",roomtype);
        model.setViewName("home/account/book_order");
        return model;
    }

    /**
     * 跳转促销订单页面
     *
     * @param modelAndView
     * @param roomTypeId
     * @return
     */
    @RequestMapping(value = "/sale_book_order", method = RequestMethod.GET)
    public ModelAndView CuXiaoBookOrderRoom(ModelAndView modelAndView, Integer roomTypeId) {
        //根据房型id将房型查出
        roomTypeDTO roomTypeDTO = roomTypeExtService.CuListRoomType(roomTypeId);
        //打折前的价钱
        roomTypeDTO.setPrePrice(roomTypeDTO.getPrice());
        //打折后的价钱
        Cuxiao.CuxiaoPrice(roomTypeDTO);
        modelAndView.addObject("roomType", roomTypeDTO);
        modelAndView.setViewName("home/account/sale_book_order");
        return modelAndView;
    }

    /**
     * 跳转八折区的订单页面
     *
     * @param modelAndView
     * @param roomTypeId
     * @return
     */
    @RequestMapping(value = "/eight_sale_book_order", method = RequestMethod.GET)
    public ModelAndView EightBookOrder(ModelAndView modelAndView, Integer roomTypeId) {
        roomTypeDTO roomTypeDTO = roomTypeExtService.CuListRoomType(roomTypeId);
        roomTypeDTO.setPrePrice(roomTypeDTO.getPrice());
        //八折区的价钱计算
        roomTypeDTO.setLaterPrice((double) Math.round(roomTypeDTO.getPrePrice() * 0.8));
        modelAndView.addObject("roomType", roomTypeDTO);
        modelAndView.setViewName("home/account/sale_book_order");
        return modelAndView;
    }



    /**
     * 提交订单-->按原价提交
     *
     * @param bookOrder
     * @param request
     * @return
     */
    @RequestMapping(value = "/book_order", method = RequestMethod.POST)
    @ResponseBody
    @Transactional
    public Map<String, Object> getBook_order(BookOrder bookOrder, HttpServletRequest request) {
        Map<String, Object> result = new HashMap<>(0);
        if (bookOrder == null) {
            result.put("success", false);
            result.put("msg", "预定信息不能为空");
            return result;
        }
        //从会话中取出客户的信息
        Account account = (Account) request.getSession().getAttribute("account");
        if (account == null) {
            result.put("success", false);
            result.put("msg", "客户不能为空!");
            return result;
        }
        //将登录客户id设为预定订单中的id
        bookOrder.setAccountid(account.getId());
        //设置时间
        bookOrder.setCreatetime(new Date());
        //设置状态为预定中八
        bookOrder.setStatus(0);

        String arriveTime = bookOrder.getArrivetime();
        String leaveTime = bookOrder.getLeavetime();

        Integer  arriveMonth = Integer.parseInt(arriveTime.substring(5, 7));
        Integer leaveMonth = Integer.parseInt(leaveTime.substring(5, 7));

        int days = CalMonthUtil.DaysWithBookOrder(arriveMonth, leaveMonth, arriveTime, leaveTime);

        bookOrder.setPrice(bookOrder.getPrice() * days);

        if (bookOrderService.insertBookOrder(bookOrder) > 0) {
            result.put("success", false);
            result.put("msg", "添加预定订单失败！");
            logService.insertContent("客户" + account.getName() + " 添加预定订单失败！");
            return result;
        }
        //添加成功后，房型的可用房间数、预定数、状态数改变
        //根据roomTypeId获取房间数
        Roomtype roomType = roomTypeService.findById(bookOrder.getRoomtypeid());
        if (roomType != null) {
            roomType.setAvilablenum(roomType.getAvilablenum() - 1);
            roomType.setBooknum(roomType.getBooknum() + 1);
            //如果可用房间数小于0，状态设为已满
            if (roomType.getAvilablenum() <= 0) {
                Roomtype roomtypeBySatus = new Roomtype();
                roomtypeBySatus.setStatus(0);
                roomTypeService.updateStatus(roomtypeBySatus);
            }
            //更新相应的数
            roomTypeService.updateNum(roomType);
        }
        result.put("success", true);
        logService.insertContent("客户" + account.getName() + " 添加预定信息成功！");
        return result;
    }



    /**
     * 退房订单
     *
     * @param bookOrderId
     * @param request
     * @return
     */
    @RequestMapping(value = "/delete", method = RequestMethod.POST)
    @ResponseBody
    @Transactional
    public Map<String, Object> deleteBookOrder(@RequestParam(value = "bookOrderId", required = false) Integer bookOrderId,
                                               HttpServletRequest request) {
        Map<String, Object> result = new HashMap<>(0);
        Account account = (Account) request.getSession().getAttribute("account");
        if (bookOrderId == null) {
            result.put("success", false);
            result.put("msg", "删除的信息为空！");
            return result;
        }

        //把添加前的信息返回，预定数，可用房间数返回
        //先根据订单id将房型找出
        BookOrder bookOrder = bookOrderService.findById(bookOrderId);
        Roomtype roomType = roomTypeService.findById(bookOrder.getRoomtypeid());
        if (roomType != null) {
            roomType.setAvilablenum(roomType.getAvilablenum() + 1);
            roomType.setBooknum(roomType.getBooknum() - 1);
            roomTypeService.updateNum(roomType);
            //如果可预订数大于1  ，状态变为可预订
            if (roomType.getAvilablenum() > 0) {
                Roomtype roomtypeByUpdateStatus = new Roomtype();
                roomtypeByUpdateStatus.setStatus(1);
                roomTypeService.updateStatus(roomtypeByUpdateStatus);
            }
        }
        if (bookOrderService.deleteBookOrder(bookOrderId) > 0) {
            result.put("success", false);
            result.put("msg", "删除失败！");
            logService.insertContent("客户" + account.getName() + "删除订单失败~");
            return result;
        }
        result.put("success", true);
        logService.insertContent("客户" + account.getName() + "删除订单成功！");
        return result;
    }


    /**
     * 个人用户信息更新
     *
     * @param account
     * @param request
     * @return
     */
    @RequestMapping(value = "/update_info", method = RequestMethod.POST)
    @ResponseBody
    public Map<String, Object> update_info(Account account, HttpServletRequest request) {
        Map<String, Object> result = new HashMap<>(0);
        if (account == null) {
            result.put("success", false);
            result.put("msg", "用户信息不能为空");
            return result;
        }
        //从session中得到登录时的客户
        Account accountBySession = (Account) request.getSession().getAttribute("account");
        //判断登录时的用户名与更新后的是否存在
        if (isExist(account.getName(), accountBySession.getId())) {
            result.put("success", false);
            result.put("msg", "用户名已存在");
            return result;
        }
        //对的登录时的客户更新
        accountBySession.setName(account.getName());
        accountBySession.setRealname(account.getRealname());
        accountBySession.setIdcard(account.getIdcard());
        accountBySession.setMobile(account.getMobile());
        accountBySession.setAddress(account.getAddress());
        if (accountService.updateAccount(accountBySession) > 0) {
            result.put("success", false);
            result.put("msg", "更新用户信息失败！");
            logService.insertContent("客户" + account.getName() + " 更新用户信息失败！");
            return result;
        }
        result.put("success", true);
        result.put("msg", "更新成功！");
        logService.insertContent("客户" + account.getName() + " 更新用户信息成功！");
        return result;
    }

    /**
     * 修改个人信息密码
     *
     * @param oldPassword
     * @param newPassword
     * @return
     */
    @RequestMapping(value = "/update_pwd", method = RequestMethod.POST)
    @ResponseBody
    public Map<String, Object> update_pwd(String oldPassword, String newPassword, HttpServletRequest request) {
        Map<String, Object> result = new HashMap<>(0);
        //判空操作交给前端处理
        //获取登录的原密码
        Account account = (Account) request.getSession().getAttribute("account");
        if (!oldPassword.equals(account.getPassword())) {
            result.put("success", false);
            result.put("msg", "原密码错误");
            return result;
        }
        //设置为新密码
        account.setPassword(newPassword);
        if (accountService.updateAccount(account) > 0) {
            result.put("success", false);
            result.put("msg", "密码更新失败！");
            logService.insertContent("客户" + account.getName() + " 更新密码失败!");
            return result;
        }
        result.put("success", true);
        result.put("msg", "密码更新成功！");
        logService.insertContent("客户" + account.getName() + " 更新密码成功!");
        return result;
    }

    /**
     * 判断用户名是否存在
     *
     * @param name
     * @param id
     * @return
     */
    public boolean isExist(String name, Integer id) {
        Account accountByName = accountService.getAccountByName(name);
        if (accountByName == null) return false;
        //编辑时表明是它本身,
        if (accountByName != null && accountByName.getId().longValue() == id.longValue()) return false;
        return true;
    }


    /**
     * 根据客户订单形成统计分析
     *
     */
    @RequestMapping(value = "/get_stats",method = RequestMethod.POST)
    @ResponseBody
    public Map<String,Object> getStatsByName (HttpServletRequest request) {
        Map<String, Object> result = new HashMap<>(0);
        Account account = (Account) request.getSession().getAttribute("account");
        List<Map> statsByName = statisticService.getStatsByName(account.getName());
        Map<String, Object> statDatas = CalStatDatas.getStatDatas(statsByName);
        if(statDatas.isEmpty()) {
            result.put("success",false);
            result.put("msg","统计信息为空！");
            return result;
        }
        result.put("success",true);
        result.put("content",statDatas);
        return result;
    }

}
