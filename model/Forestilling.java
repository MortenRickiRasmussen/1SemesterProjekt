package model;

import java.sql.Date;
import java.text.DateFormat;
import javax.swing.ImageIcon;

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
    private ImageIcon plakat;
    
    public Forestilling(int id, Date dato, String tidspunkt, int film_id, int sal_id) {
        this.id = id;
        this.dato = DateFormat.getDateInstance().format(dato);
        this.tidspunkt = tidspunkt;
        this.film_id = film_id;
        this.sal_id = sal_id;
        plakat = new ImageIcon(getClass().getResource("/rescources/standardbillede.jpg"));
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
    
    public String getFilmTitelIgonoreCase() {
        return filmTitel.toLowerCase();
    }
    
    public ImageIcon getPlakat() {
        return plakat;
    }

    public String getSalNavn() {
        return salNavn;
    }

    public void setFilmTitel(String titel) {
        this.filmTitel = titel;
        try {
        plakat = new ImageIcon(getClass().getResource("/rescources/" + titel + ".jpg"));
        } catch (NullPointerException ex) {
            plakat = new ImageIcon(getClass().getResource("/rescources/standardbillede.jpg"));
        }
    }

    public void setSal(String sal) {
        this.salNavn = sal;
    }
    
    @Override
    public String toString() {
        return filmTitel;
    }
    
    

}
