package handler;

import java.sql.*;
import java.util.ArrayList;
import model.*;

/**
 *
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
    
    public DBHandler(){
        database_usr = "mortyhdd_1SemPro";
        database_pwd = "kya94cjw";
        database_url = "mortyhd.dk:3306";
        schema = "1SemesterProjekt";
    }
    
    public void updateDBConn(String usr, String pw, String url, String schema) throws SQLException, ClassNotFoundException {
        database_usr = usr;
        database_pwd = pw;
        database_url = url;
        this.schema = schema;
        reconnectToDB();
    }

    public void reconnectToDB() throws SQLException, ClassNotFoundException {
        close();
        createConn();
    }

    public void createConn() throws ClassNotFoundException, SQLException {
        Class.forName(JDBC_DRIVER);
        conn = DriverManager.getConnection("jdbc:mysql://" + database_url + "/" + schema, database_usr, database_pwd);
        stmt = (Statement) conn.createStatement();
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

    public Forestilling retriveForestillinger(ResultSet rs) throws ClassNotFoundException, SQLException {

        int id = rs.getInt("id");
        Date dato = rs.getDate("dato");
        String tidspunkt = rs.getString("tidspunkt");
        int film_id = rs.getInt("film_id");

        Forestilling forestilling = new Forestilling(id, dato, tidspunkt, film_id);

        return forestilling;
    }

    public Film retriveFilm(ResultSet rs) throws SQLException, ClassNotFoundException {

        int id = rs.getInt("id");
        String titel = rs.getString("titel");
        int spilleTid = rs.getInt("spilleTid");

        Film film = new Film(id, titel, spilleTid);

        return film;
    }

    public Sal retriveSal(ResultSet rs) throws SQLException, ClassNotFoundException {

        int id = rs.getInt("id");
        String navn = rs.getString("navn");
        int rækker = rs.getInt("rækker");
        int sædder = rs.getInt("sædder");

        Sal sal = new Sal(id, navn, rækker, sædder);

        return sal;
    }

    public Billet retriveBilleter(ResultSet rs) throws ClassNotFoundException, SQLException {

        int id = rs.getInt("id");
        int forestillings_id = rs.getInt("forestillings_id");
        int række = rs.getInt("række");
        int sædde = rs.getInt("sædde");

        Billet billet = new Billet(id, forestillings_id, række, sædde);

        return billet;
    }

    public void loadArrayLists(ArrayList sale, ArrayList film, ArrayList forestillinger, ArrayList billetter) throws SQLException, ClassNotFoundException {
        sale.clear();
        film.clear();
        forestillinger.clear();
        billetter.clear();

        String mySQLStatement = "SELECT * FROM sale";
        ResultSet rs = databaseRetrive(mySQLStatement);
        while (rs.next()) {
            sale.add(retriveSal(rs));
        }

        mySQLStatement = "SELECT * FROM film";
        rs = databaseRetrive(mySQLStatement);
        while (rs.next()) {
            film.add(retriveFilm(rs));
        }

        mySQLStatement = "SELECT * FROM forestillinger";
        rs = databaseRetrive(mySQLStatement);
        while (rs.next()) {
            forestillinger.add(retriveForestillinger(rs));
        }

        mySQLStatement = "SELECT * FROM billeter";
        rs = databaseRetrive(mySQLStatement);
        while (rs.next()) {
            billetter.add(retriveBilleter(rs));
        }

        rs.close();
        close();

    }
}
