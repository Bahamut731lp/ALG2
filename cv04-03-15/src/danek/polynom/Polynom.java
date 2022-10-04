package danek.polynom;

import java.util.Arrays;

public class Polynom {
	//Přesnost pro práci s reálnými čísly
	//Nechal jsem ji public, aby se pak dala použít i pro třídu Polynomy
	//Je ovšem final, aby mi ji nějakej Jouda ze vsi nepřepsal
	public final static double precision = 1e-15;
	
	//Stupeň polynomu
	//Může, ale nemusí se shodovat s délkou vstupního pole
	//V tomto čísle se vynechávají trailing-zeros
	private int stupen;
	
	//Pole koeficientů
	//NOTICE: Koeficienty jsou řazeny od nejmenší mocniny (od 0) až do té nejvyšší
	private double[] koeficienty = new double[stupen];
	
	private Polynom(double[] koeficienty) {
		this.stupen = koeficienty.length;
		this.koeficienty = Arrays.copyOf(koeficienty, stupen);
	}

	/**
	 * Kontrola na požadavky pole
	 * @param length Délka pole koeficientů
	 * @throws IllegalArgumentException
	 */
	private static void assertLengthRequirements(int length) throws IllegalArgumentException {
		//Proč tohle vůbec dávat do zvláštní metody
		//Je to hlavně proto, že tam nechci mít tyhle hnusné guardy
		//Tady je sice jenom jeden, pravda, ale i tak
		//Navíc by se tohle dalo krásně o-catchovat už nahoře, kdybych chtěl
		if (length < 1) throw new IllegalArgumentException("Polynom se musí skládat z dvou a více koeficientů.");
	}
	
	/**
	 * Sanitizování pole koeficientů (pro odstranění nul na konci pole (trailing-zeroes))
	 * @param coefficients Pole koeficientů
	 * @return Pole sanitizovaných koeficientů
	 */
	private static double[] sanitizeCoefficients(double[] coefficients) {
		int lastNonZeroIndex = -1;
		
		for (int i = 0; i < coefficients.length; i++) {
			if (Math.abs(coefficients[i]) > precision) lastNonZeroIndex = i;
		}
		
		//Do metody copyOf je potřeba přičíst +1, protože se žádá nová délka, nikoliv nový index
		return Arrays.copyOf(coefficients, lastNonZeroIndex + 1);
		
	}

	/**
	 * Tovární metoda pro vytváření polynomů
	 * @param coefficients Pole koeficientů polynomu (od nulté mocniny po n-tou mocninu)
	 * @throws IllegalArgumentException Chyba nastane, pokud je pole koeficientů prázdné nebo pouze o jedné položce
	 * @return Polynom s koeficienty
	 */
	public static Polynom getInstance(double...coefficients) throws IllegalArgumentException {
		assertLengthRequirements(coefficients.length);
		
		double[] coefs = sanitizeCoefficients(coefficients);		
		return new Polynom(coefs);
	}

	/**
	 * Metoda pro získání stupně polynomu
	 * @return Stupeň polynomu
	 */
	public int getDegree() {
		return this.stupen;
	}
	
	/**
	 * Metoda získá koeficient na n-tém místě.
	 * @param index Pořadové číslo koeficientu
	 * @return Koeficient (nebo nula, pokud neexistuje)
	 */
	public double getCoefficient(int index) {	
		//Mohl bych tady vyhodit ArrayIndexOutOfBounds,
		//ale technicky vzato, pokud proměnná stupně n neexistuje, má koeficient nula.
		//Proto tedy místo výjimky vrátím nulu.
		if (index > (stupen - 1) || index < 0) return 0;
		
		return Arrays.copyOf(koeficienty, stupen)[index];
	}
	
	/*
	 * Metoda pro získání pole koeficientů
	 */
	public double[] getCoefficients() {
		return Arrays.copyOf(koeficienty, stupen);
	}
	
	/**
	 * Metoda vyhodnotí polynom pro zadanou reálnou hodnotu
	 * @param value Hodnota, pro kterou polynom vyhodnocujeme
	 * @return Vyhodnocení
	 */
	public double evaluate(double value) {
		double vysledek = 0;
		
		for (int i = koeficienty.length - 1; i >= 0; i--) {
			vysledek = vysledek * value + koeficienty[i];
		}
		
		return vysledek;
	}
	
	/**
	 * Metoda provede první derivaci aktuální instance polynomu
	 * @return Derivovaný polynom
	 */
	public Polynom getDerivation() {
		double[] derivovane = new double[stupen - 1];
		
		for (int i = 0; i < derivovane.length; i++) {
			derivovane[i] = (i + 1) * koeficienty[i + 1];
		}
		
		return new Polynom(derivovane);
	}
	
	/**
	 * Metoda naformátuje řetězec reprezentující polynom
	 * @param variable Řetězec, který bude použit jako proměnná polynomu.
	 * @param decimalPlaces
	 * @return Naformátovaný textový řetězec
	 */
	public String toFmtString(String variable, int decimalPlaces) {	
		//Můžeme debatovat, zda by nebylo lepší pro parametr variable použít char,
		//Ovšem tím bychom se omezili čistě jenom na písmena
		//Proměnné klidně mohou být i slova, pokud je člověk dostatečně odvážný
		//Proto jsem tedy ve finále zvolil String.
		String[] formatted = new String[this.stupen];
		
		for (int i = 0; i < stupen; i++) {
			formatted[i] = String.format("%+." + decimalPlaces + "f%s^%s", koeficienty[i], variable, i);
		}
		
		return String.join("", formatted);
	}
	
	public String toFmtString(String variable) {
		return this.toFmtString(variable, 2);
	}
	
	public String toFmtString() {
		return this.toFmtString("x", 2);
	}
	
	@Override
	public boolean equals(Object obj) {
        if (this == obj) return true; 
        if (obj == null) return false;
        if (getClass() != obj.getClass()) return false;
        
        final Polynom other = (Polynom) obj;
        
        if (Arrays.equals(this.getCoefficients(), other.getCoefficients())) return true;
        return false;
	}
}
