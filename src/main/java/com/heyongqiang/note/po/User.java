package com.heyongqiang.note.po;


import cn.hutool.json.JSONUtil;
import javafx.scene.chart.PieChart;
import lombok.Getter;
import lombok.Setter;

import java.sql.Timestamp;

//lombook的插件
@Getter
@Setter
public class User {
    private Integer userId;//用户id
    private String userName;//用户名称
    private String userPwd;//用户密码
    private String userTelephone;//用户电话
    private String userHead;//用户头像
    private String userLabel;//用户标签
    private String userContent;//用户简介
    private String userRealName;//用户真实姓名
    private Integer userSex;//用户性别
    private String userBirthday;//生日
    private String userEmail;//用户邮箱
    private String userLargestStudy;//最高学历
    private Integer userRole;//用户角色
}
