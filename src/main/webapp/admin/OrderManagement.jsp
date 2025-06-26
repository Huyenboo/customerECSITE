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

  <%
    @SuppressWarnings("unchecked")
  List<CartItem> orders = (List<CartItem>) request.getAttribute("orderList");
  if (orders != null && !orders.isEmpty()) {
      for (CartItem ci : orders) {
  %>
    <h3>注文ID: <%= ci.getOrderId() %>　顧客ID: <%= ci.getUserId() %>　発注日: <%= ci.getOrderDay() %></h3>
    <table>
      <tr class="order-header">
        <th>商品コード</th><th>数量</th><th>金額</th><th>到着予定日</th>
      </tr>
      <tr>
        <td><%= ci.getProduct().getProId() %></td>
        <td><%= ci.getQuantity() %></td>
        <td>¥<%= ci.getSubtotal() %></td>
        <td><%= ci.getDeliveryDate() %></td>
      </tr>
    </table>
    <p>備考：<%= ci.getOrderMemo()!=null ? ci.getOrderMemo() : "" %></p>
    <hr/>
  <%  }
    } else { %>
    <p>該当する注文履歴がありません。</p>
  <% } %>
</div>
</body></html>
