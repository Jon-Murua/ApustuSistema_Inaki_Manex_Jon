package gui;

import javax.swing.JFrame;

import domain.Erabiltzailea;
import domain.Erregistratua;
import exceptions.DiruNegatiboa;
import exceptions.ErabiltzaileaEzDago;

import javax.swing.JButton;
import java.awt.BorderLayout;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;
import javax.swing.JTextField;

import businessLogic.BLFacade;

import javax.swing.JLabel;
import javax.swing.JRadioButton;

public class JarraituEGUI extends JFrame{
	private JTextField textField;
	private JTextField textField1;
	private JFrame jarraituJF=this;
	private JTextField textField_1;
	private JTextField textField_2;
	private JFrame a=this;

	public JarraituEGUI(JFrame logeatuta, Erregistratua erabiltzailea) {
		this.setSize(743, 435);
		getContentPane().setLayout(null);
		
		textField1 = new JTextField();
		textField1.setBounds(132, 51, 178, 20);
		getContentPane().add(textField1);
		textField1.setColumns(10);
		
		JLabel lblNewLabel = new JLabel("Sartu jarraitu nahi duzun bezeroaren erabiltzailea");
		lblNewLabel.setBounds(93, 26, 296, 14);
		getContentPane().add(lblNewLabel);
		
		JLabel lblNewLabel_1 = new JLabel("");
		lblNewLabel_1.setBounds(132, 197, 202, 14);
		getContentPane().add(lblNewLabel_1);
		
		textField_1 = new JTextField();
		textField_1.setBounds(163, 107, 119, 20);
		getContentPane().add(textField_1);
		textField_1.setColumns(10);
		
		JLabel lblNewLabel_2 = new JLabel("Apostu originalaren zein %? ");
		lblNewLabel_2.setBounds(142, 82, 225, 14);
		getContentPane().add(lblNewLabel_2);
		
		JButton btnNewButton = new JButton("Jarraitu");
		btnNewButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				 BLFacade facade = MainGUI.getBusinessLogic();
				 String erab=textField1.getText();
				 int ehunekoa=Integer.parseInt(textField_1.getText());
	             try {
					facade.jarraitu(erab,erabiltzailea,ehunekoa);
					lblNewLabel_1.setText("Jarraitzen hasi zara!");
				} catch (ErabiltzaileaEzDago e1) {
					lblNewLabel_1.setText("Ez da existitzen erabiltzaile hori!");
				} catch (DiruNegatiboa e2) {
					lblNewLabel_1.setText("Sartu duzun % a negatiboa da!");
				}
			}
		});
		btnNewButton.setBounds(173, 153, 109, 33);
		getContentPane().add(btnNewButton);
		
		JButton btnNewButton_2 = new JButton("Atzera");
		btnNewButton_2.setBounds(22, 191, 89, 23);
		getContentPane().add(btnNewButton_2);
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
