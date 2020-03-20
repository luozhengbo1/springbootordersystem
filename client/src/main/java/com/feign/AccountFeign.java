package com.feign;

import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;

@FeignClient(value = "account")
public interface AccountFeign {
    @GetMapping("/account/login/{user}/{pwd}/{type}")
    public Object login(@PathVariable("user") String user,@PathVariable("pwd") String pwd, @PathVariable("type") String type);

}
