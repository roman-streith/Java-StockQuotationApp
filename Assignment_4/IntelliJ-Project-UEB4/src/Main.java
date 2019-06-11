// Authors: Philipp Lakheshar & Roman Streith

public class Main { // usage: java -jar JARPATH [-e|-n] FILENAME // config files in "input" folder as .txt files // FILENAME without .txt ending
    // f.e.: java -jar tsp.jar -e test // from folder directory
    public static void main(String[] args) {
        long startTime = System.nanoTime();
        if (args.length != 2 || (!args[0].contains("-e") && !args[0].contains("-n"))) {
            System.err.println("usage: java -jar tsp.jar -e|-n filename");
            return;
        }
        Matrix adjm = new Matrix(args[1]);
        adjm.printMatrix();
        if (args[0].contains("-e")) {
            ENM ENMPath = new ENM(adjm);
            ENMPath.printPath();
            System.out.println("Execution Time: " + ((double) (System.nanoTime() - startTime) / 1000000000) + "s");
            return;
        }
        NNH nnhPath = new NNH(adjm);
        nnhPath.printPath();
        System.out.println("Execution Time: " + ((double) (System.nanoTime() - startTime) / 1000000000) + "s");
    }
}
