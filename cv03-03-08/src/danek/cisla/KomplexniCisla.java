package danek.cisla;

public final class KomplexniCisla {
	
	static public KomplexniCislo soucet(KomplexniCislo... cisla) {
		if (!maSmysl(cisla)) return cisla[0];
				
		KomplexniCislo a = cisla[0];
		
		for (int i = 1; i < cisla.length; i++) {
			KomplexniCislo b = cisla[i];
			
	        double real = a.getReal() + b.getReal();
	        double imaginary = a.getImaginary() + b.getImaginary();
	        
	        a = new KomplexniCislo(real, imaginary);
		}
		
        return a;
	}
	
	static public KomplexniCislo soucin(KomplexniCislo...cisla) {
		if (!maSmysl(cisla)) return cisla[0];
		
		KomplexniCislo a = cisla[0];
		
		for (int i = 1; i < cisla.length; i++) {
			KomplexniCislo b = cisla[i];
			
	        double real = (a.getReal() * b.getReal()) - (a.getImaginary() * b.getImaginary());
	        double imaginary = (a.getReal() * b.getImaginary()) + (a.getImaginary() * b.getReal());
	        
	        a = new KomplexniCislo(real, imaginary);
		}
		
        return a;
	}
	
	static public KomplexniCislo rozdil(KomplexniCislo... cisla) {
		if (!maSmysl(cisla)) return cisla[0];
				
		KomplexniCislo a = cisla[0];
		
		for (int i = 1; i < cisla.length; i++) {
			KomplexniCislo b = cisla[i];
			
	        double real = a.getReal() - b.getReal();
	        double imaginary = a.getImaginary() - b.getImaginary();
	        
	        a = new KomplexniCislo(real, imaginary);
		}
		
        return a;
	}
	
	static public KomplexniCislo podil(KomplexniCislo... cisla) {
		if (!maSmysl(cisla)) return cisla[0];
				
		KomplexniCislo a = cisla[0];
		
		for (int i = 1; i < cisla.length; i++) {
			KomplexniCislo b = cisla[i].getConjugate();
			KomplexniCislo citatel = KomplexniCisla.soucin(a, b);
			
			double jmenovatel = Math.pow(b.getReal(), 2) + Math.pow(b.getImaginary(), 2);
			
	        double real = citatel.getReal() / jmenovatel;
	        double imaginary = citatel.getImaginary() / jmenovatel;
	        
	        a = new KomplexniCislo(real, imaginary);
		}
		
        return a;
	}
	
	static private boolean maSmysl(KomplexniCislo[] cisla) {
		if (cisla.length < 2) return false;
		return true;
	}
}
