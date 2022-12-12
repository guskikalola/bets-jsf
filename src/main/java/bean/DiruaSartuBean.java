package bean;

import javax.faces.application.FacesMessage;
import javax.faces.context.FacesContext;

import businessLogic.BLFacade;
import domain.Erabiltzailea;
import domain.Pertsona;

public class DiruaSartuBean {
	private double amount;
	private BLFacade facade;

	public DiruaSartuBean() {
		amount = 0;
		facade = FacadeBean.getBusinessLogic();
	}
	
	public void diruaSartu(Pertsona p) {
		if(amount <= 0) {
			FacesContext.getCurrentInstance().addMessage(null,
					new FacesMessage("Inputed money amount is not correct. It must be > 0"));
		}
		else if(!(p instanceof Erabiltzailea)) {
			FacesContext.getCurrentInstance().addMessage(null,
					new FacesMessage("User session is incorrect. User is not 'erabiltzailea'"));
		} else {			
			try {				
				double money = facade.diruaSartu((Erabiltzailea)p,amount);
				FacesContext.getCurrentInstance().addMessage(null,
						new FacesMessage("Money added. Current amount of money: " + money));
				FacadeBean.getBusinessLogic().log(String.format("Erabiltzailea(%s) dirua(%f) sartu du.",
						p.getIzena(), amount));
			} catch (RuntimeException e) {
				FacesContext.getCurrentInstance().addMessage(null,
						new FacesMessage(e.getMessage()));
			}
		}
	}

	public double getAmount() {
		return amount;
	}

	public void setAmount(double amount) {
		this.amount = amount;
	}
}
