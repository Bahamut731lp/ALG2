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
public class Hanoi {

    private static final Scanner sc = new Scanner(System.in);
    
    public static void hanoi(int n, char od, char kam, char pom) {
        if (n > 0) {
            hanoi(n-1, od, pom, kam);
            System.out.format("prenes disk %d z %c na %c %n", n, od, kam);
            hanoi(n-1, pom, kam, od);
        }
    }

    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) {
        hanoi(3, 'A', 'B', 'C');
    }

}
