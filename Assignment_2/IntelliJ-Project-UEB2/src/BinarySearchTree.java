
public class BinarySearchTree {
    private Node root;
    private String avl; //flag to indicate whether tree is avl tree or not

    public BinarySearchTree() { //on creation initialize empty tree
        this.root = null;
        this.avl = "undefined"; //undef until avlcheck
    }

    public Node getRoot() {
        return this.root;
    }

    public Node insert(int key, Node node) { //if tree emtpy first val equals root
        if (node == null) {
            this.root = new Node(key);
            return this.root;
        }
        if (key < node.getKey()) {      //traverse tree recursively according to binary search tree rules until leaf-space for insertion is found
            if (node.getLeft() == null) {   //on empty space insert when smaller
                node.setLeft(new Node(key));
                return node;    //on successful insertion return reference to new node
            }
            return insert(key, node.getLeft()); //bigger but right child is taken, recursive call with right child
        } else if (key > node.getKey()) {   //on empty space insert when bigger
            if (node.getRight() == null) {
                node.setRight(new Node(key));
                return node;    //on successful insertion return reference to new nodes
            }
            return insert(key, node.getRight());    //smaller but left child is taken, recursive call with left child
        }
        return null;    // if node with same value is found, no node is inserted and null returned
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

    private int minKey(Node node) { //traverse recursively left side until leftmost leaf is found
        if (node.getLeft() != null) {   //no left child, therefore smallest value at current node
            return this.minKey(node.getLeft());
        }
        return node.getKey();
    }

    private int maxKey(Node node) { //traverse recursively right side until rightmost leaf is found
        if (node.getRight() != null) {  //no right child, therefore biggest value at current node
            return this.maxKey(node.getRight());
        }
        return node.getKey();
    }

    private int totalKeyNum(Node node) { //recursively from leafs to root add child sums to create return value parent sum
        int sum = node.getKey();    // node value
        if (node.getRight() != null) {  //for each node recursive func-call for each child
            sum += this.totalKeyNum(node.getRight());
        }
        if (node.getLeft() != null) {   //for each node recursive func-call for each child
            sum += this.totalKeyNum(node.getLeft());
        }
        return sum; // sum of node value + child sums
    }

    private int totalNodeNum(Node node) { //recursively from leafs to root add number of children to create return value parent sum
        int sum = 1;    // additional node
        if (node.getRight() != null) {  //for each node recursive func-call for each child
            sum += this.totalNodeNum(node.getRight());
        }
        if (node.getLeft() != null) {   //for each node recursive func-call for each child
            sum += this.totalNodeNum(node.getLeft());
        }
        return sum; // 1 + child sums
    }

    public int checkAVL(Node node) { //recursively from leaf nodes to root check avl-condition
        int rightMax = 0;   //initialize max depth form left and right node-subtree with 0
        int leftMax = 0;
        if (node.getRight() != null) {  //if child exists recursively traverse until leaf is found
            rightMax = this.checkAVL(node.getRight());  //maxdepth of right subtree
        }
        if (node.getLeft() != null) {   //if child exists recursively traverse until leaf is found
            leftMax = this.checkAVL(node.getLeft());    //maxdepth of left subtree
        }
        int bal = Math.abs(leftMax - rightMax); //node balance is absolute value from left - right subtree
        node.setBalance(bal);
        if (bal > 1) {  //on AVL violation
            System.out.println("bal(" + node.getKey() + ") = " + bal + " (AVL Violation!)");
            this.avl = "no";    //  tree avl flag set to no
        } else {
            System.out.println("bal(" + node.getKey() + ") = " + bal);
        }
        if ((node == this.root) && (this.avl != "no")) {    //if no violation until (inclusive) root occured set tree avl flag to yes
            this.avl = "yes";
        }
        return Math.max(leftMax, rightMax) + 1; //return maxdepth from current node + 1 for the current node (leaf ret value always 1)
    }
}