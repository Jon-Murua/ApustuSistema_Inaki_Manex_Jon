package gui;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.Arrays;
import java.util.Date;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPasswordField;
import javax.swing.JTextArea;
import javax.swing.JTextField;

import businessLogic.BLFacade;
import configuration.UtilDate;
import domain.Erabiltzailea;
import domain.Erregistratua;
import exceptions.AdinTxikikoa;
import exceptions.ErabiltzaileaBadago;
import exceptions.PasahitzaOkerra;

public class RegisterGUI extends JFrame {
	private JTextField textField;
	private JPasswordField passwordField;
	private JPasswordField passwordField_1;
	private JTextField textField_1;
	private JTextField textField_2;
	private JTextField textField_3;
	private JTextField txtUuuu;
	private JTextField txtHh;
	private JTextField txtEe;
	
	JFrame erreg = this;
	
	public RegisterGUI(JFrame main) {
		this.setSize(743, 435);
		getContentPane().setLayout(null);
		
		JLabel lblNewLabel = new JLabel("ERREGISTRATU");
		lblNewLabel.setBounds(191, 25, 205, 27);
		getContentPane().add(lblNewLabel);
		
		JLabel lblNewLabel_1 = new JLabel("Erabiltzailea");
		lblNewLabel_1.setBounds(20, 66, 80, 14);
		getContentPane().add(lblNewLabel_1);
		
		JLabel lblNewLabel_2 = new JLabel("Pasahitza");
		lblNewLabel_2.setBounds(20, 111, 80, 14);
		getContentPane().add(lblNewLabel_2);
		
		JLabel lblNewLabel_3 = new JLabel("Berretsi");
		lblNewLabel_3.setBounds(20, 136, 95, 28);
		getContentPane().add(lblNewLabel_3);
				
		textField = new JTextField();
		textField.setBounds(103, 63, 101, 20);
		getContentPane().add(textField);
		textField.setColumns(10);
		
		passwordField = new JPasswordField();
		passwordField.setBounds(103, 108, 101, 20);
		getContentPane().add(passwordField);
		
		passwordField_1 = new JPasswordField();
		passwordField_1.setBounds(99, 156, 106, 20);
		getContentPane().add(passwordField_1);
		
		textField_1 = new JTextField();
		textField_1.setBounds(295, 63, 129, 20);
		getContentPane().add(textField_1);
		textField_1.setColumns(10);
		
		textField_2 = new JTextField();
		textField_2.setBounds(295, 91, 129, 20);
		getContentPane().add(textField_2);
		textField_2.setColumns(10);
		
		textField_3 = new JTextField();
		textField_3.setBounds(295, 132, 129, 20);
		getContentPane().add(textField_3);
		textField_3.setColumns(10);
		
		txtUuuu = new JTextField();
		txtUuuu.setText("uuuu");
		txtUuuu.setBounds(334, 164, 38, 20);
		getContentPane().add(txtUuuu);
		txtUuuu.setColumns(10);
		
		JLabel textArea = new JLabel("");
		textArea.setBounds(326, 236, 126, 36);
		getContentPane().add(textArea);
		
		
		txtHh = new JTextField();
		txtHh.setText("hh");
		txtHh.setBounds(387, 164, 30, 20);
		getContentPane().add(txtHh);
		txtHh.setColumns(10);
		
		txtEe = new JTextField();
		txtEe.setText("ee");
		txtEe.setBounds(433, 164, 30, 20);
		getContentPane().add(txtEe);
		txtEe.setColumns(10);
		
				
		JButton btnNewButton_1 = new JButton("Atzera ");
		btnNewButton_1.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				
				RegisterGUI.this.setVisible(false);
				main.setVisible(true);
				
			}
		});
		btnNewButton_1.setBounds(26, 21, 89, 23);
		getContentPane().add(btnNewButton_1);
		
		JButton btnNewButton = new JButton("Erregistratu");
		btnNewButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				try {
					
					String erab = textField.getText();
					String pas = new String(passwordField.getPassword());
					String izena =  textField_1.getText();
					String abizena = textField_2.getText();
					String posta = textField_3.getText();
					int urtea = Integer.parseInt(txtUuuu.getText());
					int hilabetea = Integer.parseInt(txtHh.getText());
					int eguna = Integer.parseInt(txtEe.getText());
									
					
					if (erab.length()>0 && pas.length()>0 && izena.length()>0 && abizena.length()>0 && posta.length()>0) {
						if (Arrays.equals(passwordField.getPassword(), passwordField_1.getPassword())) {
							BLFacade facade = MainGUI.getBusinessLogic();
							Erabiltzailea erabiltzailea=facade.erregistratu(erab, pas, izena, abizena, posta, UtilDate.newDate(urtea, hilabetea, eguna));
							
							JFrame d = new LogeatutaEGUI(main, (Erregistratua)erabiltzailea);
							d.setVisible(true);
							erreg.setVisible(false);		
							
							} else {
								textArea.setText("Pasahitza ez da zuzena.");
							}
					}
					
										
				} catch (PasahitzaOkerra e2) {
					textArea.setText("Pasahitza ez da zuzena.");
				} catch (ErabiltzaileaBadago e3) {
					textArea.setText("Erabiltzailea jada existitzen da.");
				} catch (AdinTxikikoa e1) {
					textArea.setText("Adin txikikoa zara.");
				}
			}
		});
		btnNewButton.setBounds(174, 206, 123, 23);
		getContentPane().add(btnNewButton);
		
		JLabel lblNewLabel_4 = new JLabel("Izena");
		lblNewLabel_4.setBounds(239, 66, 46, 14);
		getContentPane().add(lblNewLabel_4);
		
		JLabel lblNewLabel_5 = new JLabel("Abizena");
		lblNewLabel_5.setBounds(239, 94, 46, 14);
		getContentPane().add(lblNewLabel_5);
		

		
		JLabel lblNewLabel_6 = new JLabel("Posta");
		lblNewLabel_6.setBounds(239, 135, 46, 14);
		getContentPane().add(lblNewLabel_6);
		
		JLabel lblNewLabel_7 = new JLabel("pasahitza");
		lblNewLabel_7.setBounds(20, 156, 80, 27);
		getContentPane().add(lblNewLabel_7);
		
		JLabel lblNewLabel_8 = new JLabel("Jaiotze-data");
		lblNewLabel_8.setBounds(239, 167, 98, 14);
		getContentPane().add(lblNewLabel_8);
		

		JLabel lblNewLabel_9 = new JLabel("  /             /");
		lblNewLabel_9.setBounds(372, 167, 80, 14);
		getContentPane().add(lblNewLabel_9);
	
	
		
	
	}
}
