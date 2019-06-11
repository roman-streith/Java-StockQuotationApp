public class NNH {
    private int[] path;
    private Matrix matrix;
    private int dim;

    public NNH(Matrix matrix) {
        this.matrix = matrix;
        this.dim = matrix.getDimension();
        this.path = new int[this.dim];
        findPath(0, 0);
    }

    public void printPath() {
        System.out.println("Shortest path for NNH: ");
        matrix.printPath(path);
    }

    private void findPath(int point, int index) {
        path[index] = point + 1;
        if (index + 1 < this.dim) {
            setVisited(point);
            Edge[] distances = matrix.getMatrix()[point];
            findPath(nearest(distances), index + 1);
        }
    }

    private int nearest(Edge[] point) {
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

    private void setVisited(int row) {
        for (int i = 0; i < dim; i++) {
            matrix.getMatrix()[i][row].setVisited();
        }
    }
}
