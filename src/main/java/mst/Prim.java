package mst;

import graph.Edge;
import java.util.ArrayList;
import java.util.Comparator;
import java.util.List;
import java.util.PriorityQueue;
public class Prim {

    public static class Result {
        public int totalWeight;
        public boolean connected;
        public long edgesExamined;
        public long queueOperations;

        public Result(int totalWeight, boolean connected,
                      long edgesExamined, long queueOperations) {
            this.totalWeight = totalWeight;
            this.connected = connected;
            this.edgesExamined = edgesExamined;
            this.queueOperations = queueOperations;
        }
    }
    public static Result findMSTWithStats(int vertices, List<Edge> edges) {
        if (vertices < 1) {
            throw new IllegalArgumentException("Invalid vertex count");
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
        long queueOperations = 0;
        long edgesExamined = 0;

        for (Edge edge : adjacency.get(0)) {
            queue.add(edge);
            queueOperations++;
            edgesExamined++;
        }

        int totalWeight = 0;
        int visitedCount = 1;

        while (!queue.isEmpty() && visitedCount < vertices) {
            Edge edge = queue.poll();
            queueOperations++;
            if (visited[edge.destination]) {
                continue;
            }
            visited[edge.destination] = true;
            visitedCount++;
            totalWeight += edge.weight;

            for (Edge next : adjacency.get(edge.destination)) {
                edgesExamined++;

                if (!visited[next.destination]) {
                    queue.add(next);
                    queueOperations++;
                }
            }
        }

        boolean connected = visitedCount == vertices;
        return new Result(
                totalWeight,
                connected,
                edgesExamined,
                queueOperations
        );
    }

    public static int findMST(int vertices, List<Edge> edges) {
        Result result = findMSTWithStats(vertices, edges);
        if (!result.connected) {
            return -1;
        }
        return result.totalWeight;
    }
}
