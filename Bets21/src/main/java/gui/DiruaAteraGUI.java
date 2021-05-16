package gui;

import javax.swing.JFrame;
import javax.swing.JTextArea;
import javax.swing.JButton;
import java.awt.BorderLayout;
import javax.swing.JTextField;

import businessLogic.BLFacade;
import domain.Erabiltzailea;
import domain.Erregistratua;
import exceptions.DiruFalta;
import exceptions.DiruNegatiboa;

import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;
import javax.swing.JLabel;

public class DiruaAteraGUI extends JFrame {
    private JTextField txtZenbakiOsoakIdatzi;

    public DiruaAteraGUI(JFrame logeatua,Erabiltzailea erabiltzailea) {
		this.setSize(743, 435);

        JFrame DiruaAtera= this;
        getContentPane().setLayout(null);
        txtZenbakiOsoakIdatzi = new JTextField();
        txtZenbakiOsoakIdatzi.setText("Zenbaki osoak idatzi");
        txtZenbakiOsoakIdatzi.setBounds(46, 66, 283, 106);
        getContentPane().add(txtZenbakiOsoakIdatzi);
        txtZenbakiOsoakIdatzi.setColumns(10);
        JLabel textArea = new JLabel("");
        textArea.setBounds(116, 205, 293, 56);
        getContentPane().add(textArea);


        JButton btnNewButton = new JButton("Dirua Atera");
        btnNewButton.setBounds(326, 65, 142, 108);
        btnNewButton.addActionListener(new ActionListener() {
        public void actionPerformed(ActionEvent e) {
        	try {
                    BLFacade facade = MainGUI.getBusinessLogic();
                     int kop= Integer.parseInt(txtZenbakiOsoakIdatzi.getText());
                     facade.diruaAtera(erabiltzailea,kop);
                     textArea.setText("Dirua eguneratu da!");
                }
                catch (NumberFormatException e1) {
                    textArea.setText("Ez da zenbaki bat!");
                } catch (DiruNegatiboa e2) {
                    textArea.setText("Sarturiko kopurua negatiboa da!");
                }
                catch (DiruFalta e3) {
                    textArea.setText("Ez daukazu atera nahi duzun bezainbeste diru!");
                }
                }
        });

        getContentPane().add(btnNewButton);

        JButton btnNewButton_1 = new JButton("Atzera");
        btnNewButton_1.setBounds(22, 21, 103, 23);
        btnNewButton_1.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                
                
                BLFacade facade = MainGUI.getBusinessLogic();
                Erabiltzailea er = facade.erabiltzaileaEguneratu(erabiltzailea);
                JFrame log = new LogeatutaEGUI(null, (Erregistratua) er);
                log.setVisible(true);
                DiruaAtera.setVisible(false);
            }
        });
        getContentPane().add(btnNewButton_1);
        
 
    }



}