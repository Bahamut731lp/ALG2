package danek.datumcas;

import java.time.DateTimeException;
import java.util.Arrays;
import java.util.stream.Collectors;

public class Kalendar {
	private int den;
	private int mesic;
	private int rok;
	
	public Kalendar(int den, int mesic, int rok) {
		if (!KalendarTools.isValidDate(den, mesic, rok)) throw new DateTimeException(String.format("Datum %s.%s.%s není platné.", den, mesic, rok));
		
		this.den = den;
		this.mesic = mesic;
		this.rok = rok;
	}

	public int getDayName() {
		int k = this.rok % 100;
		int j = this.rok / 100;
		
		int vysledek = ((this.den + (((this.mesic + 1) * 26) / 10) + k + (k / 4) + (j / 4)) + (5 * j)) % 7;
		return (vysledek + 5) % 7;
	}
	
	@Override
	public String toString() {
		return String.format("%s.%s.%s", this.den, this.mesic, this.rok);
	}
	
	public String getMonthlyCalendar() {
		StringBuilder s = new StringBuilder();
		
		//Získání dne, na kterém měsíc začíná
		int start = new Kalendar(1, this.mesic, this.rok).getDayName();
		int pocetDniVMesici = KalendarTools.getNumberOfDaysInMonth(this.mesic);
		
		//Vygenerování hlavičky s názvy dnů
		String header = Arrays
			.asList(KalendarTools.dny)
			.stream()
			.map(d -> d.substring(0, 2))
			.collect(Collectors.joining("  "))
			.concat(String.format("%n"));
		
		s.append(header);
		
		//Vygenerování listu
		for (int i = 0; i < (pocetDniVMesici + start); i++) {
			//Odsazení na začátku, pokud měsíc nezačíná pondělím
			if (i - start < 0) {
				s.append(String.format("%-4s", " "));
				continue;
			}
			
			//Zalomení na nový řádek, pokud je nový týden
			if (i % 7 == 0) s.append(String.format("%n"));
			
			//Přidání čísla dne
			s.append(String.format("%-4s", i - start + 1));		
		}
		
		return s.toString();
	}
	
	public Kalendar getNextMonth() {
		
		int den = this.den;
		int mesic = this.mesic;
		int rok = this.rok;
		
		if (mesic == 12) {
			mesic = 0;
			rok += 1;
		}
		
		mesic++;
		
		if (den >= KalendarTools.getNumberOfDaysInMonth(mesic)) den = 1;
		
		return new Kalendar(den, mesic, rok);
	}
	
	public Kalendar getPrevMonth() {

		int den = this.den;
		int mesic = this.mesic;
		int rok = this.rok;
		
		if (mesic == 1) {
			mesic = 13;
			rok -= 1;
		}
		
		mesic--;
		
		if (den >= KalendarTools.getNumberOfDaysInMonth(mesic)) den = KalendarTools.getNumberOfDaysInMonth(mesic);
		
		return new Kalendar(den, mesic, rok);
	}

	public int getDen() {
		return den;
	}

	public int getMesic() {
		return mesic;
	}

	public int getRok() {
		return rok;
	}
}
