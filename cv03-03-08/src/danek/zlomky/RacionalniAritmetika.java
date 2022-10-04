package danek.zlomky;

public final class RacionalniAritmetika {

	public static void main(String[] args) {
		// TODO Auto-generated method stub
        Zlomek z1 = new Zlomek(1, -4);
        Zlomek z2 = new Zlomek(-3, 15);
        System.out.println("z1 = " + z1);
        System.out.println("z2 = " + z2);
        System.out.println("z1 + z2 = " + RacionalniAritmetika.soucet(z1, z2));
        System.out.println("z1 * z2 = " + RacionalniAritmetika.soucin(z1, z2));
        System.out.println("z1 - z2 = " + RacionalniAritmetika.rozdil(z1, z2));
        System.out.println("z1 : z2 = " + RacionalniAritmetika.podil(z1, z2));
	}
	
	private RacionalniAritmetika() {}
	
	public static Zlomek soucet(Zlomek... zlomky) {
		if (!maSmysl(zlomky)) return zlomky[0];
					
		Zlomek a = zlomky[0];
		
		for (int i = 1; i < zlomky.length; i++) {
			Zlomek b = zlomky[i];
			
	        int ci = a.getCitatel() * b.getJmenovatel() + b.getCitatel() * a.getJmenovatel();
	        int jm = a.getJmenovatel() * b.getJmenovatel();
	        
	        a = new Zlomek(ci, jm);
		}
		
        return a;
	}
	
	public static Zlomek soucin(Zlomek... zlomky) {
		if (!maSmysl(zlomky)) return zlomky[0];
		
		Zlomek a = zlomky[0];
		
		for (int i = 1; i < zlomky.length; i++) {
			Zlomek b = zlomky[i];
			
	        int ci = a.getCitatel() * b.getCitatel();
	        int jm = a.getJmenovatel() * b.getJmenovatel();
	        
	        a = new Zlomek(ci, jm);
		}
		
        return a;
	}
	
	public static Zlomek rozdil(Zlomek... zlomky) {
		if (!maSmysl(zlomky)) return zlomky[0];
		
		Zlomek a = zlomky[0];
		
		for (int i = 1; i < zlomky.length; i++) {
			Zlomek b = zlomky[i];
        
	        a = soucet(a, b.getOpacny());
		}
		
        return a;
	}
	
	public static Zlomek podil(Zlomek... zlomky) {
		if (!maSmysl(zlomky)) return zlomky[0];
		
		Zlomek a = zlomky[0];
		
		for (int i = 1; i < zlomky.length; i++) {
			Zlomek b = zlomky[i];
			
	        a = soucin(a, b.getPrevraceny());
		}
		
        return a;
	}
	
	private static boolean maSmysl(Zlomek[] zlomky) {
		//Kontrola, zda má součet smysl
		if (zlomky.length < 2) return false;
		
		return true;
	}
}
