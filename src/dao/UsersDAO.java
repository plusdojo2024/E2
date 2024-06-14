package dao;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import model.User;



public class UsersDAO {

	//ログインで使うselect文

	public boolean selectLogin(User users) {
		Connection conn = null;
		boolean loginResult = false;

		try {
			// JDBCドライバを読み込む
			Class.forName("org.h2.Driver");

			// データベースに接続する
			conn = DriverManager.getConnection("jdbc:h2:file:C:/pleiades/workspace/data/LRD", "sa", "");

			// SELECT文を準備する
			String sql = "SELECT COUNT(*) FROM users WHERE mail_address = ? AND password = ?";//ユーザーがいれる場所を？にしておく
			PreparedStatement pStmt = conn.prepareStatement(sql);
			pStmt.setString(1, users.getMailAddress());
			pStmt.setString(2,users.getPassword());

			// SELECT文を実行し、結果表を取得する
			ResultSet rs = pStmt.executeQuery();

			// メールアドレスとパスワードが一致するユーザーがいたかどうかをチェックする
			rs.next();
			if (rs.getInt("COUNT(*)") == 1) {
				loginResult = true;
			}
		}
		catch (SQLException e) {
			e.printStackTrace();
			loginResult = false;
		}
		catch (ClassNotFoundException e) {
			e.printStackTrace();
			loginResult = false;
		}
		finally {
			// データベースを切断
			if (conn != null) {
				try {
					conn.close();
				}
				catch (SQLException e) {
					e.printStackTrace();
					loginResult = false;
				}
			}
		}

		// 結果を返す
		return loginResult;
	}





	//ユーザーの情報を調べるときのselect文
		public List<User> selectMailAddress(User users) {
			Connection conn = null;
			List<User> userList = new ArrayList<User>();

			try {
				// JDBCドライバを読み込む
				Class.forName("org.h2.Driver");

				// データベースに接続する
				conn = DriverManager.getConnection("jdbc:h2:file:C:/pleiades/workspace/data/LRD", "sa", "");

				// SQL文を準備する
				String sql = "SELECT * FROM users WHERE mail_address =  ? ORDER BY id";
				PreparedStatement pStmt = conn.prepareStatement(sql);
				// SQL文を完成させる
				pStmt.setString(1, users.getMailAddress() );


				// SQL文を実行し、結果表を取得する
				ResultSet rs = pStmt.executeQuery();

				// 結果表をコレクションにコピーする
				while (rs.next()) {
					User record = new User(
					rs.getInt("id"),
					rs.getString("mail_address"),
					rs.getString("password"),
					rs.getString("day"),
					rs.getInt("point"),
					rs.getInt("character1"),
					rs.getInt("character2"),
					rs.getInt("character3")
					);
					userList.add(record);
				}
			}
			catch (SQLException e) {
				e.printStackTrace();
				userList = null;
			}
			catch (ClassNotFoundException e) {
				e.printStackTrace();
				userList = null;
			}
			finally {
				// データベースを切断
				if (conn != null) {
					try {
						conn.close();
					}
					catch (SQLException e) {
						e.printStackTrace();
						userList = null;
					}
				}
			}

			// 結果を返す
			return userList;
		}





		//ユーザーの追加を行うINSERT文

		// 引数usersで指定されたレコードを登録し、成功したらtrueを返す
		public boolean insert(User users) {
			Connection conn = null;
			boolean result = false;

			try {
				// JDBCドライバを読み込む
				Class.forName("org.h2.Driver");

				// データベースに接続する
				conn = DriverManager.getConnection("jdbc:h2:file:C:/pleiades/workspace/data/LRD", "sa", "");

				// SQL文を準備する（AUTO_INCREMENTのNUMBER列にはNULLを指定する）
				String sql = "INSERT INTO users (id, mail_address, password, day, point, character1, character2, character3) VALUES (null, ?, ?, ?, ?, ?, ?, ?)";
				PreparedStatement pStmt = conn.prepareStatement(sql);

				// SQL文を完成させる
				pStmt.setString(1, users.getMailAddress());
				pStmt.setString(2, users.getPassword());
				pStmt.setString(3, users.getDay());
				pStmt.setInt(4, users.getPoint());
				pStmt.setInt(5, users.getCharacter1());
				pStmt.setInt(6, users.getCharacter2());
				pStmt.setInt(7, users.getCharacter3());

				// SQL文を実行する
				if (pStmt.executeUpdate() == 1) {
					result = true;
				}
			}
			catch (SQLException e) {
				e.printStackTrace();
			}
			catch (ClassNotFoundException e) {
				e.printStackTrace();
			}
			finally {
				// データベースを切断
				if (conn != null) {
					try {
						conn.close();
					}
					catch (SQLException e) {
						e.printStackTrace();
					}
				}
			}

			// 結果を返す
			return result;
		}





		//更新を行うUPDATE文

		//ポイント更新のUPDATE文

