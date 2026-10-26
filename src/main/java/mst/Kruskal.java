package mst;

import dsu.DSU;
import graph.Edge;
import java.util.ArrayList;
import java.util.Comparator;
import java.util.List;

public class Kruskal {

    public static int findMST(int vertices, List<Edge> edges) {
        List<Edge> sortedEdges = new ArrayList<>(edges);
        sortedEdges.sort(Comparator.comparingInt(e -> e.weight));

        DSU dsu = new DSU(vertices);
        int totalWeight = 0;
        int edgesUsed = 0;

        for (Edge edge : sortedEdges) {
            if (dsu.union(edge.source, edge.destination)) {
                totalWeight += edge.weight;
                edgesUsed++;

                if (edgesUsed == vertices - 1) {
                    break;
                }
            }
        }
        if (edgesUsed != vertices - 1) {
            System.out.println("Graph is disconnected.");
            return -1;
        }
        return totalWeight;
    }
}
