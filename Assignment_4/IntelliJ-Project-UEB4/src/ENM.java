import java.util.ArrayList;
import java.util.Iterator;

public class ENM {
    private Matrix matrix;
    private int dim;
    private ArrayList<Integer> list;
    private ArrayList<int[]> permutations = new ArrayList<>();
    private int maxPerm;

    public ENM(Matrix matrix) {
        this.matrix = matrix;
        this.dim = matrix.getDimension();
        this.list = buildList(dim);
        this.maxPerm = factorial(dim - 1) / 2;
        this.permute(new ArrayList<>(), list);
    }

    public void printPath() {
        System.out.println("Shortest path for ENM: ");
        matrix.printPath(this.bestPath());
    }

    private ArrayList<Integer> buildList(int entries) {
        ArrayList<Integer> list = new ArrayList<>();
        for (int i = 0; i < entries; i++) {
            list.add(i + 1);
        }
        return list;
    }

    private void permute(ArrayList<Integer> prevpath, ArrayList<Integer> prevmissing) {
        if (prevmissing.size() == 0) {
            permutations.add(listToArray(prevpath));
        } else if (this.permutations.size() <= this.maxPerm) {
            for (int i = 0; i < prevmissing.size(); i++) {
                ArrayList<Integer> path = (ArrayList<Integer>) prevpath.clone();
                ArrayList<Integer> missing = (ArrayList<Integer>) prevmissing.clone();
                path.add(prevmissing.get(i));
                missing.remove(i);
                permute(path, missing);
            }
        }
    }

    private int[] listToArray(ArrayList<Integer> list) {
        int[] array = new int[list.size()];
        Iterator<Integer> iterator = list.iterator();
        for (int i = 0; i < array.length; i++) {
            array[i] = iterator.next();
        }
        return array;
    }

    private int[] bestPath() {
        int[] minPath = null;
        double minDist = Double.MAX_VALUE;
        for (int[] path : permutations) {
            if (matrix.pathDistance(path) < minDist) {
                minDist = matrix.pathDistance(path);
                minPath = path;
            }
        }
        return minPath;
    }

    private int factorial(int number) {
        int result = 1;
        for (int factor = 2; factor <= number; factor++) {
            result *= factor;
        }
        return result;
    }
}
