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
	background-color: #fff;
	padding: 30px;
}

.container {
	max-width: 600px;
	margin: 0 auto;
	border: 1px solid #ccc;
	padding: 25px;
	border-radius: 10px;
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

input[type="text"] {
	flex: 1;
	padding: 6px;
	border: 1px solid #ccc;
	border-radius: 4px;
}

.radio-group {
	display: flex;
	gap: 15px;
}

button, input[type="submit"], input[type="reset"] {
	padding: 6px 12px;
	margin-right: 10px;
}
</style>
</head>
<body>

	<div class="container">
		<h1>社員新規登録</h1>

		<%
		if (message != null) {
		%>
		<p style="color: red; text-align: center;"><%=message%></p>
		<%
		}
		%>

		<form action="<%=request.getContextPath() + "/" + actionForm%>"
			method="post">

			<div class="field">
				<label>社員番号:</label> <input type="text" name="empId"
					value="<%=employee.getEmp_id() != null ? employee.getEmp_id() : ""%>"
					required />
			</div>

			<div class="field">
				<label>社員名:</label> <input type="text" name="empName" required
					value=<%=employee.getEmp_name() != null ? employee.getEmp_name() : ""%> />
			</div>

			<div class="field">
				<label>部署:</label>
				<div class="radio-group">
					<label> <input type="radio" name="role_id" value=1
						<%=employee.getRole_id() == 1 ? "checked" : ""%> required>
						営業部
					</label> <label> <input type="radio" name="role_id" value=2
						<%=employee.getRole_id() == 2 ? "checked" : ""%>> 管理部
					</label> <label><input type="radio" name="role_id" value=3
						<%=employee.getRole_id() == 3 ? "checked" : ""%>> 部長</label>

				</div>
			</div>

			<div class="field">
				<label>役職:</label> <input type="text" name="empPosition" />
			</div>

			<div class="field">
				<label>パスワード:</label> <input type="text" name="pass" />
			</div>

			<div class="field" style="justify-content: center;">
				<input type="submit" value=<%=isUpdate ? "登録" : "登録"%> /> <input
					type="reset" value="クリア" />
				<button type="button"
					onclick="location.href='ProductManagementServlet';">戻る</button>
			</div>

		</form>
	</div>

</body>
</html>
