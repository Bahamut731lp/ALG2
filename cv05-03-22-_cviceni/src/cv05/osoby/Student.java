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
public class Student extends Osoba{
    private final String stCislo;

    public Student(String stCislo, String jmeno, String prijmeni, int rc) {
        super(jmeno, prijmeni, rc);
        this.stCislo = stCislo;
    }
    
    public Student(Osoba os, String stCislo){
        super(os.getJmeno(), os.getPrijmeni(), os.getRc());
        this.stCislo = stCislo;
    }

    public String getStCislo() {
        return stCislo;
    }

    @Override
    public String toString() {
        return "Student{" + "stCislo=" + stCislo + ", " +super.toString() + '}';
    }
    
    
}
