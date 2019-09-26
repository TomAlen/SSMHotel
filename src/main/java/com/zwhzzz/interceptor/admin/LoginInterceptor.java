package com.zwhzzz.interceptor.admin;

import com.github.pagehelper.util.StringUtil;
import com.zwhzzz.Pojo.Menu;
import com.zwhzzz.Pojo.User;
import com.zwhzzz.Util.MenuUtil;
import net.sf.json.JSONObject;
import org.springframework.web.servlet.HandlerInterceptor;
import org.springframework.web.servlet.ModelAndView;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * 后台登录拦截器
 */
public class LoginInterceptor implements HandlerInterceptor {

    @Override
    public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object o) throws Exception {
        String requestURI = request.getRequestURI();
        User admin = (User)request.getSession().getAttribute("admin");
        if(admin == null) {
            System.out.println("链接" + requestURI + "进入拦截器！");
            //表示未登录或者登录失效！
            String header = request.getHeader("X-Requested-With");
            //判断是否是ajax请求
            if("XMLHttpRequest".equals(header)) {
                //表示式ajax请求
                Map<String,Object> result = new HashMap<>(0);
                result.put("success",false);
                result.put("msg","登录会话超时，请重新登录！");
                //把信息转为json格式，在写入response中
                response.getWriter().write(JSONObject.fromObject(result).toString());
            }
            //表示链接跳转，直接重定向到登录页面。
            response.sendRedirect(request.getServletContext().getContextPath() + "/system/login");
            return false;
        }
        String mid = request.getParameter("_mid");
        if(! StringUtil.isEmpty(mid)) {
            List<Menu> thirdMenu = MenuUtil.getThirdMenu((List<Menu>) request.getSession().getAttribute("userMenus"), Integer.valueOf(mid));
            request.setAttribute("thirdMenu", thirdMenu);
        }
        return true;
    }

    @Override
    public void postHandle(HttpServletRequest httpServletRequest, HttpServletResponse httpServletResponse, Object o, ModelAndView modelAndView) throws Exception {

    }

    @Override
    public void afterCompletion(HttpServletRequest httpServletRequest, HttpServletResponse httpServletResponse, Object o, Exception e) throws Exception {

    }
}
