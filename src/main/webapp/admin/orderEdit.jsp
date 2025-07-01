<%@ page contentType="text/html; charset=UTF-8"%>
<%@ page import="com.bean.CartItem, com.bean.Product"%>
<%
CartItem order = (CartItem) request.getAttribute("orderEdit");
if (order == null) {
	response.sendRedirect(request.getContextPath() + "/OrderListServlet");
	return;
}
%>
<!DOCTYPE html>
<html lang="ja">
<head>
<meta charset="UTF-8">
<title>注文編集</title>
<style>
body {
	font-family: "Yu Gothic UI", sans-serif;
	padding: 30px;
}

.container {
	max-width: 600px;
	margin: 0 auto;
}

h1 {
	text-align: center;
	margin-bottom: 20px;
}

.field {
	margin-bottom: 10px;
}

label {
	display: inline-block;
	width: 120px;
	font-weight: bold;
}

input[type="text"], input[type="date"] {
	width: 400px;
	padding: 5px;
}

.buttons {
    margin-top: 20px;
    text-align: right; 
}

.buttons input {
    padding: 5px 20px;
    margin-right: 10px;
}
</style>
</head>
<body>
	<div class="container">
		<h1>注文編集</h1>

		<form action="<%=request.getContextPath()%>/OrderEditServlet"
			method="post">

			<div class="field">
				<label>注文ID:</label>
				<%=order.getOrderId()%>
				<input type="hidden" name="orderId"
					value="<%=order.getOrderId()%>">
			</div>

			<div class="field">
				<label>顧客ID:</label> <input type="text" name="userId"
					value="<%=order.getUserId()%>" readonly>
			</div>

			<div class="field">
				<label>顧客名:</label> <input type="text" name="userName"
					value="<%=order.getUserName()%>" readonly>
			</div>

			<div class="field">
				<label>注文コード:</label> <input type="text" name="orderCode"
					value="<%=order.getOrderCode()%>" readonly>
			</div>

			<div class="field">
				<label>商品名:</label> <input type="text" name="productName"
					value="<%=order.getProduct() != null ? order.getProduct().getProName() : ""%>"
					readonly>
			</div>

			<div class="field">
				<label>数量:</label> <input type="text" name="quantity"
					value="<%=order.getQuantity()%>" required>
			</div>

			<div class="field">
				<label>金額:</label> <input type="text" name="orderAmount"
					value="<%=order.getOrderAmount()%>" readonly>
			</div>

			<div class="field">
				<label>注文日:</label> <input type="date" name="orderDay"
					value="<%=order.getOrderDay() != null ? order.getOrderDay().toString() : ""%>"
					readonly>
			</div>

			<div class="field">
				<label>お届け日:</label> <input type="date" name="orderArrivedDay"
					value="<%=order.getOrderArrivedDay() != null ? order.getOrderArrivedDay().toString() : ""%>"
					required>
			</div>

			<div class="field">
				<label>備考:</label> <input type="text" name="orderMemo"
					value="<%=order.getOrderMemo()%>">
			</div>

			<div class="buttons">
				<input type="submit" value="登録"> <input type="button"
					value="キャンセル"
					onclick="location.href='<%=request.getContextPath()%>/OrderManagementServlet';">
			</div>
		</form>
	</div>
</body>
</html>
