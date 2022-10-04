package apps;

import java.io.FileNotFoundException;
import java.io.IOException;
import java.nio.file.AccessDeniedException;
import java.util.InputMismatchException;
import java.util.Scanner;
import telefony.TelefonniSeznam;

/**
 *
 * @author
 */
public class TelefonniSeznamApp {
    // testovaci soubory
    // "data/tel/prac.csv"
    // "data/tel/zam.csv"

    private static final Scanner sc = new Scanner(System.in);
    private static TelefonniSeznam telSez = null;
    private static boolean konecProgramu = false;

    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) {
    	
    	var textMenu = new TextMenu("Vítá vás telefonní seznam");
    	
    	textMenu.add("Vyhledat zaměstnance", () -> vyhledatZamestance());
    	textMenu.add("Kontrolní výpis všech zaměstnanců", () -> System.out.println(telSez.listEmployees()));
    	textMenu.add("Kontrolní výpis všech pracovišť", () -> System.out.println(telSez.listWorkplaces()));
    	textMenu.add("Konec programu", () -> konecProgramu = true);

        try {
            vytvorTelefonniSeznam();
            konecProgramu = telSez == null;
        } catch (FileNotFoundException e) {
            System.out.println("Nektery ze vstupnich souboru neexistuje");
            System.out.println(e.getMessage());
            e.printStackTrace();
            System.exit(-1);
        } catch (AccessDeniedException e) {
            System.out.println("Nektery ze vstupnich souboru nelze cist");
            System.out.println(e.getMessage());
            e.printStackTrace();
            System.exit(-1);        	
        } catch (IOException e) {
            System.out.println("Chyba pri nacitani vstupnich souboru");
            System.out.println("Nelze spustit aplikaci telefonnho seznamu");
            System.out.println(e.getMessage());
            e.printStackTrace();
            System.exit(-2);
        }
        // podarilo se nacist soubory, vytvorit telefonni seznam
        // hlavni ridici smycka programu
        while (!konecProgramu) {
            System.out.println(textMenu.render());
            int volba = nactiVolbu();
            
            boolean success = textMenu.call(volba);
            
            if (!success) {
            	System.out.println("Zadána neplatná volba");
            }
        } 
        System.out.println("Konec programu ...");
    }

    private static int nactiVolbu() {
        int volba = -1;
        System.out.print("Zadej zvolenou polozku menu: ");
        try {
            // zkusim nacist cislo
            volba = sc.nextInt();
        } catch (InputMismatchException e) {
            // textovy retezec se nepodarilo prevest na cele cislo
            // cokoli jiného než číslo je neplatna volba
            volba = -1;
        } finally {
            // v kazdem pripade vyprazdnime vstup - respektive 
            // aktualni radek vstupu vcetne sekvence znak; konce radku
            sc.nextLine();
        }
        // je vracena platna celocislena hodnota
        return volba;
    }

    private static void vyhledatZamestance() {
    	System.out.println("Napiště filtrovací kritérium: ");
    	var filter = sc.nextLine();
    	
    	System.out.println(telSez.filterEmployees(filter));
    }
    
    private static void vytvorTelefonniSeznam() throws IOException {
        System.out.println("Zadej název/cestu souboru se seznamem pracovišť ");
        String jmSoubPrac;
        jmSoubPrac = sc.nextLine().trim();
        
        System.out.println("Zadej název/cestu souboru se seznamem zaměstnanců ");
        String jmSoubZam;
        jmSoubZam = sc.nextLine().trim();
        
        telSez = TelefonniSeznam.getInstance(jmSoubPrac, jmSoubZam);
    }

}
