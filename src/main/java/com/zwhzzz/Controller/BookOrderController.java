package com.zwhzzz.Controller;

import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import com.zwhzzz.Pojo.BookOrder;
import com.zwhzzz.Pojo.Role;
import com.zwhzzz.Pojo.Roomtype;
import com.zwhzzz.Pojo.User;
import com.zwhzzz.Service.AccountService;
import com.zwhzzz.Service.BookOrderService;
import com.zwhzzz.Service.LogService;
import com.zwhzzz.Service.RoomTypeService;
import com.zwhzzz.Util.CalMonthUtil;
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
 * @date 19-9-25
 */
@Controller
@RequestMapping("/admin/book_order")
public class BookOrderController {

    private final BookOrderService bookOrderService;

    private final RoomTypeService roomTypeService;

    private final AccountService accountService;

    private final LogService logService;

    public BookOrderController(BookOrderService bookOrderService, RoomTypeService roomTypeService, AccountService accountService, LogService logService) {
        this.bookOrderService = bookOrderService;
        this.roomTypeService = roomTypeService;
        this.accountService = accountService;
        this.logService = logService;
    }


    @RequestMapping(value = "/list", method = RequestMethod.GET)
    public ModelAndView toBookOrderIndex(ModelAndView model) {
        model.addObject("roomTypeList", roomTypeService.getList());
        model.addObject("accountList", accountService.getList());
        model.setViewName("book_order/list");
        return model;
    }


    @RequestMapping(value = "/list", method = RequestMethod.POST)
    @ResponseBody
    public Map<String, Object> getBookOrderList(Integer page, Integer rows,
                                                @RequestParam(value = "name", defaultValue = "") String name,
                                                @RequestParam(value = "idCard", defaultValue = "") String idCard,
                                                @RequestParam(value = "mobile", defaultValue = "") String mobile,
                                                @RequestParam(value = "accountId", required = false) Integer accountId,
                                                @RequestParam(value = "roomTypeId", required = false) Integer roomTypeId,
                                                @RequestParam(value = "status", required = false) Integer status) {
        Map<String, Object> result = new HashMap<>(0);
        Map<String, Object> queryMap = new HashMap<>(0);
        queryMap.put("name", name);
        queryMap.put("idCard", idCard);
        queryMap.put("mobile", mobile);
        queryMap.put("accountId", accountId);
        queryMap.put("roomTypeId", roomTypeId);
        queryMap.put("status", status);
        //分页
        PageHelper.startPage(page, rows);
        //将模糊查询的条件查出
        List<BookOrder> bookOrderList = bookOrderService.getList(queryMap);
        //封装
        PageInfo<BookOrder> pageInfo = new PageInfo<>(bookOrderList);
        result.put("rows", pageInfo.getList());
        result.put("total", pageInfo.getTotal());
        return result;
    }


    /**
     * 添加预定订单
     *
     * @return
     */
    @RequestMapping(value = "/add", method = RequestMethod.POST)
    @ResponseBody
    public Map<String, Object> insertBookOrder(BookOrder bookOrder, HttpServletRequest request) {
        Map<String, Object> result = new HashMap<>(0);
        Role role = (Role) request.getSession().getAttribute("role");
        User admin = (User) request.getSession().getAttribute("admin");
        if (bookOrder == null) {
            result.put("success", false);
            result.put("msg", "添加的信息不能为空！");
            return result;
        }
        //设置当前日期，添加进去
        bookOrder.setCreatetime(new Date());
        String arriveTime = bookOrder.getArrivetime();
        String leaveTime = bookOrder.getLeavetime();
        //计算  月份
        int arriveMonth = Integer.parseInt(arriveTime.substring(5, 7));
        int leaveMonth = Integer.parseInt(leaveTime.substring(5, 7));
        //调用静态方法进行计算订单的天数
        int days = CalMonthUtil.DaysWithBookOrder(arriveMonth, leaveMonth, arriveTime, leaveTime);

        bookOrder.setPrice(bookOrder.getPrice() * days);
        if (bookOrderService.insertBookOrder(bookOrder) > 0) {
            result.put("success", false);
            result.put("msg", "添加失败");
            logService.insertContent(role.getName() + admin.getUsername() + " 添加预定订单失败");
            return result;
        }
        //订单成功后去修改房型的可预定数、已预定数、入住数等信息
        Roomtype roomType = roomTypeService.findById(bookOrder.getRoomtypeid());
        if (roomType != null) {
            //可预定数、已预定数相继 - 1 ，+ 1
            roomType.setAvilablenum(roomType.getAvilablenum() - 1);
            roomType.setBooknum(roomType.getBooknum() + 1);
            roomTypeService.updateNum(roomType);
            //判断如果可预定数为0，就将状态设置为已满
            if (roomType.getAvilablenum() == 0) {
                roomType.setStatus(0);
                roomTypeService.updateRoomType(roomType);
            }
        }
        result.put("success", true);
        logService.insertContent(role.getName() + admin.getUsername() + " 添加预定订单成功！");
        return result;
    }


