package com.heyongqiang.note.po;


import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class ProjectType {
    private Integer typeId;//类型id
    private String typeName;//类型名称
    private String typeHead;//类型简介
    private String typeImg;//类型图片

    @Override
    public String toString() {
        return "ProjectType{" +
                "typeId=" + typeId +
                ", typeName='" + typeName + '\'' +
                ", typeHead='" + typeHead + '\'' +
                ", typeImg='" + typeImg + '\'' +
                '}';
    }
}
