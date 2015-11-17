package model;

/**
 * Objekt som indeholder info om bestilte billetter
 *
 * @author Morten Ricki Rasmussen
 */
public class Billet {

    private int id;
    private int forestillings_id;
    private int række;
    private int sædde;

    public Billet(int id, int forestillings_id, int række, int sædde) {
        this.id = id;
        this.forestillings_id = forestillings_id;
        this.række = række;
        this.sædde = sædde;
    }
}
