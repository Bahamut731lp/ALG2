package danek.trello;

import java.util.Collection;
import java.util.HashSet;
import java.util.Set;

/**
 * Třída realizující entitu "sloupec"
 * 
 * @author Kevin Daněk
 *
 */
public class Sloupec {

	/**
	 * Název sloupce
	 */
	private String nazev;
	
	/**
	 * Množina úkolů ve sloupci
	 */
	private Set<Ukol> ukoly;

	/**
	 * Konstruktor třídy "Sloupec
	 * @param nazev Název sloupce
	 */
	public Sloupec(String nazev) {
		this.nazev = nazev;
		this.ukoly = new HashSet<Ukol>();
	}

	public Sloupec() {
		this("");
	}

	/**
	 * Metoda pro přidání úkolu do sloupce
	 * @param ukol Úkol, který se má přidat
	 */
	public void addUkol(Ukol ukol) {
		ukoly.add(ukol);
	}

	/**
	 * Getter atributu název
	 * @return Hodnotu atributu název
	 */
	public String getNazev() {
		return nazev;
	}

	/**
	 * Getter množiny úkolů
	 * @return Množina úkolů
	 */
	public Set<Ukol> getUkoly() {
		return ukoly;
	}

	/**
	 * Setter atributu název
	 * @param nazev Nový název sloupce
	 */
	public void setNazev(String nazev) {
		this.nazev = nazev;
	}

	/**
	 * Setter množiny úkolů
	 * @param ukoly Nová množina úkolů
	 */
	public void setUkoly(Collection<Ukol> ukoly) {
		this.ukoly = new HashSet<Ukol>(ukoly);
	}

	@Override
	public String toString() {
		// TODO Auto-generated method stub
		return String.format("Sloupec[nazev=%s, ukoly=%s]", nazev, ukoly);
	}

}
