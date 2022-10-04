/*
 *  Copyright (C) 2022 Jirina
 *  This program is part of Java programing education. 
 *  You can use it as you need to learn Java. 
 */
package cv05.geoms2d.dedeni;

/**
 *
 * @author jirina.kralovcova
 */
public class Obdelnik extends Geom2D{
    
    private final double a;
    private final double b;

    public Obdelnik(double a, double b) {
        if(a < 0) throw new IllegalArgumentException("Neplatny rozmer " + a);
        if(b < 0) throw new IllegalArgumentException("Neplatny rozmer " + b);
        this.a = a;
        this.b = b;
    }

    public double getA() {
        return a;
    }

    public double getB() {
        return b;
    }
    
    
    @Override
    public double getPlocha() {
        return a*b;
    }

    @Override
    public double getObvod() {
        return 2*(a+b);
    }

    @Override
    public String toString() {
        return "Obdelnik{" + "a=" + a + ", b=" + b + '}';
    }
    
}
