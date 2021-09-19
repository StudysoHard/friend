package com.heyongqiang.note.dao;

import cn.hutool.core.util.StrUtil;
import com.heyongqiang.note.po.Project;
import com.heyongqiang.note.po.ProjectRequest;
import com.heyongqiang.note.po.User;
import com.heyongqiang.note.util.dbUtil;
import com.heyongqiang.vo.resultInfo;
import javafx.beans.property.ListProperty;

import javax.management.AttributeList;
import javax.xml.crypto.Data;
import java.net.PortUnreachableException;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.List;
import java.util.function.Predicate;

public class ProjectDao {
    List<Object> list = new ArrayList<>();


    public static long findProjectCountByUserId(Integer userId) {
        String sql = "SELECT count(?) From data_project where userId = ?";
        List<Object> params = new AttributeList();
        params.add(1);
        params.add(userId);
        long count = 0;
        count = (long) BaseDao.findSingleVale(sql, params);
        return count;
    }


    /**
     * 返回搜索页面分分页的总数量
     * @param title
     * @param date
     * @param type
     * @return
     */
    public static long findProjectCountbuUserLimit( String title, String date, String type,String where) {
        String sql = "SELECT count(?) From data_project where ";
        Integer flag = 0;
        //设置参数
        List<Object> params = new ArrayList<>();
        params.add(1);
        //判断查询条件是否为空
        if (!StrUtil.isBlank(title)) {
            flag = 1;
            //查询不为空，拼接sql语句
            sql += " projectName like concat('%',?,'%')";
            params.add(title);
        }
        if (!StrUtil.isBlank(date)) {
            if(flag == 1){
                //前面有进入
                sql = sql + " and ";
            }
            flag = 1;
            sql += " createTime > ? ";
            params.add(date);
        }
        if (!StrUtil.isBlank(type)) {
            if(flag == 1){
                sql = sql + " and ";
            }
            flag = 1;
            sql += " projectType = ? ";
            params.add(type);
        }
        if (!StrUtil.isBlank(where)) {
            if(flag == 1){
                sql = sql + " and ";
            }
            flag = 1;
            sql += " province = ? ";
            params.add(where);
        }
        if(flag == 0){
            sql = sql.substring(0,sql.length()-5);
        }
        long count = (long) BaseDao.findSingleVale(sql, params);
        return count;
    }

    /**
     * 寻找我加入的项目
     * @param userName
     * @return
     */
    public static long findJoinProjectCountByUserId(String userName) {
        String sql = "SELECT count(?) From data_project_member where userName = ?";
        List<Object> params = new AttributeList();
        params.add(1);
        params.add(userName);
        long count = 0;
        count = (long) BaseDao.findSingleVale(sql, params);
        return count;
    }

    /**
     *    分页查询的限制条件
     *          @param index
     *          @param pageSize1
     *          @param title
     *          @param date
     *          @param type
     *          @return
     */
    public List<Project> findProjectByPage(Integer index, Integer pageSize1, String title, String date, String type,String where) {
        String sql = "SELECT * From data_project where ";
        Integer flag = 0;
        List<Object> params = new ArrayList<>();
        if (!StrUtil.isBlank(title)) {
            flag = 1;
            //查询不为空，拼接sql语句
            sql += "projectName like concat('%',?,'%') ";
            params.add(title);
        }
        if (!StrUtil.isBlank(date)) {
            if(flag == 1){
                sql += "and ";
            }
            flag = 2;
            sql += "createTime > ? ";
            params.add(date);
        }
        if (!StrUtil.isBlank(type)) {
            if(flag == 1 || flag == 2){
                sql += "and ";
            }
            flag = 3;
            sql += "projectType = ? ";
            params.add(type);
        }
        if (!StrUtil.isBlank(where)) {
            if(flag == 1 || flag == 2|| flag == 3){
                sql += "and ";
            }
            flag = 3;
            sql += " province = ? ";
            params.add(where);
        }
        if(flag == 0){
            sql = sql.substring(0,sql.length()-6);
        }
        //拼接
        sql += "limit ?,?";
        params.add(index);
        params.add(pageSize1);
        List<Project> projectList = BaseDao.queryRows(sql, params, Project.class);
        return projectList;
    }


    /**
     * 添加修改项目
     * @param project
     * @return
     */
    public int addOrUpdate(Project project,Integer userId) {
        //SQL语句
        String sql = "insert into data_project (projectType,projectHead,projectContent,projectName,userId,visitNumber,createTime,province) values(?,?,?,?,?,?,date_format(now(),'%Y-%m-%d'),?)";
        //设置参数
        List<Object> params = new ArrayList<>();
        params.add(project.getProjectType());
        params.add(project.getProjectHead());
        params.add(project.getProjectContent());
        params.add(project.getProjectName());
        params.add(userId);
        params.add(project.getVisitNumber());
        params.add(project.getProvince());
        int row = BaseDao.executeUpdate(sql, params);
        return row;
    }


