package businessLogic;

import java.util.ArrayList;
//hola
import java.util.Date;
import java.util.List;
import java.util.ResourceBundle;

import dataAccess.DataAccessInterface;
import domain.Admin;
import domain.Blokeoa;
import domain.Erabiltzailea;
import domain.Event;
import domain.Pertsona;
import domain.Question;
import exceptions.AdinaEzNahikoaException;
import exceptions.EventFinished;
import exceptions.PertsonaAlreadyExists;
import exceptions.QuestionAlreadyExist;

/**
 * It implements the business logic as a web service.
 */
public class BLFacadeImplementation implements BLFacade {
	DataAccessInterface dbManager;

	public BLFacadeImplementation() {
		System.out.println("Creating BLFacadeImplementation instance");
	}

	public BLFacadeImplementation(DataAccessInterface da) {

		System.out.println("Creating BLFacadeImplementation instance with DataAccess parameter");
		da.emptyDatabase();
		da.open();
		da.initializeDB();
		da.close();

		dbManager = da;
	}

	/**
	 * This method creates a question for an event, with a question text and the
	 * minimum bet
	 * 
	 * @param event      to which question is added
	 * @param question   text of the question
	 * @param betMinimum minimum quantity of the bet
	 * @return the created question, or null, or an exception
	 * @throws EventFinished        if current data is after data of the event
	 * @throws QuestionAlreadyExist if the same question already exists for the
	 *                              event
	 */

	public Question createQuestion(Event event, String question, float betMinimum)
			throws EventFinished, QuestionAlreadyExist {

		// The minimum bed must be greater than 0
		dbManager.open();
		Question qry = null;

		if (new Date().compareTo(event.getEventDate()) > 0)
			throw new EventFinished(ResourceBundle.getBundle("Etiquetas").getString("ErrorEventHasFinished"));

		qry = dbManager.createQuestion(event, question, betMinimum);

		dbManager.close();

		return qry;
	};

	/**
	 * This method invokes the data access to retrieve the events of a given date
	 * 
	 * @param date in which events are retrieved
	 * @return collection of events
	 */

	public List<Event> getEvents(Date date) {
		dbManager.open();
		List<Event> events = dbManager.getEvents(date);
		dbManager.close();
		return events;
	}

	/**
	 * This method invokes the data access to retrieve the dates a month for which
	 * there are events
	 * 
	 * @param date of the month for which days with events want to be retrieved
	 * @return collection of dates
	 */
	public List<Date> getEventsMonth(Date date) {
		dbManager.open();
		List<Date> dates = dbManager.getEventsMonth(date);
		dbManager.close();
		return dates;
	}

	public void close() {
		// DataAccess dB4oManager=new DataAccess(false);

		// dB4oManager.close();
		dbManager.close();

	}

	/**
	 * This method invokes the data access to initialize the database with some
	 * events and questions. It is invoked only when the option "initialize" is
	 * declared in the tag dataBaseOpenMode of resources/config.xml file
	 */

	public void initializeBD() {
		dbManager.open();
		dbManager.initializeDB();
		dbManager.close();
	}

	@Override
	public Pertsona loginEgin(String username, String password) {
		if (username == null || password == null)
			return null;
		dbManager.open();
		Pertsona p = null;
		if (dbManager.loginZuzena(username, password)) {
			dbManager.open(); // login zuzena commit egiten duenean sahia akabo
			p = dbManager.getPertsona(username);
		}
		dbManager.close();
		return p;
	}

	@Override
	public Pertsona registerEgin(String username, String password, Date birthDate, String rola)
			throws AdinaEzNahikoaException, PertsonaAlreadyExists, RuntimeException {
		if (username == null || password == null || birthDate == null || rola == null)
			return null;
		dbManager.open();
		Pertsona p = null;
		p = dbManager.erregistratu(username, password, birthDate, rola);
		dbManager.close();
		return p;
	}

	@Override
	public double diruaSartu(Erabiltzailea p, double amount) throws RuntimeException {
		double saldoBerria = 0;
		if (p == null) {
			throw new RuntimeException("Erabiltzailea cannot be null");
		} else if (amount <= 0) {
			throw new RuntimeException("Inputed money amount is not correct. It must be > 0");
		} else {
			dbManager.open();
			saldoBerria = dbManager.diruaSartu(p,amount);
			dbManager.close();
		}
		return saldoBerria;
	}

	@Override
	public Pertsona getPertsona(String izena) {
		if(izena == null) return null;
		Pertsona p = null;
		dbManager.open();
		p = dbManager.getPertsona(izena);
		dbManager.close();
		return p;
	}

	@Override
	public List<Pertsona> getUsers() {
		List<Pertsona> users = null;
		dbManager.open();
		users = dbManager.getUsers();
		dbManager.close();
		return users == null ? new ArrayList<Pertsona>() : users;
	}

	@Override
	public Blokeoa blokeatu(Admin nork, Pertsona nori, String mezua) {
		Blokeoa b = null;
		dbManager.open();
		b = dbManager.blokeatu(nork,nori,mezua);
		dbManager.close();
		return b;
	}

	@Override
	public Blokeoa desblokeatu(Pertsona nor) {
		Blokeoa b = null;
		dbManager.open();
		b = dbManager.desblokeatu(nor);
		dbManager.close();
		return b;
	}
	
	

}
