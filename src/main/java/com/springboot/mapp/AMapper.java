package com.springboot.mapp;

import com.springboot.enty.A;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Select;

@Mapper
public interface AMapper extends com.meditrusthealth.fast.common.page.Pagingable<A, A> {
    int deleteByPrimaryKey(Integer id);

    int insert(A record);

    int insertSelective(A record);

    A selectByPrimaryKey(Integer id);

    int updateByPrimaryKeySelective(A record);

    int updateByPrimaryKey(A record);
}