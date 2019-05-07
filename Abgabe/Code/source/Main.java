import java.util.Scanner;

public class Main {

    public static void main(String[] args) {
        HashTable stockTable = new HashTable(2003); // create new hashtable with prime 2003
        Scanner reader = new Scanner(System.in);         // init scanner for user input...
        String userInput = "";
        while (!userInput.equals("8")) {    // Menu loop
            showMenu(); //print selection menu
            userInput = reader.nextLine();  //read userinput
            switch (userInput){
                case "1": // if input "1" -> Add Stock to table
                    System.out.println("Enter the Stock you want to add to the table!");
                    System.out.format("Name: ");
                    String name = reader.nextLine();
                    System.out.format("Symbol: ");
                    String symbol = reader.nextLine();
                    System.out.format("Number: ");
                    String number = reader.nextLine();
                    System.out.format("\n");
                    stockTable.insertStock(name, symbol, number); // initialise a new stock and check if it can be inserted
                    break;
                case "2": // if input "2" -> delete the corresponding stock
                    System.out.format("Enter the Stock you want to remove from the table: ");
                    String delName = reader.nextLine();
                    stockTable.checkTable(delName, "DELETE");   // search for stock and delete it and  itd reference in the reference table
                    break;
                case "3": // if input "3" -> import the corresponding stock data
                    System.out.format("Enter the symbol of the stock you want to import: ");
                    String importSymbol = reader.nextLine();
                    stockTable.checkTable(importSymbol, "IMPORT");
                    break;
                case "4": // if input "4" -> search for the corresponding stock and display its data if available
                    System.out.format("Enter the Stock you want to search for in the table: ");
                    String searchName = reader.nextLine();
                    stockTable.checkTable(searchName, "SEARCH");
                    break;
                case "5": // if input "5" -> search for the corresponding stock and display its plot of the last 30 days
                    System.out.format("Enter symbol or name of the stock you want to plot: ");
                    String plotSymbol = reader.nextLine();
                    stockTable.checkTable(plotSymbol, "PLOT");
                    break;
                case "6": // if input "6" -> save the current table as *.ser file
                    System.out.println("Save as: *.ser");
                    System.out.format("Name: ");
                    String fileName = reader.nextLine();
                    FileManager.saveHashTable(stockTable, fileName);
                    break;
                case "7": // if input "7" -> load the corresponding *.ser file
                    System.out.println("Load : *.ser");
                    System.out.format("Enter the name of the file you want to load: ");
                    String fileToLoad = reader.nextLine();
                    stockTable = FileManager.loadHashTable(stockTable, fileToLoad);
                    break;
                case "8": // if input "8" -> exiting the application
                    System.out.println("Exiting...");
                    break;
                default: // if non of the options is provided as input
                    System.out.println("Invalid Input!");
                    break;
            }
        }
        System.out.println("Ending Application...");
    }

    private static void showMenu() {    //print menu
        String[] menu = {"ADD", "DELETE", "IMPORT", "SEARCH", "PLOT", "SAVE", "LOAD", "EXIT..."};
        System.out.format("\nEnter the according number and press enter to continue:\n");
        for(int i = 0; i < menu.length; i++) {
            System.out.println((i+1) + ") " + menu[i]);
        }
        System.out.format("\nSelect: ");
    }
}
