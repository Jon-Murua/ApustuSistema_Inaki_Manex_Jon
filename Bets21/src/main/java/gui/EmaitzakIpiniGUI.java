
package gui;
import java.awt.Dimension;
import java.awt.Rectangle;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.beans.PropertyChangeEvent;
import java.beans.PropertyChangeListener;
import java.text.DateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.Locale;
import java.util.ResourceBundle;
import java.util.Vector;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.table.DefaultTableModel;

import com.toedter.calendar.JCalendar;

import businessLogic.BLFacade;
import configuration.UtilDate;
import domain.Erabiltzailea;
import domain.Erregistratua;
import domain.Langilea;
import domain.Pronostikoa;
import domain.Question;
import exceptions.DiruFalta;
import exceptions.DiruNegatiboa;
import exceptions.PronostikoaAlreadyExists;

import javax.swing.JTextField;

public class EmaitzakIpiniGUI extends JFrame {


	private static final long serialVersionUID = 1L;

	private final JLabel jLabelEventDate = new JLabel("Gertaeraren eguna");
	private final JLabel jLabelQueries = new JLabel("Galderak"); 
	private final JLabel jLabelEvents = new JLabel("Gertaerak"); 

	private JButton jButtonClose = new JButton("Atzera");

	// Code for JCalendar
	private JCalendar jCalendar1 = new JCalendar();
	private Calendar calendarAnt = null;
	private Calendar calendarAct = null;
	private JScrollPane scrollPaneEvents = new JScrollPane();
	private JScrollPane scrollPaneQueries = new JScrollPane();
	private JScrollPane scrollPane = new JScrollPane();
	
	private Vector<Date> datesWithEventsCurrentMonth = new Vector<Date>();

	private JTable tableEvents= new JTable();
	private JTable tableQueries = new JTable();
	private JTable tablePro = new JTable();

	private DefaultTableModel tableModelEvents;
	private DefaultTableModel tableModelQueries;
	private DefaultTableModel tableModelPro;

	private String[] columnNamesEvents = new String[] {
			"Zbkia","Gertaera"

	};
	private String[] columnNamesQueries = new String[] {
			"Zbkia","Galdera"

	};
	
	private JFrame logeatuta;
	private JFrame a = this;

	private domain.Question q;
	private domain.Pronostikoa p;
	private final JTextField textField_1 = new JTextField();
	private final JTable table = new JTable();

