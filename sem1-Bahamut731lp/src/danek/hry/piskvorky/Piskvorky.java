/*
 * Copyright (C) 2022 Jirina Kralovcova
 * This program is part of Java programing education. 
 * You can use it as you need to learn Java. 
 */

package danek.hry.piskvorky;

import java.awt.Color;

import danek.apps.drawutils.StdDraw;
import danek.apps.drawutils.StdDrawUtils;

/**
 *
 * @author ALG
 */
class Piskvorky implements PiskvorkyInterface{
	
	private int pr;
	private int ps;
	private int hracNaTahu;
	private boolean konecHry;
	private int[][] plocha;
	
    Piskvorky(int pr, int ps) {
        this.pr = pr;
        this.ps = ps;
        this.plocha = new int[pr][ps];
        this.hracNaTahu = 1;
        this.konecHry = false;
    }

	@Override
	public int polozDalsiSymbol(int r, int s) {
		if (this.konecHry) throw new PiskvorkyException("Nelze položit další symbol po ukončení hry", 104);
		validateCoordinates(r, s);
		
		//Kontrola, jestli ej pole neobsazené
		if (plocha[r][s] != 0) {
			throw new PiskvorkyException(
					"Pro položení symbolu pole nesmí být obsazené", 103,
					"Řádek", Integer.toString(r),
					"Sloupec", Integer.toString(s),
					"Symbol od hráče", Integer.toString(plocha[r][s]));
		}
		
		//Položení symbolu od aktuálního hráče
		plocha[r][s] = getHrajeHrac();
				
		int stejnyVRadku = PiskvorkyTools.pocetStejnychVRadce(plocha, r, s);
		int stejnyVSloupci = PiskvorkyTools.pocetStejnychVSloupci(plocha, r, s);
		int stejnyVDiag1 = PiskvorkyTools.pocetStejnychVDiag1(plocha, r, s);
		int stejnyVDiag2 = PiskvorkyTools.pocetStejnychVDiag2(plocha, r, s);
		
		int nejvetsi = Math.max(stejnyVRadku, Math.max(stejnyVSloupci, Math.max(stejnyVDiag1, stejnyVDiag2)));
		
		if (nejvetsi >= MIN_ROZMER) {
			this.konecHry = true;
		} else {
			hracNaTahu = hracNaTahu == 1 ? 2 : 1;
		}
		
		//Nejdelší n-tice
		return nejvetsi;
	}
	
	@Override
	public int getSymbol(int r, int s) {
		validateCoordinates(r, s);
		
		// TODO Auto-generated method stub
		return this.plocha[r][s];
	}
	
	public void validateCoordinates(int r, int s) {
		if (r > pr) {
			throw new PiskvorkyException(
				"Souřadnice řádku nesmí být větší než výška hrací plochy", 102,
				"Počet řádků", Integer.toString(pr),
				"Zadaná souřadnice", Integer.toString(r));
		}
		
		if (r < 0) {
			throw new PiskvorkyException(
				"Souřadnice řádku nesmí být menší než nula", 102,
				"Zadaná souřadnice", Integer.toString(r));
		}
		
		if (s > ps) {
			throw new PiskvorkyException(
				"Souřadnice sloupce nesmí být větší než šířka hrací plochy", 102,
				"Počet sloupců", Integer.toString(ps),
				"Zadaná souřadnice", Integer.toString(s));
		}
		
		if (s < 0) {
			throw new PiskvorkyException(
				"Souřadnice sloupce nesmí být menší než nula", 102,
				"Zadaná souřadnice", Integer.toString(s));
		}
	}
	
	public void konec() {
		if (this.konecHry) throw new PiskvorkyException("Hru nelze znovu ukončit po jejím konci", 104);
		
		this.konecHry = true;
	}
	
	@Override
	public int getPocetRadku() {
		return this.pr;
	}

	@Override
	public int getPocetSloupcu() {
		return this.ps;
	}

	@Override
	public boolean jeKonecHry() {
		return this.konecHry;
	}

	@Override
	public int getHrajeHrac() {
		return this.hracNaTahu;
	}

	@Override
	public int getCisloVyherce() {
		return this.konecHry ? this.hracNaTahu : 0;
	}
}
