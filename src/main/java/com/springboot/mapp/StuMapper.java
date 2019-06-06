package com.springboot.mapp;

import com.springboot.enty.Stu;
import org.apache.ibatis.annotations.Mapper;

@Mapper
public interface StuMapper extends com.meditrusthealth.fast.common.page.Pagingable<Stu, Stu> {
    int deleteByPrimaryKey(Integer id);

    int insert(Stu record);

    int insertSelective(Stu record);

    Stu selectByPrimaryKey(Integer id);

    int updateByPrimaryKeySelective(Stu record);

    int updateByPrimaryKey(Stu record);
}