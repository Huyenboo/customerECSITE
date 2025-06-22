<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ page import="java.util.List" %>
<%@ page import="com.bean.CartItem" %>
<%
    List<CartItem> cartList = (List<CartItem>) session.getAttribute("cart");
    String address = (String) session.getAttribute("address");
    String errMsg = (String) request.getAttribute("errMsg");
    int total = 0;

    if (cartList == null || cartList.isEmpty()) {
        response.sendRedirect("cart.jsp");
        return;
    }
%>
<!DOCTYPE html>
<html lang="ja">
<head>
    <meta charset="UTF-8">
    <title>注文確認</title>
    <style>
        body {
            font-family: "Yu Gothic UI", sans-serif;
            background-color: #f5faff;
            padding: 30px;
        }
        .container {
            background-color: #fff;
            padding: 30px;
            border-radius: 20px;
            box-shadow: 0 6px 20px rgba(0, 0, 0, 0.1);
            max-width: 1000px;
            margin: 0 auto;
        }
        h2 {
            color: #2c7be5;
            text-align: center;
        }
        .note {
            text-align: center;
            font-size: 14px;
            color: #555;
            margin-bottom: 20px;
        }
        .error {
            color: red;
            text-align: center;
            margin-bottom: 10px;
        }
        table {
            width: 100%;
            border-collapse: collapse;
            margin-top: 20px;
        }
        th, td {
            border: 1px solid #ccc;
            padding: 10px;
            text-align: center;
        }
        th {
            background-color: #d7e7fb;
        }
        .address {
            margin-top: 20px;
        }
        .total {
            font-weight: bold;
            font-size: 18px;
            text-align: right;
            margin-top: 10px;
        }
        .checkbox-area {
            text-align: center;
            margin-top: 20px;
        }
        .buttons {
            text-align: center;
            margin-top: 30px;
        }
        .btn {
            padding: 12px 25px;
            background-color: #2c7be5;
            color: white;
            border: none;
            border-radius: 10px;
            font-size: 16px;
            margin: 0 15px;
            cursor: pointer;
        }
        .btn:hover {
            background-color: #1a5fc9;
        }
    </style>
</head>
<body>
<div class="container">
    <h2>注文確認</h2>

    <p class="note">☑ 天候・農場の状況により承りました注文量の調整をお願いする可能性がございます。ご了承ください。</p>

    <% if (errMsg != null) { %>
        <div class="error"><%= errMsg %></div>
    <% } %>

    <table>
        <thead>
        <tr>
            <th>商品名</th>
            <th>分類</th>
            <th>単価</th>
            <th>量</th>
            <th>備考</th>
            <th>小計</th>
            <th>納期</th>
        </tr>
        </thead>
        <tbody>
        <% for (CartItem item : cartList) {
            int subtotal = item.getSubtotal();
            total += subtotal;
        %>
        <tr>
            <td><%= item.getProduct().getProName() %></td>
            <td><%= item.getQuantity() %></td>
            <td><%= item.getProduct().getProUnitNum() %> 円</td>
            <td><%= subtotal %> 円</td>
            <td><%= item.getDeliveryDate() %></td>
        </tr>
        <input type="hidden" name="orderId[]" value="<%= item.getOrderId()%>">

		<input type="hidden" name="proBox[]" value="<%= item.getProBox() %>">
		<input type="hidden" name="deliveryDate" value="<%= item.getDeliveryDate() %>">
        <% } %>
        
        
        </tbody>
    </table>

    <div class="address">
        <p>納品先（住所）</p>
        <p><%= address != null ? address : "未登録" %></p>
    </div>

    <div class="total">合計金額: ¥<%= total %></div>

    <form action="<%= request.getContextPath() %>OrderConfirmServlet" method="post">
        <div class="checkbox-area">
            <label><input type="checkbox" name="confirmCheck"> 上記内容を確認しました</label>
        </div>
        <div class="buttons">
            <button type="submit" formaction="" class="btn">カートへ戻る</button>
            <button type="submit" class="btn">注文を確定する</button>
        </div>
    </form>
</div>
</body>
</html>
