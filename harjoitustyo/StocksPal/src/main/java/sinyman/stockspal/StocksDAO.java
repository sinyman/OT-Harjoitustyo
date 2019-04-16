package sinyman.stockspal;

import java.net.URL;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.io.IOException;

import org.json.JSONObject;

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

    JSONObject time_series = new JSONObject(json.getJSONObject("Time Series (Daily)").toString());
    JSONObject obj = new JSONObject(time_series.getJSONObject("2019-04-16").toString());
    return "Obj: "+obj.getDouble("4. close");
  }

  private String makeURL(String symbol) {
    String apiURL = "https://www.alphavantage.co/query?function=TIME_SERIES_DAILY"
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
    StringBuilder response = new StringBuilder();

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
