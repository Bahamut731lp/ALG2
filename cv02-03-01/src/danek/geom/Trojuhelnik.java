package danek.geom;

public class Trojuhelnik {
	private double prvniOdvesna;
	private double druhaOdvesna;
	private double prepona;
	
	public static void main(String[] args) {
		Trojuhelnik test = Trojuhelnik.getInstance(3.8, 3.8, 6.7);
		
		System.out.printf("%-16s: %s%n", "Odvěsna 1", test.getPrvniOdvesna());
		System.out.printf("%-16s: %s%n", "Odvěsna 2", test.getDruhaOdvesna());
		System.out.printf("%-16s: %s%n", "Přepona", test.getPrepona());
		System.out.printf("%-16s: %s%n", "Obvod", test.getObvod());
		System.out.printf("%-16s: %s%n", "Obsah", test.getObsah());
		System.out.printf("%-16s: %s%n", "Pravúhlost", test.jePravouhly());
		System.out.printf("%-16s: %s%n", "Ostroúhlost", test.jeOstrouhly());
		System.out.printf("%-16s: %s%n", "Tupoúhlost", test.jeTupouhly());
	}
	
	private Trojuhelnik(double prvniOdvesna, double druhaOdvesna, double prepona) {
		this.prvniOdvesna = prvniOdvesna;
		this.druhaOdvesna = druhaOdvesna;
		this.prepona = prepona;
	}
	
	public static Trojuhelnik getInstance(double prvniOdvesna, double druhaOdvesna, double prepona) {
		
		//Ověření délek stran
		if (prvniOdvesna <= 0) throw new IllegalArgumentException("Délka první odvěsny musí být nezáporná");
		if (druhaOdvesna <= 0) throw new IllegalArgumentException("Délka druhé odvěsny musí být nezáporná");
		if (prepona <= 0) throw new IllegalArgumentException("Délka přepony musí být nezáporná");
		
		
		//Ověření trojúhelníkové nerovnosti
		if (prvniOdvesna + druhaOdvesna <= prepona || druhaOdvesna + prepona <= prvniOdvesna || prvniOdvesna + prepona <= druhaOdvesna) {
			throw new IllegalArgumentException("Zadané délky stran nesplňují trojúhelníkovou nerovnost");
		};
		
		
		return new Trojuhelnik(prvniOdvesna, druhaOdvesna, prepona);
		
	}

	public double getPrvniOdvesna() {
		return this.prvniOdvesna;
	}

	public double getDruhaOdvesna() {
		return this.druhaOdvesna;
	}

	public double getPrepona() {
		return this.prepona;
	}
	
	public double getObsah() {
		double polomer = this.getObvod() / 2;
		
		double soucin = polomer - this.prvniOdvesna;
		soucin *= polomer - this.druhaOdvesna;
		soucin *= polomer - this.prepona;
		soucin *= polomer;
		
		return Math.sqrt(soucin);
	}
	
	public double getObvod() {
		return this.prepona + this.druhaOdvesna + this.prvniOdvesna;
	}
	
	public boolean jeRovnostranny() {
		return prepona == prvniOdvesna && prvniOdvesna == druhaOdvesna;
	}
	
	public boolean jeRovnoramenny() {
		return prepona == prvniOdvesna || prvniOdvesna == druhaOdvesna || prepona == druhaOdvesna;
		
	}
	
	public boolean jePravouhly() {
		return Math.pow(prepona, 2) == (Math.pow(prvniOdvesna, 2) + Math.pow(druhaOdvesna, 2));
	}
	
	public boolean jeTupouhly() {
		return Math.pow(prepona, 2) > (Math.pow(prvniOdvesna, 2) + Math.pow(druhaOdvesna, 2));
	}
	
	public boolean jeOstrouhly() {
		return Math.pow(prepona, 2) < (Math.pow(prvniOdvesna, 2) + Math.pow(druhaOdvesna, 2));
	}
	
	
}
