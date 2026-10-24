package org.example;
import connectivity.DirectedGraph;
import connectivity.Kosaraju;
public class Main {
    public static void main(String[] args) {
        DirectedGraph city = new DirectedGraph(5);

        city.addEdge(0, 1);
        city.addEdge(1, 2);
        city.addEdge(2, 0);

        city.addEdge(2, 3);
        city.addEdge(3, 4);
        city.addEdge(4, 3);

        System.out.println("Strongly Connected Components:");
        System.out.println(Kosaraju.findSCC(city));
    }
}
