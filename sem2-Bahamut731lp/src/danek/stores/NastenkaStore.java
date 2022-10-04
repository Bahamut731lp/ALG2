package danek.stores;

import java.io.FileNotFoundException;
import java.io.IOException;
import java.nio.charset.MalformedInputException;
import java.nio.file.Paths;
import java.util.*;
import java.util.stream.Collectors;

import danek.exceptions.*;
import danek.lib.*;
import danek.trello.*;

/**
 * Třída sloužíjí jako controller mezi uživatelským rozhraním a aplikací
 * 
 * @author Kevin Daněk
 *
 */
public class NastenkaStore {

	/**
	 * Instance aktuálně načtené nástěnky
	 */
	private static Nastenka instance = null;
	/**
	 * Boolean, jestli je aktuálně nějaká nástěnka načtena či nikoliv
	 */
	private static boolean isLoaded = false;

	/**
	 * Metoda pro získání aktuální nástěnky
	 * 
	 * @return Aktuálně načtená nástěnka
	 */
	public static Nastenka getInstance() {
		if (instance == null) instance = new Nastenka();

		return instance;
	}

	/**
	 * Metoda pro získání seznamu nástěnek na disku
	 * 
	 * @return List s názvy nástěnek (i s příponami souboru)
	 */
	public static List<String> getList() {
		return FileSystemUtils.getDirectoryJsonFiles(FileSystemUtils.cestaKNastenkam);
	}

	/**
	 * Metoda načte nástěnku z disku do paměti
	 * 
	 * @param name Název nástěnky
	 * @return
	 * @throws MalformedInputException     Pokud deserializace JSON souboru selže (pravděpodobně obsah není validní JSON)
	 * @throws FileNotFoundException       Pokud soubor neexistuje, nebo obsah není typu JsonObject
	 * @throws ClassNotFoundException      Pokud soubor obsahuje odkaz na třídu v poli <code>class</code>, která neexistuje
	 * @throws ClassInstantiationException Pokud nelze vytvořit prázdnou instanci nástěnky
	 */
	public static Nastenka load(String name) throws MalformedInputException, FileNotFoundException, ClassNotFoundException, ClassInstantiationException {
		var cesta = Paths.get(FileSystemUtils.cestaKNastenkam, name).toAbsolutePath().toString();
		var jsonNastenka = FileSystemUtils.load(cesta);
		var objektNastenky = (Nastenka) JsonUtils.parseJsonObject(jsonNastenka);

		isLoaded = true;
		instance = objektNastenky;

		return instance;
	}

	/**
	 * Metoda pro informování o stavu nástěnky
	 * 
	 * @return Boolean, zda je aktuálně načtená nějaká nástěnka
	 */
	public static boolean getLoadedStatus() {
		return isLoaded;
	}

	/**
	 * Metoda pro zapsání aktuální instance nástěnky na disk do příslušného souboru
	 * 
	 * @throws IOException Pokud nastane při zápisu chyba
	 */
	public static void save() throws IOException {
		var objekt = JsonUtils.encodeObjectToJSON(instance);
		var cesta = Paths.get(FileSystemUtils.cestaKNastenkam, instance.getNazev() + ".json").toAbsolutePath().toString();

		FileSystemUtils.write(cesta, objekt);
	}

	/**
	 * Metoda pro vytvoření nové nástěnky
	 * 
	 * @param name Jméno nástěnky
	 * @throws DuplicateNameExpection 
	 * @throws IOException 
	 */
	public static void create(String name) throws DuplicateNameExpection, IOException {
		var soubory = FileSystemUtils.getDirectoryJsonFiles(FileSystemUtils.cestaKNastenkam);
		soubory = soubory.stream().map(soubor -> soubor.substring(0, soubor.lastIndexOf(".json"))).collect(Collectors.toCollection(ArrayList::new));
		
		if (soubory.contains(name)) throw new DuplicateNameExpection();
		instance = new Nastenka(name);
		
		save();
	}
}
