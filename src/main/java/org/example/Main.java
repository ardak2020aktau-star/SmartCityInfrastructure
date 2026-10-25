package org.example;

import graph.Edge;
import shortestpath.Dijkstra;
import java.util.ArrayList;
import java.util.List;
public class Main {
    public static void main(String[] args) {
        List<Edge> roads = new ArrayList<>();
        roads.add(new Edge(0, 1, 4));
        roads.add(new Edge(1, 2, 2));
        roads.add(new Edge(2, 3, 3));

        Dijkstra.Result result =
                Dijkstra.shortestPathsWithRoutes(5, roads, 0);

        for (int i = 0; i < 5; i++) {
            System.out.println("District " + i);
            if (result.distances[i] == Long.MAX_VALUE) {
                System.out.println("Unreachable");
                System.out.println("Path: []");
            } else {
                System.out.println("Distance: " + result.distances[i]);
                System.out.println("Path: " + result.getPath(i));
            }
            System.out.println();
        }
    }
}
