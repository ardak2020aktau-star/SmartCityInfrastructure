package org.example;

import graph.Edge;
import shortestpath.Dijkstra;
import java.util.ArrayList;
import java.util.List;

public class Main {
    public static void main(String[] args) {
        List<Edge> roads = new ArrayList<>();

        roads.add(new Edge(0, 1, 4));
        roads.add(new Edge(1, 2, -5));
        roads.add(new Edge(0, 2, 2));
        try {
            Dijkstra.shortestPathsWithRoutes(3, roads, 0);
            System.out.println("Test failed");
        } catch (IllegalArgumentException e) {
            System.out.println("Negative weight detected!");
            System.out.println(e.getMessage());
        }
    }
}
