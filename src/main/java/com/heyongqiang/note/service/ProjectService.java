package com.heyongqiang.note.service;

import cn.hutool.core.util.StrUtil;
import com.heyongqiang.note.dao.BaseDao;
import com.heyongqiang.note.dao.ProjectDao;
import com.heyongqiang.note.po.Project;
import com.heyongqiang.note.po.ProjectRequest;
import com.heyongqiang.note.po.Resource;
import com.heyongqiang.note.po.User;
import com.heyongqiang.note.util.Page;
import com.heyongqiang.note.util.dbUtil;
import com.heyongqiang.vo.resultInfo;
import sun.misc.InnocuousThread;

import java.net.PortUnreachableException;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.List;
import java.util.PropertyPermission;

public class ProjectService {
    private ProjectDao projectDao = new ProjectDao();

    public Page<Project> findProjectByUserId(Integer pageNum, Integer userId) {
        //当前页默认为1
        Integer pageNum1 =1;
        //默认3条
        Integer pageSize1 = 3;
        //参数非空判断
        if (pageNum != 0){
            //设置当前页
            pageNum1=pageNum;
        }
        //查询项目数，返回总记录
        long count = 0;
        count = ProjectDao.findProjectCountByUserId(userId);
        //判断是否>0
        if (count<1){
            return null;
        }
        //大于0，调用Page，获得其他参数
        Page<Project> page = new Page<>(pageNum1,pageSize1,count);
        //得到数据库中分页查询的开始下标
        Integer index = (pageNum1-1)*pageSize1;
        //查询项目表下集合，
        List<Project> projectList = projectDao.findProjectByUserId(index,pageSize1,userId);
        //将list集合放到Page对象中
        page.setDataList(projectList);
        return page;
    }

    public List findProjectListByTypeName(String typeName){
        List<Project> projectlist = projectDao.findProjectByTypeName(typeName);
        return projectlist;
    }

    /**
     * 按项目名字查询
     * @param projectName
     * @return
     */
    public Project findProjectByName(String projectName) {
        Project project = projectDao.findProjectByProjectName(projectName);
        return project;
    }

    /**
     * 寻找所有的项目
     * @return
     */
    public List<Project> findAllProject() {
        List<Project> projectList = projectDao.findAllProject();
        return projectList;
    }

    public resultInfo<Project> addOrUpdate(String head, String content, String name, String type , Integer userId) {
        resultInfo<Project> resultInfo = new resultInfo<>();
        //判断参数是否非空
        if(StrUtil.isBlank(type)){
            resultInfo.setCode(0);
            resultInfo.setMsg("请选择项目类型");
            return resultInfo;
        }
        if(StrUtil.isBlank(head)){
            resultInfo.setCode(0);
            resultInfo.setMsg("项目简介不为空");
            return resultInfo;
        }
        if(StrUtil.isBlank(content)){
            resultInfo.setCode(0);
            resultInfo.setMsg("项目内容不允许为空");
            return resultInfo;
        }
        if(StrUtil.isBlank(name)){
            resultInfo.setCode(0);
            resultInfo.setMsg("项目名称不允许为空");
            return resultInfo;
        }

        //设置回显对象
        Project project=new Project();
        project.setProjectContent(content);
        project.setProjectName(name);
        project.setProjectType(type);
        resultInfo.setResult(project);

        //调用Dao层
        int row = projectDao.addOrUpdate(project,userId);
        //判断影响行数
        if(row>0){
            resultInfo.setCode(1);
        }else{
            resultInfo.setCode(0);
            resultInfo.setResult(project);
        }

        return resultInfo;
    }

