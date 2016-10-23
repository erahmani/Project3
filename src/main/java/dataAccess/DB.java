package dataAccess;


//import com.mysql.jdbc.Connection;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.sql.Statement;

public class DB {
    public static Connection connectDB() {
        String JDBC_DRIVER = "com.mysql.jdbc.Driver";
        String db_url = "jdbc:mysql://localhost/bank2?useUnicode=yes&characterEncoding=utf-8";
        String user = "root";
        String pass = "";
        Connection conn = null;
        try {
            //Register JDBC driver
            Class.forName(JDBC_DRIVER);
            conn = DriverManager.getConnection(db_url, user, pass);
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return conn;
    }

    private static void createDB(String dbName) {
        Connection conn = null;
        try {
            conn = connectDB();
            Statement stmt = conn.createStatement();
            String query = "CREATE DATABASE " + dbName + " DEFAULT CHARACTER SET utf8 COLLATE utf8_persian_ci";
            stmt.executeUpdate(query);
        } catch (SQLException e) {
            e.printStackTrace();
        } finally {
            try {
                if (conn != null) {
                    conn.close();
                }
            } catch (SQLException e) {
                e.printStackTrace();
            }
        }

    }
public static void main(String[] s){
    initDB();
}
    private static void initDB() {
        createDB("Bank");

        String createCustomer = "CREATE TABLE Customer " +
                "(customerId INTEGER not NULL AUTO_INCREMENT, " +
                " PRIMARY KEY ( customerId )) " +
                " DEFAULT CHARACTER SET = utf8 " +
                " COLLATE = utf8_persian_ci";

        String createRealCustomer = "CREATE TABLE RealCustomer( " +
                " customerId INT NOT NULL, " +
                " firstName VARCHAR(45) NOT NULL, " +
                " lastName VARCHAR(45) NOT NULL, " +
                " fatherName VARCHAR(45) NOT NULL, " +
                " birthDay VARCHAR(45) NOT NULL, " +
                " nationalId VARCHAR(45) NOT NULL, " +
                " PRIMARY KEY (customerId), " +
                " UNIQUE INDEX nationalId_UNIQUE (nationalId ASC), " +
                " FOREIGN KEY (customerId) " +
                " REFERENCES Customer (customerId)" +
                " ON DELETE CASCADE " +
                " ON UPDATE RESTRICT ) "+
                " DEFAULT CHARACTER SET = utf8 " +
                " COLLATE = utf8_persian_ci";

        String createLegalCustomer = "CREATE TABLE LegalCustomer( " +
                " customerId INT NOT NULL, " +
                " companyName VARCHAR(45) NOT NULL, " +
                " registrationDate VARCHAR(45) NOT NULL, " +
                " economicCode VARCHAR(45) NOT NULL, " +
                " PRIMARY KEY (customerId), " +
                " UNIQUE INDEX economicCode_UNIQUE (economicCode ASC), " +
                " FOREIGN KEY (customerId) " +
                " REFERENCES Customer (customerId)" +
                " ON DELETE CASCADE " +
                " ON UPDATE RESTRICT ) "+
                " DEFAULT CHARACTER SET = utf8 " +
                " COLLATE = utf8_persian_ci";

        Connection conn = connectDB();
        try {
            Statement stmt = conn.createStatement();
            stmt.executeUpdate(createCustomer);
            stmt.executeUpdate(createRealCustomer);
            stmt.executeUpdate(createLegalCustomer);

        } catch (SQLException e) {
            e.printStackTrace();
        } finally {
            try {
                if (conn != null) {
                    conn.close();
                }
            } catch (SQLException e) {
                e.printStackTrace();
            }
        }

    }

}
