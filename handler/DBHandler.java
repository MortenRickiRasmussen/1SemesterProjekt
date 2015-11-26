package handler;

import java.io.*;
import java.sql.*;
import java.util.ArrayList;
import model.*;

/**
 * @author Jakob Ferdinandsen
 * @author Morten Ricki Rasmussen
 *
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

    /**
     *
     * @throws IOException
     * @throws ClassNotFoundException
     * @throws SQLException
     */
    public DBHandler() throws IOException, ClassNotFoundException, SQLException {
        settingsFil = new FileHandler("DatabaseIndstillinger.txt");
        dbSettings = settingsFil.openFile();
        database_usr = dbSettings.get(1);
        database_pwd = dbSettings.get(3);
        database_url = dbSettings.get(5);
        schema = dbSettings.get(7);
        createConn();
    }

    /**
     * Metode som bruges til at opdatere forbindelsen til DB, sådan at DB
     * indstillinger kan skiftes. Metoden overskriver filen med DB
     * indstillinger, hvis der er blevet oprrettet forbindelse til ny database
     *
     * @param usr Brugernavn til databasen
     * @param pw Kodeord til databasen
     * @param url URL til databasen
     * @param schema Database navnet
     * @throws SQLException
     * @throws ClassNotFoundException
     * @throws FileNotFoundException
     * @throws UnsupportedEncodingException
     */
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

    /**
     * Metode som bruges til at reconnecte til DB. Retunere en boolean for at
     * fortælle om der blev oprettet forbindelse
     *
     * @return Boolean som fortæller om der blev oprettet forbindelse
     * @throws SQLException
     * @throws ClassNotFoundException
     */
    public boolean reconnectToDB() throws SQLException, ClassNotFoundException {
        close();
        boolean connected = createConn();
        return connected;
    }

    /**
     * Metode til at oprette forbindelse til database.
     *
     * @return Boolean til at fortælle om der blev oprettet forbindelse
     * @throws ClassNotFoundException
     * @throws SQLException
     */
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

    /**
     * Metode til at lukke for forbindelse samt statement
     *
     * @throws SQLException
     */
    public void close() throws SQLException {
        stmt.close();
        conn.close();
    }

    /**
     * Metode til at hente noget fra databasen. Denne retunere et ResultSet med
     * alt dataen. Metoden bruges af andre metoder
     *
     * @param mySQLStatement MySQLStatement til brug af databasen
     * @return ResultSet
     * @throws SQLException
     * @throws ClassNotFoundException
     */
    public ResultSet databaseRetrive(String mySQLStatement) throws SQLException, ClassNotFoundException {
        ResultSet rs = null;
        createConn();
        rs = stmt.executeQuery(mySQLStatement);
        return rs;
    }

    /**
     * Metode til at udføre en vilkårlig handling på databasen. Metoden bruges
     * af andre metoder
     *
     * @param mySQLStatement MySQLStatement til brug af databasen
     * @throws SQLException
     * @throws ClassNotFoundException
     */
    public void databaseExecute(String mySQLStatement) throws SQLException, ClassNotFoundException {
        createConn();
        stmt.execute(mySQLStatement);
        close();
    }

    /**
     * Metode til at hente forestillinger fra databasen.
     *
     * @param rs ResultSet
     * @return funden forestilling
     * @throws ClassNotFoundException
     * @throws SQLException
     */
    public Forestilling retrieveForestillinger(ResultSet rs) throws ClassNotFoundException, SQLException {

        int id = rs.getInt("id");
        Date dato = rs.getDate("dato");
        String tidspunkt = rs.getString("tidspunkt");
        int film_id = rs.getInt("film_id");
        int sal_id = rs.getInt("sal_id");

        Forestilling forestilling = new Forestilling(id, dato, tidspunkt, film_id, sal_id);

        return forestilling;
    }

    /**
     * Metode til at hente hvilke film som er i Databasen
     *
     * @return Film
     * @throws SQLException
     * @throws ClassNotFoundException
     */
    public Film retrieveFilm(ResultSet rs) throws SQLException, ClassNotFoundException {

        int id = rs.getInt("id");
        String titel = rs.getString("titel");
        int spilleTid = rs.getInt("spilleTid");

        Film film = new Film(id, titel, spilleTid);

        return film;
    }

    /**
     * Metode til at hente de forskellige sale fra databasen. Metoden bruges når
     * der skal oprettes nye forestillinger, sådan at de kun kan vælge
     * gyldigesale
     *
     * @param rs ResultSet
     * @return fundende sal
     * @throws SQLException
     * @throws ClassNotFoundException
     */
    public Sal retrieveSal(ResultSet rs) throws SQLException, ClassNotFoundException {

        int id = rs.getInt("id");
        String navn = rs.getString("navn");
        int rækker = rs.getInt("rækker");
        int sæder = rs.getInt("sæder");

        Sal sal = new Sal(id, navn, rækker, sæder);

        return sal;
    }

    /**
     * Metoden bruges til at bestemme hvilke pladser der allerede er optaget.
     * når man bestiller nye billetter
     *
     * @param rs ResultSet
     * @return funden billet
     * @throws ClassNotFoundException
     * @throws SQLException
     */
    public Billet retrieveBilleter(ResultSet rs) throws ClassNotFoundException, SQLException {

        int id = rs.getInt("id");
        int forestillings_id = rs.getInt("forestillings_id");
        int række = rs.getInt("række");
        int sæde = rs.getInt("sæde");

        Billet billet = new Billet(id, forestillings_id, række, sæde);

        return billet;
    }

    /**
     * Metoden bruges til at fylde data i ArrayLists som der efter kan bruges af
     * systemet. Metoden bruger alle de tidligere retriveSal, retriveFilm, osv.
     *
     * @param sale ArrayList til sale
     * @param film ArrayList til film
     * @param forestillinger ArrayList til forestillinger
     * @param billetter ArrayList til bestilte billetter
     * @throws SQLException
     * @throws ClassNotFoundException
     */
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

        mySQLStatement = "SELECT film.*, forestillinger.film_id, forestillinger.dato FROM forestillinger\n"
                + "LEFT JOIN film ON film.id = forestillinger.film_id WHERE dato >= CURDATE() GROUP BY film.id;";
        rs = databaseRetrive(mySQLStatement);
        while (rs.next()) {
            film.add(retrieveFilm(rs));
        }

        mySQLStatement = "SELECT * FROM forestillinger WHERE dato >= CURDATE() AND dato <= DATE_ADD(CURDATE(), INTERVAL + 4 WEEK);";
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

        mySQLStatement = "SELECT billetter.*, forestillinger.id, forestillinger.dato FROM forestillinger\n"
                + "RIGHT JOIN billetter ON forestillinger.id = billetter.forestillings_id \n"
                + "WHERE forestillinger.dato >= CURDATE();";
        rs = databaseRetrive(mySQLStatement);
        while (rs.next()) {
            billetter.add(retrieveBilleter(rs));
        }

        rs.close();
        close();

    }

    /**
     * Metode til at tilføje ny film til databasen.
     *
     * @param titel Filmtitlen
     * @param spilletid Spilletiden
     * @throws SQLException
     * @throws ClassNotFoundException
     */
    public void addFilm(String titel, int spilletid) throws SQLException, ClassNotFoundException {
        String mySQLStatement = "INSERT INTO film (titel, spilletid) VALUES ('"
                + titel + "',"
                + spilletid + ");";

        databaseExecute(mySQLStatement);
    }

    /**
     * Metode til at finde film frem fra databasen
     *
     * @param filmtitel - Filmtitlen
     * @return ArrayList med fundende film
     * @throws SQLException
     * @throws ClassNotFoundException
     */
    public ArrayList searchForFilm(String filmtitel) throws SQLException, ClassNotFoundException {
        String mySQLStatement = "SELECT * FROM film WHERE (titel LIKE '%"
                + filmtitel + "%');";

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

    /**
     * Metode til at tilføje en ny forstilling til databasen.
     *
     * @param dato Datoen for den nye forestilling
     * @param tidspunkt Tidspunktet for den nye forestilling
     * @param film_id Film_id på den film der bliver spillet
     * @param sal_id Sal_id på den sal som forestillingen vises i
     * @throws SQLException
     * @throws ClassNotFoundException
     */
    public void addForestilling(Date dato, String tidspunkt, int film_id, int sal_id) throws SQLException, ClassNotFoundException {
        String mySQLStatement = "INSERT INTO forestillinger (dato, tidspunkt, film_id, sal_id) VALUES ('"
                + dato + "','"
                + tidspunkt + "',"
                + film_id + ","
                + sal_id + ");";
        databaseExecute(mySQLStatement);
    }

    /**
     * Metode til at tilføje en ny sal til systemet.
     *
     * @param name Navnet på salen
     * @param rows antal rækker i salen
     * @param seats antal sæder på hver række
     * @throws SQLException
     * @throws ClassNotFoundException
     */
    public void addSal(String name, int rows, int seats) throws SQLException, ClassNotFoundException {
        String mySQLStatement = "INSERT INTO sale (navn, rækker, sæder) VALUES ('"
                + name + "',"
                + rows + ","
                + seats + ");";
        databaseExecute(mySQLStatement);
    }

    /**
     * Metode til at tilføje et nyt telefonnummer, hvis telefonnummeret ikke
     * allerede eksistere. Metoden returnere det id som telefonnummeret har fået
     * i databasen
     *
     * @param telefonNr Telefonnummer på den som bestiller billetter
     * @return Telefonnummer id
     * @throws SQLException
     * @throws ClassNotFoundException
     */
    public String addTelefonNr(int telefonNr) throws SQLException, ClassNotFoundException {
        String id = null;
        String mySQLStatement = "SELECT * FROM telefonnummer WHERE telefonnummer LIKE " + telefonNr;
        ResultSet rs = databaseRetrive(mySQLStatement);
        while (rs.next()) {
            id = "" + rs.getInt("id");
        }

        if (id == null) {
            mySQLStatement = "INSERT INTO telefonnummer (telefonnummer) VALUES ("
                    + telefonNr + ");";

            databaseExecute(mySQLStatement);
            mySQLStatement = "SELECT * FROM telefonnummer WHERE telefonnummer LIKE " + telefonNr;
            rs = databaseRetrive(mySQLStatement);
            while (rs.next()) {
                id = "" + rs.getInt("id");
            }
        }

        return id;
    }

    /**
     * Metoden bruges til at tilføje bestilite billetter til databasen.
     *
     * @param forestillingsId Id på den forestilling der bestilles billetter til
     * @param række Hvilken række billetten er til
     * @param sæde Hvilket sæde billetten er til
     * @param telefonnummer Telefonnummeret som der bestilles billetter med
     * @throws SQLException
     * @throws ClassNotFoundException
     */
    public void addBillet(int forestillingsId, int række, int sæde, int telefonnummer) throws SQLException, ClassNotFoundException {

        String telefon_id = addTelefonNr(telefonnummer);

        String mySQLStatement = "INSERT INTO billetter (forestillings_id, række, sæde, telefon_id) VALUES ("
                + forestillingsId + ","
                + række + ","
                + sæde + ","
                + telefon_id + ");";

        databaseExecute(mySQLStatement);
    }

}
