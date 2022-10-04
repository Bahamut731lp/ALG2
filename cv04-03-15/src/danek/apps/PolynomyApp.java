package danek.apps;

import java.util.InputMismatchException;

import danek.polynom.*;

public class PolynomyApp extends App {
	private Polynom poly1 = null;
	private Polynom poly2 = null;
	
	public static void main(String[] args) {
		PolynomyApp app = new PolynomyApp();
		boolean konecProgramu = false;
		
		do {
			try {
				konecProgramu = app
				.setup()
				.render()
				.listen();
			} catch (Exception e) {
				System.out.println(e.getMessage());
				System.out.println();
			}
		} while (!konecProgramu);
	}
	
	@Override
	protected App setup() {
		options.clear();
		// TODO Auto-generated method stub
		options.add("Načíst první polynom");
		options.add("Načíst druhý polynom");
		
		options.add("Výpis polynomů");
		options.add("Vyhodnocení polynomů pro hodnotu");
		options.add("Prohodit polynomy");
		options.add("Derivace polynomů");
		
		options.add("Sečíst polynomy");
		options.add("Odečíst polynomy");
		options.add("Násobit polynomy");
		options.add("Dělit polynomy");
		
		options.add("Konec");
		
		return this;
	}
	
	private static Polynom parsePolynom() {
        System.out.println("");
        
        try {
            System.out.print("Zadej stupen polynomu: ");
            
            int st = rd.nextInt();
            double[] a = new double[st + 1];
            
            System.out.println("Zadej koeficienty od nejnizsiho stupne, tj v poradi a0, a1, a2 atd.");
            
            for (int i = 0; i <= st; i++) {
                a[i] = rd.nextDouble();
            }
            
            return Polynom.getInstance(a);
            
        } 
        catch (InputMismatchException e) {
            System.out.println("Chyba pri zadavani stupne nebo koeficientu polynomu");
            System.out.println("Akce nebyla provedena");
            e.printStackTrace();
            return null;
        }
        finally {
            rd.nextLine();
        }
        
	}
	
	private void evaluatePolynom() {
        System.out.println("");
        System.out.println("Vypocet hodnoty polynomu pro zadane x");
        
        if (poly1 == null) {
            System.out.println("Polynom neexistuje");
            System.out.println("Akce nebyla provedena");
            return;
        }
        System.out.println("Polynom: " + poly1.toFmtString());
        System.out.print("Zadej x: ");
        
        try {
            double x = rd.nextDouble();
            rd.nextLine();
            double hod = poly1.evaluate(x);
            
            System.out.format("Hodnota polynomu pro x = %.3f je %.3f %n", x, hod);
        }
        catch (InputMismatchException ex) {
            rd.nextLine();
            System.out.println("Chybne zadane realne cislo");
            System.out.println("Nebylo mozne provest vypocet hodnoty polynomu ");
            ex.printStackTrace();
        }
	}
	
	private void derivative() {
        System.out.println("");
        System.out.println("Vypocet derivace polynomu ");
        
        if (poly1 == null) {
            System.out.println("Polynom neexistuje");
            System.out.println("Akce nebyla provedena");
            return;
        }
        
        System.out.println("Polynom:  " + poly1.toFmtString());
        Polynom polDer = poly1.getDerivation();
        System.out.println("Derivace: " + polDer.toFmtString());
	}
	
	private void swapPolynomials() {
        System.out.println("");
        System.out.println("Vymena obou polynomu");
        Polynom pom = poly1;
        poly1 = poly2;
        poly2 = pom;
	}
	
	@Override
	protected boolean handleOption(int index) {
		boolean konec = false;
		
		switch (index) {
			case 0 -> poly1 = parsePolynom();
			case 1 -> poly2 = parsePolynom();
			case 2 -> System.out.printf("%s%n%s%n", poly1.toFmtString(), poly2.toFmtString());
			case 3 -> evaluatePolynom();
			case 4 -> swapPolynomials();
			case 5 -> derivative();
			case 6 -> System.out.println(Polynomy.sum(poly1, poly2).toFmtString());
			case 7 -> System.out.println(Polynomy.difference(poly1, poly2).toFmtString());
			case 8 -> System.out.println(Polynomy.product(poly1, poly2).toFmtString());
			case 9 -> System.out.println(Polynomy.division(poly1, poly2).toFmtString());
			case 10 -> konec = true;
		
			default -> throw new IllegalArgumentException("Unexpected value: " + index);
		}
		
		return konec;
	}

}
