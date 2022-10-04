package hak.piskvorky.apps.GUI;

import hak.piskvorky.apps.GUItools.Controller;

import javax.swing.*;
import java.awt.*;

public class HlavniMenuHry extends JFrame{
    private JPanel hlavniPanel;
    private JTextField zadejtePocetRadkuTabulkyTextField;
    private JTextField zadejtePocetSloupcuTabulkyTextField;
    private JButton buttonHratHru;
    private JLabel errorLabel;

    Controller ovladac;

    public HlavniMenuHry(String myAppTitle) throws HeadlessException {
    	super(myAppTitle);
    	this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        this.setContentPane(hlavniPanel);
        this.pack();
        this.initialize();

        buttonHratHru.addActionListener(actionEvent -> {
            if(ovladac.initializePiskvorky(
                            errorLabel,
                            zadejtePocetRadkuTabulkyTextField,
                            zadejtePocetSloupcuTabulkyTextField)
            ){
                JFrame herniDeska = new HerniDeska(this.ovladac);
                herniDeska.setVisible(true);
                this.setVisible(false);
            }
        });
    }
    private void initialize(){
       ovladac = new Controller();
    }
}
