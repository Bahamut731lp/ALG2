/*
 *  Copyright (C) 2022 Jirina
 *  This program is part of Java programing education. 
 *  You can use it as you need to learn Java. 
 */
package cv05.geoms2d.dedeni;

import java.util.Comparator;

/**
 * Trida Geom2D je nadtridou trid plosnych/planarnich geometrickech 
 * objektu, kde kazdy objekt "umi" poskytnout svoji plochu a svuj obvod
 * @author jirina.kralovcova
 */
public abstract class Geom2D implements Comparable<Geom2D> {

    /**
     * Presnost porovnavani realnych cisel
     */
    public static final double EPS = 1E-14;

    /**
     * Objekt komparatoru pro vzejemne porovnavani dvou plosnych 
     * geometrickych objektu dle obvodu
     */
    public static final Comparator<Geom2D> COMP_OBVOD
            = new Comparator<>() {
        @Override
        public int compare(Geom2D o1, Geom2D o2) {
            double eps = EPS * Math.max(o1.getObvod(), o2.getObvod());
            if (Math.abs(o1.getObvod() - o2.getObvod()) < eps) {
                return 0;
            }
            if (o1.getObvod() < o2.getObvod()) {
                return -1;
            }
            return 1;
        }
    };

    /**
     * Objekt komparatoru pro vzejemne porovnavani dvou plosnych 
     * geometrickych objektu dle plochy
     */
    public static final Comparator<Geom2D> COMP_PLOCHA
            = new Comparator<>() {
        @Override
        public int compare(Geom2D o1, Geom2D o2) {
            double eps = EPS * Math.max(o1.getPlocha(), o2.getPlocha());
            if (Math.abs(o1.getPlocha() - o2.getPlocha()) < eps) {
                return 0;
            }
            if (o1.getPlocha() < o2.getPlocha()) {
                return -1;
            }
            return 1;
        }
    };

    /**
     * Metoda poskytujici plochu plosneho geometrickeho objektu
     * @return plocha geometrickeho objektu jako hodnota typu double
     */
    public abstract double getPlocha();

    /**
     * Metoda poskytujici obvod plosneho geometrickeho objektu
     * @return plocha geometrickeho objektu jako hodnota typu double
     */
    public abstract double getObvod();

    /**
     * Metoda pro porovnani daneho objektu s jinym objektem
     * @param gobj plosny geometricky objekt 
     * @return -1 je-li objekt mensi nez gobj, 0 v p59pad2 shody,
     *  1 v pripade, ze objekt je vetsi nez gobj.
     */
    @Override
    public int compareTo(Geom2D gobj) {
        double eps = EPS * Math.max(this.getPlocha(), gobj.getPlocha());
        if (Math.abs(this.getPlocha() - gobj.getPlocha()) < eps) {
            return 0;
        }
        if (this.getPlocha() < gobj.getPlocha()) {
            return -1;
        }
        return 1;
    }

    /**
     * Metoda pro zapis plochy a obvodu plosneho geometrickeho objektu 
     * do textoveho retezce
     * @return textovy retezec obsahujici dva ciselne udaje s plochou 
     * a obvodem geometrikeho objektu pro ktery je tato metoda volana     * 
     */
    public String getGeomParams() {
        return String.format("%f %f", getPlocha(), getObvod());
    }

}
