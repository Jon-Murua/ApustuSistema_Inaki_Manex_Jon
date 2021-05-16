package domain;

import java.io.Serializable;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlID;
import javax.xml.bind.annotation.adapters.XmlJavaTypeAdapter;

@XmlAccessorType(XmlAccessType.FIELD)
@Entity
public class Mugimendua implements Serializable{
	
	public Mugimendua() {
		super();
	}

	//Motak:
	//	1 apostua egin
	//	2 apostua irabazi
	//	3 dirua sartu
	//	4 dirua atea
	//	5 desuseztaturiko apostatuen dirua itzuli 
	//  6 jarraitzailearen komisioa
	@Id 
	@XmlJavaTypeAdapter(IntegerAdapter.class)
	@GeneratedValue
	@XmlID
	private Integer id;
	private int mota;
	private double zenbat;

	public Mugimendua(int mota,double zenbat) {
		this.mota=mota;
		this.zenbat=Math.round(zenbat*100.0)/100.0;
	}

	/**
	 * @return the mota
	 */
	public int getMota() {
		return mota;
	}

	/**
	 * @param mota the mota to set
	 */
	public void setMota(int mota) {
		this.mota = mota;
	}

	/**
	 * @return the zenbat
	 */
	public double getZenbat() {
		return zenbat;
	}

	/**
	 * @param zenbat the zenbat to set
	 */
	public void setZenbat(double zenbat) {
		this.zenbat = Math.round(zenbat*100.0)/100.0;
	}

	@Override
    public String toString() {
        if(mota==1) {
            return "Apostua egin: -" + this.zenbat ;
        }else if(mota==2) {
            return "Apostua irabazi: +" + this.zenbat ;
        }else if(mota==3) {
            return "Dirua sartu: +" + this.zenbat ;
        }else if(mota==4) {
            return "Dirua atera: -" + this.zenbat ;
        }else if(mota==5) {
            return "Desuseztaturiko apostatuen dirua itzuli : +" + this.zenbat ;
        }else
            return "Jarraitzailearen komisioa: +" + this.zenbat ;
    }
}
