package com.zwhzzz.Controller;

import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import com.github.pagehelper.util.StringUtil;
import com.zwhzzz.Pojo.Floor;
import com.zwhzzz.Pojo.Role;
import com.zwhzzz.Pojo.User;
import com.zwhzzz.Service.FloorService;
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
 * @date 19-9-24
 */
@Controller
@RequestMapping("/admin/floor")
public class floorController {

    private final FloorService floorService;

    private final LogService logService;

    public floorController(FloorService floorService, LogService logService) {
        this.floorService = floorService;
        this.logService = logService;
    }

    /**
     * 跳转到楼层页面
     * @param model
     * @return
     */
    @RequestMapping(value = "/list",method = RequestMethod.GET)
    @ResponseBody
    public ModelAndView getFloorList(ModelAndView model) {
        model.setViewName("/floor/list");
        return model;
    }

    /**
     * 获取楼层列表
     * @param name
     * @param page
     * @return
     */
    @RequestMapping(value = "/list",method = RequestMethod.POST)
    @ResponseBody
    public Map<String,Object> getList(@RequestParam(value = "name",required = false,defaultValue = "")String name,
                                      Integer page,Integer rows) {
        Map<String,Object> result = new HashMap<>(0);
        PageHelper.startPage(page,rows);
        List<Floor> floorList = floorService.getList(name);
        PageInfo<Floor> floorPageInfo = new PageInfo<>(floorList);
        result.put("rows",floorPageInfo.getList());
        result.put("total",floorPageInfo.getTotal());
        return result;
    }

    /**
     * 添加楼层信息
     * @param floor
     * @return
     */
    @RequestMapping(value = "/add",method = RequestMethod.POST)
    @ResponseBody
    public Map<String,String> insertFloor(Floor floor, HttpServletRequest request) {
        Map<String,String> result = new HashMap<>(0);
        if(floor == null) {
            result.put("type","error");
            result.put("msg","请输入楼层信息");
            return result;
        }
        if(StringUtil.isEmpty(floor.getName())) {
            result.put("type","error");
            result.put("msg","楼层名不能为空！");
            return result;
        }
        if(floorService.insertFloor(floor) > 0) {
            result.put("type","error");
            result.put("msg","添加楼层信息失败！");
            return result;
        }
        result.put("type","success");
        User admin = (User)request.getSession().getAttribute("admin");
        Role role = (Role)request.getSession().getAttribute("role");
        logService.insertContent(role.getName() + admin.getUsername() + " 添加楼层信息成功！");
        return  result;
    }

    /**
     * 更新楼层信息
     * @param floor
     * @param request
     * @return
     */
    @RequestMapping(value = "/edit",method = RequestMethod.POST)
    @ResponseBody
    public Map<String,String> updateFloor(Floor floor,HttpServletRequest request) {
        Map<String,String> result = new HashMap<>(0);
        if(floor == null) {
            result.put("type","error");
            result.put("msg","请输入楼层信息");
            return result;
        }
        if(StringUtil.isEmpty(floor.getName())) {
            result.put("type","error");
            result.put("msg","楼层名不能为空！");
            return result;
        }
        if(floorService.updateFloor(floor) > 0) {
            result.put("type","error");
            result.put("msg","更新楼层失败！");
            return result;
        }
        result.put("type","success");
        User admin = (User)request.getSession().getAttribute("admin");
        Role role = (Role)request.getSession().getAttribute("role");
        logService.insertContent(role.getName() + admin.getUsername() + " 更新楼层信息成功！");
        return  result;
    }

    /**
     * 删除楼层信息
     * @param id
     * @param request
     * @return
     */
    @RequestMapping(value = "/delete",method = RequestMethod.POST)
    @ResponseBody
    public Map<String,Object> deleteFloor(@RequestParam(value = "id",required = false) Integer id,
                                          HttpServletRequest request) {
        Map<String,Object> result = new HashMap<>(0);
        User admin = (User)request.getSession().getAttribute("admin");
        Role role = (Role)request.getSession().getAttribute("role");
        if(id == null) {
            result.put("success",false);
            result.put("msg","请选择一条数据删除！");
            return result;
        }
        try{
            if(floorService.deleteFloor(id) > 0) {
                result.put("success",false);
                result.put("msg","删除失败！");
                logService.insertContent(role.getName()+admin.getUsername() + " 删除楼层信息失败！");
                return result;
            }
        }catch (Exception e){
            result.put("success",false);
            result.put("msg","该楼层下的存在房间信息，请先删除该楼层下的房间信息！");
            e.printStackTrace();
            return result;
        }
        result.put("success",true);
        logService.insertContent(role.getName()+admin.getUsername()+" 删除楼层信息成功！");
        return result;
    }

}
