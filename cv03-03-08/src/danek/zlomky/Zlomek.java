package danek.zlomky;

public class Zlomek {
	
	private int citatel;
	private int jmenovatel;
	
	public Zlomek(int citatel, int jmenovatel) {	
		if (jmenovatel == 0) throw new IllegalArgumentException("Jmenovatel nesmí být 0.");
		
		int delitel = nejvetsiSpolecnyDelitel(citatel, jmenovatel);
		
		//Proměnná pro propagaci znaménka ze jmenovatele do čitatele
		int znamenkoZlomku = (int) Math.signum(jmenovatel) * (int) Math.signum(citatel);
		
		this.citatel = (znamenkoZlomku * citatel) / delitel;
		this.jmenovatel = (znamenkoZlomku * jmenovatel) / delitel;
	}
	
	public int getCitatel() {
		return this.citatel;
	}
	
	public int getJmenovatel() {
		return this.jmenovatel;
	}
	
	public double toDouble() {
		return citatel / jmenovatel;
	}
	
	private static int nejvetsiSpolecnyDelitel(int a, int b) {
       int c;
       
       while (b != 0){
           c = b;
           b = a % b;
           a = c;
       }
       
       return a;
	}
	
	public Zlomek getPrevraceny() {
		return new Zlomek(jmenovatel, citatel);
	}
	
	public Zlomek getOpacny() {
		return new Zlomek(-1 * citatel, jmenovatel);
	}
	
	public static Zlomek parseString(String input) {		
		String[] castiZlomku = input.trim().split("/");
		
		if (castiZlomku.length != 2) throw new IllegalArgumentException("Vstup musí být při analýze rozdělen na 2 části oddělené lomítkem. (Nalezeny byly " + castiZlomku.length + " částí)");
		
		int citatel = Integer.parseInt(castiZlomku[0]);
		int jmenovatel = Integer.parseInt(castiZlomku[1]);
		
		return new Zlomek(citatel, jmenovatel);
	}

	@Override
	public String toString() {
		return this.citatel + "/" + this.jmenovatel;
	}
	
	@Override
	public int hashCode() {
        int hash = 7;
        hash = 79 * hash + this.citatel;
        hash = 79 * hash + this.jmenovatel;
        return hash;
	}
	
	@Override
	public boolean equals(Object obj) {
        if (this == obj) return true; 
        if (obj == null) return false;
        if (getClass() != obj.getClass()) return false;
        
        final Zlomek other = (Zlomek) obj;
        
        if (this.citatel != other.citatel) return false;
        return this.jmenovatel == other.jmenovatel;
	}
}
