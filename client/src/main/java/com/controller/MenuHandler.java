package com.controller;

import com.entity.Menu;
import com.entity.MenuVO;
import com.entity.Type;
import com.feign.MenuFeign;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.ModelAndView;

import javax.websocket.server.PathParam;
import java.util.List;

//@RestController
@Controller //视图
@RequestMapping("/menu")
public class MenuHandler {

    @Autowired
    private MenuFeign menuFegin;

    @GetMapping("/findAll")
    @ResponseBody //返回数据 注意参数 绑定
    public MenuVO findAll(@PathParam("page") int page, @PathParam("limit") int limit){
        int    index = (page - 1) * limit;
        return menuFegin.findAll(index, limit);
    }

    @GetMapping("/count")
    public Integer count(){
        return menuFegin.count();
    }

        @GetMapping("/redirect/{location}")
    public String redirect(@PathVariable("location")  String location){
        return location;
    }

    @GetMapping("/deleteById/{id}")
    public String deleteById(@PathVariable("id") long id){
        menuFegin.deleteById(id);
        return "redirect:/menu/redirect/index";
    }

    @GetMapping("/findTypes")
    public ModelAndView findTypes(){
        ModelAndView modelAndView = new ModelAndView();
        //设置页面
        modelAndView.setViewName("menu_add");
        //数据绑定
        modelAndView.addObject("list",menuFegin.findTypes());

        return modelAndView;
    }

    @PostMapping("/save")
    //这里拿到这menu 数据 以json 格式传到menu 工程
    public String save(Menu menu){
        menuFegin.save(menu);
        return "redirect:/menu/redirect/index";
    }
    @GetMapping("/findById/{id}")
    public ModelAndView findById(@PathVariable("id") long id){
        ModelAndView modelAndView = new ModelAndView();
        modelAndView.setViewName("menu_update");
        modelAndView.addObject("menu",menuFegin.findById(id));
        //知道所有的分类才可以选
        modelAndView.addObject("list",menuFegin.findTypes());
        return  modelAndView;
    }

//    @PutMapping("/update")
//    注意这里是post 请求 用postmapping
    @PostMapping("/update")
    public String update(Menu menu){
        menuFegin.update(menu);
        return "redirect:/menu/redirect/index";
    }

}

