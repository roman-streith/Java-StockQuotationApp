import java.util.ArrayList;
import java.util.Iterator;

public class ENM {
    private Matrix matrix;  //adjacency matrix Dim(dim x dim)
    private int dim;        // dimension of matrix
    private ArrayList<Integer> solutionVol; // volume of all remaining nodes which havent been visited yet
    private ArrayList<Integer> cancelVol;   // volume of nodes who cant be last element in permutation, because every permutation with this node as first element exists
    private ArrayList<int[]> permutations;  // list of all surviving permutations
    private int optindex;                   // index of permutation with shortest path
    private double optlen;                  // length of shortest path

    public ENM(Matrix matrix) {
        this.matrix = matrix;
        this.dim = matrix.getDimension();
        this.optindex = 0;
        this.cancelVol = new ArrayList<>();
        this.permutations = new ArrayList<>();
        this.optlen = Double.MAX_VALUE;     // initalize optimal length to maximal possible value; if better length is found => update
        this.solutionVol = buildList(dim);
        this.permute(new ArrayList<>(), solutionVol, 0); // start with an empty list as "already visited", a full list as "remaining to visit" at the tree root
    }

    public void printPath() {
        //System.out.println("Number of surviving permutations: " + permutations.size());
        //printPermutations();  // uncomment function for printing of all surviving permutations
        System.out.println("Shortest path for ENM: ");
        matrix.printPath(this.permutations.get(optindex));  // print optimal path
    }

    public void printPermutations() {   // print all surviving permutations
        System.out.println("Number of surviving permutations: " + permutations.size());
        System.out.println("\nPERMUTATIONS");
        for (int[] a : permutations) {
            for (int e : a) {
                System.out.print("[" + e + "]");
            }
            System.out.println();
        }
    }

    private ArrayList<Integer> buildList(int entries) { // initial list for recursive permutation function with index of every element (this.solutionVol)
        ArrayList<Integer> list = new ArrayList<>();    // will be used as volume of remaining possible for the next step
        for (int i = 0; i < entries; i++) {             // on move to next step the possible node will be removed from this list
            list.add(i + 1);                            // empty list indicates that no further move is to be done
        }
        return list;
    }

    private boolean subsetCancelVol(ArrayList<Integer> solvol) {    // check whether the passed list (remaining possible nodes) is a subset of this.cancelVol
        for (Integer i : solvol) {                                  // this.cancelVol being a set of nodes N for which every combination already exists
            if (!cancelVol.contains(i)) {                           // with N = starting point (updated while calc. permutations)
                return false;                                       // if every node of remaining possible nodes is in this.cancelVol, none can be last
            }                                                       // because every combination with this node as End/Starting-Point already exists
        }                                                           // => therfore cancel calculation of given branch
        return true;
    }

    private double mindist(int index, ArrayList<Integer> vol) {             // get the next point from remaining possible nodes
        double retval = Double.MAX_VALUE;                                   // with the shortest distance to point on index position
        for (int i = 0; i < vol.size(); i++) {
            double w = matrix.getEdgeWeight(index, vol.get(i) - 1);
            if (w < retval) {
                retval = w;
            }
        }
        return retval;
    }

    private void permute(ArrayList<Integer> path, ArrayList<Integer> notinpath, int depth) {    // recursive permutation function with branch and cut
        int nipsize = notinpath.size();                                                         // get size of remaining possible points to hop on
        if (nipsize == 0) {                                                                     // if no point remaining full calc of permutation has
            int[] completepath = listToArray(path);                                             // taken place => add permutation to permutation array
            double pathdistance = matrix.pathDistance(completepath);                            // check whether total distance of new permutation is
            this.permutations.add(completepath);                                                // smaller(better) than the old optimal length
            if (pathdistance < this.optlen) {                                                   // if so set optimal length to distance of new perm
                this.optlen = pathdistance;                                                     // and adjust optind, to find the best path for printing
                this.optindex = permutations.size() - 1;                                        // after this is done terminate this branch
            }
            return;
        }
        if (subsetCancelVol(notinpath)) { // given previous if condition there are still possible nodes to hop on
            return;                       // if the set of all remaining possible nodes is a subset of nodes, which have already bean the first node
        }                                 // then none of the remaining nodes can be last without repetition of a permutation => terminate branch
        double currdist = 0;
        int currind = path.size();        // current index in were at in the adjacency matrix
        for (int i = 0; i < currind - 1; i++) {
            currdist += matrix.getEdgeWeight(path.get(i) - 1, path.get(i + 1) - 1); // sum of edgeweight of already traversed path
        }
        currdist += nipsize * mindist(currind, notinpath);  // from current node take the shortest distance to one of the remaining nodes
        if (currdist >= optlen) {                           // multiply by quantity of remaining nodes and add to sum => lower bound for current branch
            return;                                         // if lowerbound is already worse than our current optimal solution => cut/terminate given branch
        }
        for (int i = 0; i < notinpath.size(); i++) {        // for every node left in volume of remaining possible hop nodes
            if (depth == 0) {                               // check depth; on depth 0 can add root node of given subtree to this.cancelVol to avoid repetition
                cancelVol.add(notinpath.get(i));            // because every combination with root node first = every comination with root node last
            }
            ArrayList<Integer> subpath = (ArrayList<Integer>) path.clone(); // clone already traversed path and add the current node to the path
            ArrayList<Integer> subsolvol = (ArrayList<Integer>) notinpath.clone(); // clone remaining nodes and remove the node which was added to the new path
            subpath.add(notinpath.get(i));
            subsolvol.remove(i);
            permute(subpath, subsolvol, depth + 1); // repeat recursively until a break condition is hit going deeper and deeper into the tree
        }
    }

    private int[] listToArray(ArrayList<Integer> list) {    // helper function to convert ArrayList<Integer> into int[]
        int[] array = new int[list.size()];
        Iterator<Integer> iterator = list.iterator();
        for (int i = 0; i < array.length; i++) {
            array[i] = iterator.next();
        }
        return array;
    }
}
