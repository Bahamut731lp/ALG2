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
public class AdrFileInfo {

    public static final Scanner sc = new Scanner(System.in);

    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) throws IOException {
        String vstup;
        File soubor;
        Path cesta;

        do {
            System.out.println("");
            System.out.println("Zadej textový řetězec cesty (exit pro ukonceni)");
            vstup = sc.nextLine().trim();

            if (!vstup.equalsIgnoreCase("exit")) {
                // vstup predstavuje cestu k adresáři nebo souboru
                if (vstup.equals("")) {
                    vstup = System.getProperty("user.dir");
                }
                System.out.println("pracuji s textovym retezcem: " + vstup);
                System.out.println("");
                soubor = new File(vstup);
                System.out.println("Využití prostředků třídy File");
                System.out.println("jméno souboru: " + soubor.getName());
                System.out.println("umístění: " + soubor.getParent());
                System.out.println("existuje na disku: " + soubor.exists());
                System.out.println("je to soubor: " + soubor.isFile());
                System.out.println("je to adresář: " + soubor.isDirectory());
                System.out.println("je to absolutní cesta: " + soubor.isAbsolute());
                System.out.println("absolutní cesta: " + soubor.getAbsolutePath());
                System.out.println("kanonizovaný tvar: " + soubor.getCanonicalPath());
                System.out.println("");
                cesta = Paths.get(vstup);
                System.out.println("manipulace s objektem typu Path");
                System.out.println("jméno souboru: " + cesta.getFileName());
                System.out.println("umístění: " + cesta.getParent());
                System.out.println("existuje na disku: " + Files.exists(cesta));
                System.out.println("je to soubor: " + Files.isRegularFile(cesta));
                System.out.println("je to adresář: " + Files.isDirectory(cesta));
                System.out.println("je to absolutní cesta: " + cesta.isAbsolute());
                System.out.println("absolutní cesta: " + cesta.toAbsolutePath());
                System.out.println("normalizovaný tvar: " + cesta.normalize());
                System.out.println("kanonizovaný tvar: " + cesta.toRealPath());
                System.out.println("kanonizovaný tvar: " + cesta.toAbsolutePath().normalize());
            }
        } while (vstup.compareToIgnoreCase("exit") != 0);
    }

}
