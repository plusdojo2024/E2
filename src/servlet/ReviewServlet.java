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
import dao.UsersDAO;
import model.Goal;
import model.LoginUser;
import model.User;

/**
 * Servlet implementation class ReviewServlet
 */
@WebServlet("/ReviewServlet")
public class ReviewServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;

    /**
     * @see HttpServlet#HttpServlet()
     */
    public ReviewServlet() {
        super();
        // TODO Auto-generated constructor stub
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
		LoginUser loginUser = (LoginUser) session.getAttribute("mail_address");
		String mailAddress = loginUser.getMailAddress();
		Goal goal = null;
		GoalsDAO gDao = new GoalsDAO();
		List<Goal> goalList = gDao.select(new Goal(0,mailAddress,"",0,0,0,""));
		if(goalList != null) {
			if(goalList.size() !=0) {
				goal = goalList.get(0);
			}
		}
		UsersDAO usersDAO = new UsersDAO();
		User user = new User();
		user.setMailAddress(mailAddress);
		List<User> userList = usersDAO.selectMailAddress(user);
		user = userList.get(0);



		List<String> charaList = new ArrayList<String>();
		if(user.getCharacter1() == 3) {
			charaList.add("character/zundamon/zunmon001.png");
		}
		if(user.getCharacter2() == 3) {
			charaList.add("character/hanamaru/manbetsu-hanamaru_mini.png");
		}
		if(user.getCharacter3() == 3) {
			charaList.add("character/tumugi/春日部つむぎSD.png");
		}

		request.setAttribute("goals", goal);
		request.setAttribute("users", user);
		request.setAttribute("charaList", charaList);

		//Review.jspにフォワード
		RequestDispatcher dispatcher = request.getRequestDispatcher("/WEB-INF/jsp/review.jsp");
		dispatcher.forward(request, response);
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		HttpSession session = request.getSession();

		String pointS = request.getParameter("point");
		int point;
		if(pointS != null && !pointS.equals("")) {
			point = Integer.parseInt(pointS);
		} else {
			point = 0;
		}

		session.setAttribute("point",point);
		response.sendRedirect("/E2/ResultServlet");
	}

}
