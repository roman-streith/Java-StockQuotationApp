
public class BinaryTree {

    Node root;

    public void insertNode(int key){
        root = insertRecursive(new Node(key), root);
    }

    private Node insertRecursive(Node newNode, Node ptr){
        if (ptr == null){
            //System.out.println("Key: " + newNode.key + " inserted into tree");
            ptr = newNode;
        } else {
            if (newNode.key < ptr.key){
                //System.out.println("Key: " + newNode.key + " continues left");
                ptr.left = insertRecursive(newNode, ptr.left);

            } else if (newNode.key > ptr.key) {
                //System.out.println("Key: " + newNode.key + " continues right");
                ptr.right = insertRecursive(newNode, ptr.right);
            } else {
                //System.out.println("Key: " + newNode.key + " is duplicate");
                return ptr;
            }
        }
        return ptr;
    }

    public void getTreeStats(){

        int max = traverseMax(root, 0);
        int min = traverseMin(root, max);
        int sum = traverse(root, 0);
        int totalEntries = traverseEntries(root, 0);
        double avg = (double)sum/(double)totalEntries;
        boolean isAVL = traverseBalance(root, true);

        if(isAVL){
            System.out.println("AVL: yes");
        } else {
            System.out.println("AVL: no");
        }
        System.out.println("min: " + min + ", max: " + max + ", avg: " + avg);

    }

    private int traverseMin(Node currentNode, int currentMin){

        if (currentNode != null){
            /*if (currentMin > currentNode.key){
                currentMin = currentNode.key;
            }*/
            currentMin = traverseMin(currentNode.left, currentNode.key);
        }
        return currentMin;
    }

    private int traverseMax(Node currentNode, int currentMax){

        if (currentNode != null){
            /*if (currentMax < currentNode.key){
                currentMax = currentNode.key;
            }*/
            currentMax = traverseMax(currentNode.right, currentNode.key);
        }
        return currentMax;
    }

    private int traverse(Node currentNode, int sum){

        if (currentNode != null){
            sum = traverse(currentNode.left, sum + currentNode.key);
            sum = traverse(currentNode.right, sum);
        }
        return sum;
    }

    private int traverseEntries(Node currentNode, int sum){

        if (currentNode != null){
            sum = traverseEntries(currentNode.left, sum + 1);
            sum = traverseEntries(currentNode.right, sum);
        }
        return sum;
    }

    private boolean traverseBalance(Node currentNode, boolean isAVL){

        if (currentNode != null){
            traverseBalance(currentNode.right, true);
            traverseBalance(currentNode.left, true);
            isAVL = checkBalance(currentNode, true);
        }
        return isAVL;
    }

    private boolean checkBalance(Node currentNode, boolean isAVL){

        int leftDepth = depth(currentNode.left);
        int rightDepth = depth(currentNode.right);
        int diff = Math.abs(rightDepth -  leftDepth);

        //System.out.println("CurrentNode : " + currentNode.key + ", ldepth: " + leftDepth + ", rdepth: " + rightDepth + ", diff: " + diff);

        if (diff <= 1){
            System.out.println("bal(" + currentNode.key +") = " + diff);
        } else {
            System.out.println("bal(" + currentNode.key +") = " + diff + " (AVL Violation!)");
            isAVL = false;
        }
        return isAVL;
        //System.out.println("");

    }

    private int depth(Node currentNode){

        if (currentNode == null){
            return 0;
        }
        int lsum = traverseLeftDepth(currentNode.left, 1);
        int rsum = traverseRightDepth(currentNode.right, 1);
        return Math.max(lsum, rsum);
    }

    private int traverseLeftDepth(Node currentNode, int currentdepth){

        if (currentNode != null){
            currentdepth = traverseLeftDepth(currentNode.left, currentdepth + 1);
        }
        return currentdepth;
    }

    private int traverseRightDepth(Node currentNode, int currentdepth){

        if (currentNode != null){
            currentdepth = traverseRightDepth(currentNode.right, currentdepth + 1);
        }
        return currentdepth;
    }
}
