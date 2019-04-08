
public class HashTable {
    private Stock[] stockTable;
        // constructor method
    HashTable(int size){
        this.stockTable = new Stock[size];
    }

    public void insertStock(Stock stock){
        // convert String to Number in Ascii added up !!! NOT THE FINAL SOLUTION
        int index = hashFunction(stock.getName());
        if(stockTable[index] == null){
            stockTable[index] = stock;
        } else if (stockTable[index].getName().equals(stock.getName())){
            System.out.println("The stock "+ stock.getName() + " already exists!");
        } else {
            System.out.println("Collision!!");
            // collision handling here
        }

    }

    public void deleteStock(String userInput){
        // find in Table via Hash and set cell on index to null
        int index = hashFunction(userInput);
        Stock stock = stockTable[index];
        stock.showSelf();
        stockTable[index] = null;
        // delete 30-day history
    }

    public int  searchStock(String userInput){
        int index = hashFunction(userInput);
        Stock stock = stockTable[index];
        System.out.println("----------------------------------");
        if(stock != null) {
            stock.showSelf();
            stock.showCurrentInfo();
        } else {
            System.out.println("No such Stock in table!");
        }
        System.out.println("----------------------------------");
        return index;
    }

    public void addHistoryToStock(String name, String[] history){
        int index = hashFunction(name);
        stockTable[index].populateLatestHistory(history);
    }

    private int hashFunction(String StockName){
        int index = 0;
        for (int i = 0; i < StockName.length(); i++){
            index += StockName.charAt(i);
        }
        return index;
    }

}
