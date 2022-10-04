/*
 * Copyright (C) 2022 Jirina Kralovcova
 * This program is part of Java programing education. 
 * You can use it as you need to learn Java. 
 */
package hry.piskvorky;

/**
 * Tento interface definuje rozhrani trid objektu hry Piskvorky.
 *
 * @author ALG
 */
public interface PiskvorkyInterface {

    public static final int MIN_ROZMER = 5;
    public static final int MAX_ROZMER = 30;

    /**
     * Pro zadane rozmery hraci plochy vrati objekt hry piskvorky
     *
     * @param pr pocet radku
     * @param ps pocet sloupcu
     * @return vytvoreny objekt
     * @throws PiskvorkyException v pripade chybnych rozmeru plochy
     */
    public static PiskvorkyInterface getInstance(int pr, int ps) {
        if (pr < MIN_ROZMER) {
            throw new PiskvorkyException("Maly pocet radku", 101, "" + pr);
        }
        if (pr > MAX_ROZMER) {
            throw new PiskvorkyException("Velky pocet radku", 101, "" + pr);
        }
        if (ps < MIN_ROZMER) {
            throw new PiskvorkyException("Maly pocet sloupcu", 101, "" + ps);
        }
        if (ps > MAX_ROZMER) {
            throw new PiskvorkyException("Velky pocet sloupcu", 101, "" + ps);
        }
        return new Piskvorky(pr, ps);
    }

    /**
     * Polozi dalsi symbol aktualne hrajiciho hrace na pozici (r, s), pokud je
     * dana pozice volna. Metoda vraci informaci o tom jake nejdelsi n-tice je
     * nove polozeny symbol soucasti.
     *
     * @param r index radku
     * @param s index sloupce
     * @return delku nejdelsi souvisle n-tice, jejiz soucasti je nove polozeny
     * symbol.
     * @throws PiskvorkyException v pripade ze je pozice mimo rozsah nebo je
     * pozice obsazena, nebo jde o tah po ukonceni hry. Konkretni chybovy stav z
     * tecto tri uvedenych lze urcit z chyboveho kodu generovane vyjimky.
     */
    public int polozDalsiSymbol(int r, int s);

    /**
     * Poskytuje cislo symbolu polozene na pozici (r, s).
     *
     * @param r index radku
     * @param s index sloupce
     * @return symbol 0,1,2
     * @throws PiskvorkyException v pripade, ze zadana pozice je mimo rozsah
     * hraciho pole.
     */
    public int getSymbol(int r, int s);

    /**
     * Poskytuje pocet radku hraciho pole
     *
     * @return pocet radku
     */
    public int getPocetRadku();

    /**
     * Poskytuje pocet slopcu hraciho pole
     *
     * @return pocet sloupcu
     */
    public int getPocetSloupcu();

    /**
     * Informace o ukonceni hry
     *
     * @return true po ukonceni hry v ostatnich pripadech false
     */
    public boolean jeKonecHry();

    /**
     * Pred ukoncenim vraci cislo hrace ktery prave ted hraje. Po ukonceni hry
     * vraci hodnotu 0.
     *
     * @return cislo hrace nebo 0
     */
    public int getHrajeHrac();

    /**
     * Po ukonceni hry poskytuje cislo hrace (1 nebo 2), ktery vyhral. Pred
     * ukoncenim hry vraci hodnotu 0.
     *
     * @return cislo hrace nebo 0
     */
    public int getCisloVyherce();
}
