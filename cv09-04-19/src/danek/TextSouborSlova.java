package danek;

import java.io.BufferedWriter;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.util.HashMap;
import java.util.Scanner;

public class TextSouborSlova {

	public static void main(String[] args) {
		// TODO Auto-generated method stub

		File soubor = TextSouborTools.openFileDialog();
		if (soubor == null) System.exit(1);
		
		HashMap<String, Integer> vyskyt = getPocetSlov(soubor);
		Path cestaZapis = getNovaCesta(soubor);
		toFmtOutput(soubor, cestaZapis, vyskyt);
	}
	
	public static HashMap<String, Integer> getPocetSlov(File soubor) {
		HashMap<String, Integer> vyskyty = new HashMap<String, Integer>();
		
		try (Scanner reader = new Scanner(soubor)) {
			while (reader.hasNext()) {
				String token = reader.next();
				
				if (!token.isBlank()) {
					var pocet = vyskyty.get(token);
					vyskyty.put(token, pocet != null ? pocet + 1 : 1);
				}
			}
		} catch (FileNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		return vyskyty;
	}
	
	public static Path getNovaCesta(File soubor) {
		//Vytvoření nové cesty
		String cesta;
		
		try {
			cesta = soubor.getCanonicalPath();
		} catch (IOException e) {
			cesta = soubor.getPath();
		}
		
		int indexTecky = cesta.lastIndexOf(".");
		cesta = cesta.substring(0, indexTecky) + "_slova" + cesta.substring(indexTecky);
		
		return Path.of(cesta);
	}
	
	public static void toFmtOutput(File soubor, Path cesta, HashMap<String, Integer> vyskyty) {
		//Zapsání znaků souboru statistiky
        try (BufferedWriter writeStream = Files.newBufferedWriter(cesta)) {
        	writeStream.write(String.format("Statistiky souboru '%s'%n", soubor.getName()));
        	writeStream.write(String.format("======================= Výskyt slov =========================%n"));
        	
        	for (HashMap.Entry<String, Integer> entry : vyskyty.entrySet()) {
				String key = entry.getKey();
				Integer val = entry.getValue();
				
				System.out.println(String.format("%s\t%s%n", key, val));
				writeStream.write(String.format("%s\t%s%n", key, val));
			}
        	
        } catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	

}
