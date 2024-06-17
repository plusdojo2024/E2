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

import dao.HumansDAO;
import dao.MealDAO;
import model.Humans;
import model.LoginUser;
import model.Meal;
import model.Method;

/**
 * Servlet implementation class GraphServlet
 */
@WebServlet("/GraphServlet")
public class GraphServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;

    /**
     * @see HttpServlet#HttpServlet()
     */
    public GraphServlet() {
        super();
        // TODO Auto-generated constructor stub
        // test用　
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */

	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub

		// もしもログインしていなかったらログインサーブレットにリダイレクトする
		HttpSession session = request.getSession();

		if (session.getAttribute("mail_address") == null) {
			response.sendRedirect("/E2/LoginServlet");
			return;
		}
		//一週間の日付を所得
		ArrayList<String> day = Method.getDatesFromMonday();

		//ログインユーザー情報を取得
		LoginUser loginUser = (LoginUser) session.getAttribute("mail_address");

		//一週間の身体データ取得
		ArrayList<Humans> humansList = new ArrayList<Humans>();
		HumansDAO humansDAO = new HumansDAO();
		for (int x = 0; x < day.size(); x++) {
			Humans humans = new Humans();
			humans.setMailAddress(loginUser.getMailAddress());
			humans.setDay(day.get(x));
			List<Humans> selectList = humansDAO.select(humans);
			for (int y = 0; y < selectList.size(); y++) {
				humansList.add(selectList.get(y));
			}
		}
		request.setAttribute("humans", humansList);

		//一週間の食事データ取得
		ArrayList<Meal> mealsList = new ArrayList<Meal>();
		MealDAO mealDAO = new MealDAO();
		for (int x = 0; x <day.size(); x++) {
			Meal meal = new Meal();
			meal.setMailAddress(loginUser.getMailAddress());
			List<Meal> selectList = mealDAO.select(meal);
			for (int y = 0; y < selectList.size(); y++) {
				mealsList.add(selectList.get(y));
			}
		}
		request.setAttribute("meals", mealsList);

		//Graph.jspにフォワード
		RequestDispatcher dispatcher = request.getRequestDispatcher("/WEB-INF/jsp/graph.jsp");
		dispatcher.forward(request, response);
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */

}
