package businessLogic;

import java.util.ArrayList;
import java.util.Date;
import java.util.ResourceBundle;
import java.util.Vector;

import javax.jws.WebMethod;
import javax.jws.WebService;

import configuration.ConfigXML;
import dataAccess.DataAccess;
import domain.Question;
import domain.Erabiltzailea;
import domain.Erregistratua;
import domain.Event;
import domain.Pronostikoa;
import exceptions.AdinTxikikoa;
import exceptions.DiruFalta;
import exceptions.DiruNegatiboa;
import exceptions.ErabiltzaileaBadago;
import exceptions.ErabiltzaileaEzDago;
import exceptions.EventAlreadyExists;
import exceptions.EventFinished;
import exceptions.PasahitzaOkerra;
import exceptions.PronostikoaAlreadyExists;
import exceptions.QuestionAlreadyExist;

/**
 * It implements the business logic as a web service.
 */
@WebService(endpointInterface = "businessLogic.BLFacade")
public class BLFacadeImplementation  implements BLFacade {
	DataAccess dbManager;

	public BLFacadeImplementation()  {		
		System.out.println("Creating BLFacadeImplementation instance");
		ConfigXML c=ConfigXML.getInstance();
		
		if (c.getDataBaseOpenMode().equals("initialize")) {
		    dbManager=new DataAccess(c.getDataBaseOpenMode().equals("initialize"));
			dbManager.initializeDB();
			dbManager.close();
			}
		
	}
	
    public BLFacadeImplementation(DataAccess da)  {
		
		System.out.println("Creating BLFacadeImplementation instance with DataAccess parameter");
		ConfigXML c=ConfigXML.getInstance();
		
		if (c.getDataBaseOpenMode().equals("initialize")) {
			da.open(true);
			da.initializeDB();
			da.close();

		}
		dbManager=da;		
	}
	

	/**
	 * This method creates a question for an event, with a question text and the minimum bet
	 * 
	 * @param event to which question is added
	 * @param question text of the question
	 * @param betMinimum minimum quantity of the bet
	 * @return the created question, or null, or an exception
	 * @throws EventFinished if current data is after data of the event
 	 * @throws QuestionAlreadyExist if the same question already exists for the event
	 */
   @WebMethod
   public Question createQuestion(Event event, String question, float betMinimum) throws EventFinished, QuestionAlreadyExist{
	   
	    //The minimum bed must be greater than 0
		dbManager.open(false);
		Question qry=null;
		
	    
		if(new Date().compareTo(event.getEventDate())>0)
			throw new EventFinished(ResourceBundle.getBundle("Etiquetas").getString("ErrorEventHasFinished"));
				
		
		 qry=dbManager.createQuestion(event,question,betMinimum);		

		dbManager.close();
		
		return qry;
   };
	
	/**
	 * This method invokes the data access to retrieve the events of a given date 
	 * 
	 * @param date in which events are retrieved
	 * @return collection of events
	 */
    @WebMethod	
	public Vector<Event> getEvents(Date date)  {
		dbManager.open(false);
		Vector<Event>  events=dbManager.getEvents(date);
		dbManager.close();
		return events;
	}

    @WebMethod
    public Erabiltzailea login(String erab, String pas) throws PasahitzaOkerra, ErabiltzaileaEzDago {
    dbManager.open(false);
    Erabiltzailea er = dbManager.login(erab, pas);
    dbManager.close();
    return er;
    }
    
	@WebMethod
	public Erabiltzailea erregistratu(String erab, String pas, String izena, String abizena, String posta,
			Date jaiotzeData) throws PasahitzaOkerra, ErabiltzaileaBadago, AdinTxikikoa {
		dbManager.open(false);
		Erabiltzailea er = dbManager.erregistratu(erab, pas, izena, abizena, posta, jaiotzeData);
	    dbManager.close();
	    return er;
	}

    
	/**
	 * This method invokes the data access to retrieve the dates a month for which there are events
	 * 
	 * @param date of the month for which days with events want to be retrieved 
	 * @return collection of dates
	 */
	@WebMethod public Vector<Date> getEventsMonth(Date date) {
		dbManager.open(false);
		Vector<Date>  dates=dbManager.getEventsMonth(date);
		dbManager.close();
		return dates;
	}
	
	
	public void close() {
		DataAccess dB4oManager=new DataAccess(false);

		dB4oManager.close();

	}

