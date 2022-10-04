package danek.ui;

import java.util.LinkedList;

import danek.lib.ScannerUtils;
import danek.ui.views.View;

/**
 * Třída realizující interaktivní menu
 * 
 * @author Kevin Daněk
 * @implNote Třída nepoužívá mapu, ale dvě samostatně spravovaná pole typu LinkedList. Je to hlavně z důvodu zachování informace o pořadí vložení prvků.
 *
 */
public class TextMenu extends View {

	/**
	 * Nadpis menu
	 */
	private String heading;

	/**
	 * Seznam funkcí, které jsou asociovány k možnostem
	 */
	private LinkedList<Runnable> handles;
	/**
	 * Seznam možností
	 */
	private LinkedList<String> labels;

	/**
	 * 
	 * @param heading Nadpis 
	 */
	public TextMenu(String heading) {
		this.heading = heading;
		this.handles = new LinkedList<>();
		this.labels = new LinkedList<>();
	}

	public TextMenu() {
		this("");
	}

	/**
	 * Metoda pro přídání možnosti do menu
	 * 
	 * @param label  Popis možnosti
	 * @param handle Funkce pro volání obsluhující metody
	 */
	public void add(String label, Runnable handle) {
		labels.add(label);
		handles.add(handle);
	}

	/**
	 * Zavolá obslužnou metodu možnosti v menu
	 * 
	 * @param index Pořadové číslo možnosti (tak, jak je vykresleno v menu)
	 * @returns Bool, jestli byla obslužná metoda provedena
	 */
	public boolean call(int index) {
		if (!this.isValidOption(index - 1)) return false;

		handles.get(index - 1).run();
		return true;
	}

	/**
	 * Metoda zjistí, zda je číslo validním číslem možnosti z menu
	 * 
	 * @param index Číslo kontrolované možnosti
	 * @return Zda-li je číslo validním číslem možnosti
	 */
	public boolean isValidOption(int index) {
		return index <= labels.size() && index >= 0;
	}

	/**
	 * Metoda sestaví String pro vykreslení menu
	 * 
	 * @return Formátovaný řetězec s vykresleným menu
	 */
	public String render() {
		var frame = new StringBuilder();
		var count = labels.size();

		if (!heading.isBlank()) frame.append(String.format("%n---< %s >---%n%n", heading));

		for (int i = 0; i < count; i++) {
			var label = labels.get(i);

			frame.append(String.format("%s) %s %n", i + 1, label));
		}

		return frame.toString();
	}
	
	/**
	 * Metoda přečte uživatelům vstup
	 * @param prompt Výzva k zadání vstupu, která se ukáže uživateli
	 * @implNote K výzvě je na konec připojena dvojtečka.
	 */
	public void read(String prompt) {
		boolean konec = false;
		int option = -1;

		System.out.printf("%s: ", prompt);

		do {
			option = ScannerUtils.<Integer>nextDataOrDefault(rd::nextInt, -1);
			if (!this.isValidOption(option)) {
				System.out.println("Neplatná volba, zkuste to, prosím, znovu");
				rd.nextLine();
				continue;
			}

			konec = true;
			this.call(option);
		} while (!konec);
	}
}
