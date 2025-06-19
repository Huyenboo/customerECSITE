<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ page import="java.util.*, com.Product" %>

<%
    List<Product> list = (List<Product>) request.getAttribute("productList");
%>

<!DOCTYPE html>
<html lang="ja">
<head>
    <meta charset="UTF-8">
    <title>商品一覧</title>
    <style>
        body {
            font-family: "Yu Gothic UI", sans-serif;
            background-color: #f5faff;
            margin: 0;
            padding: 20px;
        }

        .top-bar {
            display: flex;
            justify-content: space-between;
            align-items: center;
            margin-bottom: 10px;
        }

        .top-bar h2 {
            margin: 0 auto;
            text-align: center;
        }

        .cart-button {
            margin-left: auto;
            margin-right: 30px;
        }

        .search-box {
            display: flex;
            justify-content: center;
            gap: 50px;
            margin: 10px 0 20px 0;
        }

        .search-group {
            text-align: center;
        }

        table {
            width: 90%;
            border-collapse: collapse;
            margin: 0 auto 20px auto;
        }

        th, td {
            padding: 8px;
            border: 1px solid #aaa;
            text-align: center;
        }

        th {
            background-color: #e0f0ff;
        }

        .submit-btn {
            display: block;
            margin: 0 auto;
            padding: 10px 20px;
            background-color: #2c7be5;
            color: white;
            border: none;
            border-radius: 8px;
            font-size: 16px;
            cursor: pointer;
        }

        .submit-btn:hover {
            background-color: #1a5fc9;
        }

        .add-cart-btn {
            background-color: transparent;
            border: none;
            color: #2c7be5;
            cursor: pointer;
        }

        .add-cart-btn:hover {
            text-decoration: underline;
        }

        input[type="number"] {
            width: 50px;
            padding: 5px;
        }
    </style>
</head>
<body>

<div class="top-bar">
    <div></div>
    <h2>商品一覧</h2>
    <div class="cart-button">
        <form action="<%= request.getContextPath() %>/showCart" method="get">
            <button type="submit" class="add-cart-btn">カートを見る</button>
        </form>
    </div>
</div>

<div class="search-box">
    <div class="search-group">
        <form action="<%= request.getContextPath() %>/SearchByIdServlet" method="get">
            <label>ID検索</label><br>
            <input type="text" name="productId">
            <button type="submit">検索</button>
        </form>
    </div>
    <div class="search-group">
        <form action="<%= request.getContextPath() %>/SearchByKeywordServlet" method="get">
            <label>あいまい検索</label><br>
            <input type="text" name="keyword">
            <button type="submit">検索</button>
        </form>
    </div>
</div>

<table>
    <tr>
        <th>商品名</th>
        <th>単価</th>
        <th>備考</th>
        <th>カート</th>
        <th>商品ID</th>
    </tr>

    <%
        if (list != null && !list.isEmpty()) {
            for (Product p : list) {
    %>
    <tr>
        <td><%= p.getProName() %></td>
        <td>¥<%= p.getProUnitNum() %></td>
        <td><%= p.getProMemo() %></td>
        <td>
            <form method="post" action="<%= request.getContextPath() %>/addToCart">
                <input type="hidden" name="productId" value="<%= p.getProId() %>">
                <input type="number" name="quantity" value="1" min="1">
                <button type="submit" class="add-cart-btn">カートに入れる</button>
            </form>
        </td>
        <td><%= p.getProId() %></td>
    </tr>
    <%
            }
        } else {
    %>
    <tr><td colspan="5">商品が見つかりませんでした。</td></tr>
    <%
        }
    %>
</table>

<form action="checkout.jsp" method="get">
    <button type="submit" class="submit-btn">購入に進む</button>
</form>

</body>
</html>