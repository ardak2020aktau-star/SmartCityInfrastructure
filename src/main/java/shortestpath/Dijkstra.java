package shortestpath;
import graph.Edge;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Comparator;
import java.util.List;
import java.util.PriorityQueue;

public class Dijkstra {
    public static int[] shortestPaths(int vertices,
                                      List<Edge> edges,
                                      int source) {
        if (vertices <= 0 || source < 0 || source >= vertices) {
            throw new IllegalArgumentException("Invalid graph or source");
        }

        List<List<Edge>> adjacency = new ArrayList<>();

        for (int i = 0; i < vertices; i++) {
            adjacency.add(new ArrayList<>());
        }
        for (Edge edge : edges) {
            if (edge.weight < 0) {
                throw new IllegalArgumentException(
                        "Dijkstra requires non-negative weights"
                );
            }
            if (edge.source < 0 || edge.source >= vertices ||
                    edge.destination < 0 || edge.destination >= vertices) {
                throw new IllegalArgumentException("Invalid edge vertex");
            }

            adjacency.get(edge.source).add(edge);
        }

        int[] distance = new int[vertices];
        Arrays.fill(distance, Integer.MAX_VALUE);
        distance[source] = 0;

        PriorityQueue<int[]> queue = new PriorityQueue<>(
                Comparator.comparingInt(a -> a[1])
        );

        queue.add(new int[]{source, 0});

        while (!queue.isEmpty()) {
            int[] current = queue.poll();
            int vertex = current[0];
            int currentDistance = current[1];

            if (currentDistance != distance[vertex]) {
                continue;
            }
            for (Edge edge : adjacency.get(vertex)) {
                int neighbor = edge.destination;

                long newDistance = (long) distance[vertex] + edge.weight;

                if (newDistance < distance[neighbor]) {
                    distance[neighbor] = (int) newDistance;
                    queue.add(new int[]{neighbor, distance[neighbor]});
                }
            }
        }
        return distance;
    }
}
