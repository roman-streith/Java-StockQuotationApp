public class Node { //BST-Node with key and references to left and right child
    private int key;
    private Node left;
    private Node right;
    private int balance;

    public int getBalance() {
        return this.balance;
    }

    public void setBalance(int balance) {
        this.balance = balance;
    }

    public Node(int key){
        this.key = key;
        this.left = null;
        this.right = null;
        this.balance = 0;
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
