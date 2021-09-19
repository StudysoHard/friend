package com.heyongqiang.note.po;


import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class ProjectRequest {
    private Integer requestId;//请求id
    private Integer projectId;//请求的项目id
    private String userName;//请求用户名字
    private String userLabel;//请求用户的标签
    private Integer userSex;//请求用户的性别
    private String userLargestStudy;//请求用户的最高学历
    private String userIntroduce;//请求的内容
    private Integer userId;//用户id
}
