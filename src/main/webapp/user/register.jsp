<%@ page contentType="text/html; charset=UTF-8" language="java"%>
<!DOCTYPE html>
<html lang="ja">
<head>
<meta charset="UTF-8">
<title>新規登録</title>
<style>
body {
	font-family: "Yu Gothic UI", sans-serif;
	background-color: #e6f0fa;
	background-image:
		url("https://www.transparenttextures.com/patterns/flowers.png");
	background-repeat: repeat;
	margin: 0;
	padding: 0;
	display: flex;
	justify-content: center;
	align-items: center;
	height: 100vh;
}

.register-box {
	background-color: white;
	padding: 40px;
	border-radius: 15px;
	box-shadow: 0 8px 20px rgba(0, 0, 0, 0.1);
	width: 90%;
	max-width: 500px;
	animation: fadeIn 0.6s ease-in-out;
}

h1 {
	color: #2c7be5;
	font-size: 24px;
	text-align: center;
	margin-bottom: 30px;
}

.error-msg {
	color: red;
	text-align: center;
	margin-bottom: 15px;
	font-size: 14px;
}

form {
	display: flex;
	flex-direction: column;
}

label {
	margin-bottom: 5px;
	color: #333;
	font-size: 14px;
}

input[type="text"], input[type="email"], input[type="password"] {
	padding: 10px;
	margin-bottom: 20px;
	border: 1px solid #ccc;
	border-radius: 8px;
	font-size: 16px;
}

.btn-group {
	display: flex;
	justify-content: space-between;
	margin-top: 10px;
}

.btn {
	width: 48%;
	padding: 12px;
	background-color: #2c7be5;
	color: white;
	font-size: 16px;
	border: none;
	border-radius: 20px;
	cursor: pointer;
	transition: background-color 0.3s ease;
}

.btn:hover {
	background-color: #1d5dc0;
}

.link {
	text-align: center;
	margin-top: 15px;
	font-size: 14px;
}

.link a {
	color: #2c7be5;
	text-decoration: none;
}

.link a:hover {
	text-decoration: underline;
}

@
keyframes fadeIn {from { opacity:0;
	transform: translateY(20px);
}

to {
	opacity: 1;
	transform: translateY(0);
}
}
</style>
</head>
<body>
	<div class="register-box">
		<h1>新規登録</h1>

		<%
		String errMsg = (String) request.getAttribute("errMsg");
		%>
		<%
		if (errMsg != null) {
		%>
		<div class="error-msg"><%=errMsg%></div>
		<%
		}
		%>

		<form action="<%=request.getContextPath()%>/register" method="post">
			<label for="company_name">企業名</label> <input type="text"
				id="company_name" name="company_name" required> <label
				for="company_address">住所</label> <input type="text"
				id="company_address" name="company_address" required> <label
				for="president_phone_num">代表電話番号</label> <input type="text"
				id="president_phone_num" name="president_phone_num" required>

			<label for="manager_name">担当者氏名</label> <input type="text"
				id="manager_name" name="manager_name" required> <label
				for="manager_email">担当者メールアドレス</label> <input type="email"
				id="manager_email" name="manager_email" required> <label
				for="manager_phone_num">担当者電話番号</label> <input type="text"
				id="manager_phone_num" name="manager_phone_num" required> <label
				for="password">パスワード</label> <input type="password" id="password"
				name="password" required> <label for="confirm_password">パスワード再確認</label>
			<input type="password" id="confirm_password" name="confirm_password"
				required>

			<div class="btn-group">
				<button type="button" class="btn" onclick="history.back()">戻る</button>
				<button type="submit" class="btn">登録</button>
			</div>
		</form>

		<div class="link">
			すでにアカウントをお持ちのお客様は <a href="<%=request.getContextPath()%>/user/login.jsp">こちら</a>

		</div>
	</div>
</body>
</html>
