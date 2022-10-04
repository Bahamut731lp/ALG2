package danek.ui.views;

import danek.lib.ScannerUtils;
import danek.lib.StringUtils;
import danek.stores.NastenkaStore;
import danek.trello.*;
import danek.ui.TextMenu;

/**
 * Třída rozhraní pro pohled na aktuální nástěnku
 * 
 * @author Kevin Daněk
 *
 */
public class NastenkaView extends View {

	private static Nastenka nastenka = NastenkaStore.getInstance();

	/**
	 * Pohled pro manipulaci s načtenou nástěnkou
	 */
	public NastenkaView() {
		while (!NastenkaStore.getLoadedStatus()) {
			new NastenkaLoader();
			nastenka = NastenkaStore.getInstance();
		}

		System.out.println(StringUtils.vpad());
		var menu = new TextMenu(String.format(LOC.getString("boardTitle"), nastenka.getNazev()));

		menu.add(String.format("%s...", LOC.getString("browseCols")), NastenkaView::sloupecMenu);
		menu.add(String.format("%s...", LOC.getString("browseEmployees")), () -> new ZamestnanciView());
		menu.add(LOC.getString("saveToDisk"), NastenkaView::ulozitNastenku);
		menu.add(String.format("%s...", LOC.getString("openAnotherBoard")), () -> new NastenkaLoader());
		menu.add(LOC.getString("returnBack"), () -> System.out.print(""));

		System.out.println(menu.render());
		menu.read(LOC.getString("prompt"));
	}

	/**
	 * Obslužná metoda pro možnosti se sloupci v nástěnce
	 */
	public static void sloupecMenu() {
		System.out.println(StringUtils.vpad());

		var sloupce = NastenkaStore.getInstance().getSloupce();
		var menu = new TextMenu(String.format(LOC.getString("colsTitle"), NastenkaStore.getInstance().getNazev()));

		menu.add(String.format("%s...", LOC.getString("newCol")), () -> novySloupec());
		for (Sloupec sloupec : sloupce) menu.add(String.format(LOC.getString("openCol") + "...", sloupec.getNazev()), () -> new SloupecView(sloupec));
		menu.add(LOC.getString("returnBack"), () -> System.out.println(""));

		System.out.println(menu.render());
		menu.read(LOC.getString("prompt"));

		new NastenkaView();
	}

	/**
	 * Obslužná metoda pro vytvoření nového sloupce
	 */
	public static void novySloupec() {
		System.out.printf("%s: ", LOC.getString("newColName"));
		var nazevSloupce = ScannerUtils.<String>nextDataUntilValid(rd, rd::next, () -> System.out.println(LOC.getString("invalidData")));
		System.out.println();

		nastenka.addSloupec(new Sloupec(nazevSloupce));
		System.out.printf(LOC.getString("addedCol"), nastenka.getNazev(), nazevSloupce);

		save();
		enterToContinue();
		new NastenkaView();
	}

	/**
	 * Obslužná metoda pro uložení aktuálně načtené nástěnky
	 */
	public static void ulozitNastenku() {
		System.out.println(StringUtils.vpad());

		save();
		enterToContinue();
		new NastenkaView();
	}
}
