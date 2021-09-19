package com.heyongqiang.note.po;


import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class Resource {
    private Integer docId;//文档id
    private String resourceName;//文档名称
    private Integer projectId;//关联的项目id
    private Integer userId;//上传用户的id
    private Integer uploadNum;//下载的数量
    private String postTime;//上传的时间
}
