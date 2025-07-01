<%@ page contentType="text/html; charset=UTF-8" language="java"%>
<%@ page import="java.util.*, com.bean.CartItem, com.bean.Product"%>
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
	margin-left: 15px;
}

.history-box {
	background-color: white;
	border-radius: 20px;
	padding: 30px;
	box-shadow: 0 10px 20px rgba(0, 0, 0, 0.1);
	width: 95%;
	max-width: 1100px;
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

		<div style="text-align: left; margin: 10px 0;">
			<a href="<%=request.getContextPath()%>/user/customer.jsp"
				class="top-button">TOP へ</a>
		</div>
		<h1>注文履歴</h1>


		<div>
			<a href="${pageContext.request.contextPath}/productList">注文はこちら</a>
		</div>
	</div>

	<div class="history-box">
		<table>
			<thead>
				<tr>
					<th>注文ID</th>
					<th>発注日</th>
					<th>商品ID</th>
					<th>商品名</th>
					<th>数量</th>
					<th>単価 (円)</th>
					<th>小計 (円)</th>
					<th>お届け予定日</th>
				</tr>
			</thead>
			<tbody>

				<%
				if (list != null && !list.isEmpty()) {
					for (CartItem item : list) {
						Product p = item.getProduct();
						if (p != null) {
				%>
				<tr>
					<td><%=item.getOrderId()%></td>
					<td><%=item.getOrderDay()%></td>
					<td><%=p.getProId()%></td>
					<td><%=p.getProName()%></td>
					<td><%=item.getQuantity()%></td>
					<td><%=p.getProPrice()%></td>
					<td><%=Math.round(p.getProPrice() * item.getQuantity())%></td>
					<td><%=item.getDeliveryDate()%></td>


				</tr>
				<%
				}
				}
				} else {
				%>
				<tr>
					<td colspan="10">注文履歴がありません。</td>
				</tr>
				<%
				}
				%>
			</tbody>
		</table>
	</div>

</body>
</html>