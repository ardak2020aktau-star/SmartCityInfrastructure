package org.example;

import experiment.GraphGenerator;
import graph.Edge;
import mst.Kruskal;
import java.util.List;
public class Main {
    public static void main(String[] args) {
        List<Edge> roads =
                GraphGenerator.generateConnectedGraph(10, 30, 42);
        Kruskal.Result result =
                Kruskal.findMSTWithStats(10, roads);
        System.out.println("Kruskal Statistics");
        System.out.println("MST weight: " + result.totalWeight);
        System.out.println("Connected: " + result.connected);
        System.out.println("Edges examined: " + result.edgesExamined);
        System.out.println("Find operations: " + result.findOperations);
        System.out.println("Union operations: " + result.unionOperations);
    }
}
