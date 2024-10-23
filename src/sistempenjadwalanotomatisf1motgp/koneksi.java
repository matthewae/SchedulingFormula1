package sistempenjadwalanotomatisf1motgp;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.sql.Statement;

/**
 * Koneksi class to manage database connections.
 * This class provides methods to open and close the connection to the database.
 */
public class koneksi {

    private Connection db_formula1;
    private Statement statement;
    private PreparedStatement preparedStatement;

    public koneksi() {
        this.db_formula1 = null;
    }

    /**
     * Opens a connection to the database.
     */
    public void bukaKoneksi() {
        try {
            Class.forName("com.mysql.cj.jdbc.Driver");
            db_formula1 = DriverManager.getConnection(
                    "jdbc:mysql://localhost:3306/db_formula1?user=root&password=");
            System.out.println("Koneksi berhasil.");
        } catch (SQLException e) {
            System.err.println("Koneksi gagal: " + e.getMessage());
        } catch (ClassNotFoundException e) {
            System.err.println("Driver tidak ditemukan: " + e.getMessage());
        }
    }

    /**
     * Closes the database connection and other resources.
     */
    public void tutupKoneksi() {
        try {
            if (statement != null) {
                statement.close();
            }
            if (preparedStatement != null) {
                preparedStatement.close();
            }
            if (db_formula1 != null) {
                db_formula1.close();
            }
            System.out.println("Koneksi ditutup.");
        } catch (SQLException e) {
            System.err.println("Gagal menutup koneksi: " + e.getMessage());
        }
    }

    // Getter for the connection
    public Connection getDbformula1() {
        return db_formula1;
    }

  
}
