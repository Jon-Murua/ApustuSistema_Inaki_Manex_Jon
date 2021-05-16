package domain;

import java.io.Serializable;

import javax.persistence.Entity;
import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;

@XmlAccessorType(XmlAccessType.FIELD)
@Entity
public class Administratzailea extends Erabiltzailea implements Serializable {

	public Administratzailea(String erabiltzailea, String pasahitza, String izena, String abizena) {
		super(erabiltzailea, pasahitza, izena, abizena);
	}
	
	public Administratzailea() {
		super();
	}
	

}
