package handler;

import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.UnsupportedEncodingException;
import java.sql.*;
import java.util.ArrayList;
import model.*;

/**
 * @author Jakob Ferdinandsen
 * @author Morten Ricki Rasmussen
 */
public class DBHandler {

    private final String JDBC_DRIVER = "com.mysql.jdbc.Driver";
    private String database_usr;
    private String database_pwd;
    private String database_url;
    private String schema;
    private Connection conn;
    private Statement stmt;
    private FileHandler settingsFil;
    private ArrayList<String> dbSettings;

    public DBHandler() throws IOException, ClassNotFoundException, SQLException {
        settingsFil = new FileHandler("src/rescources/DatabaseIndstillinger.txt");
        dbSettings = settingsFil.openFile();
        database_usr = dbSettings.get(1);
        database_pwd = dbSettings.get(3);
        database_url = dbSettings.get(5);
        schema = dbSettings.get(7);
        createConn();
    }

    public void updateDBConn(String usr, String pw, String url, String schema) throws SQLException, ClassNotFoundException, FileNotFoundException, UnsupportedEncodingException {
        database_usr = usr;
        database_pwd = pw;
        database_url = url;
        this.schema = schema;
        reconnectToDB();
        if (reconnectToDB() == true) {
            settingsFil.updateDBFile(database_usr, database_pwd, database_url, this.schema);
        }
    }

    public boolean reconnectToDB() throws SQLException, ClassNotFoundException {
        close();
        boolean connected = createConn();
        return connected;
    }

    public boolean createConn() throws ClassNotFoundException, SQLException {
        boolean connected = false;
        Class.forName(JDBC_DRIVER);
        conn = DriverManager.getConnection("jdbc:mysql://" + database_url + "/" + schema, database_usr, database_pwd);
        stmt = (Statement) conn.createStatement();
        if (conn.isValid(1)) {
            connected = true;
        }
        return connected;
    }

    public void close() throws SQLException {
        stmt.close();
        conn.close();
    }

    public ResultSet databaseRetrive(String mySQLStatement) throws SQLException, ClassNotFoundException {
        ResultSet rs = null;
        createConn();
        rs = stmt.executeQuery(mySQLStatement);
        return rs;
    }

    public void databaseExecute(String mySQLStatement) throws SQLException, ClassNotFoundException {
        createConn();
        stmt.execute(mySQLStatement);
        close();
    }

    public Forestilling retrieveForestillinger(ResultSet rs) throws ClassNotFoundException, SQLException {

        int id = rs.getInt("id");
        Date dato = rs.getDate("dato");
        String tidspunkt = rs.getString("tidspunkt");
        int film_id = rs.getInt("film_id");
        int sal_id = rs.getInt("sal_id");

        Forestilling forestilling = new Forestilling(id, dato, tidspunkt, film_id, sal_id);

        return forestilling;
    }

    public Film retrieveFilm(ResultSet rs) throws SQLException, ClassNotFoundException {

        int id = rs.getInt("id");
        String titel = rs.getString("titel");
        int spilleTid = rs.getInt("spilleTid");

        Film film = new Film(id, titel, spilleTid);

        return film;
    }

    public Sal retrieveSal(ResultSet rs) throws SQLException, ClassNotFoundException {

        int id = rs.getInt("id");
        String navn = rs.getString("navn");
        int rækker = rs.getInt("rækker");
        int sædder = rs.getInt("sædder");

        Sal sal = new Sal(id, navn, rækker, sædder);

        return sal;
    }

    public Billet retrieveBilleter(ResultSet rs) throws ClassNotFoundException, SQLException {

        int id = rs.getInt("id");
        int forestillings_id = rs.getInt("forestillings_id");
        int række = rs.getInt("række");
        int sædde = rs.getInt("sædde");

        Billet billet = new Billet(id, forestillings_id, række, sædde);

        return billet;
    }

    public void loadArrayLists(ArrayList<Sal> sale, ArrayList<Film> film, ArrayList<Forestilling> forestillinger, ArrayList<Billet> billetter) throws SQLException, ClassNotFoundException {
        sale.clear();
        film.clear();
        forestillinger.clear();
        billetter.clear();

        String mySQLStatement = "SELECT * FROM sale";
        ResultSet rs = databaseRetrive(mySQLStatement);
        while (rs.next()) {
            sale.add(retrieveSal(rs));
        }

        mySQLStatement = "SELECT * FROM film";
        rs = databaseRetrive(mySQLStatement);
        while (rs.next()) {
            film.add(retrieveFilm(rs));
        }

        mySQLStatement = "SELECT * FROM forestillinger";
        rs = databaseRetrive(mySQLStatement);
        while (rs.next()) {
            Forestilling forestilling = retrieveForestillinger(rs);
            for (Film film1 : film) {
                if (film1.getId() == forestilling.getFilmId()) {
                    forestilling.setFilmTitel(film1.getTitel());
                }
            }
            for (Sal sal1 : sale) {
                if (sal1.getId() == forestilling.getSalId()) {
                    forestilling.setSal(sal1.getNavn());
                }
            }
            forestillinger.add(forestilling);
        }

        mySQLStatement = "SELECT * FROM billetter";
        rs = databaseRetrive(mySQLStatement);
        while (rs.next()) {
            billetter.add(retrieveBilleter(rs));
        }

        rs.close();
        close();

    }

    public void addFilm(String titel, int spilletid) throws SQLException, ClassNotFoundException {
        String mySQLStatement = "INSERT INTO film (titel, spilletid) VALUES ('"
                + titel + "',"
                + spilletid + ");";

        databaseExecute(mySQLStatement);
    }

    public ArrayList searchForFilm(String searchTerm) throws SQLException, ClassNotFoundException {
        String mySQLStatement = "SELECT * FROM film WHERE (titel LIKE '%"
                + searchTerm + "%');";
        
        ArrayList<Film> film = new ArrayList<>();

        ResultSet rs = databaseRetrive(mySQLStatement);

        while (rs.next()) {
            int id = rs.getInt("id");
            String titel = rs.getString("titel");
            int spilletid = rs.getInt("spilletid");

            Film film1 = new Film(id, titel, spilletid);
            film.add(film1);
        }
        
        return film;
    }
    
    public void addForestilling(Date dato, String tidspunkt, int film_id, int sal_id) throws SQLException, ClassNotFoundException {
        String mySQLStatement = "INSERT INTO forestillinger (dato, tidspunkt, film_id, sal_id) VALUES ('"
                + dato + "','"
                + tidspunkt + "',"
                + film_id + ","
                + sal_id + ");";
        databaseExecute(mySQLStatement);
    }
    
    public void addSal(String name, int rows, int seats) throws SQLException, ClassNotFoundException{
        String mySQLStatement = "INSERT INTO sale (navn, rækker, sædder) VALUES ('"
                + name + "',"
                + rows + ","
                + seats + ");";
        //System.out.println(mySQLStatement);
        databaseExecute(mySQLStatement);
    }
    
}
