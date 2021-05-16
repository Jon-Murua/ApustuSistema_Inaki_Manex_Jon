package dataAccess;

import java.time.Duration;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Locale;
import java.util.Map;
import java.util.ResourceBundle;
import java.util.Vector;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;
import javax.persistence.TypedQuery;

import org.glassfish.pfl.dynamic.codegen.impl.ExpressionFactory.NewArrExpression;

import configuration.ConfigXML;
import configuration.UtilDate;
import domain.Administratzailea;
import domain.Apostua;
import domain.Erabiltzailea;
import domain.Erregistratua;
import domain.Event;
import domain.Langilea;
import domain.Mugimendua;
import domain.Pronostikoa;
import domain.Question;
import exceptions.AdinTxikikoa;
import exceptions.DiruFalta;
import exceptions.DiruNegatiboa;
import exceptions.ErabiltzaileaBadago;
import exceptions.ErabiltzaileaEzDago;
import exceptions.EventAlreadyExists;
import exceptions.PasahitzaOkerra;
import exceptions.PronostikoaAlreadyExists;
import exceptions.QuestionAlreadyExist;

/**
 * It implements the data access to the objectDb database
 */
public class DataAccess  {
	protected static EntityManager  db;
	protected static EntityManagerFactory emf;


	ConfigXML c=ConfigXML.getInstance();

     public DataAccess(boolean initializeMode)  {
		
		System.out.println("Creating DataAccess instance => isDatabaseLocal: "+c.isDatabaseLocal()+" getDatabBaseOpenMode: "+c.getDataBaseOpenMode());

		open(initializeMode);
		
	}

