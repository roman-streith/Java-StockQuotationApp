public class Main {
    public static void main(String[] args) {
        BinarySearchTree bst = new BinarySearchTree();
        FileManager.fileToTree("avl", bst);
        bst.checkAVL(bst.getRoot());
        bst.printStatistics();
    }
}
