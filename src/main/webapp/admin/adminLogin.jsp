<%@ page contentType="text/html;charset=UTF-8" language="java" %>

<!DOCTYPE html>
<html lang="ja">
<head>
    <meta charset="UTF-8">
    <title>管理者ログイン</title>
    <style>
        body {
            font-family: "Yu Gothic UI", sans-serif;
            display: flex;
            justify-content: center;
            align-items: center;
            height: 100vh;
        }
        .login-box {
            background-color: white;
            padding: 40px;
            border-radius: 10px;
            box-shadow: none;
            border: 1px solid black;
            width: 90%;
            max-width: 400px;
            text-align: center;
        }
        h1 {
            color:black;
            margin-bottom: 20px;
        }
        .error-msg {
            color: red;
            margin-bottom: 15px;
            font-size: 14px;
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
            color: black;
            border: none;
            border-radius: 10px;
            font-size: 16px;
        }
    </style>
</head>
<body>
<div class="login-box">
    <h1>社員ログイン</h1>

    <%
    String error = (String) request.getAttribute("error");
    if (error != null) {
    %>
    <p class="error-msg"><%= error %></p>
    <%
    }
    %>

    <form action="<%=request.getContextPath()%>/AdminLoginServlet" method="post">
        <input type="text" name="emp_id" placeholder="社員ID" required>
        <input type="password" name="pass" placeholder="パスワード" required>
        <button type="submit">ログイン</button>
    </form>
</div>
</body>
</html>
