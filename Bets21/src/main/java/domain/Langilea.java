package domain;

import java.io.Serializable;

import javax.persistence.Entity;
import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;

@XmlAccessorType(XmlAccessType.FIELD)
@Entity
public class Langilea extends Erabiltzailea implements Serializable{

	public Langilea(String erabiltzailea, String pasahitza, String izena, String abizena) {
		super(erabiltzailea, pasahitza, izena, abizena);
		
	}
	
	public Langilea() {
		super();
	}

	

}
