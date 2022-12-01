package bean;

import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.List;
import java.util.Vector;

import javax.faces.application.FacesMessage;
import javax.faces.context.FacesContext;

import org.primefaces.event.SelectEvent;
import org.primefaces.json.JSONArray;

import businessLogic.BLFacade;
import domain.Event;
import domain.Question;

public class QueryQuestionBean {
	private BLFacade facade;
	
	private List<Date> theDates;
	private Event gertaera;
	private List<Event> gertaerak;
	private List<Question> galderak;
	
	
	public BLFacade getFacade() {
		return facade;
	}

	public void setFacade(BLFacade facade) {
		this.facade = facade;
	}

	public void test(String test) {
		System.out.println(test);
	}
	
	public void setTheDates(List<Date> theDates) {
		this.theDates = theDates;
	}

	public void setGertaera(Event gertaera) {
		this.gertaera = gertaera;
	}

	public void setGertaerak(List<Event> gertaerak) {
		this.gertaerak = gertaerak;
	}

	public void setGalderak(List<Question> galderak) {
		this.galderak = galderak;
	}

	public List<Event> getGertaerak() {
		return gertaerak;
	}

	public List<Question> getGalderak() {
		return galderak;
	}

	
	public Event getGertaera() {
		return gertaera;
	}
	
	public void onEventSelect(SelectEvent event) {
		Event ev = (Event)event.getObject();
		galderak = ev.getQuestions();
		System.out.println("Galderak"+galderak);
	}
	
	public void onQuestionSelect(SelectEvent event) {
	}
	
	public QueryQuestionBean() {

		facade=FacadeBean.getBusinessLogic();
		gertaera = null;
		gertaerak = new ArrayList();
	}
	
	public List<Date> getTheDates() {
		return theDates;
	}
	
	public String getEventsMonth() {
		return FacadeBean.getEventsMonth();
	}
	
	public void onDateSelect(SelectEvent event) {
		FacesContext.getCurrentInstance().addMessage(null,
				new FacesMessage("Data aukeratua: "+event.getObject()));
		
		gertaerak = null;
		galderak = null;
		gertaerak =facade.getEvents((Date)event.getObject());
		System.out.println("Gertaerak"+gertaerak);
	}
}
