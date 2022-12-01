package bean;

import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.List;

import org.primefaces.json.JSONArray;

import businessLogic.BLFacade;
import businessLogic.BLFacadeImplementation;
import dataAccess.HibernateDataAccess;

public class FacadeBean {
	private static FacadeBean singleton = new FacadeBean();
	private static BLFacade facadeInterface;

	private FacadeBean() {

		try {
			facadeInterface = new BLFacadeImplementation(new HibernateDataAccess());
		} catch (Exception e) {
			System.out.println("FacadeBean: negozioaren logika sortzean errorea: " + e.getMessage());
		}
	}

	public static BLFacade getBusinessLogic() {
		return facadeInterface;
	}
	
	public static String getEventsMonth() {
		List<Date> events = new ArrayList();
		
		int year = Calendar.getInstance().YEAR;
		int month = Calendar.getInstance().MONTH; 
		int day = Calendar.getInstance().DAY_OF_WEEK;
		for(int i = 0; i < 12; i++) {
			if(month+1 > 12) {
				year++;
				month = 0;
			}
			Date date = new Date(year+121,month,day);
			month++;

			List<Date> evs = facadeInterface.getEventsMonth(date);
			events.addAll(evs);
		}
		
		JSONArray datesJSON = new JSONArray();
		for(Date d : events) {
			datesJSON.put(d.getTime());
		}
		System.out.println(datesJSON.toString());
		return datesJSON.toString();
	}

}