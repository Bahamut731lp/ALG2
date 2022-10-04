/*
 *  Copyright (C) 2022 Jirina
 *  This program is part of Java programing education. 
 *  You can use it as you need to learn Java. 
 */
package cv09.kopiekodovani;

import java.io.IOException;
import java.nio.charset.Charset;
import java.nio.file.Files;
import java.nio.file.Path;
import java.util.List;
import java.util.Scanner;

/**
 *
 * @author jirina.kralovcova
 */
public class KopieTextSouboruKodovani3 {

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
        
        // Vyuziti metod tridy Files
        // pro nacteni vsech radku do souboru
        // a jejich ulozeni do jineho vystupniho
        // vzdy se zadanim konkretni znakove sady
        List<String> radky= Files.readAllLines(Path.of(jmIn), Charset.forName(kodIn));
        Files.write(Path.of(jmOut), radky, Charset.forName(kodOut));
    }
    
}
