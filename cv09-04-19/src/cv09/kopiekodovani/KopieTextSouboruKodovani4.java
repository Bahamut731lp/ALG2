/*
 *  Copyright (C) 2022 Jirina
 *  This program is part of Java programing education. 
 *  You can use it as you need to learn Java. 
 */
package cv09.kopiekodovani;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.OpenOption;
import java.nio.file.Paths;
import java.nio.file.StandardOpenOption;
import java.util.Scanner;

/**
 *
 * @author jirina.kralovcova
 */
public class KopieTextSouboruKodovani4 {

    private static final Scanner sc = new Scanner(System.in);

    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) throws IOException {
        System.out.print("Vstupni soubor: ");
        String jmIn = sc.nextLine();
        System.out.print("Kodovani vstupniho souboru: ");
        String kodIn = sc.nextLine();
        System.out.print("Vystup do souboru: ");
        String jmOut = sc.nextLine();
        System.out.print("Kodovani vystupniho souboru: ");
        String kodOut = sc.nextLine();
        
        // vyuziti bajtoveho pristupu k souborum
        // nacteni bajtu do pole
        // prevod na retezec v zadanem kodovani pro vstupni soubor
        // konverze retezce na sled (pole) bajtu - v danem vystupnim kodovani
        // ulozeni obsahu pole bajtu do souboru
        // cteni/zapis bajtu z/do souboru - pomoci metod tridy Files
        byte[] bin = Files.readAllBytes(Paths.get(jmIn));
        System.out.println(bin.length);
        String text = new String(bin, kodIn);
        byte[] bout = text.getBytes(kodOut);
        Files.write(Paths.get(jmOut), bout, StandardOpenOption.APPEND,
                StandardOpenOption.CREATE);
        
    }
    
}
