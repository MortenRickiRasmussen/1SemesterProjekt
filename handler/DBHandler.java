package handler;

import java.sql.*;
import model.*;

/**
 *
 * @author Morten Ricki Rasmussen
 */
public class DBHandler {

    private static final String JDBC_DRIVER = "com.mysql.jdbc.Driver";
    private static final String DATABASE_USR = "mortyhdd_1SemPro";
    private static final String DATABASE_PWD = "kya94cjw";
    private static final String DATABASE_URL = "jdbc:mysql://mortyhd.dk:3306";
    private static final String SCHEMA = "/1SemesterProjekt";
    private static Connection conn;
    private static Statement stmt;

    public static void createConn() throws ClassNotFoundException, SQLException {
        Class.forName(JDBC_DRIVER);
        conn = DriverManager.getConnection(DATABASE_URL + SCHEMA, DATABASE_USR, DATABASE_PWD);
        stmt = (Statement) conn.createStatement();
    }

    public static void close() throws SQLException {
        stmt.close();
        conn.close();
    }

    public static ResultSet databaseRetrive(String mySQLStatement) throws SQLException, ClassNotFoundException{
        ResultSet rs = null;
        createConn();
        rs = stmt.executeQuery(mySQLStatement);
        return rs;
    }
    
    public static Forestilling retriveForestillinger(String mySQLStatement ) throws ClassNotFoundException, SQLException {
        
        ResultSet rs = databaseRetrive(mySQLStatement);
        
        int id = rs.getInt("id");
        Date dato = rs.getDate("dato");
        String tidspunkt = rs.getString("tidspunkt");
        int film_id = rs.getInt("film_id");
        int sal_id = rs.getInt("sal_id");
        
        Forestilling forestilling = new Forestilling(id, dato, tidspunkt, film_id, sal_id);        
        
        return forestilling;
    }
    
    public static Film retriveFilm(String mySQLStatement) throws SQLException, ClassNotFoundException {
        
        ResultSet rs = databaseRetrive(mySQLStatement);
        
        int id = rs.getInt("id");
        String titel = rs.getString("titel");
        int spilleTid = rs.getInt("spilleTid");
        
        Film film = new Film(id, titel, spilleTid);
        
        return film;
    }
    
    public static Sal retriveSal(String mySQLStatement) throws SQLException, ClassNotFoundException{
        
        ResultSet rs = databaseRetrive(mySQLStatement);
        
        int id = rs.getInt("id");
        String navn = rs.getString("navn");
        int rækker = rs.getInt("rækker");
        int sædder = rs.getInt("sædder");
        
        Sal sal = new Sal(id, navn, rækker, sædder);
        
        return sal;
    } 
    
    public static Billet retriveBilleter(String mySQLStatement) throws ClassNotFoundException, SQLException {
        
        ResultSet rs = databaseRetrive(mySQLStatement);
        
        int id = rs.getInt("id");
        int forestillings_id = rs.getInt("forestillings_id");
        int række = rs.getInt("række");
        int sædde = rs.getInt("sædde");        
        
        Billet billet = new Billet(id, forestillings_id, række, sædde);
        
        return billet;        
    }
}