	private final JButton btnNewButton = new JButton(ResourceBundle.getBundle("Etiquetas").getString("EmaitzakIpiniGUI.btnNewButton.text")); //$NON-NLS-1$ //$NON-NLS-2$
	
	
	public EmaitzakIpiniGUI(JFrame logeatuta)
	{
		

		this.logeatuta=logeatuta;
		try
		{
			jbInit();
		}
		catch(Exception e)
		{
			e.printStackTrace();
		}
	}

	
	private void jbInit() throws Exception
	{

		this.getContentPane().setLayout(null);
		this.setSize(new Dimension(900, 500));
		this.setTitle(ResourceBundle.getBundle("Etiquetas").getString("QueryQueries"));

		jLabelEventDate.setBounds(new Rectangle(40, 15, 140, 25));
		jLabelQueries.setBounds(40, 215, 406, 14);
		jLabelEvents.setBounds(442, 19, 259, 16);
		textField_1.setBounds(641, 425, 140, 20);
		textField_1.setColumns(10);
		this.getContentPane().add(jLabelEventDate, null);
		this.getContentPane().add(jLabelQueries);
		this.getContentPane().add(jLabelEvents);

		jButtonClose.setBounds(new Rectangle(33, 398, 130, 30));

		jButtonClose.addActionListener(new ActionListener()
		{
			public void actionPerformed(ActionEvent e)
			{
				a.setVisible(false);
				logeatuta.setVisible(true);
			}
		});

		this.getContentPane().add(jButtonClose, null);


		jCalendar1.setBounds(new Rectangle(66, 51, 225, 150));

		BLFacade facade = MainGUI.getBusinessLogic();
		datesWithEventsCurrentMonth=facade.getEventsMonth(jCalendar1.getDate());
		CreateQuestionGUI.paintDaysWithEvents(jCalendar1,datesWithEventsCurrentMonth);

		// Code for JCalendar
		this.jCalendar1.addPropertyChangeListener(new PropertyChangeListener()
		{
			public void propertyChange(PropertyChangeEvent propertychangeevent)
			{

				if (propertychangeevent.getPropertyName().equals("locale"))
				{
					jCalendar1.setLocale((Locale) propertychangeevent.getNewValue());
				}
				else if (propertychangeevent.getPropertyName().equals("calendar"))
				{
					calendarAnt = (Calendar) propertychangeevent.getOldValue();
					calendarAct = (Calendar) propertychangeevent.getNewValue();
					DateFormat dateformat1 = DateFormat.getDateInstance(1, jCalendar1.getLocale());
//					jCalendar1.setCalendar(calendarAct);
					Date firstDay=UtilDate.trim(new Date(jCalendar1.getCalendar().getTime().getTime()));

					 
					
					int monthAnt = calendarAnt.get(Calendar.MONTH);
					int monthAct = calendarAct.get(Calendar.MONTH);
					
					if (monthAct!=monthAnt) {
						if (monthAct==monthAnt+2) {
							// Si en JCalendar está 30 de enero y se avanza al mes siguiente, devolvería 2 de marzo (se toma como equivalente a 30 de febrero)
							// Con este código se dejará como 1 de febrero en el JCalendar
							calendarAct.set(Calendar.MONTH, monthAnt+1);
							calendarAct.set(Calendar.DAY_OF_MONTH, 1);
						}						
						
						jCalendar1.setCalendar(calendarAct);

						BLFacade facade = MainGUI.getBusinessLogic();

						datesWithEventsCurrentMonth=facade.getEventsMonth(jCalendar1.getDate());
					}



					CreateQuestionGUI.paintDaysWithEvents(jCalendar1,datesWithEventsCurrentMonth);
													
					

					try {
						tableModelEvents.setDataVector(null, columnNamesEvents);
						tableModelEvents.setColumnCount(3); // another column added to allocate ev objects

						BLFacade facade=MainGUI.getBusinessLogic();

						Vector<domain.Event> events=facade.getEvents(firstDay);

						if (events.isEmpty() ) jLabelEvents.setText(ResourceBundle.getBundle("Etiquetas").getString("NoEvents")+ ": "+dateformat1.format(calendarAct.getTime()));
						else jLabelEvents.setText(ResourceBundle.getBundle("Etiquetas").getString("Events")+ ": "+dateformat1.format(calendarAct.getTime()));
						for (domain.Event ev:events){
							Vector<Object> row = new Vector<Object>();

							System.out.println("Events "+ev);

							row.add(ev.getEventNumber());
							row.add(ev.getDescription());
							row.add(ev); // ev object added in order to obtain it with tableModelEvents.getValueAt(i,2)
							tableModelEvents.addRow(row);		
						}
						tableEvents.getColumnModel().getColumn(0).setPreferredWidth(25);
						tableEvents.getColumnModel().getColumn(1).setPreferredWidth(268);
						tableEvents.getColumnModel().removeColumn(tableEvents.getColumnModel().getColumn(2)); // not shown in JTable
					} catch (Exception e1) {

						jLabelQueries.setText(e1.getMessage());
					}

				}
			} 
		});

		this.getContentPane().add(jCalendar1, null);
		
		scrollPaneEvents.setBounds(new Rectangle(442, 50, 346, 150));
		scrollPaneQueries.setBounds(new Rectangle(40, 240, 366, 89));
		scrollPane.setBounds(new Rectangle (442, 240, 366, 89));
		

		tableEvents.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent e) {
				int i=tableEvents.getSelectedRow();
				domain.Event ev=(domain.Event)tableModelEvents.getValueAt(i,2); // obtain ev object
				Vector<Question> queries=ev.getQuestions();

				tableModelQueries.setDataVector(null, columnNamesQueries);
				tableModelQueries.setColumnCount(3);

				if (queries.isEmpty())
					jLabelQueries.setText(ResourceBundle.getBundle("Etiquetas").getString("NoQueries")+": "+ev.getDescription());
				else 
					jLabelQueries.setText(ResourceBundle.getBundle("Etiquetas").getString("SelectedEvent")+" "+ev.getDescription());

				for (domain.Question q:queries){
					Vector<Object> row = new Vector<Object>();

					row.add(q.getQuestionNumber());
					row.add(q.getQuestion());
					row.add(q);
					tableModelQueries.addRow(row);	
				}
				tableQueries.getColumnModel().getColumn(0).setPreferredWidth(25);
				tableQueries.getColumnModel().getColumn(1).setPreferredWidth(268);
				tableQueries.getColumnModel().removeColumn(tableQueries.getColumnModel().getColumn(2));
			}
		});

		Vector<String> vec = new Vector<String>();
		vec.addElement("Kuota");
		vec.addElement("Pronostikoa");
		
		scrollPaneEvents.setViewportView(tableEvents);
		tableModelEvents = new DefaultTableModel(null, columnNamesEvents);

		tableEvents.setModel(tableModelEvents);
		tableEvents.getColumnModel().getColumn(0).setPreferredWidth(25);
		tableEvents.getColumnModel().getColumn(1).setPreferredWidth(268);
		
		
		tableQueries.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent e) {
				int i=tableQueries.getSelectedRow();
				System.out.println(i);
				q=(domain.Question)tableModelQueries.getValueAt(i,2); 
				System.out.println(q.getProList());
				Vector<Pronostikoa> pronostikoak = new Vector<Pronostikoa>(q.getProList());
				 

				tableModelPro.setDataVector(null, vec);
				tableModelPro.setColumnCount(3);

				if (pronostikoak.isEmpty())
					jLabelQueries.setText("Ez daude pronostikoak");
				else 
					jLabelQueries.setText("Galdera"+" "+q.toString());

				for (int j=0; j<pronostikoak.size(); j++){
					Vector<Object> row = new Vector<Object>();

					row.add(pronostikoak.get(j).getKuota());
					row.add(pronostikoak.get(j).getpIzena());
					row.add(pronostikoak.get(j));
					tableModelPro.addRow(row);	
				}
				tablePro.getColumnModel().getColumn(0).setPreferredWidth(25);
				tablePro.getColumnModel().getColumn(1).setPreferredWidth(268);
				tablePro.getColumnModel().removeColumn(tablePro.getColumnModel().getColumn(2));
				
			}
		});
		scrollPaneQueries.setViewportView(tableQueries);
		tableModelQueries = new DefaultTableModel(null, columnNamesQueries);
		
		tableQueries.setModel(tableModelQueries);
		tableQueries.getColumnModel().getColumn(0).setPreferredWidth(25);
		tableQueries.getColumnModel().getColumn(1).setPreferredWidth(268);
				
		
		
		tablePro.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent e) {
				int i=tablePro.getSelectedRow();
				System.out.println(i);
				p = (domain.Pronostikoa)tableModelPro.getValueAt(i,2); 
				tablePro.getColumnModel().getColumn(0).setPreferredWidth(25);
				tablePro.getColumnModel().getColumn(1).setPreferredWidth(268);
				
				
			}
		});	
		
		scrollPane.setViewportView(tablePro);
		tableModelPro = new DefaultTableModel(null, vec);
		
		tablePro.setModel(tableModelPro);
		tablePro.getColumnModel().getColumn(0).setPreferredWidth(25);
		tablePro.getColumnModel().getColumn(1).setPreferredWidth(268);
		

		this.getContentPane().add(scrollPaneEvents, null);
		this.getContentPane().add(scrollPaneQueries, null);
		this.getContentPane().add(scrollPane, null);
		
		JLabel lblNewLabel = new JLabel("Pronostikoak");  //$NON-NLS-1$ //$NON-NLS-2$
		lblNewLabel.setBounds(442, 215, 130, 14);
		getContentPane().add(lblNewLabel);
		
		getContentPane().add(textField_1);
		btnNewButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				
				facade.emaitzakIpini(p);
				
			}
		});
		btnNewButton.setBounds(226, 362, 366, 35);
		
		getContentPane().add(btnNewButton);

	}
}