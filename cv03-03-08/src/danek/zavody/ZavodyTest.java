package danek.zavody;

public class ZavodyTest {
	public static void main(String[] args) {
		
		Zavodnik bohumila = new Zavodnik("Bohumila", "Poggersová", Pohlavi.Z);
		Zavodnik rob = new Zavodnik("Rob", "Swire", Pohlavi.M);
		Zavodnik hugo = new Zavodnik("Hugo", "Madeon");
		hugo.setPohlavi(Pohlavi.NB);
		
		Zavod jizerska = new Zavod("Jizerská 50");
		jizerska.registrovat(hugo, rob, bohumila);
		
		System.out.printf("Závod: %s%n", jizerska.getNazev());
		System.out.printf("Počet registrací: %s%n", jizerska.getPocetRegistraci());
		
		System.out.println();
		System.out.println(jizerska.toString());
		
		Zavod sosnova = new Zavod("Sosnová");
		sosnova.registrovat(hugo, rob);
		
	}
}
