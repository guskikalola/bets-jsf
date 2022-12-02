package bean;

import java.util.Date;

import domain.Pertsona;

public class LoginRegisterBean {

	private String username;
	private String password;
	private String passwordRepeat;
	private Pertsona pertsona;

	public Pertsona getPertsona() {
		return pertsona;
	}

	public String getPasswordRepeat() {
		return passwordRepeat;
	}

	public void setPasswordRepeat(String passwordRepeat) {
		this.passwordRepeat = passwordRepeat;
	}

	private Date birthDate;

	public Date getBirthDate() {
		return birthDate;
	}

	public void setBirthDate(Date birthDate) {
		this.birthDate = birthDate;
	}

	public String getUsername() {
		return username;
	}

	public void setUsername(String username) {
		this.username = username;
	}

	public String getPassword() {
		return password;
	}

	public void setPassword(String password) {
		this.password = password;
	}

	public LoginRegisterBean() {
		username = null;
		password = null;
		passwordRepeat = null;
		pertsona = null;
	}

	public String loginEgin() {
		if (username == null || password == null)
			return "false";

		pertsona = FacadeBean.getBusinessLogic().loginEgin(username, password);
		username = null;
		password = null;
		passwordRepeat = null;
		return pertsona != null ? "true" : "false";
	}

	public String registerEgin() {
		if (username == null || password == null)
			return "false";

		pertsona = FacadeBean.getBusinessLogic().registerEgin(username, password, birthDate);
		username = null;
		password = null;
		passwordRepeat = null;

		return pertsona != null ? "true" : "false";
	}

	public String logoutEgin() {
		System.out.println("Saioa itxita");
		pertsona = null;
		return "logout";
	}

	public boolean saioaHasita() {
		return pertsona != null;
	}
}
