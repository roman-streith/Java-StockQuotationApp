public class Node { //BST-Node with key and references to left and right child
    private int key;
    private Node left;
    private Node right;

    public Node(int key){
        this.key = key;
        this.left = null;
        this.right = null;
    }

    public void setKey(int key) {
        this.key = key;
    }

    public Node getLeft() {
        return left;
    }

    public void setLeft(Node left) {
        this.left = left;
    }

    public Node getRight() {
        return right;
    }

    public void setRight(Node right) {
        this.right = right;
    }

    public int getKey() {
        return key;
    }
}
