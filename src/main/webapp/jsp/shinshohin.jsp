<%@ page contentType="text/html;charset=UTF-8" language="java"%>

<!DOCTYPE html>
<html lang="ja">
<head>
<meta charset="UTF-8">
<title>新商品情報</title>
<style>
body {
	font-family: "Yu Gothic UI", sans-serif;
	background-color: #e6f0fa;
	display: flex;
	justify-content: center;
	align-items: center;
	height: 100vh;
	margin: 0;
}

.panel {
	background-color: #d9e9f9;
	border: 2px solid #ccc;
	border-radius: 15px;
	padding: 30px;
	width: 500px;
	box-shadow: 0 4px 10px rgba(0,0,0,0.1);
	text-align: center;
}

h2 {
	color: #2c7be5;
	margin-bottom: 20px;
}

table {
	width: 100%;
	border-collapse: collapse;
	background-color: white;
	margin: 0 auto 20px auto;
}

th, td {
	border: 1px solid #999;
	padding: 10px;
	text-align: center;
	font-size: 14px;
}

th {
	background-color: #cde5fa; /* Màu xanh nhạt cho hàng tiêu đề */
}

a.cart-link {
	color: #6633cc;
	text-decoration: none;
}

a.cart-link:hover {
	text-decoration: underline;
}

a.view-cart {
	color: #2c7be5;
	text-decoration: none;
	display: inline-block;
	margin-top: 10px;
}

a.view-cart:hover {
	text-decoration: underline;
}
</style>
</head>
<body>

<div class="panel">
	<h2>新商品情報</h2>

	<table>
		<tr>
			<th>商品ID</th>
			<th>商品名</th>
			<th>単価</th>
			<th>備考</th>
			<th>カート</th>
		</tr>
		<tr>
			<td>101</td>
			<td>バラ白</td>
			<td>30</td>
			<td>人気商品</td>
			<td><a href="#" class="cart-link">カートに入れる</a></td>
		</tr>
		<tr>
			<td>102</td>
			<td>アジサイ</td>
			<td>20</td>
			<td>季節限定</td>
			<td><a href="#" class="cart-link">カートに入れる</a></td>
		</tr>
		<tr>
			<td>103</td>
			<td>チューリップ</td>
			<td>12</td>
			<td></td>
			<td><a href="#" class="cart-link">カートに入れる</a></td>
		</tr>
	</table>

	<a href="#" class="view-cart">カートを見る</a>
</div>

</body>
</html>
