/*
 *  Copyright (C) 2022 Jirina
 *  This program is part of Java programing education. 
 *  You can use it as you need to learn Java. 
 */
package cv05.apps;

import cv05.geoms2d.dedeni.Ctverec;
import cv05.geoms2d.dedeni.Geom2D;
import cv05.geoms2d.dedeni.Obdelnik;
import cv05.geoms2d.dedeni.Trojuhelnik;
import java.util.Arrays;

/**
 *
 * @author jirina.kralovcova
 */
public class TestGeom2D {

    public static void main(String[] args) {
        Trojuhelnik trojuhelnik = new Trojuhelnik(4, 3, 5);
        System.out.println(trojuhelnik.getPlocha());
        
        Geom2D[] geoms = new Geom2D[3];
        geoms[0] = trojuhelnik;
        geoms[1] = new Obdelnik(1, 8);
        geoms[2] = new Ctverec(2);
        
        for (Geom2D geom : geoms){
            System.out.println(geom + " " + geom.getGeomParams());
        }
        
        Arrays.sort(geoms, Geom2D.COMP_OBVOD);
        
        System.out.println("");
         for (Geom2D geom : geoms){
            System.out.println(geom + " " + geom.getGeomParams());
        }
    }
}
