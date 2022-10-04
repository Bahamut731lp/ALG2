/*
 *  Copyright (C) 2022 Jirina
 *  This program is part of Java programing education. 
 *  You can use it as you need to learn Java. 
 */
package cv09.kopie;

import java.io.BufferedInputStream;
import java.io.BufferedOutputStream;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.util.Scanner;

/**
 *
 * @author jirina.kralovcova
 */
public class KopieSouboru3 {

    private static final Scanner sc = new Scanner(System.in);

    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) throws FileNotFoundException, IOException {
        System.out.println("Zadej cestu k vstupnimu souboru: ");
        String soubor = sc.nextLine();
        System.out.println("Zadej cestu ke kopii souboru: ");
        String kopie = sc.nextLine();
        
        // try-with-resources pro korektni uzavreni
        // pouziti bufferovanych proudů BufferedInputStream, BufferedOutputStream
        try (BufferedInputStream bis = new BufferedInputStream(new FileInputStream(soubor));
                BufferedOutputStream bos = new BufferedOutputStream(new FileOutputStream(kopie))) {
            int b;
            while ((b = bis.read()) >= 0) {
                bos.write(b);
            }
        }
    }
    
}
