package danek.lib;

import java.io.*;
import java.nio.charset.MalformedInputException;
import java.nio.file.*;
import java.util.ArrayList;

import com.github.cliftonlabs.json_simple.*;

/**
 * Knihnovní třída pro pomocné nástroje při interakci se soubory
 * 
 * @author Kevin Daněk
 *
 */
public final class FileSystemUtils {

	/**
	 * Konstanta cesty k adresáři s nástěnkami
	 */
	public static final String cestaKNastenkam = "data/nastenky/";

	/**
	 * Metoda pro zápis JsonObject dat na disk
	 * 
	 * @param path
	 * @param data
	 * @throws IOException
	 */
	public static void write(String path, JsonObject data) throws IOException {
		try (BufferedWriter writer = Files.newBufferedWriter(Path.of(path))) {
			writer.append(data.toJson());
		}
	}

	/**
	 * Metoda načte JSON soubor
	 * 
	 * @param path cesta k souboru
	 * @return JsonObject s daty
	 * @throws FileNotFoundException   Pokud soubor neexistuje
	 * @throws MalformedInputException Pokud JSON soubor obsahuje syntaktické chyby
	 */
	public static JsonObject load(String path) throws FileNotFoundException, MalformedInputException {
		FileReader nastenka = new FileReader(path);
		JsonObject obj = null;

		try {
			var json = Jsoner.deserialize(nastenka);

			if (json.getClass().getSimpleName().equals(JsonObject.class.getSimpleName())) {
				obj = (JsonObject) json;
			}
			else {
				throw new FileNotFoundException();
			}

		}
		catch (JsonException e) {
			throw new MalformedInputException(0);
		}

		return obj;
	}

	/**
	 * Metoda pro vypsání listu JSON souborů v adresáři
	 * 
	 * @param path Cesta k adresáři
	 * @return Názvy souborů v ArrayList (včetně přípon .json)
	 */
	public static ArrayList<String> getDirectoryJsonFiles(String path) {
		var files = new ArrayList<String>();
		File test = new File(path);

		var filesInDir = test.listFiles((t) -> t.getName().contains(".json"));

		for (File file : filesInDir) {
			files.add(file.getName());
		}

		return files;
	}
}
