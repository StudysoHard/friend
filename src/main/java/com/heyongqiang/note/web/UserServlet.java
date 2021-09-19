package com.heyongqiang.note.web;

import cn.hutool.core.util.StrUtil;
import com.heyongqiang.note.po.*;
import com.heyongqiang.note.service.IndexService;
import com.heyongqiang.note.service.MemberService;
import com.heyongqiang.note.service.UserService;
import com.heyongqiang.vo.resultInfo;
import org.apache.commons.fileupload.FileItem;
import org.apache.commons.fileupload.FileItemFactory;
import org.apache.commons.fileupload.disk.DiskFileItemFactory;
import org.apache.commons.fileupload.servlet.ServletFileUpload;
import org.apache.commons.io.FileUtils;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.File;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.Iterator;
import java.util.List;


@WebServlet("/user")
public class UserServlet extends HttpServlet {
    private UserService userService = new UserService();
    private MemberService memberService = new MemberService();
    @Override
    protected void service(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        //获取用户行为
        String actionName = request.getParameter("actionName");
        //判断用户行为 调用回应的方法
        if ("login".equals(actionName)) {
            userLogin(request, response);
        }
        //判读用户行为 调用回应方法
        else if ("register".equals(actionName)) {
            userRegister(request, response);
        }
        else if("userHead".equals(actionName)){
            //加载头像
            userHead(request,response);
        } else if("changeUserInformation".equals(actionName)){
            //修改个人信息
            changeInformation(request,response);
        }  else if("IntoElsePerosn".equals(actionName)){
            //进入他人主页
            IntoElsePerson(request,response);
        } else if("deleteUser".equals(actionName)){
            deleteUser(request,response);
        }
    }


    private void changeInformation(HttpServletRequest request, HttpServletResponse response) throws IOException  {
        User user = (User) request.getSession().getAttribute("user");
        Integer userId = user.getUserId();
        String preUserName = user.getUserName();
        String userName = "";
        String userTelephone = "";
        String userLabel = "";
        String userContent = "";
        String userEmail = "";
        String userBirthday = "";
        boolean isMultipart = ServletFileUpload.isMultipartContent(request);
        PrintWriter writer = response.getWriter();
        StringBuffer fileStr = new StringBuffer();  //上传的文件名，最后输出用
        try {
            if (isMultipart) {
                FileItemFactory factory = new DiskFileItemFactory();  //工厂实例
                ServletFileUpload upload = new ServletFileUpload(factory);  //ServletFileUpload实例依赖于FileItemFactory工厂
                List<FileItem> itemList = upload.parseRequest(request);  //解析表单字段，封装成一个FileItem实例的集合
                Iterator<FileItem> iterator = itemList.iterator();  //迭代器
                while (iterator.hasNext()) {
                    FileItem fileItem = iterator.next();  //依次解析每一个FileItem实例，即表单字段
                    String fileName = "";
                    if (!fileItem.isFormField()) {
                        String FileName = String.valueOf(userId);
                        //文件表单字段
                        String fileUpName = FileName + ".png";  //用户上传的文件名
                        File fileImg = new File("C:\\java项目\\r_note\\src\\main\\webapp\\userImg\\"+fileUpName);  //要保存到图片路径
                        //新建项目资源目录
                        if (!fileImg.exists()) {
                            fileImg.createNewFile();  //一开始肯定是没有的，所以先创建出来
                        }
                        fileItem.write(fileImg);  //写入，保存到目标文件
                        fileStr.append(fileUpName + "、");
                    }
                    else {
                        //普通表单字段
                        if (fileItem.getFieldName().equals("userName")) {
                            userName = fileItem.getString("UTF-8");
                            user.setUserName(userName);
                        } else if (fileItem.getFieldName().equals("userTelephone")) {
                            userTelephone = fileItem.getString("UTF-8");
                            user.setUserTelephone(userTelephone);
                        } else if (fileItem.getFieldName().equals("userLabel")) {
                            userLabel = fileItem.getString("UTF-8");
                            user.setUserLabel(userLabel);
                        } else if (fileItem.getFieldName().equals("userContent")) {
                            userContent = fileItem.getString("UTF-8");
                            user.setUserContent(userContent);
                        } else if (fileItem.getFieldName().equals("userEmail")) {
                            userEmail = fileItem.getString("UTF-8");
                            user.setUserEmail(userEmail);
                        } else if(fileItem.getFieldName().equals("sex")){
                            user.setUserSex(Integer.valueOf(fileItem.getString("UTF-8")));
                        } else if(fileItem.getFieldName().equals("userBirthday")){
                            userBirthday = fileItem.getString("UTF-8");
                            user.setUserBirthday(userBirthday);
                        }
                    }
                }
                boolean flag_user = false;
                //说明修改过名字 就要查询一下姓名是否重复
                if(preUserName == userName){
                    flag_user = true;
                }
                resultInfo<User> resultInfo = userService.changeUser(user,flag_user);
                request.setAttribute("resultInfo",resultInfo);
                request.getRequestDispatcher("success.jsp").forward(request,response);
            }
        }catch(Exception e){

        }

    }


