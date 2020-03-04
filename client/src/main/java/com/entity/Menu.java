package com.entity;

import lombok.Data;

@Data
public class Menu {
    private int id;
    private String name;
    private Double price;
    private String flavor;
    private Type type;
}
