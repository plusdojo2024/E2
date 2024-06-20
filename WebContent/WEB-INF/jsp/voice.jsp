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
<link rel="stylesheet" href="/E2/css/reset.css">
<link rel="stylesheet" href="/E2/css/style.css">
<script src="/E2/js/jquery-3.7.1.js"></script>
<script src="/E2/js/script.js"></script>
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
              <li><a href="/E2/SetServlet" data-number="1"><img src="img/icon/objective.png" width="20%" height="20%">目標設定</a></li>
              <li><a href="/E2/GraphServlet" data-number="2"><img src="img/icon/rireki.png" width="20%" height="20%">データ履歴</a></li>
              <li><a href="/E2/HomeServlet" data-number="3"><img src="img/icon/home.png" width="20%" height="20%">ホーム</a></li>
              <li><a href="/E2/LogoutServlet" data-number="4"><img src="img/icon/logout.png" width="20%" height="20%">ログアウト</a></li>
            </ul>
          </nav>
    </div>
    </div>
    <!-- ハンバーガーメニュー終了 -->

     <!-- メイン -->

     <!-- ホームと音楽ショップにおくスライダー、それ以外の画面では削除-->
    <main id="voice">
      <div id="slide">
        <ul>
          <li><img src="img/voiceSlide/ずんだもん.png" width="95%" height="80%" alt="img1"></li>
          <li><img src="img/voiceSlide/満別花丸.png" width="95%" height="80%" alt="img2"></li>
          <li><img src="img/voiceSlide/春日部つむぎ.png" width="95%" height="80%" alt="img3"></li>
        </ul>
      </div>
        <div id="button">
          <ul>
            <li><a href="#" class="target">1</a></li>
            <li><a href="#">2</a></li>
            <li><a href="#">3</a></li>
          </ul>
      </div>
      <!-- ホームと音楽ショップにおくスライダー-->

    <!--div項目で内容を囲む-->
      <ul class="main">
        <li>
          <img src="character/zundamon/x_zundamon.png" class="right" width="30%" height="30%">
          <div>
          <h2>キャラクター</h2>
          <p>現在の所持ポイント<strong class="point" value="${point}">${point}</strong>pt</p>
          <form id="choiceForm" action="/E2/VoiceServlet" method="post">

          <!--キャラクターショップ-->
            <div id="chara">
              <ul>
              <!--ずんだもん-->
                <li><p><strong>1.ずんだもん</strong>　　　２０ポイント　　<br>
                  <img class="charaimg" src="character/zundamon/ずんだもんSD.png" width="30%" height="30%">
                  <p class="btn" id="btn1" onclick="soundbell(1)" ><img src="img/icon/voice.png" width="80%" height="80%"></p>
                  <audio id="btn_audio1">
                      <source src="voice/zundamon/login/login2.wav" type="audio/mp3">
                  </audio>
                  <input type="submit" id="register" name="choice1" id="choiceInput" value="${choice1}"></p></li>

			<!--花丸-->
                <li><p><strong>2.満別花丸</strong>　　　　２０ポイント　　<br>
                  <img class="charaimg" src="character/hanamaru/manbetsu-hanamaru_mini.png" width="30%" height="30%">
                  <p class="btn" id="btn2" onclick="soundbell(2)"><img src="img/icon/voice.png" width="80%" height="80%"></p>
                  <audio id="btn_audio2">
                      <source src="voice/hanamaru/login/login1.wav" type="audio/mp3">
                  </audio>
                  <input type="submit" id="register" name="choice2" id="choiceInput" value="${choice2}"></p></li>

			<!--つむぎ-->
                <li><p><strong>3.春日部つむぎ</strong>　　２０ポイント　　<br>
                  <img class="charaimg" src="character/tumugi/春日部つむぎSD.png" width="30%" height="30%">
                  <p class="btn" id="btn3" onclick="soundbell(3)"><img src="img/icon/voice.png" width="80%" height="80%"></p>
                  <audio id="btn_audio3">
                      <source src="voice/tumugi/login/login1.wav" type="audio/mp3">
                  </audio>
                  <input type="submit" id="register" name="choice3" id="choiceInput" value="${choice3}"></p></li>
              </ul>
              <p>読み上げソフトのVOICEBOX<br>ずんだもん.満別花丸.春日部つむぎ<br>を利用しています。</p>
            </div>
          </form>
          </div>
        </main>
    <!-- メイン終了 -->

    <!-- フッター -->
    <footer>
      <a href="#body" class="page_top"><img src="img/icon/yazirusi.png"></a>

    </footer>
    <!-- フッター終了 -->

 </div>
 <script>
 function submitForm(choice1,choice2,choice3) {
	    document.getElementById('choiceInput').value = choice1;
	    document.getElementById('choiceInput').value = choice2;
	    document.getElementById('choiceInput').value = choice3;
	    document.getElementById('choiceForm').submit();
	  }

  function soundbell(n)
  {
    // 割り当てるID名（引数のnを連結して指定）
    var id = 'btn_audio'+n ;
    // 初回の再生以外だったら音声ファイルの再生ポイントを先頭にしておく
    if( typeof( document.getElementById( id ).currentTime ) != 'undefined' )
    {
      document.getElementById( id ).currentTime = 0;
    }
    // [ID:sound-file]の音声ファイルを再生[play()]する
    document.getElementById( id ).play() ;
  }


  </script>
</body>
</html>

