package com.learn.pojo;



import com.baomidou.mybatisplus.annotation.TableName;
import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;
@Data
@NoArgsConstructor
@AllArgsConstructor
@TableName("user")
public class User {
    private Integer id;//主键ID
    private String username;//用户名
    @JsonIgnore//让springmvc把当前对象转换成json字符串的时候忽略掉password，最终的json字符串中就没有password这个属性了
    private String password;//密码
    private LocalDateTime createTime;//创建时间
    private LocalDateTime updateTime;//更新时间
}
