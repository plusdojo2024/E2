package model;
import java.io.Serializable;

public class Humans implements Serializable {
	//ここにデータベースのテーブルの中身を追加する
	//上から順番追加
	private int id;
	private String mailAddress;
	private String day;
	private int height;
	private int weight;
	private int sleepTime;


	//引数がないコンストラクタ
	public Humans() {

	}

	//引数があるコンストラクタ
    //ソースのフィールドを指定してコンストラクタを生成で行う
	public Humans(int id, String mail_address, String day, int height, int weight, int sleep_time) {
		super();
		this.id = id;
		this.mailAddress = mail_address;
		this.day = day;
		this.height = height;
		this.weight = weight;
		this.sleepTime = sleep_time;
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

	public int getSleepTime() {
		return sleepTime;
	}

	public void setSleepTime(int sleepTime) {
		this.sleepTime = sleepTime;
	}

}
