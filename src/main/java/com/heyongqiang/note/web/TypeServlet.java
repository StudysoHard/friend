package com.heyongqiang.note.web;

import com.heyongqiang.note.po.Project;
import com.heyongqiang.note.po.ProjectType;
import com.heyongqiang.note.po.Type;
import com.heyongqiang.note.service.ProjectService;
import com.heyongqiang.note.service.TypeService;
import com.heyongqiang.note.util.Page;
import com.heyongqiang.vo.resultInfo;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.List;

@WebServlet("/type")
public class TypeServlet extends HttpServlet {
    private TypeService typeService = new TypeService();
    resultInfo resultInfo = null;
    @Override
    protected void service(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        String actionName = request.getParameter("actionName");
        if(actionName.equals("showAllProject")){
            loadInType(request,response);
        }
    }

    private void loadInType(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        //获取typeName
        Integer typeId = Integer.valueOf(request.getParameter("typeId"));
        Integer pageNum = Integer.valueOf(request.getParameter("pageNum"));
        //获取指定类型的数据和目的数据库下的符合的项目
        Page<Project> page = typeService.showAllProject(typeId,pageNum);
        Type typeInformation = typeService.findTypeInformation(typeId);
        request.setAttribute("typeInformation",typeInformation);
        request.setAttribute("projectList",page.getDataList());
        System.out.println("page是"+page.getDataList().size());
        request.setAttribute("page",page);
        request.setAttribute("count",page.getTotalCount());
        //成功之后重定向到type.jsp
        request.getRequestDispatcher("type.jsp").forward(request,response);
    }

}
