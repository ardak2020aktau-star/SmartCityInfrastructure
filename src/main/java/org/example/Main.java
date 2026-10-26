package org.example;

import experiment.GraphGenerator;
import graph.Edge;
import mst.Kruskal;
import mst.Prim;
import java.util.List;

public class Main {
    public static void main(String[] args) {
        int vertices = 10;
        int edgeCount = 30;
        long seed = 42;
        List<Edge> roads =
                GraphGenerator.generateConnectedGraph(
                        vertices, edgeCount, seed
                );
        System.out.println("Vertices: " + vertices);
        System.out.println("Edges: " + roads.size());

        int kruskal = Kruskal.findMST(vertices, roads);
        int prim = Prim.findMST(vertices, roads);

        System.out.println("Kruskal weight: " + kruskal);
        System.out.println("Prim weight: " + prim);
        System.out.println("MST weights match: "
                + (kruskal == prim));
    }
}