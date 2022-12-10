package domain;

import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.ManyToOne;
import javax.persistence.OneToOne;

@Entity
public class Blokeoa {
	public Integer getId() {
		return id;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	@Id @GeneratedValue(strategy = GenerationType.IDENTITY)
	private Integer id;
	@ManyToOne(fetch = FetchType.EAGER, targetEntity = Admin.class)
	private Admin nork;
	@OneToOne(fetch = FetchType.EAGER,targetEntity = Pertsona.class)
	private Pertsona nori;
	
	private String mezua;
	
	public Blokeoa() {
		this.nork = null;
		this.nori = null;
		this.mezua = null;
	}
	
	public Blokeoa(Admin nork, Pertsona nori, String mezua) {
		this.nork = nork;
		this.nori = nori;
		this.mezua = mezua;
	}

	public Admin getNork() {
		return nork;
	}

	public void setNork(Admin nork) {
		this.nork = nork;
	}

	public Pertsona getNori() {
		return nori;
	}

	public void setNori(Pertsona nori) {
		this.nori = nori;
	}

	public String getMezua() {
		return mezua;
	}

	public void setMezua(String mezua) {
		this.mezua = mezua;
	}
}
