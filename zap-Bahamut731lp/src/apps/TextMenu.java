package apps;

import java.util.LinkedList;

public class TextMenu {
	
	private String heading;
	
	// Nechtělo se mi to dávat do mapy
	// Takhle navíc budu moci vybírat podle čísla možnosti
	private LinkedList<Runnable> handles;
	private LinkedList<String> labels;
	
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
	 * @param label Popis možnosti
	 * @param handle Funkce pro volání obsluhující metody
	 */
	public void add(String label, Runnable handle) {
		labels.add(label);
		handles.add(handle);
	}
	
	/**
	 * Zavolá obslužnou metodu možnosti v menu
	 * @param index Pořadové číslo možnosti (tak, jak je vykresleno v menu)
	 * @returns Bool, jestli byla obslužná metoda provedena
	 */
	public boolean call(int index) {
		if (!this.isValidOption(index - 1)) return false;
		
		handles.get(index - 1).run();
		return true;
	}
	
	public boolean isValidOption(int index) {
		return index < labels.size() && index >= 0;
	}
	
	/**
	 * Metoda sestaví String pro vykreslení menu na STD/O
	 * @return Formátovaný řetězec s vykresleným menu
	 */
	public String render() {
		var frame = new StringBuilder();
		var count = labels.size();
		
		frame.append(String.format("---< %s >---%n%n", heading));
		
		for (int i = 0; i < count; i++) {
			var label = labels.get(i);
			
			frame.append(String.format("%s) %s %n", i + 1, label));
		}
		
		return frame.toString();
	}
}

