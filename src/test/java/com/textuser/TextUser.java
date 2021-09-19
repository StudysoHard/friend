package com.textuser;

import cn.hutool.core.util.StrUtil;
import com.heyongqiang.note.dao.*;
import com.heyongqiang.note.po.*;
import com.heyongqiang.note.service.*;
import com.heyongqiang.note.util.Page;
import com.heyongqiang.vo.resultInfo;
import lombok.Data;
import lombok.ToString;
import org.apache.commons.io.FileUtils;
import org.junit.Test;

import java.io.File;
import java.io.IOException;
import java.io.UnsupportedEncodingException;
import java.net.PortUnreachableException;
import java.net.URLDecoder;
import java.net.URLEncoder;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.List;

public class TextUser {
    @Test
    public void testQueryUserByName(){
        UserDao userDao = new UserDao();
        User user = userDao.queryUserByName("1");
        System.out.println(user.getUserBirthday());
    }
    @Test
    public void testUser(){
        String sql_user = "select * from data_user where userName = ?";
        List<Object> parms_user = new ArrayList<>();
        parms_user.add("李四");
        User user = (User) BaseDao.queryRow(sql_user,parms_user,User.class);
        System.out.println(user.getUserId());
    }
    @Test
    public void testDemoToShowAll(){
        ProjectService projectService = new ProjectService();
        List list = projectService.findAllProject();
        System.out.println(list.size());
    }
    /**
     * 测试注册
     */
    @Test
    public void testReagister(){
        UserService userService = new UserService();
        resultInfo<User> resultInfo= userService.userRegister("你好","123","123456789","何勇强",0,"2021-06-02","中学");
        System.out.println(resultInfo.getCode());
    }
    @Test
    public void testProjectService(){
        ProjectDao projectDao = new ProjectDao();
        Project project = new Project();
        project.setProjectType("gfd");
        project.setProjectHead("456");
        project.setProjectContent("你好帅");
        project.setProjectName("我是谁");
        int row  = projectDao.addOrUpdate(project,3);
        System.out.println(row);
    }

    /**
     * 测试搜索项目下的所有资源
     */
    @Test
    public void testResourceAll(){
        List<Resource> list = new ArrayList<>();
        ProjectService projectService = new ProjectService();
        list = projectService.findAllResourceByProjectId(14);
        System.out.println(list.size());
    }

    /**
     * 测试主页的表中的数据
     */
    @Test
    public void testIndex(){
        IndexService indexService = new IndexService();
        List<IndexProject> list1 = indexService.showIndexProject(0);
        List<IndexType> list2 = indexService.showIndexType(0);
        System.out.println(list1.size());
        System.out.println(list2.size());
    }


    /**
     * 测试对应的类型名称查找类型的信息
     */
    @Test
    public void testType(){
        TypeService typeService = new TypeService();
        ProjectType projectType = typeService.showTypeInformation("docker");
        System.out.println(projectType.getTypeHead());
    }

    /**
     * 测试project的按类型名查找
     */

    @Test
    public void testTypeNameSearchProject(){
        ProjectService projectService = new ProjectService();
        List<Project> list = projectService.findProjectListByTypeName("docker");
        System.out.println(list.get(1).getProjectImg());
    }

    @Test
    public void testProjectName(){
        ProjectDao projectDao = new ProjectDao();
        Project project = new Project();
        project.setProjectHead("text01");
        project.setProjectName("heyongqing");
        project.setProjectType("java");
        project.setProjectContent("sadfafdgfd");
        project.setProjectImg("text01");
        project.setVisitNumber(100);
        Integer in = projectDao.addOrUpdate(project,17);
        System.out.println(in);
    }

    @Test
    public void testReasource(){
        ProjectService projectService = new ProjectService();
        List<Resource> resources = projectService.findAllResourceByProjectId(1);
        System.out.println(resources.size());
    }

