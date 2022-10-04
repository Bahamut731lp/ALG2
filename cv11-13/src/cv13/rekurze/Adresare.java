/*
 *  Copyright (C) 2022 Jirina
 *  This program is part of Java programing education. 
 *  You can use it as you need to learn Java. 
 */
package cv13.rekurze;

import java.io.File;
import java.io.IOException;
import java.util.Scanner;

/**
 *
 * @author jirina.kralovcova
 */
public class Adresare {

    private static final Scanner sc = new Scanner(System.in);

    /**
     * Vypise hierarchii adresaru od zadaneho korenoveho adresare.
     *
     * @param adr
     */
    public static void vypisHierarchiiAdresaru(File adr) {
        if (!adr.isDirectory()) {
            throw new IllegalArgumentException(
                    "Zadana cesta neni existujicim adresare ");
        }
        vypisHierarchiiAdresaru(adr, 0);
    }

    // privatni rekurzivni metoda, ktera prochazi hierarchii adresaru
    private static void vypisHierarchiiAdresaru(File adr, int level) {
        for (int i = 0; i < level; i++) {
            System.out.print("-");
        }
        System.out.println(adr.getName());
        File[] vypis = adr.listFiles((File pathname) -> pathname.isDirectory());
        for (File f : vypis) {
            vypisHierarchiiAdresaru(f, ++level);
        }
    }

    /**
     * Vraci pocet byte, vsech souboru v danem adresari a ve vsech podadresarich
     * libovolne urovne.
     *
     * @param adr
     * @return
     */
    public static long velikostAdresare(File adr) {
        if (!adr.isDirectory()) {
            throw new IllegalArgumentException(
                    "Zadana cesta neni existujicim adresare ");
        }
        long vysledek = 0;
        File[] vypis = adr.listFiles();
        for (File f : vypis) {
            if (f.isFile()) {
                vysledek += f.length();
            } else if (f.isDirectory()) {
                vysledek += velikostAdresare(f);
            }
        }
        return vysledek;
    }

    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) throws IOException {
        vypisHierarchiiAdresaru((new File(".")).getCanonicalFile());

        System.out.println("");
        System.out.println("src     " + velikostAdresare(new File("src")));
        System.out.println("projekt " + velikostAdresare(new File(".")));
    }

}
