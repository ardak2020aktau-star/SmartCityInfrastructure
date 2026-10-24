package connectivity;

import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;
import java.util.Queue;

public class TopologicalSort {

    public static List<Integer> sort(DirectedGraph graph) {
        int vertices = graph.getVertices();
        int[] inDegree = new int[vertices];

        for (int i = 0; i < vertices; i++) {
            for (int neighbor : graph.getNeighbors(i)) {
                inDegree[neighbor]++;
            }
        }

        Queue<Integer> queue = new LinkedList<>();

        for (int i = 0; i < vertices; i++) {
            if (inDegree[i] == 0) {
                queue.add(i);
            }
        }

        List<Integer> order = new ArrayList<>();

        while (!queue.isEmpty()) {
            int current = queue.poll();
            order.add(current);

            for (int neighbor : graph.getNeighbors(current)) {
                inDegree[neighbor]--;

                if (inDegree[neighbor] == 0) {
                    queue.add(neighbor);
                }
            }
        }

        if (order.size() != vertices) {
            System.out.println("Cycle detected! Topological sorting is impossible.");
            return new ArrayList<>();
        }

        return order;
    }
}
