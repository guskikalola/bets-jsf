package bean;

import java.util.List;

import javax.faces.application.FacesMessage;
import javax.faces.context.FacesContext;

import businessLogic.BLFacade;
import domain.Admin;
import domain.Blokeoa;
import domain.Pertsona;
import exceptions.DagoenekoBlokeatutaDagoException;
import exceptions.ZureBuruaBlokeatuException;

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
		String gaizki = "false";
		String ondo = "true";
		if (nori.getBlokeoa() != null) {
			b = facade.desblokeatu(nori);
			if (b == null) {
				FacesContext.getCurrentInstance().addMessage(null,
						new FacesMessage("Ezin izan da pertsona hori desblokeatu"));
				return gaizki;
			}
			FacadeBean.getBusinessLogic().log(String.format("Admin(%s) Pertsona(%s) desblokeatu du.",
					nork.getIzena(), nori.getIzena(), b.getId()));
			return ondo;
		} else {
			try {
				b = facade.blokeatu(nork, nori, mezua);
				FacadeBean.getBusinessLogic().log(String.format("Admin(%s) Pertsona(%s) blokeatu(%d) du.",
						nork.getIzena(), nori.getIzena(), b.getId()));
				return ondo;
			} catch (ZureBuruaBlokeatuException e) {
				FacesContext.getCurrentInstance().addMessage(null, new FacesMessage(e.getMessage()));
				return gaizki;
			} catch (DagoenekoBlokeatutaDagoException e) {
				FacesContext.getCurrentInstance().addMessage(null, new FacesMessage(e.getMessage()));
				return gaizki;
			} catch (NullPointerException e) {
				FacesContext.getCurrentInstance().addMessage(null,
						new FacesMessage("Ezin izan da pertsona hori blokeatu"));
				return gaizki;
			}
		}
	}
}
