package hak.piskvorky.apps.GUI;

import hak.piskvorky.apps.GUItools.Controller;

import javax.swing.*;
import java.awt.*;

public class HerniDeska extends JFrame {
    private JPanel hlavniPanel;
    private JPanel boardPanel;

    Controller ovladac;
    int rowBoardSize;
    int colBoardSize;

    JButton[][] buttons;

    public HerniDeska(Controller ovladac){
        super("Piskvorky");
        this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        this.ovladac = ovladac;
        this.rowBoardSize = ovladac.getRowSizeOfAPiskvorkyBoard();
        this.colBoardSize = ovladac.getColSizeOfAPiskvorkyBoard();
        this.initPanel();
    }

    public void renderBoard(){
        //this.gameStateLabel.setText(ovladac.getCurrentPlayer());

        for(int r = 0; r < rowBoardSize; r++){
            for(int c = 0; c < colBoardSize; c++){
                var finalR = r;
                var finalC = c;

                var tickTackToe_SquareField_Value
                        = String.valueOf(ovladac.getVal(r,c));
                var tickTackToe_SquareField
                        = new JButton(tickTackToe_SquareField_Value);

                tickTackToe_SquareField.addActionListener(e -> {
                    var buttonObject = (JButton) e.getSource();
                    if(buttonObject.getText().equals("0")){
                        this.ovladac.setVal(finalR, finalC);
                        this.boardPanel.removeAll();
                        this.initPanel();

                        if(ovladac.isGameOver()){
                            var winnerDialog = new WinnerDialog(this.ovladac);
                            winnerDialog.setVisible(true);
                            this.setVisible(false);
                        }
                   }
                });
                this.boardPanel.add(tickTackToe_SquareField);
            }
        }
        //this.boardPanel.add(this.gameStateLabel);
        this.add(this.boardPanel);
    }

    private void initPanel(){
        this.boardPanel.setBorder(BorderFactory.createEmptyBorder(5, 5, 5, 5));
        this.boardPanel.setLayout(new GridLayout(rowBoardSize, colBoardSize, 10, 10));
        this.renderBoard();
        this.boardPanel.revalidate();
        this.boardPanel.repaint();
    }

    private void refreshBoard(){
        for(int r = 0; r < rowBoardSize; r++) {
            for (int c = 0; c < colBoardSize; c++) {
                var newButtonText = String.valueOf(ovladac.getVal(r,c));
                this.buttons[r][c].setName(newButtonText);
             }
        }
    }
}
