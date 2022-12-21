package bean;

import java.util.List;

import businessLogic.BLFacade;
import domain.Question;

public class AzterketaBean {

	private BLFacade facade;
	private Question question;
	
	public Question getQuestion() {
		return question;
	}

	public void setQuestion(Question question) {
		this.question = question;
	}

	public AzterketaBean() {
		facade = FacadeBean.getBusinessLogic();
	}
	
	public List<Question> getQuestions() {
		List<Question> questions = facade.getAllQuestions();
		return questions;
	}

}
