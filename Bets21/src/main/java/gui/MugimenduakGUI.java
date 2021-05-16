package gui;

import javax.swing.JFrame;

import domain.Erabiltzailea;
import domain.Erregistratua;
import domain.Mugimendua;

import javax.swing.JButton;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;
import javax.swing.JLabel;
import javax.swing.JTextPane;
import javax.swing.table.DefaultTableModel;

import businessLogic.BLFacade;

import javax.swing.JList;
import javax.swing.JTextArea;

public class MugimenduakGUI extends JFrame{

	JFrame mugimendu=this;
	public MugimenduakGUI(JFrame logeatuta, Erregistratua erabiltzailea) {
		this.setSize(743, 435);
		
		JButton btnNewButton = new JButton("Atzera");
		btnNewButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				logeatuta.setVisible(true);
				mugimendu.setVisible(false);
			}
		});
		getContentPane().setLayout(null);
		btnNewButton.setBounds(28, 23, 89, 23);
		getContentPane().add(btnNewButton);
		
		JLabel lblNewLabel = new JLabel("MUGIMENDUAK");
		lblNewLabel.setBounds(166, 21, 94, 35);
		getContentPane().add(lblNewLabel);
		
		JTextArea textArea = new JTextArea();
		textArea.setBounds(38, 57, 404, 315);
		getContentPane().add(textArea);
		
		
		BLFacade facade = MainGUI.getBusinessLogic();
        Erregistratua erabiltzaileaDB = (Erregistratua) facade.getErabiltzailea(erabiltzailea.getErabiltzailea());

        textArea.removeAll();
        
        for (Mugimendua s : erabiltzaileaDB.getMugimenduak()) {
            textArea.append(s.toString());
            textArea.append("\n");
        }
	
	}
}
