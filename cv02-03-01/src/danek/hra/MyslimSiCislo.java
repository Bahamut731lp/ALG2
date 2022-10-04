package danek.hra;

import java.util.Random;

public class MyslimSiCislo {
    private static final Random rnd = new Random();
    private final int dolniMez;
    private final int horniMez;
    private int pocetTipu;
    private int mysleneCislo;
    private boolean konecHry;

    public MyslimSiCislo(int dolniMez, int horniMez) {
        this.dolniMez = dolniMez;
        this.horniMez = horniMez;
        // generovani tipu
        mysleneCislo = rnd.nextInt(horniMez - dolniMez + 1) + dolniMez;
        
        boolean uhadnuto = false;
        System.out.printf("Myslím si číslo mezi %s a %s...\n", dolniMez, horniMez, mysleneCislo);       
    }

    public MyslimSiCislo(int horniMez) {
        this(1, horniMez);
    }
    
    // dalsi metody
    public boolean Hadam(int tip) {
    	pocetTipu++;
    	
        if (tip == mysleneCislo) {
        	konecHry = true;
        	 System.out.println("Gratulace, uhádli jste mé číslo!");
        }
        else {
        	System.out.printf("Myšlené číslo je %s než %s \n", tip < mysleneCislo ? "větší" : "menší", tip);
        }
        
		return konecHry;
    }
    
    public int getPocetTipu() {
    	return pocetTipu;
    }
}
