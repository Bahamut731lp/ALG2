package danek.trello;

import java.util.ArrayList;

/**
 * Třída reprezentující entitu "Nástěnka"
 * 
 * @author Kevin Daněk
 *
 */
public class Nastenka {
	/**
	 * Název nástěnky
	 */
	private String nazev;
	/**
	 * Seznam sloupců nástěnky
	 */
	private ArrayList<Sloupec> sloupce;
	/**
	 * Seznam zaměstnanců, kteří v nástěnce pracují
	 */
	private ArrayList<Zamestnanec> zamestnanci;

	/**
	 * Konstruktor třídy "Nastenka"
	 * 
	 * @param nazev Název nástěnky
	 */
	public Nastenka(String nazev) {
		this.nazev = nazev;
		this.sloupce = new ArrayList<Sloupec>();
		this.zamestnanci = new ArrayList<Zamestnanec>();
	}

	/**
	 * Konstruktor třídy "Nastenka"
	 */
	public Nastenka() {
		this("");
	}

	/**
	 * Metoda pro přidání nového sloupce do nástěnky
	 * 
	 * @param sloupec Instance třídy sloupec
	 */
	public void addSloupec(Sloupec sloupec) {
		this.sloupce.add(sloupec);
	}

	/**
	 * Metoda pro přidání zaměstnance do seznamu v nástěnce
	 * 
	 * @param zamestnanec
	 */
	public void addZamestnanec(Zamestnanec zamestnanec) {
		this.zamestnanci.add(zamestnanec);
	}

	/**
	 * Metoda pro nastavení sloupců
	 * 
	 * @param sloupce ArrayList sloupců
	 */
	public Nastenka setSloupce(ArrayList<Sloupec> sloupce) {
		this.sloupce = sloupce;
		
		return this;
	}

	/**
	 * Metoda pro nastavení názvu tabulky
	 * 
	 * @param nazev
	 */
	public Nastenka setNazev(String nazev) {
		this.nazev = nazev;
		
		return this;
	}

	/**
	 * Metoda pro nastavení seznamu zaměstnanců
	 * 
	 * @param zamestnanci
	 */
	public Nastenka setZamestnanci(ArrayList<Zamestnanec> zamestnanci) {
		this.zamestnanci = zamestnanci;
		
		return this;
	}

	/**
	 * Metoda zjistí název nástěnky
	 * 
	 * @return Řetězec znaků reprezentující název nástěnky
	 */
	public String getNazev() {
		return nazev;
	}

	/**
	 * Metoda zjistí sloupce v aktuální nástěnce
	 * 
	 * @return ArrayList sloupců
	 */
	public ArrayList<Sloupec> getSloupce() {
		return sloupce;
	}

	/**
	 * Metoda zjistí, kteří zaměstnanci jsou přiřazeni k nástěnce
	 * 
	 * @return ArrayList zaměstnanců
	 */
	public ArrayList<Zamestnanec> getZamestnanci() {
		return zamestnanci;
	}

	@Override
	public String toString() {
		return "Nastenka [nazev=" + nazev + ", sloupce=" + sloupce + ", zamestnanci=" + zamestnanci + "]";
	}
}
