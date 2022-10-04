package danek.exceptions;

import java.lang.reflect.Method;

/**
 * Třída reprezentující výjimku při volání metody třídy
 * 
 * @author Kevin Daněk
 *
 */
public class ClassMethodInvokationException extends Exception {

	private static final long serialVersionUID = 2698967684785630511L;

	public ClassMethodInvokationException(Method metoda) {
		var builder = new StringBuilder();

		builder.append(String.format("Nebylo možné zavolat metodu %s třídy %s %n%n", metoda.getName(), metoda.getClass().getSimpleName()));

		builder.append(String.format("Možné příčiny jsou:%n"));
		builder.append(String.format("1) Metoda je chráněna modifikátorem přístupu%n"));
		builder.append(String.format("2) Metoda vyžaduje parametry, které nebyly dodány%n"));
		builder.append(String.format("3) Metoda neexistuje%n"));

		System.err.println(builder.toString());
		printStackTrace();
	}
}
