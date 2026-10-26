package experiment;

import graph.Edge;
import mst.Kruskal;
import mst.Prim;
import java.util.List;

public class Benchmark {
    private static final int[] VERTICES = {10, 100, 1000};
    private static final int[] EDGES = {30, 500, 5000};
    private static final int REPEATS = 5;
    private static final long SEED = 42;

    public static void runMSTBenchmarks() {
        System.out.println("MST Benchmark Results");
        System.out.println("-----------------------------");

        for (int i = 0; i < VERTICES.length; i++) {
            int vertices = VERTICES[i];
            int edgeCount = EDGES[i];

            List<Edge> graph =
                    GraphGenerator.generateConnectedGraph(
                            vertices, edgeCount, SEED
                    );

            for (int j = 0; j < 3; j++) {
                Kruskal.findMST(vertices, graph);
                Prim.findMST(vertices, graph);
            }
            long kruskalTotal = 0;
            long primTotal = 0;

            for (int j = 0; j < REPEATS; j++) {
                long start = System.nanoTime();
                int kruskalWeight =
                        Kruskal.findMST(vertices, graph);
                long end = System.nanoTime();
                kruskalTotal += end - start;
                start = System.nanoTime();
                int primWeight =
                        Prim.findMST(vertices, graph);
                end = System.nanoTime();
                primTotal += end - start;

                if (kruskalWeight != primWeight) {
                    throw new IllegalStateException(
                            "MST weights do not match"
                    );
                }
            }

            double kruskalAverage =
                    kruskalTotal / (double) REPEATS / 1_000_000.0;
            double primAverage =
                    primTotal / (double) REPEATS / 1_000_000.0;

            System.out.println();
            System.out.println("Vertices: " + vertices);
            System.out.println("Edges: " + edgeCount);
            System.out.printf(
                    "Kruskal average: %.4f ms%n",
                    kruskalAverage
            );
            System.out.printf(
                    "Prim average: %.4f ms%n",
                    primAverage
            );
        }
    }
}
