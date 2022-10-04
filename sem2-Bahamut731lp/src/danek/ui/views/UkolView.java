package danek.ui.views;

import danek.lib.ScannerUtils;
import danek.lib.StringUtils;
import danek.stores.NastenkaStore;
import danek.trello.Ukol;
import danek.ui.TextMenu;

/**
 * Třída rozhraní pro manipulaci s konkrétním úkolem ve sloupci nástěnky
 * 
 * @author Kevin Daněk
 *
 */
public class UkolView extends View {

	private static Ukol ukol = null;

	public UkolView(Ukol ukol) {
		UkolView.ukol = ukol;
		// TODO Auto-generated constructor stub
		var nastenka = NastenkaStore.getInstance();
		var menu = new TextMenu(String.format(LOC.getString("boardEmployees"), nastenka.getNazev()));

		menu.add(String.format("%s...", LOC.getString("assignEmployee")), UkolView::pridatZamestnance);
		menu.add(String.format("%s...", LOC.getString("changeDeadline")), UkolView::zmenitTermin);
		menu.add(String.format("%s...", LOC.getString("changePriority")), UkolView::zmenitPrioritu);
		menu.add(String.format("%s...", LOC.getString("returnBack")), () -> new NastenkaView());

		System.out.println(StringUtils.vpad());
		System.out.println(menu.render());
		menu.read(LOC.getString("prompt"));
	}

	/**
	 * Obslužná metoda pro změnu termínu úkolu
	 */
	private static void zmenitTermin() {
		System.out.printf("%s: ", LOC.getString("taskDeadline"));
		String termin = ScannerUtils.nextDate(rd, () -> System.out.println(LOC.getString("invalidDate")));
		System.out.println();

		ukol.setTermin(termin);
		System.out.println(LOC.getString("taskDeadlineChanged"));

		save();
		enterToContinue();
		new UkolView(ukol);
	}

	/**
	 * Obslužná metoda pro změnu priority úkolu
	 */
	private static void zmenitPrioritu() {
		System.out.printf("%s: ", LOC.getString("taskPriority"));
		int priorita = ScannerUtils.nextDataOrDefault(rd::nextInt, 1);
		System.out.println();

		ukol.setPriorita(priorita);
		System.out.println(LOC.getString("taskPriorityChanged"));

		save();
		enterToContinue();
		new UkolView(ukol);
	}

	/**
	 * Obslužná metoda pro přidání zaměstnance
	 */
	private static void pridatZamestnance() {
		var zamestnanec = ZamestnanciView.vybratZamestnance();

		ukol.setPracovnik(zamestnanec);
		System.out.println(LOC.getString("employeeAssigned"));

		save();
		enterToContinue();
		new UkolView(ukol);
	}

}
