<%@ page contentType="text/html;charset=UTF-8" language="java"%>
<%@ page import="com.bean.User,java.util.*"%>


<%
List<User> list = (List<User>) request.getAttribute("listUserPending");
%>
<!DOCTYPE html>
<html lang="ja">
<head>
<meta charset="UTF-8">
<title>顧客審査</title>
<style>
body {
	font-family: "Yu Gothic UI", sans-serif;
	background-color: #f0f8ff;
	padding: 30px;
}

h2 {
	text-align: center;
	color: #2c7be5;
}

.container {
	display: flex;
	justify-content: space-around;
	margin-top: 30px;
}

.list-section, .change-section {
	background-color: #fff;
	padding: 20px;
	border-radius: 15px;
	box-shadow: 0 8px 15px rgba(0, 0, 0, 0.1);
	width: 45%;
}

table {
	width: 100%;
	border-collapse: collapse;
}

table, th, td {
	border: 1px solid #ccc;
	text-align: center;
	padding: 8px;
}

button {
	padding: 5px 15px;
	margin: 0 5px;
	cursor: pointer;
}

select, input[type="text"] {
	padding: 5px;
	width: 80%;
	margin: 10px 0;
}
</style>
</head>

<body>
	<h2>顧客審査</h2>

	<div class="container">


		<div class="list-section">
			<h3>待機中ユーザ</h3>
			<table>

				<thead>
					<tr>
						<th>会社名</th>
						<th>操作</th>
					</tr>
				</thead>



				<tbody>
					<%
					if (list != null && !list.isEmpty()) {
						for (User user : list) {
					%>


					<tr>
						<td><%=user.getCompanyName()%></td>
						<td>
							<form action="<%=request.getContextPath()%>/newCustomerAllow" method="post" style="display: inline;">
								<input type="hidden" name="userId" value="<%= user.getId() %>">
								<button type="submit">承認</button>
							</form>
							<form action="" method="post"
								style="display: inline;">
								<input type="hidden" name="companyName" value=""> 
								<button type="submit">不可</button>
							</form>
						</td>
					</tr>
					<%
					}

					} else {
					%>
					<tr>
						<td colspan="10">新規登録かいしゃがない</td>
					</tr>
					<%
					}
					%>
				</tbody>

			</table>
		</div>

		<!-- Phần thay đổi trạng thái -->
		<div class="change-section">
			<h3>ステータス変更</h3>
			<form action="ReviewServlet" method="post">
				<div>
					<input type="text" name="searchKeyword" placeholder="会社名を入力">
					<div>
						<button type="submit" name="action" value="filter">検索</button>
					</div>
				</div>
				<div>
					<select name="statusFilter">
						<option value="">ステータス</option>
						<option value="承認">承認</option>
						<option value="待機">待機</option>
						<option value="不可">不可</option>
					</select>
				</div>

			</form>
		</div>

	</div>

</body>
</html>