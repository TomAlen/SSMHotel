package com.zwhzzz.Controller.home;

import com.zwhzzz.DTO.roomTypeDTO;
import com.zwhzzz.Service.home.RoomTypeExtService;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.ModelAndView;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * @author alen zhong
 * @date 19-9-28
 */
@Controller
@RequestMapping("/home/cuxiao")
public class CuxiaoController {


    private final RoomTypeExtService  roomTypeExtService;


    public CuxiaoController(RoomTypeExtService roomTypeExtService) {
        this.roomTypeExtService = roomTypeExtService;
    }


    @RequestMapping(value = "/index",method = RequestMethod.GET)
    public ModelAndView toCuxiaoPage(ModelAndView model,
                                     @RequestParam(value = "name", required = false, defaultValue = "") String name){

        Map<String, Object> queryMap = new HashMap<>(0);
        queryMap.put("name", name);
        List<roomTypeDTO> roomTypeDTOS = roomTypeExtService.getHomeListByName(queryMap);
        for (roomTypeDTO roomTypeDTO : roomTypeDTOS) {
            Double price = roomTypeDTO.getPrice();
            double round = (double) Math.round(price * 0.8);
            roomTypeDTO.setPrice(round);
            roomTypeDTO.setPrePrice(price);
        }
        List<roomTypeDTO> roomType = roomTypeDTOS;
        model.addObject("CuroomTypes", roomType);
        model.setViewName("/home/index/cuxiao");
        return model;
    }


}
