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
              <li><a href="/E2/HomeServlet" data-number="1"><img src="img/icon/home.png" width="20%" height="20%">ホーム</a></li>
              <li><a href="/E2/GraphServlet" data-number="2"><img src="img/icon/rireki.png" width="20%" height="20%">データ履歴</a></li>
              <li><a href="/E2/VoiceServlet" data-number="3"><img src="img/icon/onp.png" width="20%" height="20%">ショップ</a></li>
              <li><a href="/E2/LogoutServlet" data-number="4"><img src="img/icon/logout.png" width="20%" height="20%">ログアウト</a></li>
            </ul>
          </nav>
    </div>
    </div>
    <!-- ハンバーガーメニュー終了 -->

     <!-- メイン -->
    <main id="set">
    <!--div項目で内容を囲む-->
      <ul class="main">
        <li>
          <img src="character/zundamon/zunmon1.png" class="right" width="30%" height="30%">
          <div>
          <h2>目標設定</h2>
          <form method="post" action="/E2/SetServlet" onsubmit="return validateForm()">
            <div class="koumoku">
              <ul>
                <p>設定する項目を選んでください</p>
                <c:if test = "${goals.sleepGoal == 1}">
                  <li><input type="checkbox" name="sleep_goal" value="magazine" id="sleep_goal"><label for="sleep_goal">睡眠時間</label></li>
                </c:if>
                <c:if test = "${goals.sleepGoal == 2}">
                  <li><input type="checkbox" name="sleep_goal" value="magazine" checked id="sleep_goal"><label for="sleep_goal">睡眠時間</label></li>
                </c:if>
                <c:if test = "${goals.mealGoal == 1}">
                  <li><input type="checkbox" name="meal_goal" value="magazine" id="meal_goal"><label for="meal_goal">食事バランス</label></li>
                </c:if>
                <c:if test = "${goals.mealGoal == 2}">
                  <li><input type="checkbox" name="meal_goal" value="magazine" checked id="meal_goal"><label for="meal_goal">食事バランス</label></li>
                </c:if>
                <c:if test = "${goals.freeGoal == 1}">
                  <li><input type="checkbox" name="free_goal" value="magazine" id="free_goal"><label for="free_goal">自由目標</label></li>
                </c:if>
                <c:if test = "${goals.freeGoal == 2}">
                  <li><input type="checkbox" name="free_goal" value="magazine" checked id="free_goal"><label for="free_goal">自由目標</label></li>
                </c:if>
                <c:if test="${goals == null}">
                  <li><input type="checkbox" name="sleep_goal" value="magazine" id="sleep_goal"><label for="sleep_goal">睡眠時間</label></li>
                  <li><input type="checkbox" name="meal_goal" value="magazine" id="meal_goal"><label for="meal_goal">食事バランス</label></li>
                  <li><input type="checkbox" name="free_goal" value="magazine" id="free_goal"><label for="free_goal">自由目標</label></li>
                </c:if>
                <li><input type="text" name="write_free_goal" placeholder="自由目標の内容" value="${goals.whiteFreeGoal}" id="subject"></li>
                <li><input type="submit" value="送信" class="submit"></li>
              </ul>
            </div>
          </form>
          </div>
<script>
  function validateForm() {
    // チェックボックスの要素を取得
    var sleepGoalCheckbox = document.getElementById('sleep_goal');
    var mealGoalCheckbox = document.getElementById('meal_goal');
    var freeGoalCheckbox = document.getElementById('free_goal');
    var text = document.getElementById('subject').value;

    // どれも選択されていない場合に警告を表示
    if (!sleepGoalCheckbox.checked && !mealGoalCheckbox.checked && !freeGoalCheckbox.checked) {
      alert('最低1つはチェックを入れてください');
      return false; // フォーム送信をキャンセル
    }

    if(freeGoalCheckbox.checked && text.trim() === ''){
    	alert('自由目標を記述してください');
        return false; // フォーム送信をキャンセル
    }
    alert('送信が成功しました');
    return true; // フォーム送信を許可
  }
</script>


        </main>
    <!-- メイン終了 -->

    <!-- フッター -->
    <footer>
      <a href="#body" class="page_top"><img src="img/icon/yazirusi.png"></a>
    </footer>
    <!-- フッター終了 -->

 </div>
</body>
</html>
