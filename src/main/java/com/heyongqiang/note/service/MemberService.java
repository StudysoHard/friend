package com.heyongqiang.note.service;

import com.heyongqiang.note.dao.BaseDao;
import com.heyongqiang.note.dao.MemberDao;
import com.heyongqiang.note.dao.UserDao;
import com.heyongqiang.note.po.Member;
import com.heyongqiang.note.po.User;
import com.heyongqiang.note.util.Page;
import com.heyongqiang.vo.resultInfo;
import sun.security.krb5.internal.PAData;

import javax.xml.crypto.Data;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.List;

public class MemberService {
    MemberDao memberDao = new MemberDao();
    public Page<Member> findMemberByProject(Integer pageNum, Integer projectId) {
        //当前页默认为1
        Integer pageNum1 =1;
        //默认3条
        Integer pageSize1 = 6;
        //参数非空判断
        if (pageNum != 0){
            //设置当前页
            pageNum1=pageNum;
        }
        //查询项目数，返回总记录
        long count = 0;
        count = MemberDao.findMemberNumByProjectId(projectId);
        //判断是否>0
        if (count<1){
            return null;
        }
        //大于0，调用Page，获得其他参数
        Page<Member> page = new Page<>(pageNum1,pageSize1,count);
        //得到数据库中分页查询的开始下标
        Integer index = (pageNum1-1)*pageSize1;
        //查询项目表下集合，
        List<Member> memberList = memberDao.findMemberList(index,pageSize1,projectId);
        //将list集合放到Page对象中
        page.setDataList(memberList);
        return page;
    }


    //降低成员的权限
    public resultInfo<Member> downMeberAssionment(Integer memberAssionment, String userName,Integer projectId) {
        resultInfo<Member> resultInfo = new resultInfo<>();
        //为了解耦
        String sql = "update data_project_member set memberAssionment = ? where projectId = ? and userName = ?";
        Member member = memberDao.changeMemberAssionment(memberAssionment,userName,projectId,sql);
        if(member == null){
            resultInfo.setCode(0);
            resultInfo.setMsg("该成员不存在");
            return resultInfo;
        }
        resultInfo.setCode(1);
        resultInfo.setResult(member);
        return resultInfo;
    }



    public resultInfo<Member> upMemberAssionment(Integer memberAssionment, String userName,Integer projectId) {
        resultInfo<Member> resultInfo = new resultInfo<>();
        //为了解耦
        String sql = "update data_project_member set memberAssionment = ? where projectId = ? and userName = ?";
        Member member = memberDao.changeMemberAssionment(memberAssionment,userName,projectId,sql);
        if(member == null){
            resultInfo.setCode(0);
            resultInfo.setMsg("该成员不存在");
            return resultInfo;
        }
        resultInfo.setCode(1);
        resultInfo.setResult(member);
        return resultInfo;
    }


    /**
     * 同意这次的请求
     * @param projectId
     * @param userName
     * @return
     */
    public resultInfo agreeThisPost(Integer projectId, String userName,Integer createUserId) {
        //需要删除这一条的记录
        resultInfo resultInfo = new resultInfo();
        String sql1 = "delete from data_project_receive_request where projectId = ? and userName = ?";
        List<Object> parms1 = new ArrayList<>();
        parms1.add(projectId);
        parms1.add(userName);
        long row = BaseDao.executeUpdate(sql1,parms1);
        //删除请求是否成功
        if(row == 0) {
            resultInfo.setMsg("请求失败了!!");
            resultInfo.setCode(0);
            return resultInfo;
        }
        String sql_user = "select * from data_user where userName = ?";
        List<Object> parms_user = new ArrayList<>();
        parms_user.add(userName);
        User user = (User) BaseDao.queryRow(sql_user,parms_user,User.class);
        String sql2 = "insert into data_project_member(projectId,createUserId,userName,memberLabel,userSex,userLargestStudy,memberAssionment,joinDay,memberHead) values (?,?,?,?,?,?,?,?,?)";
        List<Object> parms2 = new ArrayList<>();
        parms2.add(projectId);
        parms2.add(createUserId);
        parms2.add(user.getUserName());
        parms2.add(user.getUserLabel());
        parms2.add(user.getUserSex());
        parms2.add(user.getUserLargestStudy());
        parms2.add(0);
        String timeStamp = new SimpleDateFormat("yyyy-MM-dd").format(Calendar.getInstance().getTime());
        parms2.add(timeStamp);
        parms2.add(user.getUserHead());
        long row2 = BaseDao.executeUpdate(sql2,parms2);
        if(row2 == 1){
            resultInfo.setCode(1);
            resultInfo.setMsg("请求通过了!!");
        } else {
            resultInfo.setCode(0);
            resultInfo.setMsg("请求失败了!!");
        }
        return resultInfo;
    }
}
