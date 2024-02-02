package com.learn;

import com.alibaba.excel.EasyExcel;
import com.alibaba.excel.read.listener.PageReadListener;
import com.learn.mapper.EmpMapper;
import com.learn.pojo.Emp;
import com.learn.pojo.Grade;
import com.learn.service.EmpService;
import org.apache.poi.openxml4j.exceptions.InvalidFormatException;
import org.apache.poi.ss.usermodel.*;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
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

    }

    @Test
    void upload() throws IOException, InvalidFormatException {
        boolean notNull = false;
        List<Emp> empList = new ArrayList<Emp>();
        String fileName="C:\\Users\\24371\\Desktop\\my_project\\referencecount\\src\\main\\resources\\绩效.xlsx";
        if (!fileName.matches("^.+\\.(?i)(xls)$") && !fileName.matches("^.+\\.(?i)(xlsx)$")) {
            return;
        }
        FileInputStream input=new FileInputStream(fileName);
        Workbook workbook= WorkbookFactory.create(input);
        Sheet sheet= workbook.getSheetAt(0);
        for (int i = 1; i <= sheet.getLastRowNum(); i++) {
            //当前行
            Row row = sheet.getRow(i);
            //统计表格数据存入集
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
            int month=(int)row.getCell(13).getNumericCellValue();

            empList.add(new Emp(i,name,depart,quality,qualityP,submit,submitP,
                    num,numP,culture,cultureP,ad,adP,extra,month));
        }
        for (Emp e : empList) {
            System.out.println(e);
        }
    }

}
