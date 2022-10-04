package danek.cisla;

public class KomplexniCislaTest {
	public static void main(String[] args) {
		KomplexniCislo test = new KomplexniCislo(5, 3);
		KomplexniCislo cong = test.getConjugate();
		KomplexniCislo parsed = KomplexniCislo.parseString("10+18i");
		
		
		KomplexniCislo a = new KomplexniCislo(3, 4);
		KomplexniCislo b = new KomplexniCislo(1, -2);
		
		System.out.println(KomplexniCisla.soucet(a, b));
		System.out.println(KomplexniCisla.rozdil(a, b));
		System.out.println(KomplexniCisla.soucin(a, b));
		System.out.println(KomplexniCisla.podil(a, b));
	}
}
