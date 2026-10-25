package org.example;

import graph.Edge;
import shortestpath.Dijkstra;
import java.util.ArrayList;
import java.util.List;
public class Main {
    public static void main(String[] args) {
        List<Edge> roads = new ArrayList<>();
        Dijkstra.Result result =
                Dijkstra.shortestPathsWithRoutes(1, roads, 0);

        System.out.println("Single Vertex Graph Test");
        System.out.println("Distance: " + result.distances[0]);
        System.out.println("Path: " + result.getPath(0));
    }
}
