/*
 * Generated by the Jasper component of Apache Tomcat
 * Version: Apache Tomcat/7.0.37
 * Generated at: 2021-09-11 06:07:47 UTC
 * Note: The last modified time of this file was set to
 *       the last modified time of the source file after
 *       generation to assist with modification tracking.
 */
package org.apache.jsp.person;

import javax.servlet.*;
import javax.servlet.http.*;
import javax.servlet.jsp.*;

public final class user_005fprivacy_jsp extends org.apache.jasper.runtime.HttpJspBase
    implements org.apache.jasper.runtime.JspSourceDependent {

  private static final javax.servlet.jsp.JspFactory _jspxFactory =
          javax.servlet.jsp.JspFactory.getDefaultFactory();

  private static java.util.Map<java.lang.String,java.lang.Long> _jspx_dependants;

  private javax.el.ExpressionFactory _el_expressionfactory;
  private org.apache.tomcat.InstanceManager _jsp_instancemanager;

  public java.util.Map<java.lang.String,java.lang.Long> getDependants() {
    return _jspx_dependants;
  }

  public void _jspInit() {
    _el_expressionfactory = _jspxFactory.getJspApplicationContext(getServletConfig().getServletContext()).getExpressionFactory();
    _jsp_instancemanager = org.apache.jasper.runtime.InstanceManagerFactory.getInstanceManager(getServletConfig());
  }

  public void _jspDestroy() {
  }

  public void _jspService(final javax.servlet.http.HttpServletRequest request, final javax.servlet.http.HttpServletResponse response)
        throws java.io.IOException, javax.servlet.ServletException {

    final javax.servlet.jsp.PageContext pageContext;
    javax.servlet.http.HttpSession session = null;
    final javax.servlet.ServletContext application;
    final javax.servlet.ServletConfig config;
    javax.servlet.jsp.JspWriter out = null;
    final java.lang.Object page = this;
    javax.servlet.jsp.JspWriter _jspx_out = null;
    javax.servlet.jsp.PageContext _jspx_page_context = null;


    try {
      response.setContentType("text/html;charset=UTF-8");
      pageContext = _jspxFactory.getPageContext(this, request, response,
      			null, true, 8192, true);
      _jspx_page_context = pageContext;
      application = pageContext.getServletContext();
      config = pageContext.getServletConfig();
      session = pageContext.getSession();
      out = pageContext.getOut();
      _jspx_out = out;

      out.write("\r\n");
      out.write("\r\n");
      out.write("\r\n");
      out.write("<div class=\"user_privacy user_resouce\">\r\n");
      out.write("        <h1>关于隐私</h1>\r\n");
      out.write("        <div class=\"user_privacy-content\">\r\n");
      out.write("            <h2>隐私声明</h2>\r\n");
      out.write("            <p>下列是我们团队的关于隐私声明</p>\r\n");
      out.write("            <ul>\r\n");
      out.write("                <li>不搜集用户个人信息</li>\r\n");
      out.write("                <li>IP 地址仅用于统计</li>\r\n");
      out.write("                <li>不与第三方分享统计报告</li>\r\n");
      out.write("                <li>安全措施防止敏感信息的误用</li>\r\n");
      out.write("                <li>不使用 Cookie 跟踪任何个人信息</li>\r\n");
      out.write("            </ul>\r\n");
      out.write("            <hr>\r\n");
      out.write("            <h2>个人用户信息</h2>\r\n");
      out.write("            <p>我们不泄露也不利用有关用户或访问者的任何信息。</p>\r\n");
      out.write("            <p>可以以游客的身份浏览页面。</p>\r\n");
      out.write("            <hr>\r\n");
      out.write("            <h2>日志文件</h2>\r\n");
      out.write("            <p>我们不会与第三方共享统计报告。</p>\r\n");
      out.write("            <p>IP 地址不会关联个人可辨识信息。</p>\r\n");
      out.write("            <p>使用 IP 地址来诊断服务器问题、分析趋势、管理站点以及搜集统计信息。</p>\r\n");
      out.write("            <p>在防止敏感信息丢失或误用方面拥有标准的安全措施。</p>\r\n");
      out.write("            <hr>\r\n");
      out.write("            <h2>如有隐私问题请联系我们</h2>\r\n");
      out.write("            <p>86-1008611</p>\r\n");
      out.write("        </div>\r\n");
      out.write("    </div>");
    } catch (java.lang.Throwable t) {
      if (!(t instanceof javax.servlet.jsp.SkipPageException)){
        out = _jspx_out;
        if (out != null && out.getBufferSize() != 0)
          try { out.clearBuffer(); } catch (java.io.IOException e) {}
        if (_jspx_page_context != null) _jspx_page_context.handlePageException(t);
        else throw new ServletException(t);
      }
    } finally {
      _jspxFactory.releasePageContext(_jspx_page_context);
    }
  }
}
