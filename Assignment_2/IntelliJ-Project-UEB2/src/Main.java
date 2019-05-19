public class Main {
    public static void main(String[] args) {
        BinarySearchTree bst = new BinarySearchTree();
        FileManager.fileToTree("test", bst);
        bst.printStatistics();
    }
}
