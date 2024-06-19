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

<title>健康管理改善アプリ_ログイン</title>

<link rel="stylesheet" href="/E2/css/reset.css">
<link rel="stylesheet" href="/E2/css/style.css">
<link rel="stylesheet" href="/E2/css/login.css">

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


 	<form method="post" action="/E2/LoginServlet" >

    <p id = "mail">メールアドレス<input type="text" name="mail_address"><br><br></p>
    パスワード<input type="text" name="password"><br><br>
    <input type="submit" value="ログイン" id = "login"><br><br>
	<a href = /E2/RegistServlet>新規登録はこちら</a>
	</form><br>

    </main>



</div>
</body>
</html>