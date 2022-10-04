package danek.apps;

import danek.datumcas.Kalendar;
import danek.datumcas.KalendarTools;

public class KalendarApp extends App {
	private Kalendar k = new Kalendar(26, 3, 2022);
	
	public static void main(String[] args) {
		KalendarApp app = new KalendarApp();
		boolean konecProgramu = false;
		
		do {
			try {
				konecProgramu = app
				.setup()
				.render()
				.listen();
			} catch (Exception e) {
				System.out.println(e.getMessage());
				System.out.println();
			}
		} while (!konecProgramu);
	}
	
	public void parseDate() {
		System.out.println("Den: ");
		int den = rd.nextInt();
		
		System.out.println("Měsíc: ");
		int mesic = rd.nextInt();
		
		System.out.println("Rok: ");
		int rok = rd.nextInt();
		
		k = new Kalendar(den, mesic, rok);
	}

	@Override
	public boolean handleOption(int index) {
		// TODO Auto-generated method stub
		boolean konec = false;
		
		switch (index) {
			case 0 -> parseDate();
			case 1 -> System.out.println(k);
			case 2 -> calendarList();
			case 3 -> System.out.println(KalendarTools.isLeapYear(index));
			case 4 -> System.out.println(KalendarTools.getNumberOfDaysInYear(k.getRok()));
			case 5 -> konec = true;
			default -> {
				if (isValidOption(index)) {
					throw new UnsupportedOperationException("Tato možnost ještě není implementována");
				}
				
				throw new IllegalArgumentException(String.format("Možnost %s neexistuje", index));
			}
		}
		
		return konec;
	}

	@Override
	public App setup() {
		options.clear();
		// TODO Auto-generated method stub
		options.add("Nastavit datum");
		options.add("Vypiš datum");
		options.add("Vytvořit grafický kalendář");
		options.add("Je rok přestupný?");
		options.add("Počet dní v roce");
		options.add("Konec");
		
		return this;
	}
	
	public void calendarList() {
        System.out.println("");
        System.out.println("Listovani v kalendari, vyber data");
        Kalendar kalList = k;
        boolean konecListovani = false;
        do {
            System.out.format("Nalistovane datum: %s%n", kalList.toString());
            System.out.format("Nalistovany mesic: %02d/%d %n", kalList.getMesic(), kalList.getRok());
            
            System.out.println();
            System.out.println(kalList.getMonthlyCalendar());
            System.out.println();
            
            System.out.println("Prikazy pro posun v kalendari zadej prikaz jako textovy retezec ve tvaru");
            System.out.println(" +m -m +r -r ok storno");
            System.out.println(" +/-m/r 	- prikazy pro listovani po dnech, tydnech, mesicich a rocich");
            System.out.println(" ok       	- vyber aktualniho datumu a navrat do hlavniho menu");
            System.out.println(" storno   	- navrat do hlavniho menu bez zmeny aktualniho data");
            String volba = rd.next().toLowerCase();
            rd.nextLine();
            
            switch(volba) {
                case "+m" -> kalList = kalList.getNextMonth();
                case "-m" -> kalList = kalList.getPrevMonth();
                case "ok" -> { k = kalList; konecListovani = true; }
                case "storno" -> { konecListovani = true; }
            }
        } while (!konecListovani) ;
	}

}
