package danek.exceptions;

/**
 * Třída reprezentující výjimku při vytváření třídy za běhu programu
 * @author Kevin Daněk
 *
 */
public class ClassInstantiationException extends Exception {

	private static final long serialVersionUID = 6669800767610036020L;
	
	
	public ClassInstantiationException(Class<?> trida) {
		var builder = new StringBuilder();
		
		builder.append(String.format("Nebylo možné vytvořit novou instanci třídy %s z balíku %s pomocí bezparametrového konstruktoru.%n%n", trida.getSimpleName(), trida.getPackageName()));
		
		builder.append(String.format("Možné příčiny jsou:%n"));
		builder.append(String.format("1) Třída nemá veřejný konstruktor%n"));
		builder.append(String.format("2) Třída nemá definovaný bezparametrový konstruktor%n"));
		builder.append(String.format("3) Třída je privátní%n"));
		
		System.err.println(builder.toString());
		printStackTrace();
	}
}
