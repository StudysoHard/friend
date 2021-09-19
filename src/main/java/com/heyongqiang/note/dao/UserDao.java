package com.heyongqiang.note.dao;

import com.heyongqiang.note.po.User;
import com.heyongqiang.note.util.dbUtil;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.Statement;

public class UserDao {
    /**
     * 通过用户名查询
     * @param userName
     * @return
     */
    public User queryUserByName(String userName){
        User user = null;
        Connection connection = null;
        PreparedStatement preparedStatement = null;
        ResultSet resultSet = null;
        String sql = "select * from data_user where userName = ?";
        try{
        connection = dbUtil.getConnection();
        preparedStatement = connection.prepareStatement(sql);
        preparedStatement.setString(1,userName);
        resultSet = preparedStatement.executeQuery();
        if(resultSet.next()){
            user = new User();
            user.setUserName(userName);
            user.setUserPwd(resultSet.getString("userPwd"));
            user.setUserId(resultSet.getInt("userId"));
            user.setUserHead(resultSet.getString("userHead"));
            user.setUserContent(resultSet.getString("userContent"));
            user.setUserTelephone(resultSet.getString("userTelephone"));
            user.setUserLabel(resultSet.getString("userLabel"));
            user.setUserRealName(resultSet.getString("userRealName"));
            user.setUserSex(resultSet.getInt("userSex"));
            user.setUserBirthday(resultSet.getString("userBirthday"));
            user.setUserEmail(resultSet.getString("userEmail"));
        }

        } catch (Exception el){
            el.printStackTrace();
        } finally {
            dbUtil.close(resultSet,preparedStatement,connection);
        }
        return user;
    }


    public User queryUserById(Integer userId){
        User user = null;
        Connection connection = null;
        PreparedStatement preparedStatement = null;
        ResultSet resultSet = null;
        String sql = "select * from data_user where userId = ?";
        try{
            connection = dbUtil.getConnection();
            preparedStatement = connection.prepareStatement(sql);
            preparedStatement.setInt(1,userId);
            resultSet = preparedStatement.executeQuery();
            if(resultSet.next()){
                user = new User();
                user.setUserId(userId);
                user.setUserName(resultSet.getString("userName"));
                user.setUserPwd(resultSet.getString("userPwd"));
                user.setUserHead(resultSet.getString("userHead"));
                user.setUserContent(resultSet.getString("userContent"));
                user.setUserTelephone(resultSet.getString("userTelephone"));
                user.setUserLabel(resultSet.getString("userLabel"));
                user.setUserRealName(resultSet.getString("userRealName"));
                user.setUserSex(resultSet.getInt("userSex"));
                user.setUserBirthday(resultSet.getString("userBirthday"));
                user.setUserEmail(resultSet.getString("userEmail"));
            }
        } catch (Exception el){
            el.printStackTrace();
        } finally {
            dbUtil.close(resultSet,preparedStatement,connection);
        }
        return user;
    }

}