	public DataAccess()  {	
		 this(false);
	}
	
	
	/**
	 * This is the data access method that initializes the database with some events and questions.
	 * This method is invoked by the business logic (constructor of BLFacadeImplementation) when the option "initialize" is declared in the tag dataBaseOpenMode of resources/config.xml file
	 */	
	public void initializeDB(){
		
		db.getTransaction().begin();
		try {

			
		   Calendar today = Calendar.getInstance();
		   
		   int month=today.get(Calendar.MONTH);
		   month+=1;
		   int year=today.get(Calendar.YEAR);
		   if (month==12) { month=0; year+=1;}  
	    
			Event ev1=new Event(1, "Atlético-Athletic", UtilDate.newDate(year,month,17));
			Event ev2=new Event(2, "Eibar-Barcelona", UtilDate.newDate(year,month,17));
			Event ev3=new Event(3, "Getafe-Celta", UtilDate.newDate(year,month,17));
			Event ev4=new Event(4, "Alavés-Deportivo", UtilDate.newDate(year,month,17));
			Event ev5=new Event(5, "Español-Villareal", UtilDate.newDate(year,month,17));
			Event ev6=new Event(6, "Las Palmas-Sevilla", UtilDate.newDate(year,month,17));
			Event ev7=new Event(7, "Malaga-Valencia", UtilDate.newDate(year,month,17));
			Event ev8=new Event(8, "Girona-Leganés", UtilDate.newDate(year,month,17));
			Event ev9=new Event(9, "Real Sociedad-Levante", UtilDate.newDate(year,month,17));
			Event ev10=new Event(10, "Betis-Real Madrid", UtilDate.newDate(year,month,17));

			Event ev11=new Event(11, "Atletico-Athletic", UtilDate.newDate(year,month,1));
			Event ev12=new Event(12, "Eibar-Barcelona", UtilDate.newDate(year,month,1));
			Event ev13=new Event(13, "Getafe-Celta", UtilDate.newDate(year,month,1));
			Event ev14=new Event(14, "Alavés-Deportivo", UtilDate.newDate(year,month,1));
			Event ev15=new Event(15, "Español-Villareal", UtilDate.newDate(year,month,1));
			Event ev16=new Event(16, "Las Palmas-Sevilla", UtilDate.newDate(year,month,1));
			

			Event ev17=new Event(17, "Málaga-Valencia", UtilDate.newDate(year,month+1,28));
			Event ev18=new Event(18, "Girona-Leganés", UtilDate.newDate(year,month+1,28));
			Event ev19=new Event(19, "Real Sociedad-Levante", UtilDate.newDate(year,month+1,28));
			Event ev20=new Event(20, "Betis-Real Madrid", UtilDate.newDate(year,month+1,28));
			
			Question q1;
			Question q2;
			Question q3;
			Question q4;
			Question q5;
			Question q6;
			
			Administratzailea a1= new Administratzailea("111","111","admin1","admin1");
			Langilea l1=new Langilea("222","222","lan1","lan1");
			Erregistratua e1=new Erregistratua("333","333","erreg1","erreg1","erreg1@gmail.com",UtilDate.newDate(year, month, 15));
			Erregistratua e2=new Erregistratua("444","444","erreg2","erreg2","erreg2@gmail.com",UtilDate.newDate(year, month, 15));
			
				q1=ev1.addQuestion("Zeinek irabaziko du partidua?",1);
				q2=ev1.addQuestion("Zeinek sartuko du lehenengo gola?",2);
				q3=ev11.addQuestion("Zeinek irabaziko du partidua?",1);
				q4=ev11.addQuestion("Zenbat gol sartuko dira?",2);
				q5=ev17.addQuestion("Zeinek irabaziko du partidua?",1);
				q6=ev17.addQuestion("Golak sartuko dira lehenengo zatian?",2);
				
			
			e1.setDirua(5000);
			e2.setDirua(6000);
            Pronostikoa p = new Pronostikoa(q1,"Athleticek irabazi", 2);
            Pronostikoa p1 = new Pronostikoa(q1,"Atleticok irabazi", 3);
         
            q3.addPronostikoa(p1);
            q3.addPronostikoa(p);

            db.persist(p);
            db.persist(p1);
            
            
			
			db.persist(q1);
			db.persist(q2);
			db.persist(q3);
			db.persist(q4);
			db.persist(q5);
			db.persist(q6); 
	
	        
			db.persist(ev1);
			db.persist(ev2);
			db.persist(ev3);
			db.persist(ev4);
			db.persist(ev5);
			db.persist(ev6);
			db.persist(ev7);
			db.persist(ev8);
			db.persist(ev9);
			db.persist(ev10);
			db.persist(ev11);
			db.persist(ev12);
			db.persist(ev13);
			db.persist(ev14);
			db.persist(ev15);
			db.persist(ev16);
			db.persist(ev17);
			db.persist(ev18);
			db.persist(ev19);
			db.persist(ev20);	
			db.persist(a1);
			db.persist(l1);
			db.persist(e1);
            db.persist(e2);

			
			db.getTransaction().commit();
			System.out.println("Db initialized");
		}
		catch (Exception e){
			e.printStackTrace();
		}
	}
	
	/**
	 * This method creates a question for an event, with a question text and the minimum bet
	 * 
	 * @param event to which question is added
	 * @param question text of the question
	 * @param betMinimum minimum quantity of the bet
	 * @return the created question, or null, or an exception
 	 * @throws QuestionAlreadyExist if the same question already exists for the event
	 */
	public Question createQuestion(Event event, String question, float betMinimum) throws  QuestionAlreadyExist {
		System.out.println(">> DataAccess: createQuestion=> event= "+event+" question= "+question+" betMinimum="+betMinimum);
		
			Event ev = db.find(Event.class, event.getEventNumber());
			
			if (ev.DoesQuestionExists(question)) throw new QuestionAlreadyExist(ResourceBundle.getBundle("Etiquetas").getString("ErrorQueryAlreadyExist"));
			
			db.getTransaction().begin();
			Question q = ev.addQuestion(question, betMinimum);
			//db.persist(q);
			db.persist(ev); // db.persist(q) not required when CascadeType.PERSIST is added in questions property of Event class
							// @OneToMany(fetch=FetchType.EAGER, cascade=CascadeType.PERSIST)
			db.getTransaction().commit();
			return q;
		
	}
	
