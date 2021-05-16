package gui;

import javax.swing.JFrame;
import javax.swing.JTextArea;
import javax.swing.JButton;
import java.awt.BorderLayout;
import javax.swing.JTextField;

import businessLogic.BLFacade;
import domain.Erabiltzailea;
import domain.Erregistratua;
import exceptions.DiruNegatiboa;

import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;
import javax.swing.JLabel;

public class DiruaSartuGUI extends JFrame {
    private JTextField txtZenbakiOsoakSartu;

    public DiruaSartuGUI(JFrame logeatua,Erabiltzailea erabiltzailea) {

		this.setSize(743, 435);
        JFrame DiruaSartu= this;
        getContentPane().setLayout(null);
        txtZenbakiOsoakSartu = new JTextField();
        txtZenbakiOsoakSartu.setText("Zenbaki osoak idatzi");
        txtZenbakiOsoakSartu.setBounds(105, 80, 456, 152);
        getContentPane().add(txtZenbakiOsoakSartu);
        txtZenbakiOsoakSartu.setColumns(10);
        JButton btnNewButton = new JButton("Dirua Sartu");
        btnNewButton.setBounds(560, 80, 138, 152);
        JLabel textArea = new JLabel("");
        textArea.setBounds(159, 268, 402, 51);
        getContentPane().add(textArea);
        btnNewButton.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {

                try {
                    BLFacade facade = MainGUI.getBusinessLogic();
                     int kop= Integer.parseInt(txtZenbakiOsoakSartu.getText());
                     facade.diruaSartu(erabiltzailea,kop);
                     textArea.setText("Dirua eguneratu da!");
                }
                catch (NumberFormatException e1) {
                    textArea.setText("Ez da zenbaki bat!");
                } catch (DiruNegatiboa e2) {
                    textArea.setText("Sarturiko kopurua negatiboa da!");
                }
                }
        });

        getContentPane().add(btnNewButton);

        JButton btnNewButton_1 = new JButton("Atzera");
        btnNewButton_1.setBounds(38, 25, 112, 31);
        btnNewButton_1.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                
               
                BLFacade facade = MainGUI.getBusinessLogic();
                Erabiltzailea er = facade.erabiltzaileaEguneratu(erabiltzailea);
                JFrame log = new LogeatutaEGUI(null, (Erregistratua) er);
                log.setVisible(true);
                DiruaSartu.setVisible(false);
            }
        });
        getContentPane().add(btnNewButton_1);
        

    }
}
