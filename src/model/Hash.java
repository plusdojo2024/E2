package model;

import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.util.Base64;

public class Hash {
	private String password;
	private String hash;
	public Hash(String password) { //コンストラクタ
		this.password = password;
		this.hash = null;
	}
	public String getPassword() {
		return password;
	}
	public void setPassword(String password) {
		this.password = password;
	}
	public String getHash() {
		return hash;
	}
	public void setHash(String hash) {
		this.hash = hash;
	}

	public boolean makeHash() {
		boolean result = false;
		try {
			MessageDigest md = MessageDigest.getInstance("MD5");
			md.update(password.getBytes());
			byte[] hashBytes = md.digest();
			String hash = Base64.getEncoder().encodeToString(hashBytes);
			this.hash = hash;
			result = true;
		} catch (NoSuchAlgorithmException e) {
			e.printStackTrace();
		}
		return result;
	}
}
