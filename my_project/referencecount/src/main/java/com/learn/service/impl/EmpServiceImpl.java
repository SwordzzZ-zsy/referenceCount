package com.learn.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.learn.mapper.EmpMapper;
import com.learn.pojo.Emp;
import com.learn.pojo.Grade;
import com.learn.service.EmpService;
import lombok.Data;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.*;

@Service
public class EmpServiceImpl extends ServiceImpl<EmpMapper,Emp> implements EmpService {

    @Autowired
    private EmpMapper empMapper;

    @Override
    public List<Grade> getAverage() {
        List<Grade> grades = new ArrayList<>();
        long len = this.count();
        for (int i = 1; i <= len; i++) {
            Emp emp = empMapper.findById(i);
            grades.add(new Grade(emp.getId(), emp.getName(),emp.getDepart(), average(emp), emp.getMonth()));
        }
        return grades;
    }

    @Override
    public List<Grade> sort(List<Grade> empList) {
        Collections.sort(empList, Comparator.comparingDouble(Grade::getAverage).reversed());
        return empList;
    }

    @Override
    public List<Grade> deptlist(List<Grade> empList,String month) {
        List<Grade> depts = new ArrayList<>();
        //获取所有月份
        List<String> months = empMapper.selectMonths();
        for (String m : months) {
            if (month != null && !month.equals(m)) {
                continue; // 如果指定了月份且当前月份不匹配，则跳过该月份的数据处理
            }
            //前端分
            double fronts = 0;
            int a = 0;
            //服务端分
            double serves = 0;
            int b = 0;
            //大数据分
            double datas = 0;
            int c = 0;
            //测试分
            double tests = 0;
            int d = 0;
            //研发组长分
            double leaders = 0;
            int e = 0;
            //运维分
            double operations = 0;
            int f = 0;

            //查询该月份的所有员工数据
            List<Emp> emps = empMapper.findByMonth(m);
            for (Emp emp : emps) {
                if (emp.getDepart().equals("前端")) {
                    fronts += empList.get(emp.getId() - 1).getAverage();
                    a++;
                }
                if (emp.getDepart().equals("服务端")) {
                    serves += empList.get(emp.getId() - 1).getAverage();
                    b++;
                }
                if (emp.getDepart().equals("大数据")) {
                    datas += empList.get(emp.getId() - 1).getAverage();
                    c++;
                }
                if (emp.getDepart().equals("测试")) {
                    tests += empList.get(emp.getId() - 1).getAverage();
                    d++;
                }
                if (emp.getDepart().equals("研发组长")) {
                    leaders += empList.get(emp.getId() - 1).getAverage();
                    e++;
                }
                if (emp.getDepart().equals("运维")) {
                    operations += empList.get(emp.getId() - 1).getAverage();
                    f++;
                }
            }

            //计算每个部门该月份的平均分
            if (a != 0) {
                double frontAvg = fronts / a;
                depts.add(new Grade( 1,"前端", null,frontAvg,m));
            }
            if (b != 0) {
                double serveAvg = serves / b;
                depts.add(new Grade(2, "服务端", null, serveAvg,m));
            }
            if (c != 0) {
                double dataAvg = datas / c;
                depts.add(new Grade(3, "大数据", null, dataAvg,m));
            }
            if (d != 0) {
                double testAvg = tests / d;
                depts.add(new Grade(4, "测试", null, testAvg,m));
            }
            if (e != 0) {
                double leaderAvg = leaders / e;
                depts.add(new Grade(5, "研发组长", null, leaderAvg,m));
            }
            if (f != 0) {
                double operationAvg = operations / f;
                depts.add(new Grade(6, "运维", null, operationAvg,m));
            }
        }
        return depts;
    }




    @Override
    public List<Grade> NameList(String name,String month) {
        // 根据名字进行模糊查询
        QueryWrapper<Emp> queryWrapper = new QueryWrapper<>();
        if (name != null ) {
            // 根据名字进行模糊查询
            queryWrapper.like("name", name);
        }
        if (month != null ) {
            queryWrapper.eq("month", month);
        }
        if (name != null && month != null ) {
            // 根据名字和月份进行查询
            queryWrapper.like("name", name).eq("month", month);
        }
        //将根据名字模糊查询的结果存储到empList
        List<Emp> empList = baseMapper.selectList(queryWrapper);
        List<Grade> nameList = new ArrayList<>();
        int count = empList.size();
        for (int i = 0; i < count; i++) {
            Emp emp = empList.get(i);
            // 将grade对象添加到列表中
            nameList.add(new Grade(i+1,emp.getName(),emp.getDepart(),average(emp),emp.getMonth()));
        }
        return nameList;
    }

    public double average(Emp emp){
         double aver = emp.getQuality() * emp.getQualityP() + emp.getCulture() * emp.getCultureP()
                + emp.getSubmit() * emp.getSubmitP() + emp.getNum() * emp.getNumP()
                + emp.getAd() * emp.getAdP() + emp.getExtra();
         return aver;
    }
}

