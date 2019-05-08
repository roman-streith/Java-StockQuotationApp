import java.util.Date;
import java.text.DateFormat;
import java.text.SimpleDateFormat;

public class Stock implements java.io.Serializable {
    private static final long serialVersionUID = 4L;    //serialized object id for JVM to tell whether loading object matches class

    private String name;
    private String[] latestHistory = null;
    private String wkn;
    private String symbol;

    public Stock(String name, String symbol, String number) { //construct stock object with stock identifiers
        this.name = name;
        this.wkn = number;
        this.symbol = symbol;
    }

    public void showCurrentHistory() {  //show latest stock entry
        showSelf();     //print stock identifiers
        DateFormat dateFormat = new SimpleDateFormat("yyyy/MM/dd HH:mm:ss");
        Date date = new Date();
        System.out.println("LATEST HISTORY " + dateFormat.format(date));    //show current date

        if (this.latestHistory != null) {
            System.out.format("===================================================================================================\n");
            System.out.format("| %-11s | %-11s | %-11s | %-11s | %-11s | %-11s | %-11s |\n", "Date", "Open", "High", "Low", "Close", "Volume", "ADJ Close" );
            System.out.format("===================================================================================================\n|");
            String[] latestData = this.latestHistory[0].split(",");
            for (int i = 0; i < latestData.length; i++) {
                System.out.format(" %-11s |", latestData[i]);
            }
            System.out.format("\n---------------------------------------------------------------------------------------------------\n");
        } else {
            System.out.println("-----------------------------------------------------------------");
            System.out.println(" - No history found! ");
            System.out.println("-----------------------------------------------------------------");
        }
    }

    public void populateHistory(String[] data) {
        this.latestHistory = data;
    }

    public void showSelf() {    //print stock identifiers
        System.out.println("\n\nName: " + this.name);
        System.out.println("Symbol: " + this.symbol);
        System.out.println("Number: " + this.wkn);
    }

    public void plot() {
        showSelf();     //print stock identifiers
        if (this.latestHistory != null) {
            int nOfHistoryEntries = this.latestHistory.length;
            double[] close = new double[nOfHistoryEntries];
            double lowest = 0;
            double highest = 0;

            for (int i = 0; i < nOfHistoryEntries; i++) {
                String[] line = this.latestHistory[i].split(",");
                close[i] = Double.parseDouble(line[4]);
                if (i == 0) {   //set first value as reference value for plotting boundaries
                    lowest = close[i];
                    highest = close[i];
                }
                if(lowest > close[i]) {     //if smaller than smallest value until now, set as new lowest value
                    lowest = close[i];
                }
                if(highest < close[i]) {    //if bigger than biggest value until now, set as new highest value
                    highest = close[i];
                }
            }
            System.out.format("\n");

            int plotHeight = 15; // y resolution of 15 steps
            int plotWidth = nOfHistoryEntries; // x resolution number of entries => max 30
            double yRange = highest - lowest;   // value range for y
            double ySteps = yRange / (plotHeight - 1);  // calculate value of 1 step with y range and y resolution-1
            double[][] plotArray = new double[plotWidth][plotHeight];   // map of plot

            for (int i = 0; i < close.length; i++) {    // fill plot-map with close values on corresponding height and
                double currentVal = close[i];           // populate lower y-points on given x-point with flags
                currentVal = Math.round((currentVal - lowest) / ySteps);
                plotArray[i][(int) currentVal] = close[i];
                for (int j = (int) currentVal; j >= 0; j--) {
                    plotArray[i][j] = 1;
                }
            }

            double currentLineVal = highest + ySteps; //maximum value shown on y-axis

            System.out.format(" %06.2f|", currentLineVal); //print highest y-axis label
            for (int j = 0; j < plotWidth; j++) {   // fill highest y-value with blanks
                System.out.format("----");
            }
            System.out.format("\n");

            for (int y = plotHeight - 1; y >= 0; y--) {     //plot from map y-axis topdown
                currentLineVal = currentLineVal - ySteps;
                System.out.format(" %06.2f|", currentLineVal);  // y-label
                for (int x = 0; x < plotWidth; x++) {
                    if(plotArray[x][y] <= 0.0) {
                        System.out.format("----");      //blanks if no value or flags
                    } else {
                        System.out.format(" XX ");      //else markers
                    }
                }
                System.out.format("\n");
            }
            System.out.format("       |");

            for (int j = 0; j < plotWidth; j++) {
                System.out.format("|%2s|", j);  //print x-labels
            }

            System.out.format("\n\n--------------------------------------------------------------------------------------------------------------------------------\n");
        } else {
            System.out.println("-----------------------------------------------------------------");
            System.out.println(" - No history found! ");
            System.out.println("-----------------------------------------------------------------");
        }
    }

    public String getName() {
        return this.name;
    }

    public String getSymbol() {
        return this.symbol;
    }
}
