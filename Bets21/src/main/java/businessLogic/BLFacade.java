package businessLogic;

import java.util.Vector;
import java.util.ArrayList;
import java.util.Date;





//import domain.Booking;
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

import javax.jws.WebMethod;
import javax.jws.WebService;

/**
 * Interface that specifies the business logic.
 */
@WebService
public interface BLFacade  {
	  

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
	@WebMethod Question createQuestion(Event event, String question, float betMinimum) throws EventFinished, QuestionAlreadyExist;
	
	
	/**
	 * This method retrieves the events of a given date 
	 * 
	 * @param date in which events are retrieved
	 * @return collection of events
	 */
	@WebMethod public Vector<Event> getEvents(Date date);
	
	/**
	 * This method retrieves from the database the dates a month for which there are events
	 * 
	 * @param date of the month for which days with events want to be retrieved 
	 * @return collection of dates
	 */
	@WebMethod public Vector<Date> getEventsMonth(Date date);
	
	/**
	 * This method calls the data access to initialize the database with some events and questions.
	 * It is invoked only when the option "initialize" is declared in the tag dataBaseOpenMode of resources/config.xml file
	 */	
	@WebMethod public void initializeBD();
	
	@WebMethod public Erabiltzailea login(String erab, String pas) throws PasahitzaOkerra, ErabiltzaileaEzDago;
	
	@WebMethod public Erabiltzailea erregistratu(String erab, String pas, String pas2, String izena, String abizena, Date jaiotzeData) throws PasahitzaOkerra, ErabiltzaileaBadago, AdinTxikikoa;


	@WebMethod public Event createEvent(String event, Date date) throws EventAlreadyExists;
	
	@WebMethod public void diruaSartu(Erabiltzailea erabiltzailea, int kop) throws DiruNegatiboa;

    @WebMethod public void diruaAtera(Erabiltzailea erabiltzailea, int kop) throws DiruNegatiboa, DiruFalta;


	@WebMethod public Pronostikoa createPronostikoa(String pronostikoa, Double kuota, Question q) throws PronostikoaAlreadyExists;


	@WebMethod public void createApostua(Double dirua, ArrayList<Pronostikoa> unekoPronostikoa, Erregistratua erreg, Question q, boolean originala) throws DiruNegatiboa,DiruFalta;


	@WebMethod public void emaitzakIpini(Pronostikoa p);


	@WebMethod public Erabiltzailea getErabiltzailea(String erabiltzailea);


	@WebMethod public void gertaeraEzabatu(Event ev);


	@WebMethod public Erabiltzailea erabiltzaileaEguneratu(Erabiltzailea erabiltzailea);


	@WebMethod public Erregistratua erabiltzaileaEguneratuErreg(Erregistratua erab);


	@WebMethod public Erabiltzailea gehituDirua(double dirua, Erregistratua erabiltzailea);


	@WebMethod public Erabiltzailea diruaGaldu(double dirua, Erregistratua erabiltzailea) throws DiruNegatiboa,DiruFalta;


	@WebMethod public void jarraitu(String erab, Erregistratua erabiltzailea, int ehunekoa) throws ErabiltzaileaEzDago, DiruNegatiboa;


	@WebMethod public void ezJarraitu(Erregistratua erabiltzailea);

	
}
