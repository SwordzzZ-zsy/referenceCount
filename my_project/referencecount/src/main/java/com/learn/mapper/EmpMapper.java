package com.learn.mapper;


import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.learn.pojo.Emp;

import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

import java.util.Date;
import java.util.List;

@Mapper
public interface EmpMapper extends BaseMapper<Emp> {

    Emp findById(@Param("id")int i);

    int selectByNameAndMonth(@Param("name") String name, @Param("month") String month);

    void addEmp(Emp e);


    int updateEmpByNameAndMonth(Emp e);


    List<String> selectMonths();

    List<Emp> findByMonth(String month);
}
