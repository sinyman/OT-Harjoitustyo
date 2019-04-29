
package sinyman.stockspal;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.sql.Statement;

public class DatabaseDAO {
    private Connection connection;

    public DatabaseDAO() {
        setupConnection();
        setupTables();
    }

    private Connection setupConnection() {
        try {
            this.connection = DriverManager.getConnection("jdbc:sqlite:./StocksPalDB.db");
            return connection;

        } catch(SQLException e) {
            System.err.println(e.getMessage());
        }

        return null;
    }

    private void setupTables() {
        String userTableQuery = "CREATE TABLE IF NOT EXISTS USER (\n"
                + "	id INTEGER PRIMARY KEY AUTOINCREMENT,\n"
                + "	name VARCHAR(20) NOT NULL,\n"
                + "	password VARCHAR(30) NOT NULL\n"
                + ");";
        try {
            Statement stmt = connection.createStatement();
            stmt.execute(userTableQuery);

        } catch(SQLException e) {
            System.err.println(e.getMessage());
        }
    }
}
