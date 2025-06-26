<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ page import="java.util.*,com.bean.Product" %>

<!DOCTYPE html>
<html>
<head>
    <meta charset="UTF-8">
    <title>商品一覧</title>
    <style>
        table {
            width: 95%;
            border-collapse: collapse;
            margin: 20px auto;
        }
        th, td {
            padding: 8px;
            border: 1px solid #aaa;
            text-align: center;
        }
        th {
            background-color: #e0f0ff;
        }
    </style>
</head>
<body>
    <h2 style="text-align: center;">商品一覧</h2>

    <%
        List<Product> list = (List<Product>) session.getAttribute("productList");
    %>

    <table>
        <tr>
            <th>商品コード</th>
            <th>商品名</th>
            <th>商品名称</th>
            <th>英語名</th>
            <th>学名</th>
            <th>入数</th>
            <th>備考</th>
        </tr>

        <%
            if (list != null) {
                for (Product p : list) {
        %>
        <tr>
            <td><%= p.getProductCode() %></td>
            <td><%= p.getProductName() %></td>
            <td><%= p.getProductNameDetail() %></td>
            <td><%= p.getProductNameEn() %></td>
            <td><%= p.getScientificName() %></td>
            <td><%= p.getQuantity() %></td>
            <td><%= p.getNote() %></td>
        </tr>
        <%
                }
            }
        %>
    </table>
</body>
</html>
