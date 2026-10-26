package experiment;
import graph.Edge;
import shortestpath.Dijkstra;
import java.util.List;

public class DijkstraBenchmark {
    private static final int[] VERTICES = {10, 100, 1000};
    private static final int[] EDGES = {30, 500, 5000};
    private static final int REPEATS = 5;
    private static final long SEED = 42;
    public static void runBenchmarks() {
        System.out.println("Dijkstra Benchmark");
        System.out.println("------------------");
        for (int i = 0; i < VERTICES.length; i++) {
            int vertices = VERTICES[i];
            int edgeCount = EDGES[i];
            List<Edge> roads =
                    GraphGenerator.generateConnectedGraph(
                            vertices, edgeCount, SEED
                    );
            for (int j = 0; j < 3; j++) {
                Dijkstra.shortestPathsWithRoutes(vertices, roads, 0);
            }
            long totalTime = 0;
            for (int j = 0; j < REPEATS; j++) {
                long start = System.nanoTime();
                Dijkstra.Result result =
                        Dijkstra.shortestPathsWithRoutes(
                                vertices, roads, 0
                        );
                totalTime += System.nanoTime() - start;
                if (result.distances[0] != 0) {
                    throw new IllegalStateException(
                            "Invalid source distance"
                    );
                }
            }
            double averageMs =
                    totalTime / (double) REPEATS / 1_000_000.0;
            System.out.printf(
                    "V=%d E=%d Average=%.4f ms%n",
                    vertices, edgeCount, averageMs
            );
        }
    }
}