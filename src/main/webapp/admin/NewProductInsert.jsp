<%@ page contentType="text/html; charset=UTF-8" %>
<%@ page import="com.adminbean.AdminUserBean" %>
<%
    AdminUserBean user = (AdminUserBean) session.getAttribute("loginUser");;
    if (user == null) {
        response.sendRedirect(request.getContextPath() + "/ReturnAdminLoginServlet");
        return;
    }
    String error = (String) request.getAttribute("error");
%>
<html>
<head>
  <title>新規商品登録</title>
  <style>
    .container { max-width:600px; margin:0 auto; padding:10px; }
    .field { margin-bottom:10px; }
    label { display:inline-block; width:80px; }
  </style>
</head>
<body>
  <div class="container">
    <h1>新規商品登録</h1>
    <% if (error != null) { %>
	<p style="color: red;"><%=error %></p>
	<% } %>

    <form action="<%= request.getContextPath() %>/AdminNewProductServlet" method="post">
      <div class="field"><label>ID:</label><input type="text" name="proId" required /></div>
      <div class="field"><label>商品名:</label><input type="text" name="proName" required /></div>
      <div class="field"><label>単価:</label><input type="text" name="proPrice" required /></div>
      <div class="field"><label>在庫数:</label><input type="text" name="proUnitNum" required /></div>
      <div class="field"><label>備考:</label><textarea name="proMemo" rows="3" cols="30"></textarea></div>
      <div class="field">
        <input type="submit" value="登録" />
        <input type="reset" value="クリア" />
        <input type="button" value="戻る" onclick="location.href='ProductManagementServlet';" />
      </div>
    </form>
  </div>
</body>
</html>
