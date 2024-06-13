package dao;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

import model.Meal;

public class MealDAO {
	//登録用メソッド
	public boolean insert(Meal meal) {
		Connection conn = null;
		boolean result = false;

		try {
			// JDBCドライバを読み込む
			Class.forName("org.h2.Driver");

			// データベースに接続する
			conn = DriverManager.getConnection("jdbc:h2:file:C:/pleiades/workspace/data/LRD", "sa", "");

			// SQL文を準備する（AUTO_INCREMENTのNUMBER列にはNULLを指定する）
			String sql = "INSERT INTO meals (id, mail_address, day, meal, meal_balance, kcal, meal_number) VALUES (null, ?, ?, ?, ?, ?, ?);";
			PreparedStatement pStmt = conn.prepareStatement(sql);

			// SQL文を完成させる
			pStmt.setString(1, meal.getMailAddress());
			pStmt.setString(2, meal.getDay());
			pStmt.setString(3, meal.getMeal());
			pStmt.setString(4, meal.getMealBalance());
			if (Objects.equals(meal.getKcal(), null)) {
				pStmt.setString(5, "NULL");
			}
			else {
				pStmt.setInt(5, meal.getKcal());
			}
			pStmt.setInt(6, meal.getMealNumber());
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

	//検索用メソッド
	public List<Meal> select(Meal meal) {
		Connection conn = null;
		List<Meal> cardList = new ArrayList<Meal>();

		try {
			// JDBCドライバを読み込む
			Class.forName("org.h2.Driver");

			// データベースに接続する
			conn = DriverManager.getConnection("jdbc:h2:file:C:/pleiades/workspace/data/LRD", "sa", "");

			// SQL文を準備する
			String sql = "SELECT * FROM Meals WHERE mail_address = ?";
			PreparedStatement pStmt = conn.prepareStatement(sql);
			// SQL文を完成させる
			pStmt.setString(1, meal.getMailAddress());

			// SQL文を実行し、結果表を取得する
			ResultSet rs = pStmt.executeQuery();

			// 結果表をコレクションにコピーする
			while (rs.next()) {
				Meal record = new Meal(
				rs.getInt("id"),
				rs.getString("mail_address"),
				rs.getString("day"),
				rs.getString("meal"),
				rs.getString("meal_balance"),
				rs.getInt("kcal"),
				rs.getInt("meal_number")

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

	public boolean delete(Meal meal) {
		Connection conn = null;
		boolean result = false;

		try {
			// JDBCドライバを読み込む
			Class.forName("org.h2.Driver");

			// データベースに接続する
			conn = DriverManager.getConnection("jdbc:h2:file:C:/pleiades/workspace/data/LRD", "sa", "");
			// SQL文を準備する
			String sqlSelect = "SELECT * FROM Meals WHERE mail_address = ?";
			PreparedStatement pStmtSelect = conn.prepareStatement(sqlSelect);
			// SQL文を完成させる
			pStmtSelect.setString(1, meal.getMailAddress());
			ResultSet rsSelect = pStmtSelect.executeQuery();

			int number = 0;
			while (rsSelect.next()) {
				number++;
			}

			// SQL文を準備する
			String sql = "DELETE FROM Meals WHERE mail_address = ?";

			PreparedStatement pStmt = conn.prepareStatement(sql);

			// SQL文を完成させる
			pStmt.setString(1, meal.getMailAddress());
			// SQL文を実行する
			if (pStmt.executeUpdate() == number) {
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
