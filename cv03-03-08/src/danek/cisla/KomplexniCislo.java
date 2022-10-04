package danek.cisla;

public class KomplexniCislo {
	static private double presnost = 1e-15;
	private double real;
	private double imaginary;
	
	public KomplexniCislo(double real, double imaginary) {
		this.real = real;
		this.imaginary = imaginary;
	}
	
	public KomplexniCislo(double real) {
		this(real, 0);
	}
	
	public double getReal() {
		return this.real;
	}
	
	public double getImaginary() {
		return this.imaginary;
	}
	
	public double getAbs() {
		return Math.sqrt(Math.pow(real, 2) + Math.pow(imaginary, 2));
	}
	
	public double getAngle() {
		return Math.atan(this.imaginary / this.real);
	}
	
	public KomplexniCislo getPower(int exponent) {
		double angle = this.getAngle();
		
		double abs = Math.pow(this.getAbs(), exponent);
		double cos = Math.cos(exponent * angle);
		double sin = Math.sin(exponent * angle);
		
		return new KomplexniCislo(abs * cos, abs * sin);
	}
	
	public KomplexniCislo getConjugate() {
		return new KomplexniCislo(this.real, -1 * this.imaginary);
	}
		
	public static KomplexniCislo parseString(String input) {
		String[] casti = input.trim().split("(?=\\+)|(?=-)");
		
		double real = Double.parseDouble(casti[0]);
		double imaginary = Double.parseDouble(casti[1].substring(0, casti[1].indexOf("i")));
		
		return new KomplexniCislo(real, imaginary);
	}
	
	@Override
	public String toString() {
		return String.format("%f%+fi", this.real, this.imaginary);
	}
	
	@Override
	public int hashCode() {
		int hash = 99;
		
		hash = hash * 11 + (int) this.real;
		hash = hash * 11 + (int) this.imaginary;
		// TODO Auto-generated method stub¨
		
		return hash;
	}
	
	@Override
	public boolean equals(Object obj) {
        if (this == obj) return true; 
        if (obj == null) return false;
        if (getClass() != obj.getClass()) return false;
        
        final KomplexniCislo other = (KomplexniCislo) obj;
        
        if (Math.abs(this.real - other.real) < presnost) return false;
        return Math.abs(this.imaginary - other.imaginary) < presnost;
	}
}
