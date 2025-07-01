<%@ page contentType="text/html; charset=UTF-8"%>
<%@ page import="com.bean.Product"%>
<%@ page import="com.adminbean.AdminUserBean"%>
<%@ page import="com.dao.ProductDAO"%>
<%
AdminUserBean user = (AdminUserBean) session.getAttribute("loginUser");
if (user == null) {
	response.sendRedirect(request.getContextPath() + "/admin/adminLogin.jsp");
	return;
}

Product p = (Product) request.getAttribute("product");
if (p == null) {
	response.sendRedirect(request.getContextPath() + "/ProductManagementServlet");
	return;
}

String error = (String) request.getAttribute("error");

%>
<html>
<head>
<title>商品編集</title>
<style>
.container {
	max-width: 600px;
	margin: 20px auto;
	padding: 10px;
}

.field {
	margin-bottom: 12px;
}

label {
	display: inline-block;
	width: 100px;
	vertical-align: top;
}

input, textarea {
	width: 70%;
}


.buttons {
    margin-top: 20px;
    text-align: right;
}

.buttons input[type="submit"] {
    width: 80px;   
    padding: 5px;
    border: 1px solid #333;
    background-color: #fff;
    color: #000;
    cursor: pointer;
    margin-left: 10px;
}

.buttons input[type="submit"]:hover {
    background-color: #f0f0f0;
}

.buttons button {
    padding: 6px 25px;
    border: 1px solid #333;
    background-color: #fff;
    color: #000;
    cursor: pointer;
    margin-left: 10px;
}

.buttons button:hover {
    background-color: #f0f0f0;
}
</style>
</head>
<body>
	<div class="container">
		<h1>商品編集</h1>
		 <% if (error != null) { %>
	<p style="color: red;"><%=error %></p>
	<% } %>

		<form action="<%=request.getContextPath()%>/EditProductServlet"
			method="post">
			<input type="hidden" name="id" value="<%=p.getId()%>" />
			<div class="field">
				<label>商品ID:</label><span><%=p.getProId()%></span>
			</div>
			<div class="field">
				<label>商品名:</label> <input type="text" name="proName"
					value="<%=p.getProName()%>" required />
			</div>
			<div class="field">
				<label>単価:</label> <input type="text" name="proPrice"
					value="<%=p.getProPrice()%>" />
			</div>
			<div class="field">
				<label>在庫数:</label> <input type="text" name="proUnitNum"
					value="<%=p.getProUnitNum()%>" />
			</div>
			<div class="field">
				<label>備考:</label>
				<textarea name="proMemo" rows="4"><%=p.getProMemo() != null ? p.getProMemo() : ""%></textarea>
			</div>

			<div class="buttons">
				<input type="submit" value="更新" />
				<button type="button"
					onclick="location.href='<%=request.getContextPath()%>/ProductManagementServlet';">キャンセル</button>
			</div>
		</form>
	</div>
</body>
</html>
