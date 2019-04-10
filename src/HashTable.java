
public class HashTable {
    private Stock[] stockTable;
        // constructor method
    HashTable(int size){
        this.stockTable = new Stock[size];
    }

    public void insertStock(Stock stock){
        // convert String to Number in Ascii added up !!! NOT THE FINAL SOLUTION
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
            System.out.println(stock.getName() + " has been inserted at index[" + index + "]");
        }
    }

    public void deleteStock(String userInput){
        // find in Table via Hash and set cell on index to null
        int index = searchStock(userInput);
        if(index != -1){
            Stock stock = stockTable[index];
            stock.showSelf();
            stockTable[index] = null;
        } else {
            System.out.println("No suc stock in table - deleteStock()");
        }
        // delete 30-day history
    }

    public void showHistory(String userInput){
        int index = searchStock(userInput);
        if (index < 0) {
            return;
        }
        Stock stock = stockTable[index];
        stock.showSelf();
        stock.showCurrentInfo();
    }

    public int  searchStock(String userInput){
        int index = hashFunction(userInput);
        int val = 1;
        while(true){
            if(stockTable[index] == null){
                System.out.println("No such Stock in table - searchStock()");
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

    public void addHistoryToStock(String name, String[] history){
        int index = searchStock(name);
        stockTable[index].populateLatestHistory(history);
    }

    private int hashFunction(String StockName){
        int index = 0;
        for (int i = 0; i < StockName.length(); i++){
            index += StockName.charAt(i) * Math.pow(53, i);
        }
        return index%2003;


        /*
        if(stockTable[index] == null){
            return index;
        } else if (stockTable[index].getName().equals(StockName)){
            System.out.println("The stock "+ StockName + " already exists!");
            return -1;
        } else {
            System.out.println("Collision");
            return hashFunction(StockName, (value + 1) * (value + 1));
            // collision handling here
        }
        */
    }

}
