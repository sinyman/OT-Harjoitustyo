
package sinyman.stockspal;


/**
 *
 * @author Simon
 */
public class StocksDataPiece {
    private String date;
    private double openPrice;
    private double highPrice;
    private double lowPrice;
    private double closePrice;
    
    
    /**
     *  Constructor for making a data piece to make adding data to TableView
     *  available
     * 
     * @param date The date and time for the stock data
     * @param open The value of the stock at opening
     * @param high The highest value of the stock
     * @param low The lowest value of the stock
     * @param close The closing value of the stock
     */
    public StocksDataPiece(String date, double open, double high, double low, double close) {
        this.date = date;
        this.openPrice = open;
        this.highPrice = high;
        this.lowPrice = low;
        this.closePrice = close;
    }

    public double getOpenPrice() {
        return openPrice;
    }

    public void setOpenPrice(double openPrice) {
        this.openPrice = openPrice;
    }

    public double getHighPrice() {
        return highPrice;
    }

    public void setHighPrice(double highPrice) {
        this.highPrice = highPrice;
    }

    public double getLowPrice() {
        return lowPrice;
    }

    public void setLowPrice(double lowPrice) {
        this.lowPrice = lowPrice;
    }

    public String getDate() {
        return date;
    }

    public void setDate(String date) {
        this.date = date;
    }

    public double getClosePrice() {
        return closePrice;
    }

    public void setClosePrice(double closePrice) {
        this.closePrice = closePrice;
    }
}
