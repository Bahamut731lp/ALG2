/*
 * Copyright (C) 2022 Jirina Kralovcova
 * This program is part of Java programing education. 
 * You can use it as you need to learn Java. 
 */

package hak.piskvorky.hry;

/**
 *
 * @author ALG
 */
class Piskvorky implements PiskvorkyInterface {

    private final int[][] deska;
    private int cHrace;
    private boolean hraDokoncena;

    Piskvorky(int pr, int ps) {
        this.deska = new int[pr][ps];
        cHrace = 1;
        hraDokoncena = false;
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
     *                            pozice obsazena, nebo jde o tah po ukonceni hry. Konkretni chybovy stav z
     *                            tecto tri uvedenych lze urcit z chyboveho kodu generovane vyjimky.
     */
    @Override
    public int polozDalsiSymbol(int r, int s) {
        if(r > this.deska.length-1 || r < 0){
            throw new PiskvorkyException("index mimo rozsah", 101, "" + r);
        }
        if(s > this.deska[0].length-1 || s < 0){
            throw new PiskvorkyException("index mimo rozsah", 101, "" + s);
        }
        if(this.deska[r][s] != 0){
            throw new PiskvorkyException("Policko je jiz obsazeno");
        }
        if(this.hraDokoncena){
            throw new PiskvorkyException("uz je konec hry");
        }

        this.deska[r][s] = this.cHrace;

        int match = Math.max(
                Math.max(PiskvorkyTools.pocetStejnychVRadce(this.deska, r, s),
                        PiskvorkyTools.pocetStejnychVSloupci(this.deska, r, s)),
                Math.max(PiskvorkyTools.pocetStejnychVDiag1(this.deska, r, s),
                        PiskvorkyTools.pocetStejnychVDiag2(this.deska, r, s))
        );

        if(match == PiskvorkyInterface.VYHERNI_POCET) {
            this.hraDokoncena = true;
        }else{
            if(this.cHrace == 1) cHrace = 2; else cHrace = 1;
        }

        return match;
    }

    /**
     * Poskytuje cislo symbolu polozene na pozici (r, s).
     *
     * @param r index radku
     * @param s index sloupce
     * @return symbol 0,1,2
     * @throws PiskvorkyException v pripade, ze zadana pozice je mimo rozsah
     *                            hraciho pole.
     */
    @Override
    public int getSymbol(int r, int s) {
        if(r > this.deska.length-1 || r < 0){
            throw new PiskvorkyException("index mimo rozsah", 101, "" + r);
        }
        if(s > this.deska[0].length-1 || s < 0){
            throw new PiskvorkyException("index mimo rozsah", 101, "" + s);
        }
        return deska[r][s];
    }

    /**
     * Poskytuje pocet radku hraciho pole
     *
     * @return pocet radku
     */
    @Override
    public int getPocetRadku() {
        return this.deska.length;
    }

    /**
     * Poskytuje pocet slopcu hraciho pole
     *
     * @return pocet sloupcu
     */
    @Override
    public int getPocetSloupcu() {
        return this.deska[0].length;
    }

    /**
     * Informace o ukonceni hry
     *
     * @return true po ukonceni hry v ostatnich pripadech false
     */
    @Override
    public boolean jeKonecHry() {
        return this.hraDokoncena;
    }

    /**
     * Pred ukoncenim vraci cislo hrace ktery prave ted hraje. Po ukonceni hry
     * vraci hodnotu 0.
     *
     * @return cislo hrace nebo 0
     */
    @Override
    public int getHrajeHrac() {
       if(!this.hraDokoncena) return this.cHrace;
       return 0;
    }

    /**
     * Po ukonceni hry poskytuje cislo hrace (1 nebo 2), ktery vyhral. Pred
     * ukoncenim hry vraci hodnotu 0.
     *
     * @return cislo hrace nebo 0
     */
    @Override
    public int getCisloVyherce() {
        if(this.hraDokoncena) return this.cHrace;
        return 0;
    }
}
