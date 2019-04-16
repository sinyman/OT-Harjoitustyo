package sinyman.stockspal;

import java.net.URL;
import java.util.ResourceBundle;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.control.Label;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.stage.Stage;
import java.io.IOException;
import javafx.scene.control.Label;

public class FXMLController implements Initializable {

  StocksDAO dao = new StocksDAO();

  @FXML
  Label testLabel;


  @FXML
  private void handleButtonAction(ActionEvent event)  throws IOException {
    //System.out.println(dao.getStocksData("AAPL"));
    System.out.println(dao.getStocksData2("MSFT"));
    //testLabel.setText(dao.getStocksData2("MSFT"));

    Stage stage;
    Parent rootWindow;
    stage=(Stage) ((Button)(event.getSource())).getScene().getWindow();
    //rootWindow = FXMLLoader.load(getClass().getResource("/fxml/LoginScene.fxml"));
    //rootWindow = FXMLLoader.load(getClass().getResource("/fxml/MainScene.fxml"));
    rootWindow = FXMLLoader.load(getClass().getResource("/fxml/testDAO.fxml"));
    Scene scene = new Scene(rootWindow);
    stage.setScene(scene);
    stage.show();

  }

  @Override
  public void initialize(URL url, ResourceBundle rb) {
      // TODO
  }
}
