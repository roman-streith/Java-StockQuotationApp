//import java.util.Arrays;
import java.util.Scanner;

public class Main {

    public static void main(String[] args) {
        // initiate Table (Implement if no prev state loaded)
        HashTable stockTable = new HashTable(1000);
        // wait for user input...
        Scanner reader = new Scanner(System.in);
        String userInput = "";
        while (!userInput.equals("4")){
            showMenu();
            userInput = reader.nextLine();
            switch (userInput){
                case "1": // ADD
                    System.out.println("Enter the Stock you want to add to the table!");
                    System.out.println("Name: ");
                    String name = reader.nextLine();
                    System.out.println("Symbol: ");
                    String symbol = reader.nextLine();
                    System.out.println("Number: ");
                    String number = reader.nextLine();
                    // initialise as  new stock
                    Stock stock = new Stock(name, symbol, number);
                    stockTable.insertStock(stock);
                    break;
                case "2": // SEARCH
                    System.out.println("Enter the Stock you want to search for in the table: ");
                    String searchName = reader.nextLine();
                    stockTable.searchStock(searchName);
                    break;
                case "3": // DELETE
                    System.out.println("Enter the Stock you want to remove from the table: ");
                    String delName = reader.nextLine();
                    stockTable.deleteStock(delName);
                    break;
                case "4": // EXIT
                    System.out.println("Exiting...");
                    break;
                case "5": // IMPORT
                    System.out.println("Enter symbol of stock you want to import: ");
                    String importSymbol = reader.nextLine();
                    CSVFileReader.readFile(importSymbol, stockTable);
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
        System.out.println("Enter the according number and press enter to continue:");
        System.out.println("1.) ADD");
        System.out.println("2.) SEARCH");
        System.out.println("3.) DELETE");
        System.out.println("4.) EXIT...");
        System.out.println("5.) IMPORT");
    }
}
