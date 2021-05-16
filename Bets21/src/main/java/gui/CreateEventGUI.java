package gui;

import java.text.DateFormat;
import java.util.*;

import javax.swing.*;

import com.toedter.calendar.JCalendar;

import java.awt.*;
import java.awt.event.*;
import java.beans.PropertyChangeEvent;
import java.beans.PropertyChangeListener;

import businessLogic.BLFacade;
import configuration.UtilDate;
import domain.Event;
import exceptions.EventAlreadyExists;
import exceptions.EventFinished;
import exceptions.QuestionAlreadyExist;


public class CreateEventGUI extends JFrame {
	private JCalendar jCalendar = new JCalendar();
	private Calendar calendarAct = null;
	private Calendar calendarAnt = null;
	JFrame eventuaSortu = this;
	private JTextField txtIdatziEventua;
	
	public CreateEventGUI(JFrame logeatuta) {
		this.setSize(743, 435);
		getContentPane().setLayout(null);
		getContentPane().add(jCalendar);
		jCalendar.setBounds(new Rectangle(67, 35, 292, 181));
		
		JButton createEventBtn = new JButton("Gertaera sortu");
		createEventBtn.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				
				BLFacade facade = MainGUI.getBusinessLogic();

				String event = txtIdatziEventua.getText();
				Date date = UtilDate.trim(calendarAct.getTime());
				try {
					facade.createEvent(event, date);
					txtIdatziEventua.setText("Eventua ondo gehitu da!");
				} catch (EventAlreadyExists e1) {
					txtIdatziEventua.setText("Eventua existitzen da!");
				}
				
			}
		});
		createEventBtn.setBounds(266, 227, 158, 23);
		getContentPane().add(createEventBtn);
		
		JButton btnNewButton = new JButton("Atzera");
		btnNewButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				eventuaSortu.setVisible(false);
				logeatuta.setVisible(true);
			}
		});
		btnNewButton.setBounds(10, 11, 89, 23);
		getContentPane().add(btnNewButton);
		
		txtIdatziEventua = new JTextField();
		txtIdatziEventua.setText("Idatzi gertaera");
		txtIdatziEventua.setBounds(49, 228, 173, 20);
		getContentPane().add(txtIdatziEventua);
		txtIdatziEventua.setColumns(10);
		
		this.jCalendar.addPropertyChangeListener(new PropertyChangeListener() {
			public void propertyChange(PropertyChangeEvent propertychangeevent) {
//				this.jCalendar.addPropertyChangeListener(new PropertyChangeListener() {
//					public void propertyChange(PropertyChangeEvent propertychangeevent) {
				if (propertychangeevent.getPropertyName().equals("locale")) {
					jCalendar.setLocale((Locale) propertychangeevent.getNewValue());
				} else if (propertychangeevent.getPropertyName().equals("calendar")) {
					calendarAnt = (Calendar) propertychangeevent.getOldValue();
					calendarAct = (Calendar) propertychangeevent.getNewValue();
					System.out.println("calendarAnt: "+calendarAnt.getTime());
					System.out.println("calendarAct: "+calendarAct.getTime());
					DateFormat dateformat1 = DateFormat.getDateInstance(1, jCalendar.getLocale());
					
					int monthAnt = calendarAnt.get(Calendar.MONTH);
					int monthAct = calendarAct.get(Calendar.MONTH);
					if (monthAct!=monthAnt) {
						if (monthAct==monthAnt+2) { 
							// Si en JCalendar estÃ¡ 30 de enero y se avanza al mes siguiente, devolverÃ­a 2 de marzo (se toma como equivalente a 30 de febrero)
							// Con este cÃ³digo se dejarÃ¡ como 1 de febrero en el JCalendar
							calendarAct.set(Calendar.MONTH, monthAnt+1);
							calendarAct.set(Calendar.DAY_OF_MONTH, 1);
						}
						
						jCalendar.setCalendar(calendarAct);
						
						BLFacade facade = MainGUI.getBusinessLogic();

					}
					//	Date firstDay = UtilDate.trim(new Date(jCalendar.getCalendar().getTime().getTime()));
					Date firstDay = UtilDate.trim(calendarAct.getTime());

					

				}
			}
		});
	}
}
