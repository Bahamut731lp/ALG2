package danek.ui.views;

import java.io.FileNotFoundException;
import java.io.IOException;
import java.nio.charset.MalformedInputException;

import danek.exceptions.ClassInstantiationException;
import danek.exceptions.DuplicateNameExpection;
import danek.lib.ScannerUtils;
import danek.lib.StringUtils;
import danek.stores.NastenkaStore;
import danek.ui.TextMenu;

/**
 * Třída rozhraní pro načtení nástěnky do paměti
 * 
 * @author Kevin Daněk
 *
 */
public class NastenkaLoader extends View {

	public NastenkaLoader() {
		var t = LOC.getString("availableBoards");
		var menu = new TextMenu(t);
		var nastenky = NastenkaStore.getList();

		menu.add(LOC.getString("newBoard"), () -> novaNastenka());

		for (String nastenka : nastenky) {
			menu.add(nastenka, () -> {
				try {
					NastenkaStore.load(nastenka);
				}
				catch (MalformedInputException | FileNotFoundException | ClassNotFoundException | ClassInstantiationException e) {
					System.out.println(LOC.getString("cannotLoadBoard"));
				}
			});
		}

		var builder = new StringBuilder();
		builder.append(StringUtils.vpad());
		builder.append(menu.render());
		builder.append(System.lineSeparator());

		System.out.print(builder);

		menu.read(LOC.getString("prompt"));

	}

	/**
	 * Obslužná metoda pro vytvoření nové nástěnky
	 */
	private static void novaNastenka() {
		System.out.printf(String.format("%s: ", LOC.getString("newBoardName")));
		var nazev = ScannerUtils.<String>nextDataUntilValid(rd, rd::next, () -> System.out.println(LOC.getString("invalidBoardName")));

		try {
			NastenkaStore.create(nazev);
		}
		catch (DuplicateNameExpection e) {
			System.out.printf(LOC.getString("duplicateBoardName"), nazev);
			return;
		}
		catch (IOException e) {
			System.out.printf(LOC.getString("ioBoardError"), nazev);
			return;
		}

		System.out.printf(LOC.getString("newBoardSuccess"), NastenkaStore.getInstance().getNazev());

		save();
		enterToContinue();
	}
}
