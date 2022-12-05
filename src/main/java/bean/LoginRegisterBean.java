package bean;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import javax.faces.application.FacesMessage;
import javax.faces.context.FacesContext;

import domain.Admin;
import domain.Erabiltzailea;
import domain.Pertsona;
import exceptions.AdinaEzNahikoaException;
import exceptions.PertsonaAlreadyExists;

public class LoginRegisterBean {

	public class Rola implements Serializable {
		public String izena;
		public Rola(String izena) {
			this.izena = izena;
		}
		
		public String getIzena() {
			return izena;
		}
		
	    @Override
	    public int hashCode() {
	        final int prime = 31;
	        int result = 1;
	        result = prime * result + ((izena == null) ? 0 : izena.hashCode());
	        return result;
	    }

	    @Override
	    public boolean equals(Object obj) {
	        if (this == obj) {
	            return true;
	        }
	        if (obj == null) {
	            return false;
	        }
	        if (getClass() != obj.getClass()) {
	            return false;
	        }
	        Rola other = (Rola) obj;
	        if (izena == null) {
	            return other.izena == null;
	        }
	        else {
	            return izena.equals(other.izena);
	        }
	    }
	}
	
	private String username;
	private String password;
	private String passwordRepeat;
	private Pertsona pertsona;
	private List<Rola> rolak;
	private Rola registerRola;

	public Rola getRegisterRola() {
		return registerRola;
	}

	public List<Rola> getRolak() {
		return rolak;
	}

	public void setRolak(List<Rola> rolak) {
		this.rolak = rolak;
	}

	public void setRegisterRola(Rola registerRola) {
		this.registerRola = registerRola;
	}

	public Pertsona getPertsona() {
		return pertsona != null ? FacadeBean.getBusinessLogic().getPertsona(pertsona.getIzena()) : null;
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
		registerRola = null;
		rolak = new ArrayList<Rola>();
		rolak.add(new Rola("admin"));
		rolak.add(new Rola("erabiltzailea"));
	}

	public String loginEgin() {

		pertsona = FacadeBean.getBusinessLogic().loginEgin(username, password);
		username = null;
		password = null;
		passwordRepeat = null;
		registerRola = null;
		
		if(pertsona == null) {
			FacesContext.getCurrentInstance().addMessage(null,
					new FacesMessage("User or password is incorrect"));
		}
		
		return pertsona != null ? "true" : "";
	}

	public String registerEgin() {
		if (username == null || password == null)
			return "false";
		
		String rola;
		if(registerRola != null) {
			rola = registerRola.izena;
		} else {
			rola = "erabiltzailea";
		}

		try {
			pertsona = FacadeBean.getBusinessLogic().registerEgin(username, password, birthDate,rola);
		} catch (AdinaEzNahikoaException e) {
			FacesContext.getCurrentInstance().addMessage(null,
					new FacesMessage(e.getMessage()));
		} catch (PertsonaAlreadyExists e) {
			FacesContext.getCurrentInstance().addMessage(null,
					new FacesMessage(e.getMessage()));
		} catch (RuntimeException e) {
			FacesContext.getCurrentInstance().addMessage(null,
					new FacesMessage(e.getMessage()));
		}
		username = null;
		password = null;
		passwordRepeat = null;
		registerRola = null;

		return pertsona != null ? "true" : "";
	}

	public String logoutEgin() {
		System.out.println("Saioa itxita");
		pertsona = null;
		return "logout";
	}

	public String getRola() {
		if(pertsona instanceof Erabiltzailea) return "erabiltzailea";
		else if (pertsona instanceof Admin) return "admin";
		else return "";
	}
	
	public boolean saioaHasita() {
		return pertsona != null;
	}
}
