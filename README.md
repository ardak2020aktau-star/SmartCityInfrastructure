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
