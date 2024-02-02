package com.learn.service.impl;

import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.learn.mapper.EmpMapper;
import com.learn.pojo.Emp;

import com.learn.service.UploadService;

import org.apache.poi.ss.usermodel.*;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.io.InputStream;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

@Service
public class UploadServiceImpl extends ServiceImpl<EmpMapper, Emp> implements UploadService {
    @Autowired
    private EmpMapper empMapper;

    @Override
    public boolean batchImport(String fileName, MultipartFile file) throws IOException {
        boolean notNull = false;
        List<Emp> empList = new ArrayList<Emp>();
        if (!fileName.matches("^.+\\.(?i)(xls)$") && !fileName.matches("^.+\\.(?i)(xlsx)$")) {
            return notNull;
        }
        InputStream is=file.getInputStream();
        Workbook workbook= new XSSFWorkbook(is);
        Sheet sheet= workbook.getSheetAt(0);
        if(sheet!=null)
            notNull=true;
        for (int i = 1; i <= sheet.getLastRowNum(); i++) {
            //创建对象存储
            //当前行
            Row row = sheet.getRow(i);
            //统计表格数据存入集合
            String name=row.getCell(0).getStringCellValue();
            String depart=row.getCell(1).getStringCellValue();
            double quality=row.getCell(2).getNumericCellValue();
            double qualityP=row.getCell(3).getNumericCellValue();
            double submit=row.getCell(4).getNumericCellValue();
            double submitP=row.getCell(5).getNumericCellValue();
            double num=row.getCell(6).getNumericCellValue();
            double numP=row.getCell(7).getNumericCellValue();
            double culture=row.getCell(8).getNumericCellValue();
            double cultureP=row.getCell(9).getNumericCellValue();
            double ad=row.getCell(10).getNumericCellValue();
            double adP=row.getCell(11).getNumericCellValue();
            double extra=0;
            if(row.getCell(12)!=null)
                extra=row.getCell(12).getNumericCellValue();
            String month=row.getCell(13).getStringCellValue();

            empList.add(new Emp(i,name,depart,quality,qualityP,submit,submitP,
                    num,numP,culture,cultureP,ad,adP,extra,month));
        }

        for (Emp e : empList) {
            String name=e.getName();
            String month=e.getMonth();
            int cnt=empMapper.selectByNameAndMonth(name,month);
            if(cnt==0){
                empMapper.addEmp(e);
            }
            else {
                empMapper.updateEmpByNameAndMonth(e);
                System.out.println(cnt);
            }
        }
        return notNull;
    }
}
