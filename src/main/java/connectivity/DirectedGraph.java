package connectivity;

import java.util.ArrayList;
import java.util.List;

public class DirectedGraph {
    private int vertices;
    private List<List<Integer>> adjacency;

    public DirectedGraph(int vertices) {
        if (vertices < 0) {
            throw new IllegalArgumentException(
                    "Number of vertices cannot be negative"
            );
        }

        this.vertices = vertices;
        adjacency = new ArrayList<>();

        for (int i = 0; i < vertices; i++) {
            adjacency.add(new ArrayList<>());
        }
    }

    public void addEdge(int source, int destination) {
        adjacency.get(source).add(destination);
    }

    public int getVertices() {
        return vertices;
    }

    public List<Integer> getNeighbors(int vertex) {
        return adjacency.get(vertex);
    }

    public void printGraph() {
        for (int i = 0; i < vertices; i++) {
            System.out.println(i + " -> " + adjacency.get(i));
        }
    }
}
