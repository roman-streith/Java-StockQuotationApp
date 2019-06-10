import java.util.ArrayList;
import java.util.Iterator;

public class Enum {

    private Graph graph;
    private int dimension;
    private ArrayList<Integer> list;
    private ArrayList<int[]>possibleRouts = new ArrayList<>();
    private int permBound;
    private int bound = 0;
    private int []path;

    public Enum(Graph graph){
        this.graph = graph;
        this.dimension = graph.getDimension();
        this.list = buildList(dimension);
        this.permBound = fraction((dimension - 1)) / 2;
        permute(new ArrayList<>(), list);
        this.path = findShortestRout();
    }

    public void printPath(){
        System.out.println("Shortest path for Enum: ");
        graph.calcDist(path, "PRINT");
    }

    private ArrayList<Integer> buildList(int entries){
        ArrayList<Integer> list = new ArrayList<>();
        for (int i = 0; i < entries; i++){
            list.add(i + 1);
        }
        return list;
    }

    private void permute(ArrayList<Integer> permList, ArrayList<Integer> list){

        if (list.size() == 0){
            int []permArray = listToArray(permList);
            possibleRouts.add(permArray);
            this.bound++;
        } else if (this.bound < this.permBound){

            for (int i = 0; i < list.size(); i++){
                ArrayList<Integer> clonePerm = (ArrayList<Integer>) permList.clone();
                ArrayList<Integer> cloneList = (ArrayList<Integer>) list.clone();
                clonePerm.add(list.get(i));
                cloneList.remove(i);
                permute(clonePerm, cloneList);
            }
        }
    }

    private int[] listToArray(ArrayList<Integer> list){
        int [] array = new int[list.size()];
        Iterator<Integer> iterator = list.iterator();
        for (int i = 0; i < array.length; i++) {
            array[i] = iterator.next();
        }
        return array;
    }

    private int[] findShortestRout(){

        int minPath = -1;
        double minDist = Double.MAX_VALUE;
        int index = 0;

        for ( int[] path : possibleRouts){

            double currentDist = graph.calcDist(path, "NOPRINT");

            if (currentDist < minDist){
                minDist = currentDist;
                minPath = index;
            }

            index++;
        }
        return possibleRouts.get(minPath);
    }

    private int fraction(int number){
        int  result = 1;
        for (int factor = 2; factor <= number; factor++) {
            result *= factor;
        }
        return result;
    }
}
