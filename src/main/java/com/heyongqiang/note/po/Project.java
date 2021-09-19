package com.heyongqiang.note.po;


import lombok.Getter;
import lombok.Setter;

import javax.xml.crypto.Data;
import java.sql.Time;
import java.sql.Timestamp;

@Getter
@Setter
public class Project {
    private Integer projectId;//项目id
    private Integer userId;//用户id
    private String projectName;//项目名称
    private String projectType;//项目类型
    private String projectImg;//项目封面
    private String projectHead;//项目简介
    private String projectContent;//项目主体
    private Integer visitNumber;//项目成员数量
    private String province;//项目省份
    private String createTime;//项目创建时间
}
