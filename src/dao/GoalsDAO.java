package dao;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

import model.Goal;

public class GoalsDAO {
	public List<Goal> select(Goal card) {
		Connection conn = null;
		List<Goal> cardList = new ArrayList<Goal>();

		try {
			// JDBCドライバを読み込む
			Class.forName("org.h2.Driver");

			// データベースに接続する
			conn = DriverManager.getConnection("jdbc:h2:file:C:/pleiades/workspace/data/LRD", "sa", "");

			// SQL文を準備する
			String sql = "SELECT * FROM goals WHERE mail_address=?";
			PreparedStatement pStmt = conn.prepareStatement(sql);
			// SQL文を完成させる

			if (card.getMailAddress() != null) {
				pStmt.setString(1, card.getMailAddress());
			}
			else {
				pStmt.setString(1, "%");
			}

			// SQL文を実行し、結果表を取得する
			ResultSet rs = pStmt.executeQuery();

			// 結果表をコレクションにコピーする
			while (rs.next()) {
				Goal record = new Goal(rs.getInt("id"),rs.getString("mail_address"),rs.getString("day"),rs.getInt("sleep_goal"),rs.getInt("meal_goal"),rs.getInt("free_goal"),rs.getString("write_free_goal"));
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
	// 引数cardで指定されたレコードを更新し、成功したらtrueを返す
	public boolean update(Goal card) {
		Connection conn = null;
		boolean result = false;

		try {
			// JDBCドライバを読み込む
			Class.forName("org.h2.Driver");

			// データベースに接続する
			conn = DriverManager.getConnection("jdbc:h2:file:C:/pleiades/workspace/data/LRD", "sa", "");

			// SQL文を準備する
			String sql = "UPDATE goals SET sleep_goal=?, meal_goal=?, free_goal=?, write_free_goal=? WHERE mail_address=?";
			PreparedStatement pStmt = conn.prepareStatement(sql);

			// SQL文を完成させる
			if (Objects.equals(card.getSleepGoal(), null)) {
				pStmt.setInt(1, 0);
			}
			else {
				pStmt.setInt(1, card.getSleepGoal());
			}
			if (Objects.equals(card.getMealGoal(), null)) {
				pStmt.setInt(2, 0);
			}
			else {
				pStmt.setInt(2, card.getMealGoal());
			}
			if (Objects.equals(card.getFreeGoal(), null)) {
				pStmt.setInt(3, 0);
			}
			else {
				pStmt.setInt(3, card.getFreeGoal());
			}
			if (card.getWhiteFreeGoal() != null && !card.getWhiteFreeGoal().equals("")) {
				pStmt.setString(4, card.getWhiteFreeGoal());
			}
			else {
				pStmt.setString(4, null);
			}
			pStmt.setString(5, card.getMailAddress());

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
	public boolean insert(Goal card) {
		Connection conn = null;
		boolean result = false;

		try {
			// JDBCドライバを読み込む
			Class.forName("org.h2.Driver");

			// データベースに接続する
			conn = DriverManager.getConnection("jdbc:h2:file:C:/pleiades/workspace/data/LRD", "sa", "");

			// SQL文を準備する（AUTO_INCREMENTのNUMBER列にはNULLを指定する）
			String sql = "INSERT INTO goals VALUES (NULL, ?, ?, ?, ?, ?, ?)";
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
			if (Objects.equals(card.getSleepGoal(), null)) {
				pStmt.setInt(3, 0);
			}
			else {
				pStmt.setInt(3, card.getSleepGoal());
			}
			if (Objects.equals(card.getMealGoal(), null)) {
				pStmt.setInt(4, 0);
			}
			else {
				pStmt.setInt(4, card.getMealGoal());
			}
			if (Objects.equals(card.getFreeGoal(), null)) {
				pStmt.setInt(5, 0);
			}
			else {
				pStmt.setInt(5, card.getFreeGoal());
			}
			if (card.getWhiteFreeGoal() != null && !card.getWhiteFreeGoal().equals("")) {
				pStmt.setString(6, card.getWhiteFreeGoal());
			}
			else {
				pStmt.setString(6, "（未設定）");
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

}
