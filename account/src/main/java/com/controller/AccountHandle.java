package com.controller;

import com.repository.AdminRepository;
import com.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/account")
public class AccountHandle {

    @Autowired
   private AdminRepository adminRepository;
    @Autowired
    private UserRepository userRepository;

    @GetMapping("/login/{user}/{pwd}/{type}")
    public Object login(@PathVariable("user") String user, @PathVariable("pwd") String pwd , @PathVariable("type") String type){

        Object object=null;
        switch (type){
            case "admin":
                object = adminRepository.login(user,pwd);
                break;
            case "user":
                object = userRepository.login(user,pwd);
                break;
        }
        return object;
    }

}
