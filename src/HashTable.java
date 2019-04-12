import java.util.Scanner;

public class HashTable {
    private Stock[] stockTable;
        // constructor method
    HashTable(int size){
        this.stockTable = new Stock[size];
    }

    public void checkTable(String userInput, String flag){

        int index = searchStock(userInput);

        if (index >= 0){

            switch (flag){
                case "SEARCH":
                    showHistory(index);
                    break;
                case "DELETE":
                    deleteStock(index);
                    break;
                case "IMPORT":
                    importData(index, userInput);
                    break;
                default:
                    System.out.println("Unknown flag in \"checkTable()\"");
                    break;
            }

        } else {
            System.out.println("No such stock in table!");
        }
    }

    public void insertStock(Stock stock){
        // bad because we always init a new stock before we know if it is even getting used
        int index = hashFunction(stock.getName());

        int val = 1;
        boolean duplicat = false;
        while(stockTable[index] != null) {
            if (stockTable[index].getName().equals(stock.getName())) {
                System.out.println("The stock " + stock.getName() + " already exists!");
                duplicat = true;
                break;
            } else if (stockTable[index] == null) {
                break;
            }
            index += ((val + 1) * (val + 1));
            val++;
        }

        if(!duplicat){
            stockTable[index] = stock;
            System.out.println("The stock \"" + stock.getName() + "\" has been inserted at index[" + index + "].");
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
        String[] data = CSVFileReader.getThirtyDays(userInput);
        stockTable[index].populateHistory(data);
        System.out.println("History has been added to \"" +stockTable[index].getName()+ "\" at index [" +index+ "]!");
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
