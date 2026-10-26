package mst;

import dsu.DSU;
import graph.Edge;
import java.util.ArrayList;
import java.util.Comparator;
import java.util.List;

public class Kruskal {
    public static class Result {
        public int totalWeight;
        public boolean connected;
        public long edgesExamined;
        public long findOperations;
        public long unionOperations;

        public Result(int totalWeight, boolean connected,
                      long edgesExamined, long findOperations,
                      long unionOperations) {
            this.totalWeight = totalWeight;
            this.connected = connected;
            this.edgesExamined = edgesExamined;
            this.findOperations = findOperations;
            this.unionOperations = unionOperations;
        }
    }

    public static Result findMSTWithStats(int vertices, List<Edge> edges) {
        if (vertices < 1) {
            throw new IllegalArgumentException("Invalid vertex count");
        }
        List<Edge> sortedEdges = new ArrayList<>(edges);
        sortedEdges.sort(Comparator.comparingInt(e -> e.weight));

        DSU dsu = new DSU(vertices);
        int totalWeight = 0;
        int edgesUsed = 0;
        long edgesExamined = 0;

        for (Edge edge : sortedEdges) {
            edgesExamined++;
            if (dsu.union(edge.source, edge.destination)) {
                totalWeight += edge.weight;
                edgesUsed++;
                if (edgesUsed == vertices - 1) {
                    break;
                }
            }
        }

        boolean connected = edgesUsed == vertices - 1;
        return new Result(
                totalWeight,
                connected,
                edgesExamined,
                dsu.getFindCount(),
                dsu.getUnionCount()
        );
    }
    public static int findMST(int vertices, List<Edge> edges) {
        Result result = findMSTWithStats(vertices, edges);
        if (!result.connected) {
            return -1;
        }
        return result.totalWeight;
    }
}
