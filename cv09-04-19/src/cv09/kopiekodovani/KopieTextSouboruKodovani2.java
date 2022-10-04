/*
 *  Copyright (C) 2022 Jirina
 *  This program is part of Java programing education. 
 *  You can use it as you need to learn Java. 
 */
package cv09.kopiekodovani;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.PrintWriter;
import java.io.UnsupportedEncodingException;
import java.util.Scanner;

/**
 *
 * @author jirina.kralovcova
 */
public class KopieTextSouboruKodovani2 {

    private static final Scanner sc = new Scanner(System.in);

    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) throws FileNotFoundException, UnsupportedEncodingException {
        System.out.print("Vstupni soubor: ");
        String jmIn = sc.nextLine();
        System.out.print("Kodovani vstupniho souboru: ");
        String kodIn = sc.nextLine();
        System.out.print("Vystup do souboru: ");
        String jmOut = sc.nextLine();
        System.out.print("Kodovani vystupniho souboru: ");
        String kodOut = sc.nextLine();
        
        // try-with-resources
        // pro nacteni obsahu textoveho souboru pouzit objekt tridy Scanner
        // pro zapis PrintWriter
        try(Scanner in = new Scanner(new File(jmIn), kodIn); 
                PrintWriter out = new PrintWriter(jmOut, kodOut)){
            String radek;
            while(in.hasNextLine()){
                radek = in.nextLine();
                out.println(radek);
            }
        }
    }
    
}
