package experiment;

import connectivity.DirectedGraph;
import connectivity.Kosaraju;
import connectivity.TopologicalSort;
import graph.Edge;
import mst.Kruskal;
import mst.Prim;
import shortestpath.Dijkstra;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.util.ArrayList;
import java.util.List;

public class ResultsExporter {
    private static final int[] VERTICES = {10, 100, 1000};
    private static final int[] EDGES = {30, 500, 5000};
    private static final int REPEATS = 5;
    private static final long SEED = 42;
    public static void export() throws IOException {
        List<String> rows = new ArrayList<>();
        rows.add("Algorithm,GraphType,Vertices,Edges,AverageMs,EdgesExamined,OtherOperations");
        for (int i = 0; i < VERTICES.length; i++) {
            int v = VERTICES[i];
            int e = EDGES[i];
            List<Edge> roads =
                    GraphGenerator.generateConnectedGraph(v, e, SEED);
            DirectedGraph dag =
                    DirectedGraphGenerator.generateDAG(v, e, SEED);
            DirectedGraph cyclic =
                    DirectedGraphGenerator.generateCyclicGraph(v, e, SEED);
            for (int j = 0; j < 3; j++) {
                Kruskal.findMSTWithStats(v, roads);
                Prim.findMSTWithStats(v, roads);
                Dijkstra.shortestPathsWithRoutes(v, roads, 0);
                TopologicalSort.sortWithStats(dag);
                TopologicalSort.sortWithStats(cyclic);
                Kosaraju.findSCCWithStats(cyclic);
            }
            long[] times = new long[6];
            long[] examined = new long[6];
            long[] operations = new long[6];
            for (int j = 0; j < REPEATS; j++) {
                long start;
                start = System.nanoTime();
                Kruskal.Result k = Kruskal.findMSTWithStats(v, roads);
                times[0] += System.nanoTime() - start;
                examined[0] += k.edgesExamined;
                operations[0] += k.findOperations + k.unionOperations;
                start = System.nanoTime();
                Prim.Result p = Prim.findMSTWithStats(v, roads);
                times[1] += System.nanoTime() - start;
                examined[1] += p.edgesExamined;
                operations[1] += p.queueOperations;
                start = System.nanoTime();
                Dijkstra.Result d =
                        Dijkstra.shortestPathsWithRoutes(v, roads, 0);
                times[2] += System.nanoTime() - start;
                examined[2] += d.edgesExamined;
                operations[2] += d.queueOperations;
                start = System.nanoTime();
                TopologicalSort.Result t =
                        TopologicalSort.sortWithStats(dag);
                times[3] += System.nanoTime() - start;
                examined[3] += t.edgesExamined;
                start = System.nanoTime();
                TopologicalSort.Result tc =
                        TopologicalSort.sortWithStats(cyclic);
                times[4] += System.nanoTime() - start;
                examined[4] += tc.edgesExamined;
                start = System.nanoTime();
                Kosaraju.Result s =
                        Kosaraju.findSCCWithStats(cyclic);
                times[5] += System.nanoTime() - start;
                examined[5] += s.edgesExamined;
                operations[5] += s.dfsVisits;
            }
            String[] names = {
                    "Kruskal", "Prim", "Dijkstra",
                    "TopologicalSort", "TopologicalSort", "Kosaraju"
            };
            String[] types = {
                    "Undirected", "Undirected", "Directed",
                    "DAG", "Cyclic", "Cyclic"
            };
            for (int a = 0; a < names.length; a++) {
                double averageMs =
                        times[a] / (double) REPEATS / 1_000_000.0;
                rows.add(String.format(
                        java.util.Locale.US,
                        "%s,%s,%d,%d,%.6f,%d,%d",
                        names[a], types[a], v, e, averageMs,
                        examined[a] / REPEATS,
                        operations[a] / REPEATS
                ));
            }
        }
        Path output = Path.of("results", "tables", "benchmark_results.csv");
        Files.createDirectories(output.getParent());
        Files.write(output, rows);
        System.out.println("Results saved to: "
                + output.toAbsolutePath());
    }
}