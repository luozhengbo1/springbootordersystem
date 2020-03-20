package com.repository;

import com.entity.Order;
import com.entity.OrderVO;
import org.apache.ibatis.annotations.Mapper;

import java.util.List;

@Mapper
public interface OrderRepository {
    public int save(Order order);
    public List<Order> findAll(int page, int limit);
    public int countByUid(int uid);
    public  List<Order> findByUIdOrder(int page, int limit, int uid);
}
