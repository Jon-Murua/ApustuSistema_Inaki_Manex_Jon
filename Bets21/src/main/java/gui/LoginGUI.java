package gui;

import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JTextField;

import businessLogic.BLFacade;
import businessLogic.BLFacadeImplementation;
import domain.Administratzailea;
import domain.Erabiltzailea;
import domain.Erregistratua;
import domain.Langilea;
import exceptions.ErabiltzaileaEzDago;
import exceptions.PasahitzaOkerra;

import javax.swing.JPasswordField;
import javax.swing.JButton;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;
import javax.swing.JTextArea;


	public class LoginGUI extends JFrame {

		JFrame login =this;
		private JTextField textField;
		private JPasswordField passwordField;
		
		public LoginGUI(JFrame main) {
			this.setSize(743, 435);
			getContentPane().setLayout(null);
			
			JLabel lblNewLabel = new JLabel("SAIOA HASI");
			lblNewLabel.setBounds(195, 25, 80, 14);
			getContentPane().add(lblNewLabel);
			
			JLabel textArea = new JLabel("");
			textArea.setBounds(297, 245, 123, 31);
			getContentPane().add(textArea);
			
			JLabel lblNewLabel_1 = new JLabel("Erabiltzailea");
			lblNewLabel_1.setBounds(68, 83, 80, 14);
			getContentPane().add(lblNewLabel_1);
			
			JLabel lblNewLabel_2 = new JLabel("Pasahitza");
			lblNewLabel_2.setBounds(68, 131, 104, 14);
			getContentPane().add(lblNewLabel_2);
			
			textField = new JTextField();
			textField.setBounds(195, 80, 138, 20);
			getContentPane().add(textField);
			textField.setColumns(10);
			
			passwordField = new JPasswordField();
			passwordField.setBounds(195, 128, 138, 20);
			getContentPane().add(passwordField);
					
			JButton btnNewButton_1 = new JButton("Atzera ");
			btnNewButton_1.addActionListener(new ActionListener() {
				public void actionPerformed(ActionEvent e) {
					JFrame a = new MainGUI();
					a.setVisible(true);
					LoginGUI.this.setVisible(false);
					
				}
			});
			btnNewButton_1.setBounds(26, 21, 89, 23);
			getContentPane().add(btnNewButton_1);
			
			JButton btnNewButton = new JButton("Saioa hasi");
			btnNewButton.addActionListener(new ActionListener() {
				public void actionPerformed(ActionEvent e) {
					try {
						
						String erab = textField.getText();
						String pas = new String(passwordField.getPassword());
						
						if (erab.length()>0 && pas.length()>0) {
							BLFacade facade = MainGUI.getBusinessLogic();
							Erabiltzailea erabiltzailea=facade.login(erab, pas);
							if (erabiltzailea instanceof Erregistratua) {
								JFrame d = new LogeatutaEGUI(main,(Erregistratua)erabiltzailea);
								
								d.setVisible(true);
								login.setVisible(false);	
							}
							else if(erabiltzailea instanceof Langilea){
								
								JFrame f = new LogeatutaLGUI(main,erabiltzailea);
								f.setVisible(true);
								login.setVisible(false);
							}
							else if(erabiltzailea instanceof Administratzailea){
								JFrame g = new LogeatutaAGUI(main,erabiltzailea);
								
								g.setVisible(true);
								login.setVisible(false);
							}
						}
											
											
					} catch (PasahitzaOkerra e2) {
						textArea.setText("Pasahitza ez da zuzena.");
					}catch (ErabiltzaileaEzDago e3) {
						textArea.setText("Erabiltzailea ez da existitzen.");
					}
				}
			});
			btnNewButton.setBounds(168, 189, 123, 31);
			getContentPane().add(btnNewButton);

			
		}
	}
	
