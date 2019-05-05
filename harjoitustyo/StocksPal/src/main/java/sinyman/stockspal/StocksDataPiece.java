
package sinyman.stockspal;


/**
 *
 * @author Simon
 */
public class StocksDataPiece {
    private String symbol;
    private int quantity;
    private String date;
    private double openPrice;
    private double highPrice;
    private double lowPrice;
    private double closePrice;
    private double totalPrice;
    
    
    /**
     *  Constructor for making a data piece to make adding data to main TableView
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
    
    /**
     *  Second constructor to be able to use same class for both TableViews in program
     *  That is, Main one and the one used when checking own stocks
     * 
     * @param symbol Symbol/ticker of the stock
     * @param close Buying price of the stock
     * @param quantity Quantity bought
     */
    public StocksDataPiece(String symbol, double close, int quantity) {
        this.symbol = symbol;
        this.closePrice = close;
        this.quantity = quantity;
        this.totalPrice = close * quantity;
    }
    
    public double getTotalPrice() {
        return totalPrice;
    }

    public void setTotalPrice(double totalPrice) {
        this.totalPrice = totalPrice;
    }
    
    public String getSymbol() {
        return symbol;
    }

    public void setSymbol(String symbol) {
        this.symbol = symbol;
    }

    public int getQuantity() {
        return quantity;
    }

    public void setQuantity(int quantity) {
        this.quantity = quantity;
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
