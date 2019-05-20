public class Main {
    public static void main(String[] args) {
        BinarySearchTree bst = new BinarySearchTree();  //create emtpy tree object
        FileManager.fileToTree("avl", bst);     //import values from file
        bst.checkAVL(bst.getRoot());    //check if tree is avl, calculate node balance values
        bst.printStatistics();
    }
}
