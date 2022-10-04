package danek.lib;

import java.io.IOException;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.Scanner;
import java.util.function.Supplier;

/**
 * Knihovní třída pro nástroje rozšiřující třídu Scanner
 * 
 * @author Kevin Daněk
 *
 */
public final class ScannerUtils {

	private final static String dateFormat = "d.M.yyyy";

	public static boolean nextBool(Supplier<String> scannerMethod, String positiveOption) {
		String option = null;

		try {
			option = scannerMethod.get();
			return option.trim().equalsIgnoreCase(option);
		}
		catch (Exception e) {
			return false;
		}
	}

	/**
	 * Načte celé číslo z rozsahu
	 * 
	 * @param scannerInstance Instance třídy scanner
	 * @param scannerMethod   Metoda, kterou bude instance používat (např. scannerInstance::nextInt)
	 * @param min             Minimum intervalu
	 * @param max             Maximum intervalu
	 * @param failCallback    Funkce, které se zavolá při selhání/čísle mimo rozsah
	 * @implNote Hranice intervalu jsou započítany do správných hodnot
	 * @return
	 */
	public static int nextIntInRange(Scanner scannerInstance, Supplier<Integer> scannerMethod, int min, int max, Runnable failCallback) {
		int number = 0;

		do {
			number = ScannerUtils.<Integer>nextDataUntilValid(scannerInstance, scannerMethod, failCallback);

			if (number >= min && number <= max) break;
			else failCallback.run();
		} while (true);

		return number;
	}

	/**
	 * Metoda pro funkcionalitu stisknutí klávesy enter pro pokračování
	 * 
	 * @param displayPrompt Funkce, která se spustí před čekáním na stisk enteru (vhodné pro upozornění v UI)
	 */
	public static void enterToContinue(Runnable displayPrompt) {
		displayPrompt.run();
		try {
			System.in.read();
		}
		catch (IOException e) {
		}
	}

	/**
	 * Metoda bezpečně načte vstup a v případě chyby vrát výchozí hodnotu
	 * 
	 * @param <T>           Datový typ, který má scanner očekávat
	 * @param scannerMethod Metoda ze třídy Scanner, která bude číst vstup
	 * @param defaultValue  Výchozí hodnota v případě selhání
	 * @return Načtený vstup nebo výchozí hodnota
	 */
	public static <T> T nextDataOrDefault(Supplier<T> scannerMethod, T defaultValue) {
		T data = null;

		try {
			data = scannerMethod.get();
		}
		catch (Exception e) {
			return defaultValue;
		}

		return data;
	}

	/**
	 * Načítá data ze Scanneru, dokud nebudou data platná, resp. dokud nebudou správného datového typu
	 * 
	 * @param <T>             Datový typ, který má scanner načítat
	 * @param scannerInstance Instance scanneru
	 * @param scannerMethod   Reference na metodu instance scanneru, která se má použít (např. scannerInstance::nextInt)
	 * @param failCallback    Funkce, která se zavolá při selhání načtení dat ze scanneru
	 * @return Načtená data
	 */
	public static <T> T nextDataUntilValid(Scanner scannerInstance, Supplier<T> scannerMethod, Runnable failCallback) {
		var konec = false;
		T option = null;

		do {
			try {
				option = scannerMethod.get();
			}
			catch (Exception e) {
				// Spustí se callback pro selhání
				failCallback.run();
				// Vyčištění inputu
				if (scannerInstance.hasNextLine()) scannerInstance.nextLine();
				// A repeat
				continue;
			}

			konec = true;
		} while (!konec);

		return option;
	}

	/**
	 * Metoda načte datum od uživatele
	 * 
	 * @param scannerInstance Instance třídy Scanner
	 * @param failCallback    Funkce, která se zavolá v případě, že uživatel zadá špatný vstup
	 * @return Načtený datum
	 */
	public static String nextDate(Scanner scannerInstance, Runnable failCallback) {
		String input = "";
		boolean validni = false;

		do {
			try {
				input = scannerInstance.nextLine();
				LocalDate.parse(input, DateTimeFormatter.ofPattern(dateFormat));
				validni = true;
			}
			catch (Exception e) {
				failCallback.run();
			}
		} while (!validni);

		return input;
	}
}
