package model;

import java.io.Serializable;

public class Meal implements Serializable {
  private int id;
  private String mailAddress;
  private String day;
  private String meal;
  private String mealBalance;
  private int kcal;
  private int mealNumber;

  public Meal() {
	  //処理なし
  }

public Meal(int id, String mailAddress, String day, String meal, String mealBalance, int kcal, int mealNumber) {
	this.id = id;
	this.mailAddress = mailAddress;
	this.day = day;
	this.meal = meal;
	this.mealBalance = mealBalance;
	this.kcal = kcal;
	this.mealNumber = mealNumber;
}

public int getId() {
	return id;
}

public void setId(int id) {
	this.id = id;
}

public String getMailAddress() {
	return mailAddress;
}

public void setMailAddress(String mailAddress) {
	this.mailAddress = mailAddress;
}

public String getDay() {
	return day;
}

public void setDay(String day) {
	this.day = day;
}

public String getMeal() {
	return meal;
}

public void setMeal(String meal) {
	this.meal = meal;
}

public String getMealBalance() {
	return mealBalance;
}

public void setMealBalance(String mealBalance) {
	this.mealBalance = mealBalance;
}

public int getKcal() {
	return kcal;
}

public void setKcal(int kcal) {
	this.kcal = kcal;
}

public int getMealNumber() {
	return mealNumber;
}

public void setMealNumber(int mealNumber) {
	this.mealNumber = mealNumber;
}


}
