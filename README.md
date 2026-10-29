# Smart City Infrastructure and Route Planning

## Overview

This project is a Java application designed to model a smart city's infrastructure network using graph algorithms.

The main objective is to analyze connectivity, calculate minimum-cost infrastructure networks, and find shortest routes between city districts.

The city is represented as a graph:
- Vertices represent city districts or locations.
- Edges represent roads or infrastructure connections.
- Edge weights represent construction costs or travel distances.

The application implements the following algorithms:
- Kruskal's Minimum Spanning Tree algorithm
- Prim's Minimum Spanning Tree algorithm
- Disjoint Set Union (DSU)
- Topological Sorting using Kahn's algorithm
- Strongly Connected Components using Kosaraju's algorithm
- Dijkstra's Shortest Path algorithm

The project also includes graph generators, performance benchmarks, operation counters, and visualization of experimental results.

## Implementation

The project is implemented in Java using Maven.

### Graph Representation

The `graph` package contains the `Graph` and `Edge` classes.

The `Edge` class stores the source vertex, destination vertex, and weight of a connection.

The `Graph` class provides basic graph operations, including adding and displaying edges.

For directed graphs, the `DirectedGraph` class uses an adjacency list to store outgoing connections.

### Disjoint Set Union (DSU)

The `DSU` class implements the union-find data structure.

It uses:
- Path compression to shorten the paths to root vertices.
- Union by rank to keep the trees balanced.

DSU is used by Kruskal's algorithm to detect whether adding an edge would create a cycle.

### Minimum Spanning Tree

Kruskal's algorithm sorts edges by weight and uses DSU to select edges without creating cycles.

Prim's algorithm starts from a vertex and expands the minimum spanning tree using a priority queue.

Both algorithms calculate the total weight of the MST and check whether the graph is connected.

### Topological Sorting

Topological sorting is implemented using Kahn's algorithm.

The algorithm calculates the in-degree of each vertex and processes vertices with zero incoming edges.

If not all vertices can be processed, the graph contains a directed cycle.

### Strongly Connected Components

Kosaraju's algorithm is used to identify strongly connected components.

It performs a DFS traversal, reverses the graph, and performs a second DFS traversal to identify SCCs.

### Shortest Paths

Dijkstra's algorithm calculates shortest distances from a selected source vertex.

A priority queue is used to select the next vertex with the smallest tentative distance.

The implementation supports:
- Shortest distance calculation
- Path reconstruction
- Unreachable vertices
- Non-negative edge weight validation

## Complexity Analysis

The complexity analysis assumes adjacency lists, binary-heap priority queues, and DSU with path compression and union by rank.

Here, V represents the number of vertices and E represents the number of edges.

| Algorithm | Time Complexity | Space Complexity |
|---|---|---|
| DSU find/union | O(α(V)) amortized | O(V) |
| Kruskal | O(E log E + Eα(V)) | O(V + E) |
| Prim | O((V + E) log V) | O(V + E) |
| Topological Sort | O(V + E) | O(V + E) |
| Kosaraju SCC | O(V + E) | O(V + E) |
| Dijkstra | O((V + E) log V) | O(V + E) |

### Disjoint Set Union (DSU)

DSU supports two main operations: find and union.

Path compression reduces the depth of trees during find operations, while union by rank prevents trees from becoming unnecessarily tall.

Together, these optimizations provide an amortized time complexity of O(α(V)) per operation, where α is the inverse Ackermann function.

The parent and rank arrays require O(V) memory.

### Kruskal's Algorithm

Kruskal sorts all edges in ascending order of weight and processes them using DSU.

Sorting requires O(E log E) time. DSU operations require O(Eα(V)) time in total.

Therefore, the overall time complexity is O(E log E + Eα(V)).

The algorithm requires O(V + E) additional memory for the DSU structure and the sorted edge list.

### Prim's Algorithm

Prim's algorithm uses an adjacency list and a priority queue.

Building the adjacency list takes O(V + E) time.

Priority queue operations take O(log V) time for each inserted or removed edge, giving an overall time complexity of O((V + E) log V) for a simple graph.

The adjacency list, visited array, and priority queue require O(V + E) space.

### Topological Sorting

Kahn's algorithm first calculates the in-degree of each vertex.

Each vertex is processed once, and each edge is examined during in-degree calculation and traversal.

The time complexity is O(V + E).

The adjacency list, in-degree array, queue, and resulting ordering require O(V + E) space.

If the graph contains a directed cycle, the algorithm cannot produce a complete topological ordering.

### Kosaraju's Algorithm

Kosaraju's algorithm performs two depth-first searches and constructs the reversed graph.

Each DFS takes O(V + E) time, and reversing the graph also takes O(V + E).

The total time complexity is O(V + E).

