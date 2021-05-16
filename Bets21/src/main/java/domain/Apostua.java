package domain;

import java.io.Serializable;
import java.util.ArrayList;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlID;
import javax.xml.bind.annotation.XmlIDREF;
import javax.xml.bind.annotation.adapters.XmlJavaTypeAdapter;

@XmlAccessorType(XmlAccessType.FIELD)
@Entity
public class Apostua implements Serializable{

	@Id 
	@XmlJavaTypeAdapter(IntegerAdapter.class)
	@GeneratedValue
	@XmlID
	private Integer apostuID;
    private double dirua;
    @XmlIDREF
    private ArrayList<Pronostikoa> pronostikoa=new ArrayList<Pronostikoa>();
    @XmlIDREF
    private Erregistratua erregistratua;
    private Boolean tratatua=false;
    private Erregistratua egilea;
    private Boolean originala;

    public Apostua(double dirua, ArrayList<Pronostikoa> pronostikoa, Erregistratua erregistratua,Erregistratua jabea) {
        this.dirua = dirua;
        this.pronostikoa = pronostikoa;
        this.erregistratua = erregistratua;
        this.egilea=jabea;
        this.originala=false;
    }
    
    
    public Erregistratua getEgilea() {
		return egilea;
	}


	public void setEgilea(Erregistratua egilea) {
		this.egilea = egilea;
	}


	public Boolean getOriginala() {
		return originala;
	}


	public void setOriginala(Boolean originala) {
		this.originala = originala;
	}


	public Boolean getTratatua() {
		return tratatua;
	}
	public void setTratatua(Boolean tratatua) {
		this.tratatua = tratatua;
	}
	/* @return the dirua
     */
    public double getDirua() {
        return dirua;
    }
    /* @param dirua the dirua to set
     */
    public void setDirua(double dirua) {
        this.dirua = dirua;
    }
    /* @return the pronostikoa
     */
    public ArrayList<Pronostikoa> getPronostikoa() {
        return pronostikoa;
    }
    /* @param pronostikoa the pronostikoa to set
     */
    public void setPronostikoa(ArrayList<Pronostikoa> pronostikoa) {
       this.pronostikoa=pronostikoa;
    }
    /* @return the erregistratua
     */
    public Erregistratua getErregistratua() {
        return erregistratua;
    }
    /* @param erregistratua the erregistratua to set
     */
    public void setErregistratua(Erregistratua erregistratua) {
        this.erregistratua = erregistratua;
    }
    public Apostua(double dirua, ArrayList<Pronostikoa> pronostikoa, Erregistratua erregistratua) {
        this.dirua = dirua;
        this.pronostikoa = pronostikoa;
        this.erregistratua = erregistratua;
        this.egilea=null;
        this.originala=true;
    }
    
	public Apostua() {
		super();
	}
	public int getApostuID() {
		return apostuID;
	}
	public void setApostuID(int apostuID) {
		this.apostuID = apostuID;
	}
    
}