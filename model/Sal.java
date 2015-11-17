package model;

/**
 * Objekt til at indholde informtion om en sal
 *
 * @author Morten Ricki Rasmussen
 */
public class Sal {

    private int id;
    private String navn;
    private int rækker;
    private int sædder;

    public Sal(int id, String navn, int rækker, int sædder) {
        this.id = id;
        this.navn = navn;
        this.rækker = rækker;
        this.sædder = sædder;
    }

    public int getId() {
        return id;
    }

    public String getNavn() {
        return navn;
    }

    @Override
    public String toString() {
        return navn;
    }
}
