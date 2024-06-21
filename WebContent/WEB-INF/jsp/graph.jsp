<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>

<html>
<head>
<meta charset="UTF-8">
<!--スマホ画面の大きさに応じて幅を合わせる -->
<meta name="viewport"
   content="width=320,
      height=480,
      initial-scale=1.0,
      minimum-scale=1.0,
      maximum-scale=2.0,
      user-scalable=yes" />
<!-- 終了 -->
<title>健康管理改善アプリ</title>
<!--reset.cssでデフォルトの設定を無効化 -->
<link rel="stylesheet" href="css/reset.css">
<link rel="stylesheet" href="css/style.css">
<link rel="stylesheet" href="css/graph.css">
<script src="js/jquery-3.7.1.js"></script>
<script src="js/script.js"></script>
<script src="https://cdnjs.cloudflare.com/ajax/libs/Chart.js/2.7.2/Chart.bundle.js"></script>
</head>


<body id="body">
  <!-- 背景アニメーション -->
  <div class="bg"></div>
<div class="bg bg2"></div>
<div class="bg bg3"></div>
 <div class="wrap">
    <!-- ヘッダー -->
  <header>
    <img src="img/rogo.png" height="35px" width="35px">
  </header>
    <!-- ヘッダー終了 -->

    <!-- ハンバーガーメニュー -->
    <div id="menu">
    <div>
        <input type="checkbox" id="checkbox" class="checkbox">
        <label for="checkbox" class="hamburger">
          <span class="bar bar-top"></span>
          <span class="bar bar-middle"></span>
          <span class="bar bar-bottom"></span>
        </label>
        <!-- もしホームにいない場合はホームボタンは現在のページのリンクを消してナンバー1にリンクをはる
         例：現在のページが”データ履歴”の場合
         <li><a href="#" data-number="1"><img src="img/icon/home.png" width="20%" height="20%">ホーム</a></li>
              <li><a href="#" data-number="2"><img src="img/icon/objective.png" width="20%" height="20%">目標設定</a></li>
              <li><a href="#" data-number="3"><img src="img/icon/onp.png" width="20%" height="20%">ショップ</a></li>
              <li><a href="#" data-number="4"><img src="img/icon/logout.png" width="20%" height="20%">ログアウト</a></li> -->
        <nav class="nav-menu">
<ul>
  <li><a href="/eclipse_gaibu_test/HomeServlet" data-number="1"><img src="img/icon/home.png" width="20%" height="20%">ホーム</a></li>
  <li><a href="/eclipse_gaibu_test/SetServlet" data-number="2"><img src="img/icon/objective.png" width="20%" height="20%">目標設定</a></li>
  <li><a href="/eclipse_gaibu_test/VoiceServlet" data-number="3"><img src="img/icon/onp.png" width="20%" height="20%">ショップ</a></li>
  <li><a href="/eclipse_gaibu_test/LogoutServlet" data-number="4"><img src="img/icon/logout.png" width="20%" height="20%">ログアウト</a></li>
</ul>
          </nav>
    </div>
    </div>
    <!-- ハンバーガーメニュー終了 -->

<!-- メイン -->


    <!--div項目で内容を囲む-->
      <ul class="main">
        <div>
        <li class="hyou">
          <h2>一週間の睡眠時間</h2>
          <canvas id="graph" width="50%" height="40%"></canvas>
				<c:forEach var="e" items="${humans}" >
					<p hidden class="sleeptime" class="hyou">${e.sleepTime}</p>
					<p hidden class="day" class="hyou">${e.day}</p>
				</c:forEach>
        </li>
      </div>
        <li>
          <div>
          <h2>一週間の身長・体重</h2>
          <table class="humanTable">
            <tbody>
              <tr><th>日付</th><th>身長</th><th>体重</th></tr>
                <c:forEach var="e" items="${humans}" >
                  <tr><td>${e.day }</td><td>${e.height }cm</td><td>${e.weight }</td></tr>
                </c:forEach>
            </tbody>
          </table>
        </li>
        <li>
          <div>
            <h2>一週間の食事履歴</h2>
          <table class="mealsTable">
            <tbody>
              <tr><th>日付</th><th>メニュー</th></tr>
                <c:forEach var="e" items="${meals}" >
                  <tr><td>${e.day }</td><td>${e.meal}</td></tr>
                </c:forEach>
                <tr><th>カロリー</th><th>ジャンル</th></tr>
                <c:forEach var="e" items="${meals}" >
                  <tr><td>${e.kcal}kcal</td><td>${e.mealBalance}</td></tr>
                </c:forEach>
            </tbody>
          </table>
          </div>
        </li>
      </ul>
      </div>
   </main>
<script>
let sleeptime = document.getElementsByClassName('sleeptime');
let day = document.getElementsByClassName('day');
let time =[];
let days = [];
for (let i of sleeptime){
	console.log(i.textContent);
	let a = parseInt(i.textContent);
	time.push(a);
}
for (let i of day){
	console.log(i.textContent);
	days.push(i.textContent);
}
console.log(time);
console.log(days);

var ctx = document.getElementById("graph");

var myLineChart = new Chart(ctx, {
  // グラフの種類：折れ線グラフを指定
  type: 'line',
  data: {
    // x軸の各メモリ
    labels: days,
    datasets: [
      {
        label: '睡眠時間',
        data: time,
        borderColor: "#ec4343",
        backgroundColor: "#00000000"
      },
    ],
  },
  options: {
    title: {
      display: true,
    },
    scales: {
      yAxes: [{
        ticks: {
          suggestedMax: 12,
          suggestedMin: 0,
          stepSize: 2,  // 縦メモリのステップ数
          callback: function(value, index, values){
            return  value +  '時間'  // 各メモリのステップごとの表記（valueは各ステップの値）
          }
        }
      }]
    },
  }
});


</script>
    <!-- フッター -->
    <footer>
      <a href="#body" class="page_top"><img src="img/icon/yazirusi.png"></a>
    </footer>
    <!-- フッター終了 -->

 </div>
</body>
</html>
