package danek.apps;

import java.util.ArrayList;
import java.util.Scanner;

public abstract class App {
	protected static Scanner rd = new Scanner(System.in);
	
	/**
	 * List platných možností
	 */
	ArrayList<String> options = new ArrayList<>();
	
	/**
	 * Metoda slouží k přidání možností do listu options
	 */
	protected abstract App setup();
	protected abstract boolean handleOption(int index);

	/**
	 * Metoda ověřuje, zda je možnost platná
	 * @param index
	 * @return
	 */
	public boolean isValidOption(int index) {
		if (index < 0) throw new YoureAnIdiotException("Můžeš mi vysvětlit, proč mi sem cpeš zápornej index???");
		if (index >= options.size()) return false;
			
		return true;
	}
	
	public App render() {
		System.out.println("Menu:");
		
		for (int i = 0; i < options.size(); i++) {
			System.out.printf("%-4s %s%n", String.format("(%s)", i), options.get(i));
		}
		
		System.out.println();
		System.out.println("Zadejte číslo možnosti: ");
		
		return this;
	}
	
	public boolean listen() {
		int option = rd.nextInt();
		
		return handleOption(option);
	}
}
