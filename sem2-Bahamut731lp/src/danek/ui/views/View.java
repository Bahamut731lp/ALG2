package danek.ui.views;

import java.io.IOException;
import java.util.Locale;
import java.util.ResourceBundle;
import java.util.Scanner;

import danek.lib.ScannerUtils;
import danek.stores.NastenkaStore;

/**
 * Třída se společnými prostředky, které používají třídy rozhraní
 * 
 * @author Kevin Daněk
 *
 */
public class View {

	/** Česká lokalizace */
	protected static final ResourceBundle LOC_CZ = ResourceBundle.getBundle("danek.localization.locale", new Locale("cs", "CZ"));
	/** Anglická lokalizace */
	protected static final ResourceBundle LOC_EN = ResourceBundle.getBundle("danek.localization.locale_en", Locale.ENGLISH);
	/** Instance třídy Scanner */
	protected static Scanner rd = new Scanner(System.in).useDelimiter(System.lineSeparator());
	/** Aktuální lokalizace */
	protected static ResourceBundle LOC = LOC_CZ;

	/**
	 * Pomocná metoda pro uložení aktuálně načtené nástěnky
	 */
	protected static void save() {
		try {
			NastenkaStore.save();
		}
		catch (IOException e) {
			System.out.println(LOC.getString("cannotSaveBoard"));
			System.out.println(LOC.getString("tryAgainLater"));
		}
	}

	/**
	 * Pomocná metoda, která vyzve uživatele ke stisku klávesy enter pro pokračování
	 */
	protected static void enterToContinue() {
		ScannerUtils.enterToContinue(() -> System.out.printf("%s...%n", LOC.getString("enterToContinue")));
	}
}
