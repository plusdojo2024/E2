package servlet;

import java.io.IOException;
import java.util.List;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import dao.GoalsDAO;
import model.Goal;
import model.LoginUser;

@WebServlet("/SetServlet")
public class SetServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;

	protected void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		HttpSession session = request.getSession();

		if (session.getAttribute("mail_address") == null) {
			response.sendRedirect("/E2/LoginServlet");
			return;
		}
		LoginUser loginUser = (LoginUser) session.getAttribute("mail_address");
		String mailAddress = loginUser.getMailAddress();
		GoalsDAO gDao = new GoalsDAO();
		List<Goal> cardList = gDao.select(new Goal(0,mailAddress,"",0,0,0,""));

		// 検索結果をリクエストスコープに格納する
		request.setAttribute("cardList", cardList);  //cardListが空だったらjspでデータがないと表示

		// ページにフォワードする
		RequestDispatcher dispatcher = request.getRequestDispatcher("/WEB-INF/jsp/set.jsp");
		dispatcher.forward(request, response);

	}
	protected void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {

		HttpSession session = request.getSession();
		if (session.getAttribute("mail_address") == null) {
			response.sendRedirect("/E2/LoginServlet");
			return;
		}
		Object obj = session.getAttribute("mail_address");
		String mailAddress = obj.toString();
		// リクエストパラメータを取得する
		request.setCharacterEncoding("UTF-8");
		String parameter1 = request.getParameter("sleep_goal");
		if(parameter1 == null) {
			parameter1 = "1";
		}else {
			parameter1 = "2";
		}
		int sleepGoal = Integer.parseInt(parameter1);
		String parameter2 = request.getParameter("meal_goal");
		if(parameter2 == null) {
			parameter2 = "1";
		}else {
			parameter2 = "2";
		}
		int mealGoal = Integer.parseInt(parameter2);
		String parameter3 = request.getParameter("free_goal");
		if(parameter3 == null) {
			parameter3 = "1";
		}else {
			parameter3 = "2";
		}
		int freeGoal = Integer.parseInt(parameter3);
		String writeFreeGoal = request.getParameter("write_free_goal");

		GoalsDAO gDao = new GoalsDAO();
		List<Goal> cardList = gDao.select(new Goal(0,mailAddress,"",0,0,0,""));

		if(cardList.size() == 0) {
		    //登録処理を行う
			GoalsDAO insertDao = new GoalsDAO();
			if (insertDao.insert(new Goal(0, mailAddress, "", sleepGoal, mealGoal, freeGoal, writeFreeGoal))) { // 登録成功
				request.setAttribute("result",0);  //jsp側で0だった場合登録できましたと表示
			} else { // 登録失敗
				request.setAttribute("result",1);  //ｊｓｐ側で１だった場合登録できませんでしたと表示
			}
		}else {
			//更新処理を行う
			GoalsDAO updateDao = new GoalsDAO();
			if (updateDao.update(new Goal(0, mailAddress, "", sleepGoal, mealGoal, freeGoal, writeFreeGoal))) { // 更新成功
				request.setAttribute("result", 2);
			} else { // 更新失敗
				request.setAttribute("result", 3);
			}
		}

		RequestDispatcher dispatcher = request.getRequestDispatcher("/WEB-INF/jsp/set.jsp");
		dispatcher.forward(request, response);
	}
}
