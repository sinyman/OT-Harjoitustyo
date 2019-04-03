package sinyman.stockspal;

import java.net.URL;
import java.util.ResourceBundle;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Label;

public class FXMLController implements Initializable {

    private int timesClicked = 0;

    @FXML
    private Label label;

    @FXML
    private void handleButtonAction(ActionEvent event) {
      Stage stage;
      Parent root;
      stage=(Stage) ((Button)(event.getSource())).getScene().getWindow();
      root = FXMLLoader.load(getClass().getResource("MainMenu.fxml"));
      Scene scene = new Scene(root);
      stage.setScene(scene);
      stage.show();


      //timesClicked++;
      //label.setText("The button has been clicked "+timesClicked+" times!");
    }

    @Override
    public void initialize(URL url, ResourceBundle rb) {
        // TODO
    }
}
