package com.heyongqiang.note.web;

import com.heyongqiang.note.po.Project;
import com.heyongqiang.note.po.ProjectRequest;
import com.heyongqiang.note.po.User;
import com.heyongqiang.note.service.ProjectService;
import com.heyongqiang.note.service.UserService;
import com.heyongqiang.note.util.Page;
import org.apache.commons.io.FileUtils;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.File;
import java.io.IOException;
import java.util.List;


@WebServlet("/person")
public class PersonServlet extends HttpServlet {
    UserService userService = new UserService();
    @Override
    protected void service(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        String actionName = request.getParameter("actionName");
        if("movePage".equals(actionName)){
            pageChange(request,response);
        } else if("showMyProject".equals(actionName)){
            showMyProject(request,response);
        } else if("personPage".equals(actionName)){
            pageMove(request,response);
        } else if("projectJoin".equals(actionName)){
            projectJoin(request,response);
        } else if("joinMove".equals(actionName)){
            joinMove(request,response);
        }  else if("requestHead".equals(actionName)){
            requestHead(request,response);
        }
    }

    //加载头像
    private void requestHead(HttpServletRequest request, HttpServletResponse response) throws IOException {
        //获取参数（图片名称）
        Integer userId = Integer.valueOf(request.getParameter("userId"));
        //得到图片存放路径（得到项目的真实路径）,在WEB-INF中创个
        String realPath = request.getServletContext().getRealPath("/userImg");
        //通过图片的完整路径，得到file对象
        File file = new File(realPath+"/"+userId+".png");
        //通过截取，得到后缀名
        //通过不同的后缀名，设置不同的响应类型
        response.setContentType("image/png");
        //利用FileUtils的copyFile(),将图片拷贝给浏览器
        FileUtils.copyFile(file,response.getOutputStream());
    }

    /**
     * 分页
     * @param request
     * @param response
     */
    private void joinMove(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        Integer pageNum = Integer.valueOf(request.getParameter("pageNum"));
        String active = request.getParameter("active");
        String pageNav = request.getParameter("pageNav");
        pageNav = "person/"+ pageNav;
        User user = (User) request.getSession().getAttribute("user");
        Page<Project> page = new ProjectService().findMyJoinProject(pageNum,user.getUserId(),user.getUserName());
        request.setAttribute("personJoined",page);
        request.setAttribute("changePage",pageNav);
        long count = 0;
        if(page != null){
            count = page.getTotalCount();
        }
        request.setAttribute("active",active);
        request.setAttribute("pageCount",count);
        pageChange(request,response);
    }

    /**
     * 我加入的项目
     * @param request
     * @param response
     */

    private void projectJoin(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        //点击个人加入的项目页面
        User user = (User) request.getSession().getAttribute("user");
        Integer userId = user.getUserId();
        String userName = user.getUserName();
        Integer pageNum = Integer.valueOf(request.getParameter("pageNum"));
        Page<Project> personJoined = new ProjectService().findMyJoinProject(pageNum,userId,userName);
        long count = 0;
        if(personJoined != null){
            count = personJoined.getTotalCount();
        }
        User realUser = (User) request.getSession().getAttribute("user");
        List<ProjectRequest> list =  userService.findProjectRequest(realUser.getUserId());
        request.setAttribute("first",0);
        if(list.size() != 0){
            request.getSession().setAttribute("first",list.get(0).getRequestId());
            request.getSession().setAttribute("count_request",list.size());
            request.setAttribute("first",list.get(0).getRequestId());
            request.setAttribute("count_request",list.size());
        }
        request.getSession().setAttribute("requestList",list);
        request.setAttribute("requestList",list);
        request.setAttribute("pageCount",count);
        request.setAttribute("personJoined",personJoined);
        //页面跳转
        pageMove(request,response);
    }

    /**
     * 页面移动
     * @param request
     * @param response
     */
    private void pageMove(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        Integer pageNum = Integer.valueOf(request.getParameter("pageNum"));
        String pageNav = request.getParameter("pageNav");
        pageNav = "person/"+ pageNav;
        User user = (User) request.getSession().getAttribute("user");
        Integer userId = user.getUserId();
        Page<Project> page = new ProjectService().findProjectByUserId(pageNum,userId);
        request.setAttribute("personCreatePage",page);
        request.setAttribute("changePage",pageNav);
        long count = 0;
        if(page != null){
            count = page.getTotalCount();
        }
        request.setAttribute("count",count);
        pageChange(request,response);
    }

    /**
     * 进入主页大的创建的项目
     * @param request
     * @param response
     */
    private void showMyProject(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        //点击个人创建的项目页面
        String page = request.getParameter("pageNav");
        String active = request.getParameter("active");
        String changePage = "person/"+page;
        //实现分页技术
        User user = (User) request.getSession().getAttribute("user");
        Integer userId = user.getUserId();
        Integer pageNum = Integer.valueOf(request.getParameter("pageNum"));
        Page<Project> personCreatePage = new ProjectService().findProjectByUserId(pageNum,userId);
        long count = 0;
        if(personCreatePage != null){
            count = personCreatePage.getTotalCount();
        }
        request.setAttribute("count",count);
        request.setAttribute("personCreatePage",personCreatePage);
        request.setAttribute("active",active);
        //页面跳转
        request.setAttribute("changePage",changePage);
        request.getRequestDispatcher("person.jsp").forward(request,response);
    }

    /**
     * 修改个人页面的主体部分
     * @param request
     * @param response
     * @throws ServletException
     * @throws IOException
     */

    private void pageChange(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        String page = request.getParameter("pageNav");
        String active = request.getParameter("active");
        String changePage = "person/"+page;
        request.setAttribute("changePage",changePage);
        request.setAttribute("active",active);
        request.getRequestDispatcher("person.jsp").forward(request,response);
    }
}
