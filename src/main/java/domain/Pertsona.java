package domain;

import java.time.LocalDate;
import java.time.Period;
import java.util.Calendar;
import java.util.Date;
import java.util.GregorianCalendar;

import javax.persistence.Entity;
import javax.persistence.Id;

@Entity
public abstract class Pertsona {
	@Id
	private String izena;
	private String pasahitza;
	private Date jaiotzeData;

	public Pertsona() {
		this.pasahitza = null;
		this.izena = null;
		this.jaiotzeData = new Date();
	}

	public Pertsona(String izena, String pasahitza, Date jaiotzeData) {
		this.izena = izena;
		this.pasahitza = pasahitza;
		this.jaiotzeData = jaiotzeData;
	}

	public Date getJaiotzeData() {
		return jaiotzeData;
	}

	public void setJaiotzeData(Date jaiotzeData) {
		this.jaiotzeData = jaiotzeData;
	}

	public int getAdina() {
		Calendar gaur = Calendar.getInstance();
		Calendar calendar = new GregorianCalendar();
		calendar.setTime(jaiotzeData);
		int year = calendar.get(Calendar.YEAR);
		int month = calendar.get(Calendar.MONTH);
		int monthD = calendar.get(Calendar.DAY_OF_MONTH);
		Period p = Period.between(LocalDate.of(year,month,monthD),LocalDate.of(gaur.get(Calendar.YEAR),gaur.get(Calendar.MONTH),gaur.get(Calendar.DAY_OF_MONTH)));
		return p.getYears();
	}

	public String toString() {
		return "Izena: " + this.izena + " Adina: " + this.getAdina();
	}

	public String getIzena() {
		return izena;
	}

	public void setIzena(String izena) {
		this.izena = izena;
	}

	public String getPasahitza() {
		return pasahitza;
	}

	public void setPasahitza(String pasahitza) {
		this.pasahitza = pasahitza;
	}

	public boolean pasahitzaZuzena(String pasahitza) {
		return (pasahitza.equals(this.pasahitza));
	}

	@Override
	public boolean equals(Object other) {
		if (other == null)
			return false;
		else if (!(other instanceof Pertsona))
			return false;
		else {
			Pertsona oEr = (Pertsona) other;
			return (this.getIzena().equals(oEr.getIzena()));
		}
	}

}
