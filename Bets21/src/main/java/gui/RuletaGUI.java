package gui;

import java.awt.Color;
import java.awt.Dimension;

import javax.swing.JFrame;
import javax.swing.JLabel;

import domain.Erabiltzailea;
import domain.Erregistratua;
import exceptions.DiruFalta;
import exceptions.DiruNegatiboa;

import javax.swing.ButtonGroup;
import javax.swing.ImageIcon;
import javax.swing.JRadioButton;
import javax.swing.JTextField;

import businessLogic.BLFacade;

import javax.swing.JButton;
import java.awt.Font;
import java.awt.event.ActionListener;
import java.util.Random;
import java.awt.event.ActionEvent;

public class RuletaGUI extends JFrame {
	
	private JFrame frame = new JFrame();
	ImageIcon ruleta = new ImageIcon("roulette.png");
	private JTextField textField;
	private double dirua;
	private JFrame a = this;
	private Erregistratua e2;
	
	public RuletaGUI(JFrame logeatuta, Erregistratua erabiltzailea) {
		getContentPane().setLayout(null);
		this.setSize(new Dimension(730,730));
		
		JLabel lblNewLabel = new JLabel("");
		lblNewLabel.setBounds(45, 23, 690, 690);
		getContentPane().add(lblNewLabel);
		lblNewLabel.setIcon(ruleta);
		
		JLabel lblNewLabel_1 = new JLabel("Aukeratu zure apostua:");
		lblNewLabel_1.setBounds(265, 290, 200, 14);
		getContentPane().add(lblNewLabel_1);
		
		JRadioButton gorria = new JRadioButton("Gorria (x2)");
		gorria.setBounds(215, 311, 99, 23);
		getContentPane().add(gorria);
		
		JRadioButton beltza = new JRadioButton("Beltza (x2)");
		beltza.setBounds(312, 311, 102, 23);
		getContentPane().add(beltza);
		
		JRadioButton berdea = new JRadioButton("Berdea (x10)");
		berdea.setBounds(416, 311, 99, 23);
		getContentPane().add(berdea);
		
		ButtonGroup taldea = new ButtonGroup();
		taldea.add(berdea);
		taldea.add(beltza);
		taldea.add(gorria);
		
		JLabel lblNewLabel_2 = new JLabel("Sartu dirua:");
		lblNewLabel_2.setBounds(304, 213, 115, 14);
		getContentPane().add(lblNewLabel_2);
		
		textField = new JTextField();
		textField.setBounds(285, 238, 134, 20);
		getContentPane().add(textField);
		textField.setColumns(10);
		
		JLabel textField_1 = new JLabel("");
		textField_1.setBounds(309, 474, 131, 34);
		getContentPane().add(textField_1);
		
		JLabel lblNewLabel_3 = new JLabel("Atera den kolorea:");
		lblNewLabel_3.setBounds(304, 412, 200, 14);
		getContentPane().add(lblNewLabel_3);
		
		JLabel lblNewLabel_4 = new JLabel(" ");
		lblNewLabel_4.setFont(new Font("Tahoma", Font.PLAIN, 20));
		lblNewLabel_4.setBounds(307, 424, 266, 48);
		getContentPane().add(lblNewLabel_4);
		
		JLabel dirualabel = new JLabel("Zure dirua: "+ erabiltzailea.getDirua()+"€");
		dirualabel.setBounds(544, 23, 130, 14);
		getContentPane().add(dirualabel);
		
		JButton btnNewButton = new JButton("APOSTATU");
		btnNewButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				
				Random aboleo = new Random();
				dirua = Double.parseDouble(textField.getText());
				BLFacade facade = MainGUI.getBusinessLogic();
				try {
					if (berdea.isSelected()||gorria.isSelected()||beltza.isSelected()) {
						e2=(Erregistratua) facade.diruaGaldu(dirua, erabiltzailea);
					
					
						final int ran = aboleo.nextInt(37);
						if (ran > 34 ) { 
							lblNewLabel_4.setText("BERDEA");
							lblNewLabel_4.setForeground(Color.GREEN);
							if(berdea.isSelected()) {
								e2=(Erregistratua) facade.gehituDirua(dirua*10,erabiltzailea);
								textField_1.setText("Irabazi duzu!");
							}else {
								textField_1.setText("Galdu duzu!");
							}
						}else if (ran > 17 ) { 
							lblNewLabel_4.setText("GORRIA");
							lblNewLabel_4.setForeground(Color.RED);
							if(gorria.isSelected()) {
								e2=(Erregistratua) facade.gehituDirua(dirua*2,erabiltzailea);
								textField_1.setText("Irabazi duzu!");
							}else {
								textField_1.setText("Galdu duzu!");
							}
						}else { 
							lblNewLabel_4.setText("BELTZA");
							lblNewLabel_4.setForeground(Color.BLACK);
							if(beltza.isSelected()) {
								e2=(Erregistratua) facade.gehituDirua(dirua*2,erabiltzailea);
									textField_1.setText("Irabazi duzu!");				
						}else {
							textField_1.setText("Galdu duzu!");
						}
					}
					
				}else {
					textField_1.setText("Aukeratu kolore bat!");
				}
					dirualabel.setText("Zure dirua: "+ e2.getDirua()+"€");
					}catch(DiruNegatiboa e2) {
						textField_1.setText("Sartu duzun kopurua 0 baina txikiagoa da!");
						lblNewLabel_4.setText("");
					}catch(DiruFalta e1) {
						textField_1.setText("Ez daukazu diru nahikoa!");
						lblNewLabel_4.setText("");
					}
				}
		});
		btnNewButton.setBounds(285, 367, 134, 34);
		getContentPane().add(btnNewButton);
		
		JButton btnNewButton_1 = new JButton("Atzera");
		btnNewButton_1.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				 BLFacade facade = MainGUI.getBusinessLogic();
	             Erabiltzailea era = facade.erabiltzaileaEguneratu(erabiltzailea);
	             JFrame log = new LogeatutaEGUI(null, (Erregistratua) era);
				
				a.setVisible(false);
				log.setVisible(true);
			}
		});
		btnNewButton_1.setBounds(27, 23, 109, 34);
		getContentPane().add(btnNewButton_1);
		

		
	}
}
