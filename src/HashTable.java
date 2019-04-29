import java.util.Scanner;
import java.io.Serializable;

public class HashTable implements java.io.Serializable {
    private Stock[] stockTable;
    private SymbolHashTable referenceTable;
        // constructor method
    HashTable(int size){
        this.stockTable = new Stock[size];
        this.referenceTable = new SymbolHashTable(size);
    }

    public void checkTable(String userInput, String action){

        int index = searchStock(userInput);
        if(index < 0 ){
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

    public void insertStock(String name, String symbol, String number){
        int index = hashFunction(name);

        int val = 1;
        boolean duplicat = false;
        while(stockTable[index] != null) {
            if (stockTable[index].getName().equals(name)) {
                System.out.println("The stock " + name + " already exists!");
                duplicat = true;
                break;
            } else if (stockTable[index] == null) {
                break;
            }
            index += ((val + 1) * (val + 1));
            val++;
        }

        if(!duplicat){
            stockTable[index] = new Stock(name, symbol, number);
            SymbolReference reference = new SymbolReference(stockTable[index].getSymbol(), index);
            referenceTable.insertReference(reference);
            System.out.println("The stock \"" + stockTable[index].getName() + "\" has been inserted at index[" + index + "].");
        }
    }

    private void deleteStock(int index){
        // find in Table via Hash and set cell on index to null
        Stock stock = stockTable[index];
        stock.showSelf();
        // get additional permission from user to delete the stock from table
        System.out.format("Remove this stock? [Y]es/[N]o: ");
        Scanner reader = new Scanner(System.in);
        String decision = reader.nextLine();
        if(decision.equals("Y")){
            referenceTable.deleteReference(stock.getSymbol());
            stockTable[index] = null;
            System.out.println("The stock has been removed!");
        } else {
            System.out.println("The stock has NOT been removed!");
        }
    }

    private void showHistory(int index){
        Stock stock = stockTable[index];
        stock.showSelf();
    }

    private void importData(int index, String userInput){
        String[] data = FileManager.getThirtyDays(userInput);
        stockTable[index].populateHistory(data);
        System.out.println("History has been added to \"" +stockTable[index].getName()+ "\" at index [" +index+ "]!");
    }

    private void plotData(int index){
        stockTable[index].plot();
    }

    private int searchStock(String userInput){
        int index = hashFunction(userInput);
        int val = 1;
        while(true){
            if(stockTable[index] == null){
                index = -1;
                break;
            } else if(stockTable[index].getName().equals(userInput)) {
                break;
            }
            index += ((val + 1) * (val + 1)) % 2003;
            val++;
        }
        return index % 2003;
    }

    private int hashFunction(String StockName){
        int index = 0;
        for (int i = 0; i < StockName.length(); i++){
            index += StockName.charAt(i) * Math.pow(53, i);
        }
        return index%2003;
    }

}
