package domain;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.OneToMany;

@Entity
public class Erabiltzailea extends Pertsona {

	private double saldoa;
	@OneToMany(fetch = FetchType.EAGER, targetEntity = Mugimendua.class, mappedBy = "erabiltzailea", cascade = {CascadeType.PERSIST, CascadeType.REMOVE})
	private List<Mugimendua> mugimenduak;

	public List<Mugimendua> getMugimenduak() {
		return mugimenduak;
	}

	public void setMugimenduak(List<Mugimendua> mugimenduak) {
		this.mugimenduak = mugimenduak;
	}

	public Erabiltzailea() {
		super();
		this.saldoa = 0;
		mugimenduak = new ArrayList<Mugimendua>();
	}

	public Erabiltzailea(String izena, String pasahitza, Date jaiotzeData) {
		super(izena, pasahitza, jaiotzeData);
		this.saldoa = 0;
		mugimenduak = new ArrayList<Mugimendua>();
	}

	public double getSaldoa() {
		return saldoa;
	}

	public void setSaldoa(double saldoa) {
		this.saldoa = saldoa;
	}

	public boolean diruaNahikoa(Double diruKop) {
		if ((this.getSaldoa() - diruKop) >= 0) {
			return true;
		} else {
			return false;
		}
	}

	public void saldoaGehitu(Double diruKop) {
		this.saldoa += diruKop;
	}
	
	private void mugimenduaGehitu(Mugimendua m) {
		this.mugimenduak.add(m);
	}
	
	public Mugimendua mugimenduaSortu(double diruAldaketa, String mezua) {
		Mugimendua m = new Mugimendua(this, diruAldaketa, mezua);
		this.mugimenduaGehitu(m);
		return m;
	}

	@Override
	public boolean equals(Object other) {
		if (other == null)
			return false;
		else if (!(other instanceof Erabiltzailea))
			return false;
		else {
			Erabiltzailea oEr = (Erabiltzailea) other;
			return (this.getIzena().equals(oEr.getIzena()));
		}
	}

}
