package experiment;

import graph.Edge;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Random;
import java.util.Set;

public class GraphGenerator {
    public static List<Edge> generateConnectedGraph(
            int vertices, int edgeCount, long seed) {
        if (vertices < 1) {
            throw new IllegalArgumentException(
                    "Vertices must be positive"
            );
        }
        long maxEdges = (long) vertices * (vertices - 1) / 2;

        if (edgeCount < vertices - 1 || edgeCount > maxEdges) {
            throw new IllegalArgumentException(
                    "Invalid number of edges"
            );
        }
        Random random = new Random(seed);
        List<Edge> edges = new ArrayList<>();
        Set<Long> usedEdges = new HashSet<>();

        for (int i = 1; i < vertices; i++) {
            int source = i - 1;
            int destination = i;
            int weight = random.nextInt(100) + 1;

            edges.add(new Edge(source, destination, weight));
            usedEdges.add(edgeKey(source, destination));
        }
        while (edges.size() < edgeCount) {
            int source = random.nextInt(vertices);
            int destination = random.nextInt(vertices);
            if (source == destination) {
                continue;
            }

            long key = edgeKey(source, destination);

            if (usedEdges.contains(key)) {
                continue;
            }
            int weight = random.nextInt(100) + 1;

            edges.add(new Edge(source, destination, weight));
            usedEdges.add(key);
        }
        return edges;
    }

    private static long edgeKey(int a, int b) {
        int min = Math.min(a, b);
        int max = Math.max(a, b);
        return ((long) min << 32) | (max & 0xffffffffL);
    }
}