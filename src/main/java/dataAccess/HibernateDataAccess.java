package dataAccess;

import java.util.Calendar;
import java.util.Date;
import java.util.List;
import java.util.Locale;
import java.util.ResourceBundle;
import java.util.Vector;

import org.hibernate.HibernateException;
import org.hibernate.Query;
import org.hibernate.Session;

import configuration.UtilDate;
import domain.Admin;
import domain.Erabiltzailea;
import domain.Event;
import domain.Pertsona;
import domain.Question;
import exceptions.PertsonaAlreadyExists;
import exceptions.QuestionAlreadyExist;

public class HibernateDataAccess implements DataAccessInterface {

	protected Session session;

	public HibernateDataAccess() {
		open();
	}

	@Override
	public void open() {
		System.out.println("Opening DataBase");
		if(HibernateUtil.getSessionFactory().getCurrentSession().isOpen())
			session = HibernateUtil.getSessionFactory().getCurrentSession();
		else
			session = HibernateUtil.getSessionFactory().openSession();
	}

	@Override
	public void close() {
		// TODO Auto-generated method stub
		System.out.println("Closing DataBase");
		HibernateUtil.getSessionFactory().close();
		session = null;
	}

	@Override
	public void emptyDatabase() {
		// TODO : Hau galdetu
		session.beginTransaction();
		session.createQuery("DELETE FROM Event");
		session.createQuery("DELETE FROM Question");
		session.getTransaction().commit();
	}

	@Override
	public void initializeDB() {
		session.beginTransaction();
		try {
			Calendar today = Calendar.getInstance();

			int month = today.get(Calendar.MONTH);
			month += 1;
			int year = today.get(Calendar.YEAR);
			if (month == 12) {
				month = 0;
				year += 1;
			}

			Event ev1 = new Event("Atlético-Athletic", UtilDate.newDate(year, month, 17));
			Event ev2 = new Event("Eibar-Barcelona", UtilDate.newDate(year, month, 17));
			Event ev3 = new Event("Getafe-Celta", UtilDate.newDate(year, month, 17));
			Event ev4 = new Event("Alavés-Deportivo", UtilDate.newDate(year, month, 17));
			Event ev5 = new Event("Español-Villareal", UtilDate.newDate(year, month, 17));
			Event ev6 = new Event("Las Palmas-Sevilla", UtilDate.newDate(year, month, 17));
			Event ev7 = new Event("Malaga-Valencia", UtilDate.newDate(year, month, 17));
			Event ev8 = new Event("Girona-Leganés", UtilDate.newDate(year, month, 17));
			Event ev9 = new Event("Real Sociedad-Levante", UtilDate.newDate(year, month, 17));
			Event ev10 = new Event("Betis-Real Madrid", UtilDate.newDate(year, month, 17));

			Event ev11 = new Event("Atletico-Athletic", UtilDate.newDate(year, month, 1));
			Event ev12 = new Event("Eibar-Barcelona", UtilDate.newDate(year, month, 1));
			Event ev13 = new Event("Getafe-Celta", UtilDate.newDate(year, month, 1));
			Event ev14 = new Event("Alavés-Deportivo", UtilDate.newDate(year, month, 1));
			Event ev15 = new Event("Español-Villareal", UtilDate.newDate(year, month, 1));
			Event ev16 = new Event("Las Palmas-Sevilla", UtilDate.newDate(year, month, 1));

			Event ev17 = new Event("Málaga-Valencia", UtilDate.newDate(year, month, 28));
			Event ev18 = new Event("Girona-Leganés", UtilDate.newDate(year, month, 28));
			Event ev19 = new Event("Real Sociedad-Levante", UtilDate.newDate(year, month, 28));
			Event ev20 = new Event("Betis-Real Madrid", UtilDate.newDate(year, month, 28));

			Question q1;
			Question q2;
			Question q3;
			Question q4;
			Question q5;
			Question q6;

			if (Locale.getDefault().equals(new Locale("es"))) {
				q1 = ev1.addQuestion("¿Quién ganará el partido?", 1);
				q2 = ev1.addQuestion("¿Quién meterá el primer gol?", 2);
				q3 = ev11.addQuestion("¿Quién ganará el partido?", 1);
				q4 = ev11.addQuestion("¿Cuántos goles se marcarán?", 2);
				q5 = ev17.addQuestion("¿Quién ganará el partido?", 1);
				q6 = ev17.addQuestion("¿Habrá goles en la primera parte?", 2);
			} else if (Locale.getDefault().equals(new Locale("en"))) {
				q1 = ev1.addQuestion("Who will win the match?", 1);
				q2 = ev1.addQuestion("Who will score first?", 2);
				q3 = ev11.addQuestion("Who will win the match?", 1);
				q4 = ev11.addQuestion("How many goals will be scored in the match?", 2);
				q5 = ev17.addQuestion("Who will win the match?", 1);
				q6 = ev17.addQuestion("Will there be goals in the first half?", 2);
			} else {
				q1 = ev1.addQuestion("Zeinek irabaziko du partidua?", 1);
				q2 = ev1.addQuestion("Zeinek sartuko du lehenengo gola?", 2);
				q3 = ev11.addQuestion("Zeinek irabaziko du partidua?", 1);
				q4 = ev11.addQuestion("Zenbat gol sartuko dira?", 2);
				q5 = ev17.addQuestion("Zeinek irabaziko du partidua?", 1);
				q6 = ev17.addQuestion("Golak sartuko dira lehenengo zatian?", 2);

			}
			/*
			 * DAGOENEKO GORDETZEN DIRA EVENT PERSIST EGITEN DUELAKO BERE GALDERA LISTAN
			 * session.persist(q1); session.persist(q2); session.persist(q3);
			 * session.persist(q4); session.persist(q5); session.persist(q6);
			 * 
			 */
			session.persist(ev1);
			session.persist(ev2);
			session.persist(ev3);
			session.persist(ev4);
			session.persist(ev5);
			session.persist(ev6);
			session.persist(ev7);
			session.persist(ev8);
			session.persist(ev9);
			session.persist(ev10);
			session.persist(ev11);
			session.persist(ev12);
			session.persist(ev13);
			session.persist(ev14);
			session.persist(ev15);
			session.persist(ev16);
			session.persist(ev17);
			session.persist(ev18);
			session.persist(ev19);
			session.persist(ev20);

			session.getTransaction().commit();
			System.out.println("DB initialized");
		} catch (Exception e) {
			session.getTransaction().rollback();
			e.printStackTrace();
		}
	}

