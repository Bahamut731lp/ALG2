package hak.piskvorky.apps;

import hak.piskvorky.apps.GUI.HlavniMenuHry;

import javax.swing.*;

public class PiskvorkyGame {

    private static final String MY_APP_TITLE = "TICK TACK TOE";

    public static void main(String[] args) {

            JFrame mainWindow;
            mainWindow = new HlavniMenuHry(MY_APP_TITLE);
            mainWindow.setVisible(true);
    }
}
