/*
 *  Copyright (C) 2022 Jirina
 *  This program is part of Java programing education. 
 *  You can use it as you need to learn Java. 
 */
package cv05.apps;

import cv05.osoby.Osoba;
import cv05.osoby.Student;

/**
 *
 * @author jirina.kralovcova
 */
public class TestOsoby {

    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) {
        Osoba os = new Osoba("Jan", "Zikan", 1015368975);
        System.out.println(os.toString());
        Student st = new Student(os, "M22156300"); 
        System.out.println(st);
        System.out.println(st.getJmeno());
        System.out.println(st.getPrijmeni());
        System.out.println(st.getRc());
        System.out.println(st.getStCislo());
    }
    
}
