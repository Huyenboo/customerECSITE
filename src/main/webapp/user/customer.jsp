<%@ page contentType="text/html;charset=UTF-8" language="java"%>
<%@ page contentType="text/html;charset=UTF-8" language="java"%>
<%
com.bean.User loginUser = (com.bean.User) session.getAttribute("loginUser");
if (loginUser == null) {
	response.sendRedirect(request.getContextPath() + "/login.jsp");
	return;
}
%>
<!DOCTYPE html>
<html lang="ja">
<head>
<meta charset="UTF-8">
<title>お客様ページ</title>
<style>
body {
	font-family: "Yu Gothic UI", sans-serif;
	background-color: #e6f0fa;
	margin: 0;
	padding: 0;
}

.top-bar {
	display: flex;
	justify-content: flex-end;
	padding: 15px 30px;
}

.top-bar a {
	color: #2c7be5;
	font-size: 14px;
	text-decoration: none;
	border: none;
	background: none;
	cursor: pointer;
}

.main-container {
	display: flex;
	justify-content: center;
	align-items: flex-start;
	gap: 60px;
	padding: 20px 40px;
}

.left-panel {
	flex: 1;
	max-width: 300px;
}

.left-panel h1 {
	font-size: 22px;
	color: #333;
	margin-bottom: 5px;
}

.left-panel p {
	font-size: 16px;
	margin-bottom: 30px;
}

.left-panel form {
	margin-bottom: 15px;
}

.left-panel button {
	width: 100%;
	padding: 15px;
	font-size: 16px;
	background-color: white;
	color: #2c7be5;
	border: 2px solid #2c7be5;
	border-radius: 10px;
	cursor: pointer;
}

.left-panel button:hover {
	background-color: #e6f0ff;
}

.right-panel {
	flex: 1;
	max-width: 400px;
	background-color: white;
	border-radius: 15px;
	border: 2px solid #ccc;
	padding: 25px;
}

.right-panel h2 {
	text-align: center;
	color: #2c7be5;
	margin-bottom: 30px;
	font-size: 20px;
}

.product-box {
	border: 1px solid #999;
	height: 80px;
	margin-bottom: 20px;
	border-radius: 10px;
	background-color: #f9f9f9;
}

.product-link {
	text-align: center;
	margin-top: 20px;
}

.product-link a {
	color: #2c7be5;
	text-decoration: none;
}

.product-link a:hover {
	text-decoration: underline;
}
</style>
</head>
<body>

	<div class="top-bar">
		<a href="login.jsp">ログアウト</a>
	</div>

	<div class="main-container">
		<div class="left-panel">
			<h1>お客様ページ</h1>
			<p>
				ようこそ
				<%=loginUser.getManagerName()%>
				様
			</p>

			<form action="<%=request.getContextPath()%>/productList">
				<button type="submit">購入をする</button>
			</form>

			<form action="<%=request.getContextPath()%>/OrderHistory"
				method="post">
				<input type="hidden" name="userId" value="<%=loginUser.getId()%>">
				<button type="submit">注文履歴</button>
			</form>

			<form action="passChange.jsp">
				<button type="submit">パスワード変更</button>
			</form>
			<form action="<%=request.getContextPath()%>/newProductServlet" >
				<button type="submit">新商品情報</button>
			</form>
		</div>
</body>
</html>