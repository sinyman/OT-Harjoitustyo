package sinyman.stockspal;

import javafx.application.Application;
import static javafx.application.Application.launch;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;


public class MainApp extends Application {

    @Override
    public void start(Stage stage) throws Exception {
        Parent root = FXMLLoader.load(getClass().getResource("/fxml/LoginScene.fxml"));

        Scene scene = new Scene(root);
        scene.getStylesheets().add("/styles/Styles.css");

        stage.setTitle("StocksPal - Osakeseurantajärjestelmä");
        stage.setScene(scene);

        // Setting max window size to fit backround image
        stage.setMaxWidth(1920);
        stage.setMaxHeight(1080);
        stage.show();
    }

    public static void main(String[] args) {
        launch(args);
    }

}
