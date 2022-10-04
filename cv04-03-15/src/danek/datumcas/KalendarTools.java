package danek.datumcas;

public final class KalendarTools {
	public static final int[] DnyVMesici = new int[]{31,28,31,30,31,30,31,31,30,31,30,31};
	public static final String[] dny = new String[]{"pondělí", "úterý", "středa", "čtvrtek", "pátek", "sobota", "neděle"};
	
	public static void getNumberOfDaysInYear() {}
	
	public static boolean isLeapYear(int year) {
		return year % 4 == 0;
	}
	
	public static boolean isValidDate(int day, int month, int year) {
		if (isLeapYear(year) && month == 2 && day == 29) return true;
		if (month > 12 || day > DnyVMesici[month - 1]) return false;
		
		return true;
	}
	
	public static int getNumberOfDaysInMonth(int month) {
		return month == 2 && isLeapYear(month) ? 29 : DnyVMesici[month - 1];
	}
	
	public static int getNumberOfDaysInYear(int year) {
		int days = 0;
		
		for (int i = 0; i < DnyVMesici.length; i++) {
			if (i == 1 && isLeapYear(year)) {
				 days += 29;
				 continue;
			}
			
			days += DnyVMesici[i];
		}
		
		return days;
	}
}
