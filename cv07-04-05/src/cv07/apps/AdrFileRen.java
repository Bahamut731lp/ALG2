/*
 *  Copyright (C) 2022 Jirina
 *  This program is part of Java programing education. 
 *  You can use it as you need to learn Java. 
 */
package cv07.apps;

import java.io.File;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.Scanner;

/**
 *
 * @author jirina.kralovcova
 */
public class AdrFileRen {

    public static final Scanner sc = new Scanner(System.in);

    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) throws IOException {
        String jm1, jm2;
        System.out.println("Prejmenovani souboru");
        System.out.println("Vyuziti objektu typu File");
        System.out.println("Zadej cestu (k souboru nebo adresari)");
        while ((jm1 = sc.nextLine().trim()).length() > 0) {
            System.out.println("Zadej nove jmeno/cestu");
            jm2 = sc.nextLine();
            File f1 = new File(jm1);
            System.out.println(f1);
            if (f1.exists()) {
                // prejmenovani/presunuti
                f1.renameTo(new File(jm2));
            } else {
                //f1.createNewFile();
                //f1.mkdir();
            }
            // data objektu f1 zustavaji nezmenena
            System.out.println(f1);
            System.out.println("");
            System.out.println("Zadej cestu (k souboru nebo adresari)");
        }
        
        System.out.println("");
        System.out.println("Prejmenovani souboru znovu a jinak");
        System.out.println("Vyuziti objektu typu Path");
        System.out.println("Zadej cestu (k souboru nebo adresari)");
        while ((jm1 = sc.nextLine().trim()).length() > 0) {
            System.out.println("Zadej nove jmeno/cestu");
            jm2 = sc.nextLine();
            Path p1 = Paths.get(jm1);
            Path p2 = Paths.get(jm2);
            System.out.println(p1);
            if (Files.exists(p1)) {
                // presunuti/prejmenovani
                Files.copy(p1, p2);
            } else {
                //Files.createFile(p1);
                //Files.createDirectory(p1);
            }
            System.out.println("");
            System.out.println("Zadej cestu (k souboru nebo adresari)");
        }
    }

}
