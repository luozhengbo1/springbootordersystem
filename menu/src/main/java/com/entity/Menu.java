package com.entity;

import lombok.Data;

@Data
public class Menu {
    private int id;
    private String name;
    private Double price;
    private String flavor;
//    private int tid;
    //使用关联查询
    private Type type;
}
