package bean;

import java.util.List;

import javax.faces.application.FacesMessage;
import javax.faces.context.FacesContext;

import businessLogic.BLFacade;
import domain.Admin;
import domain.Blokeoa;
import domain.Pertsona;

public class ManageUsersBean {

	private BLFacade facade;
	private String userIzena;
	private String mezua;
	public ManageUsersBean() {
		facade = FacadeBean.getBusinessLogic();
		userIzena = null;
		mezua = "";
	}

	public String getUserIzena() {
		return userIzena;
	}

	public void setUserIzena(String userIzena) {
		this.userIzena = userIzena;
	}

	public String getMezua() {
		return mezua;
	}

	public void setMezua(String mezua) {
		this.mezua = mezua;
	}

	public List<Pertsona> getUsersList() {
		return facade.getUsers();
	}

	public String blokeatu(Admin nork) {
		Pertsona nori = facade.getPertsona(userIzena);
		Blokeoa b = null;
		if (nori.getBlokeoa() != null) {
			b = facade.desblokeatu(nori);
			return "true";
		} else {
			b = facade.blokeatu(nork, nori, mezua);
			if (b == null) {
				FacesContext.getCurrentInstance().addMessage(null,
						new FacesMessage("Ezin izan da pertsona hori blokeatu"));
				return "false";
			} else {
				return "true";
			}
		}
	}
}
