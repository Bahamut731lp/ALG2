/*
 *  Copyright (C) 2022 Jirina
 *  This program is part of Java programing education. 
 *  You can use it as you need to learn Java. 
 */
package cv11.zavody;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.nio.file.StandardOpenOption;
import java.util.ArrayList;
import java.util.List;

/**
 *
 * @author jirina.kralovcova
 */
public class ZavodRegistrace {

    private Path regFile;
    private int posledniRegCislo;

    private ZavodRegistrace(Path path, int pRegCislo) {
        regFile = path;
        posledniRegCislo = pRegCislo;
    }

    public static ZavodRegistrace getInstance(String soubor) throws IOException {
        return getInstance(Paths.get(soubor));
    }

    public static ZavodRegistrace getInstance(Path path) throws IOException {
        // overit, zda soubor existuje
        path = path.toAbsolutePath().normalize();

        if (!Files.isRegularFile(path)) {
            Files.createFile(path);
            ArrayList<String> lines = new ArrayList<>();
            lines.add("regCislo;jmeno;prijmeni;rocnik");
            Files.write(path, lines);
            return new ZavodRegistrace(path, 0);
        }
        // popripade nacist data
        int max = 0;
        try ( BufferedReader br = Files.newBufferedReader(path)) {
            String line;
            br.readLine();
            int regN;
            while ((line = br.readLine()) != null) {
                line = line.trim();
                if (line.length() == 0) {
                    continue;
                }
                // mozna validace
                String[] data = line.split(";");
                regN = Integer.parseInt(data[0]);
                if (regN > max) {
                    max = regN;
                }
            }

        }
        return new ZavodRegistrace(path, max);
    }

    public void pridejZavodnika(String jmeno, String prijmeni, int rocnik) throws IOException {
        posledniRegCislo++;
        try ( BufferedWriter bw = Files.newBufferedWriter(regFile, StandardOpenOption.APPEND, StandardOpenOption.WRITE)) {

            String zapis = String.format("%d;%s;%s;%d", posledniRegCislo, jmeno, prijmeni, rocnik);
            bw.write(zapis);
            bw.newLine();

        } catch (Exception ex) {
            posledniRegCislo--;

            throw ex;
        }

    }

    public List<String> getVypisZavodniku() throws IOException {        
        return getVypisZavodniku(false);
    }

//    public String getVypisZavodniku() {
//        
//    }
    public List<String> getVypisZavodniku(boolean dleJmen) throws IOException {
        List<Zavodnik> zavodnici = new ArrayList<>();
        try (BufferedReader br = Files.newBufferedReader(regFile)) {
            String line;
            br.readLine();
            while ((line = br.readLine()) != null) {
                line = line.trim();
                if (line.length() == 0) {
                    continue;
                }
                // mozna validace
                String[] data = line.split(";");
                int regN = Integer.parseInt(data[0]);
                String jmeno = data[1];
                String prijmeni = data[2];
                int rocnik = Integer.parseInt(data[3]);
                Zavodnik zavodnik = new Zavodnik(jmeno, prijmeni, regN, rocnik);
                zavodnici.add(zavodnik);
            }
            
        } 
        if (dleJmen) {
            zavodnici.sort(Zavodnik.COMP_CELE_JMENO);
        }
        List<String> result = new ArrayList<>();
        for(Zavodnik zavodnik : zavodnici) {
            result.add(zavodnik.toString());
        }
        return result;
    }
}
