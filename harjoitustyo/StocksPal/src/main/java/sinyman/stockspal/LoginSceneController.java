package sinyman.stockspal;

import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.control.PasswordField;
import javafx.stage.Stage;


public class LoginSceneController implements Initializable {

    AuthenticationManager authMan = new AuthenticationManager();

    @FXML TextField unameField;
    @FXML PasswordField pwField;
    @FXML Label errorLabel;

  
    @FXML
  private void handleLogin(ActionEvent event) throws IOException {
        boolean loginSuccessful = authMan.logIn(unameField.getText(), pwField.getText());

        // If login was successful, load the main scene and show it
        if (loginSuccessful) {
            Stage stage;
            Parent rootWindow;
            stage = (Stage) ((Button) (event.getSource())).getScene().getWindow();

            rootWindow = FXMLLoader.load(getClass().getResource("/fxml/MainScene.fxml"));

            Scene scene = new Scene(rootWindow);
            stage.setScene(scene);

            stage.hide();
            stage.show();

        } else {
            errorLabel.setText("Login unsuccessful! Check username and password!");
        }
    }
  
    @FXML
  private void handleRegistration(ActionEvent event) {
        String name = unameField.getText();
        String pw = pwField.getText();

        // Checking to see that fields aren't empty
        if (name.length() > 0 && pw.length() > 0) {
            if (authMan.registerUser(name, pw)) {
                errorLabel.setText("Registration was successful!");
            }
        } else {
            errorLabel.setText("Registration was unsuccessful, check your username and password!");
        }
    }

    @Override
  public void initialize(URL url, ResourceBundle rb) {
        //TODO
    }
}
