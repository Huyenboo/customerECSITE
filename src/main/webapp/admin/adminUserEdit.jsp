<%@ page contentType="text/html; charset=UTF-8" %>
<%@ page import="com.adminbean.AdminUserBean" %>
<%

AdminUserBean user = (AdminUserBean) request.getAttribute("userEdit");
String errorMsg = (String) request.getAttribute("errorMsg");

if (user == null) {
    response.sendRedirect(request.getContextPath() + "/empListServlet");
    return;
}
%>
<!DOCTYPE html>
<html lang="ja">
<head>
<meta charset="UTF-8">
<title>社員情報編集</title>
<style>
    body { font-family: "Yu Gothic UI", sans-serif; background-color: #fff; padding: 30px; }
    .container { max-width: 500px; margin: 0 auto; border: 1px solid #ccc; padding: 20px; border-radius: 8px; background-color: #f9f9f9; }
    h1 { text-align: center; margin-bottom: 20px; }
    .field { margin-bottom: 15px; display: flex; align-items: center; }
    label { width: 100px; font-weight: bold; }
    input[type="text"], input[type="password"] { flex: 1; padding: 5px; }
    .buttons { text-align: center; margin-top: 20px; }
    .buttons input { padding: 6px 15px; margin: 0 10px; }
    .error { color: red; text-align: center; margin-bottom: 15px; }
</style>
</head>
<body>

<div class="container">
    <h1>社員情報編集</h1>

    <% if (errorMsg != null) { %>
        <p class="error"><%= errorMsg %></p>
    <% } %>

    <form action="<%= request.getContextPath() %>/UpdateEmployeeServlet" method="post">
        <div class="field">
            <label>社員番号:</label>
            <%= user.getEmp_id() %>
            <input type="hidden" name="emp_id" value="<%= user.getEmp_id() %>">
        </div>

        <div class="field">
            <label>社員名:</label>
            <input type="text" name="emp_name" maxlength="100" value="<%= user.getEmp_name() != null ? user.getEmp_name() : "" %>">
        </div>

        <div class="field">
            <label>権限:</label>
            <label><input type="radio" name="role_id" value="1" <%= user.getRole_id() == 1 ? "checked" : "" %>> 管理者</label>
            <label><input type="radio" name="role_id" value="2" <%= user.getRole_id() == 2 ? "checked" : "" %>> 部長</label>
            <label><input type="radio" name="role_id" value="3" <%= user.getRole_id() == 3 ? "checked" : "" %>> 営業部</label>
        </div>

        <div class="field">
            <label>役職:</label>
            <input type="text" name="emp_position" maxlength="50" value="<%= user.getEmp_position() != null ? user.getEmp_position() : "" %>">
        </div>

        <div class="field">
            <label>パスワード:</label>
            <input type="password" name="pass" maxlength="50" value="<%= user.getPass() != null ? user.getPass() : "" %>">
        </div>

        <div class="buttons">
            <input type="submit" value="更新">
            <input type="button" value="キャンセル" onclick="location.href='<%= request.getContextPath() %>/empListServlet';">
        </div>
    </form>
</div>

</body>
</html>
