package com.repository;

import com.entity.User;
import org.apache.ibatis.annotations.Mapper;

import java.util.List;

@Mapper
public interface UserRepository {
    public List<User> findAll(int index,int limit);
    public User findById(int id);
    public Integer count();
    public void save(User user);
    public void update(User user);
    public  void deleteById(int id);
}
