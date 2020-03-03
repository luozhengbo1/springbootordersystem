package com.controller;

import com.entity.Menu;
import com.feign.MenuFeign;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import javax.websocket.server.PathParam;
import java.util.List;

//@RestController
@Controller //视图
@RequestMapping("/client")
public class ClientHandler {

    @Autowired
    private MenuFeign menuFegin;

    @GetMapping("/findAll")
    @ResponseBody //返回数据 注意参数 绑定
    public List<Menu> findAll(@PathParam("page") int page, @PathParam("limit") int limit){
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


}

