<%@ page contentType="text/html; charset=UTF-8"%>
<%@ page import="com.adminbean.AdminUserBean"%>
<%
AdminUserBean user = (AdminUserBean) request.getAttribute("userEdit");
String errorMsg = (String) request.getAttribute("errorMsg");

if (user == null) {
	response.sendRedirect(request.getContextPath() + "/empListServlet");
	return;
}
%>
<!DOCTYPE html>
<html lang="ja">
<head>
<meta charset="UTF-8">
<title>社員情報編集</title>
<style>
body {
	font-family: "Yu Gothic UI", sans-serif;
	background-color: #f2f2f2;
	height: 100vh;
	display: flex;
	justify-content: center;
	align-items: center;
	margin: 0;
}

.container {
	width: 400px;
	background-color: #fff;
	border: 1px solid #ccc;
	border-radius: 10px;
	padding: 30px;
	box-shadow: 0 0 10px rgba(0, 0, 0, 0.1);
}

h1 {
	text-align: center;
	margin-bottom: 20px;
}

.field {
	margin-bottom: 15px;
	display: flex;
	align-items: center;
}

label {
	width: 100px;
	font-weight: bold;
}

input[type="text"], input[type="password"] {
	flex: 1;
	padding: 8px;
	border: 1px solid #ccc;
	border-radius: 5px;
}

.radio-group {
	display: flex;
	gap: 10px;
}

.buttons {
	text-align: center;
	margin-top: 20px;
}

.buttons input {
	padding: 8px 20px;
	margin: 0 10px;
	border: 1px solid #ccc;
	border-radius: 5px;
	background-color: #f9f9f9;
	cursor: pointer;
}

.buttons input:hover {
	background-color: #e6e6e6;
}

.error {
	color: red;
	text-align: center;
	margin-bottom: 15px;
}
</style>
</head>
<body>

	<div class="container">
		<h1>社員情報編集</h1>

		<%
		if (errorMsg != null) {
		%>
		<p class="error"><%=errorMsg%></p>
		<%
		}
		%>

		<form action="<%=request.getContextPath()%>/UpdateEmployeeServlet"
			method="post">
			<div class="field">
				<label>社員ID:</label> <input type="text" name="emp_id"
					value="<%=user.getEmp_id()%>">
			</div>

			<div class="field">
				<label>社員名:</label> <input type="text" name="emp_name"
					maxlength="100"
					value="<%=user.getEmp_name() != null ? user.getEmp_name() : ""%>">
			</div>

			<div class="field">
				<label>部署:</label>
				<div class="radio-group">
					<label><input type="radio" name="role_id" value="1"
						<%=user.getRole_id() == 1 ? "checked" : ""%>>営業部</label> <label><input
						type="radio" name="role_id" value="2"
						<%=user.getRole_id() == 2 ? "checked" : ""%>>管理部</label> <label><input
						type="radio" name="role_id" value="3"
						<%=user.getRole_id() == 3 ? "checked" : ""%>>部長</label>
				</div>
			</div>

			<div class="field">
				<label>役職:</label> <input type="text" name="emp_position"
					maxlength="50"
					value="<%=user.getEmp_position() != null ? user.getEmp_position() : ""%>">
			</div>

			<div class="field">
				<label>パスワード:</label> <input type="password" name="pass"
					maxlength="50"
					value="<%=user.getPass() != null ? user.getPass() : ""%>">
			</div>

			<div class="buttons">
				<input type="submit" value="更新"> <input type="button"
					value="キャンセル"
					onclick="location.href='<%=request.getContextPath()%>/empListServlet';">
			</div>
		</form>
	</div>

</body>
</html>
