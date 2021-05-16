package domain;


import java.io.Serializable;
import java.util.ArrayList;
import java.util.Date;

import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.OneToMany;
import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;

@XmlAccessorType(XmlAccessType.FIELD)
@Entity
public class Erregistratua extends Erabiltzailea implements Serializable{
	public Erregistratua() {
		super();
	}
	private String postaElektronikoa;
	private Date jaiotzeData;
	private ArrayList<Erregistratua> jarraitzaileak = new ArrayList<Erregistratua>();
    private Erregistratua jarraitzen;
    private int ehunekoa=0;

	@OneToMany(fetch=FetchType.EAGER, cascade=CascadeType.PERSIST)
	private ArrayList<Mugimendua> mugimenduak = new ArrayList<Mugimendua>();

	@OneToMany(fetch=FetchType.EAGER, cascade=CascadeType.PERSIST)
	private ArrayList<Apostua> egindakoApostuak = new ArrayList<Apostua>();
	
	@OneToMany(fetch=FetchType.EAGER, cascade=CascadeType.PERSIST)
	private ArrayList<Apostua> unekoApostuak = new ArrayList<Apostua>();
	
	
	public Erregistratua(String erabiltzailea, String pasahitza, String izena, String abizena, String postaElektronikoa, Date jaiotzeData) {
		super(erabiltzailea, pasahitza, izena, abizena);
		this.postaElektronikoa=postaElektronikoa;
		this.jaiotzeData=jaiotzeData;
	}
	
	public ArrayList<Erregistratua> getJarraitzaileak() {
		return jarraitzaileak;
	}
	public void setJarraitzaileak(ArrayList<Erregistratua> jarraitzaileak) {
		this.jarraitzaileak = jarraitzaileak;
	}
	public Erregistratua getJarraitzen() {
		return jarraitzen;
	}
	public void setJarraitzen(Erregistratua jarraitzen) {
		this.jarraitzen = jarraitzen;
	}
	public int getEhunekoa() {
		return ehunekoa;
	}
	public void setEhunekoa(int ehunekoa) {
		this.ehunekoa = ehunekoa;
	}
	public ArrayList<Mugimendua> getMugimenduak() {
		return mugimenduak;
	}
	public void setMugimenduak(ArrayList<Mugimendua> mugimenduak) {
		this.mugimenduak = mugimenduak;
	}
	public void addMugimendua(Mugimendua mugimendua) {
		mugimenduak.add(mugimendua);
	}
	public String getPostaElektronikoa() {
		return postaElektronikoa;
	}
	public void setPostaElektronikoa(String postaElektronikoa) {
		this.postaElektronikoa = postaElektronikoa;
	}
	public Date getJaiotzeData() {
		return jaiotzeData;
	}
	public void setJaiotzeData(Date jaiotzeData) {
		this.jaiotzeData = jaiotzeData;
	}
		public ArrayList<Apostua> getEgindakoApostuak() {
		return egindakoApostuak;
	}
	public void setEgindakoApostuak(ArrayList<Apostua> egindakoApostuak) {
		this.egindakoApostuak = egindakoApostuak;
	}
	public ArrayList<Apostua> getUnekoApostuak() {
		return unekoApostuak;
	}
	public void setUnekoApostuak(ArrayList<Apostua> unekoApostuak) {
		this.unekoApostuak = unekoApostuak;
	}
	public void addApostua(Apostua a) {
        egindakoApostuak.add(a);
    }
	public void addJarraitzailea(Erregistratua jarraitzailea) {
		jarraitzaileak.add(jarraitzailea);		
	}
	
}
