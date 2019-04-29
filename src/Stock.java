import java.util.Arrays;
import java.util.Date;
import java.text.DateFormat;
import java.text.SimpleDateFormat;

import static java.lang.Integer.parseInt;

public class Stock implements java.io.Serializable{
    // constructor method
    Stock(String name, String symbol, String number){ // , String wkn, String token
        this.name = name;
        this.wkn = number; // Wertpapierkennnummer
        this.symbol = symbol; // Kürzel
    }
    private String name;
    private String[] latestHistory = null;
    private String wkn;
    private String symbol;

    private void showCurrentHistory(){
        DateFormat dateFormat = new SimpleDateFormat("yyyy/MM/dd HH:mm:ss");
        Date date = new Date();
        System.out.println("LATEST HISTORY " + dateFormat.format(date)); //2016/11/16 12:08:43

        if( latestHistory != null){
            System.out.format("===================================================================================================\n");
            System.out.format("| %-11s | %-11s | %-11s | %-11s | %-11s | %-11s | %-11s |\n", "Date", "Open", "High", "Low", "Close", "Volume", "ADJ Close" );
            System.out.format("===================================================================================================\n|");
            String[] latestData = latestHistory[0].split(",");
            for (int i = 0; i < latestData.length; i++){
                System.out.format(" %-11s |", latestData[i]);
            }
            System.out.format("\n---------------------------------------------------------------------------------------------------\n");
        } else {
            System.out.println("-----------------------------------------------------------------");
            System.out.println(" - No history found! ");
            System.out.println("-----------------------------------------------------------------");
        }
    }

    public void populateHistory(String[] data){
        this.latestHistory = data;
    }

    public void showSelf(){
        System.out.println("Name: " + this.name);
        System.out.println("Symbol: " + this.symbol);
        System.out.println("Number: " + this.wkn);
        showCurrentHistory();
    }

    public void plot(){
        if( latestHistory != null){
            int nOfHistoryEntries = latestHistory.length;
            double[] close = new double[nOfHistoryEntries];
            double lowest = 0;
            double highest = 0;

            for (int i = 0; i < nOfHistoryEntries; i++){
                String[] line = latestHistory[i].split(",");
                close[i] = Double.parseDouble(line[4]);
                if (i == 0){
                    lowest = close[i];
                    highest = close[i];
                }
                if(lowest > close[i]){
                    lowest = close[i];
                }
                if(highest < close[i]){
                    highest = close[i];
                }
                //System.out.format(",[%s] %-5s ", i, close[i]);
            }
            System.out.format("\n");

            int plotHeight = 15;
            int plotWidth = nOfHistoryEntries;
            double yRange = highest - lowest;
            double ySteps = yRange / (plotHeight - 1);
            double[][] plotArray = new double[plotWidth][plotHeight];

            for (int i = 0; i < close.length; i++){
                double currentVal = close[i];
                currentVal = Math.round((currentVal - lowest) / ySteps);
                //System.out.format("[%s] %-5s |\n", i, (int)currentVal);
                if ((int)currentVal == plotHeight){
                    currentVal --;
                }
                plotArray[i][(int)currentVal] = close[i];
                /*for (int j = (int)currentVal; j >= 0 ; j--){
                    plotArray[i][j] = 1;
                }*/
            }

            double currentLineVal = highest+ ySteps;

            System.out.format(" %.2f|", currentLineVal);
            for(int j = 0; j < plotWidth;  j++){
                System.out.format("----");
            }
            System.out.format("\n");

            for (int y = plotHeight - 1; y >= 0; y--){
                currentLineVal = currentLineVal- ySteps;
                System.out.format(" %.2f|", currentLineVal);
                for (int x = 0; x < plotWidth; x++){
                    if(plotArray[x][y] <= 0.0){
                        System.out.format("----");
                    }else {
                        System.out.format(" XX ");
                    }
                }
                System.out.format("\n");
            }
            System.out.format("      |");

            for(int j = 0; j < plotWidth;  j++){
                System.out.format("|%-2s|", j);
            }
            System.out.format("\n\n--------------------------------------------------------------------------------------------------------------------------------\n");
        } else {
            System.out.println("-----------------------------------------------------------------");
            System.out.println(" - No history found! ");
            System.out.println("-----------------------------------------------------------------");
        }
    }

    public String getName(){
        return this.name;
    }
    public String getSymbol(){
        return this.symbol;
    }
}
