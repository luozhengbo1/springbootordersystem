package com.controller;

import com.entity.Menu;
import com.entity.MenuVO;
import com.repository.MenuRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("/menu")
public class MenuHandler {
    @Value("${server.port}")
    private String port;

    //注入
    @Autowired
    private MenuRepository menuRepository;

    @GetMapping("/index")
    public String index(){
        return this.port;
    }


    @GetMapping("/findAll/{index}/{limit}")
    public MenuVO findAll(@PathVariable("index") int index, @PathVariable("limit") int limit){
        return new MenuVO(0, "", 100, menuRepository.findAll(index, limit));

    }
    @GetMapping("/count")
    public Integer count(){
        Integer count = menuRepository.count();
        return count;
    }
    @GetMapping("/findById/{id}")
    public Menu findById(@PathVariable("id") long id){
        Menu menu = menuRepository.findById(id);
        return menu;
    }
}
