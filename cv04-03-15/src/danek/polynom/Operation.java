package danek.polynom;

@FunctionalInterface
public interface Operation {
    public double apply(double t, double u);
}