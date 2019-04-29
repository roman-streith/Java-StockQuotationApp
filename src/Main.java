//import java.util.Arrays;
import java.io.*;
import java.util.Scanner;

public class Main {

    public static void main(String[] args) {
        // initiate Table (Implement if no prev state loaded)
        HashTable stockTable = new HashTable(2003);
        // init scanner for user input...
        Scanner reader = new Scanner(System.in);
        String userInput = "";
        while (!userInput.equals("8")){
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
                    stockTable.insertStock(name, symbol, number);
                    break;
                case "2": // DELETE
                    System.out.format("Enter the Stock you want to remove from the table: ");
                    String delName = reader.nextLine();
                    stockTable.checkTable(delName, "DELETE");
                    break;
                case "3": // IMPORT
                    System.out.format("Enter symbol of the stock you want to import: ");
                    String importSymbol = reader.nextLine();
                    stockTable.checkTable(importSymbol, "IMPORT");
                    break;
                case "4": // SEARCH
                    System.out.format("Enter the Stock you want to search for in the table: ");
                    String searchName = reader.nextLine();
                    stockTable.checkTable(searchName, "SEARCH");
                    //stockTable.showHistory(searchName);
                    break;
                case "5": // PLOT
                    System.out.format("Enter symbol or name of the stock you want to plot: ");
                    String plotSymbol = reader.nextLine();
                    stockTable.checkTable(plotSymbol, "PLOT");
                    break;
                /*case "5": // PRINT CSV
                    System.out.format("Enter symbol of stock you want to print: ");
                    String printSymbol = reader.nextLine();
                    FileManager.printCSV(printSymbol, stockTable);
                    break;*/
                case "6": //
                    System.out.println("Save as: *.ser");
                    System.out.format("Name: ");
                    String fileName = reader.nextLine();
                    FileManager.saveHashTable(stockTable, fileName);
                    break;
                case "7": //
                    System.out.println("Load : *.ser");
                    System.out.format("Enter the name of the file you want to load: ");
                    String fileToLoad = reader.nextLine();
                    stockTable = FileManager.loadHashTable(stockTable, fileToLoad);
                    break;
                case "8": // EXIT
                    System.out.println("Exiting...");
                    break;
                default:
                    System.out.println("Invalid Input!");
                    break;
            }
        }
        System.out.println("Ending Application...");
    }

    private static void showMenu(){
        String[] menu = {"ADD", "DELETE", "IMPORT", "SEARCH", "PLOT", "SAVE", "LOAD", "EXIT..."};
        System.out.format("\nEnter the according number and press enter to continue:\n");
        for(int i = 0; i < menu.length; i++){
            System.out.println((i+1) + ") " + menu[i]);
        }
        System.out.format("\nSelect: ");
    }
}
