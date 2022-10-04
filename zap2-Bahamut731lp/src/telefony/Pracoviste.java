package telefony;

/**
 *
 * @author 
 */
public class Pracoviste {
    private int cisloPrac;
    private String zkratka;
    private String nazev;

    public Pracoviste(int cisloPracoviste, String zkratka, String nazev) {
        this.cisloPrac = cisloPracoviste;
        this.zkratka = zkratka;
        this.nazev = nazev;
    }

    public int getCisloPracoviste() {
        return cisloPrac;
    }

    public String getZkratka() {
        return zkratka;
    }

    public String getNazev() {
        return nazev;
    }

    public void setZkratka(String zkratka) {
        this.zkratka = zkratka;
    }

    public void setNazev(String nazev) {
        this.nazev = nazev;
    }

    @Override
    public String toString() {
        return "Pracoviste{" + "cisloPracoviste=" + cisloPrac + ", zkratka=" + zkratka + ", nazev=" + nazev + '}';
    }
    
}
