package com.heyongqiang.note.web;

import com.heyongqiang.note.dao.BaseDao;
import com.heyongqiang.note.po.Member;
import com.heyongqiang.note.po.Project;
import com.heyongqiang.note.po.User;
import com.heyongqiang.note.service.MemberService;
import com.heyongqiang.note.service.ProjectService;
import com.heyongqiang.note.util.Page;
import com.heyongqiang.vo.resultInfo;


import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;


@WebServlet("/member")
public class MemberServlet extends HttpServlet {
    Integer projectId = 0;
    resultInfo resultInfo = new resultInfo();
    MemberService memberService = new MemberService();
    @Override
    protected void service(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        String actionName = request.getParameter("actionName");
        if("showMystaff".equals(actionName)){
            showMyMember(request,response);
        } else if("PageMove".equals(actionName)){
            pageMove(request,response);
        } else if("moveMember".equals(actionName)){
            memberMove(request,response);
        } else if("upMember".equals(actionName)){
            memberUp(request,response);
        } else if("downMember".equals(actionName)){
            memberDown(request,response);
        } else if("agreeThisPost".equals(actionName)){
            agreeThisPost(request,response);
        }
    }


    /**
     * 同意这个请求了
     * @param request
     * @param response
     */
    private void agreeThisPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        Integer projectId = Integer.valueOf(request.getParameter("projectId"));
        String userName = request.getParameter("userName");
        User user = (User) request.getSession().getAttribute("user");
        Integer createUserId = user.getUserId();
        //进入service函数
        resultInfo resultInfo = memberService.agreeThisPost(projectId,userName,createUserId);
        request.setAttribute("resultInfo",resultInfo);
        request.getRequestDispatcher("success.jsp").forward(request,response);
    }


    /**
     * 降低组员的权限
     * @param request
     * @param response
     */
    private void memberDown(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        Integer memberAssionment = Integer.valueOf(request.getParameter("memberAssionment"));
        Integer projectId = Integer.valueOf(request.getParameter("projectId"));
        String userName = request.getParameter("userName");
        //权限判断
        if(memberAssionment <= 0){
            resultInfo.setMsg("权限已经最低了!!!");
            returnMember(request,response,projectId);
        } else {
            resultInfo<Member> resultInfo = memberService.downMeberAssionment(memberAssionment-1,userName,projectId);
            returnMember(request,response,projectId);

        }
    }

    /**
     * 提高组员的权限
     * @param request
     * @param response
     */

    private void memberUp(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        Integer memberAssionment = Integer.valueOf(request.getParameter("memberAssionment"));
        Integer projectId = Integer.valueOf(request.getParameter("projectId"));
        String userName = request.getParameter("userName");
        //权限判断
        if(memberAssionment >= 2){
            resultInfo.setMsg("权限已经最高了!!!");
            returnMember(request,response,projectId);
        } else {
            resultInfo<Member> resultInfo = memberService.upMemberAssionment(memberAssionment+1,userName,projectId);
            returnMember(request,response,projectId);
        }
    }


    /**
     * 移除这个成员
     *
     * @param request
     * @param response
     */
    private void memberMove(HttpServletRequest request, HttpServletResponse response) {
        //获得组员的名字
        String memberName = request.getParameter("userName");

    }

    /**
     * 页面移动
     * @param request
     * @param response
     */
    private void pageMove(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        Integer pageNum = Integer.valueOf(request.getParameter("pageNum"));
        Page<Member> page = new MemberService().findMemberByProject(pageNum,projectId);
        request.setAttribute("memberPage",page);
        long count = 0;
        if(page != null){
            count = page.getTotalCount();
        }
        request.setAttribute("count",count);
        request.getRequestDispatcher("member.jsp").forward(request,response);
    }

    /**
     * 进入主页大的管理的项目
     * @param request
     * @param response
     */
    private void showMyMember(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        //实现分页技术
        projectId = Integer.valueOf(request.getParameter("projectId"));
        Integer pageNum = Integer.valueOf(request.getParameter("pageNum"));
        Page<Member> memberPage = new MemberService().findMemberByProject(pageNum,projectId);
        long count = 0;
        if(memberPage != null){
            count = memberPage.getTotalCount();
        }
        Project project = new ProjectService().findProjectByprojectId(projectId);
        request.setAttribute("count",count);
        request.setAttribute("memberPage",memberPage);
        request.setAttribute("project",project);
        //页面跳转
        request.getRequestDispatcher("member.jsp").forward(request,response);
    }

    private void returnMember(HttpServletRequest request,HttpServletResponse response,Integer projectId) throws ServletException, IOException {
        Page<Member> memberPage = new MemberService().findMemberByProject(1,projectId);
        Project project = new ProjectService().findProjectByprojectId(projectId);
        long count = 0;
        if(memberPage != null){
            count = memberPage.getTotalCount();
        }
        request.setAttribute("project",project);
        request.setAttribute("count",count);
        request.setAttribute("memberPage",memberPage);
        //页面跳转
        request.getRequestDispatcher("member.jsp").forward(request,response);
    }
}
