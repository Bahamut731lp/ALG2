/*
 *  Copyright (C) 2022 Jirina
 *  This program is part of Java programing education. 
 *  You can use it as you need to learn Java. 
 */
package cv09.kopiekodovani;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.IOException;
import java.nio.charset.Charset;
import java.nio.file.Files;
import java.nio.file.Path;
import java.util.Scanner;

/**
 *
 * @author jirina.kralovcova
 */
public class KopieTextSouboruKodovani5 {

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

        // try-with-resources  pro spravne uzavreni
        // objekty bufferovanych proudu ziskány pomoci odpovidajicich metod z Files
        // vytvoreni techto objektu - obdobne jako v prvni variante, zapis je jednodussi
        try (BufferedReader br = Files.newBufferedReader(Path.of(jmIn), Charset.forName(kodIn));  
                BufferedWriter bw = Files.newBufferedWriter(Path.of(jmOut), Charset.forName(kodOut))) {
            String radek;
            while ((radek = br.readLine()) != null) {
                bw.write(radek);
                bw.newLine();
            }
        }
    }

}
