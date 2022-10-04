package danek.trello;

import java.util.Comparator;

/**
 * Třída realizující entitu "Úkol"
 * 
 * @author Kevin Daněk
 *
 */
public class Ukol implements Comparable<Ukol> {
	/**
	 * Název úkolu
	 */
	private String nazev;
	
	/**
	 * Popis úkolu
	 */
	private String popis;
	
	/**
	 * Termín úkolu
	 * @implNote Termín je ukládán jako <code>String</code> pro lepší manipulaci, ale stále je kontrolován proti instanci <code>LocalDate</code> s formátem den.měsíc.rok
	 */
	private String termin;
	/**
	 * Priorita úkolu
	 */
	private int priorita;
	/**
	 * Přiřazený pracovník k úkolu
	 */
	private Zamestnanec pracovnik;

	/**
	 * Komparátor pro porovnávání úkolů podle priority
	 */
	public static final Comparator<Ukol> COMP_PRIORITA = (Ukol o1, Ukol o2) -> o1.compareTo(o2);
	/**
	 * Komparátor pro porovnávání úkolů podle názvu
	 */
	public static final Comparator<Ukol> COMP_NAZEV = (Ukol o1, Ukol o2) -> o1.getNazev().compareTo(o2.getNazev());
	/**
	 * Komparátor pro porovnávání úkolů podle termínu
	 */
	public static final Comparator<Ukol> COMP_TERMIN = (Ukol o1, Ukol o2) -> o1.getTermin().compareTo(o2.getTermin());

	/**
	 * Konstruktor pro vytvoření nového úkolu
	 * 
	 * @param nazev  Krátký název úkolu
	 * @param popis  Delší popis konkrétního úkolu
	 * @param termin Termín, do kterého musí být úkol splněn
	 * @param priorita Priorita úkolu (čím vyšší číslo, tím vyšší priorita)
	 */
	public Ukol(String nazev, String popis, String termin, int priorita) {
		this.nazev = nazev;
		this.popis = popis;
		this.termin = termin;
		this.priorita = priorita;
	}

	@Override
	public String toString() {
		return "Ukol [nazev=" + nazev + ", popis=" + popis + ", termin=" + termin + "]";
	}

	/**
	 * Prázdný konstruktor třídy úkol
	 */
	public Ukol() {
		this("", "", "", 1);
	}

	/**
	 * Getter atributu název
	 * @return Hodnota atributu název
	 */
	public String getNazev() {
		return this.nazev;
	}

	/**
	 * Getter atributu popis
	 * @return Hodnota atributu popis
	 */
	public String getPopis() {
		return this.popis;
	}

	/**
	 * Getter atributu termin
	 * @return Hodnota atributu termin
	 */
	public String getTermin() {
		return this.termin;
	}

	/**
	 * Setter atributu název
	 * @param nazev Nový název úkolu
	 */
	public void setNazev(String nazev) {
		this.nazev = nazev;
	}

	/**
	 * Setter atributu popis
	 * @param popis Nový popis úkolu
	 */
	public void setPopis(String popis) {
		this.popis = popis;
	}

	/**
	 * Setter atributu termin
	 * @param termin Nový termín úkolu
	 */
	public void setTermin(String termin) {
		this.termin = termin;
	}

	/**
	 * Metoda pro výchozí způsob porovnávání objektů typu Ukol
	 * @param o Úkol, se kterým má být instance porovnána
	 * @return 1, 0 či -1 pro porovnání
	 */
	@Override
	public int compareTo(Ukol o) {
		return (int) Math.signum(this.priorita - o.priorita);
	}

	/**
	 * Getter atributu priorita
	 * @return Hodnota atributu priorita
	 */
	public int getPriorita() {
		return priorita;
	}

	/**
	 * Setter atributu priorita
	 * @param priorita Nová priorita úkolu
	 */
	public void setPriorita(int priorita) {
		this.priorita = priorita;
	}

	/**
	 * Getter atributu pracovnik
	 * @return Hodnota atributu pracovnik
	 */
	public Zamestnanec getPracovnik() {
		return pracovnik;
	}

	/**
	 * Setter atributu pracovnik
	 * @param pracovnik Nový přiřazený pracovník úkolu
	 */
	public void setPracovnik(Zamestnanec pracovnik) {
		this.pracovnik = pracovnik;
	}
}
