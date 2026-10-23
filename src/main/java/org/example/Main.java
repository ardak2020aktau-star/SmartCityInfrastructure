package org.example;

import graph.Edge;
import mst.Kruskal;
import mst.Prim;
import java.util.ArrayList;
import java.util.List;

public class Main {
    public static void main(String[] args) {
        List<Edge> roads = new ArrayList<>();

        roads.add(new Edge(0, 1, 5));
        roads.add(new Edge(0, 2, 5));
        roads.add(new Edge(1, 3, 5));
        roads.add(new Edge(2, 3, 5));
        roads.add(new Edge(0, 3, 10));

        System.out.println("=== Kruskal ===");
        int kruskalWeight = Kruskal.findMST(4, roads);

        System.out.println("\n=== Prim ===");
        int primWeight = Prim.findMST(4, roads);

        System.out.println("\n=== Comparison ===");
        System.out.println("Kruskal MST: " + kruskalWeight);
        System.out.println("Prim MST: " + primWeight);
        System.out.println("Same weight: "
                + (kruskalWeight == primWeight));
    }
}