    /**
     * 编辑订单信息
     *
     * @param bookOrder
     * @param request
     * @return
     */
    @RequestMapping(value = "/edit", method = RequestMethod.POST)
    @ResponseBody
    @Transactional
    public Map<String, Object> updateBookOrder(BookOrder bookOrder, HttpServletRequest request) {
        Map<String, Object> result = new HashMap<>(0);
        Role role = (Role) request.getSession().getAttribute("role");
        User admin = (User) request.getSession().getAttribute("admin");
        if (bookOrder == null) {
            result.put("success", false);
            result.put("msg", "更新的数据不能为空！");
            return result;
        }
        //先根据id找出找出原预定订单信息
        BookOrder ExistBook = bookOrderService.findById(bookOrder.getId());
        if (ExistBook == null) {
            result.put("success", false);
            result.put("msg", "请选择正确的数据进行编辑！");
            return result;
        }
        if (bookOrderService.updateBookOrder(bookOrder) > 0) {
            result.put("success", false);
            result.put("msg", "更新失败！");
            logService.insertContent(role.getName() + admin.getUsername() + " 更新预定订单信息失败！");
            return result;
        }
        //思路：
        //先将原房型查出，再对该可预定数、已预定数进行修改
        //再将新房型查出，一样对可预定数和已预订数进行修改

        //如果id不同，表示id不重复，可用
        if (ExistBook.getId().longValue() != bookOrder.getId().longValue()) {
            //原预定订单
            Roomtype oldRoomType = roomTypeService.findById(ExistBook.getRoomtypeid());
            //可预定数 +1
            oldRoomType.setAvilablenum(oldRoomType.getAvilablenum() + 1);
            //已预订数 -1
            oldRoomType.setBooknum(oldRoomType.getBooknum() - 1);
            //更新
            roomTypeService.updateNum(oldRoomType);
            //如果原先的是满房，恢复后则为不满
            if (oldRoomType.getAvilablenum() > 0) {
                Roomtype roomtype = new Roomtype();
                roomtype.setStatus(1);
                roomTypeService.updateStatus(roomtype);
            }
            //新预定订单
            Roomtype newRoomType = roomTypeService.findById(bookOrder.getRoomtypeid());
            //可预定数+1
            newRoomType.setAvilablenum(newRoomType.getAvilablenum() - 1);
            //已预订数 +1
            newRoomType.setBooknum(newRoomType.getBooknum() + 1);
            roomTypeService.updateNum(newRoomType);
            //如果可预定数为0，那么就将状态设置为已住满
            if (newRoomType.getAvilablenum() == 0) {
                //将状态设置为已满
                Roomtype roomtype = new Roomtype();
                roomtype.setStatus(1);
                roomTypeService.updateStatus(roomtype);
            }
        } else {
            /*
            同一个订单进行修改
            1  房型修改 --> 原房型的类型  可用房间数 + 1  已预订数  - 1
                       --> 修改后的房型  可用房间数 - 1  已预订数  + 1
             */
            //修改前的房型
            Roomtype oldRoomType = roomTypeService.findById(ExistBook.getRoomtypeid());
            if (oldRoomType != null) {
                oldRoomType.setAvilablenum(oldRoomType.getAvilablenum() + 1);
                oldRoomType.setBooknum(oldRoomType.getBooknum() - 1);

                roomTypeService.updateRoomType(oldRoomType);
            }
            //修改后的房型
            Roomtype newRoomType = roomTypeService.findById(bookOrder.getRoomtypeid());
            newRoomType.setAvilablenum(newRoomType.getAvilablenum() - 1);
            newRoomType.setBooknum(newRoomType.getBooknum() + 1);
            if (oldRoomType.getAvilablenum() <= 0) {
                Roomtype roomtype = new Roomtype();
                roomtype.setStatus(0);
                roomTypeService.updateStatus(roomtype);
            }
            roomTypeService.updateRoomType(newRoomType);
        }
        result.put("success", true);
        logService.insertContent(role.getName() + admin.getUsername() + " 更新预定订单信息成功！");
        return result;
    }

}
