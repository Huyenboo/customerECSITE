<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<!DOCTYPE html>
<html lang="ja">
<head>
    <meta charset="UTF-8">
    <title>ログイン</title>
    <style>
        body {
            font-family: "Yu Gothic UI", sans-serif;
            background-color: #e6f0fa;
            display: flex;
            justify-content: center;
            align-items: center;
            height: 100vh;
            margin: 0;
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
        label {
            display: block;
            text-align: left;
            margin-bottom: 5px;
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
            background-color: #2c7be5;
            color: white;
            border: none;
            border-radius: 10px;
            font-size: 16px;
            cursor: pointer;
        }
        a {
            display: block;
            margin-top: 15px;
            font-size: 13px;
            color: #2c7be5;
            text-decoration: none;
        }
    </style>
</head>
<body>
<div class="login-box">
	<h1>ログイン</h1>
	<form action="<%=request.getContextPath()%>/login" method="post">
		<label for="phone">電話番号:</label>
		<input type="text" id="phone" name="phone" required>       
		       
		<label for="password">パスワード</label>
		<input type="password" id="password" name="password" required>
		
		<button type="submit">ログイン</button>
	</form>
	<a href="<%=request.getContextPath()%>/jsp/register.jsp">新規登録はこちら</a>
</div>
</body>
</html>