The reversed graph, visited arrays, stack, and SCC results require O(V + E) space.

### Dijkstra's Algorithm

Dijkstra uses an adjacency list and a priority queue to find shortest paths from a selected source.

With a binary heap, the time complexity is O((V + E) log V) for a simple graph.

The adjacency list, distance array, parent array, and priority queue require O(V + E) space.

Dijkstra requires non-negative edge weights because its greedy approach assumes that shortest distances will not decrease after a vertex has been finalized.

### Impact of Graph Size

As V and E increase, algorithms must process more graph data.

Kruskal and Prim include sorting or priority queue operations, which introduce logarithmic factors.

Topological Sort and Kosaraju have linear O(V + E) complexity.

Dijkstra also uses a priority queue, so its runtime generally increases faster than linear traversal algorithms as graph size grows.

Actual execution times may differ because of JVM optimization, memory allocation, graph structure, and system load.

## Experimental Setup

The performance of the implemented algorithms was evaluated using three graph configurations.

| Graph Size | Vertices (V) | Edges (E) |
|---|---:|---:|
| Small | 10 | 30 |
| Medium | 100 | 500 |
| Large | 1000 | 5000 |

### Graph Generation

All graphs were generated using a fixed random seed of 42 to ensure reproducibility.

For MST experiments, connected undirected graphs were generated. A chain connecting all vertices was created first, followed by additional randomly generated edges.

For directed graph experiments, two types of graphs were generated:

- DAG: edges were directed from smaller to larger vertex indices, ensuring that no directed cycles exist.
- Cyclic graphs: a cycle (0 -> 1 -> 2 -> 0) was created first, followed by additional random directed edges.

Duplicate edges and self-loops were excluded during graph generation.

For Dijkstra experiments, generated edges were interpreted as directed connections with positive weights.

### Measurement Procedure

Each algorithm was tested on all three graph sizes.

The following procedure was used:

1. Generate a graph using the fixed random seed.
2. Execute three warm-up runs before measurement.
3. Run each algorithm five times.
4. Measure execution time using System.nanoTime().
5. Calculate average execution time in milliseconds.
6. Record relevant operation counts.
7. Export results to a CSV file.

The benchmark results are stored in:

`results/tables/benchmark_results.csv`

Performance plots are stored in:

`results/plots/`

The experiments were executed using Java and Maven.

### Operation Counting

The following operations were recorded:

- Kruskal: examined edges, DSU find calls, and union calls.
- Prim: examined adjacency entries and priority queue operations.
- Dijkstra: examined directed edges and priority queue operations.
- Topological Sort: examined directed edges.
- Kosaraju: examined edges during DFS and graph reversal, and DFS vertex visits.

Operation counts use algorithm-specific definitions and should not be interpreted as identical units of computational work.

## Results

The following tables summarize the average execution times measured over five runs.

### MST Performance Comparison

| Vertices | Edges | Kruskal (ms) | Prim (ms) |
|---:|---:|---:|---:|
| 10 | 30 | 0.037800 | 0.071820 |
| 100 | 500 | 0.408320 | 0.368540 |
| 1000 | 5000 | 1.379980 | 1.303040 |

### Shortest Path Performance

| Vertices | Edges | Dijkstra (ms) |
|---:|---:|---:|
| 10 | 30 | 0.041660 |
| 100 | 500 | 0.226000 |
| 1000 | 5000 | 0.978500 |

### Directed Graph Performance

| Vertices | Edges | Topological DAG (ms) | Topological Cyclic (ms) | Kosaraju SCC (ms) |
|---:|---:|---:|---:|---:|
| 10 | 30 | 0.022960 | 0.007240 | 0.043600 |
| 100 | 500 | 0.153260 | 0.058840 | 0.149220 |
| 1000 | 5000 | 0.588960 | 0.312260 | 0.944200 |

### Performance Plots

#### Execution Time vs Number of Vertices

![Execution Time vs Vertices](results/plots/time_vs_vertices.png)

#### Execution Time vs Number of Edges

![Execution Time vs Edges](results/plots/time_vs_edges.png)

Both plots compare Kruskal's and Prim's algorithms using the measured benchmark results.

The number of vertices and edges increases together across the three graph configurations. Therefore, the plots illustrate performance changes across the selected graph sizes rather than the independent effect of each variable.

### Operation Counts

Detailed operation counts for all algorithms are available in:

[Benchmark Results CSV](results/tables/benchmark_results.csv)

The CSV includes the average execution time, examined edges, and algorithm-specific operation counts for each graph configuration.

## Comparison and Discussion

### Kruskal vs Prim

Both Kruskal's and Prim's algorithms find a minimum spanning tree for a connected, undirected, weighted graph.

