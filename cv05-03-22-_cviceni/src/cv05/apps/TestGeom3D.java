/*
 *  Copyright (C) 2022 Jirina
 *  This program is part of Java programing education. 
 *  You can use it as you need to learn Java. 
 */
package cv05.apps;

import cv05.geoms3d.interf.Geom3D;
import java.util.ArrayList;
import java.util.Collections;

/**
 *
 * @author jirina.kralovcova
 */
public class TestGeom3D {

    public static void main(String[] args) {
        
        ArrayList<Geom3D> geo = new ArrayList<>();
        geo.add(Geom3D.getInstance(3));
        geo.add(Geom3D.getInstance(Geom3D.TypObjektu.KVADR,1, 8, 8));
        geo.add(Geom3D.getInstance(9));
        geo.add(Geom3D.getInstance(2, 3, 4));
        
        for(Geom3D g: geo){
            System.out.println(g+" "+g.getPlochaObjem());
        }
        
        System.out.println("");
        Collections.sort(geo, Geom3D.COMP_PLOCHA);
        
        for(Geom3D g: geo){
            System.out.println(g+" "+g.getPlochaObjem());
        }
        
        System.out.println("");
        Collections.sort(geo);
        
        for(Geom3D g: geo){
            System.out.println(g+" "+g.getPlochaObjem());
        }
    }
    
}
