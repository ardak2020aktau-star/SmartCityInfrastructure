package org.example;

import graph.Edge;
import mst.Kruskal;
import java.util.ArrayList;
import java.util.List;

public class Main {
    public static void main(String[] args) {
        List<Edge> roads = new ArrayList<>();

        roads.add(new Edge(0, 1, 10));

        int result = Kruskal.findMST(3, roads);

        System.out.println("MST result: " + result);
    }
}
