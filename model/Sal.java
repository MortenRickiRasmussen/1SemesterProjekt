package model;

/**
 * Objekt til at indholde informtion om en sal
 * @author Morten Ricki Rasmussen
 */

public class Sal {

    private int id;
    private String navn;
    private int rækker;
    private int sæder;

    public Sal(int id, String navn, int rækker, int sæder) {
        this.id = id;
        this.navn = navn;
        this.rækker = rækker;
        this.sæder = sæder;
    }

    public int getId() {
        return id;
    }

    public String getNavn() {
        return navn;
    }

    public int getRækker() {
        return rækker;
    }

    public int getSæder() {
        return sæder;
    }
    
    @Override
    public String toString() {
        return navn;
    }
    
}
