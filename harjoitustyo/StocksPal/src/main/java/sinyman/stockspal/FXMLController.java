package sinyman.stockspal;

import java.net.URL;
import java.util.ResourceBundle;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.TextField;
import javafx.scene.control.PasswordField;
import javafx.scene.layout.AnchorPane;
import javafx.stage.Stage;
import java.io.IOException;


public class FXMLController implements Initializable {

  StocksDAO dao = new StocksDAO();

  @FXML
  TextField unameField;
  PasswordField pwField;
  AnchorPane anchorPane;

  @FXML
  private void handleLogin(ActionEvent event) throws Exception {

    dao.getStocksData3("AAPL");

    Stage stage;
    Parent rootWindow;
    stage=(Stage) ((Button)(event.getSource())).getScene().getWindow();

    rootWindow = FXMLLoader.load(getClass().getResource("/fxml/MainScene.fxml"));
    Scene scene = new Scene(rootWindow);
    stage.setScene(scene);

    stage.hide();
    stage.show();
  }

  @Override
  public void initialize(URL url, ResourceBundle rb) {
    //TODO
  }
}
