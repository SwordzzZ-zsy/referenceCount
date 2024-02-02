package com.learn.pojo;

import com.alibaba.excel.annotation.ExcelProperty;
import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableField;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.Date;

@Data
@NoArgsConstructor
@AllArgsConstructor
@TableName(value = "employee")
public class Emp {
    private int id;
    @TableField(value = "name")
    private String name;
    @TableField(value = "depart")
    private String depart;
    @TableField(value = "quality")
    private double quality;
    @TableField(value = "quality_p")
    private double qualityP;
    @TableField(value = "submit")
    private double submit;
    @TableField(value = "submit_p")
    private double submitP;
    @TableField(value = "num")
    private double num;
    @TableField(value = "num_p")
    private double numP;
    @TableField(value = "culture")
    private double culture;
    @TableField(value = "culture_p")
    private double cultureP;
    @TableField(value = "ad")
    private double ad;
    @TableField(value = "ad_p")
    private double adP;
    @TableField(value = "extra")
    private double extra;
    @TableField(value = "month")
    private String month;
}
