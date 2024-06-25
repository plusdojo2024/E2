package servlet;

import java.io.IOException;
import java.time.DayOfWeek;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.List;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import dao.HumansDAO;
import dao.LoginDAO;
import dao.MealDAO;
import dao.UsersDAO;
import model.Humans;
import model.Login;
import model.LoginUser;
import model.Meal;
import model.User;

/**
 * Servlet implementation class HomeServlet
 */
@WebServlet("/HomeServlet")
public class HomeServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;


	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

		HttpSession session = request.getSession();

		if (session.getAttribute("mail_address") == null) {
			response.sendRedirect("/E2/LoginServlet");
			return;
		}
//		String mailAddressTest = "aoki@gmail.com";

		LoginUser loginUser = (LoginUser) session.getAttribute("mail_address");
		String mailAddress = loginUser.getMailAddress();
		LocalDate currentDate = LocalDate.now();
		DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd");
		String formattedDate = currentDate.format(formatter);

		LoginDAO lDao = new LoginDAO();
		List<Login> loginList = lDao.select(new Login(0,mailAddress,formattedDate));
		Login login = null;
		String lastLoginDay = "null";
		LocalDate lastLoginDate = null;
		if (loginList != null) {
			if (loginList.size() != 0) {
				login = loginList.get(loginList.size()-1);
				lastLoginDay = login.getLoginDay();
				lastLoginDate = LocalDate.parse(lastLoginDay,formatter);
			}
		}

		//ポイント処理
		if(!lastLoginDay.equals(formattedDate)) {
			//ポイント増加させるUserを検索
			UsersDAO uDao = new UsersDAO();
			List<User> userList = uDao.selectMailAddress(new User(0,mailAddress,"password","day",0,0,0,0));
			if(userList != null) {
				if(userList.size() != 0) {
					User user = userList.get(0);
					user.setPoint(user.getPoint() + 1);
					if(uDao.updatePoint(user)) {
						request.setAttribute("login_message", "ログインボーナスとして1ポイント付与しました");
					}
				}
			}
			//最新ログイン日をLoginTableに追加
			lDao.insert(new Login(0,mailAddress,formattedDate));
		}

		//来週の月曜日になったら目標評価画面遷移

		//前回ログイン日の曜日を取得
		DayOfWeek todayOfWeek = null;
		LocalDate nextMonday = null;
		if(lastLoginDate != null) {
			todayOfWeek = lastLoginDate.getDayOfWeek();
			int countDay = DayOfWeek.MONDAY.getValue() - todayOfWeek.getValue();
			if (countDay <= 0) {
				countDay += 7; // 来週の月曜日までの日数を計算
			}
			nextMonday = lastLoginDate.plusDays(countDay);
		}
		if(nextMonday != null) {
			if(currentDate.isAfter(nextMonday)) {
				response.sendRedirect("/E2/ReviewServlet");
				return;
			}
		}

		//前回表示
        List<Humans> cardList = null;
        if(loginList != null) {
        	int i = loginList.size() - 1;
        	if(i > -1) {
        		do {
        			Login login1 = loginList.get(i);
        			String day = login1.getLoginDay();
        			HumansDAO hDao = new HumansDAO();
        			cardList = hDao.select(new Humans(0,mailAddress,day,0,0,0));
        			i--;
        		} while(cardList.size() == 0 && i > -1) ;
        	}
        }
		Humans humans = null;
		if(cardList != null) {
			if(cardList.size() != 0) {
				humans = cardList.get(cardList.size() - 1);
			}
		}

		// 検索結果をリクエストスコープに格納する
		request.setAttribute("humans", humans);  //cardListが空だったらjspでデータがないと表示
		request.setAttribute("formattedDate",formattedDate);




		// 登録ページにフォワードする
		RequestDispatcher dispatcher = request.getRequestDispatcher("/WEB-INF/jsp/home.jsp");
		dispatcher.forward(request, response);

	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

		HttpSession session = request.getSession();

		LoginUser loginUser = (LoginUser) session.getAttribute("mail_address");
		String mailAddress = loginUser.getMailAddress();

		LocalDate currentDate = LocalDate.now();
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd");
		String formattedDate = currentDate.format(formatter);


		request.setCharacterEncoding("UTF-8");
		//食事入力か身体かの判定
		String formType = request.getParameter("formType");

		//生体情報入力処理
		if("hform".equals(formType)) {
			String heights = request.getParameter("height");
			String weights = request.getParameter("weight");
			String sleep_times = request.getParameter("sleep_time");

			if(heights.equals("") || weights.equals("") || sleep_times.equals("")){
				//三つ入力していない場合
				request.setAttribute("HerrorMessage", "身長、体重、睡眠時間が入力されていません");
			}else {
				try {
					int height = Integer.parseInt(heights);
					int weight = Integer.parseInt(weights);
					int sleep_time = Integer.parseInt(sleep_times);
					HumansDAO hdao = new HumansDAO();
					if(hdao.insert(new Humans(0,mailAddress,formattedDate, height, weight, sleep_time))) {
						request.setAttribute("HerrorMessege", "データの登録に成功しました。");
					} else {
						request.setAttribute("HerrorMessege", "データの登録に失敗しました。");
					}
				}catch(NumberFormatException e){
					request.setAttribute("HerrorMessege","身長、体重、睡眠時間は数値で入力してください。");
				}
			}
		}


		//食事入力処理
		if("mform".equals(formType)) {
//			int mealNumber = 1;
			int index = 0;
			//項目を増やすcount
			String counts = request.getParameter("count");
			int count = Integer.parseInt(counts);
			MealDAO mdao = new MealDAO();
			UsersDAO udao = new UsersDAO();


			//３回入力の処理
			List<Meal> meals = mdao.select(new Meal(0, mailAddress, formattedDate, "", "", 0, 0));
			int size = meals.size();
			int number ;
			if(size != 0) {
				number = meals.get(size-1).getMealNumber()+1;
			}else {
				number = 1;
			}


			//３回入力された時のポイント付与
			if(number == 3) {
				List<User> userList = udao.selectMailAddress(new User(0,mailAddress,"password","day",0,0,0,0));
				User user = userList.get(0);
				user.setPoint(user.getPoint() + 5);
				if(udao.updatePoint(user)) {
					request.setAttribute("MerrorMessage", "食事を３回入力したので5ポイント付与しました");
				}
			}


		    while(index <= count) {
		    	String meal = request.getParameter("mealMenu_" + index);
		    	String mealBalance = request.getParameter("genre_" + index);
		    	String kcals1 = request.getParameter("kcal_"+ index);
		    	String kcals = (kcals1.equals("")) ? "0": kcals1;


		    	if(meal.equals("")||mealBalance.equals("")) {
		    		//食事とジャンルを入力していない場合
		    		request.setAttribute("MerrorMessage", "食事、ジャンルが入力されていません");

		    		break;
		    	}else {
		    		try {
		    			int kcal = Integer.parseInt(kcals);
		    			if(mdao.insert(new Meal(0,mailAddress,formattedDate,meal,mealBalance,kcal,number))) {
		    					request.setAttribute("MerrorMessage", "データの登録に成功しました。");
		    			} else {
		    				request.setAttribute("MerrorMessage", "データの登録に失敗しました。");
		    			}
		    		}catch(NumberFormatException e) {
		    			request.setAttribute("MerrorMessage", "カロリーは数値で入力してください。");
		    			//System.out.println("カロリーは数値で入力してください。");
		    		}
		    	}
		    	index++;
		    }
		}

    	//doGetページを呼び出し
		doGet(request, response);
	}
}
