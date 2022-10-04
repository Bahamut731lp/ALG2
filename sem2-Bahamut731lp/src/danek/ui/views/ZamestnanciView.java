package danek.ui.views;

import danek.lib.ScannerUtils;
import danek.lib.StringUtils;
import danek.stores.NastenkaStore;
import danek.trello.Zamestnanec;
import danek.ui.TextMenu;

/**
 * Třída rozhraní pro manipulaci se zaměstnanci v nástěnce
 * 
 * @author Kevin Daněk
 *
 */
public class ZamestnanciView extends View {

	public ZamestnanciView() {
		var nastenka = NastenkaStore.getInstance();
		var menu = new TextMenu(String.format(LOC.getString("boardEmployees"), nastenka.getNazev()));

		menu.add(String.format("%s...", LOC.getString("listEmployees")), ZamestnanciView::vypsatZamestnance);
		menu.add(String.format("%s...", LOC.getString("addEmployee")), ZamestnanciView::pridatZamestnance);
		menu.add(String.format("%s...", LOC.getString("delEmployee")), ZamestnanciView::odebratZamestnance);
		menu.add(String.format("%s...", LOC.getString("returnBack")), () -> new NastenkaView());

		System.out.println(StringUtils.vpad());
		System.out.println(menu.render());
		menu.read(LOC.getString("prompt"));
	}

	/**
	 * Obslužná metoda pro vypsání zaměstnanců
	 */
	private static void vypsatZamestnance() {
		var zamestnanci = NastenkaStore.getInstance().getZamestnanci();
		var builder = new StringBuilder();
		var index = 1;

		for (Zamestnanec zamestnanec : zamestnanci) {
			builder.append(String.format("%s) %s (%s)", index, zamestnanec.getJmeno(), zamestnanec.getPozice()));
			index++;
		}

		System.out.println(builder);

		enterToContinue();
		new ZamestnanciView();
	}

	/**
	 * Obslužná metoda pro přidání zaměstnance
	 */
	private static void pridatZamestnance() {

		System.out.printf("%s: ", LOC.getString("employeeName"));
		var jmeno = ScannerUtils.<String>nextDataUntilValid(rd, rd::next, () -> System.out.println(LOC.getString("invalidData")));
		System.out.println();

		System.out.printf("%s: ", LOC.getString("employeePosition"));
		var pozice = ScannerUtils.<String>nextDataUntilValid(rd, rd::next, () -> System.out.println(LOC.getString("invalidData")));
		System.out.println();

		NastenkaStore.getInstance().addZamestnanec(new Zamestnanec(jmeno, pozice));
		System.out.println(LOC.getString("employeeAdded"));

		save();
		enterToContinue();
		new ZamestnanciView();
	}

	/**
	 * Obslužná metoda pro odebrání zaměstnance z nástěnky
	 */
	private static void odebratZamestnance() {

		System.out.printf(LOC.getString("selectEmployee"));
		var zamestnanec = vybratZamestnance();

		NastenkaStore.getInstance().getZamestnanci().remove(zamestnanec);
		System.out.println(LOC.getString("employeeRemoved"));

		save();
		enterToContinue();
		new ZamestnanciView();
	}

	/**
	 * Pomocná metoda pro výběr zaměstnance ze seznamu zaměstnanců
	 * 
	 * @return Zaměstnanec vybraný uživatelem
	 */
	public static Zamestnanec vybratZamestnance() {
		if (NastenkaStore.getInstance().getZamestnanci().size() == 0) {
			System.out.println(LOC.getString("noEmployeesYet"));
			pridatZamestnance();
		}
		
		System.out.printf("%s: ", LOC.getString("selectEmployee"));
		vypsatZamestnance();

		var zamestnanci = NastenkaStore.getInstance().getZamestnanci();
		var volba = ScannerUtils.nextIntInRange(rd, rd::nextInt, 1, zamestnanci.size(), () -> System.out.println(LOC.getString("invalidData")));

		return zamestnanci.get(volba - 1);
	}

}
