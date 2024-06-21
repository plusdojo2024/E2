<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
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
<title>テストホーム</title>
<!--reset.cssでデフォルトの設定を無効化 -->
<link rel="stylesheet" href="css/reset.css">
<link rel="stylesheet" href="css/style.css">
<link rel="stylesheet" href="css/home.css">
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
              <li><a href="/E2/VoiceServlet" data-number="3"><img src="img/icon/onp.png" width="20%" height="20%">ショップ</a></li>
              <li><a href="/E2/LogoutServlet" data-number="4"><img src="img/icon/logout.png" width="20%" height="20%">ログアウト</a></li>
            </ul>
          </nav>
    </div>
    </div>
    <!-- ハンバーガーメニュー終了 -->

     <!-- メイン -->

     <!-- ホームと音楽ショップにおくスライダー、それ以外の画面では削除-->
    <main id="home">
      <div id="slide">
        <ul>
          <li><img src="img/運動.jpg" width="95%" height="80%" alt="img1"></li>
          <li><img src="img/食事.jpg" width="95%" height="80%" alt="img2"></li>
          <li><img src="img/睡眠.jpg" width="95%" height="80%" alt="img3"></li>
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
    <div class="menu">
        <h2>身体登録機能</h2>
        <form id="human-form" action="HomeServlet" method="post">
        <input type="hidden" name="formType" value="hform">
            <p>身長:<input type="text" size="25" name="height" value="${humans.height}"></p>
            <p>体重:<input type="text" size="25" name="weight" value="${humans.weight}"></p>
            <p>睡眠時間:<input type="text"  name="sleep_time" value="${humans.sleepTime}"></p>
       	<%
    		String HerrorMessage = (String)request.getAttribute("HerrorMessage");
    		if(HerrorMessage != null && !HerrorMessage.isEmpty()) {
    	%>
    		<div class="errorMessage">
    			<%= HerrorMessage %>
			</div>
    	<%
    		}
    	%>
            <input type="reset" id="register" name="reset" value="リセット">
            <input type="submit" id="register" value=" 登 録　">
        </form>
        </div>

        <div class="menu">
        <h2>食事登録機能</h2>
        <form id="meal-form" action="HomeServlet" method="post">
        <input id="counts" type="hidden" name="count" value="0">
         <input type="hidden" name="formType" value="mform">
         <div class="meal-entry">
            <p>メニュー:<input type="text" name="mealMenu_0"><br></p>
            <p>カロリー:<input type="text" name="kcal_0"><br></p>
            <p>ジャンル:<input type="text" name="genre_0"><br></p>
         </div>
        <%
    		String MerrorMessage = (String)request.getAttribute("MerrorMessage");
    		if(MerrorMessage != null && !MerrorMessage.isEmpty()) {
    	%>
    		<div class="errorMessage">
    			<%= MerrorMessage %>
			</div>
    	<%
    		}
    	%>
            <input type="reset" id="register" name="reset" value="リセット">
            <button id="add-button"> 追 加　</button>
            <input type="submit" id="register" value=" 登 録　">
        </form>
        </div>
        <div class="menu">
            <h2>食事登録ジャンル表</h2>
            <img src="img/3大栄養素.jpg" width="95%" height="90%">
        </div>
     </main>
        <script>
        // 入力フォームを増やす処理
        let count=0;
            document.getElementById('add-button').addEventListener('click', function() {

                 event.preventDefault(); // デフォルトの送信動作をキャンセルする
                // エラーメッセージを削除する
                const errorMessageDiv = document.querySelector('.errorMessage');
                if (errorMessageDiv) {
                    errorMessageDiv.parentNode.removeChild(errorMessageDiv); // エラーメッセージをDOMから削除する
                }
                count++;
                document.getElementById("counts").setAttribute("value",count);
                const mealForm = document.getElementById('meal-form');
                const newEntry = document.createElement('div');
                newEntry.className = 'meal-entry';
                newEntry.innerHTML = `
                    <div class="meal-entry">
                    <p>メニュー:<input type="text" name="mealMenu_"><br></p>
                    <p>カロリー:<input type="text" name="kcal_"><br></p>
                    <p>ジャンル:<input type="text" name="genre_"><br></p>
                    </div>`;
                mealForm.insertBefore(newEntry, mealForm.querySelector('input[type="reset"]'));
                newEntry.querySelector('input[name="mealMenu_"]').setAttribute("name", "mealMenu_" + count);
                newEntry.querySelector('input[name="kcal_"]').setAttribute("name", "kcal_" + count);
                newEntry.querySelector('input[name="genre_"]').setAttribute("name", "genre_" + count);
            });

            // 身体登録機能フォームの送信時に確認ダイアログを表示する
            document.getElementById('human-form').addEventListener('submit', function(event) {
                if (!confirm('身体情報を登録します。よろしいですか')) {
                    event.preventDefault();
                }
            });

            // 食事登録機能フォームの送信時に確認ダイアログを表示する
            document.getElementById('meal-form').addEventListener('submit', function(event) {
                if (!confirm('食事を登録します。よろしいですか')) {
                    event.preventDefault();
                }
            });
        </script>
    <!-- メイン終了 -->

    <!-- フッター -->
    <footer>
      <a href="#body" class="page_top"><img src="img/icon/yazirusi.png"></a>

    </footer>
    <!-- フッター終了 -->

 </div>
</body>
</html>
