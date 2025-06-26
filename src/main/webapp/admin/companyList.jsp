<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ page import="java.util.*,com.bean.company" %>

<!DOCTYPE html>
<html>
<head>
    <meta charset="UTF-8">
    <title>管理部管理</title>
    <style>
        body {
            font-family: "Segoe UI", sans-serif;
            background-color: #f4f8fb;
            padding: 20px;
        }

        h2 {
            text-align: center;
            color: #333;
        }

        table {
            border-collapse: collapse;
            width: 95%;
            margin: 20px auto;
            background-color: #fff;
            box-shadow: 0 0 10px rgba(0,0,0,0.1);
        }

        th, td {
            padding: 10px 15px;
            border: 1px solid #ccc;
            text-align: center;
        }

        th {
            background-color: #e0eaff;
            color: #333;
        }

        tr:nth-child(even) {
            background-color: #f9f9f9;
        }

        .no-data {
            text-align: center;
            color: red;
        }

        .summary {
            text-align: center;
            font-weight: bold;
            margin-bottom: 10px;
            
             }
        .container {
            max-width: 1000px;
            margin: 40px auto;
            background-color: white;
            border-radius: 20px;
            box-shadow: 0 0 10px rgba(0,0,0,0.1);
            padding: 30px;
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
        .row {
            display: flex;
            justify-content: space-between;
            gap: 30px;
            margin-bottom: 30px;
        }
        .box {
            flex: 1;
            padding: 20px;
            border: 2px solid #ccc;
            border-radius: 10px;
            background-color: #f9f9f9;
        }
        .box h3 {
            margin-top: 0;
            color: #2c7be5;
        }
        .button-row {
            display: flex;
            justify-content: space-between;
            gap: 30px;
            margin-bottom: 30px;
        }
 
    </style>
</head>
<body>

<div class="container">
    <!-- Hàng 1: Tiêu đề -->
    <div class="header">管理部</div>

    <!-- Hàng 2: 新商品 và お知らせ -->
    <div class="row">
        <div class="box">
            <h3>顧客管理</h3>
        </div>
        <div class="box">
            <form action="LogoutServlet" method="post">
    			<h3><button type="submit">ログアウト</button></h3>
            </form>

        </div>
    </div>
    <h2>顧客リスト</h2>

    <%
    List<bean.company> companyList = (List<bean.company>) session.getAttribute("companyList");
    %>

    <div class="summary">
        <%=(companyList != null) ? companyList.size() : 0%> 社
    </div>

    <table>
        <tr>
            <th>会社名</th>
            <th>承認</th>

        </tr>
        <%
        if (companyList != null && !companyList.isEmpty()) {
                        for (bean.company c : companyList) {
        %>
        <tr>
            <td><%= c.getCompanyName() %></td>
            <td><input type="submit"  name= "承認" value ="承認"></td>

        </tr>
        <%
                }
            } else {
        %>
        <tr>
            <td colspan="5" class="no-data">データがありません。</td>
        </tr>
        <%
            }
        %>
    </table>
</body>
</html>
