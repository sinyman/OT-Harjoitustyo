
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
            connection = DriverManager.getConnection("jdbc:sqlite:./StocksPalDB.db");
            return connection;
            
        } catch(SQLException e) {
            System.err.println(e.getMessage());
        }
        
        return null;
    }
    
    private void setupTables() {
        String userTableQuery = "CREATE TABLE IF NOT EXISTS USER (\n"
                + "	id integer PRIMARY KEY NOT NULL AUTOINCREMENT,\n"
                + "	name text NOT NULL,\n"
                + "	password text NOT NULL\n"
                + ");";
        try {
            Statement stmt = connection.createStatement();
            stmt.execute(userTableQuery);
            
        } catch(SQLException e) {
            System.err.println(e.getMessage());
        }
    }
}
