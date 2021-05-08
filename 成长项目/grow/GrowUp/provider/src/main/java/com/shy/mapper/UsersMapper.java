package com.shy.mapper;

import com.shy.model.Users;
import org.apache.ibatis.annotations.Mapper;

@Mapper
public interface UsersMapper {
    int insert(Users users);
    Users selectByPrimaryKey(String username);
    int deleteByPrimaryKey(String username);
    int updateByPrimaryKey(Users users);
}