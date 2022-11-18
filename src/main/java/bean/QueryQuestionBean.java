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
		
		List<Date> events = new ArrayList();
		
		List<Date> dates = new ArrayList();
		
		int year = Calendar.getInstance().YEAR;
		int month = Calendar.getInstance().MONTH; 
		int day = Calendar.getInstance().DAY_OF_WEEK;
		for(int i = 0; i < 12; i++) {
			if(month+1 > 12) {
				year++;
				month = 0;
			}
			dates.add(new Date(year+121,month,day));
			month++;
		}
		
		for(Date date : dates) {
			List<Date> evs = facade.getEventsMonth(date);
			events.addAll(evs);
		}
		
		JSONArray datesJSON = new JSONArray();
		for(Date d : events) {
			datesJSON.put(d.getTime());
		}
		System.out.println(datesJSON.toString());
		return datesJSON.toString();
	}
	
	public void onDateSelect(SelectEvent event) {
		FacesContext.getCurrentInstance().addMessage(null,
				new FacesMessage("Data aukeratua: "+event.getObject()));
		
		gertaerak =facade.getEvents((Date)event.getObject());
		System.out.println("Gertaerak"+gertaerak);
	}
}
