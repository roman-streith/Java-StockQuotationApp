import java.util.Scanner;
import java.io.Serializable;

public class HashTable implements java.io.Serializable {
    private Stock[] stockTable;
    private SymbolHashTable referenceTable;

    public HashTable(int size) {    //construct hash-table (name hashed) with reference to symbol-table
        this.stockTable = new Stock[size];
        this.referenceTable = new SymbolHashTable(size);
    }

    public void checkTable(String userInput, String action) {   //

        int index = searchStock(userInput);
        if (index < 0 ) {
            index = referenceTable.searchReference(userInput, "GET_STOCK_INDEX");
        }

        if (index >= 0){

            switch (action){
                case "SEARCH":
                    showHistory(index);
                    break;
                case "DELETE":
                    deleteStock(index);
                    break;
                case "IMPORT":
                    importData(index, userInput);
                    break;
                case "PLOT":
                    plotData(index);
                    break;
                default:
                    System.out.println("Unknown flag in \"checkTable()\"");
                    break;
            }

        } else {
            System.out.println("No such stock in table!");
        }
    }

    public void insertStock(String name, String symbol, String number) {
        int hashIndex = hashFunction(name); //hash name of stock
        System.out.println("Initial hashIndex of " + name + ", is at Index: " + hashIndex);
        int count = 1;
        int collisionIndex = hashIndex;
        while (stockTable[hashIndex] != null) {     //square probing on collision until empty space or object with equal name is found
            if (stockTable[hashIndex].getName().equals(name)) {
                System.out.println("This stock already exists!");
                return;
            }
            hashIndex = collisionHandling(collisionIndex, count);
            count++;
        }
        SymbolReference reference = new SymbolReference(symbol, hashIndex);
        boolean referenceAlreadyInTable = referenceTable.insertReference(reference);    //try to insert reference to name-table stock-index in symbol-table with hashed symbol index
        if (!referenceAlreadyInTable) {     //on successful symbol mapping
            stockTable[hashIndex] = new Stock(name, symbol, number);    //create stockobject and insert into name-table
            System.out.println("The stock \"" + stockTable[hashIndex].getName() + "\" has been inserted at index[" + hashIndex + "].");
        } else {
            System.out.println("Stock with this ID-Number already exists!");
        }
    }

    private void deleteStock(int index) {
        Stock stock = stockTable[index];    // delete function must be preceeded by search function
        stock.showSelf();   //print stock identifiers
        System.out.format("Remove this stock? [Y]es/[N]o: ");   // get additional permission from user to delete the stock from table
        Scanner reader = new Scanner(System.in);
        String decision = reader.nextLine();
        if (decision.equals("Y")) {
            referenceTable.deleteReference(stock.getSymbol());
            stockTable[index] = new Stock(null, null, null);    //fill deleted stock-index with empty stock-dummy to guarantee correct searching of collision-objects
            System.out.println("The stock has been removed!");
        } else {
            System.out.println("The stock has NOT been removed!");
        }
    }

    private void showHistory(int index) {   //show latest stock entry of stock on given index
        Stock stock = stockTable[index];
        stock.showCurrentHistory();
    }

    private void importData(int index, String userInput) {  //import stock data from csv for stock on given index
        String[] data = FileManager.getThirtyDays(userInput);   //FM-class method for data import
        stockTable[index].populateHistory(data);    //save imported data in stockobject as stringarray
        System.out.println("History has been added to \"" + stockTable[index].getName() + "\" at index [" + index + "]!");
    }

    private void plotData(int index) {  //plot stock on given index
        stockTable[index].plot();   //Stock-class plotting method
    }

    private int searchStock(String userInput) {
        int hashIndex = hashFunction(userInput);
        int count = 1;
        int collisionIndex = hashIndex;
        while (true) {
            if (stockTable[hashIndex] == null) {    //empty field indicates that data is not in table, set index to an non-table value (-1)
                hashIndex = -1;
                break;
            } else if (stockTable[hashIndex].getName().equals(userInput)) { //break on match with item in table
                break;
            }
            hashIndex = collisionHandling(collisionIndex, count);   //else manipulate index with collision-handling method (square probing)
            count++;
        }
        return hashIndex;
    }

    private int hashFunction(String StockName) {    //hash function
        int index = 0;
        for (int i = 0; i < StockName.length(); i++) {
            //System.out.println("StockName[" + i + "]: " + StockName.charAt(i) + ", hashVal: " + StockName.charAt(i) * Math.pow(53, i));
            index += (StockName.charAt(i) * Math.pow(53, i)) % 2003; //sum of int-representation of a character times 53 to the power of character-position
            //index %= 2003;
        }
        return index % 2003;    // mod table-size to stay in bounds
    }

    private int collisionHandling(int collisionindex, int index) {  //on collision calculate new index with square probing method
        double retVal = (collisionindex + Math.pow((-1), index + 1) * (Math.ceil((index / 2.0)) * Math.ceil((index / 2.0)))) % 2003;
        return (int) retVal;
    }
}
