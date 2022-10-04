/*
 * Copyright (C) 2022 Jirina Kralovcova
 * This program is part of Java programing education. 
 * You can use it as you need to learn Java. 
 */

package apps;

import apps.drawutils.StdDraw;
import java.util.InputMismatchException;
import java.util.Scanner;
/**
 *
 * @author ALG
 */
public class PiskvorkyApp {
    
    private static final Scanner sc = new Scanner(System.in);

    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) {
        boolean konecProgramu;
        do {
            vypisMenu();
            int volba = nactiVolbu();
            konecProgramu = obsluzVolbu(volba);
        } while (!konecProgramu);
        System.out.println("Koncim ...");
    }

    private static void vypisMenu() {
        System.out.println("");
        System.out.println("Hlavni menu programu");
        System.out.println("1. Hrat hru Piskvorky");
        System.out.println("2. Zmena velikosti hraci plochy");
        System.out.println("3. Zmena symbolu pouzitych pro jednotlive hrace");
        System.out.println("4. Zmena barevneho nastaveni");
        System.out.println("0. Konec programu");
    }

    private static int nactiVolbu() {
        int volba;
        try {
            volba = sc.nextInt();      
        } catch (InputMismatchException ex) {
            volba = -1;
        }
        sc.nextLine();
        return volba;
    }

    private static boolean obsluzVolbu(int volba) {
        switch (volba) {
            case 0: return true;
            case 1: hratHru(); break;
            case 2: zmenaRozmeru(); break;
            case 3: zmenaSymbolu(); break;
            case 4: zmenaBarev(); break;
            default:
                System.out.println("Neznama volba");
        }
        return false;
    }

    private static void hratHru() {
        System.out.println("");
        System.out.println("Hra piskvorky");
        System.out.println("Hra je realizovana v samostatnem okne ");
        // vytvoreni objektu hry
        StdDraw.clear();     
    }

    private static void zmenaRozmeru() {
        System.out.println("");
        System.out.println("Nastaveni velikosti hraciho pole");
        // vypis aktualnich hodnot
        // nacteni novych hodnot
        // provedeni zmeny pouze v pripade platnych hodnot apod.  
    }

    private static void zmenaSymbolu() {
        throw new UnsupportedOperationException("Not supported yet.");
    }

    private static void zmenaBarev() {
        throw new UnsupportedOperationException("Not supported yet.");
    }

}
