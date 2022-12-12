package bean;

import java.util.List;

import domain.LogEntry;

public class LogsBean {

	public LogsBean() {
	}
	
	public List<LogEntry> getLogs() {
		return FacadeBean.getBusinessLogic().getLogs();
	}

}
