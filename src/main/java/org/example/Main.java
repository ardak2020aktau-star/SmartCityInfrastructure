package org.example;

import connectivity.DirectedGraph;
import connectivity.TopologicalSort;
import connectivity.Kosaraju;
import experiment.DirectedGraphGenerator;

public class Main {
    public static void main(String[] args) {
        int vertices = 100;
        int edges = 500;
        DirectedGraph dag =
                DirectedGraphGenerator.generateDAG(
                        vertices, edges, 42
                );
        TopologicalSort.Result topo =
                TopologicalSort.sortWithStats(dag);
        System.out.println("=== Topological Sort ===");
        System.out.println("Has cycle: " + topo.hasCycle);
        System.out.println("Order size: " + topo.order.size());
        System.out.println("Edges examined: " + topo.edgesExamined);
        DirectedGraph cyclic =
                DirectedGraphGenerator.generateCyclicGraph(
                        vertices, edges, 42
                );
        Kosaraju.Result scc =
                Kosaraju.findSCCWithStats(cyclic);
        System.out.println("\n=== Kosaraju SCC ===");
        System.out.println("Components: " + scc.components.size());
        System.out.println("Edges examined: " + scc.edgesExamined);
        System.out.println("DFS visits: " + scc.dfsVisits);
    }
}