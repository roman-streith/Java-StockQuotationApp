public class NNH {
    private int[] path; // resultuing path
    private Matrix matrix;  //adjacency matrix
    private int dim;    //dimension

    public NNH(Matrix matrix) {
        this.matrix = matrix;
        this.dim = matrix.getDimension();
        this.path = new int[this.dim];
        findPath(0, 0); // beginning with point 1(index 0) as starting node
    }

    public void printPath() {
        System.out.println("Shortest path for NNH: ");
        matrix.printPath(path);
    }

    private void findPath(int point, int index) {
        path[index] = point + 1;    // add point to path
        if (index + 1 < this.dim) { // while not finished yet
            setVisited(point);      // set added point as visited so no cycluses happen
            Edge[] distances = matrix.getMatrix()[point]; // select whole column with edge weigths to other point from added point
            findPath(nearest(distances), index + 1);    // find the shortest distance/smallest edge weight within given column, move to
        }                                                     // corresponding point and repeat until no unvisited points are lest
    }

    private int nearest(Edge[] point) { // find the nearest point in a column adjacency matrix/ the shortest edge to a point not yet visited
        int index = 0;
        double weight = Double.MAX_VALUE;
        for (int i = 0; i < this.dim; i++) {
            if (point[i].getWeight() < weight && !point[i].isVisited()) {
                weight = point[i].getWeight();
                index = i;
            }
        }
        return index;
    }

    private void setVisited(int row) {  // sets a point/row in the adjacence matrix to already visited so the next point picked is valis
        for (int i = 0; i < dim; i++) {
            matrix.getMatrix()[i][row].setVisited();
        }
    }
}
