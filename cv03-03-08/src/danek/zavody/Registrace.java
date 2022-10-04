package danek.zavody;

public class Registrace{
	private String jmeno;
	private String prijmeni;
	private int startovniCislo;
	private String casStartu;
	private String casCile;
	private String casCelkem;
	
	public Registrace(Zavodnik osoba, int startovniCislo) {
		this.jmeno = osoba.getJmeno();
		this.prijmeni = osoba.getPrijmeni();
		
		this.startovniCislo = startovniCislo;
	}
	
	public void setCasStartu(String start) {
		this.casStartu = start;
	}
	
	public void setCasCile(String konec) {
		this.casCile = konec;
	}
	
	public void setCasCelkem(String celkem) {
		this.casCelkem = celkem;
	}
	
	public String getJmeno() {
		return this.jmeno;
	}
	
	public String getPrijmeni() {
		return this.prijmeni;
	}
	
	public String getCasStartu() {
		return this.casStartu;
	}
	
	public String getCasCile() {
		return this.casCile;
	}
	
	public String getCasCelkem() {
		return this.casCelkem;
	}
	
	public int getStartovniCislo() {
		return this.startovniCislo;
	}
	
	@Override
	public String toString() {	
		String csv = String.format("%s;%s;%s;%s;%s;%s%n", this.jmeno, this.prijmeni, this.startovniCislo, this.casStartu, this.casCile, this.casCelkem);
		
		return csv;
	}
	
}
