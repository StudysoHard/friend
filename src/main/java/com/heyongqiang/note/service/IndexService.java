package com.heyongqiang.note.service;

import com.heyongqiang.note.dao.BaseDao;
import com.heyongqiang.note.po.IndexProject;
import com.heyongqiang.note.po.IndexType;

import java.util.ArrayList;
import java.util.List;

public class IndexService {

    /**
     * 进入主站之后的推荐项目模块
     * @return
     */
    public List<IndexProject> showIndexProject(Integer limit){
        String sql = "select * from data_index_project limit ?,4";
        List<Object> parms = new ArrayList<>();
        parms.add(limit);
        List<IndexProject> list = BaseDao.queryAll(sql,parms,IndexProject.class);
        return list;
    }

    public List<IndexType> showIndexType(Integer limit){
        String sql = "select * from data_index_type limit ?,3";
        List<Object> parms = new ArrayList<>();
        parms.add(limit);
        List<IndexType> list = BaseDao.queryAll(sql,parms,IndexType.class);
        return list;
    }

}
