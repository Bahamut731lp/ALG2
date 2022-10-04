/*
 *  Copyright (C) 2022 Jirina
 *  This program is part of Java programing education. 
 *  You can use it as you need to learn Java. 
 */

package cv11.apps;

import cv11.zavody.ZavodRegistrace;
import java.io.IOException;
import java.util.InputMismatchException;
import java.util.List;
import java.util.Scanner;
/**
 *
 * @author jirina.kralovcova
 */
public class RegistraceApp {
    private static ZavodRegistrace zavod = null;

    private static final Scanner sc = new Scanner(System.in);

    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) throws IOException {
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
        System.out.println("1. Zadani souboru");
        System.out.println("2. Registrace dalsiho zavodnika");
        System.out.println("3. Vypis vsech registraci");
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

    private static boolean obsluzVolbu(int volba) throws IOException {
        switch (volba) {
            case 0 -> {
                return true;
            }
            case 1 ->
                vyberSouboru();
            case 2 ->
                registraceZavodnika();
            case 3 ->
                vypisRegistrovanychZavodniku();
            default ->
                System.out.println("Neznama volba");
        }
        return false;
    }

    private static void vyberSouboru() throws IOException {
        System.out.println("");
        System.out.println("Zadani souboru");
        String path = sc.nextLine();
        zavod = ZavodRegistrace.getInstance(path);
    }

    private static void registraceZavodnika() throws IOException {
        System.out.println("");
        System.out.println("Registrace dalsiho zavodnika");
        System.out.println("zadej jmeno, prijmeni a rocnik");
        zavod.pridejZavodnika(sc.next(), sc.next(), sc.nextInt());
    }

    private static void vypisRegistrovanychZavodniku() throws IOException {
        System.out.println("");
        System.out.println("Vypis registrovanych zavodniku");
        List<String> vypis = zavod.getVypisZavodniku(true);
        for(String str : vypis) {
            System.out.println(str);
        }
    }
    
    

}
