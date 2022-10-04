package danek.trello;

/**
 * Třída realizující entitu "zaměstanec"
 * 
 * @author Kevin Daněk
 *
 */
public class Zamestnanec {

	/**
	 * Jméno zaměstnance
	 */
	private String jmeno;
	/**
	 * Pozice zaměstnance
	 */
	private String pozice;

	/**
	 * Konstruktor třídy "zaměstnanec"
	 * @param jmeno Jméno zaměstnance
	 * @param pozice Pozice zaměstnance
	 */
	public Zamestnanec(String jmeno, String pozice) {
		this.jmeno = jmeno;
		this.pozice = pozice;
	}

	public Zamestnanec() {
		this("", "");
	}

	/**
	 * Getter atributu jméno
	 * @return Hodnota atributu jméno
	 */
	public String getJmeno() {
		return jmeno;
	}

	/**
	 * Setter atributu jméno
	 * @param jmeno Nová hodnota atributu jméno
	 */
	public void setJmeno(String jmeno) {
		this.jmeno = jmeno;
	}

	/**
	 * Getter atributu pozice
	 * @return Hodnota atributu pozice
	 */
	public String getPozice() {
		return pozice;
	}

	/**
	 * Setter atributu pozice
	 * @param pozice Nová hodnota atributu pozice
	 */
	public void setPozice(String pozice) {
		this.pozice = pozice;
	}
}
