package utility;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class ConnectionDB {

    public static Connection createConnection() {
        try {
            String dbURL = String.format(
                    "", // String URL goes here
                    System.getenv("HOST"),
                    System.getenv("DB"),
                    System.getenv("USER"),
                    System.getenv("PASSWORD"),
                    System.getenv("PORT")
            );
            return DriverManager.getConnection(dbURL);
        } catch (SQLException e) {
            e.printStackTrace();
            return null;
        }
    }

    public static void main(String[] args) {
        System.out.println(createConnection());
    }
}
