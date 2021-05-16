package domain;

import java.io.Serializable;
import java.util.ArrayList;

import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.OneToMany;
import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlID;
import javax.xml.bind.annotation.XmlIDREF;
import javax.xml.bind.annotation.adapters.XmlJavaTypeAdapter;

@SuppressWarnings("serial")
@XmlAccessorType(XmlAccessType.FIELD)
@Entity
public class Pronostikoa implements Serializable{

	public Pronostikoa() {
		super();
	}

	public Question getGaldera() {
		return galdera;
	}

	public void setGaldera(Question galdera) {
		this.galdera = galdera;
	}

	public ArrayList<Apostua> getEgindakoApostuak() {
		return egindakoApostuak;
	}

	public void setEgindakoApostuak(ArrayList<Apostua> egindakoApostuak) {
		this.egindakoApostuak = egindakoApostuak;
	}


	@XmlID
	@XmlJavaTypeAdapter(IntegerAdapter.class)
	@Id @GeneratedValue
	private Integer id;
	private String pIzena;
    private double kuota;
    private boolean asmatua=false;
    
    public boolean isAsmatua() {
		return asmatua;
	}

	public void setAsmatua(boolean asmatua) {
		this.asmatua = asmatua;
	}


	@XmlIDREF
    private Question galdera;
    @OneToMany(fetch=FetchType.EAGER, cascade=CascadeType.PERSIST)
    private ArrayList<Apostua> egindakoApostuak = new ArrayList<Apostua>();

    public Pronostikoa(Question galdera,String pIzena, double kuota) {
        this.pIzena = pIzena;
        this.kuota = kuota;
    }

    /* @return the pIzena
     */
    public String getpIzena() {
        return pIzena;
    }


    /* @param pIzena the pIzena to set
     */
    public void setpIzena(String pIzena) {
        this.pIzena = pIzena;
    }


    /* @return the kuota
     */
    public double getKuota() {
        return kuota;
    }


    /* @param kuota the kuota to set
     */
    public void setKuota(double kuota) {
        this.kuota = kuota;
    }


    public void addApostua(Apostua a) {
        egindakoApostuak.add(a);
    }

	
	
    public int getId() {
		return id;
	}


	public void setId(int id) {
		this.id = id;
	}


}