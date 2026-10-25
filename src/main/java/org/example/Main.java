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

        int[] distances = Dijkstra.shortestPaths(4, roads, 0);
        for (int i = 0; i < distances.length; i++) {
            System.out.println("Distance to " + i + ": " + distances[i]);
        }
    }
}
