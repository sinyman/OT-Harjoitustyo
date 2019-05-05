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
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.stage.Stage;


public class MyStocksController implements Initializable {
    AuthenticationManager authMan = new AuthenticationManager();
    DatabaseDAO dbDAO = new DatabaseDAO();
    
    @FXML TextField symbolField;
    @FXML TextField priceField;
    @FXML TextField quantityField;

    @FXML TableView tableView;
    @FXML TableColumn symbolColumn;
    @FXML TableColumn quantityColumn;
    @FXML TableColumn buyingPriceColumn;
    @FXML TableColumn totalColumn;
  

    @FXML
    private void handleSceneChange(ActionEvent event) {
        try {
            Stage stage;
            Parent rootWindow;
            stage = (Stage) ((Button) (event.getSource())).getScene().getWindow();

            rootWindow = FXMLLoader.load(getClass().getResource("/fxml/MainScene.fxml"));

            Scene scene = new Scene(rootWindow);
            stage.setScene(scene);

            stage.hide();
            stage.show();
            
        } catch (IOException ioe) {
            ioe.printStackTrace();
        }
    }
    
    @FXML
    private void handleStocksSaving(ActionEvent event) {
        if (symbolField.getText().length() > 0 && quantityField.getText().length() > 0 && priceField.getText().length() > 0) {
            dbDAO.addUserStock(authMan.userLoggedIn, symbolField.getText(), Integer.parseInt(quantityField.getText()), Double.parseDouble(priceField.getText()));
        }
        
        
        // Updating table after insertion
        updateStocksTable();
    }
    
    @FXML
    private void updateStocksTable() {
        /**
         * A method for keeping the table up to date
         * 
         */
        
        /*
        // Using a try catch-clause because
        // program will crash if table is empty
        try {
            if (!tableView.getItems().isEmpty()) {
                // Clearing the table of old data
                tableView.getItems().clear();
            }
            
            // Getting new data and adding the data to the table
            tableView.setItems(dbDAO.getUserStockAsObservableList(authMan.userLoggedIn));
            
        } catch (NullPointerException npe) {
            npe.printStackTrace();
            return;
        }
        */
        
        // Getting new data and adding the data to the table
        tableView.setItems(dbDAO.getUserStockAsObservableList(authMan.userLoggedIn));
    }
    
    @FXML
    private void setupTableView() {
        // Symbol column data
        symbolColumn.setCellValueFactory(new PropertyValueFactory<>("symbol"));
        
        // Quantity column data
        quantityColumn.setCellValueFactory(new PropertyValueFactory<>("quantity"));
        
        // Buying price column data
        buyingPriceColumn.setCellValueFactory(new PropertyValueFactory<>("buyingPrice"));
        
        // Total price column data
        totalColumn.setCellValueFactory(new PropertyValueFactory<>("totalPrice"));
    }

    @Override
    public void initialize(URL url, ResourceBundle rb) {
        setupTableView();
        updateStocksTable();
    }
}
