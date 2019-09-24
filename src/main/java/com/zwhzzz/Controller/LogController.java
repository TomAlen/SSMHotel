package com.zwhzzz.Controller;

import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import com.github.pagehelper.util.StringUtil;
import com.zwhzzz.Pojo.Log;
import com.zwhzzz.Service.LogService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.ModelAndView;

import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Controller
@RequestMapping("/admin/log")
public class LogController {

    @Autowired
    private LogService logService;

    /**
     * 跳转到日志管理页面
     * @param model
     * @return
     */
    @RequestMapping(value = "/list",method = RequestMethod.GET)
    public ModelAndView getLog(ModelAndView model) {
        model.setViewName("log/list");
        return model;
    }


    /**
     * 获取日志列表
     * @param content
     * @return
     */
    @RequestMapping(value = "/list",method = RequestMethod.POST)
    @ResponseBody
    public Map<String,Object> getLogList(Integer page,Integer rows,
                                         @RequestParam(value = "content",required = false,defaultValue = "")String content
                                         ) {
        Map<String,Object> result = new HashMap<>(0);
        List<Log> logByContent = logService.getLogByContent(content);
        PageHelper.startPage(page,rows);
        PageInfo<Log> logPageInfo = new PageInfo<>(logByContent);
        result.put("rows",logPageInfo.getList());
        result.put("total",logPageInfo.getTotal());
        return result;
    }

    /**
     * 添加日志
     * @param log
     * @return
     */
    @RequestMapping(value = "/add",method = RequestMethod.POST)
    @ResponseBody
    public Map<String,Object> insertLog(Log log) {
        Map<String,Object> result = new HashMap<>(0);
        if(log == null) {
            result.put("success",false);
            result.put("msg","日志不能为空！");
            return result;
        }
        if(StringUtil.isEmpty(log.getContent())) {
            result.put("success",false);
            result.put("msg","日志内容不能为空！");
            return result;
        }
        log.setCreatetime(new Date());
        if(logService.insertLog(log) > 0) {
            result.put("success",false);
            result.put("msg","添加日志失败！");
            return result;
        }
        result.put("success",true);
        result.put("msg","添加成功！");
        return result;
    }

    /**
     *
     * @return
     */
    @RequestMapping(value = "/delete",method = RequestMethod.POST)
    @ResponseBody
    public Map<String,String> deleteLog(String ids) {
        Map<String, String> result = new HashMap<>(0);
        if (ids == null) {
            result.put("type", "error");
            result.put("msg", "请选择要删除的数据！");
            return result;
        }
        if (ids.contains(",")) {
            ids = ids.substring(0, ids.length() - 1);
        }
        if (logService.deleteLog(ids) > 0) {
            result.put("type", "error");
            result.put("msg", "删除失败！");
            return result;
        }
        result.put("type","success");
        return result;
    }
}
