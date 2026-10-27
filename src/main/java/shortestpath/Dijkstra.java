package shortestpath;
import graph.Edge;
import java.util.*;

public class Dijkstra {
    public static class Result {
        public long[] distances;
        public int[] parent;
        public long edgesExamined;
        public long queueOperations;
        public Result(long[] distances, int[] parent) {
            this.distances = distances;
            this.parent = parent;
        }
        public List<Integer> getPath(int target) {
            List<Integer> path = new ArrayList<>();
            if (target < 0 || target >= distances.length) {
                throw new IllegalArgumentException("Invalid target");
            }

            if (distances[target] == Long.MAX_VALUE) {
                return path;
            }

            for (int at = target; at != -1; at = parent[at]) {
                path.add(at);
            }
            Collections.reverse(path);
            return path;
        }
    }
    public static Result shortestPathsWithRoutes(
            int vertices, List<Edge> edges, int source) {
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
        long[] distances = new long[vertices];
        int[] parent = new int[vertices];

        Arrays.fill(distances, Long.MAX_VALUE);
        Arrays.fill(parent, -1);
        distances[source] = 0;
        PriorityQueue<long[]> queue = new PriorityQueue<>(
                Comparator.comparingLong(a -> a[1])
        );
        queue.add(new long[]{source, 0});
        long edgesExamined = 0;
        long queueOperations = 1;
        while (!queue.isEmpty()) {
            long[] current = queue.poll();
            queueOperations++;
            int vertex = (int) current[0];
            long currentDistance = current[1];

            if (currentDistance != distances[vertex]) {
                continue;
            }

            for (Edge edge : adjacency.get(vertex)) {
                edgesExamined++;
                int neighbor = edge.destination;
                long newDistance = distances[vertex] + edge.weight;

                if (newDistance < distances[neighbor]) {
                    distances[neighbor] = newDistance;
                    parent[neighbor] = vertex;
                    queue.add(new long[]{neighbor, newDistance});
                    queueOperations++;
                }
            }
        }
        Result result = new Result(distances, parent);
        result.edgesExamined = edgesExamined;
        result.queueOperations = queueOperations;
        return result;
    }
    public static int[] shortestPaths(
            int vertices, List<Edge> edges, int source) {
        Result result = shortestPathsWithRoutes(vertices, edges, source);
        int[] distances = new int[vertices];

        for (int i = 0; i < vertices; i++) {
            if (result.distances[i] >= Integer.MAX_VALUE) {
                distances[i] = Integer.MAX_VALUE;
            } else {
                distances[i] = (int) result.distances[i];
            }
        }
        return distances;
    }
}