    private void deleteUser(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        //获取用户Id
        User user = (User) request.getSession().getAttribute("user");
        Integer userId = user.getUserId();
        resultInfo resultInfo = userService.deleteUser(userId);
        request.setAttribute("resultInfo",resultInfo);
        request.getSession().setAttribute("user", null);
        response.sendRedirect("login.jsp");
    }

    /**
     * 进入他人的主页
     * @param request
     * @param response
     */
    private void IntoElsePerson(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        //获得进入的用户的id
        Integer userid = Integer.valueOf(request.getParameter("userId"));
        User elseUser = userService.fingUserByUserId(userid);
        User user = (User) request.getSession().getAttribute("user");
        request.setAttribute("user",user);
        request.setAttribute("elsePerson",elseUser);
        request.getRequestDispatcher("elsePerson.jsp").forward(request,response);
    }



    /**
     * 用户登录
     *
     * @param request
     * @param response
     */
    private void userLogin(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        //获取参数 (姓名 密码)
        String userName = request.getParameter("username");
        String userPwd = request.getParameter("userpwd");
        //调用service层的方法 返回resultInfo对象
        resultInfo<User> resultInfo = userService.userLogin(userName, userPwd);

        //判断是否登录成功
        if (resultInfo.getCode() == 1) {
            //成功
            request.getSession().setAttribute("user", resultInfo.getResult());
            request.getSession().setAttribute("username", resultInfo.getResult().getUserName());
            String[] rem = new String[5];
            rem =  request.getParameterValues("rem");
            if(rem != null&&rem.length > 0 ){
                    //记住密码 得到cookie对象
                //   Cookie cookie = new Cookie("user", URLEncoder.encode(userName, "UTF-8") + "-" + userPwd);
                Cookie cookie = new Cookie("user", userName + "-"+ userPwd);
                    cookie.setMaxAge(24 * 60 * 60);
                    response.addCookie(cookie);
            }  else {
                Cookie cookie = new Cookie("user", null);
                cookie.setMaxAge(0);
                response.addCookie(cookie);
            }
            //加载主页的推荐模块
            IndexService indexService = new IndexService();
            List<IndexProject> IndexProject1 = indexService.showIndexProject(0);
            List<IndexProject> IndexProject2 = indexService.showIndexProject(4);
            request.getSession().setAttribute("indexProject1",IndexProject1);
            request.getSession().setAttribute("indexProject2",IndexProject2);
            List<IndexType> IndexType1 = indexService.showIndexType(0);
            List<IndexType> IndexType2 = indexService.showIndexType(3);
            request.getSession().setAttribute("indexType1",IndexType1);
            request.getSession().setAttribute("indexType2",IndexType2);
            //回到首页
            response.sendRedirect("index.jsp");
        } else {
            //失败
            request.setAttribute("resultInfo", resultInfo);
            request.getRequestDispatcher("login.jsp").forward(request, response);
        }
    }

    private void userRegister(HttpServletRequest request, HttpServletResponse response) throws IOException, ServletException {
        String userName = request.getParameter("username");
        String userPwd = request.getParameter("userpwd");
        String userTelephone = request.getParameter("usertelephone");
        String userRealName = request.getParameter("userrealname");
        Integer userSex = Integer.valueOf(request.getParameter("usersex"));
        String birthday = request.getParameter("birthday");
        String userLargestStudy = request.getParameter("userlargestStudy");
//        System.out.println(userSex);
//        System.out.println(userName);
//        System.out.println(userPwd);
//        System.out.println(userLargestStudy);
//        System.out.println(userTelephone);
//        System.out.println(userRealName);
//        System.out.println(birthday);
        //调用servlce的方法
        resultInfo<User> resultInfo = userService.userRegister(userName, userPwd, userTelephone,userRealName,userSex,birthday,userLargestStudy);
        if (resultInfo.getCode() == 1) {
            //注册成功
            request.getRequestDispatcher("register_success.jsp").forward(request,response);
        } else {
            request.setAttribute("resultInfo",resultInfo);
            request.getRequestDispatcher("register.jsp").forward(request,response);
        }
    }


    //加载头像
    private void userHead(HttpServletRequest request, HttpServletResponse response) throws IOException {
        //获取参数（图片名称）
        User user = (User) request.getSession().getAttribute("user");
        String head = user.getUserHead();
        if(StrUtil.isBlank(head)){
            head = "simple.jpg";
        }
        //得到图片存放路径（得到项目的真实路径）,在WEB-INF中创个
        String realPath = request.getServletContext().getRealPath("/userImg");
        //通过图片的完整路径，得到file对象
        File file = new File(realPath+"/"+head);
        //通过截取，得到后缀名
        String pic = head.substring(head.lastIndexOf(".")+1);
        //通过不同的后缀名，设置不同的响应类型
        if("PNG".equalsIgnoreCase(pic)){
            response.setContentType("image/png");
        }else if("JPG".equalsIgnoreCase(pic) || "JPEG".equalsIgnoreCase(pic)){
            response.setContentType("image/ipeg");
        }else if("GIF".equalsIgnoreCase(pic)){
            response.setContentType("image/gif");
        }
        //利用FileUtils的copyFile(),将图片拷贝给浏览器
        FileUtils.copyFile(file,response.getOutputStream());
    }
}
