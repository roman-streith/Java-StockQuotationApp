
import java.io.*;

public class FileManager{

    public static String[] getThirtyDays(String symbol){
        String csvFile =  System.getProperty("user.dir") + "/csv/" + symbol.toUpperCase() +".csv";
        String line;
        String[] history = new String[30];
        try (BufferedReader br = new BufferedReader(new FileReader(csvFile))) {

            int currentLine = -1;
            while ((line = br.readLine()) != null && currentLine < 30) {
                if (currentLine != -1) {
                    history[currentLine] = line;
                }
                currentLine++;
            }

        } catch (IOException e) {
            e.printStackTrace();
        }

        return trimmArray(history);
    }

    public static void saveHashTable(HashTable stockTable, String fileName){

        try {
            FileOutputStream fileOut = new FileOutputStream("saves/"+fileName+".ser");
            ObjectOutputStream out = new ObjectOutputStream(fileOut);
            out.writeObject(stockTable);
            out.close();
            fileOut.close();
            System.out.printf( fileName + ".ser has been saved.\n");
        } catch (IOException i) {
            i.printStackTrace();
        }
    }

    public static HashTable loadHashTable(HashTable stockTable, String fileName){
        HashTable tmp = stockTable;
        try {
            FileInputStream fileIn = new FileInputStream("saves/"+fileName+".ser");
            ObjectInputStream in = new ObjectInputStream(fileIn);
            stockTable = (HashTable) in.readObject();
            in.close();
            fileIn.close();
            System.out.println(fileName + ".ser has been loaded.\n");
        } catch (IOException i) {
            i.printStackTrace();
        } catch (ClassNotFoundException c) {
            System.out.println("HashTable class not found in this savefile...");
            c.printStackTrace();
            return tmp;
        }
        return stockTable;
    }

    // maybe create a "Helper" class for functions like this
    private static String[] trimmArray(String[] arr){
        int count = 0;
        for (String i : arr) {
            if (i != null) {
                count++;
            }
        }
        String[] trimmedArr = new String[count];
        int index = 0;
        for (String i : arr) {
            if (i != null) {
                trimmedArr[index++] = i;
            }
        }
        return trimmedArr;
    }

    /*public static void printCSV(String symbol, HashTable stockTable) {

        String csvFile =  System.getProperty("user.dir") + "/csv/" + symbol.toUpperCase() +".csv";
        String line;
        try (BufferedReader br = new BufferedReader(new FileReader(csvFile))) {
            int currentLine = 0;
            while ((line = br.readLine()) != null && currentLine < 31) {
                // use comma as separator
                String[] items = line.split(",");
                if (currentLine == 1 || currentLine == 0){
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

    }*/

}