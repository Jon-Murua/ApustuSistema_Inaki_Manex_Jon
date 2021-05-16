package domain;

import java.io.Serializable;

import javax.persistence.Entity;
import javax.persistence.Id;
import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlID;
import javax.xml.bind.annotation.XmlSeeAlso;

@XmlSeeAlso({Langilea.class,Administratzailea.class})
@XmlAccessorType(XmlAccessType.FIELD)
@Entity
public class Erabiltzailea implements Serializable {

	@XmlID
	@Id
	private String erabiltzailea;
	private String pasahitza;
	private String izena;
	private String abizena;
	private double dirua;
	
	public Erabiltzailea() {
		super();
	}

	
	public Erabiltzailea(String erabiltzailea, String pasahitza, String izena, String abizena) {
		super();
		this.erabiltzailea = erabiltzailea;
		this.pasahitza = pasahitza;
		this.izena = izena;
		this.abizena = abizena;
		this.dirua=0;
	}
	

	public String getErabiltzailea() {
		return erabiltzailea;
	}

	public void setErabiltzailea(String erabiltzailea) {
		this.erabiltzailea = erabiltzailea;
	}

	public String getPasahitza() {
		return pasahitza;
	}

	public void setPasahitza(String pasahitza) {
		this.pasahitza = pasahitza;
	}

	public String getIzena() {
		return izena;
	}

	public void setIzena(String izena) {
		this.izena = izena;
	}

	public String getAbizena() {
		return abizena;
	}

	public void setAbizena(String abizena) {
		this.abizena = abizena;
	}

	public double getDirua() {
		return dirua;
	}

	public void setDirua(double d) {
		this.dirua = d;
	}


	
	
}
