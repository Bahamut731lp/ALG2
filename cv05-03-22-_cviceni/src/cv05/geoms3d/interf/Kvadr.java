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
 class Kvadr implements Geom3D {
    
    private final double a;
    private final double b;
    private final double c;
    private final double objem;
    private final double plocha;
    
    private Kvadr(double a,double b,double c){
        this.a=a;
        this.b=b;
        this.c=c;
        this.objem=a*b*c;
        this.plocha=2*((a*b)+(a*c)+(b*c));
    }
    
     static Kvadr getInstance(double a,double b,double c){
        if(a<0){throw new ChybnyRozmerException("Zaporny rozmer a: "+a);}
        if(b<0){throw new ChybnyRozmerException("Zaporny rozmer b: "+b);}
        if(c<0){throw new ChybnyRozmerException("Zaporny rozmer c: "+c);}
    
        return new Kvadr(a,b,c);
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
        return "Kvadr{" + "a=" + a + ", b=" + b + ", c=" + c + '}';
    }
    
    
    
    
}
