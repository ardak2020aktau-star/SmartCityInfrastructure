package connectivity;
import java.util.ArrayList;
import java.util.List;
import java.util.Stack;

public class Kosaraju {
    private static void dfsOrder(DirectedGraph graph, int vertex,
                                 boolean[] visited, Stack<Integer> stack) {
        visited[vertex] = true;

        for (int neighbor : graph.getNeighbors(vertex)) {
            if (!visited[neighbor]) {
                dfsOrder(graph, neighbor, visited, stack);
            }
        }

        stack.push(vertex);
    }
    private static void dfsComponent(DirectedGraph graph, int vertex,
                                     boolean[] visited, List<Integer> component) {
        visited[vertex] = true;
        component.add(vertex);

        for (int neighbor : graph.getNeighbors(vertex)) {
            if (!visited[neighbor]) {
                dfsComponent(graph, neighbor, visited, component);
            }
        }
    }

    public static List<List<Integer>> findSCC(DirectedGraph graph) {
        int vertices = graph.getVertices();
        boolean[] visited = new boolean[vertices];
        Stack<Integer> stack = new Stack<>();

        for (int i = 0; i < vertices; i++) {
            if (!visited[i]) {
                dfsOrder(graph, i, visited, stack);
            }
        }
        DirectedGraph reversed = new DirectedGraph(vertices);

        for (int i = 0; i < vertices; i++) {
            for (int neighbor : graph.getNeighbors(i)) {
                reversed.addEdge(neighbor, i);
            }
        }

        visited = new boolean[vertices];
        List<List<Integer>> components = new ArrayList<>();

        while (!stack.isEmpty()) {
            int vertex = stack.pop();

            if (!visited[vertex]) {
                List<Integer> component = new ArrayList<>();
                dfsComponent(reversed, vertex, visited, component);
                components.add(component);
            }
        }
        return components;
    }
}
