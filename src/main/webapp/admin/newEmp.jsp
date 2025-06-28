<%@ page contentType="text/html; charset=UTF-8"%>
<%@ page import="com.adminbean.AdminUserBean"%>
<%
boolean isUpdate = request.getAttribute("employee") != null;
AdminUserBean employee = isUpdate ? (AdminUserBean) request.getAttribute("employee") : new AdminUserBean();
String actionForm = isUpdate ? "UpdateEmployeeServlet" : "newEmpServlet";
String message = (String) request.getAttribute("message");
%>
<html>
<head>
<title>社員新規登録</title>
<style>
body {
	font-family: "Yu Gothic UI", sans-serif;
	background-color: #f2f2f2;
	height: 100vh;
	margin: 0;
	display: flex;
	justify-content: center;
	align-items: center;
}

.container {
	width: 450px;
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
	flex-wrap: wrap;
}

.buttons {
	text-align: center;
	margin-top: 20px;
}

.buttons input, .buttons button {
	padding: 8px 20px;
	margin: 0 10px;
	border: 1px solid #ccc;
	border-radius: 5px;
	background-color: #f9f9f9;
	cursor: pointer;
}

.buttons input:hover, .buttons button:hover {
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
		<h1>社員新規登録</h1>

		<%
		if (message != null) {
		%>
		<p class="error"><%=message%></p>
		<%
		}
		%>

		<form action="<%=request.getContextPath() + "/" + actionForm%>"
			method="post">

			<div class="field">
				<label>社員ID:</label> <input type="text" name="empId"
					value="<%=employee.getEmp_id() != null ? employee.getEmp_id() : ""%>"
					required>
			</div>

			<div class="field">
				<label>社員名:</label> <input type="text" name="empName" required
					value="<%=employee.getEmp_name() != null ? employee.getEmp_name() : ""%>">
			</div>

			<div class="field">
				<label>部署:</label>
				<div class="radio-group">
					<label><input type="radio" name="role_id" value="1"
						<%=employee.getRole_id() == 1 ? "checked" : ""%>> 営業部</label> <label><input
						type="radio" name="role_id" value="2"
						<%=employee.getRole_id() == 2 ? "checked" : ""%>> 管理部</label> <label><input
						type="radio" name="role_id" value="3"
						<%=employee.getRole_id() == 3 ? "checked" : ""%>> 部長</label>
				</div>
			</div>

			<div class="field">
				<label>役職:</label> <input type="text" name="empPosition"
					value="<%=employee.getEmp_position() != null ? employee.getEmp_position() : ""%>">
			</div>

			<div class="field">
				<label>パスワード:</label> <input type="text" name="pass"
					value="<%=employee.getPass() != null ? employee.getPass() : ""%>">
			</div>

			<div class="buttons">
				<input type="submit" value="登録"> <input type="reset"
					value="クリア">
				<button type="button"
					onclick="location.href='<%=request.getContextPath()%>/admin/sales.jsp'">戻る</button>
			</div>

		</form>
	</div>

</body>
</html>
