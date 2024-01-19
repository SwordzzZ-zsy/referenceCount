package com.learn;

import com.alibaba.excel.EasyExcel;
import com.alibaba.excel.read.listener.PageReadListener;
import com.learn.mapper.EmpMapper;
import com.learn.pojo.Emp;
import com.learn.pojo.Grade;
import com.learn.service.EmpService;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import java.util.ArrayList;
import java.util.List;

@SpringBootTest
class ReferencecountApplicationTests {
    @Autowired
    private EmpService empService;

    @Autowired
    private EmpMapper empMapper;

    //@Test
    void contextLoads() {
        List<Grade> grades = new ArrayList<>();
        long len=empService.count();
        for (int i=0;i<len;i++){
            Emp emp=empMapper.selectById(i);
            double average=emp.getQuality()* emp.getCulture_p()+emp.getSubmit()*emp.getSubmit_p()
                    +emp.getNum()*emp.getNum_p()+emp.getCulture()*emp.getCulture_p()+emp.getAd()*emp.getAd_p()
                    +emp.getExtra();
            grades.add(new Grade(emp.getEmpId(),emp.getName(),average));
            for (Grade grade :grades) {
                System.out.println(grade);
            }
        }
    }

    //@Test
    void upload(){
        String fileName="C:\\Users\\24371\\Desktop\\my_project\\referencecount\\src\\main\\resources\\绩效.xlsx";
        EasyExcel.read(fileName,Emp.class,new PageReadListener<Emp>(empList -> {
            empService.saveBatch(empList);
        })).sheet().doRead();
    }

}
