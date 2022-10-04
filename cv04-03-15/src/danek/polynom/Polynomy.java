package danek.polynom;

public final class Polynomy {
	/**
	 * Metoda sečte vstupní polynomy
	 * @param polynomials
	 * @return
	 */
	public static Polynom sum(Polynom... polynomials) {	
		Operation sumFunction = (a, b) -> a + b;
		return calculate(sumFunction, polynomials);
	}
	
	/**
	 * Metoda vynásobí vstupní polynomy
	 * @param polynomials
	 * @return
	 */
	public static Polynom product(Polynom... polynomials) {
		Operation productFunction = (a, b) -> a * b;
		return calculate(productFunction, polynomials);
	}
	
	/**
	 * Metoda odečte vstupní polynomy od sebe
	 * @param polynomials
	 * @return
	 */
	public static Polynom difference(Polynom... polynomials) {
		Operation differenceFunction = (a, b) -> a - b;
		return calculate(differenceFunction, polynomials);
	}
	
	/**
	 * Metoda podělí vstupní polynomy navzájem mezi sebou
	 * @param polynomials
	 * @return
	 */
	public static Polynom division(Polynom... polynomials) {
		Operation divisionFunction = (a, b) -> a / b;
		
		//Tady bude problematické dělení nulou...
		//Nehodlám to nějak extra ošetřovat.
		return calculate(divisionFunction, polynomials);		
	}
	
	private static Polynom calculate(Operation op, Polynom[] polynomials) {
		if (polynomials.length < 2) throw new IllegalArgumentException("Počet polynomů v operaci musí být větší než 1");
		
		Polynom a = polynomials[0];
		if (a == null) throw new IllegalStateException("Nelze provádět operace bez definovaných polynomů");
		
		for (int i = 1; i < polynomials.length; i++) {
			Polynom b = polynomials[i];
			if (b == null) throw new IllegalStateException("Nelze provádět operace bez definovaných polynomů");
			
			int length  = Math.max(a.getDegree(), b.getDegree());
			double[] coeffs = new double[length];
			
			for (int j = 0; j < length; j++) {
				coeffs[j] = op.apply(a.getCoefficient(j), b.getCoefficient(j));
			}
			
			a = Polynom.getInstance(coeffs);
		}
		
		return a;
	}
}
