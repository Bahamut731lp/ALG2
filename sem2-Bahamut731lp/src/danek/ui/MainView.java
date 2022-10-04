package danek.ui;

import java.awt.Desktop;
import java.io.File;
import danek.lib.StringUtils;
import danek.ui.views.*;

/**
 * Třída rozhraní, která realizuje hlavní menu programu
 * 
 * @author Kevin Daněk
 *
 */
public class MainView extends View {

	/**
	 * Instance třídy Desktop pro přístup k registrovaným aplikacím a jejich protokolům
	 */
	private static Desktop desk = Desktop.getDesktop();

	public static void main(String[] args) {
		var menu = new TextMenu(LOC.getString("mainMenu"));

		menu.add("Procházet nástěnky", () -> new NastenkaView());
		menu.add(LOC.getString("mainMenuBrowseFiles"), MainView::browseFiles);
		menu.add(LOC.getString("mainMenuChangeLang"), MainView::changeLanguage);
		menu.add(LOC.getString("mainMenuExit"), MainView::endProgram);

		while (true) {
			System.out.println(menu.render());
			menu.read(LOC.getString("prompt"));
		}
	}

	/**
	 * Obslužná metoda pro ukončení programu
	 */
	private static void endProgram() {
		System.out.println("Ukončuji program...");

		save();
		System.exit(0);
	}

	/**
	 * Obslužná metoda pro změnu jazyka programu
	 */
	private static void changeLanguage() {
		System.out.println(StringUtils.vpad());

		var langMenu = new TextMenu(LOC.getString("langMenu"));
		langMenu.add(LOC.getString("langCZ"), () -> browseFiles());
		langMenu.add(LOC.getString("langEN"), () -> LOC = LOC_EN);
		langMenu.add(LOC.getString("back"), () -> System.out.println(""));

		System.out.println(langMenu.render());
		langMenu.read(LOC.getString("prompt"));
	}

	/**
	 * Obslužná metoda pro procházení souborů
	 */
	public static void browseFiles() {
		try {
			desk.browse(new File("data/").getAbsoluteFile().toURI());
		}
		catch (Exception e) {
			System.out.println(LOC.getString("mainMenuCannotOpenData"));
		}
	}
}
