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
public class Trojuhelnik extends Geom2D {
    private final double a;
    private final double b;
    private final double c;

    public Trojuhelnik(double a, double b, double c) {
        if(a < 0) throw new IllegalArgumentException("Neplatny rozmer " + a);
        if(b < 0) throw new IllegalArgumentException("Neplatny rozmer " + b);
        if(c < 0) throw new IllegalArgumentException("Neplatny rozmer " + c);
        if(a+b < c || Math.abs(a - b) > c)throw new IllegalArgumentException("Neplatne rozmery");
        this.a = a;
        this.b = b;
        this.c = c;
    }

    public double getA() {
        return a;
    }

    public double getB() {
        return b;
    }

    public double getC() {
        return c;
    }
    
    @Override
    public double getPlocha() {
       double s = getObvod()/2;
       return Math.sqrt(s*(s-a)*(s-b)*(s -c));
    }

    @Override
    public double getObvod() {
        return a+b+c;
    }

    @Override
    public String toString() {
        return "Trojuhelnik{" + "a=" + a + ", b=" + b + ", c=" + c + '}';
    }
     
    
}
