package model;
import java.io.Serializable;

public class Login implements Serializable {

  private int id;
  private String mailAddress;
  private String loginDay;

  //引数ないコンストラクタを定義
  public Login() {

  }

  public Login(int id, String mailAddress, String loginDay) {
    super();
    this.id = id;
    this.mailAddress = mailAddress;
    this.loginDay = loginDay;
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

public String getLoginDay() {
	return loginDay;
}

public void setLoginDay(String loginDay) {
	this.loginDay = loginDay;
}

}
