package com.heyongqiang.note.dao;

import com.heyongqiang.note.po.Member;
import com.heyongqiang.note.po.Project;
import com.heyongqiang.vo.resultInfo;

import javax.management.AttributeList;
import java.lang.reflect.Array;
import java.util.ArrayList;
import java.util.List;

public class MemberDao {

    public static long findMemberNumByProjectId(Integer projectId) {
        String sql = "SELECT count(?) From data_project_member where projectId = ?";
        List<Object> params = new AttributeList();
        params.add(1);
        params.add(projectId);
        long count = 0;
        count = (long) BaseDao.findSingleVale(sql, params);
        return count;
    }

    public List<Member> findMemberList(Integer index, Integer pageSize1, Integer projectId) {
        String sql = "SELECT * From data_project_member where projectId = ? ";
        List<Object> params = new ArrayList<>();
        params.add(projectId);
        //拼接
        sql += "limit ?,?";
        params.add(index);
        params.add(pageSize1);
        List<Member> memberList = BaseDao.queryRows(sql, params, Member.class);
        return memberList;
    }


    /**
     * 修改用户的权限
     * @param memberAssionment
     * @param userName
     * @param projectId
     * @return
     */
    public Member changeMemberAssionment(Integer memberAssionment, String userName, Integer projectId, String sql) {
        resultInfo resultInfo = new resultInfo();
        Member member = new Member();
        List<Object> parms = new ArrayList<>();
        parms.add(memberAssionment);
        parms.add(projectId);
        parms.add(userName);
        Integer row =  BaseDao.executeUpdate(sql,parms);
        if(row <= 0){
            resultInfo.setMsg("没有这个成员");
            return member;
        }
        String sql2 = "select * from data_project_member where projectId = ? and userName = ?";
        List<Object> parms2 = new ArrayList<>();
        parms2.add(projectId);
        parms2.add(userName);
        member = (Member) BaseDao.queryRow(sql2,parms2,Member.class);
        return member;
    }
}
