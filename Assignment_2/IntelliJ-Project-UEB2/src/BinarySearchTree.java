
public class BinarySearchTree {
    private Node root;

    public BinarySearchTree() {
        root = null;
    }

    public Node getRoot() {
        return root;
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
        if (checkAVL(this.root)) {
            System.out.println("AVL: yes");
        } else {
            System.out.println("AVL: no");
        }
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

    private boolean checkAVL(Node node) {
        boolean right = true;
        boolean left = true;
        if (node.getRight() != null) {
            right = this.checkAVL(node.getRight());
        }
        if (node.getLeft() != null) {
            left = this.checkAVL(node.getLeft());
        }
        if (this.balance(node) && right && left) { //set this.balance(node) as last if condition for more efficiency
            return true;
        }
        return false;
    }

    private boolean balance(Node node) {
        int diff = 0;
        if ((node.getRight()!= null) && (node.getLeft()!= null)) {
            diff = Math.abs(maxdepth(node.getRight())- maxdepth(node.getLeft()));
        } else if ((node.getRight() == null) && (node.getLeft()!= null)) {
            diff = Math.abs(maxdepth(node.getLeft()));
        } else if ((node.getLeft() == null) && (node.getRight()!= null)) {
            diff = Math.abs(maxdepth(node.getRight()));
        }
        if (diff > 1) {
            System.out.println("bal(" + node.getKey() + ") = " + diff + " (AVL Violation!)");
            return false;
        } else {
            System.out.println("bal(" + node.getKey() + ") = " + diff);
            return true;
        }
    }

    private int maxdepth(Node node) {
        int right = 1;
        int left = 1;
        if (node.getRight() != null) {
            right += maxdepth(node.getRight());
        }
        if (node.getLeft() != null) {
            left += maxdepth(node.getLeft());
        }
        return Math.max(left, right);
    }
}