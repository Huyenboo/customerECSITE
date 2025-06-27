<%@ page contentType="text/html; charset=UTF-8" %>
<%@ page import="com.adminbean.AdminUserBean" %>
<%
    AdminUserBean loginUser = (AdminUserBean) session.getAttribute("loginUser");
    if (loginUser == null) {
        response.sendRedirect(request.getContextPath() + "/ReturnAdminLoginServlet");
        return;
    }

    AdminUserBean user = (AdminUserBean) request.getAttribute("userEdit");
    if (user == null) {
        response.sendRedirect(request.getContextPath() + "/AdminUserListServlet");
        return;
    }
%>
<!DOCTYPE html>
<html lang="ja">
<head>
    <meta charset="UTF-8">
    <title>社員管理編集</title>
    <style>
        body {
            font-family: "Yu Gothic UI", sans-serif;
            padding: 30px;
        }
        .container {
            max-width: 500px;
            margin: 0 auto;
            border: 1px solid #ccc;
            padding: 20px;
            border-radius: 8px;
        }
        h1 {
            text-align: center;
            margin-bottom: 20px;
        }
        .field {
            margin-bottom: 15px;
        }
        label {
            display: inline-block;
            width: 100px;
            font-weight: bold;
        }
        input[type="text"], input[type="password"] {
            width: 250px;
            padding: 5px;
        }
        .buttons {
            text-align: center;
            margin-top: 20px;
        }
        .buttons input {
            padding: 5px 15px;
            margin: 0 10px;
        }
    </style>
</head>
<body>
<div class="container">
    <h1>社員管理編集</h1>
    
    <form action="<%=request.getContextPath()%>/AdminUserUpdateServlet" method="post">
        <div class="field">
            <label>社員番号:</label>
            <%= user.getEmp_id() %>
            <input type="hidden" name="emp_id" value="<%= user.getEmp_id() %>">
        </div>

        <div class="field">
            <label>社員名:</label>
            <input type="text" name="emp_name" value="<%= user.getEmp_name() %>" required>
        </div>

        <div class="field">
            <label>権限:</label>
            <input type="radio" name="role_id" value="1" <%= user.getRole_id() == 1 ? "checked" : "" %>> 管理者
            <input type="radio" name="role_id" value="2" <%= user.getRole_id() == 2 ? "checked" : "" %>> 部長
            <input type="radio" name="role_id" value="3" <%= user.getRole_id() == 3 ? "checked" : "" %>> 一般社員
        </div>

        <div class="field">
            <label>役職:</label>
            <input type="text" name="emp_position" value="<%= user.getEmp_position() %>" required>
        </div>

        <div class="field">
            <label>パスワード:</label>
            <input type="password" name="pass" value="<%= user.getPass() %>" required>
        </div>

        <div class="buttons">
            <input type="submit" value="登録">
            <input type="button" value="キャンセル" onclick="history.back();">
        </div>
    </form>
</div>
</body>
</html>
