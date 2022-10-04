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
public class KopieSouboru2 {

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
        // odchyceni a osetreni vyjimek
        // vstup a vystup nejsou bufferovane
        // ctení/zapis vzdy jedinou operaci
        try (FileInputStream fis = new FileInputStream(soubor);
                FileOutputStream fos = new FileOutputStream(kopie)) {

            byte[] b = fis.readAllBytes();
            fos.write(b);
        } catch (FileNotFoundException ex) {
            System.out.println("nelze otevrit jeden ze souboru");
            ex.printStackTrace();
        } catch (IOException ex) {
            System.out.println("Jina chyba");
            ex.printStackTrace();
        }
    }

}
