package com.feign;


import com.entity.User;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.*;

import java.util.List;

//关联服务提供者
@FeignClient(value = "user")
public interface UserFeign {

    @GetMapping("/user/findAll/{index}/{limit}")
    public List<User> findAll(@PathVariable("index") int index, @PathVariable("limit") int limit);

    @GetMapping("/user/count")
    public Integer count();

    //删除
    @GetMapping("/user/deleteById/{id}")
    public void deleteById(@PathVariable("id") long id);


    @PostMapping("/user/save")
    public void save(User user);

    //get mapping 是去
    @GetMapping("/user/findById/{id}")
    public User findById(@PathVariable("id") long id);

    @PutMapping("/user/update")
    public void update(User user);
}
