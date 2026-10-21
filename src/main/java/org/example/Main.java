package org.example;

import graph.Graph;

public class Main {
    public static void main(String[] args) {
        Graph city = new Graph(4);

        city.addEdge(0, 1, 10);
        city.addEdge(1, 2, 20);
        city.addEdge(2, 3, 15);

        city.printEdges();
    }
}