package servlet;

import java.io.IOException;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import dao.UsersDAO;
import model.Hash;
import model.LoginUser;
import model.User;

/**
 * Servlet implementation class RegistServlet
 */
@WebServlet("/RegistServlet")
public class RegistServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// 登録ページにフォワードする
		RequestDispatcher dispatcher = request.getRequestDispatcher("/WEB-INF/jsp/regist.jsp");
		dispatcher.forward(request, response);
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// 登録に必要なリクエストパラメータを取得する
				request.setCharacterEncoding("UTF-8");
				String mailAddress = request.getParameter("mail_address");
				String password = request.getParameter("password");
				String day = null;
				int point = 0;
				int character1 = 3;
				int character2 = 1;
				int character3 = 1;

				//hash化
				Hash hash = new Hash(password);

				if(hash.makeHash()) {
					password = hash.getHash();
				}

				// 登録処理を行う
				UsersDAO uDao = new UsersDAO();
				if (uDao.insert(new User(0, mailAddress, password, day, point, character1, character2, character3))) {
					// 登録成功

					// セッションスコープにパラメータを格納する
					HttpSession session = request.getSession();
					LoginUser loginUser = new LoginUser(mailAddress);

					session.setAttribute("login_user",loginUser);

					// ホームサーブレットにリダイレクトする
					//メールアドレス、パスワードを入力してください
					response.sendRedirect("/E2/HomeServlet");
				}
				else {		// 登録失敗（すでに登録されている）
					// リクエストスコープに登録失敗の結果、メッセージを格納する
					request.setAttribute("result", 0);
					request.setAttribute("error", "このメールアドレスとパスワードは既に登録されています。");


					// 登録ページにフォワードする
					RequestDispatcher dispatcher = request.getRequestDispatcher("/WEB-INF/jsp/regist.jsp");
					dispatcher.forward(request, response);
				}

		}
}