package dataAccess;

import java.util.Date;
import java.util.List;

import domain.Admin;
import domain.Blokeoa;
import domain.Erabiltzailea;
import domain.Event;
import domain.LogEntry;
import domain.Pertsona;
import domain.Question;
import exceptions.AdinaEzNahikoaException;
import exceptions.DagoenekoBlokeatutaDagoException;
import exceptions.PertsonaAlreadyExists;
import exceptions.QuestionAlreadyExist;

public interface DataAccessInterface {

	/**
	 * This method opens the database
	 */
	void open();

	/**
	 * This method closes the database
	 */
	void close();

	/**
	 * This method removes all the elements of the database
	 */
	void emptyDatabase();

	/**
	 * This is the data access method that initializes the database with some events
	 * and questions. This method is invoked by the business logic (constructor of
	 * BLFacadeImplementation) when the option "initialize" is declared in the tag
	 * dataBaseOpenMode of resources/config.xml file
	 */
	void initializeDB();

	/**
	 * This method creates a question for an event, with a question text and the
	 * minimum bet
	 * 
	 * @param event      to which question is added
	 * @param question   text of the question
	 * @param betMinimum minimum quantity of the bet
	 * @return the created question, or null, or an exception
	 * @throws QuestionAlreadyExist if the same question already exists for the
	 *                              event
	 */
	Question createQuestion(Event event, String question, float betMinimum) throws QuestionAlreadyExist;

	/**
	 * This method retrieves from the database the events of a given date
	 * 
	 * @param date in which events are retrieved
	 * @return collection of events
	 */
	List<Event> getEvents(Date date);

	/**
	 * This method retrieves from the database the dates a month for which there are
	 * events
	 * 
	 * @param date of the month for which days with events want to be retrieved
	 * @return collection of dates
	 */
	List<Date> getEventsMonth(Date date);

	/**
	 * This method checks if the question has been previously added to the event
	 * 
	 * @param event    the event
	 * @param question the question to check
	 * @return true if the event contains this the questions, false in other case
	 */
	boolean existQuestion(Event event, String question);

	public Pertsona getPertsona(String izena);

	public Pertsona erregistratu(String izena, String pasahitza, Date jaiotzeData, String mota) throws PertsonaAlreadyExists, RuntimeException, AdinaEzNahikoaException;

	public boolean loginZuzena(String izena, String pasahitza);

	public double diruaSartu(Erabiltzailea p, double amount);

	public List<Pertsona> getUsers();

	public Blokeoa blokeatu(Admin nork, Pertsona nori, String mezua) throws DagoenekoBlokeatutaDagoException;
	
	public Blokeoa desblokeatu(Pertsona nor);

	public LogEntry log(String mezua);

	public List<LogEntry> getLogs();

	public List<Question> getAllQuestions();
}