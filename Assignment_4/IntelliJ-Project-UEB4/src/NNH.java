
public class NNH {

    private int[] path;
    private Node [][] map;
    private int dimension;

    public NNH(Node [][] matrix){
        this.map = matrix;
        this.dimension = matrix[0].length;
        this.path = new int[dimension + 1];
        double totalWeight = findPath(0, 0,0);
        System.out.println("\nTotal Weight: " + totalWeight);
        printPath();
    }

    private double findPath(int level, int lastLevel, int visits){
        double weight = 0.0;
        path[visits] = level + 1;
        if (visits < this.dimension ){
            setVisited(level, lastLevel);
            Node []currentLevel = map[level];
            //System.out.format("Level: %d -> ", level + 1);
            //printNodeArray(currentLevel);
            int closest = findClosest(currentLevel);
            weight = currentLevel[closest].getWeight();
            weight += findPath(closest, level,visits + 1);
        }
        return weight;
    }

    private int findClosest(Node []level){
        int closestIndex = 0;
        double closest = 0;
        boolean firstSet = true;
        for (int i = 0; i < dimension; i++){
            if (!level[i].isVisited() && firstSet){
                closest = level[i].getWeight();
                closestIndex = i;
                firstSet = false;
            } else if (level[i].getWeight() < closest && !level[i].isVisited()){
                closest = level[i].getWeight();
                closestIndex = i;
            }
        }
        if (firstSet){
            return 0;
        }
        //System.out.format("Closes: %f at index %d\n\n", closest, closestIndex + 1);
        return closestIndex;
    }

    private void setVisited(int level, int lastlevel){
        for (int i = 0; i < dimension; i++){
            for (int j = 0; j < dimension; j++){
                if(j == level || j == lastlevel){
                    map[i][j].setVisited();
                }
            }
        }
    }

    private void printNodeArray(Node [] array){
        for (int i = 0; i < dimension; i++){
            System.out.format("[%f| v = %s]",array[i].getWeight(), array[i].isVisited());
        }
        System.out.format("\n");
    }

    private void printPath(){
        System.out.format("[%d]", path[0]);
        for (int i = 1; i < dimension + 1; i++){
            System.out.format("->[%d]", path[i]);
        }
        System.out.println();
    }

}
