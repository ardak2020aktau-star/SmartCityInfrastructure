package org.example;

import experiment.GraphGenerator;
import graph.Edge;
import mst.Kruskal;
import mst.Prim;
import java.util.List;
public class Main {
    public static void main(String[] args) {
        int vertices = 100;
        int edgeCount = 500;
        List<Edge> roads =
                GraphGenerator.generateConnectedGraph(
                        vertices, edgeCount, 42
                );
        Kruskal.Result kruskal =
                Kruskal.findMSTWithStats(vertices, roads);

        Prim.Result prim =
                Prim.findMSTWithStats(vertices, roads);

        System.out.println("=== MST Comparison ===");
        System.out.println("Kruskal MST: " + kruskal.totalWeight);
        System.out.println("Prim MST: " + prim.totalWeight);
        System.out.println("Same MST weight: "
                + (kruskal.connected
                && prim.connected
                && kruskal.totalWeight == prim.totalWeight));
        System.out.println("\n=== Kruskal Statistics ===");
        System.out.println("Edges examined: " + kruskal.edgesExamined);
        System.out.println("Find operations: " + kruskal.findOperations);
        System.out.println("Union operations: " + kruskal.unionOperations);
        System.out.println("\n=== Prim Statistics ===");
        System.out.println("Edges examined: " + prim.edgesExamined);
        System.out.println("Queue operations: " + prim.queueOperations);
    }
}
