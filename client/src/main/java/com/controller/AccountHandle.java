package com.controller;

import com.entity.Admin;
import com.entity.User;
import com.feign.AccountFeign;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import javax.servlet.http.HttpSession;
import javax.websocket.server.PathParam;
import java.util.LinkedHashMap;

@Controller
@RequestMapping("/account")
public class AccountHandle {
    @Autowired
    AccountFeign accountFeign;

    @PostMapping("/login")
    public String login(@PathParam("user") String user, @PathParam("pwd") String pwd, @PathParam("type") String type, HttpSession session){
        Object object = accountFeign.login(user,pwd,type);
        //注意拿到的是hashmap 格式
        LinkedHashMap<String , Object> hashMap = (LinkedHashMap)object;
        String result = null;
        if(object== null){
            result = "login";
        }else{
            switch (type){
                case "user":
                    User user1 = new User();
                    Integer idStr =  (Integer)hashMap.get("id");
                    user1.setId(idStr);
                    String nickname=(String)hashMap.get("nickname");
                    user1.setNickname(nickname);
                    session.setAttribute("user",user1);
                    result = "index";
                    break;
                case "admin":
                    Admin admin = new Admin();
                    Integer id = (Integer)hashMap.get("id");
                    String username = (String)hashMap.get("username");
                    admin.setId(id);
                    admin.setUsername(username);
                    session.setAttribute("admin",admin);
                    result = "main";
                    break;
            }
        }

        return result;
    }

    @GetMapping("/logout")
    public String logout(HttpSession session){
        //清楚session
        session.invalidate();
        return "redirect:/login.html";
    }
}
