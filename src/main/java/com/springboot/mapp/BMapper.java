package com.springboot.mapp;

import com.springboot.enty.B;
import org.apache.ibatis.annotations.Mapper;

@Mapper
public interface BMapper extends com.meditrusthealth.fast.common.page.Pagingable<B, B> {
    int deleteByPrimaryKey(Integer id);

    int insert(B record);

    int insertSelective(B record);

    B selectByPrimaryKey(Integer id);

    int updateByPrimaryKeySelective(B record);

    int updateByPrimaryKey(B record);
}