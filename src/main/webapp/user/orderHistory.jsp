<%@ page contentType="text/html; charset=UTF-8" language="java" %>
<%@ page import="java.util.*, com.bean.CartItem" %>
<%
    List<CartItem> list = (List<CartItem>) request.getAttribute("listOrder");
%>
<!DOCTYPE html>
<html lang="ja">
<head>
    <meta charset="UTF-8">
    <title>注文履歴</title>
    <style>
        body {
            font-family: "Yu Gothic UI", sans-serif;
            background-color: #e6f0fa;
            margin: 0;
            padding: 40px;
        }
        .header {
            display: flex;
            justify-content: space-between;
            align-items: center;
            margin-bottom: 30px;
        }
        .header h1 {
            font-size: 24px;
            color: #2c7be5;
        }
        .header a {
            text-decoration: none;
            color: #2c7be5;
            font-size: 16px;
            font-weight: bold;
        }
        .history-box {
            background-color: white;
            border-radius: 20px;
            padding: 30px;
            box-shadow: 0 10px 20px rgba(0,0,0,0.1);
            width: 95%;
            max-width: 900px;
            margin: 0 auto;
        }
        table {
            width: 100%;
            border-collapse: collapse;
        }
        th, td {
            border: 1px solid #333;
            padding: 12px;
            text-align: center;
            font-size: 14px;
        }
        th {
            background-color: #f0f8ff;
            font-weight: bold;
        }
        .btn-reorder {
            background-color: white;
            border: 1px solid #2c7be5;
            padding: 6px 12px;
            color: #2c7be5;
            border-radius: 8px;
            cursor: pointer;
        }
        .btn-reorder:hover {
            background-color: #dbeaff;
        }
    </style>
</head>
<body>

<div class="header">
    <h1>注文履歴</h1>
    <a href="customer.jsp">TOPへ</a>
</div>

<div class="history-box">
    <table>
        <thead>
        <tr>
            <th>注文日時</th>
            <th>注文ID</th>
            <th>▽詳細を見る</th>
            <th>再購入ボタン</th>
        </tr>
        </thead>
        <tbody>

        <%
            if (list != null && !list.isEmpty()) {
                for (CartItem item : list) {
        %>
            <tr>
                <td><%= item.getOrderDay() %></td>
                <td><%= item.getOrderId() %></td>
                <td><a href="order.jsp?orderId=<%= item.getOrderId() %>">詳細</a></td>
                <td>
                    <form action="addToCart" method="post">
                        <button type="submit" class="btn-reorder">再購入</button>
                    </form>
                </td>
            </tr>
        <%
                }
            } else {
        %>
            <tr>
                <td colspan="4">注文履歴がありません。</td>
            </tr>
        <%
            }
        %>
        </tbody>
    </table>
</div>

</body>
</html>
