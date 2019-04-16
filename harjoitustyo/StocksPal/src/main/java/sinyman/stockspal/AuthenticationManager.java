
package sinyman.stockspal;


public class AuthenticationManager {
    private User userLoggedIn;
    
    public AuthenticationManager() {}
    
    public Boolean logIn(User user) {
        try {
            
            
            userLoggedIn = user;
            return true;
         
        } catch(Exception e) {
            return false;
        }
        
    }
    
    public void logOut() {
        userLoggedIn = null;
    }
    
}
