<%@ page contentType="text/html; charset=UTF-8" language="java" %>
<!DOCTYPE html>
<html lang="ja">
<head>
    <meta charset="UTF-8">
    <title>注文完了</title>
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
            box-shadow: 0 15px 30px rgba(0, 0, 0, 0.1);
            text-align: center;
            width: 90%;
            max-width: 500px;
        }

        h1 {
            color: #2c7be5;
            font-size: 24px;
            margin-bottom: 20px;
        }

        h2 {
            font-size: 20px;
            margin-bottom: 20px;
            color: #333;
        }

        p {
            font-size: 14px;
            color: #333;
            line-height: 1.6;
            margin-bottom: 30px;
        }

        .btn-group {
            display: flex;
            justify-content: center;
            gap: 30px;
        }

        a.button {
            display: inline-block;
            padding: 10px 25px;
            background-color: #2c7be5;
            color: white;
            text-decoration: none;
            border-radius: 30px;
            font-size: 15px;
        }

        a.button:hover {
            background-color: #1a5fc9;
        }
    </style>
</head>
<body>
<div class="success-box">
    <h1>注文完了</h1>
    <h2>注文が完了しました！</h2>
    <p>
        ※天候・農場の状況により注文量の調整をお願いする可能性がございます。<br>
        ※お支払いは納品後になります。注文確認は注文履歴をご覧ください。
    </p>
    <div class="btn-group">
        <a href="<%= request.getContextPath() %>/OrderHistory" class="button">注文履歴を見る</a>
        <a href="customer.jsp" class="button">TOPへ</a>
    </div>
</div>
</body>
</html>
