package danek.lib;

/**
 * Třída pro pomocné metody při manipulaci s textovými řetězci
 * 
 * @author Kevin Daněk
 *
 */
public class StringUtils {

	/**
	 * Velikost vertikálního odsazení
	 */
	static final int padding = 25;

	/**
	 * Metoda pro vytvoření vertikálního odsazení
	 * @return Vertikální odsazení o velikosti konstanty padding
	 */
	public static String vpad() {
		return System.lineSeparator().repeat(padding);
	}

	/**
	 * Metoda omezí délku textového řetězce na fixní délku
	 * 
	 * @param content Textový řetězec, který se má omezit
	 * @param length  Délka, na kterou by se měl omezit
	 * @return Zkrácený řetězec
	 */
	public static String truncate(final String content, int length) {
		String truncated = content;

		if (content.length() > length) {
			truncated = truncated.substring(0, length - 3) + "...";
		}

		return truncated;
	}

	/**
	 * Metoda pro zalomení textu na daný počet znaků
	 * 
	 * @author Aaron Bar
	 * @param content   Text na zalomení
	 * @param lastIndex Index, za kterým má být text zalomen
	 * @return Zalomený text
	 */
	public static String wrapLines(final String content, final int lastIndex) {
		String result = "";
		String retResult = "";
		// Check for empty so we don't throw null pointer exception
		if (!content.isEmpty()) {
			result = content.substring(0, lastIndex);
			if (content.charAt(lastIndex) != ' ') {
				// Try the split, but catch OutOfBounds in case string is an
				// uninterrupted string with no spaces
				try {
					result = result.substring(0, result.lastIndexOf(" "));
				}
				catch (StringIndexOutOfBoundsException e) {
					// if no spaces, force a break
					result = content.substring(0, lastIndex);
				}
				// See if we need to repeat the process again
				if (content.length() - result.length() > lastIndex) {
					retResult = wrapLines(content.substring(result.length(), content.length()), lastIndex);
				}
				else {
					return result.concat("\n").concat(content.substring(result.length(), content.length()));
				}
			}
			// Return the result concatenating a newline character on the end
			return result.concat("\n").concat(retResult);
			// May need to use this depending on your app
			// return result.concat("\r\n").concat(retResult);;
		}
		else {
			return content;
		}
	}
}
