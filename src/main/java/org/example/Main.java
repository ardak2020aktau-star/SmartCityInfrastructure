
package org.example;

import connectivity.DirectedGraph;
import connectivity.TopologicalSort;

public class Main {
    public static void main(String[] args) {
        DirectedGraph city = new DirectedGraph(4);

        city.addEdge(0, 1);
        city.addEdge(0, 2);
        city.addEdge(1, 3);
        city.addEdge(2, 3);

        System.out.println("Topological order:");
        System.out.println(TopologicalSort.sort(city));
    }
}
