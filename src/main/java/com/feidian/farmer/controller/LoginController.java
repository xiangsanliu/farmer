package com.feidian.farmer.controller;

import com.alibaba.fastjson.JSONObject;
import com.feidian.farmer.dao.entity.User;
import com.feidian.farmer.service.UserService;
import com.feidian.farmer.share.Cons;
import com.feidian.farmer.share.ResponseBean;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import javax.servlet.http.HttpSession;

@RestController
public class LoginController {

    private final UserService userService;

    @Autowired
    public LoginController(UserService userService) {
        this.userService = userService;
    }

    @RequestMapping(value = "/login", method = RequestMethod.POST)
    public ResponseBean login(@RequestBody String data, HttpSession session) {
        JSONObject jsonObject = JSONObject.parseObject(data);
        String username = jsonObject.getString("username");
        String password = jsonObject.getString("password");
        if ("guest".equals(username)) {
            session.setAttribute(Cons.USER, username);
            return ResponseBean.ok("success");
        }

        User user = userService.getOne(username);

        if (user == null) {
            return ResponseBean.error("该用户不存在！");
        }
        if (user.getPassword().equals(password)) {
            session.setAttribute(Cons.USER, user);
            return ResponseBean.ok("success");
        }

        return ResponseBean.error("密码错误");
    }


    @RequestMapping(value = "/initUser", method = RequestMethod.GET)
    public ResponseBean initUser(HttpSession session) {
        User user = (User) session.getAttribute(Cons.USER);
        JSONObject obj = new JSONObject();
        obj.put("username", user.getUsername());
        obj.put("userType", user.getUserType());
        return ResponseBean.ok("success", obj);
    }

    @RequestMapping(value = "/logout")
    public ResponseBean logout(HttpSession session) {
        session.removeAttribute(Cons.USER);
        return ResponseBean.ok();
    }

}
