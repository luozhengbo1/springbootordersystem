package com.controller;

import com.entity.User;
import com.feign.UserFeign;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.ModelAndView;

import javax.websocket.server.PathParam;
import java.util.List;

//@RestController
@Controller //视图
@RequestMapping("/user")
public class UserHandler {

    @Autowired
    private UserFeign userFeign;

//    @GetMapping("/findAll")
//    @ResponseBody //返回数据 注意参数 绑定
//    public List<User> findAll(@PathParam("page") int page, @PathParam("limit") int limit){
//        int    index = (page - 1) * limit;
//        return userFeign.findAll(index, limit);
//    }
    @GetMapping("/findAll")
    public ModelAndView findAll(@PathParam("page") int page, @PathParam("limit") int limit){
        int    index = (page - 1) * limit;
        ModelAndView modelAndView = new ModelAndView();
        modelAndView.setViewName("user_manage");
        modelAndView.addObject("user",userFeign.findAll(index,limit));
        //知道所有的分类才可以选
        return  modelAndView;
    }
    @GetMapping("/count")
    public Integer count(){
        return userFeign.count();
    }

        @GetMapping("/redirect/{location}")
    public String redirect(@PathVariable("location")  String location){
        return location;
    }

    @GetMapping("/deleteById/{id}")
    public String deleteById(@PathVariable("id") long id){
        userFeign.deleteById(id);
        return "redirect:/user/redirect/index";
    }
    @PostMapping("/save")
    //这里拿到这menu 数据 以json 格式传到menu 工程
    public String save(User user){
        userFeign.save(user);
        return "redirect:/user/redirect/index";
    }
    @GetMapping("/findById/{id}")
    public ModelAndView findById(@PathVariable("id") long id){
        ModelAndView modelAndView = new ModelAndView();
        modelAndView.setViewName("user_update");
        modelAndView.addObject("user",userFeign.findById(id));
        //知道所有的分类才可以选
        return  modelAndView;
    }


//    @PutMapping("/update")
//    注意这里是post 请求 用postmapping
    @PostMapping("/update")
    public String update(User user){
        userFeign.update(user);
        return "redirect:/user/redirect/index";
    }

}

