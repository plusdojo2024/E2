package model;

import java.io.Serializable;

public class LoginUser implements Serializable {
	private String mailAddress;

	public LoginUser() {
	//中身無し
	}

	public LoginUser(String mailAddress) {

		this.mailAddress = mailAddress;
	}

	public String getMailAddress() {
		return mailAddress;
	}

	public void setMailAddress(String mailAddress) {
		this.mailAddress = mailAddress;
	}


}
