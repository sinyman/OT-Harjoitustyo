package sinyman.stockspal;

import java.net.URL;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.io.IOException;

import java.util.Map;
import java.util.Set;
import java.util.ArrayList;

import javafx.util.Pair;

// Google GSON library imports
import com.google.gson.JsonElement;
import com.google.gson.JsonObject;
import com.google.gson.JsonParser;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;

public class StocksDAO {

    // Probably not so smart to have the API-key out in full here,
    // but it will do for the scope of this course
    private String apikey = "AHQBK32GYN1HHUCW";

    //Constructor; This is here to make instantiating an object in the classes
    // possible. That allows for easier-to-read code.
    public StocksDAO() {}
  
    
    /**
     *  A method to be user for creating an ObservableList of stocks data to be
     *  used in adding data to a TableView
     * 
     * @param symbol The stocks symbol/ticker of the stock data wanted
     * 
     * @return Returns an ObservableList of stocksdata
     * @throws MalformedURLException
     * @throws IOException 
     */
    public ObservableList<StocksDataPiece> getStocksDataAsObservableList(String symbol) throws MalformedURLException, IOException {
        ObservableList<StocksDataPiece> data = FXCollections.observableArrayList();

        String json = getJSON(symbol);

        // Making the json-string into a JsonObject and then getting the entries
        // to interate over and extract the necessary data
        JsonObject jsonObject = new JsonParser().parse(json).getAsJsonObject();
        Set<Map.Entry<String, JsonElement>> entrySet = jsonObject.entrySet();

        int counter = 0;
        for (Map.Entry entry: entrySet) {
            if (counter != 0) {
                JsonElement jsElement = (JsonElement) entry.getValue();
                JsonObject jsObject = jsElement.getAsJsonObject();

                // Making an entryset of all the JsonObjects in the "dataobject"
                // to iterate over and get the stocks value data
                Set<Map.Entry<String, JsonElement>> entries = jsObject.entrySet();

                // Iterating through all data entries and adding
                // them to the ObservableList for exporting to TableView
                for (Map.Entry entry2 : entries) {
                    jsElement = (JsonElement) entry2.getValue();
                    jsObject = jsElement.getAsJsonObject();

                    data.add(new StocksDataPiece(entry2.getKey().toString(), jsObject.get("1. open").getAsDouble(), jsObject.get("2. high").getAsDouble(),
                            jsObject.get("3. low").getAsDouble(), jsObject.get("4. close").getAsDouble()));
                }
            }
            counter++;
        }
        return data;
    }

    /**
     *  The main method for getting stocks data from the api to the LineChart
     * 
     * @param symbol Symbol/ticker of the wanted companys stocks
     * @return Returns an ArrayList of pairs with stocks date as key and stocks value as pair value
     * @throws Exception 
     */
    public ArrayList<Pair<String, String>> getStocksData(String symbol) throws Exception {
        String json = getJSON(symbol);

        // Making the json-string into a JsonObject and then getting the entries
        // to interate over and extract the necessary data
        Set<Map.Entry<String, JsonElement>> entrySet = (new JsonParser().parse(json).getAsJsonObject()).entrySet();
        
        ArrayList<Pair<String, String>> stockDataPairs = new ArrayList();
        int counter = 0;
        for (Map.Entry entry: entrySet) {
            if (counter < 11) {
                if (counter == 0) {

                    // Getting the stock api-call metadata
                    // like symbol and last refrehed
                    JsonElement jsElement = (JsonElement) entry.getValue();
                    JsonObject jsObject = jsElement.getAsJsonObject();
                    
                    stockDataPairs.add(new Pair("Metadata", jsObject.get("2. Symbol").getAsString()));
                    stockDataPairs.add(new Pair("Metadata", jsObject.get("3. Last Refreshed").getAsString()));

                } else {
                    JsonObject jsObject = ((JsonElement) entry.getValue()).getAsJsonObject();

                    // Making an entryset of all the JsonObjects in the "dataobject"
                    // to iterate over and get the stocks value data
                    Set<Map.Entry<String, JsonElement>> entries = jsObject.entrySet();

                    // Iterating through all data entries and adding
                    // them to the ArrayList for exporting
                    for (Map.Entry entry2 : entries) {
                        jsObject = ((JsonElement) entry2.getValue()).getAsJsonObject();

                        // Adding all stock data to an ArrayList to return data for later usage
                        stockDataPairs.add(new Pair(entry2.getKey(), jsObject.get("4. close").getAsString()));
                    }
                }
            }

            counter++;
        }

        //System.out.println("ArrayList: "+stockDataPairs.get(1).getKey() + " // " +stockDataPairs.get(1).getValue());
        return stockDataPairs;
    }

    // Method that prepares an URL for the api call
    private String makeURL(String symbol) {
        String apiURL = "https://www.alphavantage.co/query?function=TIME_SERIES_INTRADAY"
                        + "&symbol=" + symbol
                        + "&interval=30min"
                        + "&apikey=" + apikey;

        return apiURL;
    }

    private String getJSON(String symbol) throws MalformedURLException, IOException {
        String url = makeURL(symbol);
        URL urlObject = new URL(url);

        // Opening a connection to the url
        HttpURLConnection connection = (HttpURLConnection) urlObject.openConnection();

        // Initializing a BufferedReader to read the input from the web page
        BufferedReader webInput = new BufferedReader(new InputStreamReader(connection.getInputStream()));

        String inputLine;
        StringBuilder response = new StringBuilder();

        // Going through the lines in the response and adding them to a
        // StringBuffer-object so I can later get the entire response as a String
        while ((inputLine = webInput.readLine()) != null) {
            response.append(inputLine);
        }

        // Closing the BufferedReader "session" now that the reading
        // is done to not take extra processing power
        webInput.close();

        // Returning the JSON data I got as a String
        return response.toString();
    }
}
