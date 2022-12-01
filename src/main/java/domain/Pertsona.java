package domain;

import java.util.Calendar;
import java.util.Date;

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
		int urteDif = Math.abs(gaur.get(Calendar.YEAR) - jaiotzeData.getYear());
		int hilbDif = gaur.get(Calendar.MONTH) - jaiotzeData.getMonth();

		int hilabKop = urteDif * 12 + (hilbDif > 0 ? hilbDif : 0);

		int adina = hilabKop / 12;
		return adina;
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
