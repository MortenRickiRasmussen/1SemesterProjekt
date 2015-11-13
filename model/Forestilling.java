package model;

import java.sql.Date;


/**
 *
 * @author Morten Ricki Rasmussen 
 */
public class Forestilling {
    private int id;
    private Date dato;
    private String tidspunkt;
    private int film_id;
    private Sal sal;

    public Forestilling(int id, Date dato, String tidspunkt, int film_id) {
        this.id = id;
        this.dato = dato;
        this.tidspunkt = tidspunkt;
        this.film_id = film_id;
    }
    
    public Forestilling(int id, Date dato, String tidspunkt, int film_id, Sal sal) {
        this.id = id;
        this.dato = dato;
        this.tidspunkt = tidspunkt;
        this.film_id = film_id;
        this.sal = sal;
    }
    
    
}
