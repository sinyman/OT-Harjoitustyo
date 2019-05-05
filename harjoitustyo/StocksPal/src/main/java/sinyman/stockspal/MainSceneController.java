package sinyman.stockspal;

import java.io.IOException;
import java.net.URL;
import java.util.ArrayList;
import java.util.ResourceBundle;
import java.util.logging.Level;
import java.util.logging.Logger;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.chart.LineChart;
import javafx.scene.chart.NumberAxis;
import javafx.scene.chart.XYChart;
import javafx.scene.control.Button;
import javafx.scene.control.MenuBar;
import javafx.scene.control.MenuItem;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.input.KeyCode;
import javafx.scene.input.KeyEvent;
import javafx.stage.Stage;
import javafx.util.Pair;


public class MainSceneController implements Initializable {

    StocksDAO dao = new StocksDAO();
    AuthenticationManager authMan = new AuthenticationManager();

    @FXML private LineChart<String, Number> dataChart; 
    @FXML private NumberAxis yAxis;
    
    @FXML private TableView tableView;
    @FXML private TableColumn dateColumn;
    @FXML private TableColumn openColumn;
    @FXML private TableColumn highColumn;
    @FXML private TableColumn lowColumn;
    @FXML private TableColumn closeColumn;
    
    @FXML private MenuBar menuBar;
    @FXML private MenuItem closeButton;
    
    @FXML private TextField symbolSearch;
    
    @FXML private Button myStocksButton;
    
    @FXML
    private void applyStocksDataFromSearch(KeyEvent event) throws Exception {
        if (event.getCode() == KeyCode.ENTER) {
            String symbol = symbolSearch.getText();
        
            // Clearing the table of old data
            tableView.getItems().clear();

            // Getting new data and adding the data to the table
            try {
                tableView.setItems(dao.getStocksDataAsObservableList(symbol));
            } catch (IOException ex) {
                Logger.getLogger(MainSceneController.class.getName()).log(Level.SEVERE, null, ex);
            }

            updateLineChart(symbol);
        }

    }
    
    @FXML
    private void handleViewChange(ActionEvent event) throws IOException {
        Stage stage;
        Parent rootWindow;
        stage = (Stage) ((Button) (event.getSource())).getScene().getWindow();

        rootWindow = FXMLLoader.load(getClass().getResource("/fxml/MyStocksScene.fxml"));

        Scene scene = new Scene(rootWindow);
        stage.setScene(scene);

        stage.hide();
        stage.show();
    }
    
    @FXML
    private void handleButtonAction(ActionEvent event) throws IOException {  
        // Closes application    
        if (event.getSource() == closeButton) {
            System.exit(0);   
        }
    }
    
    private void updateLineChart(String symbol) throws Exception {
        ArrayList<Pair<String, String>> stocksData = new ArrayList();
        
        stocksData = dao.getStocksData(symbol);
        
        dataChart.getData().clear();
        dataChart.setTitle("Stocks Data - " + stocksData.get(0).getValue());

        // Setting y axis ranges arbitrarily based on latest stock value to make easier to read
        yAxis.setAutoRanging(false);
        yAxis.setLowerBound(Double.parseDouble(stocksData.get(stocksData.size() - 1).getValue()) - 100);
        yAxis.setUpperBound(Double.parseDouble(stocksData.get(stocksData.size() - 1).getValue()) + 100);
        yAxis.setTickUnit(0.01);
        
        XYChart.Series<String, Number> dataSeries = new XYChart.Series<>();
        
        // Iterating through the stock values
        // Starting from the end because the data is "backwards" and not very intuituve
        for (int i = stocksData.size() - 1; i > 1; i--) {
            double price = Double.parseDouble(stocksData.get(i).getValue());
            dataSeries.getData().add(new XYChart.Data<String, Number>((String) stocksData.get(i).getKey(), price));
        }
        
        dataSeries.setName("Stock Price");
        dataChart.getData().add(dataSeries);
    }
    
    private void prepareLineChart(String symbol) {        

        ArrayList<Pair<String, String>> stocksData = new ArrayList();
        try {
            stocksData = dao.getStocksData(symbol);
        } catch (Exception ex) {
            Logger.getLogger(MainSceneController.class.getName()).log(Level.SEVERE, null, ex);
        }
        dataChart.getData().clear();
        dataChart.setTitle("Stocks Data - " + stocksData.get(0).getValue());
        
        // Setting the y axis increments to make data easier to read
        yAxis.setAutoRanging(false);
        
        // Setting y axis ranges arbitrarily based on latest stock value
        yAxis.setLowerBound(Double.parseDouble(stocksData.get(stocksData.size() - 1).getValue()) - 20);
        yAxis.setUpperBound(Double.parseDouble(stocksData.get(stocksData.size() - 1).getValue()) + 20);
        yAxis.setTickUnit(0.01);

        XYChart.Series<String, Number> dataSeries = new XYChart.Series<>();
        
        // Iterating through the stock values
        // Starting from the end because the data is "backwards" and needs
        // to be added this way for a more intuitive line chart
        for (int i = stocksData.size() - 1; i > 1; i--) {
            double price = Double.parseDouble(stocksData.get(i).getValue());
            dataSeries.getData().add(new XYChart.Data<String, Number>((String) stocksData.get(i).getKey(), price));
        }

        dataChart.getData().add(dataSeries);
    }
    
    private void updateTableView(String symbol) {
        // Clearing the table of old data
        tableView.getItems().clear();
        
        // Getting new data and adding the data to the table
        try {
            tableView.setItems(dao.getStocksDataAsObservableList(symbol));
        } catch (IOException ex) {
            Logger.getLogger(MainSceneController.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
    
    private void prepareTableView() {
        // Date column data
        dateColumn.setCellValueFactory(new PropertyValueFactory<>("date"));
        
        // Open column data
        openColumn.setCellValueFactory(new PropertyValueFactory<>("openPrice"));
        
        // High column data
        highColumn.setCellValueFactory(new PropertyValueFactory<>("highPrice"));
        
        // Low column data
        lowColumn.setCellValueFactory(new PropertyValueFactory<>("lowPrice"));
        
        // Close column data
        closeColumn.setCellValueFactory(new PropertyValueFactory<>("closePrice"));
    }

    @Override
    public void initialize(URL url, ResourceBundle rb) {
        prepareTableView();

    }
}