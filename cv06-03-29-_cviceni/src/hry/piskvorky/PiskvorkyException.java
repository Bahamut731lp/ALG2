/*
 * Copyright (C) 2022 Jirina Kralovcova
 * This program is part of Java programing education. 
 * You can use it as you need to learn Java. 
 */
package hry.piskvorky;

/**
 * {@code PiskvorkyException} je třída vyjimek, jejichz vyhozeni/generovani
 * indikuje chybne pouziti prostredku v baliku {@code hry.piskvorky}.
 * <p>
 * Trida zavadi moznost urcit chybovy kod {@code errCode}, pomoci ktereho je
 * mozne podrobneji rozlisit konkretni chybove/nestandardni stavy.
 * <p>
 * Ruzne chybove stavy a jejich vyznam jsou nasledujici:
 * <ul>
 * <li> 100 - Chyba nerozlisena chybovym kodem.
 * <li> 101 - Zadane rozmery hraci plochy jsou mimo povoleny rozsah.
 * <li> 102 - Zadana pozice hraciho pole je mimo rozsah.
 * <li> 103 - Zadana pozice je jiz obsazena symbolem jednoho z hracu.
 * <li> 104 - Pozadavek na provedeni dalsiho tahu hry po jejim ukonceni.
 * </ul>
 *
 * @author ALG
 */
public class PiskvorkyException extends RuntimeException {

    private final int errCode;
    private final String dataInfo;

    public PiskvorkyException(String message) {
        this(message, 100, "");
    }

    /**
     * Vytvori objekt vyjimky s informaci o chybe {@code message} a chybovym
     * kodem {@code errCode}.
     *
     * @param message textova informace o chybe
     * @param errCode chybovy kod pro blizsi specifikaci nastale chyby
     */
    public PiskvorkyException(String message, int errCode) {
        this(message, errCode, "");
    }

    /**
     * Vytvori objekt vyjimky s informaci o chybe {@code message}, chybovym
     * kodem {@code errCode} a podrobnejsimi informacemi {@code dataInfo}
     *
     * @param message textova informace o chybe
     * @param errCode chybovy kod pro blizsi specifikaci nastale chyby
     * @param dataInfo podrobnejsi informace o chybe
     */
    public PiskvorkyException(String message, int errCode, String dataInfo) {
        super(message);
        this.errCode = errCode;
        this.dataInfo = dataInfo;
    }

    /**
     * Poskytuje chybovy kod pro blizsi rozliseni chyby.
     * @return chybovy kod
     */
    public int getErrCode() {
        return errCode;
    }

    /**
     * Poskytuje podrobnejsi informace o chybe.
     * @return podrobnejsi informace o chybe
     */
    public String getDataInfo() {
        return dataInfo;
    }
}
