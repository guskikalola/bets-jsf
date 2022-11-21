package bean;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import javax.faces.application.FacesMessage;
import javax.faces.context.FacesContext;

import org.primefaces.event.SelectEvent;

import businessLogic.BLFacade;
import domain.Event;
import domain.Question;
import exceptions.EventFinished;
import exceptions.QuestionAlreadyExist;

public class CreateQuestionBean {
	private BLFacade facade;

	private List<Date> theDates;
	private Event gertaera;
	private List<Event> gertaerak;
	private List<Question> galderak;

	private String question;
	private Float minBet;

	public BLFacade getFacade() {
		return facade;
	}

	public void setFacade(BLFacade facade) {
		this.facade = facade;
	}

	public String getQuestion() {
		return question;
	}

	public void setQuestion(String question) {
		this.question = question;
	}

	public Float getMinBet() {
		return minBet;
	}

	public void setMinBet(Float minBet) {
		this.minBet = minBet;
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
		Event ev = (Event) event.getObject();
		gertaera = ev;
		galderak = ev.getQuestions();
	}

	public CreateQuestionBean() {

		facade = FacadeBean.getBusinessLogic();
		gertaera = null;
		gertaerak = new ArrayList();
	}

	public List<Date> getTheDates() {
		return theDates;
	}

	public String getEventsMonth() {
		return FacadeBean.getEventsMonth();
	}

	public void createQuestion() {
		try {
			if(question.length() == 0) {
				FacesContext.getCurrentInstance().addMessage(null,
						new FacesMessage("Galdera sartzea falta da."));	
			} else {
				facade.createQuestion(gertaera, question, minBet);
				FacesContext.getCurrentInstance().addMessage(null,
						new FacesMessage("Galdera sortuta."));				
			}
		} catch (NullPointerException e) {
			e.printStackTrace();
			FacesContext.getCurrentInstance().addMessage(null,
					new FacesMessage("Ez dira eremu guztiak bete."));
		} catch (EventFinished e) {
			e.printStackTrace();
			FacesContext.getCurrentInstance().addMessage(null, new FacesMessage("Gertaera dagoeneko bukatu da."));
		} catch (QuestionAlreadyExist e) {
			e.printStackTrace();
			FacesContext.getCurrentInstance().addMessage(null,
					new FacesMessage("Galdera dagoeneko existitzen da: " + question));
		}
	}

	public void onDateSelect(SelectEvent event) {
		FacesContext.getCurrentInstance().addMessage(null, new FacesMessage("Data aukeratua: " + event.getObject()));

		gertaerak = facade.getEvents((Date) event.getObject());
		System.out.println("Gertaerak" + gertaerak);
	}
}
