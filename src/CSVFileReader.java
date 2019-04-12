
import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;

public class CSVFileReader{

    public static String[] getThirtyDays(String symbol){
        String csvFile =  System.getProperty("user.dir") + "/csv/" + symbol.toUpperCase() +".csv";
        String line;
        String[] history = new String[31];
        try (BufferedReader br = new BufferedReader(new FileReader(csvFile))) {

            int currentLine = 0;
            while ((line = br.readLine()) != null && currentLine < 31) {
                history[currentLine] = line;
                currentLine++;
            }

        } catch (IOException e) {
            e.printStackTrace();
        }
        return history;
    }


    public static void printFile(String symbol, HashTable stockTable) {

        String csvFile =  System.getProperty("user.dir") + "/csv/" + symbol.toUpperCase() +".csv";
        String line;
        try (BufferedReader br = new BufferedReader(new FileReader(csvFile))) {
            int currentLine = 0;
            while ((line = br.readLine()) != null && currentLine < 31) {
                // use comma as separator
                String[] items = line.split(",");
                if(currentLine == 1 || currentLine == 0){
                    System.out.println("========================================================================================================");
                } else {
                    System.out.println("--------------------------------------------------------------------------------------------------------");
                }

                if(currentLine < 10){
                    System.out.format("|%2d  |", currentLine);
                } else {
                    System.out.format("| %2d |", currentLine);
                }

                for (int i = 0; i < items.length; i++){
                    System.out.format(" %-11s |", items[i]);
                }
                System.out.format("\n");

                currentLine++;
            }
            System.out.println("--------------------------------------------------------------------------------------------------------");

        } catch (IOException e) {
            e.printStackTrace();
        }

    }

}