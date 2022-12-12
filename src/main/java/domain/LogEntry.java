package domain;

import java.util.Date;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;

@Entity
public class LogEntry {

	@Id @GeneratedValue(strategy = GenerationType.IDENTITY)
	private Integer id;
	private Date data;
	private String mezua;
	
	public LogEntry() {
		id = null;
		data = null;
		mezua = null;
	}
	
	public LogEntry(String mezua) {
		this.data = new Date();
		this.mezua = mezua;
	}
	
	public Integer getId() {
		return id;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	public Date getData() {
		return data;
	}

	public void setData(Date data) {
		this.data = data;
	}

	public String getMezua() {
		return mezua;
	}

	public void setMezua(String mezua) {
		this.mezua = mezua;
	}

}
