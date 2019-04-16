package sinyman.stockspal;

import java.net.URL;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.io.IOException;

import java.lang.StringBuffer;

import org.json.JSONObject;
import org.json.JSONArray;

public class StocksDAO {

  // Probably not so smart to have the API-key out in full here,
  // but it will do for the scope of this course
  private String apikey = "AHQBK32GYN1HHUCW";

  //Constructor
  public StocksDAO() {

  }


  // Main method for getting stocks data from API
  public String getStocksData(String symbol) throws MalformedURLException, IOException {
    return getJSON(symbol).toString();
  }

  // Tester method
  public String getStocksData2(String symbol) throws MalformedURLException, IOException {
    JSONObject json = getJSON(symbol);

    JSONArray array = json.getJSONArray("Time Series (30min)");

    String closeValues = "";
    for(int i = 0; i < array.length(); i++) {
      closeValues += array.getJSONObject(i).getString("4. close") + ", ";
    }
    //JSONObject time_series = new JSONObject(json.getJSONObject("Time Series").toString());
    //return "Metadata: "+time_series.getString("2. Symbol");
    return closeValues;
  }

  private String makeURL(String symbol) {
    String apiURL = "https://www.alphavantage.co/query?function=TIME_SERIES_INTRADAY"
                      + "&symbol="+ symbol
                      + "&interval=30min"
                      + "&apikey="+apikey;

    return apiURL;
  }

  private JSONObject getJSON(String symbol) throws MalformedURLException, IOException {
    String url = makeURL(symbol);
    URL urlObject = new URL(url);

    // Opening a connection to the url
    HttpURLConnection connection = (HttpURLConnection) urlObject.openConnection();

    // Initializing a BufferedReader to read the input from the web page
    BufferedReader webInput = new BufferedReader(new InputStreamReader(connection.getInputStream()));

    String inputLine;
    StringBuffer response = new StringBuffer();

    // Going through the lines in the response and adding it to a
    // StringBuffer-object so I can later get the entire response as a String
    while((inputLine = webInput.readLine()) != null) {
      response.append(inputLine);
    }

    webInput.close();

    // Returning the JSON data I got as a JSONObject
    return new JSONObject(response.toString());
  }
}
