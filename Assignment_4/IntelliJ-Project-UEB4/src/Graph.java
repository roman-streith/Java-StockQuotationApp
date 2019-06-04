public class Graph {

    private Node [][]matrix;

    public Graph(double []testX, double []testY){
        this.matrix = new Node [testX.length][testX.length];
        for (int i = 0; i < testX.length; i++){
            for (int j = 0; j < testY.length; j++){
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

    private void setNode(int x, int y, double dist){
        matrix[x][y] = new Node(dist);
        matrix[y][x] = new Node(dist);
    }

    public Node [][] getMatrix(){
        return this.matrix;
    }

    public void printMatrix(){
        System.out.format("   ");
        for(int j = 0; j < this.matrix.length; j++){
            System.out.format("    %d    ", j+1);
        }
        System.out.format("\n");
        for(int i = 0; i < this.matrix.length; i++){
            System.out.format("%d |", i + 1);
            for(int j = 0; j < this.matrix.length; j++){
                System.out.format("%f|", this.matrix[i][j].getWeight());
            }
            System.out.format("\n");
        }
    }

    private double getDistance(double x1, double y1, double x2, double y2){
        return Math.sqrt(Math.pow((x1-x2), 2) + Math.pow((y1-y2), 2));
    }

}
