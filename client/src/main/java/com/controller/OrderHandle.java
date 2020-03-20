package com.controller;

import com.entity.Menu;
import com.entity.Order;
import com.entity.OrderVO;
import com.entity.User;
import com.feign.OrderFeign;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpSession;
import javax.websocket.server.PathParam;

@Controller
@RequestMapping("/order")
public class OrderHandle {

    @Autowired
    OrderFeign orderFeign;

    @GetMapping("/save/{mid}")
    public String save(@PathVariable("mid") int mid, HttpSession session){
        Order order = new Order();
        User user= (User)session.getAttribute("user");
        order.setUser(user);
        Menu menu = new Menu();
        menu.setId(mid);
        order.setMenu(menu);
        orderFeign.save(order);
        return "order";
    }

    @GetMapping("/count/{uid}")
    public int count(@PathVariable("uid") int uid){
      return   orderFeign.count(uid);
    }

    //目的查询除某一个人点的所有餐需要做分页查询

    @GetMapping("/findByUIdOrder")
    @ResponseBody
    public OrderVO findByUIdOrder(@PathParam("page") int page, @PathParam("limit") int limit,HttpSession session ){
        User user = (User) session.getAttribute("user");
        int index  = (page-1)*limit;
        return orderFeign.findByUIdOrder(index,limit, (int) user.getId());
    }

    @GetMapping("/findAll")
    //这里和layui 框架交流反数据
    @ResponseBody
    public OrderVO findAll(@PathParam("page") int page,@PathParam("limit") int limit ){
        int index  = (page-1)*limit;
        return orderFeign.findAll(index,limit);
    }

    //这里直接跟前端对接不能用put
    @GetMapping("/updateState/{id}")
    public String updateState(@PathVariable("id") int id){
        orderFeign.updateState(id);
        return "redirect:/menu/redirect/order_handler";
    }

}
