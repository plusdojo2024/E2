package servlet;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

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
import model.Meal;
import model.User;
import model.WeekDay;

@WebServlet("/ResultServlet")
public class ResultServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;

		protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
			// もしもログインしていなかったらログインサーブレットにリダイレクトする
			HttpSession session = request.getSession();

			if (session.getAttribute("mail_address") == null) {
				response.sendRedirect("/E2/LoginServlet");
				return;
			}

			Object obj = session.getAttribute("mail_address");
			String mailAddress = obj.toString();
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
			int random1 = 0;
			int random2 = 0;
			int random3 = 0;
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
				random1 = 1;
			}else {
				random1 = 0;
			}
			if(character2 == 3) {     //選択済み
				random2 = 1;
			}else {
				random2 = 0;
			}
			if(character3 == 3) {     //選択済み
				random3 = 1;
			}else {
				random3 = 0;
			}

			request.setAttribute("score",score);
			request.setAttribute("totalScore",totalScore);
			request.setAttribute("random1",random1);
			request.setAttribute("random2",random2);
			request.setAttribute("random3",random3);
			request.setAttribute("result",result);
			request.setAttribute("addPoint",addPoint);    //これらをjspで拾う
			// ボイスページにフォワードする
			RequestDispatcher dispatcher = request.getRequestDispatcher("/WEB-INF/jsp/result.jsp");
			dispatcher.forward(request, response);
		}
}
