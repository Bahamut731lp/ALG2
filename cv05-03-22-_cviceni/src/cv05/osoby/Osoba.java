/*
 *  Copyright (C) 2022 Jirina
 *  This program is part of Java programing education. 
 *  You can use it as you need to learn Java. 
 */
package cv05.osoby;

/**
 *
 * @author jirina.kralovcova
 */
public class Osoba {
    private final String jmeno;
    private String prijmeni;
    private final int rc;

    public Osoba(String jmeno, String prijmeni, int rc) {
        this.jmeno = jmeno;
        this.prijmeni = prijmeni;
        this.rc = rc;
    }

    public String getJmeno() {
        return jmeno;
    }

    public String getPrijmeni() {
        return prijmeni;
    }

    public int getRc() {
        return rc;
    }

    public void setPrijmeni(String prijmeni) {
        this.prijmeni = prijmeni;
    }

    @Override
    public String toString() {
        return "Osoba{" + "jmeno=" + jmeno + ", prijmeni=" + prijmeni + ", rc=" + rc + '}';
    }
    
    
    
}