	/**
	 * This method invokes the data access to initialize the database with some events and questions.
	 * It is invoked only when the option "initialize" is declared in the tag dataBaseOpenMode of resources/config.xml file
	 */	
    @WebMethod	
	 public void initializeBD(){
    	dbManager.open(false);
		dbManager.initializeDB();
		dbManager.close();
	}

	@Override
	public Event createEvent(String event, Date date) throws EventAlreadyExists {
		dbManager.open(false);
		Event ev= dbManager.createEvent(event, date);
		dbManager.close();
		return ev;
	}

	@Override
	public void diruaSartu(Erabiltzailea erabiltzailea, int kop) throws DiruNegatiboa {
		dbManager.open(false);
        dbManager.diruaSartu(erabiltzailea, kop);
        dbManager.close();		
	}

	@Override
	public void diruaAtera(Erabiltzailea erabiltzailea, int kop) throws DiruNegatiboa, DiruFalta {
        dbManager.open(false);
        dbManager.diruaAtera(erabiltzailea, kop);
        dbManager.close();		
	}

	@Override
    public Pronostikoa createPronostikoa(String pronostikoa, Double kuota, Question q) throws PronostikoaAlreadyExists {
        dbManager.open(false);
        Pronostikoa p= dbManager.createPronostikoa(pronostikoa, kuota, q);
        dbManager.close();
        return p;

    }

	@Override
    public void createApostua(Double dirua,ArrayList<Pronostikoa> unekoPronostikoa, Erregistratua e, Question q, boolean originala) throws DiruNegatiboa, DiruFalta {
        dbManager.open(false);
        dbManager.createApostua(dirua, unekoPronostikoa, e, q, originala);
        dbManager.close();
    }

	@Override
	public void emaitzakIpini(Pronostikoa p) {
		dbManager.open(false);
        dbManager.emaitzakIpini(p);
        dbManager.close();
	}

	@Override
	public Erabiltzailea getErabiltzailea(String erabiltzailea) {
        dbManager.open(false);
        Erabiltzailea erabiltzaileaDB = dbManager.getErabiltzailea(erabiltzailea);
        dbManager.close();
        return erabiltzaileaDB;
    }

	@Override
	public void gertaeraEzabatu(Event ev) {
		 dbManager.open(false);
	        dbManager.gertaeraEzabatu(ev);
	     dbManager.close();		
	}
	
	@Override
    public Erabiltzailea erabiltzaileaEguneratu(Erabiltzailea erabiltzailea) {
        dbManager.open(false);
        Erabiltzailea er = dbManager.erabiltzaileaEguneratu(erabiltzailea);
        dbManager.close();
        return er;
    }

	@Override
    public Erregistratua erabiltzaileaEguneratuErreg(Erregistratua erab) {
        dbManager.open(false);
        Erregistratua erabiltzailea = dbManager.erabiltzaileaEguneratuErreg(erab);
        dbManager.close();
        return erabiltzailea;
    }

	@Override
	public Erabiltzailea gehituDirua(double dirua, Erregistratua erabiltzailea) {
		dbManager.open(false);
		Erabiltzailea e=dbManager.gehituDirua(dirua, erabiltzailea);
        dbManager.close();
        return e;
	}

	@Override
	public Erabiltzailea diruaGaldu(double dirua, Erregistratua erabiltzailea) throws DiruNegatiboa,DiruFalta{
		dbManager.open(false);
        Erabiltzailea e= dbManager.diruaGaldu(dirua, erabiltzailea);
        dbManager.close();
        return e;
	}
	
	@Override
    public void jarraitu(String erab, Erregistratua erabiltzailea,int ehunekoa) throws ErabiltzaileaEzDago, DiruNegatiboa {
        dbManager.open(false);
        dbManager.jarraitu(erab, erabiltzailea,ehunekoa);
        dbManager.close();

    }

    @Override
    public void ezJarraitu(Erregistratua erabiltzailea) {
        dbManager.open(false);
        dbManager.ezJarraitu(erabiltzailea);
        dbManager.close();

    }
	
}

