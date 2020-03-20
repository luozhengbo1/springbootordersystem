package com.feign;

import com.entity.Order;
import com.entity.OrderVO;
import com.fallback.OrderFeignFallback;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.*;

@FeignClient(value = "order",fallback = OrderFeignFallback.class)
public interface OrderFeign {

    @PostMapping("/order/save")
    public void save(@RequestBody Order order);

    //调用
    @GetMapping("/order/count")
    int count(int uid);
    @GetMapping("/order/findByUIdOrder/{page}/{limit}/{uid}")
    OrderVO findByUIdOrder(@PathVariable("page") int page, @PathVariable("limit") int limit, @PathVariable("uid") int uid);

    @GetMapping("/order/findAll/{index}/{limit}")
    OrderVO findAll(@PathVariable("index") int index,@PathVariable("limit") int limit);

    @PutMapping("/order/updateState/{id}")
    void updateState(@PathVariable("id") int id);

}
