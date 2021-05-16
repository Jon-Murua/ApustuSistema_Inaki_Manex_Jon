package gui;

import javax.swing.JFrame;
import javax.swing.JLabel;

import businessLogic.BLFacade;
import domain.Erabiltzailea;
import domain.Erregistratua;

import java.awt.BorderLayout;
import javax.swing.JButton;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;

public class LogeatutaEGUI extends JFrame {
	JFrame logeatuta =this;
	public LogeatutaEGUI(JFrame main, Erregistratua erabiltzailea) {
		this.setSize(743, 435);
		getContentPane().setLayout(null);
		
		JButton btnNewButton = new JButton("Saioa itxi");
		btnNewButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				JFrame a = new LoginGUI(logeatuta);
				a.setVisible(true);
				logeatuta.setVisible(false);
			}
		});
		btnNewButton.setBounds(31, 29, 89, 23);
		getContentPane().add(btnNewButton);
		
		JButton btnNewButton_1 = new JButton("Dirua Sartu");
		btnNewButton_1.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				 JFrame b= new DiruaSartuGUI(logeatuta,erabiltzailea);
	              b.setVisible(true);
	              logeatuta.setVisible(false);
			}
		});
		btnNewButton_1.setBounds(53, 71, 189, 136);
		getContentPane().add(btnNewButton_1);
		
		JButton btnNewButton_2 = new JButton("Dirua Atera");
		btnNewButton_2.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				 JFrame b= new DiruaAteraGUI(logeatuta,erabiltzailea);
	             b.setVisible(true);
	             logeatuta.setVisible(false);
			}
		});
		btnNewButton_2.setBounds(53, 226, 188, 131);
		getContentPane().add(btnNewButton_2);
		
		JButton btnNewButton_3 = new JButton("Apustua egin");
		btnNewButton_3.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				JFrame c= new ApustuEginGUI(logeatuta, erabiltzailea);
				c.setVisible(true);
				logeatuta.setVisible(false);
			}
		});
		btnNewButton_3.setBounds(272, 71, 201, 137);
		getContentPane().add(btnNewButton_3);
		
		JButton btnNewButton_4 = new JButton("Mugimenduak ikusi");
		btnNewButton_4.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				JFrame c= new MugimenduakGUI(logeatuta, erabiltzailea);
				c.setVisible(true);
				logeatuta.setVisible(false);
			}
		});
		btnNewButton_4.setBounds(272, 225, 203, 133);
		getContentPane().add(btnNewButton_4);
		
		JLabel lblNewLabel = new JLabel("Zure dirua: "+ erabiltzailea.getDirua()+"€");
		lblNewLabel.setBounds(422, 31, 130, 14);
		getContentPane().add(lblNewLabel);
		
		JButton btnNewButton_5 = new JButton("Ruleta");
		btnNewButton_5.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				JFrame c= new RuletaGUI(logeatuta, erabiltzailea);
				c.setVisible(true);
				logeatuta.setVisible(false);
			}
		});
		btnNewButton_5.setBounds(495, 71, 203, 136);
		getContentPane().add(btnNewButton_5);
		
		JButton btnNewButton_6 = new JButton("Jarraitu Bezeroa");
		btnNewButton_6.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				BLFacade facade = MainGUI.getBusinessLogic();
				Erregistratua er=(Erregistratua)facade.getErabiltzailea(erabiltzailea.getErabiltzailea());
				if(er.getEhunekoa()==0) {
					JFrame c= new JarraituEGUI(logeatuta, erabiltzailea);
					c.setVisible(true);
				}else {
					JFrame c= new JarraitzenEGUI(logeatuta, erabiltzailea);
					c.setVisible(true);
				}				
				logeatuta.setVisible(false);
			}
		});
		btnNewButton_6.setBounds(495, 225, 203, 133);
		getContentPane().add(btnNewButton_6);
		
		
	}
}