package model;

import java.sql.Date;
import java.text.DateFormat;

/**
 *
 * @author Morten Ricki Rasmussen
 */
public class Forestilling {

    private int id;
    private String dato;
    private String tidspunkt;
    private String filmTitel;
    private int film_id;
    private int sal_id;
    private String salNavn;

    public Forestilling(int id, Date dato, String tidspunkt, int film_id, int sal_id) {
        this.id = id;
        this.dato = DateFormat.getDateInstance().format(dato);
        this.tidspunkt = tidspunkt;
        this.film_id = film_id;
        this.sal_id = sal_id;
    }

    public int getId() {
        return id;
    }

    public String getDato() {
        return dato;
    }

    public String getTidspunkt() {
        return tidspunkt;
    }

    public int getFilmId() {
        return film_id;
    }

    public int getSalId() {
        return sal_id;
    }

    public String getFilmTitel() {
        return filmTitel;
    }

    public String getSalNavn() {
        return salNavn;
    }

    public void setFilmTitel(String titel) {
        this.filmTitel = titel;
    }

    public void setSal(String sal) {
        this.salNavn = sal;
    }
    
    public String toString() {
        return filmTitel;
    }

}
