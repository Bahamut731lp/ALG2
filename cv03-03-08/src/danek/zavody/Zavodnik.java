package danek.zavody;

import java.util.Calendar;

public class Zavodnik {
	private String jmeno;
	private String prijmeni;
	
	private Pohlavi pohlavi;
	private int rokNarozeni;
	private String klub;
	
	public Zavodnik(String jmeno, String prijmeni) {
		this.jmeno = jmeno;
		this.prijmeni = prijmeni;
	}
	
	public Zavodnik(String jmeno, String prijmeni, Pohlavi pohlavi) {
		this.jmeno = jmeno;
		this.prijmeni = prijmeni;
		this.pohlavi = pohlavi;
	}
	
	public String getJmeno() {
		return this.jmeno;
	}
	
	public String getPrijmeni() {
		return this.prijmeni;
	}
	
	public String getPohlavi() {
		return this.pohlavi.label;
	}
	
	public String getKlub() {
		return this.klub;
	}
	
	public int getRokNarozeni() {
		return this.rokNarozeni;
	}
	
	public void setPohlavi(Pohlavi p) {
		this.pohlavi = p;
	}
	
	public void setKlub(String klub) {
		this.klub = klub;
	}
	
	public void setRokNarozeni(int rok) {
		if (rok < 0) throw new IllegalArgumentException("Rok závodníka nesmí být záporný");
		
		int year = Calendar.getInstance().get(Calendar.YEAR);
		if (Math.abs(year - rok) < 15) throw new IllegalArgumentException("Může vůbec tenhle závodník řídit??? (Věk závodníka je menší než 15)"); 
		
		this.rokNarozeni = rok;
	}
	
	@Override
	public String toString() {
		String csv = String.format("%s;%s;%s;%s;%s%n", "jmeno", "prijmeni", "pohalvi");
		csv += String.format("%s;%s;%s;%s;%s%n", this.jmeno, this.prijmeni, this.pohlavi.label);
		
		return csv;
	}
}
