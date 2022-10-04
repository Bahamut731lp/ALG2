package cv05.geoms3d.interf;

import java.util.Comparator;

public interface Geom3D extends Comparable<Geom3D> {

    public static enum TypObjektu {
        KRYCHLE, KVADR;
    }

    public static final Comparator<Geom3D> COMP_PLOCHA
            = new Comparator<>() {
        @Override
        public int compare(Geom3D o1, Geom3D o2) {
            return (int) Math.round(Math.signum(o1.getPlocha() - o2.getPlocha()));
        }
    };

    public static Geom3D getInstance(double... a) {
        if (a == null) {
            return null;
        }
        if (a.length == 1) {
            return Krychle.getInstance(a[0]);
        }
        if (a.length == 3) {
            return Kvadr.getInstance(a[0], a[1], a[2]);
        }
        return null;
    }

    public static Geom3D getInstance(TypObjektu typ, double... a) {
        switch (typ) {
            case KRYCHLE:
                return Krychle.getInstance(a[0]);
            case KVADR:
                return Kvadr.getInstance(a[0], a[1], a[2]);
        }
        return null;
    }

    public double getObjem();

    public double getPlocha();

    @Override
    public default int compareTo(Geom3D gobj) {
        return (int) Math.round(Math.signum(this.getObjem() - gobj.getObjem()));
    }

    public default String getPlochaObjem() {
        String st = "Objem: " + getObjem() + " Plocha: " + getPlocha();
        return st;
    }
}