Kruskal sorts all edges by weight and uses DSU to avoid cycles. Prim starts from one vertex and expands the MST using a priority queue.

According to the experimental results:

- For 10 vertices, Kruskal was faster (0.037800 ms vs 0.071820 ms).
- For 100 vertices, Prim was faster (0.368540 ms vs 0.408320 ms).
- For 1000 vertices, Prim was slightly faster (1.303040 ms vs 1.379980 ms).

The results show that Prim performed better on the medium and large graphs in these measurements.

However, the difference may depend on graph structure, JVM optimization, and system load. Five runs are not enough to establish a universal performance advantage.

### DSU Performance

Kruskal uses Disjoint Set Union with path compression and union by rank.

Path compression reduces the length of paths to root vertices, while union by rank prevents trees from becoming unnecessarily deep.

These optimizations make find and union operations very efficient, with amortized complexity O(α(V)).

### Strongly Connected Components

Kosaraju's algorithm has theoretical time complexity O(V + E).

The measured execution times were:

- 10 vertices: 0.043600 ms
- 100 vertices: 0.149220 ms
- 1000 vertices: 0.944200 ms

The execution time increased as the graph became larger, which is consistent with the algorithm processing more vertices and edges.

### Topological Sorting

Topological sorting was tested on both DAGs and cyclic graphs.

For DAGs, the algorithm returned a valid ordering.

For cyclic graphs, the algorithm detected that a complete topological ordering was impossible.

The cyclic graph experiments were faster in the recorded measurements. This does not imply that cycle detection is always faster, since performance depends on graph structure and processing order.

### Dijkstra's Algorithm

Dijkstra's algorithm was tested using directed graphs with non-negative edge weights.

The measured execution times were:

- 10 vertices: 0.041660 ms
- 100 vertices: 0.226000 ms
- 1000 vertices: 0.978500 ms

Execution time increased with graph size.

The algorithm uses a priority queue to efficiently select vertices with the smallest tentative distances.

The implementation also supports path reconstruction and unreachable vertices.

### Theoretical vs Experimental Results

The theoretical complexity analysis describes how algorithms scale as the input size increases.

The experimental results generally show increased execution time for larger graphs.

However, real execution times are influenced by additional factors such as JVM warm-up, garbage collection, CPU scheduling, and memory allocation.

Furthermore, the number of vertices and edges increased together in the experiments, so their individual effects cannot be isolated from these measurements.

Overall, the experimental results are broadly consistent with the expected algorithmic behavior, but additional repetitions would be needed for more reliable performance comparisons.

## Testing

Automated tests were implemented using JUnit 5.

The test suite includes 10 tests covering:

- Kruskal and Prim MST correctness
- Disconnected graph detection
- Topological sorting for DAGs
- Cycle detection in directed graphs
- Strongly connected components
- Dijkstra shortest distances and paths
- Unreachable vertices
- Negative edge weight validation
- Single-vertex graphs
- Duplicate edge weights

### Test Results

The tests were executed using Maven Surefire.

- Tests run: 10
- Failures: 0
- Errors: 0
- Skipped: 0
- Build status: SUCCESS

To reproduce the tests, run:

    mvn test

All 10 automated tests passed successfully.

## Conclusion

This project demonstrates how graph algorithms can be applied to smart city infrastructure planning and route optimization.

The main objectives of the assignment were achieved through the implementation of minimum spanning tree algorithms, connectivity analysis, and shortest path calculations.

Kruskal's and Prim's algorithms were used to find minimum-cost infrastructure networks. Both algorithms produced the same MST weights for the tested connected graphs.

Disjoint Set Union improved Kruskal's efficiency through path compression and union by rank.

Topological sorting was used to analyze dependencies in directed graphs and detect cycles. Kosaraju's algorithm identified strongly connected components in infrastructure networks.

Dijkstra's algorithm calculated shortest distances and reconstructed routes between city districts while handling unreachable vertices and rejecting negative edge weights.

Performance experiments were conducted using graphs with 10, 100, and 1000 vertices. Each algorithm was measured five times using System.nanoTime().

The experimental results showed that execution time generally increased with graph size. Prim was slightly faster than Kruskal on the medium and large graphs in the recorded measurements, although performance differences depend on graph structure and execution conditions.

All 10 automated JUnit tests passed successfully.

Overall, the project demonstrates that different graph algorithms are suitable for different smart city planning tasks:

- Kruskal and Prim are appropriate for minimum-cost infrastructure construction.
- DSU supports efficient connectivity management.
- Topological sorting is useful for scheduling infrastructure dependencies.
- Kosaraju identifies mutually reachable groups of locations.
- Dijkstra finds efficient routes through weighted road networks.

The project provides a foundation for further development of smart city infrastructure analysis and route planning systems.
