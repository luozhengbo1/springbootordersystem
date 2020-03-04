package com.repository;

import com.entity.Menu;
import org.apache.ibatis.annotations.Mapper;

import java.util.List;


@Mapper
public interface MenuRepository {

    public List<Menu> findAll(int index , int limit);
    public int count();
    public Menu findById(long id);
    public void update(Menu menu);
    public void deleteById(long id);
    public int save(Menu menu);

}
