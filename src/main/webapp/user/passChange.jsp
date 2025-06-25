<%@ page contentType="text/html;charset=UTF-8" language="java"%>
<!DOCTYPE html>
<html lang="ja">
<head>
<meta charset="UTF-8">
<title>パスワード変更</title>
<style>
body {
	font-family: "Yu Gothic UI", sans-serif;
	background-color: #e6f0fa;
	display: flex;
	justify-content: center;
	align-items: center;
	height: 100vh;
}

.change-box {
	background: white;
	padding: 40px;
	border-radius: 20px;
	box-shadow: 0 10px 20px rgba(0, 0, 0, 0.1);
	width: 400px;
}

h2 {
	text-align: center;
	margin-bottom: 20px;
	color: #2c7be5;
}

.input-group {
	margin-bottom: 20px;
}

.input-group input {
	width: 100%;
	padding: 12px;
	font-size: 16px;
	border-radius: 10px;
	border: 1px solid #ccc;
}

.btn-submit {
	width: 100%;
	padding: 12px;
	background: #2c7be5;
	color: white;
	font-size: 16px;
	border: none;
	border-radius: 10px;
}

.error-message {
	color: red;
	text-align: center;
	margin-bottom: 10px;
}
</style>
</head>
<body>
	<div class="change-box">
		<h2>パスワード変更</h2>

		<%
		String error = (String) request.getAttribute("error");
		%>
		<%
		if (error != null) {
		%>
		<div class="error-message"><%=error%></div>
		<%
		}
		%>

		<form action="<%=request.getContextPath()%>/changePassword"
			method="post">
			<div class="input-group">
				<input type="password" name="currentPassword" placeholder="現在のパスワード"
					required>
			</div>
			<div class="input-group">
				<input type="password" name="newPassword" placeholder="新しいパスワード"
					required>
			</div>
			<div class="input-group">
				<input type="password" name="confirmPassword" placeholder="確認パスワード"
					required>
			</div>
			<button type="submit" class="btn-submit">✔ 変更</button>
		</form>
	</div>
</body>
</html>
