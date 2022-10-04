package danek.zavody;

public enum Pohlavi {
    M("Muž"),
    Z("Žena"),
    NB("Nebinární"),
	TG("Transgender"),
	IS("Intersex"),
	NN("Neuvedeno");

    public final String label;

    private Pohlavi(String label) {
        this.label = label;
    }
}
