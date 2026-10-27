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