	/**
	 * This method retrieves from the database the events of a given date 
	 * 
	 * @param date in which events are retrieved
	 * @return collection of events
	 */
	public Vector<Event> getEvents(Date date) {
		System.out.println(">> DataAccess: getEvents");
		Vector<Event> res = new Vector<Event>();	
		TypedQuery<Event> query = db.createQuery("SELECT ev FROM Event ev WHERE ev.eventDate=?1",Event.class);   
		query.setParameter(1, date);
		List<Event> events = query.getResultList();
	 	 for (Event ev:events){
	 	   System.out.println(ev.toString());		 
		   res.add(ev);
		  }
	 	return res;
	}
	
	/**
	 * This method retrieves from the database the dates a month for which there are events
	 * 
	 * @param date of the month for which days with events want to be retrieved 
	 * @return collection of dates
	 */
	public Vector<Date> getEventsMonth(Date date) {
		System.out.println(">> DataAccess: getEventsMonth");
		Vector<Date> res = new Vector<Date>();	
		
		Date firstDayMonthDate= UtilDate.firstDayMonth(date);
		Date lastDayMonthDate= UtilDate.lastDayMonth(date);
				
		
		TypedQuery<Date> query = db.createQuery("SELECT DISTINCT ev.eventDate FROM Event ev WHERE ev.eventDate BETWEEN ?1 and ?2",Date.class);   
		query.setParameter(1, firstDayMonthDate);
		query.setParameter(2, lastDayMonthDate);
		List<Date> dates = query.getResultList();
	 	 for (Date d:dates){
	 	   System.out.println(d.toString());		 
		   res.add(d);
		  }
	 	return res;
	}
	

public void open(boolean initializeMode){
		
		System.out.println("Opening DataAccess instance => isDatabaseLocal: "+c.isDatabaseLocal()+" getDatabBaseOpenMode: "+c.getDataBaseOpenMode());

		String fileName=c.getDbFilename();
		if (initializeMode) {
			fileName=fileName+";drop";
			System.out.println("Deleting the DataBase");
		}
		
		if (c.isDatabaseLocal()) {
			  emf = Persistence.createEntityManagerFactory("objectdb:"+fileName);
			  db = emf.createEntityManager();
		} else {
			Map<String, String> properties = new HashMap<String, String>();
			  properties.put("javax.persistence.jdbc.user", c.getUser());
			  properties.put("javax.persistence.jdbc.password", c.getPassword());

			  emf = Persistence.createEntityManagerFactory("objectdb://"+c.getDatabaseNode()+":"+c.getDatabasePort()+"/"+fileName, properties);

			  db = emf.createEntityManager();
    	   }
		
	}
	public boolean existQuestion(Event event, String question) {
		System.out.println(">> DataAccess: existQuestion=> event= "+event+" question= "+question);
		Event ev = db.find(Event.class, event.getEventNumber());
		return ev.DoesQuestionExists(question);
	}

	public Erabiltzailea login(String erab, String pas) throws PasahitzaOkerra, ErabiltzaileaEzDago{

	Erabiltzailea er = db.find(Erabiltzailea.class, erab);

	if (er!=null) {
		System.out.println(pas);
		System.out.println(er.getPasahitza());
		if(er.getPasahitza().compareTo(pas)==0) {
			return er;
		}
		else {
			throw new PasahitzaOkerra();
		}
	}else {
		throw new ErabiltzaileaEzDago();
	}
	}
	
