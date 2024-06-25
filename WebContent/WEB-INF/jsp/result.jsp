<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>

<!doctype html>
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
<script src="js/jquery-3.7.1.js"></script>
<script src="js/script.js"></script>
</head>


<body id="body">
  <!-- 背景アニメーション -->
  <div class="bg"></div>
<div class="bg bg2"></div>
<div class="bg bg3"></div>
 <div class="wrap">
    <!-- ヘッダー -->
  <header>
    <img src="img/スクリーンショット 2024-06-13 143834.png" height="35px" width="35px">
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
              <li><a href="/E2/SetServlet" data-number="1"><img src="img/icon/objective.png" width="20%" height="20%">目標設定</a></li>
              <li><a href="/E2/GraphServlet" data-number="2"><img src="img/icon/rireki.png" width="20%" height="20%">データ履歴</a></li>
              <li><a href="/E2/HomeServlet" data-number="3"><img src="img/icon/home.png" width="20%" height="20%">ホーム</a></li>
              <li><a href="/E2/VoiceServlet" data-number="4"><img src="img/icon/onp.png" width="20%" height="20%">ショップ</a></li>
            </ul>
          </nav>
    </div>
    </div>
    <!-- ハンバーガーメニュー終了 -->

     <!-- メイン -->
    <main id="result">

        <div class="gouhi">
            <h2>結果は</h2>
            <p><strong class="ten">${score}</strong>点/${totalScore}点</p>
            <h2>${result}です</h2>
            <p>${addPoint}ポイント獲得しました</p>
            </div>
    <!--div項目で内容を囲む-->
      <ul class="main">
            <li>
                <div class="coment">
                <p>${sentense1}</p>
                <p class="btn" onclick="audio()"><img src="img/icon/voice.png"></p>

                <audio id="btn_audio">
                    <source src= ${sentense2} type="audio/mp3">
                </audio>

                </div>
            </li>
          <li class="chara">
                <img  src="${sentense3}" width="100%" height="100%">

          </li>
          </ul>
          <p class="inyou">${sentense4}</p>
        </main>
    <!-- メイン終了 -->

    <!-- フッター -->
    <footer>
      <a href="#body" class="page_top"><img src="img/icon/yazirusi.png"></a>
    </footer>
    <!-- フッター終了 -->
 </div>

 <script>
     // ボイス再生
     function audio() {
    document.getElementById('btn_audio').currentTime = 0; //連続クリックに対応
    document.getElementById('btn_audio').play(); //クリックしたら音を再生
}
    </script>
</body>
</html>
