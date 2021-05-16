package gui;

import javax.swing.JFrame;

import domain.Erabiltzailea;
import domain.Erregistratua;
import javax.swing.JTextField;

import businessLogic.BLFacade;

import javax.swing.JButton;
import javax.swing.JLabel;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;

public class JarraitzenEGUI extends JFrame {
	private JFrame a=this;

	public JarraitzenEGUI(JFrame logeatuta, Erregistratua erabiltzailea) {
		this.setSize(743, 435);
		getContentPane().setLayout(null);
		
		
		JButton btnNewButton_1 = new JButton("Jarraitzeaz utzi");
		btnNewButton_1.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				 BLFacade facade = MainGUI.getBusinessLogic();
				 facade.ezJarraitu(erabiltzailea);
				 Erabiltzailea er = facade.erabiltzaileaEguneratu(erabiltzailea);
	             JFrame log = new LogeatutaEGUI(null, (Erregistratua) er);
				a.setVisible(false);
				log.setVisible(true);
				 
			}
		});
		btnNewButton_1.setBounds(162, 191, 158, 23);
		getContentPane().add(btnNewButton_1);
		
		JLabel lblNewLabel = new JLabel("Jarraitzen duzun erabiltzailea");
		lblNewLabel.setBounds(29, 93, 190, 14);
		getContentPane().add(lblNewLabel);
		
	
		JButton btnNewButton_2 = new JButton("Atzera");
		btnNewButton_2.setBounds(22, 191, 89, 23);
		getContentPane().add(btnNewButton_2);
			
		JLabel lblNewLabel_1 = new JLabel(erabiltzailea.getJarraitzen().getErabiltzailea());
		lblNewLabel_1.setBounds(232, 93, 124, 14);
		getContentPane().add(lblNewLabel_1);
		btnNewButton_2.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				BLFacade facade = MainGUI.getBusinessLogic();
	             Erabiltzailea er = facade.erabiltzaileaEguneratu(erabiltzailea);
	             JFrame log = new LogeatutaEGUI(null, (Erregistratua) er);
				a.setVisible(false);
				log.setVisible(true);
			}
		});
	}

}
