import java.util.Scanner;

public class SymbolHashTable implements java.io.Serializable{
    private SymbolReference[] referenceTable;

    SymbolHashTable(int size){
        this.referenceTable = new SymbolReference[size];
    }

    public boolean insertReference(SymbolReference reference){
        int hashIndex = hashFunction(reference.getSymbol());
        int count = 2;
        boolean duplicat = false;
        int collisionindex = hashIndex;
        while (referenceTable[hashIndex] != null) {
            if (referenceTable[hashIndex].getSymbol().equals(reference.getSymbol())) {
                System.out.println("The reference/symbol " + reference.getSymbol() + " already exists!");
                duplicat = true;
                break;
            } else if (referenceTable[hashIndex] == null) {
                break;
            }

            hashIndex= collisionHandling(collisionindex, count);
            count++;
        }
        if (!duplicat){
            referenceTable[hashIndex] = reference;
            System.out.println("The reference/symbol \"" + reference.getSymbol() +
                            "\" has been inserted at index[" + hashIndex + "].");
            return false;
        } else {
            return true;
        }
    }

    public void deleteReference(String symbol){
        int index = searchReference(symbol, "GET_REFERENCE_INDEX");
        referenceTable[index] = null;
    }

    public int searchReference(String userInput, String action){
        int index = hashFunction(userInput);
        int val = 1;
        while (true){
            if (referenceTable[index] == null){
                index = -1;
                break;
            } else if(referenceTable[index].getSymbol().equals(userInput)) {
                break;
            }
            index += ((val + 1) * (val + 1)) % 2003;
            val++;
        }
        // gets the index of the actual stock
        if (action.equals("GET_STOCK_INDEX") && index != -1) {
            return referenceTable[index].getIndex() % 2003;
        // gets the index of the reference itself
        } else if (action.equals("GET_REFERENCE_INDEX")) {
            return index % 2003;
        }
        return -1;
    }

    private int hashFunction(String StockName){
        int index = 0;
        for (int i = 0; i < StockName.length(); i++){
            index += StockName.charAt(i) * Math.pow(53, i);
        }
        return index%2003;
    }

    private int collisionHandling(int collisionindex, int index){
        double retVal = 0;
        for ( int i = 0; i < 10; i++){
            retVal = (collisionindex + Math.pow((-1), index + 1) * (Math.ceil((index/2)) * Math.ceil((index/2)))) % 2003;
            System.out.format("retval["+index+"]: " + retVal +"\n");
            index++;
        }
        return (int)retVal;
    }

}
