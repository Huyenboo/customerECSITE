<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<!DOCTYPE html>
<html lang="ja">
<head>
    <meta charset="UTF-8">
    <title>登録申請完了</title>
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
        .success-box {
            background-color: white;
            padding: 40px;
            border-radius: 20px;
            box-shadow: 0 15px 30px rgba(0,0,0,0.1);
            text-align: center;
            width: 90%;
            max-width: 400px;
        }
        h1 {
            color: #2c7be5;
            font-size: 24px;
            margin-bottom: 20px;
        }
        p {
            font-size: 16px;
            color: #333;
            margin-bottom: 30px;
        }
        a.button {
            display: inline-block;
            padding: 12px 30px;
            background-color: #2c7be5;
            color: white;
            text-decoration: none;
            border-radius: 30px;
            font-size: 16px;
        }
    </style>
</head>
<body>
<div class="success-box">
    <h1>登録申請が完了しました</h1>
    <p>承認されましたらメールでお知らせします</p>
    <a class="button" href="<%=request.getContextPath()%>/user/login.jsp">ログインページへ</a>
</div>
</body>
</html>
