package com.controller;

import com.entity.Menu;
import com.entity.MenuVO;
import com.entity.Type;
import com.repository.MenuRepository;
import com.repository.TypeRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.web.bind.annotation.*;

import java.util.List;


@RestController
@RequestMapping("/menu")
public class MenuHandler {
    @Value("${server.port}")
    private String port;

    //注入
    @Autowired
    private MenuRepository menuRepository;
    @Autowired
    private TypeRepository typeRepository;

    @GetMapping("/index")
    public String index(){
        return this.port;
    }


    @GetMapping("/findAll/{index}/{limit}")
    public MenuVO findAll(@PathVariable("index") int index, @PathVariable("limit") int limit){
        MenuVO menuVO = new MenuVO(0, "", menuRepository.count(), menuRepository.findAll(index, limit));
        return menuVO;

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

    @DeleteMapping("/deleteById/{id}")
    public void deleteById(@PathVariable("id") long id){
        menuRepository.deleteById(id);
    }

    @GetMapping("/findTypes")
    public List<Type> findTypes(){
        return typeRepository.findTypes();
    }

    @PostMapping("/save")
    //注意要加注解 不然收到的参数是json
    public void save(@RequestBody  Menu menu){
        menuRepository.save(menu);
    }

    @PutMapping("/update")
    public void  update(@RequestBody Menu menu){
        menuRepository.update(menu);
    }

}
