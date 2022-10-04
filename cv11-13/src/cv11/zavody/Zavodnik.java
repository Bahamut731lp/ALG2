package cv11.zavody;

import java.text.Collator;
import java.util.Comparator;
import java.util.Locale;
import java.util.Objects;

/**
 *
 * @author Jirina
 */
class Zavodnik implements Comparable<Zavodnik> {

    private static final Collator colCz = Collator.getInstance(new Locale("cs", "CZ"));
    private final String jmeno;
    private final String prijmeni;
    private final int regCislo;
    private final int rocnik;
    private CasDne casStartu;
    private CasDne casCile;

    public static final Comparator<Zavodnik> COMP_ROCNIK = (Zavodnik z1, Zavodnik z2)
            -> z1.rocnik - z2.rocnik;

    public static final Comparator<Zavodnik> COMP_CELE_JMENO = (Zavodnik z1, Zavodnik z2) -> {     
        int vysl = colCz.compare(z1.prijmeni, z2.prijmeni);
        if (vysl != 0) {
            return vysl;
        }
        return colCz.compare(z1.jmeno, z2.jmeno);
    };

    public static final Comparator<Zavodnik> COMP_CAS_STARTU = (Zavodnik z1, Zavodnik z2) -> {
        long vysl = z1.casStartu.getPocetMilis() - z2.casStartu.getPocetMilis();
        if (vysl < 0) {
            return -1;
        }
        if (vysl > 0) {
            return 1;
        }
        return 0;
    };

    /**
     * Instance Comparator pro porovnani dvou objektu Zavodnik dle doby zavodu
     */
    public static final Comparator<Zavodnik> COMP_DOBA_ZAVODU = (Zavodnik z1, Zavodnik z2) -> {
        // musi byt definovany casy startu a cile zavodniku
        if (z1.getDobaZavoduMilis() < z2.getDobaZavoduMilis()) {
            return -1;
        }
        if (z1.getDobaZavoduMilis() > z2.getDobaZavoduMilis()) {
            return 1;
        }
        return 0;
    };

    Zavodnik(String jmeno, String prijmeni, int regCislo, int rocnik) {
        this.jmeno = jmeno;
        this.prijmeni = prijmeni;
        this.regCislo = regCislo;
        this.rocnik = rocnik;
    }

    public String getJmeno() {
        return jmeno;
    }

    public String getPrijmeni() {
        return prijmeni;
    }

    public int getRegCislo() {
        return regCislo;
    }

    public int getRocnik() {
        return rocnik;
    }

    public void zaznamenejCasStartu(int hod, int min, int sek) {
        casStartu = new CasDne(hod, min, sek);
    }
    
    public void zaznamenejCasStartu(String casovyUdaj) {
        casStartu = CasDne.parseFromString(casovyUdaj);
    }

    public void zaznamenejCasCile(int hod, int min, int sek, int set) {
        casCile = new CasDne(hod, min, sek, set);
    }
    
    public void zaznamenejCasCile(String casovyUdaj) {
        casCile = CasDne.parseFromString(casovyUdaj);
    }

    public CasDne getCasCile() {
        return casCile;
    }

    /**
     * Metoda pro ziskani doby zavodu zavodnika v poctech milisekund
     * @return doba zavodu v ms
     */
    public int getDobaZavoduMilis() {
        if (casStartu == null) {
            return Integer.MAX_VALUE;
        }
        if (casCile == null) {
            return Integer.MAX_VALUE - 1;
        }
        return casCile.getPocetMilis() - casStartu.getPocetMilis();
    }
    
    public String toStringSVysledky() {
        String casoveUdaje;
        if (casStartu == null) {
            casoveUdaje = "nenastoupil";
        } else if (casCile == null) {
            casoveUdaje = "nedokoncil";
        } else {
            casoveUdaje = String.format("%12s %12s %12s", 
                    casStartu, casCile, CasDne.getInstanceFromMilis(getDobaZavoduMilis()));
        }
        return String.format("%10s %10s %4d %s", 
                prijmeni, jmeno, regCislo, casoveUdaje);
    }

    @Override
    public int compareTo(Zavodnik jinyZavodnik) {
        return regCislo - jinyZavodnik.regCislo;
    }

    @Override
    public String toString() {
        return String.format("%10s %10s %4d %4d %12s %12s", 
                prijmeni, jmeno, regCislo, rocnik, casStartu, casCile);
    }

    @Override
    public int hashCode() {
        int hash = 7;
        hash = 29 * hash + Objects.hashCode(this.jmeno.toUpperCase());
        hash = 29 * hash + Objects.hashCode(this.prijmeni.toUpperCase());
        hash = 29 * hash + this.regCislo;
        hash = 29 * hash + this.rocnik;
        return hash;
    }

    @Override
    public boolean equals(Object obj) {
        if (this == obj) {
            return true;
        }
        if (obj == null) {
            return false;
        }
        if (getClass() != obj.getClass()) {
            return false;
        }
        final Zavodnik other = (Zavodnik) obj;
        if (this.regCislo != other.regCislo) {
            return false;
        }
        if (this.rocnik != other.rocnik) {
            return false;
        }
        if (!Objects.equals(this.jmeno.toUpperCase(), other.jmeno.toUpperCase())) {
            return false;
        }
        if (!Objects.equals(this.prijmeni.toUpperCase(), other.prijmeni.toUpperCase())) {
            return false;
        }
        return true;
    }

}
