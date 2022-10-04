/*
 * Copyright (C) 2022 Jirina Kralovcova
 * This program is part of Java programing education. 
 * You can use it as you need to learn Java. 
 */
package cv12.banka;

import java.io.DataInput;
import java.io.DataOutput;
import java.io.IOException;

/**
 *
 * @author Jirina
 */
public class Transakce {
    
    private int den,mesic,rok;
    private double castka;

    public Transakce(int den, int mesic, int rok, double castka) {
        this.den = den;
        this.mesic = mesic;
        this.rok = rok;
        this.castka = castka;
    }
    
    public void uloz(DataOutput dof) throws IOException{
        dof.writeInt(den);
        dof.writeInt(mesic);
        dof.writeInt(rok);
        dof.writeDouble(castka);
        
    }
    
    
    public static Transakce nacti(DataInput dif) throws IOException{        
        return new Transakce(dif.readInt(),dif.readInt(),dif.readInt(),dif.readDouble());     
    }

    @Override
    public String toString() {
        return "Transakce{" + "den=" + den + ", mesic=" + mesic + ", rok=" + rok + ", castka=" + castka + '}';
    }
    
    
    
    
    
}
