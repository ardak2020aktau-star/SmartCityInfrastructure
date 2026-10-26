package experiment;

import connectivity.DirectedGraph;
import connectivity.TopologicalSort;
import connectivity.Kosaraju;

public class DirectedBenchmark {
    private static final int[] VERTICES = {10, 100, 1000};
    private static final int[] EDGES = {30, 500, 5000};
    private static final int REPEATS = 5;
    private static final long SEED = 42;
    public static void runBenchmarks() {

        System.out.println("Directed Graph Benchmark");
        System.out.println("------------------------");
        for (int i = 0; i < VERTICES.length; i++) {
            int vertices = VERTICES[i];
            int edges = EDGES[i];
            DirectedGraph dag =
                    DirectedGraphGenerator.generateDAG(
                            vertices, edges, SEED
                    );
            DirectedGraph cyclic =
                    DirectedGraphGenerator.generateCyclicGraph(
                            vertices, edges, SEED
                    );
            for (int j = 0; j < 3; j++) {
                TopologicalSort.sort(dag);
                TopologicalSort.sort(cyclic);
                Kosaraju.findSCC(cyclic);
            }
            long dagTotal = 0;
            long cyclicTotal = 0;
            long sccTotal = 0;
            for (int j = 0; j < REPEATS; j++) {
                long start = System.nanoTime();
                var dagOrder = TopologicalSort.sort(dag);
                dagTotal += System.nanoTime() - start;

                start = System.nanoTime();
                var cyclicOrder = TopologicalSort.sort(cyclic);
                cyclicTotal += System.nanoTime() - start;

                start = System.nanoTime();
                var components = Kosaraju.findSCC(cyclic);
                sccTotal += System.nanoTime() - start;

                if (dagOrder.size() != vertices) {
                    throw new IllegalStateException(
                            "DAG ordering failed"
                    );
                }
                if (!cyclicOrder.isEmpty()) {
                    throw new IllegalStateException(
                            "Cycle was not detected"
                    );
                }
                if (components.isEmpty()) {
                    throw new IllegalStateException(
                            "SCC detection failed"
                    );
                }
            }
            System.out.println();
            System.out.println("Vertices: " + vertices);
            System.out.println("Edges: " + edges);
            System.out.printf(
                    "Topological DAG: %.4f ms%n",
                    dagTotal / (double) REPEATS / 1_000_000
            );
            System.out.printf(
                    "Topological Cyclic: %.4f ms%n",
                    cyclicTotal / (double) REPEATS / 1_000_000
            );
            System.out.printf(
                    "Kosaraju SCC: %.4f ms%n",
                    sccTotal / (double) REPEATS / 1_000_000
            );
        }
    }
}
