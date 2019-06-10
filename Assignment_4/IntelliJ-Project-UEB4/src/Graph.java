public class Graph {

    private Node [][]matrix;
    private int dimension;

    public Graph(double []testX, double []testY){
        this.matrix = new Node [testX.length][testX.length];
        this.dimension = testX.length;
        for (int i = 0; i < dimension; i++){
            for (int j = 0; j < dimension; j++){
                // better efficiency with "obere/untere Dreiecksmatrix" possible
                if(i == j){
                    setNode(i, j, 0.0);
                } else {
                    double dist = getDistance(testX[i], testY[i], testX[j], testY[j]);
                    setNode(i, j, dist);
                }
            }
        }
    }

    public Node [][] getMatrix(){
        return this.matrix;
    }

    public void printMatrix(){
        System.out.format("   ");
        for(int j = 0; j < dimension; j++){
            System.out.format("    %d    ", j+1);
        }
        System.out.format("\n");
        for(int i = 0; i < dimension; i++){
            System.out.format("%d |", i + 1);
            for(int j = 0; j < dimension; j++){
                System.out.format("%f|", this.matrix[i][j].getWeight());
            }
            System.out.format("\n");
        }
    }

    public int getDimension(){
        return this.dimension;
    }

    public double calcDist(int []path, String flag){
        double currentDist = 0;
        int nextNode = -1;

        for (int j = 0; j < dimension; j++){
            int currentNode = path[j];
            if (j == dimension - 1){
                nextNode = path[0];
            } else {
                nextNode = path[j+1];
            }
            currentDist += matrix[currentNode - 1][nextNode - 1].getWeight();
            if (flag.equals("PRINT")) { System.out.format("[%d]->", currentNode); }
        }
        if (flag.equals("PRINT")) { System.out.format("[%d] Total: %f\n\n", nextNode, currentDist); }
        return currentDist;
    }

    private void setNode(int x, int y, double dist){
        matrix[x][y] = new Node(dist);
        matrix[y][x] = new Node(dist);
    }

    private double getDistance(double x1, double y1, double x2, double y2){
        return Math.sqrt(Math.pow((x1-x2), 2) + Math.pow((y1-y2), 2));
    }
}