        public Page<Project> findProjectListByPage(Integer pageNum, String title,String date,String type,String where) {
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
        long count = ProjectDao.findProjectCountbuUserLimit(title,date,type,where);
        //判断是否>0
        if (count<1){
            return null;
        }
        //大于0，调用Page，获得其他参数
        Page<Project> page = new Page<>(pageNum1,pageSize1,count);
        //得到数据库中分页查询的开始下标
        Integer index = (pageNum1-1)*pageSize1;
        //查询项目表下集合，
        List<Project> projectList = projectDao.findProjectByPage(index,pageSize1,title,date,type,where);
        //将list集合放到Page对象中
        page.setDataList(projectList);
        return page;
    }

    public List<Resource> findAllResourceByProjectId(Integer projectId) {
        String sql = "select * from data_project_resources where projectId = ?";
        List<Object> parms = new ArrayList<>();
        parms.add(projectId);
        List<Resource> resources =  BaseDao.queryAll(sql,parms,Resource.class);
        return resources;
    }

    public Project findProjectByprojectId(Integer projectId) {
        String sql = "select * from data_project where projectId = ?";
        List<Object> parms = new ArrayList<>();
        parms.add(projectId);
        Project project = (Project) BaseDao.queryRow(sql,parms,Project.class);
        return project;
    }

    /**
     * 查找权限
     * @param userName
     * @param projectId
     * @return
     */

    public Integer findAssionment(String userName, Integer projectId) {
        String sql = "select memberAssionment from data_project_member where projectId = ? and userName = ?";
        List<Object> parms = new ArrayList<>();
        parms.add(projectId);
        parms.add(userName);
        Integer assionment = (Integer) BaseDao.findSingleVale(sql,parms);
        return assionment;
    }



    public resultInfo<Project> insertProject(Project project) {
        resultInfo<Project> resultInfo = new resultInfo<>();

        int re = projectDao.addOrUpdate(project,project.getUserId());
        if(re == 1){
            Project re_project = findProjectByName(project.getProjectName());
            resultInfo.setCode(1);
            resultInfo.setResult(re_project);
            return resultInfo;
        } else {
            resultInfo.setCode(0);
            resultInfo.setMsg("输入的项目出错了!!!");
            return resultInfo;
        }
    }

    /**
     * 在项目申请表中加入这一条的数据
     * @param user
     * @param projectId
     * @return
     */
    public resultInfo<ProjectRequest> intoProjectRequest(User user, Integer projectId,String userContent) {
//        Integer userId = user.getUserId();
//        Integer userSex = user.getUserSex();
//        String userLargestStudy = user.getLargestStudy();
//        String userLable = user.getUserLable();
        String userName = user.getUserName();
        resultInfo resultInfo = new resultInfo();
        ProjectRequest projectRequest = projectDao.findRequest(userName,projectId);
        if(projectRequest != null){
            resultInfo.setCode(0);
            resultInfo.setMsg("你已经发过了这一次的请求了!!");
            return resultInfo;
        }
        int i = projectDao.insertProjectRequest(user, projectId,userContent);
        if(i == 0){
            resultInfo.setCode(0);
            resultInfo.setMsg("出现未知错误请查看log日志");
        }
        resultInfo.setMsg("申请成功了!!");
        resultInfo.setCode(1);
        return resultInfo;
    }

    /**
     * 寻找我加入的项目
     * @param pageNum
     * @param userId
     * @return
     */
    public Page<Project> findMyJoinProject(Integer pageNum, Integer userId,String userName) {
        //当前页默认为1
        Integer pageNum1 =1;
        //默认3条
        Integer pageSize1 = 3;
        //参数非空判断
        if (pageNum != 0){
            //设置当前页
            pageNum1=pageNum;
        }
        //查询项目数，返回总记录
        long count = 0;
        count = ProjectDao.findJoinProjectCountByUserId(userName);
        //判断是否>0
        if (count<1){
            return null;
        }
        //大于0，调用Page，获得其他参数
        Page<Project> page = new Page<>(pageNum1,pageSize1,count);
        //得到数据库中分页查询的开始下标
        Integer index = (pageNum1-1)*pageSize1;
        //查询项目表下集合，
        List<Project> projectList = projectDao.findJoinProjectByUserId(index,pageSize1,userName);
        //将list集合放到Page对象中
        page.setDataList(projectList);
        return page;
    }

