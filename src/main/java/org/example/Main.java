package org.example;
import connectivity.DirectedGraph;
import connectivity.Kosaraju;
import java.util.List;
public class Main {
    public static void main(String[] args) {
        DirectedGraph city = new DirectedGraph(6);

        city.addEdge(0, 1);
        city.addEdge(1, 2);
        city.addEdge(2, 0);

        city.addEdge(2, 3);
        city.addEdge(3, 4);
        city.addEdge(4, 3);

        city.addEdge(4, 5);
        List<List<Integer>> components = Kosaraju.findSCC(city);

        System.out.println("SCC: " + components);
        System.out.println("Number of SCC: " + components.size());

        boolean[] seen = new boolean[6];
        boolean valid = true;
        for (List<Integer> component : components) {
            for (int vertex : component) {
                if (seen[vertex]) {
                    valid = false;
                }
                seen[vertex] = true;
            }
        }

        for (boolean visited : seen) {
            if (!visited) {
                valid = false;
            }
        }
        System.out.println("Every vertex belongs to exactly one SCC: "
                + valid);
    }
}
