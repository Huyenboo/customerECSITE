<%@ page contentType="text/html;charset=UTF-8" language="java" %>

<!DOCTYPE html>
<html lang="ja">
<head>
    <meta charset="UTF-8">
    <title>管理者ログイン</title>
    <%
    String error = (String) request.getAttribute("error");
    if (error != null) {
%>
    <p style="color:red;"><%= error %></p>
<%
    }
%>
    <style>
        body {
            font-family: "Yu Gothic UI", sans-serif;
            background-color: #e6f0fa;
            display: flex;
            justify-content: center;
            align-items: center;
            height: 100vh;
        }
        .login-box {
            background-color: white;
            padding: 40px;
            border-radius: 20px;
            box-shadow: 0 15px 30px rgba(0,0,0,0.1);
            width: 90%;
            max-width: 400px;
            text-align: center;
        }
        h1 {
            color: #2c7be5;
            margin-bottom: 30px;
        }
        input[type="text"], input[type="password"] {
            width: 100%;
            padding: 12px;
            margin-bottom: 20px;
            border: 1px solid #ccc;
            border-radius: 10px;
            font-size: 16px;
        }
        button {
            padding: 12px;
            width: 100%;
            background-color: #2c7be5;
            color: white;
            border: none;
            border-radius: 10px;
            font-size: 16px;
        }
    </style>
</head>
<body>
<div class="login-box">
    <h1>管理者ログイン</h1>
    <form action="<%=request.getContextPath()%>/AdminLoginServlet" method="post">
        <input type="text" name="emp_id" placeholder="社員ID" required>
        <input type="password" name="pass" placeholder="パスワード" required>
        <button type="submit">ログイン</button>
    </form>
</div>
</body>
</html>
