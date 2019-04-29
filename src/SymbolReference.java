public class SymbolReference implements java.io.Serializable {
    // constructor method
    SymbolReference(String symbol, int index){
        this.indexInTable = index;
        this.symbol = symbol; // Kürzel
    }

    private int indexInTable;
    private String symbol;

    public String getSymbol(){
        return this.symbol;
    }
    public int getIndex(){
        return this.indexInTable;
    }
}
