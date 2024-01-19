package com.learn.service;

import com.baomidou.mybatisplus.extension.service.IService;
import com.learn.pojo.Emp;
import com.learn.pojo.Grade;

import java.util.List;

public interface EmpService extends IService<Emp> {
    List<Grade> getAverage();

    List<Grade> sort(List<Grade> empList);


    List<Grade> deptlist(List<Grade> empList);


    List<Grade> NameList(String name);
}
