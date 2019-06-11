public class Edge { // Edges for adjacency martix with weight and visited flag for NNH
    private double weight;
    private boolean visited;

    public Edge(double w) {
        this.weight = w;
        this.visited = false;
    }

    public double getWeight() {
        return this.weight;
    }

    public void setWeight(double weight) {
        this.weight = weight;
    }

    public boolean isVisited() {
        return this.visited;
    }

    public void setVisited() {
        this.visited = true;
    }
}
