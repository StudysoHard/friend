package com.heyongqiang.note.web;

import cn.hutool.core.util.StrUtil;
import com.heyongqiang.note.po.Project;
import com.heyongqiang.note.po.ProjectRequest;
import com.heyongqiang.note.po.Resource;
import com.heyongqiang.note.po.User;
import com.heyongqiang.note.service.ProjectService;
import com.heyongqiang.note.service.UserService;
import com.heyongqiang.note.util.Page;
import com.heyongqiang.vo.resultInfo;
import org.apache.commons.fileupload.FileItem;
import org.apache.commons.fileupload.FileItemFactory;
import org.apache.commons.fileupload.disk.DiskFileItemFactory;
import org.apache.commons.fileupload.servlet.ServletFileUpload;
import org.apache.commons.io.FileUtils;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.File;
import java.io.IOException;
import java.io.PrintWriter;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Iterator;
import java.util.List;

@WebServlet("/project")
public class ProjectServlet extends HttpServlet {

    private ProjectService projectService = new ProjectService();
    @Override
    protected void service(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        //得到用户行为
        String actionName = request.getParameter("actionName");
        //判断用户行为
        if(actionName.equals("showAllProject")){
            //进入搜索的时候自动搜索全部的项目
            projectShowAll(request,response);
        } else if(actionName.equals("searchName")){
            //按照名字查询对应的项目
            projectSearchByName(request,response);
        } else if(actionName.equals("searchLimit")){
            projectSearch(request,response);
        } else if(actionName.equals("showresource")){
            projectSourceList(request,response);
        } else if(actionName.equals("movePage")){
            projectMovePage(request,response);
        } else if(actionName.equals("createProject")){
            projectCreate(request,response);
        } else if(actionName.equals("intoThisProject")){
            requestProject(request,response);
        } else if(actionName.equals("deleteProject")){
            deleteProject(request,response);
        } else if(actionName.equals("postResource")){
            postResource(request,response);
        } else if(actionName.equals("deleteResource")){
            deleteResource(request,response);
        } else if(actionName.equals("changProject")){
            changeProject(request,response);
        }
    }


    /**
     * 删除项目资源
     * @param request
     * @param response
     */
    private void deleteResource(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        Integer docId = Integer.valueOf(request.getParameter("docId"));
        String projectId = request.getParameter("projectId");
        String resourceName = request.getParameter("resourceName");
        resultInfo resultInfo = new resultInfo();
        resultInfo = projectService.deleteResource(docId);
        request.setAttribute("resultInfo",resultInfo);
        if(resultInfo.getCode() == 0) {
            request.getRequestDispatcher("success.jsp").forward(request, response);
        }
        String realPath = "C:\\java项目\\r_note\\src\\main\\webapp\\projectFile";
        File delFile = new File(realPath+"\\" + projectId+"\\"+resourceName);
        FileUtils.forceDelete(delFile);
        request.getRequestDispatcher("success.jsp").forward(request, response);
    }


    /**
     * 修改用户信息
     * @param request
     * @param response
     * @throws IOException
     */
    private void changeProject(HttpServletRequest request, HttpServletResponse response) throws IOException {
        Integer projectId = Integer.valueOf(request.getParameter("projectId"));
        Project project = new Project();
        project.setProjectId(projectId);
        project.setProjectName(request.getParameter("projectName"));
        project.setProjectContent(request.getParameter("projectContent"));
        project.setProjectType(request.getParameter("projectType"));
        project.setProvince(request.getParameter("province"));
        projectService.changeProject(project);


    }



