import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

/**
 * A class which manages the connection to database
 */
public class Database {
    private static final String URL =
            "jdbc:mysql://localhost:3306/database";
    private static final String USER = "user";
    private static final String PASSWORD = "password";
    private static Connection connection = null;

    //default constructor
    private Database() {
    }

    public static Connection getCon() {
        createConnection();
        return connection;
    }

    //method for creating a connection with database
    private static void createConnection() {
        try {
            connection=DriverManager.getConnection(URL, USER, PASSWORD);
            //connection.setAutoCommit(false);
        } catch (SQLException e) {
            System.err.println(e);
        }
    }

    //method for closing the connection with database
    public static void closeConnection() {
        try{
            connection.close();
        }
        catch (SQLException e) {
            System.err.println(e);
        }
    }
}