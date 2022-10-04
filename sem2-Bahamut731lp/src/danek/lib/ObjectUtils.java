package danek.lib;

import java.lang.reflect.*;
import java.util.*;
import java.util.function.Function;

/**
 * Knihovní třída pro pomocné metody obecných objektů a tříd
 * 
 * @author Kevin Daněk
 *
 */
public final class ObjectUtils {

	/**
	 * Metoda pro získání atributů ve třídě
	 * 
	 * @param trida
	 * @return List atributů
	 */
	private static ArrayList<Field> getFields(Class<?> trida) {
		return new ArrayList<>(Arrays.asList(trida.getDeclaredFields()));
	}

	/**
	 * Metoda pro získání getterů ve třídě
	 * 
	 * @param trida Třída objektu
	 * @return List getterů
	 */
	public static ArrayList<Method> getGetters(Class<?> trida) {
		return new ArrayList<>(Arrays.asList((trida.getDeclaredMethods())).stream().filter((method) -> method.getName().startsWith("get")).toList());
	}

	/**
	 * Metoda pro získání setterů ve třídě
	 * 
	 * @param trida Třída objektu
	 * @return List getterů
	 */
	public static ArrayList<Method> getSetters(Class<?> trida) {
		return new ArrayList<>(Arrays.asList((trida.getDeclaredMethods())).stream().filter((method) -> method.getName().startsWith("set")).toList());
	}

	/**
	 * Metoda pro namapování atributů třídy k jejich getterům
	 * 
	 * @param trida Třída, u které se budou zkoumat atributy a jejich gettery
	 * @implNote Předpokládá se, že getterry jsou pojmenované ve tvaru <code>getAttribute</code> (místo
	 *           <code>Attribute</code> je název atributu)
	 * @return Mapa s názvy atributů a jejich getterů
	 */
	public static HashMap<String, Method> mapMethodsAndFields(Class<?> trida, Function<Class<?>, ArrayList<Method>> methodGatherer) {
		var<String, Method> map = new HashMap<String, Method>();
		var atributes = getFields(trida);
		var methods = methodGatherer.apply(trida);

		var<String> attributesName = new ArrayList<String>(atributes.stream().map((t) -> t.getName()).toList());

		for (Method method : methods) {
			// Standardní tvar pro getter je get<Atribut>, kde název atributu začíná velkým
			// písmenem
			String possibleAttribute = method.getName().substring(3).toLowerCase();
			if (attributesName.contains(possibleAttribute)) map.put(possibleAttribute, method);
		}

		return map;
	}
}
