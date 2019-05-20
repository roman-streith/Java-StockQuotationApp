
public class BinarySearchTree {
    private Node root;
    private String avl;

    public BinarySearchTree() {
        this.root = null;
        this.avl = "undefined";
    }

    public Node getRoot() {
        return this.root;
    }

    public Node insert(int key, Node node) {
        if (node == null) {
            this.root = new Node(key);
            return this.root;
        }
        if (key < node.getKey()) {
            if (node.getLeft() == null) {
                node.setLeft(new Node(key));
                return node;
            }
            return insert(key, node.getLeft());
        } else if (key > node.getKey()) {
            if (node.getRight() == null) {
                node.setRight(new Node(key));
                return node;
            }
            return insert(key, node.getRight());
        }
        return null;
    }

    public void printStatistics() {
        if (root == null) {
            System.out.println("No Tree instantiated.");
            return;
        }
        System.out.println("AVL: " + this.avl);
        System.out.println("min: " + this.minKey(this.root));
        System.out.println("max: " + this.maxKey(this.root));
        System.out.println("avg: " + ((double) this.totalKeyNum(this.root) / (double) this.totalNodeNum(this.root)));

    }

    private int minKey(Node node) {
        if (node.getLeft() != null) {
            return this.minKey(node.getLeft());
        }
        return node.getKey();
    }

    private int maxKey(Node node) {
        if (node.getRight() != null) {
            return this.maxKey(node.getRight());
        }
        return node.getKey();
    }

    private int totalKeyNum(Node node) {
        int sum = node.getKey();
        if (node.getRight() != null) {
            sum += this.totalKeyNum(node.getRight());
        }
        if (node.getLeft() != null) {
            sum += this.totalKeyNum(node.getLeft());
        }
        return sum;
    }

    private int totalNodeNum(Node node) {
        int sum = 1;
        if (node.getRight() != null) {
            sum += this.totalNodeNum(node.getRight());
        }
        if (node.getLeft() != null) {
            sum += this.totalNodeNum(node.getLeft());
        }
        return sum;
    }

    public int checkAVL(Node node) {
        int rightMax = 0;
        int leftMax = 0;
        if (node.getRight() != null) {
            rightMax = this.checkAVL(node.getRight());
        }
        if (node.getLeft() != null) {
            leftMax = this.checkAVL(node.getLeft());
        }
        int bal = Math.abs(leftMax - rightMax);
        node.setBalance(bal);
        if (bal > 1) {
            System.out.println("bal(" + node.getKey() + ") = " + bal + " (AVL Violation!)");
            this.avl = "no";
        } else {
            System.out.println("bal(" + node.getKey() + ") = " + bal);
        }
        if ((node == this.root) && (this.avl != "no")) {
            this.avl = "yes";
        }
        return Math.max(leftMax, rightMax) + 1;
    }
}