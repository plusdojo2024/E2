package model;
import java.io.Serializable;

public class Human implements Serializable {
	//ここにデータベースのテーブルの中身を追加する
	//上から順番追加
	private int id;
	private String mailAddress;
	private String day;
	private int height;
	private int weight;
	private int sleepTimes;


	//引数がないコンストラクタ
	public Human() {

	}

	//引数があるコンストラクタ
    //ソースのフィールドを指定してコンストラクタを生成で行う
	public Human(int id, String mail_address, String day, int height, int weight, int sleep_times) {
		super();
		this.id = id;
		this.mailAddress = mail_address;
		this.day = day;
		this.height = height;
		this.weight = weight;
		this.sleepTimes = sleep_times;
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

	public int getHeight() {
		return height;
	}

	public void setHeight(int height) {
		this.height = height;
	}

	public int getWeight() {
		return weight;
	}

	public void setWeight(int weight) {
		this.weight = weight;
	}

	public int getSleepTimes() {
		return sleepTimes;
	}

	public void setSleepTimes(int sleepTimes) {
		this.sleepTimes = sleepTimes;
	}

}
