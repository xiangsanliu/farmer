package com.feidian.farmer.interceptor;

import com.alibaba.fastjson.JSON;
import com.feidian.farmer.dao.entity.User;
import com.feidian.farmer.share.Cons;
import com.feidian.farmer.share.ResponseBean;
import org.springframework.stereotype.Component;
import org.springframework.web.servlet.HandlerInterceptor;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;

@Component
public class LoginInterceptor implements HandlerInterceptor {

    @Override
    public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler) throws Exception {
        HttpSession session = request.getSession();
        User user = (User) session.getAttribute(Cons.USER);
        if (user == null) {
            // 未登录
            sendError(response, ResponseBean.auth());
            return false;
        } else if (!permitted(request.getRequestURI(), user)) {
            // 无权限
            sendError(response, ResponseBean.forbid());
            return false;
        } else {
            return true;
        }
    }

    private void sendError(HttpServletResponse response, ResponseBean responseBean) throws IOException {
        response.getWriter().write(JSON.toJSONString(responseBean));
    }

    /**
     * 匹配权限
     *
     * @param url  当前url
     * @param user 当前用户
     * @return bool
     */
    private boolean permitted(String url, User user) {
        for (String item : user.getPermissions()) {
            if (url.equals(item)) {
                return true;
            }
        }
        return false;
    }

}
