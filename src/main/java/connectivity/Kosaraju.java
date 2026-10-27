package connectivity;

import java.util.ArrayList;
import java.util.List;
import java.util.Stack;

public class Kosaraju {
    public static class Result {
        public List<List<Integer>> components;
        public long edgesExamined;
        public long dfsVisits;
        public Result(List<List<Integer>> components,
                      long edgesExamined, long dfsVisits) {
            this.components = components;
            this.edgesExamined = edgesExamined;
            this.dfsVisits = dfsVisits;
        }
    }
    private static class Counter {
        long edges = 0;
        long visits = 0;
    }
    private static void dfsOrder(
            DirectedGraph graph, int vertex,
            boolean[] visited, Stack<Integer> stack,
            Counter counter) {
        visited[vertex] = true;
        counter.visits++;
        for (int neighbor : graph.getNeighbors(vertex)) {
            counter.edges++;
            if (!visited[neighbor]) {
                dfsOrder(graph, neighbor, visited, stack, counter);
            }
        }
        stack.push(vertex);
    }
    private static void dfsComponent(
            DirectedGraph graph, int vertex,
            boolean[] visited, List<Integer> component,
            Counter counter) {
        visited[vertex] = true;
        counter.visits++;
        component.add(vertex);

        for (int neighbor : graph.getNeighbors(vertex)) {
            counter.edges++;

            if (!visited[neighbor]) {
                dfsComponent(graph, neighbor, visited,
                        component, counter);
            }
        }
    }
    public static Result findSCCWithStats(DirectedGraph graph) {
        int vertices = graph.getVertices();
        boolean[] visited = new boolean[vertices];
        Stack<Integer> stack = new Stack<>();
        Counter counter = new Counter();
        for (int i = 0; i < vertices; i++) {
            if (!visited[i]) {
                dfsOrder(graph, i, visited, stack, counter);
            }
        }
        DirectedGraph reversed = new DirectedGraph(vertices);
        for (int i = 0; i < vertices; i++) {
            for (int neighbor : graph.getNeighbors(i)) {
                reversed.addEdge(neighbor, i);
                counter.edges++;
            }
        }
        visited = new boolean[vertices];
        List<List<Integer>> components = new ArrayList<>();

        while (!stack.isEmpty()) {
            int vertex = stack.pop();
            if (!visited[vertex]) {
                List<Integer> component = new ArrayList<>();
                dfsComponent(reversed, vertex, visited,
                        component, counter);
                components.add(component);
            }
        }
        return new Result(
                components, counter.edges, counter.visits
        );
    }
    public static List<List<Integer>> findSCC(
            DirectedGraph graph) {
        return findSCCWithStats(graph).components;
    }
}
