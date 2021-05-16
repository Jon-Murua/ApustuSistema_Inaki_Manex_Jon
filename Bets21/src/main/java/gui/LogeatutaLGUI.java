package gui;

import javax.swing.JFrame;
import javax.swing.JLabel;

import domain.Erabiltzailea;
import domain.Event;

import javax.swing.JButton;
import java.awt.event.ActionListener;
import java.util.ResourceBundle;
import java.util.Vector;
import java.awt.event.ActionEvent;

public class LogeatutaLGUI extends JFrame {
	JFrame logeatuta =this;

	public LogeatutaLGUI(JFrame main, Erabiltzailea erabiltzailea) {
		this.setSize(743, 435);
		getContentPane().setLayout(null);
		

		
		JButton createEventBtn = new JButton("Gertaera sortu");
		createEventBtn.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				JFrame a = new CreateEventGUI(logeatuta);
				a.setVisible(true);
				logeatuta.setVisible(false);
			}
		});
		createEventBtn.setBounds(90, 63, 184, 118);
		getContentPane().add(createEventBtn);
		
		JButton btnNewButton = new JButton("Saioa itxi");
		btnNewButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				JFrame a = new LoginGUI(logeatuta);
				a.setVisible(true);
				logeatuta.setVisible(false);
			}
		});
		btnNewButton.setBounds(27, 29, 89, 23);
		getContentPane().add(btnNewButton);
		
		JButton createPronostikoa = new JButton("Pronostikoak sortu");
        createPronostikoa.addActionListener(new ActionListener() {
                    public void actionPerformed(ActionEvent arg0) {
                        JFrame b = new CreatePronostikoaGUI(logeatuta);
                        b.setVisible(true);
                        logeatuta.setVisible(false);
            }
        });
        createPronostikoa.setBounds(356, 63, 178, 116);
        getContentPane().add(createPronostikoa);
        
        JButton btnNewButton_1 = new JButton("Emaitzak ipini");
        btnNewButton_1.addActionListener(new ActionListener() {
        	public void actionPerformed(ActionEvent arg0) {
        		JFrame b = new EmaitzakIpiniGUI(logeatuta);
                b.setVisible(true);
                logeatuta.setVisible(false);
        	}
        });
        btnNewButton_1.setBounds(91, 223, 187, 108);
        getContentPane().add(btnNewButton_1);
        
        JButton btnNewButton_2 = new JButton("Galderak sortu");
        btnNewButton_2.addActionListener(new ActionListener() {
        	public void actionPerformed(ActionEvent e) {
        		
        		JFrame b = new CreateQuestionGUI(new Vector<Event>(), logeatuta);
                b.setVisible(true);
                logeatuta.setVisible(false);
        	}
        });
        btnNewButton_2.setBounds(345, 227, 189, 104);
        getContentPane().add(btnNewButton_2);
		
	}
}

