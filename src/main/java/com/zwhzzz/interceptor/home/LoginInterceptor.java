package com.zwhzzz.interceptor.home;

import com.zwhzzz.Pojo.Account;
import net.sf.json.JSONObject;
import org.springframework.web.servlet.HandlerInterceptor;
import org.springframework.web.servlet.ModelAndView;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.util.HashMap;
import java.util.Map;

/**
 * @author alen zhong
 * @date 19-9-29
 */
public class LoginInterceptor implements HandlerInterceptor {

    @Override
    public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object o) throws Exception {
        String requestURI = request.getRequestURI();
        Account account = (Account) request.getSession().getAttribute("account");
        if(account == null) {
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
            response.sendRedirect(request.getServletContext().getContextPath() + "/home/login");
            return false;
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
