/*
 *  Copyright (C) 2022 Jirina
 *  This program is part of Java programing education. 
 *  You can use it as you need to learn Java. 
 */
package cv13.rekurze;

import java.math.BigInteger;

/**
 *
 * @author jirina.kralovcova
 */
public class Faktorialy {
    
    public static long fakRek(int n) {
        if (n > 1) {
            return n * fakRek(n-1);
        }
        return 1;
    }
    
    public static long fak (int n) {
       long f = 1;
       for (int i = 2; i <= n; i++) {
           f *= i;
       }
       return f;
    }
    
    public static BigInteger faktorial (int n) {
        BigInteger f = new BigInteger("1");
        for (int i = 2; i <= n; i++) {
           f = f.multiply(new BigInteger("" + i));
       }
       return f;
    }
    
    public static void main(String[] args) {
        for (int i = 2; i < 15; i++) {
            System.out.println(faktorial(i));
            System.out.println(fak(i));
            System.out.println(fakRek(i));
            System.out.println("");
        }
    }
}
