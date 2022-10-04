/*
 *  Copyright (C) 2022 Jirina
 *  This program is part of Java programing education. 
 *  You can use it as you need to learn Java. 
 */
package cv11.zavody;

import java.io.FileNotFoundException;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.util.Arrays;
import java.util.Collection;
import java.util.Collections;
import java.util.Iterator;
import java.util.List;
import java.util.Map;
import java.util.TreeMap;

/**
 *
 * @author jirina.kralovcova
 */
public class ZavodVysledky {

    private Map<Integer, Zavodnik> zavodnici;

    public ZavodVysledky(Map<Integer, Zavodnik> zavodnici) {
        this.zavodnici = zavodnici;
    }

    /**
     *
     * @param regSoubor
     * @return
     * @throws FileNotFoundException
     * @throws IOException
     * @throws ArrayIndexOutOfBoundsException
     * @throws NumberFormatException
     *
     */
    public ZavodVysledky getInstance(String regSoubor) throws FileNotFoundException, IOException {
        Path p = Path.of(regSoubor);
        p = p.toAbsolutePath().normalize();
        if (!Files.isRegularFile(p)) {
            throw new FileNotFoundException("Soubor neexistuje " + p.toString());
        }
        Map<Integer, Zavodnik> zav = new TreeMap<>();
        List<String> lin = Files.readAllLines(p);
        Iterator<String> it = lin.iterator();
        it.next();
        while (it.hasNext()) {
            String str = it.next().trim();
            if (str.length() == 0) {
                continue;
            }
            String[] dataStr = str.split(";");
            Zavodnik z = new Zavodnik(dataStr[1], dataStr[2],
                    Integer.parseInt(dataStr[0]), Integer.parseInt(dataStr[4]));
            zav.put(z.getRegCislo(), z);
        }
        return new ZavodVysledky(zav);
    }

    public void zaznamenejDataStartu(String soubStart) throws IOException {
        Path p = Path.of(soubStart);
        List<String> lin = Files.readAllLines(p);
        Iterator<String> it = lin.iterator();
        it.next();
        while (it.hasNext()) {
            String str = it.next().trim();
            if (str.length() == 0) {
                continue;
            }
            String[] dataStr = str.split(";");
            Integer regCislo = Integer.valueOf(dataStr[0]);
            Zavodnik zav = zavodnici.get(regCislo);
            zav.zaznamenejCasStartu(dataStr[1]);
        }
    }

    public void zaznamenejDataCile(String soubStart) {

    }

    // vraci viceradkovy textovy retezec
    public String vysledkovaListina() {
        Zavodnik[] dataZav = 
                zavodnici.values().toArray(new Zavodnik[zavodnici.size()]);
        Arrays.sort(dataZav, Zavodnik.COMP_DOBA_ZAVODU);
        StringBuilder sb = new StringBuilder();
        for (Zavodnik z : dataZav) {
            if(z == null) continue; // popripade break
            sb.append(z.toStringSVysledky());
            sb.append("\n");
        }
        return sb.toString();
    }
}
