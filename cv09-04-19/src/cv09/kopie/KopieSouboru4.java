/*
 *  Copyright (C) 2022 Jirina
 *  This program is part of Java programing education. 
 *  You can use it as you need to learn Java. 
 */
package cv09.kopie;

import java.io.FileNotFoundException;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.OpenOption;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.nio.file.StandardOpenOption;
import java.util.Scanner;

/**
 *
 * @author jirina.kralovcova
 */
public class KopieSouboru4 {

    private static final Scanner sc = new Scanner(System.in);

    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) throws FileNotFoundException, IOException {
        System.out.println("Zadej cestu k souboru: ");
        String soubor = sc.nextLine();
        System.out.println("Zadej cestu ke kopii souboru: ");
        String kopie = sc.nextLine();
        
        // vyuziti prostredku tridy Files pro nacteni obsahu celeho souboru
        // a pro vytvoření nového souboru se stejným obsahem
        // metody Files.readAllBytes(), Files.write()
        //   zajisti korektní praci se soubory (otevreni, zpracovani, uzavreni)
        byte[] b = Files.readAllBytes(Paths.get(soubor));
        System.out.println(b.length);
        OpenOption oo;
        Files.write(Paths.get(kopie), b, StandardOpenOption.APPEND,
                StandardOpenOption.CREATE);
       
    }
}
