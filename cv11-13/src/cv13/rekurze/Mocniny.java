/*
 *  Copyright (C) 2022 Jirina
 *  This program is part of Java programing education. 
 *  You can use it as you need to learn Java. 
 */

package cv13.rekurze;

import java.util.Scanner;

/**
 *
 * @author jirina.kralovcova
 */
public class Mocniny {

    private static final Scanner sc = new Scanner(System.in);
    
    /**
     * Rekurzivni metoda pro vypocet celociselne mocniny realneho cisla.
     * @param x - zaklad mocniny, realne cislo
     * @param n - mocnina, cele cislo
     * @return vypoctena mocnina
     */ 
    public static double mocninaRek(double x, int n) {
        if (n < 0) return 1./mocninaRek(x, -n);
        if (n == 0) return 1.;
        return x * mocninaRek(x, n-1);
    }
    
    public static double mocnina(double x, int n) {
        if (n < 0) return 1./mocnina(x, -n);
        double v = 1.;
        for (int i = 0; i <= n; i++){
            v *= x;
        }
        return v;
    }

    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) {
        
    }

}