	@Override
	public Question createQuestion(Event event, String question, float betMinimum) throws QuestionAlreadyExist {
		System.out.println(">> DataAccess: createQuestion=> event= " + event + " question= " + question + " betMinimum="
				+ betMinimum);
		System.out.println(session + " " + event);

		session.beginTransaction();
		Event ev = (Event) session.get(Event.class, event.getEventNumber());

		if (ev.DoesQuestionExists(question)) {
			session.getTransaction().rollback();
			throw new QuestionAlreadyExist(ResourceBundle.getBundle("Etiquetas").getString("ErrorQueryAlreadyExist"));
		}

		Question q = ev.addQuestion(question, betMinimum);
		// db.persist(q);
		session.persist(ev);
		session.getTransaction().commit();
		return q;
	}

	@Override
	public List<Event> getEvents(Date date) {
		session.beginTransaction();
		System.out.println(">> DataAccess: getEvents");
		Vector<Event> res = new Vector<Event>();
		Query query = session.createQuery("SELECT ev FROM Event ev WHERE ev.eventDate=:date");
		query.setParameter("date", date);
		List<Event> events = query.list();
		for (Event ev : events) {
			System.out.println(ev.toString());
			res.add(ev);
		}
		session.getTransaction().commit();
		return res;
	}

	@Override
	public List<Date> getEventsMonth(Date date) {
		session.beginTransaction();
		System.out.println(">> DataAccess: getEventsMonth");
		Vector<Date> res = new Vector<Date>();

		Date firstDayMonthDate = UtilDate.firstDayMonth(date);
		Date lastDayMonthDate = UtilDate.lastDayMonth(date);

		Query query = session
				.createQuery("SELECT DISTINCT ev.eventDate FROM Event ev WHERE ev.eventDate BETWEEN :has and :buk");
		query.setParameter("has", firstDayMonthDate);
		query.setParameter("buk", lastDayMonthDate);
		List<Date> dates = query.list();
		for (Date d : dates) {
			System.out.println(d.toString());
			res.add(d);
		}
		session.getTransaction().commit();
		return res;
	}

	@Override
	public boolean existQuestion(Event event, String question) {
		System.out.println(">> DataAccess: existQuestion=> event= " + event + " question= " + question);
		session.beginTransaction();
		Event ev = (Event) session.get(Event.class, event.getEventNumber());
		session.getTransaction().commit();
		return ev.DoesQuestionExists(question);
	}

	@Override
	public Pertsona getPertsona(String izena) {
		session.beginTransaction();
		Pertsona p = null;
		try {
			p = (Pertsona) session.get(Pertsona.class, izena);
			session.getTransaction().commit();
		} catch (HibernateException e) {
			System.out.println(e);
			session.getTransaction().rollback();
			return null;
		}
		return p;
	}

	@Override
	public Pertsona erregistratu(String izena, String pasahitza, Date jaiotzeData, String mota)
			throws PertsonaAlreadyExists, RuntimeException {
		Pertsona p = this.getPertsona(izena);
		this.open(); // Commit session itxi egiten du
		if (p != null)
			throw new PertsonaAlreadyExists("Dagoeneko existitzen da erabiltzaile bat izen horrekin. Izena: " + izena);
		if (!mota.equals("admin") && !mota.equals("erabiltzailea"))
			throw new RuntimeException(mota + " pertsona mota ez da existitzen");
		session.beginTransaction();
		try {
			if (mota.equals("admin")) {
				p = new Admin(izena, pasahitza, jaiotzeData);
			} else {
				p = new Erabiltzailea(izena, pasahitza, jaiotzeData);
			}
			session.persist(p);
			session.getTransaction().commit();
			return p;
		} catch (HibernateException e) {
			System.out.println(e);
			session.getTransaction().rollback();
			return null;
		}
	}

	@Override
	public boolean loginZuzena(String izena, String pasahitza) {
		Pertsona p = this.getPertsona(izena);
		if(p == null) return false;
		return p.pasahitzaZuzena(pasahitza);
	}

}
