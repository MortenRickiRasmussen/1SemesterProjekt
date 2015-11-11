package handler;

import com.mysql.jdbc.Statement;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;

/**
 *
 * @author Morten Ricki Rasmussen
 */
public class DBHandler {

    private static final String JDBC_DRIVER = "com.mysql.jdbc.Driver";
    private static final String DATABASE_USR = "root";
    private static final String DATABASE_PWD = "kya94cjw";
    private static final String DATABASE_URL = "jdbc:mysql://localhost:3306";
    private static final String SCHEMA = "/faurkilde";
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

    public static ResultSet databaseRetrive(String mySQLStatement) throws ClassNotFoundException, SQLException {
        ResultSet rs = null;
        createConn();
        rs = stmt.executeQuery(mySQLStatement);
        return rs;
    }
}
