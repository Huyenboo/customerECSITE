<%@ page contentType="text/html;charset=UTF-8" language="java"%>
<%@ page import="java.util.List"%>
<%@ page import="com.bean.CartItem, com.bean.Product"%>
<%
List<CartItem> cartList = (List<CartItem>) session.getAttribute("cart");
String address = (String) request.getAttribute("address");
String errMsg = (String) request.getAttribute("errMsg");
int total = 0;

if (cartList == null || cartList.isEmpty()) {
	response.sendRedirect(request.getContextPath() + "/user/cart.jsp");
	return;
}
%>
<!DOCTYPE html>
<html lang="ja">
<head>
<meta charset="UTF-8">
<title>注文確認</title>
<style>
body {
	font-family: "Yu Gothic UI", sans-serif;
	background-color: #f5faff;
	padding: 30px;
}

.container {
	background-color: #fff;
	padding: 30px;
	border-radius: 20px;
	box-shadow: 0 8px 20px rgba(0, 0, 0, 0.1);
	max-width: 1000px;
	margin: 0 auto;
}

h2 {
	color: #2c7be5;
	text-align: center;
	margin-bottom: 20px;
}

.note {
	text-align: center;
	font-size: 14px;
	color: #555;
	margin-bottom: 20px;
}

.error {
	color: red;
	text-align: center;
	margin-bottom: 15px;
}

table {
	width: 100%;
	border-collapse: collapse;
	margin-top: 20px;
}

th, td {
	border: 1px solid #ccc;
	padding: 10px;
	text-align: center;
}

th {
	background-color: #d7e7fb;
}

.address {
	margin-top: 20px;
}

.total {
	font-weight: bold;
	font-size: 18px;
	text-align: right;
	margin-top: 10px;
}

.checkbox-area {
	text-align: center;
	margin-top: 20px;
}

.buttons {
	text-align: center;
	margin-top: 30px;
	display: flex;
	justify-content: center;
	gap: 20px;
}

.btn {
	padding: 12px 25px;
	background-color: #2c7be5;
	color: white;
	border: none;
	border-radius: 10px;
	font-size: 16px;
	cursor: pointer;
}

.btn:hover {
	background-color: #1a5fc9;
}

a.btn-link {
	display: inline-block;
	padding: 12px 25px;
	background-color: #2c7be5;
	color: white;
	border-radius: 10px;
	text-decoration: none;
}

a.btn-link:hover {
	background-color: #888;
}
</style>
</head>
<body>
	<div class="container">
		<h2>注文確認</h2>

		<%
		if (errMsg != null) {
		%>
		<div class="error"><%=errMsg%></div>
		<%
		}
		%>

		<table>
			<thead>
				<tr>
					<th>商品ID</th>
					<th>商品名</th>
					<th>お届け予定日</th>
					<th>単価（円）</th>
					<th>数量</th>
					<th>備考</th>
					<th>小計（円）</th>
				</tr>
			</thead>
			<tbody>
				<%
				if (cartList != null && !cartList.isEmpty()) {
					for (CartItem item : cartList) {
						Product p = item.getProduct();
						double proPrice = p.getProPrice();
						int quantity = item.getQuantity();
						int subtotal = item.getSubtotal(proPrice);
						total += subtotal;
				%>
				<tr>
					<td><%=p.getProId()%></td>
					<td><%=p.getProName()%></td>
					<td><%=item.getOrderArrivedDay() != null ? item.getOrderArrivedDay().toString() : ""%></td>
					<td><%=p.getProPrice()%> </td>
					<td><%=item.getQuantity()%></td>
					<td><%=item.getOrderMemo() != null ? item.getOrderMemo() : ""%></td>
					<td><%= Math.round(subtotal) %></td>
				
				</tr>
				<%
				}}
				%>
			</tbody>
		</table>

		<div class="address">
			<p>納品先住所:</p>
			<p><%=address != null ? address : "未設定"%></p>
		</div>

		<div class="total">
			合計金額: ¥<%=total%></div>

		<div class="checkbox-area">
			<form action="<%=request.getContextPath()%>/orderComplete"
				method="post">
				<label> <input type="checkbox" name="confirmCheck" required>
					天候・農場の状況により承りました注文量の調整をお願いする可能性がございます。ご了承ください。
				</label>

				<div class="buttons">
					<button type="submit" class="btn">注文を確定する</button>
					<a href="<%=request.getContextPath()%>/user/cart.jsp" class="btn-link">カートへ戻る</a>
				</div>
			</form>
		</div>

	</div>
</body>
</html>