	public Erabiltzailea erregistratu(String erab, String pas,String izena, String abizena, String posta,
			Date jaiotzeData) throws PasahitzaOkerra, ErabiltzaileaBadago, AdinTxikikoa {
		Erabiltzailea er = db.find(Erabiltzailea.class, erab);

		if (er==null) {
			
				Calendar today = Calendar.getInstance();
				   
				   int day=today.get(Calendar.DAY_OF_MONTH);
				   int month=today.get(Calendar.MONTH);
				   month+=1;
				   int year=today.get(Calendar.YEAR)-18;	
				
				   System.out.println(UtilDate.newDate(year, month, day));
				   
				if (jaiotzeData.before(UtilDate.newDate(year, month, day))) {
					db.getTransaction().begin();
					Erregistratua x = new Erregistratua(erab, pas, izena, abizena, posta, jaiotzeData);
					db.persist(x);
					db.getTransaction().commit();
					return new Erregistratua(erab, pas, izena, abizena, posta, jaiotzeData);
				}else {
					throw new AdinTxikikoa();
				}
		}else {
			 throw new ErabiltzaileaBadago();
		}	
	}

	public void close(){
		db.close();
		System.out.println("DataBase closed");
	}

	public Event createEvent(String event, Date date) throws EventAlreadyExists {
		
		for (Event e:this.getEvents(date)){
			if (e.getDescription().compareTo(event)==0)
				throw new EventAlreadyExists();
		}		
		
		db.getTransaction().begin();
		Event ev = new Event(event, date);
		db.persist(ev);
		db.getTransaction().commit();
		return ev;
	}

	public void diruaSartu(Erabiltzailea erabiltzailea, int kop) throws DiruNegatiboa{

        if(kop>0) {
            db.getTransaction().begin();
            Mugimendua m1=new Mugimendua(3,kop);
            Erregistratua erab = (Erregistratua) db.find(Erabiltzailea.class, erabiltzailea.getErabiltzailea());
            erab.setDirua(erab.getDirua()+kop);
            erab.addMugimendua(m1);
            db.persist(m1);
            db.persist(erab);
            System.out.println(erab.getDirua());
            db.getTransaction().commit();

        }else {
            throw new DiruNegatiboa();
        }
    }

	public void diruaAtera(Erabiltzailea erabiltzailea, int kop) throws DiruNegatiboa, DiruFalta {
        db.getTransaction().begin();
        Erregistratua erab = (Erregistratua) db.find(Erabiltzailea.class, erabiltzailea.getErabiltzailea());
        if(kop>0) {
            if(erab.getDirua()>=kop) {
            Mugimendua m1=new Mugimendua(4,kop);
            erab.setDirua(erab.getDirua()-kop);
            erab.addMugimendua(m1);
            db.persist(m1);
            db.persist(erab);
            System.out.println(erab.getDirua());
            db.getTransaction().commit();

        }else {
            throw new DiruFalta();
        }
        }else {
            throw new DiruNegatiboa();
        }
    }

	public Pronostikoa createPronostikoa(String pronostikoa, Double kuota, Question q) throws PronostikoaAlreadyExists{
        Question q1= db.find(Question.class, q.getQuestionNumber());
        
		for (int i=0; i<q1.getProList().size(); i++){
            if (q1.getProList().get(i).getpIzena().equals(pronostikoa))
                throw new PronostikoaAlreadyExists();
        }
		
            db.getTransaction().begin();
            Pronostikoa p = new Pronostikoa(q, pronostikoa, kuota);
            q1.addPronostikoa(p);
            db.getTransaction().commit();
            return p;
    }

