<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ page import="java.util.List" %>
<%@ page import="com.bean.CartItem" %>
<%@ page import="com.bean.Product" %>
<%
    List<CartItem> cartList = (List<CartItem>) session.getAttribute("cart");
    int total = 0;
%>

<!DOCTYPE html>
<html lang="ja">
<head>
    <meta charset="UTF-8">
    <title>注文確認</title>
    <style>
        body {
            font-family: "Yu Gothic UI", sans-serif;
            background-color: #e6f0fa;
            margin: 0;
            padding: 30px;
        }
        .container {
            background-color: white;
            border-radius: 25px;
            box-shadow: 0 8px 20px rgba(0, 0, 0, 0.1);
            padding: 30px;
            width: 95%;
            max-width: 1100px;
            margin: 0 auto;
        }
        h2 {
            color: #2c7be5;
            text-align: center;
            margin-bottom: 20px;
        }
        table {
            width: 100%;
            border-collapse: collapse;
            margin-top: 10px;
        }
        th, td {
            border: 1px solid #aaa;
            text-align: center;
            padding: 8px;
        }
        th {
            background-color: #d7e7fb;
        }
        .total {
            font-weight: bold;
            text-align: right;
            margin-top: 20px;
            font-size: 18px;
        }
        .button-group {
            display: flex;
            justify-content: center;
            gap: 40px;
            margin-top: 30px;
        }
        .btn {
            padding: 12px 30px;
            font-size: 16px;
            border: none;
            border-radius: 10px;
            background-color: #2c7be5;
            color: white;
            cursor: pointer;
        }
        .btn:hover {
            background-color: #1a5fc9;
        }
        .note {
            font-size: 14px;
            margin-bottom: 10px;
        }
    </style>
</head>
<body>
<div class="container">
    <h2>注文確認</h2>

    <p class="note">☑ 天候・農場の状況により承りました注文量の調整をお願いする可能性がございます。ご了承ください。</p>

    <table>
        <thead>
        <tr>
            <th>商品名</th>
            <th>お届け予定日</th>
            <th>単価</th>
            <th>量</th>
            <th>備考</th>
            <th>商品ID</th>
            <th>小計</th>
        </tr>
        </thead>

    </table>

    <p style="margin-top: 30px;">納品先（住所）</p>
    <hr style="border-top: 1px solid #999; margin-bottom: 20px;">

    <div class="total">合計: ¥ total </div>

    <div class="button-group">
        <form action="<">
            <button type="submit" class="btn">カートへ戻る</button>
        </form>
        <form action="" method="post">
            <button type="submit" class="btn">注文を確定する</button>
        </form>
    </div>
</div>
</body>
</html>
