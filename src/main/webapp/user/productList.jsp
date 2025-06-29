<%@ page contentType="text/html;charset=UTF-8" language="java"%>
<%@ page import="java.util.*, com.bean.Product"%>

<%
List<Product> list = (List<Product>) request.getAttribute("productList");
%>

<!DOCTYPE html>
<html lang="ja">
<head>
<meta charset="UTF-8">
<title>商品一覧</title>
<style>
body {
	font-family: "Yu Gothic UI", sans-serif;
	background-color: #f5faff;
	margin: 0;
	padding: 20px;
}

.top-bar {
	display: flex;
	justify-content: space-between;
	align-items: center;
	margin-bottom: 10px;
}

.top-bar h2 {
	margin: 0 auto;
	text-align: center;
}

.cart-button {
	margin-left: auto;
	margin-right: 30px;
}

.search-box {
	display: flex;
	justify-content: center;
	gap: 50px;
	margin: 10px 0 20px 0;
}

.search-group {
	text-align: center;
}

table {
	width: 90%;
	border-collapse: collapse;
	margin: 0 auto 20px auto;
}

th, td {
	padding: 8px;
	border: 1px solid #aaa;
	text-align: center;
}

th {
	background-color: #e0f0ff;
}

.submit-btn {
	display: block;
	margin: 0 auto;
	padding: 10px 20px;
	background-color: #2c7be5;
	color: white;
	border: none;
	border-radius: 8px;
	font-size: 16px;
	cursor: pointer;
}

.submit-btn:hover {
	background-color: #1a5fc9;
}

.add-cart-btn {
	background-color: transparent;
	border: none;
	color: #2c7be5;
	cursor: pointer;
}

.add-cart-btn:hover {
	text-decoration: underline;
}

input[type="number"] {
	width: 50px;
	padding: 5px;
}
</style>
</head>
<body>

	<div class="top-bar">
		<div></div>
		<h2>商品一覧</h2>
		<div class="cart-button">
			<form action="<%=request.getContextPath()%>/showCart" method="get">
				<button type="submit" class="add-cart-btn">カートを見る</button>
			</form>
		</div>
	</div>

	<div class="search-box">
		<div class="search-group">
			<form action="<%=request.getContextPath()%>/SearchProduct"
				method="get">
				<label>商品ID検索</label><br> <input type="text" name="productId">
				<button type="submit">検索</button>
			</form>
		</div>
		<div class="search-group">
			<form action="<%=request.getContextPath()%>/SearchProduct"
				method="get">
				<label>商品名検索</label><br> <input type="text" name="keyword">
				<button type="submit">検索</button>
			</form>
		</div>
	</div>

	<table>
		<tr>
			<th>商品ID</th> 
			<th>商品名</th>
			<th>単価</th>
			<th>備考</th>
			<th>カート</th>

		</tr>

		<%
		if (list != null && !list.isEmpty()) {
			for (Product p : list) {
		%>
		<tr>
		
		<td><%=p.getProId()%></td>
			<td><%=p.getProName()%></td>
			<td>¥<%=p.getProPrice()%></td>
			<td><%=p.getProMemo()%></td>
			<td>
				<form method="post" action="<%=request.getContextPath()%>/addToCart">
					<input type="hidden" name="productId" value="<%=p.getProId()%>">
					<input type="number" name="quantity" value="1" min="1">
					<button type="submit" class="add-cart-btn">カートに入れる</button>
				</form>
			</td>
		</tr>
		<%
		}
		} else {
		%>
		<tr>
			<td colspan="5">商品が見つかりませんでした。</td>
		</tr>
		<%
		}
		%>
	</table>
	<div style="text-align: center; margin-top: 20px;">
		<%
		int currentPage = 1;// 現在のページ番号（初期値は1）
		int totalPages = 1; //総ページ数（初期値は1

		//リクエストから現在のページ番号を取得
		if (request.getAttribute("currentPage") != null) {

			// リクエストから総ページ数を取得
			currentPage = (Integer) request.getAttribute("currentPage");
		}
		if (request.getAttribute("totalPages") != null) {
			totalPages = (Integer) request.getAttribute("totalPages");
		}
		%>

		<div style="margin: 20px 0;">
			<%
			String baseUrl = request.getContextPath() + "/productList?page=";
			int maxPageToShow = 6;
			int totalPageCount = totalPages;

			//  currentPage　によって最初のページ番号や最後のページ番号を表示
			int half = maxPageToShow / 2;
			int startPage = currentPage - half;
			int endPage = currentPage + half;

			if (startPage < 1) {
				startPage = 1;
				endPage = Math.min(maxPageToShow, totalPageCount);
			}
			if (endPage > totalPageCount) {
				endPage = totalPageCount;
				startPage = Math.max(1, endPage - maxPageToShow + 1);
			}

			// ページを表示
			for (int i = startPage; i <= endPage; i++) {
				if (i == currentPage) {
			%>
			<strong><%=i%></strong>
			<%
			} else {
			%>
			<a href="<%=baseUrl + i%>"><%=i%></a>
			<%
			}
			}

			// 後ろのページがあれば ...を表示
			if (endPage < totalPageCount) {
			%>
			<span>...</span> <a href="<%=baseUrl + totalPageCount%>"><%=totalPageCount%></a>
			<%
}
%>

		</div>

	</div>




</body>
</html>