	public void createApostua(Double dirua, ArrayList<Pronostikoa> unekoPronostikoa, Erregistratua e, Question q,boolean originala) throws DiruNegatiboa, DiruFalta {

		Erregistratua e1= db.find(Erregistratua.class, e.getErabiltzailea());
		Apostua a=null;
		if (dirua>e1.getDirua() && originala) throw new DiruFalta();
		//Bakoitzean ea diru nahikoa dugun ikusteko foreach-a
		for (Pronostikoa p : unekoPronostikoa) {		
			Pronostikoa p1= db.find(Pronostikoa.class, p.getId());
		}
		
		db.getTransaction().begin();
		if(originala) {
			 a = new Apostua(dirua, unekoPronostikoa, e1);
			 
			 for (Pronostikoa p : unekoPronostikoa) {					
					Pronostikoa p1= db.find(Pronostikoa.class, p.getId());					
			        p1.addApostua(a);
			        db.persist(p1);				
				}		
				//Mugimendua sortu eta persist egin
		        Mugimendua m1=new Mugimendua(1,dirua);
		        e1.addMugimendua(m1);
		        db.persist(m1);
		        
		        db.persist(a);
		        e1.setDirua(e1.getDirua()-dirua);
		        e1.addApostua(a);
		        db.persist(e1);

		        db.getTransaction().commit();
		        for(Erregistratua erreg: e1.getJarraitzaileak()) {
					createApostua(dirua*erreg.getEhunekoa()/100,unekoPronostikoa,erreg,q,false);
					
				}
		}else if(e1.getDirua()>=dirua*e1.getEhunekoa()/100) {
			 a = new Apostua(dirua, unekoPronostikoa, e1,e1.getJarraitzen());			 
			 for (Pronostikoa p : unekoPronostikoa) {				
					Pronostikoa p1= db.find(Pronostikoa.class, p.getId());					
			        p1.addApostua(a);
			        db.persist(p1);					
				}
				
				//Mugimendua sortu eta persist egin
		        Mugimendua m1=new Mugimendua(1,dirua);
		        e1.addMugimendua(m1);
		        db.persist(m1);
		        
		        db.persist(a);
		        e1.setDirua(e1.getDirua()-dirua);
		        e1.addApostua(a);
		        db.persist(e1);

		        db.getTransaction().commit();
		        for(Erregistratua erreg: e1.getJarraitzaileak()) {
					createApostua(dirua*erreg.getEhunekoa()/100,unekoPronostikoa,erreg,q,false);					
				}
		}

	}

	public void emaitzakIpini(Pronostikoa p) {
		
        Pronostikoa p1= db.find(Pronostikoa.class, p.getId());
        db.getTransaction().begin();
        Mugimendua m1;
        Mugimendua m2;
        Erregistratua e1;
        int trueDirenak=0;
        double kuotaGuztira=1;
        p1.setAsmatua(true);
        db.persist(p1);
        for (Apostua apostu : p1.getEgindakoApostuak()) {
            e1=db.find(Erregistratua.class, apostu.getErregistratua().getErabiltzailea());
            Apostua apostu1=db.find(Apostua.class, apostu.getApostuID());
            
            System.out.println(apostu1.getPronostikoa().size());
            for (Pronostikoa p2 : apostu1.getPronostikoa()) {
                if(p2.isAsmatua()) {
                    trueDirenak++;
                    kuotaGuztira=kuotaGuztira*p2.getKuota();
                }
            }
            if(trueDirenak==apostu.getPronostikoa().size()) {
            	if(apostu1.getOriginala()) {
            		m1=new Mugimendua(2,apostu1.getDirua()*kuotaGuztira);
            		e1.setDirua(e1.getDirua()+apostu1.getDirua()*kuotaGuztira);
            		e1.addMugimendua(m1);
            		db.persist(m1);
            		db.persist(e1);
            	}else {
            		Erregistratua jabea =db.find(Erregistratua.class, apostu.getErregistratua().getJarraitzen().getErabiltzailea());
            		m1=new Mugimendua(2,apostu1.getDirua()*kuotaGuztira*0.95);
            		m2=new Mugimendua(6,apostu1.getDirua()*kuotaGuztira*0.05);
            		e1.setDirua(e1.getDirua()+apostu1.getDirua()*kuotaGuztira*0.95);
            		e1.addMugimendua(m1);
            		jabea.setDirua(jabea.getDirua()+apostu1.getDirua()*kuotaGuztira*0.05);
            		jabea.addMugimendua(m2);
            		db.persist(m1);
            		db.persist(e1);
            		db.persist(m2);
            		db.persist(jabea);
            	}
                
            }
            trueDirenak=0;
            kuotaGuztira=1;
        }
        db.getTransaction().commit();
	}
	public Erabiltzailea getErabiltzailea(String erabiltzailea) {
        Erabiltzailea erabiltzaile = db.find(Erabiltzailea.class, erabiltzailea);
        return erabiltzaile;
    }

