package gui;

import javax.swing.JFrame;
import javax.swing.JLabel;

import domain.Erabiltzailea;
import javax.swing.JButton;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;

public class LogeatutaAGUI extends JFrame {
	JFrame logeatuta =this;
	public LogeatutaAGUI(JFrame main, Erabiltzailea erabiltzailea) {
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
		btnNewButton.setBounds(24, 29, 89, 23);
		getContentPane().add(btnNewButton);
		
		JButton btnNewButton_1 = new JButton("Gertaera ezabatu");
		btnNewButton_1.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				JFrame a = new GertaerakEzabatuGUI(logeatuta);
				a.setVisible(true);
				logeatuta.setVisible(false);
			}
		});
		btnNewButton_1.setBounds(220, 118, 156, 95);
		getContentPane().add(btnNewButton_1);
		
	}
}

