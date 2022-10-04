/*
 * Copyright (C) 2022 Jirina Kralovcova
 * This program is part of Java programing education. 
 * You can use it as you need to learn Java. 
 */
package cv12.banka;

import java.io.BufferedInputStream;
import java.io.BufferedOutputStream;
import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.io.EOFException;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;

/**
 * Trida, jejiz instance reprezentuji konkretni bankovni ucet.
 * 
 * @author Jirina
 */
public class Ucet {

    public static int MIN_CISLO_UCTU = 100;
    private File file;

    private Ucet(File file) {
        this.file = file;
    }

    /**
     * Metoda pro ziskani instance tridy Ucet.
     * @param adr umisteni souboru uctu
     * @param cisloUctu cisslo uctu vetsi rovno 100
     * @return instanci tridy ucet
     * @throws IOException v pripade, ze nelze vytvorit soubor 
     * @throws IllegalArgumentException v pripade neplatneho cisla uctu
     */
    public static Ucet getInstance(File adr, int cisloUctu) throws IOException {
        if (cisloUctu < MIN_CISLO_UCTU) {
            throw new IllegalArgumentException("Neplatne cislo uctu: " + cisloUctu);
        }
        File file = new File(adr, getJmenoUctu(cisloUctu));
        if (!file.exists()) {
            file.createNewFile();
        }
        return new Ucet(file);
    }

    private static String getJmenoUctu(int cislo) {
        return String.format("ucet%010d.dat", cislo);
    }

    /** 
     * Zajisti ulozeni transakce do souboru uctu.
     * @param tr objekt s daty transakce
     * @throws IOException 
     */
    public void ulozTransakci(Transakce tr) throws IOException {
        try ( DataOutputStream dos = new DataOutputStream(
                new BufferedOutputStream(new FileOutputStream(file, true)))) {
            tr.uloz(dos);
        }
    }

    public String getVypis() throws IOException {
        StringBuilder sb = new StringBuilder();
        try ( DataInputStream dis = new DataInputStream(
                new BufferedInputStream(new FileInputStream(file)))) {
            boolean konec = false;

            while (!konec) {
                try {
                    Transakce tr = Transakce.nacti(dis);
                    sb.append(tr.toString());
                    sb.append(String.format("%n"));
                } catch (EOFException e) {
                    konec = true;
                }
            }

        }
        return sb.toString();
    }

}
