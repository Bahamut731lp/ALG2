/*
 *  Copyright (C) 2022 Jirina
 *  This program is part of Java programing education. 
 *  You can use it as you need to learn Java. 
 */
package cv09.kopie;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.util.Scanner;

/**
 *
 * @author jirina.kralovcova
 */
public class KopieSouboru {

    private static final Scanner sc = new Scanner(System.in);

    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) throws IOException {
        System.out.println("Zadej cestu k vstupnimu souboru: ");
        String soubor = sc.nextLine();
        System.out.println("Zadej cestu ke kopii souboru: ");
        String kopie = sc.nextLine();
        
        // try-finally pro korektni uzavreni souboru
        // osetreni vyjimek pri manipulaci se souborem
        // nejsou osetreny vyjimky pri uzavirani souboru
        // pouziti FileInputStream, FileOutputStream 
        //  - nejsou to bufferovane proudy
        FileInputStream fis = null;
        FileOutputStream fos = null;
        try {
            fis = new FileInputStream(soubor);
            fos = new FileOutputStream(kopie);
            byte[] b = new byte[256]; int n;
            while ((n = fis.read(b)) >= 0) {
                fos.write(b, 0, n);
            }
        } catch (FileNotFoundException ex) {
            System.out.println("nelze otevrit jeden ze souboru");
            ex.printStackTrace();
        } catch (IOException ex) {
            System.out.println("Jina chyba");
            ex.printStackTrace();
        }finally {
            try {
                if (fis != null) {
                    fis.close();
                }
            } finally {
                if (fos != null) {
                    fos.close();
                }
            }
        }
    }

}
