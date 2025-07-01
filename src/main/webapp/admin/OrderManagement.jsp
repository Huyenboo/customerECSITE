<%@ page contentType="text/html; charset=UTF-8"%>
<%@ page
	import="java.util.List, com.adminbean.AdminUserBean, com.dto.OrderListDTO"%>

<%
AdminUserBean user = (AdminUserBean) session.getAttribute("loginUser");
if (user == null) {
	response.sendRedirect(request.getContextPath() + "/admin/salesTop.jsp");
	return;
}
%>

<html>
<head>
<meta charset="UTF-8">
<title>注文管理</title>
<style>
body {
	font-family: "Yu Gothic UI", sans-serif;
	background-color: #f9f9f9;
}

h1 {
	text-align: center;
	margin-top: 20px;
}

.container {
	max-width: 950px;
	margin: 20px auto;
}

table {
	width: 100%;
	border-collapse: collapse;
	margin-top: 15px;
}

th, td {
	border: 1px solid #333;
	padding: 5px;
	text-align: center;
	font-size: 14px;
}

th {
	background: #eee;
}

.search-section {
	margin: 15px 0;
	text-align: center;
}

button, .btn-link {
	padding: 5px 10px;
	margin: 0 5px;
}

a.btn-link {
	text-decoration: none;
	background: #ddd;
	border: 1px solid #aaa;
}

.pagination {
	margin-top: 15px;
	text-align: center;
}

.pagination a {
	padding: 5px 10px;
	margin: 0 5px;
	border: 1px solid #aaa;
	background: #eee;
	text-decoration: none;
}
</style>
</head>
<body>

	<div class="container">
		<h1>注文管理</h1>

		<div class="search-section">
			<form method="get" action="OrderManagementServlet"
				style="display: inline;">
				顧客名： <input type="text" name="userName"
					value="<%=request.getAttribute("keyword") != null ? request.getAttribute("keyword") : ""%>" />
				<input type="submit" value="検索" />
			</form>
			<form method="get" action="printExcel" style="display: inline;">
				<input type="submit" value="出力する" />
			</form>
		</div>

		<table>
			<tr>
				<th>発注日</th>
				<th>注文ID</th>
				<th>顧客名</th>
				<th>商品ID</th>
				<th>商品名</th>
				<th>数量</th>
				<th>単価</th>
				<th>小計</th>
				<th>お届け予定日</th>
				<th>備考</th>
				<th>編集</th>
			</tr>

			<%
			List<OrderListDTO> orders = (List<OrderListDTO>) request.getAttribute("orderList");

			if (orders != null && !orders.isEmpty()) {
				for (OrderListDTO dto : orders) {
			%>
			<tr>
				<td><%=dto.getOrderDay()%></td>
				<td><%=dto.getOrderId()%></td>
				<td><%=dto.getCompanyName()%></td>
				<td><%=dto.getProId()%></td>
				<td><%=dto.getProName()%></td>
				<td><%=dto.getAmount()%></td>
				<td><%=dto.getProPrice()%></td>
				<td><%=Math.round(dto.getSubTotal())%></td>
				<td><%=dto.getArrivedDay()%></td>
				<td><%=dto.getMemo() != null ? dto.getMemo() : ""%></td>
				<td><a
					href="OrderDetailUpdate?id=<%=dto.getOrderId()%>&memo=確定">確定</a>
					<a href="OrderEditServlet?id=<%=dto.getOrderId()%>">編集</a> <a
					href="DeleteOrderServlet?id=<%=dto.getOrderId()%>"
					onclick="return confirm('本当に削除しますか？');">削除</a></td>
			</tr>
			<%
			}
			} else {
			%>
			<tr>
				<td colspan="11">該当する注文履歴がありません。</td>
			</tr>
			<%
			}
			%>
		</table>

		<div class="pagination">
			<%
			int currentPage = request.getAttribute("currentPage") != null ? (Integer) request.getAttribute("currentPage") : 1;
			int totalPages = request.getAttribute("totalPages") != null ? (Integer) request.getAttribute("totalPages") : 1;
			String keyword = request.getAttribute("keyword") != null ? (String) request.getAttribute("keyword") : "";
			String baseUrl = "OrderManagementServlet";
			%>

			<%
			if (currentPage > 1) {
			%>
			<a
				href="<%=baseUrl%>?page=<%=currentPage - 1%>&userName=<%=keyword%>">前へ</a>
			<%
			}
			%>

			<span><%=currentPage%> / <%=totalPages%></span>

			<%
			if (currentPage < totalPages) {
			%>
			<a
				href="<%=baseUrl%>?page=<%=currentPage + 1%>&userName=<%=keyword%>">次へ</a>
			<%
			}
			%>
		</div>

		<br> <a href="<%=request.getContextPath()%>/admin/salesTop.jsp"
			class="btn-link">TOPへ戻る</a>
	</div>

</body>
</html>
