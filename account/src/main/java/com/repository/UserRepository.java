package com.repository;

import com.entity.User;
import org.apache.ibatis.annotations.Mapper;

@Mapper
public interface UserRepository {
    public User login(String user,String pwd);

}
