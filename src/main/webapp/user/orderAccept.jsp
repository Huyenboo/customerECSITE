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
        /* CSS giữ nguyên */
    </style>
</head>
<body>
<div class="container">
    <h2>注文確認</h2>

    <p class="note">☑ 天候・農場の状況により承りました注文量の調整をお願いする可能性がございます。ご了承ください。</p>

    <% if (errMsg != null) { %>
        <div class="error"><%= errMsg %></div>
    <% } %>

    <form action="<%= request.getContextPath() %>/OrderConfirmServlet" method="post">

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
                <td><%= item.getProduct().getProBox() %></td> 
                <td><%= item.getProduct().getProUnit() %> 円</td> 
                <td><%= item.getQuantity() %></td>
                <td><%= item.getOrderMemo() != null ? item.getOrderMemo() : "" %></td>
                <td><%= subtotal %> 円</td>
                <td><%= item.getDeliveryDate() %></td>
            </tr>

            <input type="hidden" name="orderId[]" value="<%= item.getOrderId() %>">
            <input type="hidden" name="proBox[]" value="<%= item.getProduct().getProBox() %>">
            <input type="hidden" name="deliveryDate[]" value="<%= item.getDeliveryDate() %>">
            <% } %>
            </tbody>
        </table>

        <div class="address">
            <p>納品先（住所）</p>
            <p><%= address != null ? address : "" %></p>
        </div>

        <div class="total">合計金額: ¥<%= total %></div>

        <div class="checkbox-area">
            <label><input type="checkbox" name="confirmCheck"> 上記内容を確認しました</label>
        </div>

        <div class="buttons">
            <button type="submit" formaction="cart.jsp" class="btn">カートへ戻る</button>
            <button type="submit" class="btn">注文を確定する</button>
        </div>

    </form>
</div>
</body>
</html>
