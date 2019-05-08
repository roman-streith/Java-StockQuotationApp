import java.io.*;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;

public class FileManager {

    public static String[] getThirtyDays(String symbol) {   //read values for 30 last days from csv
        String csvFile = System.getProperty("user.dir") + "/csv/" + symbol.toUpperCase() + ".csv";
        String line;
        String[] history = new String[30];
        try {
            BufferedReader br = new BufferedReader(new FileReader(csvFile));
            br.readLine();
            br.mark(214748364); //marking inputstream with char readahead limit
            int currentLine = 0;   //first line description with labels
            while ((line = br.readLine()) != null && currentLine < 30) { //read top down 30 values
                history[currentLine] = line;
                currentLine++;
            }
            try {
                SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
                Date date0 = sdf.parse(history[0].split(",")[0]);
                Date date1 = sdf.parse(history[1].split(",")[0]);
                if (date1.compareTo(date0) > 0) { //check if date of second value is later than date of first value
                    history = new String[30];   //reset array
                    br.reset(); //place reader to the perviously set mark
                    int linenum = 0;
                    while (br.readLine() != null) { // count number of lines/days/values
                        linenum++;
                    }
                    br.reset(); //reset again to top of the file after first line
                    if (linenum > 30) { //if bigger than 30 move to position so that 30 values remain
                        for (int i = 0; i < linenum - 30; i++) {
                            br.readLine();
                        }
                        linenum = 30;
                    }
                    for (int j = linenum - 1; j >= 0; j--) {    //read into array from back to front
                        history[j] = br.readLine();
                    }
                }
            } catch (ParseException p) {
                return trimmArray(history); //trimm array function if less than 30 values are saved
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
        return trimmArray(history); //trimm array function if less than 30 values are saved
    }

    public static void saveHashTable(HashTable stockTable, String fileName) {   //save serialized hashtable using objectstream
        try {
            FileOutputStream fileOut = new FileOutputStream("saves/"+fileName+".ser");
            ObjectOutputStream out = new ObjectOutputStream(fileOut);
            out.writeObject(stockTable);
            out.close();
            fileOut.close();
            System.out.println(fileName + ".ser has been saved.");
        } catch (IOException i) {
            i.printStackTrace();
        }
    }

    public static HashTable loadHashTable(HashTable stockTable, String fileName) {  //load serialized hashtable using objectstream
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
            System.out.println("\nNo such file to load.\n");
        } catch (ClassNotFoundException c) {
            System.out.println("HashTable class not found in this savefile...");
            c.printStackTrace();
            return tmp;
        }
        return stockTable;
    }

    private static String[] trimmArray(String[] arr) { //trimm string-array if less than 30 values are saved
        int count = 0;
        for (String i: arr) {   //count number of non-empty strings
            if (i != null) {
                count++;
            }
        }
        String[] trimmedArr = new String[count];
        int index = 0;
        for (String i: arr) {   //create new return-array with size equal to number of non-empty strings and save strings in given array
            if (i != null) {
                trimmedArr[index++] = i;
            }
        }
        return trimmedArr;
    }
}