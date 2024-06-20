package dao;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import model.Humans;

public class HumansDAO {



	// 引数cardで指定されたレコードを登録し、成功したらtrueを返す
	public boolean insert(Humans card) {
		Connection conn = null;
		boolean result = false;

		try {
			// JDBCドライバを読み込む
			Class.forName("org.h2.Driver");

			// データベースに接続する
			conn = DriverManager.getConnection("jdbc:h2:file:C:/pleiades/workspace/data/LRD", "sa", "");


			// SQL文を準備する（AUTO_INCREMENTのNUMBER列にはNULLを指定する）
			String sql = "INSERT INTO Humans VALUES (NULL, ?, ?, ?, ?, ?)";
			PreparedStatement pStmt = conn.prepareStatement(sql);

			// SQL文を完成させる
			if (card.getMailAddress() != null && !card.getMailAddress().equals("")) {
				pStmt.setString(1, card.getMailAddress());
			}
			else {
				pStmt.setString(1, "（未設定）");
			}
			if (card.getDay() != null && !card.getDay().equals("")) {
				pStmt.setString(2, card.getDay());
			}
			else {
				pStmt.setString(2, "（未設定）");
			}
			int height = card.getHeight();
			String height1 = String.valueOf(height);
			if (height1.equals("") && height1!= null) {
				pStmt.setInt(3, 0);
			}
			else {
				pStmt.setInt(3, height);
			}
			//変えた部分--------------------------------------------------
			int weight = card.getWeight();
			String weight1 = String.valueOf(weight);
			if (weight1.equals("") && weight1!= null) {
				pStmt.setInt(4, 0);
			}
			else {
				pStmt.setInt(4, weight);
			}
			int sleep_time = card.getSleepTime();
			String sleep_time1 = String.valueOf(sleep_time);
			if (sleep_time1.equals("") && sleep_time1 != null) {
				pStmt.setInt(5, 0);
			}
			//ここまで-----------------------------------------------------
			else {
				pStmt.setInt(5, sleep_time);
			}

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

	public List<Humans> select(Humans card) {
		Connection conn = null;
		//cardlistのなまえ
		List<Humans> cardList = new ArrayList<Humans>();

		try {
			// JDBCドライバを読み込む
			Class.forName("org.h2.Driver");

			// データベースに接続する
			conn = DriverManager.getConnection("jdbc:h2:file:C:/pleiades/workspace/data/LRD", "sa", "");


			// SQL文を準備する
			//検索するもの　ここ改造必須
			String sql = "SELECT * FROM Humans WHERE mail_address = ? AND day = ? ORDER BY id";
			PreparedStatement pStmt = conn.prepareStatement(sql);



	        if (card.getMailAddress() != null && !card.getMailAddress().isEmpty()) {
	            pStmt.setString(1, card.getMailAddress());
	        } else {
	            pStmt.setString(1, "%");
	        }

	        if (card.getDay() != null && !card.getDay().isEmpty()) {
	            pStmt.setString(2, card.getDay());
	        } else {
	            pStmt.setString(2, "%");
	        }

			// SQL文を実行し、結果表を取得する
			ResultSet rs = pStmt.executeQuery();




			// 結果表をコレクションにコピーする
			while (rs.next()) {
				Humans record = new Humans(
				rs.getInt("id"),
				rs.getString("mail_address"),
				rs.getString("day"),
				rs.getInt("height"),
				rs.getInt("weight"),
				rs.getInt("sleep_time")
				);
				cardList.add(record);
			}
		}
		catch (SQLException e) {
			e.printStackTrace();
			cardList = null;
		}
		catch (ClassNotFoundException e) {
			e.printStackTrace();
			cardList = null;
		}
		finally {
			// データベースを切断
			if (conn != null) {
				try {
					conn.close();
				}
				catch (SQLException e) {
					e.printStackTrace();
					cardList = null;
				}
			}
		}

		// 結果を返す
		return cardList;
	}



	// 引数numberで指定されたレコードを削除し、成功したらtrueを返す
	public boolean delete(Humans humans) {
		Connection conn = null;
		boolean result = false;

		try {
			// JDBCドライバを読み込む
			Class.forName("org.h2.Driver");

			// データベースに接続する
			conn = DriverManager.getConnection("jdbc:h2:file:C:/pleiades/workspace/data/LRD", "sa", "");

			// SQL文を準備する
			String COUNTS="SELECT COUNT(*) AS sum FROM Humans WHERE mail_address=?";
			PreparedStatement pStmtCount = conn.prepareStatement(COUNTS);

			// SQL文を完成させる
			pStmtCount.setString(1, humans.getMailAddress());

			ResultSet rs1 = pStmtCount.executeQuery();
//			rs1.beforeFirst();
			rs1.next();
			int number = rs1.getInt("sum");



//			int number=Integer.parseInt(rs1.beforeFirst());
//			System.out.printf("COUNTS:%d\n", number);
//			int COUNT = Integer.parseInt(COUNTS);

			// SQL文を実行する
//			if (pStmtCount.executeUpdate() == number) {
//				result = true;
//			}
			if(number != 0) {
			String sql = "DELETE FROM Humans WHERE mail_address=?";
			PreparedStatement pStmt = conn.prepareStatement(sql);

//			System.out.println(result);



			// SQL文を完成させる
			pStmt.setString(1, humans.getMailAddress());

			if (pStmt.executeUpdate() == number) {
				result = true;
			}
		}else {
//			System.out.println("0件削除");
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