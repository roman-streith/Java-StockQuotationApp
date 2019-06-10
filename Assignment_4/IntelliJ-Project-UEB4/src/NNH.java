
public class NNH {

    private int[] path;
    private Graph graph;
    private int dimension;

    public NNH(Graph graph){
        this.graph = graph;
        this.dimension = graph.getDimension();
        this.path = new int[dimension + 1];
        findPath(0, 0,0);
    }

    public void printPath(){
        System.out.println("Shortest path fro NNH: ");
        graph.calcDist(path, "PRINT");
    }

    private void findPath(int level, int lastLevel, int visits){

        path[visits] = level + 1;

        if (visits < this.dimension ){
            setVisited(level, lastLevel);
            Node []currentLevel = graph.getMatrix()[level];
            int closest = findClosest(currentLevel);
            findPath(closest, level,visits + 1);
        }
    }

    private int findClosest(Node []level){
        int closestIndex = 0;
        double closest = Double.MAX_VALUE;

        for (int i = 0; i < dimension; i++){
            if (level[i].getWeight() < closest && !level[i].isVisited()){
                closest = level[i].getWeight();
                closestIndex = i;
            }
        }
        return closestIndex;
    }

    private void setVisited(int level, int lastLevel){
        for (int i = 0; i < dimension; i++){
            for (int j = 0; j < dimension; j++){
                if(j == level || j == lastLevel){
                    graph.getMatrix()[i][j].setVisited();
                }
            }
        }
    }
}