	public void gertaeraEzabatu(Event ev) {
		
		Event ev1 = db.find(Event.class, ev.getEventNumber());
		
		db.getTransaction().begin();
		Mugimendua m1=null;
		for (Question q : ev1.getQuestions()) {
			for (Pronostikoa p : q.getProList()) {
					for (Apostua a : p.getEgindakoApostuak()) {
						if (!a.getTratatua()) {
							m1=new Mugimendua(5,a.getDirua());
							a.getErregistratua().setDirua(a.getErregistratua().getDirua()+a.getDirua());
							a.getErregistratua().addMugimendua(m1);
							db.persist(m1);
							a.setTratatua(true);
						}
						
					}
				}
			
		}
		db.remove(ev1);
		db.getTransaction().commit();
	}
	
	public Erregistratua erabiltzaileaEguneratuErreg(Erregistratua erab) {
        Erregistratua er = (Erregistratua) db.find(Erabiltzailea.class, erab);
        return er;
    }

    public Erabiltzailea erabiltzaileaEguneratu(Erabiltzailea erabiltzailea) {
        Erabiltzailea er = (Erregistratua) db.find(Erabiltzailea.class, erabiltzailea);
        return er;
    }

	public Erabiltzailea gehituDirua(double dirua, Erregistratua erabiltzailea) {
		
		db.getTransaction().begin();
		Erabiltzailea er = (Erregistratua) db.find(Erabiltzailea.class, erabiltzailea);
		er.setDirua(er.getDirua()+dirua);
		db.persist(er);
		db.getTransaction().commit();
		return er;
	}


	public Erabiltzailea diruaGaldu(double dirua, Erregistratua erabiltzailea) throws DiruNegatiboa,DiruFalta{
		if(dirua<0) throw new DiruNegatiboa();
		Erabiltzailea er = (Erregistratua) db.find(Erabiltzailea.class, erabiltzailea);
		if(dirua>er.getDirua()) throw new DiruFalta();
		db.getTransaction().begin();		
		er.setDirua(er.getDirua()-dirua);	
		db.persist(er);
		db.getTransaction().commit();
		return er;
	}

	public void jarraitu(String erab, Erregistratua erabiltzailea, int ehunekoa) throws ErabiltzaileaEzDago, DiruNegatiboa{
        Erregistratua jarraitzailea = (Erregistratua) db.find(Erabiltzailea.class, erabiltzailea.getErabiltzailea());
        Erregistratua  jarraitua= (Erregistratua) db.find(Erabiltzailea.class, erab);
        if(jarraitua==null) throw new ErabiltzaileaEzDago();
        if(ehunekoa<0) throw new DiruNegatiboa();
        db.getTransaction().begin();
        jarraitua.addJarraitzailea(jarraitzailea);
        jarraitzailea.setEhunekoa(ehunekoa);
        jarraitzailea.setJarraitzen(jarraitua);
        db.persist(jarraitzailea);
        db.persist(jarraitua);
        db.getTransaction().commit();
    }

    public void ezJarraitu(Erregistratua erabiltzailea) {
        Erregistratua jarraitzailea = (Erregistratua) db.find(Erabiltzailea.class, erabiltzailea.getErabiltzailea());
        Erregistratua jarraitua=(Erregistratua) db.find(Erabiltzailea.class, erabiltzailea.getJarraitzen().getErabiltzailea());
        db.getTransaction().begin();
        for(int i=0;i<jarraitua.getJarraitzaileak().size();i++) {
            if(jarraitua.getJarraitzaileak().get(i).equals(jarraitzailea)) {
                jarraitua.getJarraitzaileak().remove(i);
            }
        }
        jarraitzailea.setJarraitzen(null);
        jarraitzailea.setEhunekoa(0);
        db.persist(jarraitzailea);
        db.persist(jarraitua);
        db.getTransaction().commit();

    }
    
}
