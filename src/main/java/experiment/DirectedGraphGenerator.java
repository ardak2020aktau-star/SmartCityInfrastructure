package experiment;

import connectivity.DirectedGraph;
import java.util.HashSet;
import java.util.Random;
import java.util.Set;

public class DirectedGraphGenerator {
    public static DirectedGraph generateDAG(
            int vertices, int edgeCount, long seed) {
        long maxEdges = (long) vertices * (vertices - 1) / 2;
        if (vertices < 1 || edgeCount < 0 || edgeCount > maxEdges) {
            throw new IllegalArgumentException("Invalid graph size");
        }
        DirectedGraph graph = new DirectedGraph(vertices);
        Random random = new Random(seed);
        Set<Long> usedEdges = new HashSet<>();

        while (usedEdges.size() < edgeCount) {
            int source = random.nextInt(vertices);
            int destination = random.nextInt(vertices);
            if (source >= destination) {
                continue;
            }
            long key = edgeKey(source, destination);
            if (usedEdges.add(key)) {
                graph.addEdge(source, destination);
            }
        }
        return graph;
    }

    public static DirectedGraph generateCyclicGraph(
            int vertices, int edgeCount, long seed) {
        if (vertices < 3) {
            throw new IllegalArgumentException(
                    "At least 3 vertices required"
            );
        }
        long maxEdges = (long) vertices * (vertices - 1);

        if (edgeCount < 3 || edgeCount > maxEdges) {
            throw new IllegalArgumentException("Invalid edge count");
        }
        DirectedGraph graph = new DirectedGraph(vertices);
        Random random = new Random(seed);
        Set<Long> usedEdges = new HashSet<>();

        addUniqueEdge(graph, usedEdges, 0, 1);
        addUniqueEdge(graph, usedEdges, 1, 2);
        addUniqueEdge(graph, usedEdges, 2, 0);

        while (usedEdges.size() < edgeCount) {
            int source = random.nextInt(vertices);
            int destination = random.nextInt(vertices);
            if (source == destination) {
                continue;
            }
            addUniqueEdge(graph, usedEdges, source, destination);
        }
        return graph;
    }

    private static void addUniqueEdge(
            DirectedGraph graph, Set<Long> usedEdges,
            int source, int destination) {
        long key = edgeKey(source, destination);
        if (usedEdges.add(key)) {
            graph.addEdge(source, destination);
        }
    }

    private static long edgeKey(int source, int destination) {
        return ((long) source << 32)
                | (destination & 0xffffffffL);
    }
}