<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<!DOCTYPE html>
<html lang="ja">
<head>
    <meta charset="UTF-8">
    <title>お客様ページ</title>
    <style>
        body {
            font-family: "Yu Gothic UI", sans-serif;
            background-color: #e6f0fa;
            margin: 0;
            padding: 0;
        }
        .container {
            max-width: 1000px;
            margin: 40px auto;
            background-color: white;
            border-radius: 20px;
            box-shadow: 0 0 10px rgba(0,0,0,0.1);
            padding: 30px;
            position: relative;
        }
        .header {
            background-color: #2c7be5;
            color: white;
            text-align: center;
            padding: 20px;
            font-size: 26px;
            font-weight: bold;
            border-radius: 10px;
            margin-bottom: 30px;
        }
        .button-row {
            display: flex;
            justify-content: space-between;
            gap: 30px;
            margin-bottom: 30px;
        }
        .button-row form {
            flex: 1;
        }
        .button-row button {
            width: 100%;
            padding: 15px;
            font-size: 16px;
            border: 2px solid #2c7be5;
            background-color: white;
            color: #2c7be5;
            border-radius: 10px;
            cursor: pointer;
        }
        .button-row button:hover {
            background-color: #e6f0ff;
        }
        .logout-container {
            display: flex;
            justify-content: flex-end;
            margin-top: 50px;
        }
        .logout-container form button {
            padding: 10px 20px;
            font-size: 14px;
            border: none;
            background-color: white;
            color: #2c7be5;
            border-radius: 5px;
            cursor: pointer;
        }
        .logout-container form button:hover {
            background-color:#2c7be4;
        }
    </style>
</head>
<body>
<div class="container">

    <div class="header">営業部管理</div>

    <div class="button-row">
        <form action="">
            <button type="submit">商品管理</button>
        </form>
        <form action="">
            <button type="submit">顧客管理</button>
        </form>
    </div>

    <div class="button-row">
        <form action="">
            <button type="submit">注文管理</button>
        </form>
        <form action="">
            <button type="submit">売上日計表</button>
        </form>
    </div>

    <div class="logout-container">
        <form action="<%=request.getContextPath()%>/adminLogin" method="post">
            <button type="submit">ログアウト</button>
        </form>
    </div>

</div>
</body>
</html>
