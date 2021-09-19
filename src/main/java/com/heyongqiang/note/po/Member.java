package com.heyongqiang.note.po;


import lombok.Getter;
import lombok.Setter;


@Getter
@Setter
public class Member {
    private Integer projectId;//项目id
    private Integer memberId;//成员id
    private Integer createUserId;//创建用户id
    private String userName;//用户名字
    private String memberLabel;//用户标签
    private Integer userSex;//用户性别
    private String userLargestStudy;//用户最高学历
    private Double memberLon;//用户经度
    private Double memberLat;//用户维度
    private Integer memberAssionment;//用户权限
    private String joinDay;//用户加入日期
    private String memberHead;//用户头部
}
