package cv11.zavody;

import java.util.InputMismatchException;

/**
 *
 * @author Jirina
 */
class CasDne {

    public static final String TIME_PARTS_SEPARATOR = ":";
    public static final String FORMAT_STRING
            = "%02d" + TIME_PARTS_SEPARATOR + "%02d" + TIME_PARTS_SEPARATOR
            + "%02d" + TIME_PARTS_SEPARATOR + "%02d";

    private final int hod;
    private final int min;
    private final int sek;
    private final int set;
    private final int pocetMilis; // zvazit int/long

    CasDne(int hod, int min, int sek, int set) {
        this.hod = hod;
        this.min = min;
        this.sek = sek;
        this.set = set;
        pocetMilis = pocetMilisekund(hod, min, sek, set);
    }

    CasDne(int hod, int min, int sek) {
        this(hod, min, sek, 0);
    }

    CasDne(int hod, int min) {
        this(hod, min, 0, 0);
    }

    /**
     * Vytvori instanci ze zadaneho poctu milisekund vzhledem k implementaci
     * tridy dojde ke ztrate informace (jednotky sekund nejsou
     * zaznamenane/zachovane ve vytvorene instanci)
     *
     * @param milis pocet milisekund
     * @return instanci tridy CasDne
     */
    static CasDne getInstanceFromMilis(int milis) {
        int pocet = milis / 100; // setiny 
        int set = pocet % 100;
        pocet /= 100; // sekundy
        int sec = pocet % 60;
        pocet /= 60;
        int min = pocet % 60;
        int hod = pocet / 60;
        return new CasDne(hod, min, sec, set);
    }

    /**
     * Z textoveho retezce, ve kterem jsou dilci casove udaje oddelene
     * dvojteckou vytvori objekt tridy casovyUdaj nemusi obsahovat vsechny ctyri
     * dilci udaje, v tom pripade ty s mensi vyznamnosti nabyvaji hodnoty 0
     *
     * @param casovyUdaj textovy retezec s casovy udajem
     * @return neve vytvoreny objekt
     * @throws IllegalArgumentException v pripade chybneho formatu textoveho
     * retezce a textovy retezec tak nelze prevest na CasDne
     */
    static CasDne parseFromString(String casovyUdaj) {
//        if (neplatnycasovyUdaj) {  // regularni vyraz
//            throw new IllegalArgumentException("Chybny format casoveho udaje " + casovyUdaj);
//        }
        try {
            String[] dataStr = casovyUdaj.split(TIME_PARTS_SEPARATOR);
            int hod = Integer.parseInt(dataStr[0]);
            int min = (dataStr.length > 1) ? (Integer.parseInt(dataStr[1])) : (0);
            int sec = (dataStr.length > 2) ? (Integer.parseInt(dataStr[2])) : (0);
            int set = (dataStr.length > 3) ? (Integer.parseInt(dataStr[3])) : (0);
            return new CasDne(hod, min, sec, set);
        } catch (NumberFormatException ex) {
            throw new IllegalArgumentException("Chybny format casoveho udaje " + casovyUdaj);
        }
    }

    public int getHod() {
        return hod;
    }

    public int getMin() {
        return min;
    }

    public int getSek() {
        return sek;
    }

    public int getSet() {
        return set;
    }

    public int getPocetMilis() {
        return pocetMilis;
    }

    @Override
    public String toString() {
        return "" + hod + TIME_PARTS_SEPARATOR + min + TIME_PARTS_SEPARATOR
                + sek + TIME_PARTS_SEPARATOR + set;
    }

    public String getAsString() {
        return String.format(FORMAT_STRING, hod, min, sek, set);
    }

    private static int pocetMilisekund(int hod, int min, int sek, int set) {
        return (((((hod * 60) + min) * 60 + sek) * 100) + set) * 10;
    }
}
