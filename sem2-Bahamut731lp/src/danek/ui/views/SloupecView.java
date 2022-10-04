package danek.ui.views;

import java.util.ArrayList;
import danek.lib.ScannerUtils;
import danek.lib.StringUtils;
import danek.stores.NastenkaStore;
import danek.trello.*;
import danek.ui.TextMenu;

/**
 * Třída rozhraní pro náhled sloupce nástěnky
 * 
 * @author Kevin Daněk
 *
 */
public class SloupecView extends View {

	private static Sloupec sloupec = null;

	public SloupecView(Sloupec sloupec) {
		SloupecView.sloupec = sloupec;

		var builder = new StringBuilder();
		var nazev = sloupec.getNazev();
		var menu = new TextMenu(String.format("Sloupec \"%s\"", nazev));

		menu.add(String.format("%s...", LOC.getString("newTask")), SloupecView::pridatUkol);
		menu.add(String.format("%s...", LOC.getString("editTask")), () -> new UkolView(vybratUkol()));
		menu.add(String.format("%s...", LOC.getString("moveTask")), SloupecView::presunoutUkol);
		menu.add(String.format("%s...", LOC.getString("delTask")), SloupecView::odebratUkol);
		menu.add(String.format("%s...", LOC.getString("listTasks")), SloupecView::vypsatUkoly);
		menu.add(LOC.getString("returnBack"), () -> System.out.print(""));

		builder.append(StringUtils.vpad());
		builder.append(menu.render());

		System.out.println(builder.toString());
		menu.read(LOC.getString("prompt"));
	}

	/**
	 * Obslužná metoda pro přesunutí úkolu
	 */
	private static void presunoutUkol() {
		var ukol = vybratUkol();
		var sloupce = NastenkaStore.getInstance().getSloupce();
		var builder = new StringBuilder();
		var index = 1;

		if (ukol == null) {
			System.out.println(LOC.getString("noTaskToMove"));
			enterToContinue();
			new SloupecView(sloupec);
			return;
		}

		for (Sloupec moznost : sloupce) {
			builder.append(String.format("%s) %s", index, moznost.getNazev()));
			builder.append(System.lineSeparator());

			index++;
		}

		System.out.println(builder);
		var vyber = ScannerUtils.nextIntInRange(rd, rd::nextInt, 1, sloupce.size(), () -> System.out.println("Neplatná volba sloupce, prosím, zkuste to znovu"));

		sloupce.get(vyber - 1).addUkol(ukol);
		sloupec.getUkoly().remove(ukol);
		System.out.println(LOC.getString("taskMoved"));

		save();
		enterToContinue();
	}

	/**
	 * Obslužná metoda pro vypsání úkolů
	 */
	private static void vypsatUkoly() {
		var ukoly = new ArrayList<Ukol>(sloupec.getUkoly());
		var builder = new StringBuilder();

		System.out.printf("%s [y/n] ", LOC.getString("sortTasks"));
		var razeni = ScannerUtils.nextBool(rd::next, "y");

		if (razeni) {
			var menu = new TextMenu();

			menu.add(LOC.getString("sortByPriority"), () -> ukoly.sort(Ukol.COMP_PRIORITA));
			menu.add(LOC.getString("sortByDeadline"), () -> ukoly.sort(Ukol.COMP_NAZEV));
			menu.add(LOC.getString("sortyByName"), () -> ukoly.sort(Ukol.COMP_NAZEV));

			System.out.println(menu.render());
			menu.read(LOC.getString("prompt"));
		}

		System.out.println();

		for (Ukol ukol : ukoly) {
			builder.append(String.format("%n%4s%16s%%n-20s%n%s", ukol.getPriorita(), ukol.getTermin(), ukol.getNazev(), ukol.getPopis()));
			builder.append(System.lineSeparator());
		}

		System.out.println(builder.toString());
		enterToContinue();

		new SloupecView(sloupec);
	}

	/**
	 * Obslužná metoda pro přidání úkolu do sloupce
	 * 
	 * @param sloupec Aktuální otevřený sloupec
	 */
	private static void pridatUkol() {
		rd.nextLine();

		var nazvyUkolu = new ArrayList<>(sloupec.getUkoly().stream().map((Ukol ukol) -> ukol.getNazev()).toList());
		var nazev = "";

		do {
			System.out.printf("%s: ", LOC.getString("taskName"));
			nazev = ScannerUtils.<String>nextDataUntilValid(rd, rd::nextLine, () -> System.out.println(LOC.getString("invalidData"))).trim();
			System.out.println();

			if (!nazvyUkolu.contains(nazev)) break;

			System.out.println(LOC.getString("taskNameAlreadyExists"));
		} while (true);

		System.out.printf(String.format("%s :", LOC.getString("taskDesc")));
		String popis = ScannerUtils.<String>nextDataUntilValid(rd, rd::nextLine, () -> System.out.println(LOC.getString("invalidData")));
		System.out.println();

		System.out.printf(String.format("%s :", LOC.getString("taskDeadline")));
		String termin = ScannerUtils.nextDate(rd, () -> System.out.println(LOC.getString("invalidDate")));
		System.out.println();

		System.out.printf(String.format("%s :", LOC.getString("taskPriority")));
		int priorita = ScannerUtils.nextDataOrDefault(rd::nextInt, 1);
		System.out.println();

		sloupec.addUkol(new Ukol(nazev, popis, termin, priorita));

		save();
		enterToContinue();
		new SloupecView(sloupec);
	}

	/**
	 * Obslužná metoda pro odebrání úkolu ze sloupce
	 * 
	 * @param sloupec Aktuální otevřený sloupec
	 */
	private static void odebratUkol() {
		var ukol = vybratUkol();

		if (ukol == null) {
			System.out.println(LOC.getString("noTaskToDelete"));
			enterToContinue();
			new SloupecView(sloupec);
			return;
		}

		sloupec.getUkoly().remove(ukol);
		System.out.println(LOC.getString("taskDeleted"));

		save();
		enterToContinue();
		new SloupecView(sloupec);
	}

	/**
	 * Pomocná metoda pro výběr úkolu ze sloupce
	 * 
	 * @return Uživatelem vybraný úkol
	 */
	private static Ukol vybratUkol() {
		var ukoly = new ArrayList<Ukol>(sloupec.getUkoly());
		var builder = new StringBuilder();
		var index = 1;

		if (ukoly.size() == 0) return null;

		System.out.println(String.format("%s: ", LOC.getString("selectTask")));

		for (Ukol ukol : ukoly) {
			builder.append(String.format("%s) %s", index, ukol.getNazev()));
			builder.append(System.lineSeparator());

			index++;
		}

		System.out.println(builder.toString());
		var option = ScannerUtils.nextIntInRange(rd, rd::nextInt, 1, ukoly.size(), () -> System.out.println(LOC.getString("invalidData")));

		return ukoly.get(option - 1);
	}

}
