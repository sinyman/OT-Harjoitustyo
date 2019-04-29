package sinyman.stockspal;

import javafx.application.Application;
import static javafx.application.Application.launch;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;

import java.awt.Toolkit;


public class MainApp extends Application {

    @Override
    public void start(Stage stage) throws Exception {
        Parent root = FXMLLoader.load(getClass().getResource("/fxml/LoginScene.fxml"));

        Scene scene = new Scene(root/*, x, y*/);
        scene.getStylesheets().add("/styles/Styles.css");

        stage.setTitle("StocksPal - Osakeseurantajärjestelmä");

        // Getting screensize and setting application
        // window size accordingly
        Toolkit tk = Toolkit.getDefaultToolkit();
        int x = (int) tk.getScreenSize().getWidth();
        int y = (int) tk.getScreenSize().getHeight();
        stage.setWidth(x);
        stage.setHeight(y);

        stage.setScene(scene);

        stage.hide();
        stage.show();
    }

    public static void main(String[] args) {
        launch(args);
    }

}
