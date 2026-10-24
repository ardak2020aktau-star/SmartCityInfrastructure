package org.example;

import connectivity.DirectedGraph;
import connectivity.TopologicalSort;

public class Main {
    public static void main(String[] args) {
        DirectedGraph city = new DirectedGraph(3);

        city.addEdge(0, 1);
        city.addEdge(1, 2);
        city.addEdge(2, 0);

        System.out.println("Testing graph with cycle:");
        System.out.println(TopologicalSort.sort(city));
    }
}
