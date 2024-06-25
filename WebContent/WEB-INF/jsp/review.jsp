<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
    <%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
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
<link rel="stylesheet" href="css/review.css">
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

    <!-- 自由目標選択画面 -->
	<c:if test="${goals.freeGoal == 2}">
     <div id="review">
    <div class="menu">
      <h2>自己評価</h2>
      <h3>自由目標の達成はできましたか？</h3>
     <form method="post" action="/E2/ReviewServlet">
        <h3>${goals.whiteFreeGoal}</h3>
        <p><label><input type="radio" name="point" value="10">そう思う</label><br></p>
        <p><label><input type="radio" name="point" value="5" checked>どちらでもない</label><br></p>
        <p><label><input type="radio" name="point" value="0">そう思わない</label></p>
        <input class="register" type="submit" value="結果を見る">
        </form>
    </div>
    </div>
    </c:if>
   <!-- 自由目標未選択画面 -->
	<c:if test="${goals.freeGoal == 1}">
   <div id="review">
   <div class="menu">
   <h2>評価</h2>
   <form method="post" action="/E2/ReviewServlet">
   <img src="img/pome.gif" width="95%" height="90%">
        <input class="register"  type="submit" value="結果を見る">
        </form>
        </div>
        </div>
        </c:if>

    <!-- 入れ替わる画像 -->
     <!-- スクリプト側に渡すために取ってます -->
    <c:forEach var="e" items="${charaList}" >
     <p class="charaList" hidden>${e}</p>
    </c:forEach>


    <img id="pic">
   <!-- メイン終了 -->

    <!-- フッター -->
    <footer>
    <!--移動する画像-->
    </footer>
    <!-- フッター終了 -->

<script>
let charaList = document.getElementsByClassName('charaList');
let chara = [];
let count = -1;

//順番に配列に格納している
for (let i of charaList){
	console.log(i.textContent);
	chara.push(i.textContent);
}

if (chara.length > 0) {
    picChange(); // 関数を実行
}

function picChange() {

  count++;

  // カウントが最大になれば配列を初期値に戻すため「0」を指定する
  if (count == chara.length) count = 0;

  // 画像選択
  document.getElementById("pic").src = chara[count];

  // 1秒ごとに実行
  setTimeout("picChange()", 3000);

}
</script>
</div>
</body>
</html>
