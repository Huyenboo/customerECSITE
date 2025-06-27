<%@ page contentType="text/html; charset=UTF-8" %>

<html>
<head>
<meta charset="UTF-8">
<title>売上日計表</title>
<style>
body {
    font-family: "Yu Gothic UI", sans-serif;
    text-align: center;
}
.container {
    max-width: 800px;
    margin: 20px auto;
    text-align: left;
}
h1 {
    text-align: center;
    margin-bottom: 20px;
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
}
th {
    background: #eee;
}
.search-section {
    margin: 15px 0;
    text-align: center;
}
input[type="text"], select {
    padding: 3px;
    width: 70px;
}
button {
    padding: 3px 8px;
}
.total {
    font-weight: bold;
    margin-top: 15px;
}
</style>
</head>
<body>

<h1>売上日計表</h1>


<div class="search-section">
    表示対象： 

    <label>月別</label>
    <br><br>
対象月：
    <input type="text" placeholder="年/月" > 

    <button>表示</button>
</div>

<div class="container">
    <table>
        <tr>
            <th>No</th>
            <th>顧客名</th>
            <th>商品ID</th>
            <th>商品名</th>
            <th>数量</th>
            <th>単価</th>
            <th>小計</th>
        </tr>

        <tr><td>1</td><td>コーナンあるる野市店</td><td>1000000262</td><td>アゲラタム ハワイ シェルピンク</td><td>1000</td><td>32.13</td><td>3213</td></tr>
        <tr><td>2</td><td>コーナンいすみ市店</td><td>1000002579</td><td>アゲラタム ハワイ ミックス</td><td>200</td><td>35.12</td><td>7024</td></tr>
        <tr><td>3</td><td>コーナン安房鴨川多里町店</td><td>1000000049</td><td>アスター キャンディボックス ミックス</td><td>100</td><td>22.54</td><td>2254</td></tr>
        <tr><td>4</td><td>コーナン印西市店</td><td>1000000046</td><td>アネモネ モナリザ ピンクフラッシュ</td><td>200</td><td>23.42</td><td>4684</td></tr>
        <tr><td>5</td><td>コーナン浦安市店</td><td>1000003172</td><td>アリッサム アフロディーテ サーモン</td><td>100</td><td>24.42</td><td>2412</td></tr>

    </table>

    <p class="total">合計売上金額：11,800円</p>

    <div style="text-align: center;">
        <a href="">TOPへ戻る</a>
    </div>
</div>

</body>
</html>
