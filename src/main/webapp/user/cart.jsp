
<%@ page contentType="text/html;charset=UTF-8" language="java"%>
<%@ page import="java.util.*, com.bean.CartItem, com.bean.Product"%>
<%
List<CartItem> cartList = (List<CartItem>) session.getAttribute("cart");
int total = 0;
%>
<!DOCTYPE html>
<html lang="ja">
<head>
<meta charset="UTF-8">
<title>カート情報</title>
<style>
body {
	font-family: "Yu Gothic UI", sans-serif;
	background-color: #e6f0fa;
	margin: 0;
	padding: 30px;
}

.container {
	background-color: white;
	border-radius: 25px;
	box-shadow: 0 8px 20px rgba(0, 0, 0, 0.1);
	padding: 30px;
	width: 95%;
	max-width: 1100px;
	margin: 0 auto;
}

h2 {
	color: #2c7be5;
	text-align: center;
	border-bottom: 2px solid #2c7be5;
	padding-bottom: 10px;
}

table {
	width: 100%;
	border-collapse: collapse;
	margin-top: 25px;
}

th, td {
	border: 1px solid #aaa;
	text-align: center;
	padding: 10px;
}

th {
	background-color: #d7e7fb;
}

.subtotal, .total {
	font-weight: bold;
	text-align: right;
	margin-top: 20px;
	color: #2c7be5;
	font-size: 18px;
}

.button-group {
	display: flex;
	justify-content: center;
	gap: 40px;
	margin-top: 30px;
}

.btn {
	padding: 12px 30px;
	font-size: 16px;
	border: none;
	border-radius: 10px;
	background-color: #2c7be5;
	color: white;
	cursor: pointer;
}

.btn:hover {
	background-color: #1a5fc9;
}

.action-button {
	padding: 5px 10px;
	font-size: 14px;
	background-color: #eee;
	border: 1px solid #aaa;
	border-radius: 5px;
	cursor: pointer;
}

.action-button:hover {
	background-color: #ddd;
}
</style>
</head>
<body>
	<div class="container">
		<h2>カート情報</h2>

		<table>
			<thead>
				<tr>
					<th>商品名</th>
					<th>お届け予定日</th>
					<th>単価</th>
					<th>量</th>
					<th>備考</th>
					<th>商品ID</th>
					<th>削除</th>
					<th>変更</th>
					<th>小計</th>
				</tr>
			</thead>
			<tbody>
				<%
				if (cartList != null && !cartList.isEmpty()) {
					for (CartItem item : cartList) {
						Product p = item.getProduct();
						int subtotal = item.getSubtotal();
						total += subtotal;
				%>
				<tr>
					<td><%=p.getProName()%></td>
					<td><input type="date" name="orderArrivedDay"
						value="<%=item.getOrderArrivedDay()%>"></td>
					<td>¥<%=p.getProUnitNum()%></td>

					<td><%=item.getQuantity()%></td>
					<td><%=p.getProMemo() != null ? p.getProMemo() : ""%></td>
					<td><%=p.getProId()%></td>
					<td>
						<form method="post"
							action="<%=request.getContextPath()%>/deleteCartItem">
							<input type="hidden" name="productId" value="<%=p.getProId()%>">
							<button type="submit" class="action-button">削除</button>
						</form>
					</td>
					<td>
						<form method="post"
							action="<%=request.getContextPath()%>/updateCartItem">
							<input type="hidden" name="productId" value="<%=p.getProId()%>">
							<!--							<input type="hidden" name="orderArrivedDay"
								value="<%=item.getOrderArrivedDay()%>"> -->
							<input type="number" name="quantity"
								value="<%=item.getQuantity()%>" min="1" style="width: 60px;">
							<button type="submit">変更</button>

						</form>
					</td>
					<td>¥<%=subtotal%></td>
				</tr>
				<%
				}
				} else {
				%>
				<tr>
					<td colspan="9">カートは空です。</td>
				</tr>
				<%
				}
				%>
			</tbody>
		</table>

		<div class="total">
			合計: ¥<%=total%></div>

		<div class="button-group">
			<form action="<%=request.getContextPath()%>/productList">
				<button type="submit" class="btn">購入を続ける</button>
			</form>
			<form action="<%=request.getContextPath()%>/updateCartItem"
				method="post">
				<button type="submit" class="btn">注文へ進む</button>
			</form>
		</div>
	</div>
</body>
</html>
