package com.fallback;

import com.entity.Order;
import com.entity.OrderVO;
import com.feign.OrderFeign;
import feign.hystrix.FallbackFactory;

public class OrderFeignFallback implements FallbackFactory<OrderFeign> {

    @Override
    public OrderFeign create(Throwable throwable) {
        return new OrderFeign() {
            @Override
            public void save(Order order) {
                System.out.println("进入回调。。。。");
            }

            @Override
            public int count(int uid) {
                System.out.println("进入回调。。。。");
                return -1;
            }

            @Override
            public OrderVO findByUIdOrder(int page, int limit, int uid) {
                System.out.println("进入回调。。。。");
                return null;
            }

            @Override
            public OrderVO findAll(int index, int limit) {
                return null;
            }

            @Override
            public void updateState(int id) {

            }
        };
    }
}
