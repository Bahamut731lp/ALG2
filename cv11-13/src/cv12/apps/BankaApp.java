/*
 * Copyright (C) 2022 Jirina Kralovcova
 * This program is part of Java programing education. 
 * You can use it as you need to learn Java. 
 */
package cv12.apps;

import cv12.banka.Transakce;
import cv12.banka.Ucet;
import java.io.File;
import java.io.IOException;
import java.util.InputMismatchException;
import java.util.Locale;
import java.util.ResourceBundle;
import java.util.Scanner;
import javax.swing.UIManager;

/**
 *
 * @author Jirina
 */
public class BankaApp {

    private static final Scanner sc = new Scanner(System.in);
    private static File pracovniAdr = null;
    private static Ucet aktualniUcet = null;
    private static final ResourceBundle RB_CZ = ResourceBundle.getBundle("cv12.props.popisky", new Locale("cs", "CZ"));
    private static final ResourceBundle RB_UK = ResourceBundle.getBundle("cv12.props.popisky", Locale.UK);
    private static ResourceBundle rbAkt = RB_CZ;

    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) throws IOException {
        boolean konecProgramu;
        try {
            do {
                vypisMenu();
                int volba = nactiVolbu();
                konecProgramu = obsluzVolbu(volba);
            } while (!konecProgramu);
        } catch (Exception ex) {
            System.out.println("Neocekavana chyba v programu, program bude ukoncen");
            ex.printStackTrace();
        } catch (Error err) {
            System.out.println("Chyba JVM, nelze pokracovat");
            err.printStackTrace();
        }
        System.out.println(rbAkt.getString("infoKonec"));
    }

    private static void vypisMenu() {
        System.out.println("");
        System.out.println(rbAkt.getString("titul"));
        System.out.println("1. " + rbAkt.getString("miPracAdr"));
        if (pracovniAdr != null) {
            System.out.println("2. Vyber uctu");
            System.out.println("3. Vypis pohybu na uctu");
            System.out.println("4. Zaznam transakce");
            System.out.println("5. Cesky");
            System.out.println("6. Anglicky");
        }
        System.out.println("0. Konec programu");
    }

    private static int nactiVolbu() {
        int volba;
        try {
            System.out.print("Zadej volbu: ");
            volba = sc.nextInt();
        } catch (InputMismatchException ex) {
            volba = -1;
        }
        sc.nextLine();
        return volba;
    }

    private static boolean obsluzVolbu(int volba) throws IOException {
        switch (volba) {
            case 0 -> {
                return true;
            }
            case 1 ->
                nacteniPracovnihoAdr();
            case 2 ->
                vyberUctu();
            case 3 ->
                vypisPohybuNaUctu();
            case 4 ->
                zaznamTransakce();
            case 5 ->
                rbAkt = RB_CZ;
            case 6 ->
                rbAkt = RB_UK;
            default ->
                System.out.println();
        }
        return false;
    }

    private static void nacteniPracovnihoAdr() {
        System.out.print("Zadejte cestu k pracovnimu adresari: ");
        File soubor = new File(sc.nextLine().trim());
        if (soubor.isDirectory()) {
            pracovniAdr = soubor;
        }
    }

    private static void vyberUctu() throws IOException {
        System.out.println("Zadejte cislo uctu: ");
        try {
            int cislo = sc.nextInt();
            Ucet ucet = Ucet.getInstance(pracovniAdr, cislo);
            aktualniUcet = ucet;
            return;
        } catch (IOException ex) {
            System.out.println("Nebylo mozne vytvorit soubor utu pro zadane cislo");
            necoByloChybne(ex);
        } catch (IllegalArgumentException ex) {
            System.out.println("Zadano cislo nesplnujici pozadavky na cislo uctu");
            necoByloChybne(ex);
        } catch (InputMismatchException ex) {
            System.out.println("Chybne zadane cislo");
            necoByloChybne(ex);
        }
    }

    private static void vypisPohybuNaUctu() throws IOException {
        String vypis = aktualniUcet.getVypis();
        System.out.printf(vypis);
    }

    private static void zaznamTransakce() throws IOException {

        System.out.println("Zadejte den,mesic,rok,castku:");
        int den = sc.nextInt();
        int mesic = sc.nextInt();
        int rok = sc.nextInt();
        double castka = sc.nextDouble();

        Transakce tr = new Transakce(den, mesic, rok, castka);
        aktualniUcet.ulozTransakci(tr);
    }

    private static void necoByloChybne(Exception ex) {
        System.err.println(ex.getMessage());
        ex.printStackTrace();
    }

}
