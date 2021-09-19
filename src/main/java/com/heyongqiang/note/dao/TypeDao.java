package com.heyongqiang.note.dao;

import cn.hutool.core.util.StrUtil;
import com.heyongqiang.note.po.Project;
import com.heyongqiang.note.po.Type;
import com.heyongqiang.note.util.Page;

import java.util.ArrayList;
import java.util.List;

public class TypeDao {

    /**
     * 按项目id查找符合条件的project
     * @param typeName
     * @return
     */
    public long countAllProject(String typeName) {
        String sql = "select count(?) from data_project where projectType = ? ";
        List<Object> parms = new ArrayList<>();
        parms.add(1);
        parms.add(typeName);
        long count = (long) BaseDao.findSingleVale(sql,parms);
        return count;
    }


    /**
     * 查看所有符合条件的项目
     * @param index
     * @param pageSize1
     * @param typeName
     * @return
     */
    public List<Project> findTypeProject(Integer index, Integer pageSize1, String typeName) {
        String sql = "SELECT * From data_project where projectType = ? ";
        Integer flag = 0;
        List<Object> params = new ArrayList<>();
        //拼接
        sql += " limit ?,? ";
        params.add(typeName);
        params.add(index);
        params.add(pageSize1);
        List<Project> projectList = BaseDao.queryRows(sql, params, Project.class);
        return projectList;
    }


    /**
     * 类型标签
     * @param typeId
     * @return
     */
    public Type findTypeInformation(Integer typeId) {
        String sql = "select * from data_type where typeId = ?";
        List<Object> parms = new ArrayList<>();
        parms.add(typeId);
        Type type = (Type) BaseDao.queryRow(sql,parms,Type.class);
        return type;
    }


    /**
     * 按项类型的type决定tpye的名字
     * @param typeId
     * @return
     */
    public String findTypeNameById(Integer typeId) {
        String sql = "select typeName from data_type where typeId = ?";
        List<Object> parms = new ArrayList<>();
        parms.add(typeId);
        return (String) BaseDao.findSingleVale(sql,parms);
    }
}
