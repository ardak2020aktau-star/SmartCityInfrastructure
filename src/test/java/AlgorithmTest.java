import connectivity.DirectedGraph;
import connectivity.Kosaraju;
import connectivity.TopologicalSort;
import graph.Edge;
import mst.Kruskal;
import mst.Prim;
import shortestpath.Dijkstra;
import org.junit.jupiter.api.Test;
import java.util.List;
import static org.junit.jupiter.api.Assertions.*;

public class AlgorithmTest {
    private static void verifyTree(int vertices, List<Edge> edges, int weight) {
        assertEquals(vertices - 1, edges.size());
        dsu.DSU components = new dsu.DSU(vertices);
        int sum = 0;
        for (Edge edge : edges) {
            assertTrue(components.union(edge.source, edge.destination));
            sum += edge.weight;
        }
        assertEquals(weight, sum);
        for (int i = 0; i < vertices; i++) {
            assertEquals(components.find(0), components.find(i));
        }
    }
    @Test
    void mstAlgorithmsReturnSameWeight() {
        List<Edge> edges = List.of(
                new Edge(0, 1, 10),
                new Edge(0, 2, 6),
                new Edge(0, 3, 5),
                new Edge(1, 3, 15),
                new Edge(2, 3, 4)
        );
        assertEquals(19, Kruskal.findMST(4, edges));
        assertEquals(19, Prim.findMST(4, edges));
        verifyTree(4, Kruskal.findMSTWithStats(4, edges).selectedEdges, 19);
        verifyTree(4, Prim.findMSTWithStats(4, edges).selectedEdges, 19);
    }
    @Test
    void disconnectedGraphIsDetected() {
        List<Edge> edges = List.of(new Edge(0, 1, 5));
        assertEquals(-1, Kruskal.findMST(3, edges));
        assertEquals(-1, Prim.findMST(3, edges));
    }
    @Test
    void topologicalSortWorksForDAG() {
        DirectedGraph graph = new DirectedGraph(3);
        graph.addEdge(0, 1);
        graph.addEdge(1, 2);
        assertEquals(List.of(0, 1, 2),
                TopologicalSort.sort(graph));
    }
    @Test
    void topologicalSortDetectsCycle() {
        DirectedGraph graph = new DirectedGraph(3);
        graph.addEdge(0, 1);
        graph.addEdge(1, 2);
        graph.addEdge(2, 0);

        assertTrue(TopologicalSort.sortWithStats(graph).hasCycle);
    }
    @Test
    void kosarajuFindsCorrectComponents() {
        DirectedGraph graph = new DirectedGraph(4);
        graph.addEdge(0, 1);
        graph.addEdge(1, 0);
        graph.addEdge(1, 2);
        graph.addEdge(2, 3);
        graph.addEdge(3, 2);
        List<List<Integer>> components =
                Kosaraju.findSCC(graph);
        assertEquals(2, components.size());
        assertTrue(components.stream()
                .anyMatch(c -> c.size() == 2
                        && c.containsAll(List.of(0, 1))));
        assertTrue(components.stream()
                .anyMatch(c -> c.size() == 2
                        && c.containsAll(List.of(2, 3))));
    }
    @Test
    void dijkstraFindsShortestPath() {
        List<Edge> edges = List.of(
                new Edge(0, 1, 4),
                new Edge(1, 2, 2),
                new Edge(0, 2, 10)
        );
        Dijkstra.Result result =
                Dijkstra.shortestPathsWithRoutes(3, edges, 0);
        assertEquals(6, result.distances[2]);
        assertEquals(List.of(0, 1, 2), result.getPath(2));
    }
    @Test
    void dijkstraHandlesUnreachableVertex() {
        List<Edge> edges = List.of(new Edge(0, 1, 5));
        Dijkstra.Result result =
                Dijkstra.shortestPathsWithRoutes(3, edges, 0);
        assertEquals(Long.MAX_VALUE, result.distances[2]);
        assertTrue(result.getPath(2).isEmpty());
    }
    @Test
    void dijkstraRejectsNegativeWeights() {
        List<Edge> edges = List.of(new Edge(0, 1, -5));
        assertThrows(IllegalArgumentException.class,
                () -> Dijkstra.shortestPathsWithRoutes(2, edges, 0));
    }
    @Test
    void singleVertexGraphWorks() {
        List<Edge> edges = List.of();
        assertEquals(0, Kruskal.findMST(1, edges));
        assertEquals(0, Prim.findMST(1, edges));
        Dijkstra.Result result =
                Dijkstra.shortestPathsWithRoutes(1, edges, 0);
        assertEquals(0, result.distances[0]);
        assertEquals(List.of(0), result.getPath(0));
    }
    @Test
    void duplicateWeightsProduceValidMST() {
        List<Edge> edges = List.of(
                new Edge(0, 1, 5),
                new Edge(0, 2, 5),
                new Edge(1, 2, 5)
        );
        assertEquals(10, Kruskal.findMST(3, edges));
        assertEquals(10, Prim.findMST(3, edges));
    }
}