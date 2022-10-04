package hak.piskvorky.apps.GUI;

import hak.piskvorky.apps.GUItools.Controller;

import javax.swing.*;

public class WinnerDialog extends JFrame{

    Controller controller;
    private JPanel mainPanel;
    private JLabel winnerNameNumberLabel;

    public WinnerDialog(Controller controller) {
        super("Winner Winner Chicken Dinner!");
        this.controller = controller;
        this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        this.setContentPane(mainPanel);
        this.pack();
        this.setWinnerNameNumberLabel();
    }

    private void setWinnerNameNumberLabel(){
        this.winnerNameNumberLabel.setText(String.valueOf(controller.getWinner()));
    }
}
