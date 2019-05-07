public class SymbolHashTable implements java.io.Serializable {
    private static final long serialVersionUID = 3L;    //serialized object id for JVM to tell whether loading object matches class

    private SymbolReference[] referenceTable;

    public  SymbolHashTable(int size) {     //construct symbol-table with index-mapping objects to name-table
        this.referenceTable = new SymbolReference[size];
    }

    public boolean insertReference(SymbolReference reference) {
        int hashIndex = hashFunction(reference.getSymbol()); //hash stock-symbol
        int count = 1;
        int collisionindex = hashIndex;
        while (referenceTable[hashIndex] != null) { //square probing on collision until empty space or object with equal name is found
            if (referenceTable[hashIndex].getSymbol().equals(reference.getSymbol())) {
                System.out.println("The reference/symbol " + reference.getSymbol() + " already exists!");
                return true;
            }
            hashIndex = collisionHandling(collisionindex, count);
            count++;
        }
        referenceTable[hashIndex] = reference; //insert reference-object(symbol + name-table index) at first available space
        System.out.println("The reference/symbol \"" + reference.getSymbol() + "\" has been inserted at index[" + hashIndex + "].");
        return false;
    }

    public void deleteReference(String symbol) {
        int index = searchReference(symbol, "GET_REFERENCE_INDEX");
        referenceTable[index] = new SymbolReference(null, -1);  //fill deleted symbol-index with empty reference-object-dummy to guarantee correct searching of collision-objects
    }

    public int searchReference(String userInput, String action) {
        int hashIndex = hashFunction(userInput);
        int count = 1;
        int collisionIndex = hashIndex;
        while (true) {
            if (referenceTable[hashIndex] == null) {    //empty field indicates that data is not in table, return non-table value (-1)
                return -1;
            } else if((referenceTable[hashIndex].getSymbol() != null) && referenceTable[hashIndex].getSymbol().equals(userInput)) {    //break on match with item in table
                break;
            }
            hashIndex = collisionHandling(collisionIndex, count);   //else manipulate index with collision-handling method (square probing)
            count++;
        }
        if (action.equals("GET_STOCK_INDEX")) { //return index of name-table alternatively
            return referenceTable[hashIndex].getIndex();
        }
        return hashIndex;
    }

    private int hashFunction(String StockName) {    //hash function
        int index = 0;
        for (int i = 0; i < StockName.length(); i++) {
            index += StockName.charAt(i) * Math.pow(53, i) % 2003; //sum of int-representation of a character times 53 to the power of character-position
        }
        return index % 2003;    // mod table-size to stay in bounds
    }

    private int collisionHandling(int collisionindex, int index) {  //on collision calculate new index with square probing method
        double retVal = (collisionindex + Math.pow((-1), index + 1) * (Math.ceil((index / 2.0)) * Math.ceil((index / 2.0)))) % 2003;
        return (int) retVal;
    }
}
