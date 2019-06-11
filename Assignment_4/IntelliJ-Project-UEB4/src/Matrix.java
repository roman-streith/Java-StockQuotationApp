import java.io.BufferedReader;
import java.io.FileReader;

public class Matrix {
    private Edge[][] matrix;
    private double[] x_vals;
    private double[] y_vals;
    private int dim;

    public Matrix(String fname) {
        try (BufferedReader br = new BufferedReader(new FileReader(System.getProperty("user.dir") + "/input/" + fname + ".txt"))) {
            this.dim = Integer.parseInt(br.readLine());
            this.matrix = new Edge[this.dim][this.dim];
            this.x_vals = new double[this.dim];
            this.y_vals = new double[this.dim];
            for (int i = 0; i < this.dim; i++) {
                String line = br.readLine();
                System.out.println(line);
                this.x_vals[i] = Double.parseDouble(line.split(" ")[0]);
                this.y_vals[i] = Double.parseDouble(line.split(" ")[1]);
            }
            for (int i = 0; i < this.dim; i++) {
                this.matrix[i][i] = new Edge(0);
            }
            for (int i = 1; i < this.dim; i++) {
                for (int j = 0; j < i; j++) {
                    this.matrix[i][j] = new Edge(getDistance(this.x_vals[i], this.y_vals[i], this.x_vals[j], this.y_vals[j]));
                    this.matrix[j][i] = new Edge(this.matrix[i][j].getWeight());
                }
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
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

    public void printPath(int[] path) {
        for (int j = 0; j < this.dim; j++) {
            System.out.format("[%d]->", path[j]);
        }
        System.out.format("[%d] Total: %f\n\n", path[0], pathDistance(path));
    }

    public double pathDistance(int[] path) {
        double distance = 0;
        for (int j = 0; j < dim; j++) {
            distance += matrix[path[j] - 1][path[(j + 1) % this.dim] - 1].getWeight();
        }
        return distance;
    }

    private double getDistance(double x1, double y1, double x2, double y2) {
        return Math.sqrt(Math.pow((x1 - x2), 2) + Math.pow((y1 - y2), 2));
    }
}
