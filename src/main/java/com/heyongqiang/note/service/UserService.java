package com.heyongqiang.note.service;

import cn.hutool.core.util.StrUtil;
import cn.hutool.crypto.digest.DigestUtil;
import com.heyongqiang.note.dao.BaseDao;
import com.heyongqiang.note.dao.UserDao;
import com.heyongqiang.note.po.ProjectRequest;
import com.heyongqiang.note.po.User;
import com.heyongqiang.vo.resultInfo;
import org.apache.commons.io.FileUtils;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.File;
import java.io.IOException;
import java.sql.Timestamp;
import java.util.ArrayList;
import java.util.Base64;
import java.util.List;

public class UserService {

    private UserDao userDao = new UserDao();

    /**
     * 用户登录
     * @param userName
     * @param userPwd
     * @return
     */

    public resultInfo<User> userLogin(String userName, String userPwd){
        resultInfo<User> resultInfo = new resultInfo<>();
        User u = new User();
        u.setUserName(userName);
        u.setUserPwd(userPwd);
        resultInfo.setResult(u);
        //参数是否为空
        if(StrUtil.isBlank(userName)|| StrUtil.isBlank(userPwd)){
            resultInfo.setCode(0);
            resultInfo.setMsg("用户姓名或密码不能为空");
            //返回resultInfo
            return resultInfo;
        }
        //如果不为空 通过用户名查询用户对象
        User user = userDao.queryUserByName(userName);
        if(user == null){
            resultInfo.setCode(0);
            resultInfo.setMsg("该用户不存在");
            return resultInfo;
        }
        // 如果用户对象不为空 将数据库中查询的用户对象 密码和前台的密码做比较
        // 将前台传递的密码按md5算法的方式加密
        //userPwd = DigestUtil.md5Hex(userPwd);
        //判断加密后的密码是否一致
        if(!userPwd.equals(user.getUserPwd())){
            resultInfo.setCode(0);
            resultInfo.setMsg("用户密码不正确");
            return resultInfo;
        }
        resultInfo.setCode(1);
        resultInfo.setResult(user);
        return resultInfo;
    }

    /**
     * 用户注册
     */
    public resultInfo<User> userRegister(String username, String userpwd, String telephone, String realname, Integer sex, String birthday, String largestStudy){
        resultInfo<User> resultInfo = new resultInfo<>();
        User u = new User();
        u.setUserName(username);
        u.setUserPwd(userpwd);
        u.setUserTelephone(telephone);
        u.setUserRealName(realname);
        u.setUserSex(sex);
        u.setUserBirthday(birthday);
        u.setUserLargestStudy(largestStudy);
        resultInfo.setResult(u);
        //参数是否为空
        if(StrUtil.isBlank(username)|| StrUtil.isBlank(userpwd)){
            resultInfo.setCode(0);
            resultInfo.setMsg("用户姓名或密码不能为空");
            resultInfo.setResult(u);
            //返回resultInfo
            return resultInfo;
        }
        //
        String sql = "insert into data_user(userName,userPwd,userTelephone,userRealName,userSex,userBirthday,userLargestStudy) values(?,?,?,?,?,?,?)";
        List<Object> parms = new ArrayList<>();
        parms.add(username);
        parms.add(userpwd);
        parms.add(telephone);
        parms.add(realname);
        parms.add(sex);
        parms.add(birthday);
        parms.add(largestStudy);
        UserDao userDao = new UserDao();
        User user = userDao.queryUserByName(username);
        if(user != null){
            //有这个用户
            resultInfo.setCode(0);
            resultInfo.setMsg("该用户已经被注册了!!");
            return resultInfo;
        }
        BaseDao.executeUpdate(sql,parms);
            // 如果用户对象不在数据库中
            // 将前台传递的密码按md5算法的方式加密
            //userpwd = DigestUtil.md5Hex(userpwd);
            resultInfo.setCode(1);
            return resultInfo;
        }


    public User fingUserByUserId(Integer userId) {
        User user = new User();
        user = userDao.queryUserById(userId);
        return user;
    }

    public User fingUserByUserName(String userName) {
        User user = new User();
        user = userDao.queryUserByName(userName);
        return user;
    }


    /**
     * 返回request中的所有请求
     * @param userId
     * @return
     */
    public List<ProjectRequest> findProjectRequest(Integer userId) {
        String sql = "select * from data_project_receive_request r join (select projectId from data_user u join  data_project p on u.userId = p.userId where u.userId = ?) l on r.projectId = l.projectId";
        List<Object> parms = new ArrayList<>();
        parms.add(userId);
        List<ProjectRequest> list = BaseDao.queryRows(sql,parms,ProjectRequest.class);
        return list;
    }


    /**
     * 删除用户
     * @param userId
     * @return
     */

    public resultInfo deleteUser(Integer userId) {
        String sql = "DELETE FROM data_user WHERE userId=?";
        List<Object> parms = new ArrayList<>();
        parms.add(userId);
        int row = BaseDao.executeUpdate(sql, parms);
        resultInfo resultInfo = new resultInfo();
        if (row>0){
            resultInfo.setMsg("删除成功");
            resultInfo.setCode(1);
        }else{
            resultInfo.setMsg("删除失败");
            resultInfo.setCode(0);
        }
        return resultInfo;
    }


    public resultInfo changeUser(User user,boolean flag_name) {
        resultInfo resultInfo = new resultInfo();
        User username = null;
        if(flag_name == true){
            username = userDao.queryUserByName(user.getUserName());
        }
        if(username != null){
            //有这个用户
            resultInfo.setCode(0);
            resultInfo.setMsg("create repeat!!");
            return resultInfo;
        }
        String sql = "UPDATE data_user SET ";
        List<Object> parms = new ArrayList<>();
        int flag = 0;
        if(!StrUtil.isBlank(user.getUserName())){
            flag = 1;
            sql += " userName = ? ";
            parms.add(user.getUserName());
        }
        if(!StrUtil.isBlank(user.getUserTelephone())){
            if(flag == 1){
                sql += " , ";
            }
            sql += " userTelephone = ? ";
            flag = 1;
            parms.add(user.getUserTelephone());
        }
        if(!StrUtil.isBlank(user.getUserLabel())){
            if(flag == 1){
                sql += ", ";
            }
            sql += " userLabel = ? ";
            flag = 1;
            parms.add(user.getUserLabel());
        }

        if(!StrUtil.isBlank(user.getUserContent())){
            if(flag == 1){
                sql += " , ";
            }
            sql += " userContent = ?";
            flag = 1;
            parms.add(user.getUserContent());
        }

        if(!StrUtil.isBlank(user.getUserEmail())){
            if(flag == 1){
                sql += " , ";
            }
            sql += " userEmail = ?";
            flag = 1;
            parms.add(user.getUserEmail());
        }

        if(!StrUtil.isBlank(user.getUserBirthday())){
            if(flag == 1){
                sql += " , ";
            }
            sql += " userBirthday = ? ";
            flag = 1;
            parms.add(user.getUserBirthday());
        }

        if(user.getUserSex() == 0 || user.getUserSex() == 1){
            if(flag == 1){
                sql += " , ";
            }
            sql += " userSex = ? ";
            parms.add(user.getUserSex());
        }
        sql += " where userId = ?";
        parms.add(user.getUserId());
        int row = BaseDao.executeUpdate(sql,parms);
        if (row>0){
            resultInfo.setMsg("change success");
            resultInfo.setCode(1);
        }else{
            resultInfo.setMsg("change default");
            resultInfo.setCode(0);
        }
        return resultInfo;
    }
}

