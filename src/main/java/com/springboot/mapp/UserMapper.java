package com.springboot.mapp;

import com.springboot.enty.User;

import java.util.List;

import org.apache.ibatis.annotations.Mapper;

@Mapper
public interface UserMapper extends com.meditrusthealth.fast.common.page.Pagingable<User, User> {
    int insert(User record);

    int insertSelective(User record);
    
    
  
}