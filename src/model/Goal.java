package model;
import java.io.Serializable;

public class Goal implements Serializable {

	private int id;
	private String mailAddress;
	private String day;
	private int sleepGoal;
	private int mealGoal;
	private int freeGoal;
	private String whiteFreeGoal;

	public Goal() {

	}

	public Goal(int id, String mailAddress, String day, int sleepGoal, int mealGoal, int freeGoal,
			String whiteFreeGoal) {
		super();
		this.id = id;
		this.mailAddress = mailAddress;
		this.day = day;
		this.sleepGoal = sleepGoal;
		this.mealGoal = mealGoal;
		this.freeGoal = freeGoal;
		this.whiteFreeGoal = whiteFreeGoal;
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

	public int getSleepGoal() {
		return sleepGoal;
	}

	public void setSleepGoal(int sleepGoal) {
		this.sleepGoal = sleepGoal;
	}

	public int getMealGoal() {
		return mealGoal;
	}

	public void setMealGoal(int mealGoal) {
		this.mealGoal = mealGoal;
	}

	public int getFreeGoal() {
		return freeGoal;
	}

	public void setFreeGoal(int freeGoal) {
		this.freeGoal = freeGoal;
	}

	public String getWhiteFreeGoal() {
		return whiteFreeGoal;
	}

	public void setWhiteFreeGoal(String whiteFreeGoal) {
		this.whiteFreeGoal = whiteFreeGoal;
	}

}