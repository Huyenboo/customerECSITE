<%@ page contentType="text/html; charset=UTF-8" %>
<%@ page import="java.util.List, com.adminbean.AdminUserBean, com.bean.CartItem" %>
<%
	AdminUserBean user = (AdminUserBean) session.getAttribute("loginUser");	
    if (user == null) {
        response.sendRedirect(request.getContextPath() + "/salesTop.jsp");
        return;
    }
%>
<html><head><title>注文管理</title>
<style>
.container { max-width:900px; margin:20px auto; }
table { width:100%; border-collapse:collapse; }
th, td { border:1px solid #333; padding:4px; font-size:90%; }
.order-header { background:#eee; }
.controls { margin-bottom:15px; }
</style>
</head><body>
<div class="container">
  <h1>注文管理</h1>
  <p><%= user.getEmp_name() %> さん（<%= user.getRole_name() %>）</p>

  <div class="controls">
    <form method="get" action="OrderManagementServlet" style="display:inline;">
      顧客ID：
      <input type="text" name="userId" value="<%= request.getParameter("userId")==null ? "" : request.getParameter("userId") %>" />
      <input type="submit" value="検索" />
    </form>
    <a href="<%= request.getContextPath()%>jsp/salesTop.jsp">TOPへ戻る</a>
  </div>

  
</div>
</body></html>
