public class Main {

    public static void main(String[] args) {
        String filename = "test";
        BinaryTree tree = new BinaryTree();
        String result = FileManager.readFromFileAndBuildTree(filename, tree);
        tree.getTreeStats();
    }

}
