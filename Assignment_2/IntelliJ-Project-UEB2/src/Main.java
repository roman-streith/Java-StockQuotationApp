import java.util.Scanner;

public class Main {
    public static void main(String[] args) {
        try {
            Scanner scanner = new Scanner(System.in);
            String input = scanner.nextLine();
            BinarySearchTree bst = new BinarySearchTree();  //create emtpy tree object
            FileManager.fileToTree(input.split(" ")[1], bst);     //import values from file
            bst.checkAVL(bst.getRoot());    //check if tree is avl, calculate node balance values
            bst.printStatistics();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
