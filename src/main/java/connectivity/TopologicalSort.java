package connectivity;

import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;
import java.util.Queue;

public class TopologicalSort {
    public static class Result {
        public List<Integer> order;
        public boolean hasCycle;
        public long edgesExamined;
        public Result(List<Integer> order,
                      boolean hasCycle, long edgesExamined) {
            this.order = order;
            this.hasCycle = hasCycle;
            this.edgesExamined = edgesExamined;
        }
    }
    public static Result sortWithStats(DirectedGraph graph) {
        int vertices = graph.getVertices();
        int[] inDegree = new int[vertices];
        long edgesExamined = 0;
        for (int i = 0; i < vertices; i++) {
            for (int neighbor : graph.getNeighbors(i)) {
                inDegree[neighbor]++;
                edgesExamined++;
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
                edgesExamined++;
                if (inDegree[neighbor] == 0) {
                    queue.add(neighbor);
                }
            }
        }
        boolean hasCycle = order.size() != vertices;
        if (hasCycle) {
            return new Result(
                    new ArrayList<>(), true, edgesExamined
            );
        }
        return new Result(order, false, edgesExamined);
    }
    public static List<Integer> sort(DirectedGraph graph) {
        return sortWithStats(graph).order;
    }
}
