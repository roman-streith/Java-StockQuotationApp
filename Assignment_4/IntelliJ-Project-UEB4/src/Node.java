public class Node {
    private double weight;
    private boolean visited;

    public Node(double w){
        this.weight = w;
        this.visited = false;
    }

    public double getWeight() {
        return this.weight;
    }

    public boolean isVisited() {
        return this.visited;
    }

    public void setVisited() {
        this.visited = true;
    }
}
