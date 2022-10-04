package danek.hra;

public class HadamCislo {
    private int dolniMez;
    private int horniMez;
    private int pocetTipu;
    private int posledniTip;
    private boolean konecHry;
    
    public HadamCislo(int dolniMez, int horniMez) {
        this.dolniMez = dolniMez;
        this.horniMez = horniMez;
        // generovani tipu
       
        boolean uhadnuto = false;
        //System.out.printf("Myslím si číslo mezi %s a %s...\n", dolniMez, horniMez, mysleneCislo);       
    }

    public HadamCislo(int horniMez) {
        this(1, horniMez);
    }
    
    public int novyTip() {
    	this.posledniTip = (dolniMez + horniMez) / 2;
    	return this.posledniTip;
    }
    
    public void jeMensi() {
    	this.horniMez = posledniTip;
    }
    
    public void jeVetsi() {
    	this.dolniMez = posledniTip;
    }
    
    public void jeStejne() {
    	this.konecHry = true;
    	System.out.printf("Úhádl jsem vaše číslo! Bylo to číslo %s!", this.posledniTip);
    }
    
    public int getPocetTipu() {
    	return this.pocetTipu;
    }
    
    public boolean jeKonec() {
    	return this.konecHry;
    }
    
}
