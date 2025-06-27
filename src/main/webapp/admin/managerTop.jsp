<%@ page contentType="text/html; charset=UTF-8" %>
<%@ page session="true" %>
<%@ page import="com.adminbean.AdminUserBean" %>

<%
	AdminUserBean user = (AdminUserBean) session.getAttribute("loginUser");

if (user == null) {
        response.sendRedirect("admin/adminLogin.jsp");
        return;
    }
%>
<!DOCTYPE html>
<html>
<head>
    <meta charset="UTF-8">
    <title>管理部 TOP</title>
    <style>
        body {
            display: flex;
            flex-direction: column;
            align-items: center; /* 横中央 */
            margin: 0;
            padding: 20px;
            font-family: Arial, sans-serif;
            
        }
        .logout-button {
            align-self: flex-end; /* 右上に配置 */
        }
        .menu-container {
            display: grid;
            grid-template-columns: repeat(2, 200px);
            grid-template-rows: repeat(2, 100px);
            gap: 20px;
            margin-top: 30px;
        }
        .menu-item {
            display: flex;
            align-items: center;
            justify-content: center;
            border: 1px solid #333;
            background-color: #f5f5f5;
            text-decoration: none;
            color: #000;
            font-size: 1.1em;
        }
        h1, h3 {
            text-align: center;
            margin: 10px 0;
        }
    </style>
</head>
<body>
    <form action="<%=request.getContextPath()%>/AdminLogoutServlet" method="post" class="logout-button">
        <input type="submit" value="ログアウト" />
    </form>

    <h1>管理部 TOP</h1>
    <h3>ようこそ <%= user.getRole_name() %> <%= user.getEmp_name() %> さん</h3>

    <div class="menu-container">
        <a href="<%=request.getContextPath()%>/empListServlet" class="menu-item">社員一覧・登録</a>
        <a href="<%=request.getContextPath()%>/newCustomerAllow" class="menu-item">顧客審査</a>

    </div>
</body>
</html>
