package domain;

import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.ManyToOne;

@Entity 
public class Mugimendua {
	@Id 
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Integer mugimenduZbkia;
	private double kantitatea;
	private String arrazoia;
	@ManyToOne(targetEntity = Erabiltzailea.class, fetch = FetchType.EAGER)
	private Erabiltzailea erabiltzailea;
	
	public Mugimendua() {
		this.erabiltzailea = null;
		this.kantitatea = 0;
		this.arrazoia = null;
	}
	
	public Mugimendua(Erabiltzailea erabiltzailea, double kantitatea, String arrazoia) {
		this.erabiltzailea = erabiltzailea;
		this.kantitatea = kantitatea;
		this.arrazoia = arrazoia;
	}
	
	public int getMugimenduZbkia() {
		return mugimenduZbkia;
	}
	public void setMugimenduZbkia(int mugimenduZbkia) {
		this.mugimenduZbkia = mugimenduZbkia;
	}
	public double getKantitatea() {
		return kantitatea;
	}
	public void setKantitatea(double kantitatea) {
		this.kantitatea = kantitatea;
	}
	public String getArrazoia() {
		return arrazoia;
	}
	public void setArrazoia(String arrazoia) {
		this.arrazoia = arrazoia;
	}
	public Erabiltzailea getErabiltzailea() {
		return erabiltzailea;
	}
	public void setErabiltzailea(Erabiltzailea erabiltzailea) {
		this.erabiltzailea = erabiltzailea;
	}
	
}