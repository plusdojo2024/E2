<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>ホームページ</title>
</head>
<body>
<a href="/E2/VoiceServlet">test</a>
    <h2>情報を入力してください</h2>
    <p>生体情報</p>
    <form action="HomeServlet" method="post">
    <input type="hidden" name="formType" value="hform">
        身長: <input type="text" name="height" value="${humans.height}"><br>
        体重: <input type="text" name="weight" value="${humans.weight}"><br>
        睡眠時間: <input type="text" name="sleep_time" value="${humans.sleepTime}"><br>
        <input type="submit" value="送信">
    </form>


    <p>食事情報</p>
    <div>
    <button id="add-button">入力欄を増やす</button>
    <form id="meal-form" action="HomeServlet" method="post">
    <input id="counts" type="hidden" name="count" value="0">
     <input type="hidden" name="formType" value="mform">
     <div class="meal-entry">
        食事: <input type="text" name="mealMenu_0"><br>
        カロリー: <input type="text" name="kcal_0"><br>
        ジャンル: <input type="text" name="genre_0"><br>
     </div>
        <input type="submit" value="送信">
    </form>
    </div>

    <script>
    // 入力フォームを増やす処理
    let count=0;
        document.getElementById('add-button').addEventListener('click', function() {
        	count++;
        	document.getElementById("counts").setAttribute("value",count);
            const mealForm = document.getElementById('meal-form');
            const newEntry = document.createElement('div');
            newEntry.className = 'meal-entry';
            newEntry.innerHTML = `
                食事: <input type="text" name="mealMenu_"><br>
                カロリー: <input type="text" name="kcal_"><br>
                ジャンル: <input type="text" name="genre_"><br>`;
            mealForm.insertBefore(newEntry, mealForm.querySelector('input[type="submit"]'));
            newEntry.querySelector('input[name="mealMenu_"]').setAttribute("name", "mealMenu_" + count);
            newEntry.querySelector('input[name="kcal_"]').setAttribute("name", "kcal_" + count);
            newEntry.querySelector('input[name="genre_"]').setAttribute("name", "genre_" + count);
        });
    </script>


</body>
</html>