package bean;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

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
	
	public String getEventsMonth(Date date) {
		/**
		 * 
 * 
 function highlightDays(date) {
     var dates = #{queryQuestion.getEventsMonth(date)};
     console.log(dates);
     var cssclass = '';
     for (var i = 0; i < dates.length; i++) {
         if (date === new Date(dates[i])) {
            cssclass = 'highlight-calendar';
         }
     }
     return [true, cssclass];
 }
 
 */

		List<Date> events = facade.getEventsMonth(date);
		JSONArray dates = new JSONArray();
		for(Date d : events) {
			dates.put(d.toString());
		}
		return dates.toString();
	}
	
	public void onDateSelect(SelectEvent event) {
		FacesContext.getCurrentInstance().addMessage(null,
				new FacesMessage("Data aukeratua: "+event.getObject()));
		
		gertaerak =facade.getEvents((Date)event.getObject());
		System.out.println("Gertaerak"+gertaerak);
	}
}
