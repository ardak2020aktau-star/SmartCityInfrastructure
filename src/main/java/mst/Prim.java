package mst;

import graph.Edge;
import java.util.ArrayList;
import java.util.List;
import java.util.PriorityQueue;
import java.util.Comparator;

public class Prim {

    public static int findMST(int vertices, List<Edge> edges) {
        if (vertices == 0) {
            return 0;
        }

        List<List<Edge>> adjacency = new ArrayList<>();

        for (int i = 0; i < vertices; i++) {
            adjacency.add(new ArrayList<>());
        }

        for (Edge edge : edges) {
            adjacency.get(edge.source).add(edge);
            adjacency.get(edge.destination).add(
                    new Edge(edge.destination, edge.source, edge.weight)
            );
        }

        boolean[] visited = new boolean[vertices];

        PriorityQueue<Edge> queue = new PriorityQueue<>(
                Comparator.comparingInt(e -> e.weight)
        );

        visited[0] = true;
        queue.addAll(adjacency.get(0));

        int totalWeight = 0;
        int visitedCount = 1;

        while (!queue.isEmpty() && visitedCount < vertices) {
            Edge edge = queue.poll();

            if (visited[edge.destination]) {
                continue;
            }

            visited[edge.destination] = true;
            visitedCount++;
            totalWeight += edge.weight;

            System.out.println("Selected: " + edge.source
                    + " - " + edge.destination
                    + " (weight: " + edge.weight + ")");

            for (Edge next : adjacency.get(edge.destination)) {
                if (!visited[next.destination]) {
                    queue.add(next);
                }
            }
        }

        if (visitedCount != vertices) {
            System.out.println("Graph is disconnected.");
            return -1;
        }

        return totalWeight;
    }
}
