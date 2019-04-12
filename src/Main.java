//import java.util.Arrays;
import java.util.Scanner;

public class Main {

    public static void main(String[] args) {
        // initiate Table (Implement if no prev state loaded)
        HashTable stockTable = new HashTable(2003);
        // wait for user input...
        Scanner reader = new Scanner(System.in);
        String userInput = "";
        while (!userInput.equals("6")){
            showMenu();
            userInput = reader.nextLine();
            switch (userInput){
                case "1": // ADD
                    System.out.println("Enter the Stock you want to add to the table!");
                    System.out.format("Name: ");
                    String name = reader.nextLine();
                    System.out.format("Symbol: ");
                    String symbol = reader.nextLine();
                    System.out.format("Number: ");
                    String number = reader.nextLine();
                    // initialise as  new stock
                    Stock stock = new Stock(name, symbol, number);
                    stockTable.insertStock(stock);
                    break;
                case "2": // SEARCH
                    System.out.format("Enter the Stock you want to search for in the table: ");
                    String searchName = reader.nextLine();
                    stockTable.checkTable(searchName, "SEARCH");
                    //stockTable.showHistory(searchName);
                    break;
                case "3": // DELETE
                    System.out.format("Enter the Stock you want to remove from the table: ");
                    String delName = reader.nextLine();
                    stockTable.checkTable(delName, "DELETE");
                    break;
                case "4": // IMPORT
                    System.out.format("Enter symbol of stock you want to import: ");
                    String importSymbol = reader.nextLine();
                    stockTable.checkTable(importSymbol, "IMPORT");
                    break;
                case "5": // PRINT CSV
                    System.out.format("Enter symbol of stock you want to print: ");
                    String printSymbol = reader.nextLine();
                    CSVFileReader.printFile(printSymbol, stockTable);
                    break;
                case "6": // EXIT
                    System.out.println("Exiting...");
                    break;
                 //...
                default:
                    System.out.println("Invalid Input!");
                    break;
            }
        }
        System.out.println("Ending Application...");
    }

    private static void showMenu(){
        String[] menu = {"ADD", "SEARCH", "DELETE", "IMPORT", "PRINT CSV", "EXIT..."};
        System.out.format("\nEnter the according number and press enter to continue:\n");
        for(int i = 0; i < menu.length; i++){
            System.out.println((i+1) + ".) " + menu[i]);
        }
    }
}
