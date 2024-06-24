package servlet;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.Random;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import dao.GoalsDAO;
import dao.HumansDAO;
import dao.MealDAO;
import dao.UsersDAO;
import model.Goal;
import model.Humans;
import model.LoginUser;
import model.Meal;
import model.User;
import model.WeekDay;

@WebServlet("/ResultServlet")
public class ResultServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;

		protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
			// もしもログインしていなかったらログインサーブレットにリダイレクトする
			HttpSession session = request.getSession();
			session.setAttribute("mail_address", new LoginUser("mail_address"));
			if (session.getAttribute("mail_address") == null) {
				response.sendRedirect("/E2/LoginServlet");
				return;
			}

			LoginUser loginUser = (LoginUser) session.getAttribute("mail_address");
			String mailAddress = loginUser.getMailAddress();
			UsersDAO uDao = new UsersDAO();
			User user = new User(0,mailAddress,"","",0,0,0,0);
			List<User> userList = uDao.selectMailAddress(user);
			User user1 = userList.get(0);
			int point1 = user1.getPoint();    //このpoint1に合格した場合の獲得pointを追加する
			int character1 = user1.getCharacter1();
			int character2 = user1.getCharacter2();
			int character3 = user1.getCharacter3();  //フラグ確認で使う

			GoalsDAO gDao = new GoalsDAO();
			Goal goal = new Goal(0,mailAddress,"",0,0,0,"");
			List<Goal>goalList = gDao.select(goal);
			Goal goal1 = new Goal();
			if(goalList != null) {
				if(goalList.size() != 0) {
					goal1 = goalList.get(0);
				}
			}
			int sleepGoal = goal1.getSleepGoal();
			int mealGoal = goal1.getMealGoal();
			int freeGoal = goal1.getFreeGoal();      //フラグ取得（１は未選択、２は選択）
			int score = 0;     //初期点数（これに加算していく）
			int totalScore = 0;   //フラグが２の時の最大点数
			int addPoint = 0;   //獲得point（表示point）
			String random1;
			String random2;
			String random3;
			String result;        //合否結果

			if(sleepGoal == 2) {
				totalScore += 20;
				HumansDAO hDao = new HumansDAO();
				ArrayList <String> dateList = WeekDay.previousWeekDates();
				int time = 0;
				int count = 0;
				for (String date : dateList) {
					Humans user2 = new Humans(0,mailAddress,date,0,0,0);
					List<Humans> userList2 = hDao.select(user2);
					if(userList2 != null) {
						if(userList2.size() != 0) {
							Humans user3 = userList2.get(0);
							time = time + user3.getSleepTime();
							count++;
						}
					}
		        }
				double averageTime = 0;
				if(count != 0) {
					averageTime = (double) time / count;
				}
				double gapTime = averageTime - 7;
				if(gapTime == 0) {
					score += 20;
				}else if(gapTime <= 1 && gapTime >= -1) {
					score += 12;
				}else if(gapTime <= 2 && gapTime >= -2) {
					score += 5;
				}
			}

			if(mealGoal == 2) {
				totalScore += 20;
				int countRed = 0;
				int countGreen = 0;
				int countYellow = 0;
				MealDAO mDao = new MealDAO();
				ArrayList <String> dateList = WeekDay.previousWeekDates();
				for (String date : dateList) {                            //	先週の食事バランスの３色のカウント
					Meal user2 = new Meal(0,mailAddress,date,"","",0,0);
					List<Meal> userList2 = mDao.select(user2);
					if(userList2 != null) {
						for(int i=0;i< userList2.size();i++) {
							Meal user3 = userList2.get(i);
							if(user3.getMealBalance().equals("赤")) {
								countRed++;
							}else if(user3.getMealBalance().equals("緑")) {
								countGreen++;
							}else if(user3.getMealBalance().equals("黄")){
								countYellow++;
							}
						}
					}
		        }
				if((countRed-countGreen<=3 && countRed-countGreen>=-3)&&(countRed-countYellow<=3 && countRed-countYellow>=-3)&&(countGreen-countYellow<=3 && countGreen-countYellow>=-3)) {
					score += 20;
				}else if((countRed-countGreen<=5 && countRed-countGreen>=-5)&&(countRed-countYellow<=5 && countRed-countYellow>=-5)&&(countGreen-countYellow<=5 && countGreen-countYellow>=-5)){
					score += 12;
				}else if((countRed-countGreen<=8 && countRed-countGreen>=-8)&&(countRed-countYellow<=8 && countRed-countYellow>=-8)&&(countGreen-countYellow<=8 && countGreen-countYellow>=-8)){
					score += 5;
				}
			}

			if(freeGoal == 2) {
				totalScore += 10;

				Object obj2 = session.getAttribute("point");
				String obj3 = obj2.toString();
				int point = Integer.parseInt(obj3);
				score += point;
			}

			if((double)score / totalScore >= 0.6) {
				result = "合格";
				addPoint = 30;
			}else {
				result = "不合格";
			}

			int resetPoint = point1 + addPoint;
			UsersDAO addDao = new UsersDAO();
			addDao.updatePoint(new User(0,"mail_address","","",resetPoint,0,0,0));





			if(character1 == 3) {     //選択済み
				random1 = "選択済み";
			}else {
				random1 = "未選択";
			}
			if(character2 == 3) {     //選択済み
				random2 = "選択済み";
			}else {
				random2 = "未選択";
			}
			if(character3 == 3) {     //選択済み
				random3 = "選択済み";
			}else {
				random3 = "未選択";
			}

			String[] array = new String[3];
			array[0] = random1;
			array[1] = random2;
			array[2] = random3;
			Random random = new Random();
			int randomIndex = random.nextInt(3);
			String selectSentense = array[randomIndex];
			String sentense1 = "";
			String sentense2 = "";
			String sentense3 = "";
			String sentense4 = "";

			while(true){
				if(selectSentense.equals("選択済み")){
					if(randomIndex == 0){                                                  //ずんだもん
						if(result.equals("合格")){
							int randomNum = random.nextInt(2);
							if(randomNum == 0){
								sentense1 = "やればできるじゃん。僕も鼻が高いのだっ。";
								sentense2 = "voice/zundamon/goukaku/goukaku1.wav";
								sentense3 = "character/zundamon/ずんだもん.png";
								sentense4 = "VOICEVOX:ずんだもん";
								int randomSecret = random.nextInt(2);
								if(randomSecret == 1) {
									sentense1 = "合格とありますが、それってあなたの感想ですよね。";
									sentense2 = "voice/zundamon/goukaku/hiroyuki音声.mp3";
									sentense3 = "character/zundamon/ひろゆき.png";
									sentense4 = "Voiced by https://CoeFont.cloud　おしゃべりひろゆきメーカー";
								}
							}else{
								sentense1 = "へへへへ。この調子で健康になるのだ。";
								sentense2 = "voice/zundamon/goukaku/goukaku2.wav";
								sentense3 = "character/zundamon/ずんだもん喜.png";
								sentense4 = "VOICEVOX:ずんだもん";
							}
						}else{
							int randomNum = random.nextInt(2);
							if(randomNum == 0){
								sentense1 = "おいおい、だめじゃねーか。ちゃんとえだまめくったか？";
								sentense2 = "voice/zundamon/fugoukaku/fugoukaku1.wav";
								sentense3 = "character/zundamon/zunmon_3002.png";
								sentense4 = "VOICEVOX:ずんだもん";
							}else {
								sentense1 = "はあ、この世は結果がすべてなのだ。この査定は僕の給料にも響くのだ。。。";
								sentense2 = "voice/zundamon/fugoukaku/fugoukaku2.wav";
								sentense3 = "character/zundamon/zunmon_3003.png";
								sentense4 = "VOICEVOX:ずんだもん";
							}
						}
					}else if(randomIndex == 1){
						if(result.equals("合格")){
							int randomNum = random.nextInt(2);
							if(randomNum == 0){
								sentense1 = "さっすが、やっぱり努力家ちゃんだね！。あーしも嬉しい！";
								sentense2 = "voice/tumugi/goukaku/goukaku1.wav";
								sentense3 = "character/tumugi/春日部つむぎSD.png";
								sentense4 = "VOICEVOX:春日部つむぎ";
							}else{
								sentense1 = "いいねいいねーー、じゃあー、ご褒美に埼玉で、一緒にごはん、食べよっ。";
								sentense2 = "voice/tumugi/goukaku/goukaku2.wav";
								sentense3 = "character/tumugi/春日部つむぎ１.png";
								sentense4 = "VOICEVOX:春日部つむぎ　立ち絵：坂本アヒル";
							}
						}else{
							int randomNum = random.nextInt(2);
							if(randomNum == 0){
								sentense1 = "え、ふーーーん。まじでいってんの。次は、結果、だしてね。";
								sentense2 = "voice/tumugi/fugoukaku/fugoukaku2.wav";
								sentense3 = "character/tumugi/春日部つむぎ４.png";
								sentense4 = "VOICEVOX:春日部つむぎ　立ち絵：坂本アヒル";
							}else {
								sentense1 = "はあー、がち、だるいんですけど。その空っぽの頭で反省しな。";
								sentense2 = "voice/tumugi/fugoukaku/fugoukaku1.wav";
								sentense3 = "character/tumugi/春日部つむぎ３.png";
								sentense4 = "VOICEVOX:春日部つむぎ　立ち絵：坂本アヒル";
							}
						}
					}else{
						if(result.equals("合格")){
							int randomNum = random.nextInt(2);
							if(randomNum == 0){
								sentense1 = "達成ーおめでとうっ。はい、花丸！";
								sentense2 = "voice/hanamaru/goukaku/goukaku1.wav";
								sentense3 = "character/hanamaru/manbetsu-hanamaru_mini_2.png";
								sentense4 = "VOICEVOX:満別花丸";
							}else{
								sentense1 = "うわあー、すごいね！やればできる子、君は満点！";
								sentense2 = "voice/hanamaru/goukaku/goukaku2.wav";
								sentense3 = "character/hanamaru/manbetsu-hanamaru_mini_3.png";
								sentense4 = "VOICEVOX:満別花丸";
							}
						}else{
							int randomNum = random.nextInt(2);
							if(randomNum == 0){
								sentense1 = "うーん、不合格だがら、君わーーー、ぜろてん！";
								sentense2 = "voice/hanamaru/fugoukaku/fugoukaku1.wav";
								sentense3 = "character/hanamaru/manbetsu-hanamaru_mini.png";
								sentense4 = "VOICEVOX:満別花丸";
							}else {
								sentense1 = "はあ！もう、いい加減なおしてね！";
								sentense2 = "voice/hanamaru/fugoukaku/fugoukaku2.wav";
								sentense3 = "character/hanamaru/花丸.png";
								sentense4 = "VOICEVOX:満別花丸";
							}
						}
					}
					break;
				}
				continue;
			}

			request.setAttribute("score",score);
			request.setAttribute("totalScore",totalScore);
			request.setAttribute("result",result);
			request.setAttribute("sentense1",sentense1);
			request.setAttribute("sentense2",sentense2);
			request.setAttribute("sentense3",sentense3);
			request.setAttribute("sentense4",sentense4);
			request.setAttribute("addPoint",addPoint);    //これらをjspで拾う
			// ボイスページにフォワードする
			RequestDispatcher dispatcher = request.getRequestDispatcher("/WEB-INF/jsp/result.jsp");
			dispatcher.forward(request, response);
		}
}
