package com.repository;

import com.entity.Type;
import org.apache.ibatis.annotations.Mapper;

import java.util.List;

@Mapper
public interface TypeRepository {
    public Type findById(long id);

    List<Type> findTypes();
}
