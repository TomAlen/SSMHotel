package com.zwhzzz.Controller;

import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import com.zwhzzz.Pojo.Role;
import com.zwhzzz.Pojo.Roomtype;
import com.zwhzzz.Pojo.User;
import com.zwhzzz.Service.LogService;
import com.zwhzzz.Service.RoomTypeService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.transaction.annotation.Transactional;
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
@RequestMapping("/admin/RoomType")
public class RoomTypeController {


    private final RoomTypeService roomTypeService;

    private final LogService logService;

    public RoomTypeController(RoomTypeService roomTypeService, LogService logService) {
        this.roomTypeService = roomTypeService;
        this.logService = logService;
    }

    /**
     * 转到房型页面
     * @param model
     * @return
     */
    @RequestMapping(value = "/list",method = RequestMethod.GET)
    public ModelAndView getRoomTypeList(ModelAndView model) {
        model.setViewName("room_type/list");
        return model;
    }

    /**
     * 获取房型列表
     * @param page
     * @param rows
     * @param status
     * @return
     */
    @RequestMapping(value = "/list",method = RequestMethod.POST)
    @ResponseBody
    public Map<String,Object> getList(Integer page,Integer rows,
                                      @RequestParam(value = "name",required = false,defaultValue = "")String name,
                                      @RequestParam(value = "status",required = false)Integer status) {
        Map<String,Object> result = new HashMap<>(0);
        Map<String,Object> queryMap = new HashMap<>(0);
        queryMap.put("name",name);
        queryMap.put("status",status);
        PageHelper.startPage(page,rows);
        List<Roomtype> roomTypeList = roomTypeService.getRoomTypeList(queryMap);
        PageInfo<Roomtype> roomtypePageInfo = new PageInfo<>(roomTypeList);
        result.put("rows",roomtypePageInfo.getList());
        result.put("total",roomtypePageInfo.getTotal());
        return result;
    }


    /**
     * 添加房型
     * @param roomType
     * @return
     */
    @RequestMapping(value = "/add",method = RequestMethod.POST)
    @ResponseBody
    public Map<String,String> insertRoomType(Roomtype roomType, HttpServletRequest request) {
        Map<String,String> result = new HashMap<>(0);
        User admin = (User)request.getSession().getAttribute("admin");
        Role role = (Role)request.getSession().getAttribute("role");
        if(roomType == null) {
            result.put("type","error");
            result.put("msg","添加的信息为空！");
            return result;
        }
        Roomtype roomTypeByName = roomTypeService.findRoomTypeByName(roomType.getName());
        if(roomTypeByName != null) {
            result.put("type","error");
            result.put("msg","该房型已存在！");
            return result;
        }
        roomType.setAvilablenum(roomType.getRoomnum());//初始为可预订房间为房间数
        //默认已预订数和已入住数为0
        roomType.setBooknum(0);
        roomType.setLivednum(0);
        if(roomTypeService.insertRoomType(roomType) > 0) {
            result.put("type","error");
            result.put("msg","添加失败！");
            logService.insertContent(role.getName() + admin.getUsername() + " 添加房型信息失败！");
            return result;
        }
        result.put("type","success");
        logService.insertContent(role.getName() + admin.getUsername() + " 添加房型信息成功！");
        return result;
    }


    /**
     * 修改房型
     * @param roomType
     * @return
     */
    @RequestMapping(value = "/edit",method = RequestMethod.POST)
    @ResponseBody
    @Transactional
    public Map<String,Object> updateRoomType(Roomtype roomType,HttpServletRequest request) {
        Map<String,Object> result = new HashMap<>(0);
        User admin = (User)request.getSession().getAttribute("admin");
        Role role = (Role)request.getSession().getAttribute("role");
        if(roomType == null) {
            result.put("type","error");
            result.put("msg","信息不能为空");
            return result;
        }
        //修改房间数，相应的可预订数页随之改变
        //根据id找到房型
        Roomtype roomTypeById = roomTypeService.findById(roomType.getId());
        if(roomTypeById == null) {
            result.put("type","error");
            result.put("msg","改房型不存在！");
            return result;
        }
        //先算出修改后与修改前的差值，然后计算出可预定数。
        int offset = roomType.getRoomnum() - roomTypeById.getRoomnum();
        //用修改前的可预定数加上偏移量(差值)
        roomType.setAvilablenum(roomTypeById.getAvilablenum() + offset);
        roomType.setStatus(1);
        System.out.println(offset+"->"+roomType.getAvilablenum());
        //如果得出的可预定数小于0，那么就设置为0，状态也设置为已满
        if(roomType.getAvilablenum() <= 0) {
            roomType.setAvilablenum(0);
            roomType.setStatus(0);//房型已满
            if(roomType.getAvilablenum() + roomTypeById.getBooknum() + roomTypeById.getLivednum() > roomType.getRoomnum()) {
                result.put("type","error");
                result.put("msg","房间数设置不合理！");
                return result;
            }
        }
        if(roomTypeService.updateRoomType(roomType) > 0) {
            result.put("type","error");
            result.put("msg","修改失败！");
            logService.insertContent(role.getName() + admin.getUsername() + " 修改房型信息失败！");
            return result;
        }
        result.put("type","success");
        logService.insertContent(role.getName() + admin.getUsername() + " 修改房型信息成功！");
        return result;
    }

    /**
     * 删除房型信息
     * @param id
     * @param request
     * @return
     */
    @RequestMapping(value = "/delete",method = RequestMethod.POST)
    @ResponseBody
    public Map<String,Object> deleteRoomType(Integer id,HttpServletRequest request) {
        User admin = (User)request.getSession().getAttribute("admin");
        Role role = (Role)request.getSession().getAttribute("role");
        Map<String,Object> result = new HashMap<>(0);
        if(id == null) {
            result.put("success",false);
            result.put("msg","请选择一条数据删除！");
            return result;
        }
        try{
            if(roomTypeService.deleteRoomType(id) > 0) {
                result.put("type","error");
                result.put("msg","删除失败");
                logService.insertContent(role.getName() + admin.getUsername() + " 删除房型失败！");
                return  result;
            }
        }catch (Exception e){
            result.put("type","error");
            result.put("msg","该房型下的存在房间信息，请先删除该楼层下的房间信息！");
            e.printStackTrace();
            return result;
        }
        result.put("type","success");
        logService.insertContent(role.getName() + admin.getUsername() + " 删除房型信息成功！");
        return result;
    }

}
