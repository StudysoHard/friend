package com.heyongqiang.note.service;


import com.heyongqiang.note.dao.BaseDao;
import com.heyongqiang.note.dao.ProjectDao;
import com.heyongqiang.note.dao.TypeDao;
import com.heyongqiang.note.po.Project;
import com.heyongqiang.note.po.ProjectType;
import com.heyongqiang.note.po.Type;
import com.heyongqiang.note.util.Page;
import com.heyongqiang.vo.resultInfo;

import java.awt.*;
import java.util.ArrayList;
import java.util.ArrayList;
import java.util.List;

public class TypeService {
    TypeDao typedao = new TypeDao();
    /**
     * 这个方法用来显示数据库中的该类型的所有数据
     * @param typeName
     * @return
     */
    public ProjectType showTypeInformation(String typeName) {
        String sql = "select typeId,typeName,typeHead,typeImg from data_type where typeName = ?";
        List<Object> parms = new ArrayList<>();
        parms.add(typeName);
        ProjectType typeInformation = (ProjectType) BaseDao.queryRow(sql,parms,ProjectType.class);
        return typeInformation;
    }

    public Page<Project> showAllProject(Integer typeId,Integer pageNum ) {
        //当前页默认为1
        Integer pageNum1 =1;
        //默认5条
        Integer pageSize1 = 5;
        //参数非空判断
        if (pageNum != 0){
            //设置当前页
            pageNum1=pageNum;
        }
        //查询项目数，返回总记录
        String typeName = typedao.findTypeNameById(typeId);
        long count = typedao.countAllProject(typeName);
        //判断是否>0
        if (count<1){
            return null;
        }
        //大于0，调用Page，获得其他参数
        Page<Project> page = new Page<>(pageNum1,pageSize1,count);
        //得到数据库中分页查询的开始下标
        Integer index = (pageNum1-1)*pageSize1;
        //查询项目表下集合，
        List<Project> projectList = typedao.findTypeProject(index,pageSize1,typeName);
        //将list集合放到Page对象中
        page.setDataList(projectList);
        return page;
    }

    /**
     * 显示该类型的所有信息
     * @param typeId
     * @return
     */
    public Type findTypeInformation(Integer typeId) {
        Type type = typedao.findTypeInformation(typeId);
        return type;
    }
}
