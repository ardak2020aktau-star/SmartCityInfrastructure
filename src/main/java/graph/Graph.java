package graph;
import java.util.ArrayList;
import java.util.List;

public class Graph {
    private int vertices;
    private List<Edge> edges;

    public Graph(int vertices) {
        this.vertices = vertices;
        this.edges = new ArrayList<>();
    }

    public void addEdge(int source, int destination, int weight) {
        edges.add(new Edge(source, destination, weight));
    }

    public void printEdges() {
        for (Edge edge : edges) {
            System.out.println(edge.source + " -> "
                    + edge.destination + " : " + edge.weight);
        }
    }
}