    /**
     * 删除项目
     * @param projectId
     * @return
     */

    public resultInfo deleteProject(Integer projectId) {
        resultInfo resultInfo = new resultInfo();
        String sql = "delete from data_project where projectId = ?";
        List<Object> parms = new ArrayList<>();
        parms.add(projectId);
        long row = BaseDao.executeUpdate(sql,parms);
        if(row == 1){
            resultInfo.setCode(1);
            resultInfo.setMsg("你删除成功了!!");
        } else {
            resultInfo.setCode(0);
            resultInfo.setMsg("你没有删除成功!!");
        }
        return resultInfo;
    }

    /**
     * 在数据库中加入一条对应的记录
     * @param projectId
     * @param fileName
     * @param userId
     * @param postTime
     * @return
     */
    public resultInfo insertResource(Integer projectId, String fileName, Integer userId, String postTime) {
        resultInfo resultInfo = new resultInfo();
        String sql = "select * from data_project_resources where resourceName = ?";
        List<Object> parms = new ArrayList<>();
        parms.add(fileName);
        Resource resource = (Resource) BaseDao.queryRow(sql,parms,Resource.class);
        if(resource != null){
            resultInfo.setCode(0);
            resultInfo.setMsg("已经有了这一条的记录!!");
            return resultInfo;
        }
        String sql2 = "insert into data_project_resources(resourceName,projectId,userId,uploadNum,postTime) values(?,?,?,?,?)";
        List<Object> parms2 = new ArrayList<>();
        parms2.add(fileName);
        parms2.add(projectId);
        parms2.add(userId);
        parms2.add(0);
        parms2.add(postTime);
        long row = BaseDao.executeUpdate(sql2,parms2);
        if(row == 1){
            resultInfo.setMsg("成功上传数据了!!!");
            resultInfo.setCode(1);
        } else {
            resultInfo.setMsg("上传失败!!");
            resultInfo.setCode(0);
        }
        return resultInfo;
    }



    public resultInfo<User> changeProject(Project project) {
        resultInfo resultInfo = new resultInfo();
        String sql = "UPDATE data_project SET projectName= ? , projectType= ?  , projectContent = ?  WHERE projectId = ?";
        List<Object> parms = new ArrayList<>();
        parms.add(project.getProjectName());
        parms.add(project.getProjectType());
        parms.add(project.getProjectContent());
        parms.add(project.getProjectId());
        int row = BaseDao.executeUpdate(sql,parms);
        if (row>0){
            resultInfo.setMsg("修改完成");
            resultInfo.setCode(1);
        }else{
            resultInfo.setMsg("修改失败");
            resultInfo.setCode(0);
        }
        return resultInfo;
    }


    /**
     * 第二次插入数据
     * @param fileUpName
     */
    public void updataProjectImgName(String fileUpName,Integer projectId) {
        String sql = "update data_project set projectImg = ? where projectId = ?";
        List<Object> parms = new ArrayList<>();
        parms.add(fileUpName);
        parms.add(projectId);
        int i = BaseDao.executeUpdate(sql, parms);
        if(i == 1){
            return;
        }
    }


    /**
     * 删除项目的资源
     * @param docId
     * @return
     */
    public resultInfo deleteResource(Integer docId) {
        resultInfo resultInfo = new resultInfo();
        String sql = "delete from data_project_resources where docId = ?";
        List<Object> parms = new ArrayList<>();
        parms.add(docId);
        long count = BaseDao.executeUpdate(sql,parms);
        if(count != 1){
            resultInfo.setCode(0);
            resultInfo.setMsg("删除失败!!");
        } else{
            resultInfo.setCode(1);
            resultInfo.setMsg("删除成功!!");
        }
        return resultInfo;
    }
}
