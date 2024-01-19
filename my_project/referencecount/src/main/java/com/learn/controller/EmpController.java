package com.learn.controller;

import com.learn.pojo.Grade;
import com.learn.pojo.Result;
import com.learn.service.EmpService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.io.IOException;
import java.util.List;

@RestController
@RequestMapping("/show")
public class EmpController {

    @Autowired
    private EmpService empService;

    @GetMapping("/emp")
    public Result listByEmp() {
        //使用service查询员工
        List<Grade> empList = empService.getAverage();
        return Result.success(empList);
    }

    @GetMapping("/sort")
    public Result sortEmp() throws IOException {
        //将员工表按平均分进行排序
        List<Grade> empList = empService.getAverage();
        empService.sort(empList);
        return Result.success(empList);
    }

    @GetMapping("/dept")
    public Result listByFronts()  {
        //使用service查询员工
        List<Grade> empList = empService.getAverage();
        List<Grade> deptList=empService.deptlist(empList);
        return Result.success(deptList);
    }

    @GetMapping("/search")
    public Result searchByName(@RequestParam(required = false) String name){
        List<Grade> nameList= empService.NameList(name);
        return Result.success(nameList);
    }
}
