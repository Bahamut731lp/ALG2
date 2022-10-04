package danek.zavody;

import java.util.ArrayList;
import java.util.List;

/*
 * Třída, která se stará o správu konkrétního závodu (události)
 */
public class Zavod {
	private String nazev;
	private List<Registrace> registrace = new ArrayList<Registrace>();
	
	public Zavod(String nazev) {
		this.nazev = nazev;
	}
	
	public String getNazev() {
		return this.nazev;
	}
	
	private int pridelitCislo() {
		return registrace.size() + 1;
	}
	
	public int getPocetRegistraci() {
		return registrace.size();
	}
	
	public void registrovat(Zavodnik... zavodnici) {
		for (Zavodnik ucastnik : zavodnici) {
			Registrace reg = new Registrace(ucastnik, this.pridelitCislo());
			registrace.add(reg);
		}
	}
	
	@Override
	public String toString() {
		String csv = String.format("%s;%s;%s;%s;%s;%s%n", "jmeno", "prijmeni", "startovniCislo", "casStart", "casCil", "casCelkem");
				
		for (Registrace r : registrace ) {
			csv += r.toString();
		}

		return csv;
	}
}
