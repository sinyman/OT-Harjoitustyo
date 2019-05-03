
package sinyman.stockspal;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

public class DatabaseDAO {
    private Connection connection;

    public DatabaseDAO() {
        setupConnection();
        setupTables();
    }
    
    /**
    *   Gets a users password from the database
    *
    *@param name The username of the person whose password is being requested
    *
    *@return Returns the requested users password
    */
    public String getUsersPasswordByName(String name) {
        String query = "SELECT password FROM USER WHERE name = ? ;";
        
        try {
            PreparedStatement pstmt = connection.prepareStatement(query);
            
            // Setting the searched for value
            pstmt.setString(1, name);
            
            ResultSet rs = pstmt.executeQuery();
            
            while (rs.next()) {
                return rs.getString("password"); 
            }

        } catch (SQLException e) {
            System.err.println(e.getMessage());
            
        // If the resultset is empty a NullPointerException will be thrown
        // this just returns a string that indicates no user found
        // without crashing the application
        } catch (NullPointerException npe) {
            return "User was not found!";
        }
        
        return null;
    }
    
    /**
     *  Registers a new user into the database
     * 
     * @param username The username of the person to be registered
     * @param password The password of the person to be registered
     * 
     * @return Returns a boolean value representing whether the registration was successful or not
     */
    public boolean registerUser(String username, String password) {
        String query = "INSERT INTO USER(name, password) VALUES(?,?)";
        
        int rowsUpdated = 0;
        try {
            PreparedStatement pstmt = connection.prepareStatement(query);
            
            // Setting the searched for value
            pstmt.setString(1, username);
            pstmt.setString(2, password);
            
            rowsUpdated = pstmt.executeUpdate();
            
        } catch (SQLException e) {
            System.err.println(e.getMessage());
        }
        
        // If executeUpdate returns 1 the transaction worked
        // otherwise it failed. This method returns true/false
        // based on if the registration was successful or not
        return rowsUpdated > 0;
    }
    
    /**
     *  A method to check if a username is already registered in the database
     * 
     * @param username The username of the account to be checked against database records
     * @return Returns 'true' if person is already registered, 'false' otherwise
     */
    public boolean userIsAlreadyRegistered(String username) {
        String query = "SELECT * FROM USER WHERE name = ? ;";
        
        try {
            PreparedStatement pstmt = connection.prepareStatement(query);
            
            // Setting the searched for value
            pstmt.setString(1, username);
            
            ResultSet rs = pstmt.executeQuery();
            
            // Checking if the resultset is empty
            // and therefore checking if anyone
            // with this username is registered
            if (!rs.next()) {
                return false;
            }

        } catch (SQLException e) {
            System.err.println(e.getMessage());
        }
        
        return true;
    }

    private Connection setupConnection() {
        try {
            this.connection = DriverManager.getConnection("jdbc:sqlite:./StocksPalDB.db");
            return connection;

        } catch (SQLException e) {
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
        
        String userStocksTableQuery = "CREATE TABLE IF NOT EXISTS USERSTOCK (\n"
                + "	id INTEGER PRIMARY KEY AUTOINCREMENT,\n"
                + "	price INTEGER,\n"
                + "	symbol VARCHAR(5) NOT NULL,\n"
                + "	owner_id INTEGER NOT NULL REFERENCES USER(id) ON UPDATE CASCADE\n"
                + ");";
        
        try {
            Statement stmt = connection.createStatement();
            
            // Creating the tables in the DB via SQL-statements
            stmt.execute(userTableQuery);
            stmt.execute(userStocksTableQuery);

        } catch (SQLException e) {
            System.err.println(e.getMessage());
        }
    }
}
