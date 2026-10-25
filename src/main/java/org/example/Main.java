package org.example;

import graph.Edge;
import shortestpath.Dijkstra;
import java.util.ArrayList;
import java.util.List;
public class Main {
    public static void main(String[] args) {
        List<Edge> roads = new ArrayList<>();
        roads.add(new Edge(0, 1, 4));
        roads.add(new Edge(0, 2, 10));
        roads.add(new Edge(1, 2, 2));
        roads.add(new Edge(1, 3, 3));
        roads.add(new Edge(2, 3, 1));

        Dijkstra.Result result =
                Dijkstra.shortestPathsWithRoutes(4, roads, 0);

        for (int i = 0; i < 4; i++) {
            System.out.println("District " + i);
            System.out.println("Distance: " + result.distances[i]);
            System.out.println("Path: " + result.getPath(i));
            System.out.println();
        }
    }
}
