package danek;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.File;
import java.io.IOException;
import java.nio.file.*;
import java.util.HashMap;

public class TextSouborStatistika {
	public static void main(String[] args){	 
		File soubor = TextSouborTools.openFileDialog();
		if (soubor == null) System.exit(1);

		HashMap<String, Integer> znaky = getPocetZnaku(soubor);
		int pocetRadku = getPocetRadku(soubor);
		int pocetNeprazdnychRadku = getPocetRadku(soubor, true);
		
		toFmtFile(soubor, znaky, pocetRadku, pocetNeprazdnychRadku);
	}
	

	/**
	 * Metoda zanalyzuej počet výskytů znaků v souboru
	 * @param soubor Soubor, ve kterém se mají znaky počítat
	 * @return Mapa se znaky a počtem jejich výskytu
	 */
	public static HashMap<String, Integer> getPocetZnaku(File soubor) {
		HashMap<String, Integer> statistikaZnaku = new HashMap<String, Integer>();
		
		//Přečtění znaků
        try (BufferedReader readStream = Files.newBufferedReader(Path.of(soubor.getCanonicalPath()))) {
        	int character;

        	while((character = readStream.read()) != -1){
        		String znak = String.valueOf((char) character);
        		
        		if (!znak.isBlank()) {
        			var pocet = statistikaZnaku.get(znak);
        			statistikaZnaku.put(znak, pocet != null ? pocet + 1 : 1);
        		}
    		}
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
        
        return statistikaZnaku;
	}
	
	/**
	 * Metoda na počítání řádků v souboru
	 * @param soubor Soubor, který se má zanalyzovat
	 * @param pouzePrazdne Přepínač, zda-li má metoda počítat pouze neprázdné řádky či nikoliv (true = pouze prázdné, default = false)
	 * @return Počet řádků
	 */
	public static int getPocetRadku(File soubor, boolean pouzePrazdne) {
		int pocet = 0;
		
		try (BufferedReader readStream = Files.newBufferedReader(Path.of(soubor.getCanonicalPath()))) {
			pocet = (int) readStream.lines().filter(line -> !line.isBlank()).count();
		}
		catch (Exception e) {}
		
		return pocet;
	}
	
	public static int getPocetRadku(File soubor) {
		return getPocetRadku(soubor, false);
	}
	
	/**
	 * Metoda naformátuje data do souboru a standardního výstupu.
	 * @param soubor Soubor, který se analyzoval
	 * @param znaky Mapa znaků z metody <code>getPocetZnaku</code>
	 * @param pocetRadku Počet řádků z metody <code>getPocetRadku</code>
	 * @param pocetNeprazdnychRadku Počet řádků z metody <code>getPocetRadku</code> s přepínačem <code>true</code>
	 */
	public static void toFmtFile(File soubor, HashMap<String, Integer> znaky, int pocetRadku, int pocetNeprazdnychRadku) {
        //Vytvoření nové cesty pro soubor se statistikou
        String nameWithoutExt;
		try {
			nameWithoutExt = soubor.getCanonicalPath();
		} catch (IOException e1) {
			// TODO Auto-generated catch block
			nameWithoutExt = soubor.getPath();
		}
		
        nameWithoutExt = nameWithoutExt.substring(0, nameWithoutExt.lastIndexOf("."));
        
        //Zapsání znaků souboru statistiky
        try (BufferedWriter writeStream = Files.newBufferedWriter(Path.of(nameWithoutExt + ".stat"))) {
        	
        	writeStream.write(String.format("Statistiky souboru '%s'%n", soubor.getName()));
        	writeStream.write(String.format("Počet řádků:\t%s%n", pocetRadku));
        	writeStream.write(String.format("Počet neprázdných řádků:\t%s%n", pocetNeprazdnychRadku));
        	
        	writeStream.write(String.format("======================= Výskyt znaků =========================%n"));
        	
        	for (HashMap.Entry<String, Integer> entry : znaky.entrySet()) {
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
