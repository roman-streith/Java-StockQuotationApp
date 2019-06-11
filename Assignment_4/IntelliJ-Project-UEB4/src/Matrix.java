import java.io.BufferedReader;
import java.io.FileReader;

public class Matrix {   // adjacency matrix
    private Edge[][] matrix;
    private double[] x_vals;
    private double[] y_vals;
    private int dim;

    public Matrix(String fname) {
        try (BufferedReader br = new BufferedReader(new FileReader(System.getProperty("user.dir") + "/input/" + fname + ".txt"))) {
            this.dim = Integer.parseInt(br.readLine()); // first value read in is dimension of adjacency matrix
            this.matrix = new Edge[this.dim][this.dim]; // init matrix with dimension
            this.x_vals = new double[this.dim];         // arrays for x and y values of read in points
            this.y_vals = new double[this.dim];
            for (int i = 0; i < this.dim; i++) {
                String line = br.readLine();
                System.out.println(line);
                this.x_vals[i] = Double.parseDouble(line.split(" ")[0]);    // x = first value of read line split by space
                this.y_vals[i] = Double.parseDouble(line.split(" ")[1]);    // y = second value
            }
            for (int i = 0; i < this.dim; i++) {        // distance from a node to itself is always 0 ->> matrix[i][j] = 0 | i = j
                this.matrix[i][i] = new Edge(0);
            }
            for (int i = 1; i < this.dim; i++) {    // because graph edges have no direction/are doubledirective traverse upper triangle matrix and mirror values
                for (int j = 0; j < i; j++) {       // using the 0-diagonal of given matrix
                    this.matrix[i][j] = new Edge(getDistance(this.x_vals[i], this.y_vals[i], this.x_vals[j], this.y_vals[j]));
                    this.matrix[j][i] = new Edge(this.matrix[i][j].getWeight());
                }
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public double getEdgeWeight(int x, int y) {
        return this.matrix[x][y].getWeight();
    }

    public Edge[][] getMatrix() {
        return this.matrix;
    }

    public void printMatrix() {
        System.out.format("   ");
        for (int j = 0; j < dim; j++) {
            System.out.format("    %d    ", j + 1);
        }
        System.out.format("\n");
        for (int i = 0; i < dim; i++) {
            System.out.format("%2d |", i + 1);
            for (int j = 0; j < dim; j++) {
                System.out.format("%f|", this.matrix[i][j].getWeight());
            }
            System.out.format("\n");
        }
        System.out.format("\n");
    }

    public int getDimension() {
        return this.dim;
    }

    public void printPath(int[] path) {     // print given path/loop
        for (int j = 0; j < this.dim; j++) {
            System.out.format("[%d]->", path[j]);
        }
        System.out.format("[%d] Total: %f\n\n", path[0], pathDistance(path));
    }

    public double pathDistance(int[] path) {    // caluclate path distance of given path with consideration of closing the cycle
        double distance = 0;
        for (int j = 0; j < dim; j++) {
            distance += matrix[path[j] - 1][path[(j + 1) % this.dim] - 1].getWeight();
        }
        return distance;
    }

    private double getDistance(double x1, double y1, double x2, double y2) {    // distance between 2 2-dim points
        return Math.sqrt(Math.pow((x1 - x2), 2) + Math.pow((y1 - y2), 2));
    }
}
