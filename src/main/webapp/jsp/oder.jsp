<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<!DOCTYPE html>
<html lang="ja">
<head>
    <meta charset="UTF-8">
    <title>商品マスター</title>
    <style>
        body {
            font-family: "Yu Gothic UI", sans-serif;
            background-color: #e6f0fa;
            padding: 0;
            margin: 0;
            display: flex;
            justify-content: center;
        }

        .container {
            margin-top: 30px;
            background: white;
            border-radius: 10px;
            padding: 20px;
            width: 90%;
            max-width: 1200px;
            box-shadow: 0 8px 20px rgba(0,0,0,0.1);
        }

        h2 {
            text-align: center;
            color: #2c7be5;
            font-size: 28px;
            margin-bottom: 20px;
        }

        table {
            width: 100%;
            border-collapse: collapse;
        }

        th {
            background-color: #2c7be5;
            color: white;
            padding: 12px;
            font-size: 16px;
            text-align: center;
        }

        td {
            padding: 10px;
            text-align: center;
        }

        input[type="text"], input[type="date"] {
            width: 90%;
            padding: 8px;
            border: 1px solid #ccc;
            border-radius: 8px;
            font-size: 14px;
        }

        .footer {
            display: flex;
            justify-content: center;
            margin-top: 20px;
            gap: 30px;
        }

        .footer button {
            background-color: #e6f0fa;
            border: none;
            border-radius: 25px;
            padding: 12px 25px;
            font-size: 16px;
            color: #2c7be5;
            cursor: pointer;
        }

        .footer button:hover {
            background-color: #d0e6fd;
        }
    </style>
</head>
<body>
<div class="container">
    <h2>商品マスター</h2>
    <form action="" method="post">
        <table>
            <thead>
            <tr>
                <th>商品コード</th>
                <th>色</th>
                <th>数量</th>
                <th>納期</th>
                <th>備考</th>
            </tr>
            </thead>
            <tbody>
            <% for (int i = 0; i < 5; i++) { %>
            <tr>
                <td><input type="text" name="code<%=i%>"></td>
                <td><input type="text" name="color<%=i%>"></td>
                <td><input type="text" name="quantity<%=i%>"></td>
                <td><input type="date" name="delivery<%=i%>"></td>
                <td><input type="text" name="note<%=i%>"></td>
            </tr>
            <% } %>
            </tbody>
        </table>

        <div class="footer">
            <button type="button">いつもありがとうございます！</button>
            <button type="submit">🛒 ご注文内容をご確認ください</button>
        </div>
    </form>
</div>
</body>
</html>
