package danek.lib;

import java.lang.reflect.*;
import java.util.*;
import java.util.Map.Entry;
import com.github.cliftonlabs.json_simple.*;
import danek.exceptions.*;

/**
 * Třída pomocných metod sloužících pro práci s JSON formátem
 * 
 * @author Kevin Daněk
 *
 */
public class JsonUtils {

	/**
	 * Metoda rekurzivně parsuje JSON Objekt do příslušných instancí objektu
	 * 
	 * @param json JsonObject, který se má naparsovat
	 * @return
	 * @throws ClassNotFoundException
	 * @throws ClassInstantiationException
	 */
	public static Object parseJsonObject(JsonObject json) throws ClassNotFoundException, ClassInstantiationException {
		Class<?> trida = Class.forName((String) json.getOrDefault("class", ""));
		Object instance = null;
		var setters = ObjectUtils.mapMethodsAndFields(trida, ObjectUtils::getSetters);

		// Pokus o vytvoření nové instance objektu
		// Tady to může vyhodit tolik výjimek,
		// že každou ošetřovat zvlášť nemá cenu
		try {
			instance = trida.getConstructor().newInstance();
		}
		catch (Exception e) {
			throw new ClassInstantiationException(trida);
		}

		// Každá atribut se pokusíme dostat do nové instance pomocí veřejného setteru
		for (Entry<String, Object> entry : json.entrySet()) {
			String key = entry.getKey();
			Object val = entry.getValue();

			// Kontrola, jestli mapa se settery obsahuje setter i pro daný atribut,
			// který chceme nové instanci přiřadit
			if (!setters.containsKey(key)) continue;
			Method setter = setters.get(key);

			// V případě, že je hodnota typu JsonArray,
			// tak je potřeba ji zvlášť přelouskat, resp. každý prvek pole
			// nechat přeložit jako samostatný JsonObject
			if (val.getClass().getSimpleName() == JsonArray.class.getSimpleName()) {
				val = parseJsonArray((JsonArray) val);
			}

			if (val.getClass().getSimpleName() == JsonObject.class.getSimpleName()) {
				val = parseJsonObject((JsonObject) val);
			}

			// Zavolání setteru daného atributu
			// Chyba tady může nastat, pokud by byl argument mismatch
			// Basically se to zatím nebudu pokoušet ošetřovat.
			try {
				setter.invoke(instance, val);
			}
			catch (Exception e) {
			}

		}

		return instance;
	}

	/**
	 * Metoda pro parsování JSON pole JSON objektů
	 * 
	 * @param array Pole typu JsonArray
	 * @return ArrayList naparsovaných objektů
	 * @throws ClassNotFoundException      Pokud objekt v poli nemá platnou třídu (viz parseJsonObject)
	 * @throws ClassInstantiationException Pokud nešla vytvořit instance ze třídy, kterou má objekt v poli (viz
	 *                                     parseJsonObject)
	 */
	private static ArrayList<Object> parseJsonArray(JsonArray array) throws ClassNotFoundException, ClassInstantiationException {
		if (array.size() == 0) return new ArrayList<Object>();

		var list = new ArrayList<Object>();

		for (int i = 0; i < array.size(); i++) {
			list.add(parseJsonObject((JsonObject) array.get(i)));
		}

		return list;
	}

	/**
	 * Metoda pro převod instance třídy na JSON objekt
	 * 
	 * @param objekt Instance třídy
	 * @return JSON objekt reprezentující objekt
	 */
	public static JsonObject encodeObjectToJSON(Object objekt) {
		var methods = ObjectUtils.mapMethodsAndFields(objekt.getClass(), ObjectUtils::getGetters);
		var data = new JsonObject();

		for (var entry : methods.entrySet()) {
			var key = entry.getKey();
			var val = entry.getValue();

			// Serializace různých kolekcí
			if (Collection.class.isAssignableFrom(val.getReturnType())) {
				try {
					// Tím, že víme, že návratový typ je potomkem kolekce,
					// můžeme si docela bezpečně type-castnout do kolekce
					Collection<?> abstractCollection = (Collection<?>) val.invoke(objekt);
					data.put(key, encodeArrayToJson(abstractCollection));
				}
				catch (Exception e) {
				}
			}
			else {
				try {
					data.put(key, val.invoke(objekt));
				}
				catch (Exception e) {
					e.printStackTrace();
				}
			}

		}

		data.put("class", objekt.getClass().getCanonicalName());

		return data;
	}

	/**
	 * Pomocná metoda pro překlad kolekce do objektu typu JSONArray
	 * 
	 * @param kolekce Kolekce, která se má přeložit
	 * @return Instance typu JSONArray s prvky kolekce typu JSONObject
	 */
	private static JsonArray encodeArrayToJson(Collection<?> kolekce) {
		var koleckeVJson = kolekce.stream().map((Object prvek) -> encodeObjectToJSON(prvek)).toList();

		return new JsonArray(koleckeVJson);
	}
}