    /**
     * 测试projectId查找项目
     */
    @Test
    public void testProjectId(){
        ProjectService projectService = new ProjectService();
        Integer assionment = projectService.findAssionment("a", 1);
        System.out.println(assionment);
    }

    @Test
    public void testSearchSevice(){
        ProjectService projectService = new ProjectService();
        Page<Project> page =  projectService.findProjectListByPage(1,null,null,null,"10");
        System.out.println(page.getTotalCount());
    }

    @Test
    public void testPageById(){
        Page<Project> personCreatePage = new ProjectService().findProjectByUserId(1,4);
        System.out.println(personCreatePage.getTotalCount());
    }


    /**
     * 测试成员
     */
    @Test
    public void testMember(){
        MemberService memberService = new MemberService();
        Page<Member> member = memberService.findMemberByProject(0,12);
        System.out.println(member.getDataList().size());
    }

    @Test
    public void testMe(){
        MemberDao memberDao = new MemberDao();
        String sql = "update data_project_member set memberAssionment = ? where projectId = ? and userName = ?";
        Member member = memberDao.changeMemberAssionment(1,"l",5,sql);
        System.out.println(member.getMemberAssionment());
    }


    /**
     * 测试文件夹创建
     * @throws IOException
     */
    @Test
    public void testCreate() throws IOException {
        String fileImg = "text";
        FileUtils.forceMkdir(new File("C:\\java项目\\r_note\\src\\main\\projectFile\\"+fileImg));
    }

    @Test
    public void testFind(){
        ProjectDao projectDao = new ProjectDao();
        ProjectRequest projectRequest = projectDao.findRequest("1",1);
        System.out.println(projectRequest);
    }

    @Test
    public void testUserFind(){
        UserDao userDao = new UserDao();
        User user =  userDao.queryUserByName("sdaf");
        System.out.println(user);
    }

    /**
     * 测试插入一条项目请求
     */
    @Test
    public void testInser(){
        ProjectDao projectDao = new ProjectDao();
        User user = new User();
        user.setUserName("name");
        user.setUserId(12);
        user.setUserSex(0);
        user.setUserLabel("test");
        int i = projectDao.insertProjectRequest(user,5,"nidn");
        System.out.println(i);
    }

    @Test
    public void testRequest(){
        UserService userService = new UserService();
        List<ProjectRequest> list = userService.findProjectRequest(58);
        System.out.println(list.size());
    }

    @Test
    public void testJoin(){
        ProjectService projectService = new ProjectService();
        Page<Project> list = projectService.findMyJoinProject(2,16,"k");
        System.out.println(list.getTotalCount());
    }

    @Test
    public void delete(){
        UserService userService = new UserService();
        resultInfo<User> resultInfo = userService.deleteUser(24);
        System.out.println(resultInfo.getMsg());
    }

    @Test
    public void data_noew(){
        MemberService memberService = new MemberService();
        resultInfo resultInfo = memberService.agreeThisPost(11,"21613123",57);
        System.out.println(resultInfo.getMsg());
    }

    @Test
    public void selectResource(){
        String sql = "select * from data_project_resources where resourceName = ?";
        List<Object> parms = new ArrayList<>();
        parms.add("123");
        Resource resource = (Resource) BaseDao.queryRow(sql,parms,Resource.class);
        System.out.println(resource);
    }

    @Test
    public void totestRequest() throws UnsupportedEncodingException {
        String name = URLEncoder.encode("张三","UTF-8");
        System.out.println(name);
        String decodeStr = URLDecoder.decode(name, "UTF-8");
        System.out.println("解码:" + decodeStr);
    }

    @Test
    public void testTypeDao(){
        TypeService typeService = new TypeService();
        Page<Project> projectPage = typeService.showAllProject(1,1);
        System.out.println(projectPage.getDataList().size());
    }

    @Test
    public void testnull(){
        User user = new User();
        String userName = user.getUserName();
        System.out.println(StrUtil.isBlank(userName));
        System.out.println(userName);
    }

}