		// 引数usersで指定されたレコードを更新し、成功したらtrueを返す
		public boolean updatePoint(User users) {
			Connection conn = null;
			boolean result = false;

			try {
				// JDBCドライバを読み込む
				Class.forName("org.h2.Driver");

				// データベースに接続する
				conn = DriverManager.getConnection("jdbc:h2:file:C:/pleiades/workspace/data/LRD", "sa", "");

				// SQL文を準備する
				String sql = "UPDATE users SET point= ? WHERE mail_address= ?";
				PreparedStatement pStmt = conn.prepareStatement(sql);

				// SQL文を完成させる

				pStmt.setInt(1, users.getPoint());
				pStmt.setString(2, users.getMailAddress());

				// SQL文を実行する
				if (pStmt.executeUpdate() == 1) {
					result = true;
				}
			}
			catch (SQLException e) {
				e.printStackTrace();
			}
			catch (ClassNotFoundException e) {
				e.printStackTrace();
			}
			finally {
				// データベースを切断
				if (conn != null) {
					try {
						conn.close();
					}
					catch (SQLException e) {
						e.printStackTrace();
					}
				}
			}

			// 結果を返す
			return result;
		}





		//ずんだもん更新のUPDATE文

		// 引数usersで指定されたレコードを更新し、成功したらtrueを返す
		public boolean updateCharacter1(User users) {
			Connection conn = null;
			boolean result = false;

			try {
				// JDBCドライバを読み込む
				Class.forName("org.h2.Driver");

				// データベースに接続する
				conn = DriverManager.getConnection("jdbc:h2:file:C:/pleiades/workspace/data/LRD", "sa", "");

				// SQL文を準備する
				String sql = "UPDATE users SET character1= ? WHERE mail_address= ? ";
				PreparedStatement pStmt = conn.prepareStatement(sql);

				// SQL文を完成させる

				pStmt.setInt(1, users.getCharacter1());
				pStmt.setString(2, users.getMailAddress());

				// SQL文を実行する
				if (pStmt.executeUpdate() == 1) {
					result = true;
				}
			}
			catch (SQLException e) {
				e.printStackTrace();
			}
			catch (ClassNotFoundException e) {
				e.printStackTrace();
			}
			finally {
				// データベースを切断
				if (conn != null) {
					try {
						conn.close();
					}
					catch (SQLException e) {
						e.printStackTrace();
					}
				}
			}

			// 結果を返す
			return result;
		}





		//満別花丸更新のUPDATE文

				// 引数usersで指定されたレコードを更新し、成功したらtrueを返す
				public boolean updateCharacter2(User users) {
					Connection conn = null;
					boolean result = false;

					try {
						// JDBCドライバを読み込む
						Class.forName("org.h2.Driver");

						// データベースに接続する
						conn = DriverManager.getConnection("jdbc:h2:file:C:/pleiades/workspace/data/LRD", "sa", "");

						// SQL文を準備する
						String sql = "UPDATE users SET character2= ? WHERE mail_address= ? ";
						PreparedStatement pStmt = conn.prepareStatement(sql);

						// SQL文を完成させる

						pStmt.setInt(1, users.getCharacter2());
						pStmt.setString(2, users.getMailAddress());

						// SQL文を実行する
						if (pStmt.executeUpdate() == 1) {
							result = true;
						}
					}
					catch (SQLException e) {
						e.printStackTrace();
					}
					catch (ClassNotFoundException e) {
						e.printStackTrace();
					}
					finally {
						// データベースを切断
						if (conn != null) {
							try {
								conn.close();
							}
							catch (SQLException e) {
								e.printStackTrace();
							}
						}
					}

					// 結果を返す
					return result;
				}





		//春日部つむぎ更新のUPDATE文

				// 引数usersで指定されたレコードを更新し、成功したらtrueを返す
				public boolean updateCharacter3(User users) {
					Connection conn = null;
					boolean result = false;

					try {
						// JDBCドライバを読み込む
						Class.forName("org.h2.Driver");

						// データベースに接続する
						conn = DriverManager.getConnection("jdbc:h2:file:C:/pleiades/workspace/data/LRD", "sa", "");

						// SQL文を準備する
						String sql = "UPDATE users SET character3= ? WHERE mail_address= ?";
						PreparedStatement pStmt = conn.prepareStatement(sql);

						// SQL文を完成させる

						pStmt.setInt(1, users.getCharacter3());
						pStmt.setString(2, users.getMailAddress());

						// SQL文を実行する
						if (pStmt.executeUpdate() == 1) {
							result = true;
						}
					}
					catch (SQLException e) {
						e.printStackTrace();
					}
					catch (ClassNotFoundException e) {
						e.printStackTrace();
					}
					finally {
						// データベースを切断
						if (conn != null) {
							try {
								conn.close();
							}
							catch (SQLException e) {
								e.printStackTrace();
							}
						}
					}

					// 結果を返す
					return result;
				}
}