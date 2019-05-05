
package sinyman.stockspal;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;

public class DatabaseDAO {
    private static Connection connection;

    public DatabaseDAO() {
        setupConnection();
        setupTables();
    }
    
    /**
     *  A method for getting an ObservableList of all the stocks a user owns
     *  to later show in a TableView
     * 
     * @param username The username of the person whose stocks are to be returned
     * @return An ObservableList of a users owned stocks and following data
     */
    public ObservableList getUserStockAsObservableList(String username) {
        String query = "SELECT * FROM USERSTOCK WHERE owner_id = ? ;";
        ObservableList<StocksDataPiece> stocksList = FXCollections.observableArrayList();
        
        try {
            PreparedStatement pstmt = connection.prepareStatement(query);    
            pstmt.setInt(1, getUsersIDByName(username));
            
            ResultSet rs = pstmt.executeQuery();
            while (!rs.next()) {
                stocksList.add(new StocksDataPiece(rs.getString("symbol"), rs.getDouble("price"), rs.getInt("quantity")));
            }
            
        } catch (SQLException sqle) {
            sqle.printStackTrace();
        }
        
        return stocksList;
    }
    
    /**
     *  Adds a "stock-collection" to a user in the database
     * 
     * @param username The owner of the stocks
     * @param symbol Stock symbol/ticker
     * @param quantity The amount of said stock that was bought
     * @param buyingPrice The price at which the stock was bought
     * @return A boolean value based on if the transaction was successful or not
     */
    public boolean addUserStock(String username, String symbol, int quantity, double buyingPrice) {
        String query = "INSERT INTO USERSTOCK(price, symbol, quantity, owner_id) VALUES (?,?,?,?);";
        
        try {
            PreparedStatement pstmt = connection.prepareStatement(query);
            
            // Setting the values of the query
            pstmt.setDouble(1, buyingPrice);
            pstmt.setString(2, symbol);
            pstmt.setInt(3, quantity);
            pstmt.setInt(4, getUsersIDByName(username));
            
            // If the amount of rows changed are larger than 0, the transaction was
            // successful, therefore return true
            if (pstmt.executeUpdate() > 0) {
                return true;
            }
            
        } catch (SQLException sqle) {
            sqle.printStackTrace();
        }
        
        return false;
    }
    
    /**
    *   Gets a users id from the database
    *
    *@param name The username of the person whose user-id is being requested
    *
    *@return Returns the id of the requested person
    */
    public int getUsersIDByName(String name) {
        String query = "SELECT id FROM USER WHERE name = ? ;";
        
        try {
            PreparedStatement pstmt = connection.prepareStatement(query);
            
            // Setting the searched for value
            pstmt.setString(1, name);
            
            ResultSet rs = pstmt.executeQuery();
            
            while (rs.next()) {
                return rs.getInt("id");
            }

        } catch (SQLException e) {
            System.err.println(e.getMessage());
            
        // If the resultset is empty a NullPointerException will be thrown
        // this just returns an integer that indicates no user was found
        // without crashing the application
        } catch (NullPointerException npe) {
            return 0;
        }
        
        // 0 represents that the user wasn't found
        return 0;
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
                + "	price REAL,\n"
                + "	symbol VARCHAR(5) NOT NULL,\n"
                + "	quantity INTEGER NOT NULL,\n"
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
