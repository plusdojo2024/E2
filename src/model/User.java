//Java Beansのクラス
package model;
import java.io.Serializable;


public class User implements  Serializable{

	private int id;//ID
	private String mailAddress;//メールアドレス
	private String password;//パスワード
	private String day;//日付
	private int point;//ポイント
	private int character1;//ずんだもん
	private int character2;//満別花丸
	private int character3;//春日部つむぎ

	//引数なしコンストラクタ
	public User(){

	}

	//引数ありコンストラクタ
	public User(int id, String mailAddress, String password, String day, int point,
			int character1,int character2, int character3) {
		super();
		this.id = id;
		this.mailAddress = mailAddress;
		this.password = password;
		this.day = day;
		this.point = point;
		this.character1 = character1;
		this.character2 = character2;
		this.character3 = character3;
	}

	//フィールドに対応したsetter/getter
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

	public String getPassword() {
		return password;
	}

	public void setPassword(String password) {
		this.password = password;
	}

	public String getDay() {
		return day;
	}

	public void setDay(String day) {
		this.day = day;
	}

	public int getPoint() {
		return point;
	}

	public void setPoint(int point) {
		this.point = point;
	}

	public int getCharacter1() {
		return character1;
	}

	public void setCharacter1(int character1) {
		this.character1 = character1;
	}

	public int getCharacter2() {
		return character2;
	}

	public void setCharacter2(int character2) {
		this.character2 = character2;
	}

	public int getCharacter3() {
		return character3;
	}

	public void setCharacter3(int character3) {
		this.character3 = character3;
	}

}
