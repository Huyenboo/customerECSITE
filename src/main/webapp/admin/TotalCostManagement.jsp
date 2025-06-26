<%@ page contentType="text/html; charset=UTF-8" %>
<%@ page import="java.util.List, com.adminbean.AdminUserBean, bean.TotalCost" %>
<%
    AdminUserBean user = (AdminUserBean) session.getAttribute("loginUser");
    if (user == null) {
        response.sendRedirect(request.getContextPath() + "/adminLogin.jsp");
        return;
    }
%>
<html><head><title>売上管理</title>
<style>
.container { max-width:800px; margin:20px auto; font-family:Arial; }
table { width:100%; border-collapse:collapse; }
th, td { border:1px solid #333; padding:8px; text-align:center; }
th { background:#f2f2f2; }
.header { margin-bottom:15px; }
</style>
</head><body>
<div class="container">
  <h1>売上日計表</h1>
  <p>担当者：<%= user.getEmp_name() %>（<%= user.getRole_name() %>）</p>

  <table>
    <tr>
      <th>日付</th><th>売上金額</th><th>件数</th><th>平均単価</th>
    </tr>
    <%
        @SuppressWarnings("unchecked")
        List<TotalCost> totalList = (List<TotalCost>) request.getAttribute("totalList");
        if (totalList != null && !totalList.isEmpty()) {
            for (TotalCost t : totalList) {
    %>
    <tr>
      <td><%= t.getDate() %></td>
      <td>¥<%= t.getTotalAmount() %></td>
      <td><%= t.getCount() %></td>
      <td>¥<%= t.getAveragePrice() %></td>
    </tr>
    <% 
            }
        } else {
    %>
    <tr><td colspan="4">データがありません</td></tr>
    <% } %>
  </table>

  <form action="<%=request.getContextPath()%>/salesTop.jsp" method="get" style="margin-top:10px;">
    <input type="submit" value="TOPへ戻る" />
  </form>
</div>
</body></html>
