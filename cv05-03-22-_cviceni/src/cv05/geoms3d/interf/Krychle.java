/*
 *  Copyright (C) 2022 Jirina
 *  This program is part of Java programing education. 
 *  You can use it as you need to learn Java. 
 */
package cv05.geoms3d.interf;

/**
 *
 * @author jirina.kralovcova
 */
 class Krychle implements Geom3D {
    
    private final double a;
    private final double objem;
    private final double plocha;
    
    private Krychle(double a){
        this.a=a;
        this.objem=a*a*a;
        this.plocha=6*a*a;
    }
    
     static Krychle getInstance(double a){
        if(a<0){
            return null;
        }
        return new Krychle(a);
    }

    @Override
    public double getObjem() {
        return objem;
    }

    @Override
    public double getPlocha() {
        return plocha;
    }

    @Override
    public String toString() {
        return "Krychle{" + "a=" + a + '}';
    }
    
    
    
}
