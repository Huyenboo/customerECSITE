<%@ page contentType="text/html; charset=UTF-8" %>


<html>
<head>
<meta charset="UTF-8">
<title>注文管理</title>
<style>
body {
    font-family: "Yu Gothic UI", sans-serif;
}
h1 {
    text-align: center;
    margin-top: 20px;
}
.container {
    max-width: 900px;
    margin: 20px auto;
}
table {
    width: 100%;
    border-collapse: collapse;
    margin-top: 15px;
}
th, td {
    border: 1px solid #333;
    padding: 5px;
    text-align: center;
    font-size: 14px;
}
th {
    background: #eee;
}
.search-section {
    display: flex;
    justify-content: center;
    margin: 15px 0;
    gap: 20px;
}
input[type="text"] {
    width: 200px;
    padding: 3px;
}
button, .btn-link {
    padding: 3px 8px;
    margin-left: 5px;
    cursor: pointer;
}
a.btn-link {
    text-decoration: none;
    background: #ddd;
    border: 1px solid #aaa;
    padding: 3px 8px;
}
</style>
</head>
<body>

<div class="container">
    <h1>注文管理</h1>

    <div class="search-section">
        <form method="get">
            <input type="text" name="userId" placeholder="顧客ID" />
            <button type="submit">検索</button>
        </form>
    </div>

    <table>
        <tr>
            <th>発注日</th>
            <th>注文ID</th>
            <th>顧客名</th>
            <th>商品ID</th>
            <th>商品名</th>
            <th>数量</th>
            <th>単価</th>
            <th>小計</th>
            <th>お届け予定日</th>
            <th>備考</th>
            <th>編集</th>
        </tr>

        <tr><td>2025/6/18</td><td>116</td><td>A社</td><td>111</td><td>バラ白</td><td>500</td><td>30</td><td>15000</td><td>2025/6/18</td><td>確定</td><td><a href="#">確定| </a> <a href="#">編集| </a> <a href="#">削除</a></td></tr>
        <tr><td>2025/6/18</td><td>116</td><td>A社</td><td>112</td><td>アジサイ</td><td>400</td><td>20</td><td>8000</td><td>2025/6/18</td><td></td><td><a href="#">確定| </a> <a href="#">編集| </a> <a href="#">削除</a></td></tr>
        <tr><td>2025/6/18</td><td>115</td><td>B社</td><td>120</td><td>チュー</td><td>1000</td><td>12</td><td>12000</td><td>2025/6/18</td><td></td><td><a href="#">確定| </a> <a href="#">編集| </a> <a href="#">削除</a></td></tr>
        <tr><td>2025/6/15</td><td>114</td><td>C社</td><td>131</td><td>針槐</td><td>3000</td><td>42</td><td>126000</td><td>2025/6/15</td><td></td><td><a href="#">確定| </a> <a href="#">編集| </a> <a href="#">削除</a></td></tr>
        <tr><td>2025/6/15</td><td>114</td><td>C社</td><td>201</td><td>パンジ</td><td>400</td><td>11</td><td>4400</td><td>2025/6/15</td><td></td><td><a href="#">確定| </a> <a href="#">編集| </a> <a href="#">削除</a></td></tr>
        <tr><td>2025/6/14</td><td>113</td><td>D社</td><td>407</td><td>バラ赤</td><td>200</td><td>45</td><td>9000</td><td>2025/6/14</td><td></td><td><a href="#">確定| </a> <a href="#">編集| </a> <a href="#">削除</a></td></tr>
        <tr><td>2025/6/14</td><td>113</td><td>D社</td><td>409</td><td>ヒノキ</td><td>400</td><td>34</td><td>13600</td><td>2025/6/14</td><td></td><td><a href="#">確定| </a> <a href="#">編集| </a> <a href="#">削除</a></td></tr>

    </table>

    <div style="margin-top: 15px;">
        <a href="#" class="btn-link">TOPへ戻る</a>
    </div>
</div>

</body>
</html>