    public List<Project> findProjectByUserId(Integer index,Integer pageSize1,Integer userId) {
        String sql = "SELECT * From data_project where userId = ? ";
        List<Object> params = new ArrayList<>();
        params.add(userId);
        //拼接
        sql += "limit ?,?";
        params.add(index);
        params.add(pageSize1);
        List<Project> projectList = BaseDao.queryRows(sql, params, Project.class);
        return projectList;
    }

    public Project findProjectByProjectName(String projectName) {
        String sql = "select projectId,projectName,projectType,projectContent,userId,projectHead from data_project where projectName = ?";
        List<Object> parms = new ArrayList<>();
        parms.add(projectName);
        Project project = (Project) BaseDao.queryRow(sql,parms,Project.class);
        //返回符合的集合
        return project;
    }

    public List<Project> findAllProject() {
        String sql = "select projectId,projectName,projectType,projectContent,userId,projectHead from data_project";
        List<Object> parms = new ArrayList<>();
        List<Project> list = BaseDao.queryAll(sql,parms,Project.class);
        return list;
    }

    public List<Project> findProjectByTypeName(String typeName) {
        String sql = "select * from data_project where projectLabel = ? ";
        List<Object> parms = new ArrayList<>();
        parms.add(typeName);
        List<Project> list = BaseDao.queryAll(sql,parms,Project.class);
        return list;
    }

    /**
     * 查找request表中是否有这一条的记录
     * @param userName
     * @param projectId
     * @return
     */
    public ProjectRequest findRequest(String userName, Integer projectId) {
        String sql = "select * from data_project_receive_request where projectId = ? and userName = ?";
        ProjectRequest projectRequest = null;
        Connection connection = null;
        PreparedStatement preparedStatement = null;
        ResultSet resultSet = null;
        try{
            connection = dbUtil.getConnection();
            preparedStatement = connection.prepareStatement(sql);
            preparedStatement.setInt(1,projectId);
            preparedStatement.setString(2,userName);
            resultSet = preparedStatement.executeQuery();
            if(resultSet.next()){
                projectRequest = new ProjectRequest();
                projectRequest.setUserSex(resultSet.getInt("projectId"));
            }
        } catch (Exception el){
            el.printStackTrace();
        } finally {
            dbUtil.close(resultSet,preparedStatement,connection);
        }
        return projectRequest;
    }

    /**
     * 在request数据库中插入一条request的请求
     * @param user
     * @param projectId
     */
    public int insertProjectRequest(User user, Integer projectId,String userCotent) {
        String sql = "insert into data_project_receive_request(projectId,userName,userLabel,userSex,userLargestStudy,userIntroduce,userId) values(?,?,?,?,?,?,?)";
        List parms = new ArrayList();
        parms.add(projectId);
        parms.add(user.getUserName());
        parms.add(user.getUserLabel());
        parms.add(user.getUserSex());
        parms.add(user.getUserLargestStudy());
        parms.add(userCotent);
        parms.add(user.getUserId());
        int i = BaseDao.executeUpdate(sql, parms);
        return i;
    }


    /**
     * 寻找所有加入的项目
     * @param index
     * @param pageSize1
     * @param userName
     * @return
     */
    public List<Project> findJoinProjectByUserId(Integer index, Integer pageSize1, String userName) {
        String sql = "SELECT p.projectId,projectHead,projectName,projectImg FROM `data_project_member` m JOIN data_project p on p.projectId = m.projectId where m.userName = ?";
        List<Object> params = new ArrayList<>();
        params.add(userName);
        //拼接
        sql += "limit ?,?";
        params.add(index);
        params.add(pageSize1);
        List<Project> projectList = BaseDao.queryRows(sql, params, Project.class);
        return projectList;
    }



    /**
     * 修改项目信息
     * @param ProjectName
     * @return
     */
    public Project queryByName(String ProjectName){
        Project project= null;
        Connection connection = null;
        PreparedStatement preparedStatement = null;
        ResultSet resultSet = null;
        String sql = "select * from data_projcet where userName = ?";
        try{
            connection = dbUtil.getConnection();
            preparedStatement = connection.prepareStatement(sql);
            preparedStatement.setString(1,ProjectName);
            resultSet = preparedStatement.executeQuery();
            if(resultSet.next()){
                project = new Project();
                project.setProjectName(ProjectName);
                project.setProjectImg(resultSet.getString("projectImg"));
                project.setProjectId(resultSet.getInt("projectId"));
                project.setProjectHead(resultSet.getString("projectHead"));
                project.setProjectContent(resultSet.getString("projectContent"));
                project.setProjectType(resultSet.getString("projectType"));
                project.setProvince(resultSet.getString("province"));
            }

        } catch (Exception el){
            el.printStackTrace();
        } finally {
            dbUtil.close(resultSet,preparedStatement,connection);
        }
        return project;
    }
}
