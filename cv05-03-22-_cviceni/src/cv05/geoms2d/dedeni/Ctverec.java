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
public class Ctverec extends Geom2D{
    private final double a;
    private final double plocha;
    private final double obvod;

    
    public Ctverec(double a){
        if(a < 0) throw new IllegalArgumentException("Neplatny rozmer " + a);
        this.a = a;
        this.obvod = 4*a;
        this.plocha = a*a;
    }

    public double getA() {
        return a;
    }

    @Override
    public double getPlocha() {
        return this.plocha;
    }

    @Override
    public double getObvod() {
        return this.obvod;
    }

    @Override
    public String toString() {
        return "Ctverec{" + "a=" + a + '}';
    }
    
    
    
}
