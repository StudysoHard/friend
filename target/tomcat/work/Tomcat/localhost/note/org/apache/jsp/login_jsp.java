/*
 * Generated by the Jasper component of Apache Tomcat
 * Version: Apache Tomcat/7.0.37
 * Generated at: 2021-09-10 11:54:22 UTC
 * Note: The last modified time of this file was set to
 *       the last modified time of the source file after
 *       generation to assist with modification tracking.
 */
package org.apache.jsp;

import javax.servlet.*;
import javax.servlet.http.*;
import javax.servlet.jsp.*;

public final class login_jsp extends org.apache.jasper.runtime.HttpJspBase
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
      out.write("<!DOCTYPE html>\r\n");
      out.write("<html lang=\"en\">\r\n");
      out.write("\r\n");
      out.write("<head>\r\n");
      out.write("    <meta charset=\"UTF-8\">\r\n");
      out.write("    <meta http-equiv=\"X-UA-Compatible\" content=\"IE=edge\">\r\n");
      out.write("    <meta name=\"viewport\" content=\"width=device-width, initial-scale=1.0\">\r\n");
      out.write("    <!-- ??????login.css -->\r\n");
      out.write("    <link rel=\"stylesheet\" href=\"./static/css/login.css\">\r\n");
      out.write("    <link rel=\"stylesheet\" href=\"./static/css/bootstrap.min.css\">\r\n");
      out.write("    <script src=\"static/js/jquery.js\"></script>\r\n");
      out.write("    <script src=\"static/js/util.js\"></script>\r\n");
      out.write("    <script src=\"./static/js/config.js\"></script>\r\n");
      out.write("    <script src=\"./static/js/bootstrap.min.js\"></script>\r\n");
      out.write("    <title>????????????</title>\r\n");
      out.write("</head>\r\n");
      out.write("<div class=\"container\">\r\n");
      out.write("    <div class=\"registerarea\">\r\n");
      out.write("        <h3 style=\"position: relative;margin-top: 10px;\" class=\"back\"><a href=\"./index.jsp\">????????????</a></h3>\r\n");
      out.write("        <h3 style=\"position: relative;margin-top: 10px;\" class=\"back\"><a href=\"./register.jsp\">?????????</a></h3>\r\n");
      out.write("        <h3>??????</h3>\r\n");
      out.write("        <div class=\"reg_form\">\r\n");
      out.write("            <form action=\"user\" method=\"post\" id=\"login_data\">\r\n");
      out.write("                <ul>\r\n");
      out.write("                    ");
      out.write("\r\n");
      out.write("                    <input type=\"hidden\" name=\"actionName\" value=\"login\">\r\n");
      out.write("                    <li><label>?????????:</label> <input type=\"text\" class=\"inp\" name=\"username\" id=\"user_name\" value=\"");
      out.write((java.lang.String) org.apache.jasper.runtime.PageContextImpl.proprietaryEvaluate("${resultInfo.getResult().getUserName()}", java.lang.String.class, (javax.servlet.jsp.PageContext)_jspx_page_context, null, false));
      out.write("\">\r\n");
      out.write("                        <span id=\"boolean_span_username\" style=\"color: #b1191a\">\r\n");
      out.write("                            </span></li>\r\n");
      out.write("                    <li><label>????????????:</label> <input type=\"password\" class=\"inp\" name=\"userpwd\" id=\"user_pwd\" value=\"");
      out.write((java.lang.String) org.apache.jasper.runtime.PageContextImpl.proprietaryEvaluate("${resultInfo.getResult().getUserPwd()}", java.lang.String.class, (javax.servlet.jsp.PageContext)_jspx_page_context, null, false));
      out.write("\">\r\n");
      out.write("                        <span id=\"boolean_span_pwd\" style=\"color: #b1191a\">\r\n");
      out.write("                            </span></li>\r\n");
      out.write("                        <li class=\"agree\" style=\"margin-left: 50px\">\r\n");
      out.write("                            <input type=\"checkbox\" id=\"rem\" name=\"rem\"> <P style=\"display: inline-block;\">?????????</P>\r\n");
      out.write("                        </li>\r\n");
      out.write("                    <li class=\"agree\">\r\n");
      out.write("                        <input type=\"checkbox\" checked> <p style=\"display: inline-block\">?????????????????????</p><a href=\"#\">????????????????????????</a>\r\n");
      out.write("                    </li>\r\n");
      out.write("                    <li style=\"position: absolute;margin-left: 122px\">\r\n");
      out.write("                        <span id=\"msg\" style=\"color: red;width: 50px;height: 50px\">");
      out.write((java.lang.String) org.apache.jasper.runtime.PageContextImpl.proprietaryEvaluate("${resultInfo.msg}", java.lang.String.class, (javax.servlet.jsp.PageContext)_jspx_page_context, null, false));
      out.write("</span>\r\n");
      out.write("                    </li>\r\n");
      out.write("                    <li>\r\n");
      out.write("                        <input type=\"button\" value=\"????????????\" class=\"btn\" onclick=\"checklogin()\">\r\n");
      out.write("                    </li>\r\n");
      out.write("                </ul>\r\n");
      out.write("            </form>\r\n");
      out.write("        </div>\r\n");
      out.write("    </div>\r\n");
      out.write("\r\n");
      out.write("    <div class=\"mod_copyright\">\r\n");
      out.write("        <div class=\"links\">\r\n");
      out.write("            ???????????? | ???????????? | ???????????? | ???????????? | English Site | Contact U\r\n");
      out.write("        </div>\r\n");
      out.write("        <div class=\"copyright\">\r\n");
      out.write("            ????????????????????? ?????????350001 ?????????123456 ?????????789456 ??????: nihaoxian.com ???ICP???08001421??????????????????110108007702\r\n");
      out.write("        </div>\r\n");
      out.write("\r\n");
      out.write("    </div>\r\n");
      out.write("</div>\r\n");
      out.write("\r\n");
      out.write("<body>\r\n");
      out.write("\r\n");
      out.write("</body>\r\n");
      out.write("\r\n");
      out.write("</html>");
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
