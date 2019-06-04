import java.lang.reflect.Array;

public class Main {

    public static void main(String[] args) {

        double[] testX = {1.0, 5.5, 3.0, 0.5, 4.0};
        double[] testY = {1.0, 0.9, 1.5, 4.3, 3.0};

        Graph map = new Graph(testX, testY);
        map.printMatrix();

        /** number of all possible routs from specific start
         *  nRouts = (nNodes - 1)! / 2
         *  case nNodes = 5 -> nRouts = 12
         */
        //new Enum(map.getMatrix());

        new NNH(map.getMatrix());
    }

}
