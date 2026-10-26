package org.example;

import connectivity.DirectedGraph;
import connectivity.TopologicalSort;
import connectivity.Kosaraju;
import experiment.DirectedGraphGenerator;

public class Main {
    public static void main(String[] args) {
        DirectedGraph dag =
                DirectedGraphGenerator.generateDAG(10, 30, 42);

        System.out.println("=== DAG ===");
        System.out.println("Topological order: "
                + TopologicalSort.sort(dag));
        DirectedGraph cyclic =
                DirectedGraphGenerator.generateCyclicGraph(10, 30, 42);
        System.out.println("\n=== Cyclic Graph ===");
        System.out.println("Topological order: "
                + TopologicalSort.sort(cyclic));
        System.out.println("SCC count: "
                + Kosaraju.findSCC(cyclic).size());
    }
}