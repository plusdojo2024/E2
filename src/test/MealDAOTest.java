package test;
import java.util.List;

import dao.MealDAO;
import model.Meal;

public class MealDAOTest {
	public static void main(String[] args) {
		MealDAO dao = new MealDAO();
		// select()のテスト
		System.out.println("---------- select()のテスト ----------");
		//int id, String mailAddress, String day, String meal, String mealBalance, int kcal, int mealNumber
		List<Meal> cardList2 = dao.select(new Meal(0, "?", "", "", "", 1, 1));
		for (Meal meal : cardList2) {
			System.out.println(meal.getId());
			System.out.println(meal.getMailAddress());
			System.out.println(meal.getMeal());
			System.out.println(meal.getMealBalance());
			System.out.println(meal.getKcal());
			System.out.println(meal.getMealNumber());
			System.out.println();
		}

		// insert()のテスト
		Meal insRec = new Meal(0, "挿入", "挿入", "挿入", "挿入", 1, 1);
		System.out.println("---------- insert()のテスト ----------");
		if (dao.insert(insRec)) {
			System.out.println("登録成功！");
			List<Meal> cardList3 = dao.select(insRec);
			for (Meal meal : cardList3) {
				System.out.println(meal.getId());
				System.out.println(meal.getMailAddress());
				System.out.println(meal.getMeal());
				System.out.println(meal.getMealBalance());
				System.out.println(meal.getKcal());
				System.out.println(meal.getMealNumber());
				System.out.println();
			}
		}
		else {
			System.out.println("登録失敗！");
		}

		// delete()のテスト
		System.out.println("---------- delete()のテスト ----------");
		if (dao.delete(insRec)) {
			System.out.println("削除成功！");
		}
		else {
			System.out.println("削除失敗！");
		}
	}
}
