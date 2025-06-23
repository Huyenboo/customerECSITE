<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ page import="java.util.*, com.bean.CartItem" %>
<%
    List<CartItem> cartList = (List<CartItem>) session.getAttribute("cart");
    int total = 0;
%>
<!DOCTYPE html>
<html lang="ja">
<head>
    <meta charset="UTF-8">
    <title>注文確認</title>
</head>
<body>

<h2>注文確認</h2>

<table border="1">
    <tr>
        <th>商品名</th>
        <th>お届け予定日</th>
        <th>単価</th>
        <th>量</th>
        <th>小計</th>
    </tr>
<% if (cartList != null && !cartList.isEmpty()) {
       for (CartItem item : cartList) {
           int subtotal = item.getSubtotal();
           total += subtotal;
%>
    <tr>
        <td><%= item.getProduct().getProName() %></td>
        <td><%= item.getOrderArrivedDay() %></td>
        <td>¥<%= item.getProduct().getProUnitNum() %></td>
        <td><%= item.getQuantity() %></td>
        <td>¥<%= subtotal %></td>
    </tr>
<% } } %>
</table>

<h3>合計金額: ¥<%= total %></h3>

<form action="<%= request.getContextPath() %>/OrderConfirmServlet" method="post">
    <input type="submit" value="注文を確定する">
</form>

</body>
</html>
