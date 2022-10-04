/*
 *  Copyright (C) 2022 Jirina
 *  This program is part of Java programing education. 
 *  You can use it as you need to learn Java. 
 */

package cv11.apps;

import java.util.InputMismatchException;
import java.util.Scanner;
/**
 *
 * @author jirina.kralovcova
 */
public class VysledkyZavoduApp {


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
        System.out.println("1. Vyber souboru registrace");
        System.out.println("2. Nacteni dat startu");
        System.out.println("3. Nacteni dat cile");
        System.out.println("4. Zobrazeni vysledku");
        System.out.println("0. Konec programu");
    }

    private static int nactiVolbu() {
        int volba;
        try {
            System.out.print("Zadej volbu: ");
            volba = sc.nextInt();
        } catch (InputMismatchException ex) {
            volba = -1;
        } finally {
            sc.nextLine();
        }
        return volba;
    }

    private static boolean obsluzVolbu(int volba) {
        switch (volba) {
            case 0 -> {
                return true;
            }
            case 1 ->
                obsluhaVolby1();
            case 2 ->
                obsluhaVolby2();
            default ->
                System.out.println("Neznama volba");
        }
        return false;
    }

    private static void obsluhaVolby1() {
        System.out.println("");
        System.out.println("Obsluhuji volbu 1");
    }

    private static void obsluhaVolby2() {
        System.out.println("");
        System.out.println("Obsluhuji volbu 2");
    }

}
