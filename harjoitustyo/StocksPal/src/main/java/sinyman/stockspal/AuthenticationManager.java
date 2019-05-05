
package sinyman.stockspal;

public class AuthenticationManager {
    static String userLoggedIn;
    private DatabaseDAO dbDAO = new DatabaseDAO();
    
    public AuthenticationManager() {}
    
    /**
    *   Logs the user in to the system
    *
    *@param name The username of the person logging in
    *@param password The password of the user logging in
    *
    *@return Returns 'true' if the login was successful, 'false' otherwise
    */
    public Boolean logIn(String name, String password) {
        String response = dbDAO.getUsersPasswordByName(name);
        
        // Checking first to see if a user was found and transaction succesful
        if (!response.equals("User was not found!")) {
            if (response.equals(password)) {
                userLoggedIn = name;
                return true;
            }
        }
        
        return false;
    }
    
    /**
    *   Logs the user out of the system
    */
    public void logOut() {
        userLoggedIn = null;
    }
    
    /**
    *   Registers a new user to use the system
    *
    *@param name The username of the person to register a new account
    *@param password The password for the account
    *
    *@return Returns 'true' if the registration was successful, 'false' otherwise
    */
    public boolean registerUser(String name, String password) {
        if (!dbDAO.userIsAlreadyRegistered(name)) {
            return dbDAO.registerUser(name, password);
        }
        return false;
    }
}
