package domain;

import java.util.Date;

import javax.persistence.Entity;

@Entity
public class Erabiltzailea extends Pertsona {

	private double saldoa;

	public Erabiltzailea() {
		super();
		this.saldoa = 0;
	}

	public Erabiltzailea(String izena, String pasahitza, Date jaiotzeData) {
		super(izena, pasahitza, jaiotzeData);
		this.saldoa = 0;
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
