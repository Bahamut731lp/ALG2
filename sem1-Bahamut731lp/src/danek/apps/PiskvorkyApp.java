/*
 * Copyright (C) 2022 Jirina Kralovcova
 * This program is part of Java programing education. 
 * You can use it as you need to learn Java. 
 */

package danek.apps;

import danek.hry.piskvorky.*;
import danek.apps.drawutils.StdDraw;
import danek.apps.drawutils.StdDrawUtils;

import java.util.Arrays;
import java.util.InputMismatchException;
import java.util.List;
import java.util.Scanner;
import java.awt.Color;
import java.lang.reflect.Field;
/**
 *
 * @author ALG
 */
public class PiskvorkyApp {
    
    private static final Scanner sc = new Scanner(System.in);
    private static PiskvorkyInterface psvk;

    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) {
        boolean konecProgramu;
        
        int min = PiskvorkyInterface.MIN_ROZMER;
        psvk = PiskvorkyInterface.getInstance(min, min);
        
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
        System.out.println("1. Hrat hru Piskvorky");
        System.out.println("2. Zmena velikosti hraci plochy");
        System.out.println("3. Zmena barevneho nastaveni");
        System.out.println("0. Konec programu");
    }

    private static int nactiVolbu() {
        int volba;
        try {
            volba = sc.nextInt();      
        } catch (InputMismatchException ex) {
            volba = -1;
        }
        sc.nextLine();
        return volba;
    }

    private static boolean obsluzVolbu(int volba) {
        switch (volba) {
            case 0: return true;
            case 1: hratHru(); break;
            case 2: zmenaRozmeru(); break;
            case 3: zmenaBarev(); break;
            default:
                System.out.println("Neznama volba");
        }
        return false;
    }

    private static void hratHru() {
        System.out.println("");
        System.out.println("Hra piskvorky");
        System.out.println("Hra je realizovana v samostatnem okne ");
        
        StdDraw.clear();
        StdDrawUtils.Grid(psvk.getPocetRadku(), psvk.getPocetSloupcu(), StdDraw.GRAY);
        
        //Vytvoření fresh instance        
        psvk = PiskvorkyInterface.getInstance(psvk.getPocetRadku(), psvk.getPocetSloupcu());
        
        boolean jeStisknuto = false;
        
        while (!psvk.jeKonecHry()) {
        		
        	if (StdDraw.isMousePressed()) jeStisknuto = true;
        	
        	//Simulování události isMouseReleased
        	if (!StdDraw.isMousePressed() && jeStisknuto) {
            	int[] coords = StdDrawUtils.getGridMouseCoords(psvk.getPocetRadku(), psvk.getPocetSloupcu());
            	
            	try {
            		psvk.polozDalsiSymbol(coords[0], coords[1]);
            		StdDrawUtils.DrawPlayerSymbol(psvk.getSymbol(coords[0], coords[1]), coords[0], coords[1], psvk.getPocetRadku(), psvk.getPocetSloupcu());
				} catch (Exception e) {
					System.out.printf("Pozice %s %s je již obsazená - zvolte jinou.%n", coords[0], coords[1]);
				}
            	finally {
            		if (psvk.jeKonecHry()) break;
            		jeStisknuto = false;
            	}

        	}
        	
        }
        
        System.out.printf("Vyhrává hráč číslo %s", psvk.getCisloVyherce());
    }
    
    private static void zmenaRozmeru() {
        System.out.println("");
        System.out.println("Nastaveni velikosti hraciho pole");
        
        System.out.printf("Minimální rozměr: %s%n", PiskvorkyInterface.MIN_ROZMER);
        System.out.printf("Maximální rozměr: %s%n", PiskvorkyInterface.MAX_ROZMER);
        
        System.out.printf("Zadejte nový počet řádků (enter = %s)%n", psvk.getPocetRadku());
        int rows = readInt(psvk.getPocetRadku());
        
        System.out.printf("Zadejte nový počet řádků (enter = %s)%n", psvk.getPocetRadku());  
        int cols = readInt(psvk.getPocetSloupcu());
        
        System.out.printf("Nové rozměry: %s řádků, %s sloupců %n", rows, cols);

        psvk = PiskvorkyInterface.getInstance(rows, cols);
    }
    
    private static int readInt(int defaultValue) {
    	String input = sc.nextLine();
    	int rozmer;
    	
    	try {
			rozmer = Integer.parseInt(input);
		} catch (Exception e) {
			rozmer = defaultValue;
		}
    	
    	return rozmer;
    }

    private static void zmenaBarev() {
    	//Vytáhnutí si dostupných barev ze třídy StdDraw
    	List<Field> moznosti = Arrays.stream(StdDraw.class.getFields()).filter(field -> field.getType() == Color.class).toList();
    	
    	System.out.println("Následující list jsou všechny možné barvy:");
    	//Sestavení možností
    	for (int i = 0; i < moznosti.size(); i++) {
			System.out.printf("%-4s: %s%n", String.format("(%s)", i), moznosti.get(i).getName());
		}
    	
    	System.out.println("Vyberte možnost pro křížky (enter = bez změny):");
    	int volbaKrizek = readInt(-1);
    	try {
    		Color barva = (Color) moznosti.get(volbaKrizek).get(null);
    		StdDrawUtils.barvy[0] = barva;
    		
    		System.out.printf("Barva křížku byla změněna na %s%n", moznosti.get(volbaKrizek).getName());
		} catch (Exception e) {
			System.out.println("Barva křížku zůstává beze změn");
		}

    	System.out.println("Vyberte možnost pro křížky (enter = bez změny):");
    	int volbaKolecko = readInt(-1);
    	try {
    		Color barva = (Color) moznosti.get(volbaKolecko).get(null);
    		StdDrawUtils.barvy[1] = barva;
    		
    		System.out.printf("Barva kolečka byla změněna na %s%n", moznosti.get(volbaKolecko).getName());
		} catch (Exception e) {
			System.out.println("Barva kolečka zůstává beze změn");
		}

    }
    
   

}
