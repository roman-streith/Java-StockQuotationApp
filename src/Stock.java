import java.util.Date;
import java.text.DateFormat;
import java.text.SimpleDateFormat;

public class Stock {
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
            String[] latestData = latestHistory[1].split(",");
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
    public String getName(){
        return this.name;
    }
}
