<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ page import="java.util.*, com.bean.Product"%>

<%
List<Product> list = (List<Product>) request.getAttribute("productList");
%>
<!DOCTYPE html>
<html lang="ja">
<head>
<meta charset="UTF-8">
<title>新商品情報</title>
<style>
body {
	font-family: "Yu Gothic UI", sans-serif;
	background-color: #e6f0fa;
	margin: 20px;
}

.container {
	max-width: 800px;
	margin: 0 auto;
	background-color: #fff;
	padding: 25px;
	border-radius: 10px;
	box-shadow: 0 4px 10px rgba(0, 0, 0, 0.1);
}

h2 {
	text-align: center;
	color: #2c7be5;
	margin-bottom: 20px;
}

table {
	width: 100%;
	border-collapse: collapse;
	background-color: #f9f9f9; 
}

th, td {
	border: 1px solid #999;
	padding: 10px;
	text-align: center;
	font-size: 14px;
	background-color: #f9f9f9; 
}

th {
	background-color: #cde5fa;
}

.link-btn {
	background: none;
	border: none;
	color: #2c7be5;
	text-decoration: underline;
	cursor: pointer;
	font-size: 14px;
	padding: 0;
}

.link-btn:hover {
	color: #1a5fb4;
	text-decoration: underline;
}

.top-link {
	color: #2c7be5;
	text-decoration: underline;
	font-size: 14px;
}
.top-link:hover {
	color: #1a5fb4;
	text-decoration: underline;
}

.nav-container {
	display: flex;
	justify-content: space-between;
	margin-bottom: 10px;
}
</style>
</head>
<body>

<div class="container">

	<div class="nav-container">
		<a href="<%=request.getContextPath()%>/user/customer.jsp" class="top-link">TOPへ</a>

		<form action="<%=request.getContextPath()%>/showCart" method="get">
			<button type="submit" class="link-btn">カートを見る</button>
		</form>
	</div>

	<h2>新商品情報</h2>

	<table>
		<tr>
			<th>商品ID</th>
			<th>商品名</th>
			<th>単価</th>
			<th>備考</th>
			<th>カート</th>
		</tr>

		<%
		if (list != null && !list.isEmpty()) {
			for (Product p : list) {
		%>
		<tr>
			<td><%= p.getProId() %></td>
			<td><%= p.getProName() %></td>
			<td>¥<%= p.getProPrice() %></td>
			<td><%= p.getProMemo() != null ? p.getProMemo() : "" %></td>
			<td>
				<form method="post" action="<%= request.getContextPath() %>/addToCart">
					<input type="hidden" name="productId" value="<%= p.getProId() %>">
					<input type="number" name="quantity" value="1" min="1" style="width: 50px;">
					<button type="submit" class="link-btn">カートに入れる</button>
				</form>
			</td>
		</tr>
		<%
			}
		} else {
		%>
		<tr>
			<td colspan="5">商品が見つかりませんでした。</td>
		</tr>
		<%
		}
		%>
	</table>
</div>

</body>
</html>
