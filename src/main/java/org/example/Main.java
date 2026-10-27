package org.example;

import experiment.GraphGenerator;
import graph.Edge;
import shortestpath.Dijkstra;
import java.util.List;
public class Main {
    public static void main(String[] args) {
        int vertices = 100;
        int edges = 500;
        List<Edge> roads =
                GraphGenerator.generateConnectedGraph(
                        vertices, edges, 42
                );
        Dijkstra.Result result =
                Dijkstra.shortestPathsWithRoutes(
                        vertices, roads, 0
                );
        System.out.println("=== Dijkstra Statistics ===");
        System.out.println("Vertices: " + vertices);
        System.out.println("Edges: " + roads.size());
        System.out.println("Edges examined: "
                + result.edgesExamined);
        System.out.println("Queue operations: "
                + result.queueOperations);
    }
}