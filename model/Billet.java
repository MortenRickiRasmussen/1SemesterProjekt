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
    private int sæde;

    public Billet(int id, int forestillings_id, int række, int sæde) {
        this.id = id;
        this.forestillings_id = forestillings_id;
        this.række = række;
        this.sæde = sæde;
    }

    public int getForestillings_id() {
        return forestillings_id;
    }

    public int getRække() {
        return række;
    }

    public int getSæde() {
        return sæde;
    }
    
    
}
