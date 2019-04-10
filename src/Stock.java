import java.util.Date;
import java.text.DateFormat;
import java.text.SimpleDateFormat;

public class Stock {
    // constructor method
    public Stock(String name, String symbol, String number){ // , String wkn, String token
        this.name = name;
        this.wkn = number; // Wertpapierkennnummer
        this.symbol = symbol; // Kürzel
    }
    private String name;
    private String[] latestHistory = null;
    private String wkn;
    private String symbol;
    /*private String date;
    private String open; // Wert bei Börsenöffnung
    private String high;
    private String low;
    private String close; // Wert bei Börsenschluss
    private String volume;
    private String AdjClose; // Berichtigter Kurs
    private Record stockRecord;
    */
    public void showCurrentInfo(){
        DateFormat dateFormat = new SimpleDateFormat("yyyy/MM/dd HH:mm:ss");
        Date date = new Date();
        System.out.println("LATEST HISTORY " + dateFormat.format(date)); //2016/11/16 12:08:43

        if( latestHistory != null){
            System.out.format("===================================================================================================\n");
            System.out.format("| %-11s | %-11s | %-11s | %-11s | %-11s | %-11s | %-11s |\n", "Date", "Open", "High", "Low", "Close", "Volume", "ADJ Close" );
            System.out.format("===================================================================================================\n|");
            for (int i = 0; i < latestHistory.length; i++){
                System.out.format(" %-11s |", latestHistory[i]);
            }
            System.out.format("\n-----------------------------------------------------------------");
        } else {
            System.out.format("\n-----------------------------------------------------------------");
            System.out.println(" - No history found! ");
        }
    }

    public void populateLatestHistory(String[] latestStats){
        this.latestHistory = latestStats;
    }

    public void showSelf(){
        System.out.println("Name: " + this.name);
        System.out.println("Symbol: " + this.symbol);
        System.out.println("Number: " + this.wkn);
    }
    public String getName(){
        return this.name;
    }
}
