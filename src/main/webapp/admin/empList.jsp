<%@ page contentType="text/html; charset=UTF-8" %>
<%@ page import="java.util.List, com.adminbean.AdminUserBean" %>
<%
    AdminUserBean user = (AdminUserBean) session.getAttribute("loginUser");
    if (user == null) {
        response.sendRedirect(request.getContextPath() + "/salesTop.jsp");
        return;
    }

    List<AdminUserBean> empList = (List<AdminUserBean>) request.getAttribute("empList");
%>
<html>
<head>
    <title>社員一覧</title>
    <style>
        .container { max-width:800px; margin:20px auto; }
        table { width:100%; border-collapse: collapse; }
        th, td { border:1px solid #333; padding:5px; text-align:center; }
        .button { margin:5px; }
    </style>
</head>
<body>
<div class="container">
    <h1>社員一覧</h1>
    <p><%= user.getEmp_name() %> さん（<%= user.getRole_name() %>）</p>

    <table>
        <tr>
            <th>社員番号</th>
            <th>社員名</th>
            <th>生年月日</th>
            <th>住所</th>
            <th>入社日</th>
            <th>役職</th>
            <th>等級</th>
            <th>パスワード</th>
        </tr>

        <% 
            if (empList != null && !empList.isEmpty()) {
                for (AdminUserBean emp : empList) {
        %>
            <tr>
                <td><%= emp.getEmp_id() %></td>
                <td><%= emp.getEmp_name() %></td>
               <td><%= emp.getEmp_birth_date() != null ? emp.getEmp_birth_date() : "" %></td>
                <td><%= emp.getEmp_address() %></td>
               <td><%= emp.getEmp_entry_date() != null ? emp.getEmp_entry_date() : "" %></td>
                <td><%= emp.getEmp_position() %></td>
                <td><%= emp.getEmp_grade() %></td>
                <td><%= emp.getPass() %></td>
            </tr>
        <% 
                }
            } else { 
        %>
            <tr>
                <td colspan="8">データがありません。</td>
            </tr>
        <% 
            } 
        %>
    </table>
</div>
</body>
</html>
