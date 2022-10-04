package telefony;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.nio.file.AccessDeniedException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.util.ArrayList;
import java.util.Collections;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 *
 * @author Kevin Daněk
 */
public class TelefonniSeznam {

    private List<Pracoviste> pracoviste;
    private List<Zamestnanec> zamestnanci;    
    private Map<Integer, String> nazvyPracovist;

    private TelefonniSeznam(List<Pracoviste> pracoviste, List<Zamestnanec> zamestnanci) {
        this.pracoviste = pracoviste;
        this.zamestnanci = zamestnanci;
        this.nazvyPracovist = new HashMap<>();
        
        // Vytvoření slovníku čísel pracovišť a jejich názvů
        for (Pracoviste prac : pracoviste) {
			nazvyPracovist.put(prac.getCisloPracoviste(), prac.getNazev());
		}
        
    }

    public static TelefonniSeznam getInstance(String jmSoubPrac, String jmSoubZam) throws IOException {
    	File pracoviste = new File(jmSoubPrac);
    	File zamestanci = new File(jmSoubZam);
    	
    	// Později může být přesunuto do nějakých utils
    	if (!pracoviste.isFile()) throw new FileNotFoundException(String.format("Cesta k souboru %s není cestou k souboru", pracoviste.getName()));
    	if (!zamestanci.isFile()) throw new FileNotFoundException(String.format("Cesta k souboru %s není cestou k souboru", zamestanci.getName()));
    	if (!pracoviste.canRead()) throw new AccessDeniedException(String.format("Ze souboru %s nelze číst.%n", pracoviste.getName()));
		if (!zamestanci.canRead()) throw new AccessDeniedException(String.format("Ze souboru %s nelze číst.%n", zamestanci.getName()));
		
		ArrayList<Pracoviste> pracovisteList;
		ArrayList<Zamestnanec> zamestnanciList;
		
		// Opět by šlo krásně zobecnit
		
		try(BufferedReader pracovisteReader = Files.newBufferedReader(Path.of(pracoviste.getAbsolutePath()))) {
			pracovisteList = new ArrayList<>(pracovisteReader.lines().map(prac -> prac.split(";")).skip(1).map(prac -> new Pracoviste(Integer.parseInt(prac[0]), prac[1], prac[2])).toList());	
		} 

		try(BufferedReader zamestnanciReader = Files.newBufferedReader(Path.of(zamestanci.getAbsolutePath()))) {
			zamestnanciList = new ArrayList<>(zamestnanciReader.lines().map(zam -> zam.split(";")).skip(1).map(zam -> new Zamestnanec(Integer.parseInt(zam[0]), zam[1], zam[2], Integer.parseInt(zam[3]), Integer.parseInt(zam[4]))).toList());
		}
		
		// Tohle by šlo pěkně zobecnit přes java reflection
		return new TelefonniSeznam(pracovisteList, zamestnanciList);
    }
    
    /**
     * Metoda vytvoří naformátovanou tabulku všech pracovišť
     * @return Naformátovaný řetězec
     */
    public String listWorkplaces() {
    	var builder = new StringBuilder();
    	
    	builder.append(String.format("%4s %8s %-16s%n", "ČP", "Zkratka", "Název"));
    	
    	for (Pracoviste workspace : pracoviste) {
    		builder.append(String.format("%4s %8s %-16s%n", workspace.getCisloPracoviste(), workspace.getZkratka(), workspace.getNazev()));
		}
    	
    	return builder.toString();
    }
    
    /**
     * Metoda vytvoří naformátovanou tabulku seznamu zaměstnanců
     * @param Seznam zaměstanců
     * @return Naformátovaný řetězec
     */
    public String listEmployees(List<Zamestnanec> seznam) {
    	var builder = new StringBuilder();
    	
    	builder.append(String.format("%8s %12s %9s %-32s%n", "Jméno", "Příjmení", "Telefon", "Pracoviště"));
    	
    	for (Zamestnanec zamestnanec : seznam) {
    		builder.append(String.format("%8s %12s %9s %-32s%n", zamestnanec.getJmeno(), zamestnanec.getPrijmeni(), zamestnanec.getTelCislo(), nazvyPracovist.get(zamestnanec.getCisloPrac())));
		}
    	
    	return builder.toString();
    }
    
    /**
     * Metoda vytvoří naformátovanou tabulku všech zaměstnanců
     * @return Naformátovaný řetězec
     */
    public String listEmployees() {
    	return this.listEmployees(zamestnanci);
    }
    
    public String filterEmployees(String filter) {
    	var criteria = filter.toLowerCase();
    	var novyList = new ArrayList<Zamestnanec>(zamestnanci.stream().filter(z -> (z.getJmeno().toLowerCase().contains(criteria) || z.getPrijmeni().toLowerCase().contains(criteria))).toList());
    	
    	// Seřazení podle příjmení
    	Collections.sort(novyList);
    	
    	return novyList.size() > 0 ? listEmployees(novyList) : String.format("Kritérium \"%s\" nesplňují žádná jména (ani příjmení) zaměstnanců.%n", filter);
    }

}    
    
    
