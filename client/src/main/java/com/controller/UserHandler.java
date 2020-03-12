package com.controller;

import com.entity.User;
import com.entity.UserVO;
import com.feign.UserFeign;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.ModelAndView;

import javax.websocket.server.PathParam;
import java.util.Date;
import java.util.List;

//@RestController
@Controller //视图
@RequestMapping("/user")
public class UserHandler {

    @Autowired
    private UserFeign userFeign;

    @GetMapping("/findAll")
    @ResponseBody //返回数据 注意参数 绑定
    public UserVO findAll(@PathParam("page") int page, @PathParam("limit") int limit){
        int    index = (page - 1) * limit;
        UserVO userVO = new UserVO();
        userVO.setCode(0);
        userVO.setMsg("");
        userVO.setCount(userFeign.count());
        userVO.setData(userFeign.findAll(index,limit));
        return userVO;
    }


    @GetMapping("/count")
    public Integer count(){
        return userFeign.count();
    }

    @GetMapping("/redirect/{location}")
    public String redirect(@PathVariable("location")  String location){
        return location;
    }

    @GetMapping("/deleteById/{id}") //这里是一个get 请求
    public String deleteById(@PathVariable("id") long id){
        userFeign.deleteById(id);
        return "redirect:/user/redirect/user_manage";
    }
    @PostMapping("/save")
    //这里拿到这menu 数据 以json 格式传到menu 工程
    //@RequestBody  这里不应该加这个不是一个json 对象
    public String save( User user){
        user.setRegisterdate(new Date());//设置当前时间
        userFeign.save(user);
        return "redirect:/user/redirect/user_manage";
    }
    @GetMapping("/findById/{id}")
    public ModelAndView findById(@PathVariable("id") long id){
        ModelAndView modelAndView = new ModelAndView();
        modelAndView.setViewName("user_update");
        modelAndView.addObject("user",userFeign.findById(id));
        //知道所有的分类才可以选
        return  modelAndView;
    }

}