    /**
     * 上传文件
     * @param request
     * @param response
     */
    private void postResource(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

        Integer projectId = Integer.valueOf(request.getParameter("projectId"));
        User user = (User) request.getSession().getAttribute("user");
        Integer userId = user.getUserId();
        String postTime = new SimpleDateFormat("yyyy-MM-dd").format(Calendar.getInstance().getTime());
        request.setCharacterEncoding("utf-8");//从前端获取的文件类型编码设置
        response.setCharacterEncoding("utf-8");//响应的文件类型的编码
        response.setContentType("text/html;charset=UTF-8");//响应文件的类型MIME类型设置
        //上传文件到servlet
        boolean isMultipart = ServletFileUpload.isMultipartContent(request);//判断前端发送的数据是否是multipart类型
        if(isMultipart) {//确定前端form表单中是否有enctype元素
            DiskFileItemFactory factory = new DiskFileItemFactory();
            ServletFileUpload upload = new ServletFileUpload(factory); //这样就可以完美的处理数据了
            //设置缓冲区的大小
            factory.setSizeThreshold(10240);
            //通过parseRequest解析form中的所有字段 并保存到items集合中
            try {
                List<FileItem> items = upload.parseRequest(request);//处理前端的请求数据  即前端传过来的sname sno spicture 就存储在items中
                Iterator<FileItem> iter = items.iterator();//设置遍历的类型
                while (iter.hasNext()) {
                    FileItem item = iter.next();//依次取出数据 数据可以看做是字典对象
                    String itemName = item.getFieldName();//获取name属性值
                    //判断上传类型是否符合要求  不符合要求返回
                    int sno = -1;
                    String sname = null;
                    if (item.isFormField()) {
                        if (itemName.equals("sno")) {//判断是否是sno属性
                            sno = Integer.parseInt(item.getString());
                        } else {//判断是否是sname属性
                            sname = item.getString();
                        }
                    } else {//是文件字段
                        String fileName = item.getName();//获取文件名
//                        检查是存在文件了
                        resultInfo resultInfo = projectService.insertResource(projectId,fileName,userId,postTime);
                        if(resultInfo.getCode() == 0){
                            request.setAttribute("resultInfo",resultInfo);
                            request.getRequestDispatcher("success.jsp").forward(request,response);
                        }
                        String path = "C:\\java项目\\r_note\\src\\main\\webapp\\projectFile\\"+projectId+"\\";//设置路径为定值
                        String type = fileName.substring(fileName.indexOf(".") + 1);//获取文件的后缀
                        File file = new File(path, fileName);
                        item.write(file);//这样就将文件存储到指定的文件了
                        request.setAttribute("resultInfo",resultInfo);
                        request.getRequestDispatcher("success.jsp").forward(request,response);
                    }
                }
            } catch (Exception e) {
                e.printStackTrace();
            }
            }
        }


    /**
     * 删除项目
     * @param request
     * @param response
     */
    private void deleteProject(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        Integer projectId = Integer.valueOf(request.getParameter("projectId"));
        resultInfo resultInfo = projectService.deleteProject(projectId);
        request.setAttribute("resultInfo",resultInfo);
        request.getRequestDispatcher("success.jsp").forward(request,response);
    }




    /**
     * 请求这个项目
     * @param request
     * @param response
     */
    private void requestProject(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        Integer projectId = Integer.valueOf(request.getParameter("projectId"));
        User user = (User) request.getSession().getAttribute("user");
        String userContent = request.getParameter("content");
        //执行插入数据
        resultInfo<ProjectRequest> resultInfo = projectService.intoProjectRequest(user,projectId,userContent);
        request.setAttribute("resultInfo",resultInfo);
        request.getRequestDispatcher("submit_after.jsp").forward(request,response);
    }

    /**
     * 创建项目
     * @param request
     * @param response
     */
    private void projectCreate(HttpServletRequest request, HttpServletResponse response) throws IOException {
        boolean isMultipart= ServletFileUpload.isMultipartContent(request);  //enctype属性是否是multipart/form-data
        PrintWriter writer=response.getWriter();
        String projectName = "";
        String projectHead = "";
        String projectContent = "";
        String projectType = "";
        String province = "";
        Project project = new Project();
        User user = (User) request.getSession().getAttribute("user");
        Integer userId = user.getUserId();
        project.setUserId(userId);
        project.setVisitNumber(0);
        StringBuffer fileStr=new StringBuffer();  //上传的文件名，最后输出用
        //写入上传的图片
        try {
            if (isMultipart){
                FileItemFactory factory=new DiskFileItemFactory();  //工厂实例
                ServletFileUpload upload=new ServletFileUpload(factory);  //ServletFileUpload实例依赖于FileItemFactory工厂
                List<FileItem> itemList=upload.parseRequest(request);  //解析表单字段，封装成一个FileItem实例的集合
                Iterator<FileItem> iterator=itemList.iterator();  //迭代器
                while (iterator.hasNext()){
                    FileItem fileItem=iterator.next();  //依次解析每一个FileItem实例，即表单字段
                    if (fileItem.isFormField()){
                        //普通表单字段
                        String fileName = fileItem.getFieldName();
                        if (fileItem.getFieldName().equals("projectName")){
                            projectName=fileItem.getString("UTF-8");
                            project.setProjectName(projectName);
                        } else if(fileItem.getFieldName().equals("projectHead")){
                            projectHead = fileItem.getString("UTF-8");
                            project.setProjectHead(projectHead);
                        } else if(fileItem.getFieldName().equals("projectContent_real")){
                            projectContent = fileItem.getString("UTF-8");
                            project.setProjectContent(projectContent);
                        } else if(fileItem.getFieldName().equals("projectType_real")){
                            projectType = fileItem.getString("UTF-8");
                            project.setProjectType(projectType);
                        } else if(fileItem.getFieldName().equals("province")){
                            province = fileItem.getString("UTF-8");
                            project.setProvince(province);
                        }
                    }else {
                        resultInfo<Project> resultInfo = projectService.insertProject(project);
                        String FileName = String.valueOf(resultInfo.getResult().getProjectId());
                        //存入图片
                        project.setProjectImg(FileName);
                        //文件表单字段
                        String fileUpName=FileName+".png" ;  //用户上传的文件名
                        projectService.updataProjectImgName(fileUpName,resultInfo.getResult().getProjectId());
                        File fileImg=new File("C:\\java项目\\r_note\\src\\main\\webapp\\projectImg\\"+fileUpName);  //要保存到图片路径
                        FileUtils.forceMkdir(new File("C:\\java项目\\r_note\\src\\main\\projectFile\\"+fileUpName));     //新建项目资源目录
                        if (!fileImg.exists()){
                            fileImg.createNewFile();  //一开始肯定是没有的，所以先创建出来
                        }
                        fileItem.write(fileImg);  //写入，保存到目标文件
                        fileStr.append(fileUpName+"、");
                    }
                }
            }
            response.sendRedirect("create_success.jsp");
        }catch (Exception e){

        }
    }

