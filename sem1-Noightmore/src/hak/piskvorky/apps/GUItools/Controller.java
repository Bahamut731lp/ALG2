package hak.piskvorky.apps.GUItools;

import hak.piskvorky.hry.PiskvorkyException;
import hak.piskvorky.hry.PiskvorkyInterface;

import javax.swing.*;

public class Controller {
    private PiskvorkyInterface piskvorky;

    public Controller(){
    }

    public boolean initializePiskvorky(JLabel errorLabel, JTextField PRfield, JTextField PSfield) {
        errorLabel.setText("");
        int pr = 0;
        try {
            pr = Integer.parseInt(PRfield.getText());
        } catch (NumberFormatException e) {
            PRfield.setText("ZADEJ CISLO");
        }
        int ps = 0;
        try {
            ps = Integer.parseInt(PSfield.getText());
        } catch (NumberFormatException e) {
            PSfield.setText("ZADEJ CISLO!");
        }
        if(ps != 0 && pr != 0){
            try{
                piskvorky = PiskvorkyInterface.getInstance(pr, ps);
                return true;
            }catch(PiskvorkyException e){
                //e.printStackTrace();
                errorLabel.setText(e.getMessage());
            }
        }
        return false;
    }



    public int getRowSizeOfAPiskvorkyBoard(){
        return this.piskvorky.getPocetRadku();
    }

    public int getColSizeOfAPiskvorkyBoard(){
        return this.piskvorky.getPocetSloupcu();
    }

    public int getVal(int rp, int cp){
        return this.piskvorky.getSymbol(rp,cp);
    }

    public void setVal(int rp, int cp){
        this.piskvorky.polozDalsiSymbol(rp, cp);
    }

    public boolean isGameOver() {
        return this.piskvorky.jeKonecHry();
    }

    public int getWinner() {
        return this.piskvorky.getCisloVyherce();
    }

    public String getCurrentPlayer() {
        return String.valueOf(this.piskvorky.getHrajeHrac());
    }

}
