package com.controller;

import com.entity.Order;
import com.entity.OrderVO;
import com.repository.OrderRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import java.util.Date;


@RestController
@RequestMapping("/order")
public class OrderHandler {

    @Autowired
    private   OrderRepository orderRepository;

    @PostMapping("/save")
    public  void save(@RequestBody Order order){
        order.setDate(new Date());
        orderRepository.save(order);
    }

    @GetMapping("/count/{uid}")
    @ResponseBody
    public int count(@PathVariable("uid") int uid){
        return orderRepository.countByUid(uid);
    }

    @GetMapping("/findByUIdOrder/{page}/{limit}/{uid}")
    @ResponseBody
    public OrderVO findByUIdOrder(@PathVariable("page") int page, @PathVariable("limit") int limit, @PathVariable("uid")  int uid){
        OrderVO orderVO = new OrderVO();
        orderVO.setMsg("");
        orderVO.setData(orderRepository.findByUIdOrder(page,limit,uid));
        orderVO.setCode(0);
        orderVO.setCount(orderRepository.countByUid(uid));
        return orderVO;
    }

}
