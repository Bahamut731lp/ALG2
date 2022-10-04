package danek.hra;

import java.util.InputMismatchException;
import java.util.Scanner;

import org.w3c.dom.ranges.RangeException;

public class NewTextUI {


    private static final Scanner sc = new Scanner(System.in);
    private static int dolniMez = 1;
    private static int horniMez = 100;
    private static final int MIN_ROZSAH = 10;

    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) {
        boolean konecProgramu;
        do {
            vypisMenu();
            int volba = nactiVolbu();
            konecProgramu = obsluzVolbu(volba);
        } while (!konecProgramu);
        System.out.println("Koncim ...");
    }

    private static void vypisMenu() {
        System.out.println("");
        System.out.println("Hlavni menu programu");
        System.out.println("1. Hrat hru, budu hadat");
        System.out.println("2. Hrat hru, budu si myslet cislo");
        System.out.println("3. Zmena nastaveni");
        System.out.println("0. Konec programu");
    }

    private static int nactiVolbu() {
        int volba;
        try {
            System.out.print("Zadej volbu: ");
            volba = sc.nextInt();
        } catch (InputMismatchException ex) {
            volba = -1;
        } finally {
            sc.nextLine();
        }
        return volba;
    }

    private static boolean obsluzVolbu(int volba) {
        switch (volba) {
            case 0 -> {
                return true;
            }
            case 1 ->
                hratHracHada();
            case 2 ->
                hratHracMysli();
            case 3 ->
                zmenaNastaveni();
            default ->
                System.out.println("Neznama volba");
        }
        return false;
    }

    private static void hratHracHada() {
        System.out.println("");
        System.out.println("Hrani hry, hadej cislo");
        
        MyslimSiCislo hra = new MyslimSiCislo(dolniMez, horniMez);
        boolean uhadnuto = false;
        
        while (!uhadnuto) {
            int pokus = sc.nextInt();
            uhadnuto = hra.Hadam(pokus);
        }
        
    }

    private static void hratHracMysli() {
        System.out.println("");
        System.out.println("Hrani hry, mysli si cislo, budu hadat");
        
        HadamCislo hra = new HadamCislo(dolniMez, horniMez);
        boolean uhadnuto = false;
        
        System.out.printf("- Pokud je číslo větší, napiště %s\n -Pokud je číslo menší, napiště %s\n -Pokud vaše myšlené číslo uhádnu, napište %s\n","V", "M", "U");
        
        while (!uhadnuto) {
        	System.out.printf("Je vaše myšlené číslo %s?\n", hra.novyTip());
        	String odpoved = sc.next();
            
        	switch (odpoved.toLowerCase()) {
				case "v": {
					hra.jeVetsi();
					break;
				}
				case "m": {
					hra.jeMensi(); 
					break;
				}
				case "u": {
					hra.jeStejne();
					break;
				}
				default:
					throw new IllegalArgumentException("Neplatný vstup: " + odpoved);
			}
        	
        	uhadnuto = hra.jeKonec();
        	
        }
        
        
    }

    private static void zmenaNastaveni() {
        System.out.println(""); 
        System.out.println("Zmena nastaveni dolni a horni meze hadanych cisel");
        // vypsat aktualni hodnoty
        // umoznit zadani novych
        // pokud jsou nove hodnoty platne potom zmenit nastaveni
        
        System.out.printf("%-16s: %s%n", "Dolní mez", dolniMez);
        System.out.printf("%-16s: %s%n", "Dolní mez", horniMez);
        System.out.printf("%-16s: %s%n", "Minimální rozsah", MIN_ROZSAH);
        
        System.out.println();
        
        System.out.println("Zadávejte nové hodnoty parametrů");
        System.out.printf("Zadejte novou dolní mez: ", dolniMez);
        int NovaDolniMez = sc.nextInt();
        
        System.out.printf("Zadejte novou horní mez: ", horniMez);
        int NovaHorniMez = sc.nextInt();
        
        if (NovaDolniMez > NovaHorniMez) throw new IllegalStateException("Nová dolní mez nesmí být větší než nová horní mez. (" + NovaDolniMez + " > " + NovaHorniMez + ")");
        if (Math.abs(NovaHorniMez - NovaDolniMez) <= MIN_ROZSAH) throw new IllegalArgumentException("Nové meze nemají dostatečný rozestup (Min. rozestup je " + MIN_ROZSAH + ")");
    }

}