    /**
     * 下方的按钮page分页
     * @param request
     * @param response
     * @throws ServletException
     * @throws IOException
     */
    private void projectMovePage(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        String title = (String) request.getSession().getAttribute("title");
        String date = (String) request.getSession().getAttribute("date");
        String type = (String) request.getSession().getAttribute("type");
        String where = (String) request.getSession().getAttribute("where");
        projectList(request,response,title,date,type,where);
        request.getRequestDispatcher("search.jsp").forward(request,response);
    }

    /**
     * 用作点击对应的项目之后会显示该项目的对应资源文件
     * 并且存储对应的项目的权限
     * @param request
     * @param response
     */

    private void projectSourceList(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        //获取项目名称和用户id
        Integer projectId = Integer.valueOf(request.getParameter("projectId"));
        User user = (User) request.getSession().getAttribute("user");
        String userName = String.valueOf(user.getUserName());
        //加载对应权限
        Integer assionment = projectService.findAssionment(userName,projectId);
        Project project = projectService.findProjectByprojectId(projectId);
        List<Resource> docList = projectService.findAllResourceByProjectId(projectId);
        User createUser = new UserService().fingUserByUserId(project.getUserId());
        request.setAttribute("createUser",createUser);
        request.setAttribute("assionment",assionment);
        request.setAttribute("project",project);
        request.setAttribute("resourceList",docList);
        request.getRequestDispatcher("list.jsp").forward(request,response);
    }

    /**
     *   自动触发搜索所有的项目并且分页
     *          @param request
     *          @param response
     */

    private void projectShowAll(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        projectList(request,response,null,null,null,null);
        request.getRequestDispatcher("search.jsp").forward(request,response);
    }

    /**
     *      按照输入的项目的名字搜索
     *
     *          @param request
     *          @param response
     *          @throws ServletException
     *          @throws IOException
     */
    private void projectSearchByName(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        String projectName = request.getParameter("projectName");
        Project project = projectService.findProjectByName(projectName);
        request.setAttribute("projectList",project);
        request.getRequestDispatcher("search.jsp").forward(request,response);
    }


    /**
     *     多条件搜索
     *          @param request
     *          @param response
     */

    private void projectSearch(HttpServletRequest request, HttpServletResponse response) throws  ServletException, IOException {
        String title = request.getParameter("title");
        String date = request.getParameter("date");
        String type = request.getParameter("type");
        String where = request.getParameter("where");
        request.getSession().setAttribute("title",title);
        request.getSession().setAttribute("date",date);
        request.getSession().setAttribute("type",type);
        projectList(request,response,title,date,type,where);
        request.getRequestDispatcher("search.jsp").forward(request,response);
    }

    /**
     * 分页查询到后台返回的list列表
     * @param request
     * @param response
     * @param title
     * @param date
     * @param type
     */
    private void projectList(HttpServletRequest request, HttpServletResponse response,String title, String date,String type,String where) {
        //接受参数
        Integer pageNum = Integer.valueOf(request.getParameter("pageNum"));
        //调用service层方法，查询
        Page<Project> page = new ProjectService().findProjectListByPage(pageNum,title,date,type,where);
        //将page对象设置到request对象
        request.setAttribute("page",page);
    }

    /**
     * 加入或者更新一个项目
     *          @param request
     *          @param response
     *          @throws IOException
     *          @throws ServletException
     */

    private void projectaddOrUpdate(HttpServletRequest request, HttpServletResponse response) throws IOException, ServletException {
        //接受参数，头像，名称，类型，简介，内容
        String head = request.getParameter("head");//头像
        String content = request.getParameter("content");//内容
        String name = request.getParameter("name");//名称
        String type = request.getParameter("type");//
        User user = (User) request.getSession().getAttribute("user");
        Integer userId = user.getUserId();
        //调用Service层方法，返回resultInfo对象
        resultInfo<Project> resultInfo= projectService.addOrUpdate(head,content,name,type,userId);
        if (resultInfo.getCode() == 1){
            //重定向到首页个人主页
            response.sendRedirect("personalindex");
        }else{
            request.setAttribute("resultInfo",request);
            //请求转发到project?actionName=view
            request.getRequestDispatcher("project?actionName=view").forward(request,response);
        }
        //
    }

}
