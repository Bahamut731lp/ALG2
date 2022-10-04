/*
 *  Copyright (C) 2022 Jirina
 *  This program is part of Java programing education. 
 *  You can use it as you need to learn Java. 
 */
package cv09.kopiekodovani;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.util.Scanner;

/**
 *
 * @author jirina.kralovcova
 */
public class KopieTextSouboruKodovani {

    private static final Scanner sc = new Scanner(System.in);

    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) throws IOException {
        System.out.println("Implicitni kodovani pro zpracovani textovych souboru (zpracovani po znacich");
        System.out.println("  " + System.getProperty("file.encoding"));
        System.out.println("");
        System.out.println(System.getProperty("file.encoding"));
        System.out.print("Vstupni soubor: ");
        String jmIn = sc.nextLine();
        System.out.print("Kodovani vstupniho souboru: ");
        String kodIn = sc.nextLine();
        System.out.print("Vystup do souboru: ");
        String jmOut = sc.nextLine();
        System.out.print("Kodovani vystupniho souboru: ");
        String kodOut = sc.nextLine();
        
        // try-with-resources  pro spravne uzavreni
        // pouziti bufferovanych proudu, ktere ziskavaji vstup 
        // "filtrovany" objekty InputStreamReader nebo OutputStreamWriter
        try(BufferedReader r = new BufferedReader(new InputStreamReader(new FileInputStream(jmIn),kodIn));
                BufferedWriter w = new BufferedWriter(new OutputStreamWriter(new FileOutputStream(jmOut),kodOut))){
            int ch;
            while((ch = r.read()) >= 0){
                System.out.println(ch + "  " +(char)ch);
                w.write(ch);
            }
        }
    }
}
