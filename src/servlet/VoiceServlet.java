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

import dao.UsersDAO;
import model.LoginUser;
import model.User;


@WebServlet("/VoiceServlet")
public class VoiceServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;

		protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
			// もしもログインしていなかったらログインサーブレットにリダイレクトする
			HttpSession session = request.getSession();

			if (session.getAttribute("mail_address") == null) {
				response.sendRedirect("/E2/LoginServlet");
				return;
			}


			//ボイス購入ボタンの分岐
			LoginUser loginUser = (LoginUser) session.getAttribute("mail_address");
			String mailAddress = loginUser.getMailAddress();
			UsersDAO uDao = new UsersDAO();
			User user = new User(0,mailAddress,"","",0,0,0,0);
			List<User> userList = uDao.selectMailAddress(user);
			int point = 0;
			int character1 = 0;
			int character2 = 0;
			int character3 = 0;
			if(userList != null) {
				if(userList.size() !=0) {
					User user1 = userList.get(0);
					point = user1.getPoint();
					character1 = user1.getCharacter1();
					character2 = user1.getCharacter2();
					character3 = user1.getCharacter3();
				}
			}
			if(point < 20 && character1 == 1) {
				request.setAttribute("choice1","購入できません");
			}else if(point >= 20 && character1 == 1) {
				request.setAttribute("choice1","購入");
			}else if(character1 == 2) {
				request.setAttribute("choice1","選択");
			}else if(character1 == 3) {
				request.setAttribute("choice1","選択済み");
			}

			if(point < 20 && character2 == 1) {
				request.setAttribute("choice2","購入できません");
			}else if(point >= 20 && character2 == 1) {
				request.setAttribute("choice2","購入");
			}else if(character2 == 2) {
				request.setAttribute("choice2","選択");
			}else if(character2 == 3) {
				request.setAttribute("choice2","選択済み");
			}

			if(point < 20 && character3 == 1) {
				request.setAttribute("choice3","購入できません");
			}else if(point >= 20 && character3 == 1) {
				request.setAttribute("choice3","購入");
			}else if(character3 == 2) {
				request.setAttribute("choice3","選択");
			}else if(character3 == 3) {
				request.setAttribute("choice3","選択済み");
			}
			// ボイスページにフォワードする
			RequestDispatcher dispatcher = request.getRequestDispatcher("/WEB-INF/jsp/voice.jsp");
			dispatcher.forward(request, response);
		}
	protected void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {

		HttpSession session = request.getSession();
		if (session.getAttribute("mail_address") == null) {
			response.sendRedirect("/E2/LoginServlet");
			return;
		}

		// リクエストパラメータを取得する
		request.setCharacterEncoding("UTF-8");
		String choice1 = request.getParameter("choice1");
		String choice2 = request.getParameter("choice2");
		String choice3 = request.getParameter("choice3");
		Object obj = session.getAttribute("mail_address");
		String mailAddress = obj.toString();
		UsersDAO uDao = new UsersDAO();
		User user = new User(0,mailAddress,"","",0,0,0,0);
		List<User> userList = uDao.selectMailAddress(user);
		User user1 = userList.get(0);
		int point = user1.getPoint();
		int character1 = user1.getCharacter1();
		int character2 = user1.getCharacter2();
		int character3 = user1.getCharacter3();
		if (choice1 != null) {
                    // choice1 が選択された場合の処理
                	if(character1==1 && point<20){
                		character1 = 1;
                    }else if(character1==1) {
                    	point = point - 20;
                    	character1 = 2;

                    }else if(character1==2) {
                    	character1 = 3;

                    }else if(character1==3) {
                    	character1 = 2;
                    }
		}

		if (choice2 != null) {
            // choice1 が選択された場合の処理
        	if(character2==1 && point<20){
        		character2 = 1;
            }else if(character2==1) {
            	point = point - 20;
            	character2 = 2;

            }else if(character2==2) {
            	character2 = 3;

            }else if(character2==3) {
            	character2 = 2;
            }
}
		if (choice3 != null) {
            // choice1 が選択された場合の処理
        	if(character3==1 && point<20){
        		character3 = 1;
            }else if(character3==1) {
            	point = point - 20;
            	character3 = 2;

            }else if(character3==2) {
            	character3 = 3;

            }else if(character3==3) {
            	character3 = 2;
            }
}
                    // その他の場合の処理
		//購入押されたときは２(pointは-20)、選択押されたときは３（ほかの選択済みは２に戻る）、それ以外はそのままの数字


		User user2 = new User(0,mailAddress,"","",point,character1,character2,character3);
		uDao.updatePoint(user2);
		uDao.updateCharacter1(user2);
		uDao.updateCharacter2(user2);
		uDao.updateCharacter3(user2);

		String a = "";
		String b = "";
		String c = "";

		//ボタンの文字切り替え
		//character1
		if(character1==1 && point<20) {
			a = "購入できません";
		} else if(character1==1 && point>=20) {
			a = "購入";
		} else if(character1==2) {
			a = "選択";
		} else if(character1==3) {
			a = "選択済み";
		}

		//character2
		if(character2==1 && point<20) {
			b = "購入できません";
		} else if(character2==1 && point>=20) {
			b = "購入";
		} else if(character2==2) {
			b = "選択";
		} else if(character2==3) {
			b = "選択済み";
		}

		//character3
		if(character3==1 && point<20) {
			c = "購入できません";
		} else if(character3==1 && point>=20) {
			c = "購入";
		} else if(character3==2) {
			c = "選択";
		} else if(character3==3) {
			c = "選択済み";
		}

		request.setAttribute("choice1",a);
		request.setAttribute("choice2",b);
		request.setAttribute("choice3",c);
		RequestDispatcher dispatcher = request.getRequestDispatcher("/WEB-INF/jsp/voice.jsp");
		dispatcher.forward(request, response);
	}
}
