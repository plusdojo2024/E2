<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">

<meta name="viewport"
   content="width=320,
      height=480,
      initial-scale=1.0,
      minimum-scale=1.0,
      maximum-scale=2.0,
      user-scalable=yes" />

<title>健康管理改善アプリ_新規登録</title>

<link rel="stylesheet" href="/E2/css/reset.css">
<link rel="stylesheet" href="/E2/css/style.css">
<link rel="stylesheet" href="/E2/css/regist.css">

</head>

<body class="login">
<div class="wrap">
    <div class="bg"></div>
    <div class="bg bg2"></div>
    <div class="bg bg3"></div>

<header>
    <ul>
        <li>
    <img src="img/スクリーンショット 2024-06-13 143834.png" width="30%" height="30%">
    <img src="img/新ロゴ.png" width="30%" height="30%">
        </li>
    </ul>
</header>



    <main><br>
    <h1>アカウント新規作成</h1><br>

 	<form method="post" action="/E2/RegistServlet" id = "formObj">
    <p id = "messeage">メールアドレス、パスワードは半角英数字で<br>
    入力してください。<br><br>
    パスワードは大文字、小文字、数字、記号を<br>
    組み合わせ、10桁以上の長さにしてください。<br><br></p>
    <p id = "mail">メールアドレス<input type="text" name="mail_address"><br><br></p>
    パスワード<input type="password" name="password"><br><br>
    <input type="submit" value="登録" id = "regist">
	</form><br>
	<h1 id= "error">${error}</h1>
    </main>



</div>
<script>

	//エラーメッセージを表示させるjavascript
	let form = document.getElementById("formObj");
	let error = document.getElementById("error");

	form.onsubmit = function(){

	if(!form.mail_address.value || !form.password.value){//!form.name属性.value(ユーザーが入力した値)=フォームに入っている値がない

		error.textContent = "メールアドレス、パスワードを入力してください。";
		return false;

		}
	};





</script>
</body>
</html>



