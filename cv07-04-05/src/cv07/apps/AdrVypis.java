/*
 *  Copyright (C) 2022 Jirina
 *  This program is part of Java programing education. 
 *  You can use it as you need to learn Java. 
 */
package cv07.apps;

import static cv07.apps.AdrFileInfo.sc;
import java.io.File;
import java.io.FileFilter;
import java.io.IOException;
import java.nio.file.DirectoryStream;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.Arrays;
import java.util.Scanner;

/**
 *
 * @author jirina.kralovcova
 */
public class AdrVypis {

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
            System.out.println("Zadej cestu k adresari na disku (exit pro ukonceni)");
            vstup = sc.nextLine().trim();
            if (!vstup.equalsIgnoreCase("exit")) {
                // vstup predstavuje cestu k souboru
                // zpracovani vstupu
                if (vstup.equals("")) {
                    vstup = System.getProperty("user.dir");
                }
                soubor = new File(vstup);
                if (!soubor.isDirectory()) {
                    System.out.println("Zadana cesta na disku nexistuje");
                    System.out.println("nebo se nejedna o adresar");
                    continue;
                }

                System.out.println("");
                System.out.println("Vypis obsahu adresare");
                System.out.println("Polozky (alfanumericky) usporadane");
                String[] pole = soubor.list();
                Arrays.sort(pole);
                for (String s : pole) {
                    System.out.println("  " + s);
                }

                System.out.println("");
                System.out.println("Vypis obsahu adresare");
                System.out.println("Pridana informace o souboru nebo adresari");
                File[] ff = soubor.listFiles();
                for (File f : ff) {
                    if (f.isDirectory()) {
                        System.out.println("  adresář  " + f.getName());
                    } else {
                        System.out.println("  soubor   " + f.getName());
                    }
                }
                
                ff = soubor.listFiles();
                // vyuziti anonymni tridy
                //ff = soubor.listFiles(new FileFilter() {
                //    @Override
                //    public boolean accept(File pathname) {
                //        return pathname.isFile();
                //    }
                //});
                //vyuziti lambda vyrazu
                ff = soubor.listFiles((File pathname) -> pathname.isFile());
                System.out.println("");
                System.out.println("Filtrovany vypis adresare");
                System.out.println("Vypis pouze souboru, celkova velikost souboru v adresari");
                long velikost = 0;
                for (File f : ff) {
                    System.out.println("  " + f.getName());
                    velikost += f.length();
                }
                System.out.println("celková velikost všech souborů: " + velikost + " B");
                
                cesta = Paths.get(vstup);
                System.out.println("");
                System.out.println("Jeste jednou vypis adresare");
                System.out.println("Vyuziti objektu typu Path");
                DirectoryStream<Path> vypis = Files.newDirectoryStream(cesta);
                for (Path p : vypis) {
                    System.out.println("  " + p.getFileName());
                }
            }

        } while (vstup.compareToIgnoreCase("exit") != 0);
    }

}
