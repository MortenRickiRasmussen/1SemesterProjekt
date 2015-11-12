package model;

/**
 *
 * @author Morten Ricki Rasmussen 
 */
public class Film {
    private  int id;
    private String titel;
    private int spilleTid;
    
    public Film(int id, String titel, int spilleTid) {
        this.id = id;
        this.titel = titel;
        this.spilleTid = spilleTid;
    }
}
