import java.util.Scanner;

public class SymbolHashTable {
    private SymbolReference[] referenceTable;

    SymbolHashTable(int size){
        this.referenceTable = new SymbolReference[size];
    }

    public void insertReference(SymbolReference reference){
        int index = hashFunction(reference.getSymbol());

        int val = 1;
        boolean duplicat = false;
        while(referenceTable[index] != null) {
            if (referenceTable[index].getSymbol().equals(reference.getSymbol())) {
                System.out.println("The reference " + reference.getSymbol() + " already exists!");
                duplicat = true;
                break;
            } else if (referenceTable[index] == null) {
                break;
            }
            index += ((val + 1) * (val + 1));
            val++;
        }

        if(!duplicat){
            referenceTable[index] = reference;
            System.out.println("The reference \"" + reference.getSymbol() + "\" has been inserted at index[" + index + "].");
        }
    }

    public void deleteReference(String symbol){
        int index = searchReference(symbol, "REFERENCE_INDEX");
        referenceTable[index] = null;
    }

    public int searchReference(String userInput, String flag){
        int index = hashFunction(userInput);
        int val = 1;
        while(true){
            if(referenceTable[index] == null){
                index = -1;
                break;
            } else if(referenceTable[index].getSymbol().equals(userInput)) {
                break;
            }
            index += ((val + 1) * (val + 1)) % 2003;
            val++;
        }
        if(flag.equals("STOCK_INDEX") && index != -1) {
            return referenceTable[index].getIndex() % 2003;
        } else if (flag.equals("REFERENCE_INDEX")) {
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

}
